// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.*;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.motorcontrol.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.clp.CLPMotors;
import frc.robot.events.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.servos.Servos;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

/**
 * This class contains the following important objects:
 * <li>
 *     The Joystick which is plugged into the computer running the robot, can be accessed by other classes with: {@link Robot#getJoystick()}
 * </li>
 * <li>
 *     The left and right motors for the robot's drive base, these can be controlled simultaneously with a differential driver. Get left and right motor with: {@link Robot#getLeftMotor()}
 *     and {@link Robot#getRightMotor()}, get the differential driver with {@link Robot#getDifferentialDrive()}. If you just wish to move or rotate the robot use the {@link Robot#move(double, double)} method.
 * </li>
 * <li>
 *     A group of motor controller which are used for the CLP are stored in a {@link CLPMotors} object, this class provides many methods which would exist on a single motor object but applies them to all it's
 *     motors. Get the CLPMotors with {@link Robot#getClpMotors()}
 * </li>
 * <li>
 *     A list of servos which control the linear actuators that control the CLP. These servos are contained in a {@link Servos} object, this class provides implementation of many of the methods a single servo
 *     has but applies them to all its servos. Get the Servos with {@link Robot#getActuatorServos()}
 * </li>
 * <h2>Components</h3>
 * <p>
 *     In previous years we have put large portions of the code which controls the robot into the Robot class. Although this practice does have some upsides (namely that it's easy to do) it also leads
 *     to issues with readability and code-reusability.
 * </p>
 * <p>
 *     This project attempts to fix this issue by moving the code into separate components, all of which extend the {@link ComponentBase} class. This class contains many methods which the Robot class also contains,
 *     whenever these methods are called on the robot they are also called on its components. To add a component to the robot use the: {@link Robot#addComponent(ComponentBase)} method.
 * </p>
 * <h2>Events</h3>
 * <h3>Limitations of the Components system.</h4>
 * <p>
 *     Many components are created that do some of the same things, for example: there are multiple components that do something when a button is pressed. This brings up two issues: firstly that there is little
 *     communication between components, and secondly that the code for doing something when a button is pressed is repeated many times over several components.
 * </p>
 * <h3>The event system</h3>
 * <p>
 *     The event system fixes this issue by storing variables for doing certain actions, these variables contain {@link EventHandler}s which get triggered when their respective events get called. One of these variables
 *     is the {@link Robot#eventMap}, this hashmap contains a map of integers and EventHandlers. Whenever a button is pressed, if there is a value in the {@code eventMap} that corresponds to the pressed button then
 *     that event handler receives the event, otherwise it gets its {@link EventHandler#otherwise()} method called. For an example of the event system in use see: {@link Events}
 * </p>
 */
public class Robot /* Do not change class name */ extends TimedRobot {

    public Robot() {
        initJoystickSlots(DEFAULT_JOYSTICK_SLOT_1, DEFAULT_JOYSTICK_SLOT_2);
        climberEncoder.setDistancePerRotation(2 * Math.PI);
    }

    public static Robot getWithComponents(ComponentBase... components) {
        Robot robot = new Robot();
        robot.components.addAll(Arrays.asList(components));
        return robot;
    }

    // WPILib required variables.
    private static final String kDefaultAuto = "Default";
    private static final String kCustomAuto = "My Auto";
    private String m_autoSelected;
    private final SendableChooser<String> m_chooser = new SendableChooser<>();

    /**
     * <p> For drive team: Joystick slot is determined here: </p>
     * This variable controls the slot the joystick is created from.
     */
    private static int DEFAULT_JOYSTICK_SLOT_1 = 0;
    private static int DEFAULT_JOYSTICK_SLOT_2 = 1;

    private static ArrayList<Double> actions = new ArrayList<>();

    //The four drivebase motors controlled by the Spark motor controllers (m_frontLeft/m_frontRight and m_rearLeft/m_rearLeft these may not be entirely correct, I'll double check on Tuesday)
    private final MotorController m_frontLeft = new Spark(3);
    private final MotorController m_rearLeft = new Spark(2);
    private final MotorController m_frontRight = new Spark(0);
    private final MotorController m_rearRight  = new Spark(1);

    private final MotorControllerGroup leftMotor = new MotorControllerGroup(m_frontLeft, m_rearLeft);
    private final MotorControllerGroup rightMotor = new MotorControllerGroup(m_frontRight, m_rearRight);

    /** A Differential driver which contains the {@link Robot#leftMotor} and {@link Robot#rightMotor}. */
    private final DifferentialDrive robotDrive = new DifferentialDrive(leftMotor, rightMotor);

    /** 
     * For drive team: Joystick slot is determined here:
     * A Joystick pulled from the port specified in {@link Robot#DEFAULT_JOYSTICK_SLOT_1}.
    **/
    private Joystick stick; 

    /** This just exists for situations where you want to process the {@link Robot#stick} as an XboxController. Will probably be removed in a future version. */
    private XboxController xboxController1;
    private XboxController xboxController2;

    /** A list of servos used to control the linear actuators. */
    private final Servo intakeServo = new Servo(4);

    private final Talon intakeMotor = new Talon(1);
    private final Talon climberMotor = new Talon(2);
    private final DutyCycleEncoder climberEncoder = new DutyCycleEncoder(5);

        //** A list of motors used in the tower: shooter and tower */
    private final WPI_VictorSPX towerMotor1 = new WPI_VictorSPX(3); //Assuming the Phoenix tuner ID is the same as the method parameter here, I'm also only making it a WPI_VictorSPX object for now instead of a TowerMotors object
    private final WPI_VictorSPX towerMotor2 = new WPI_VictorSPX(4); //Same assumptions as above
    private final WPI_VictorSPX shooterMotor1 = new WPI_VictorSPX(5); //Same assumptions 
    private final WPI_VictorSPX shooterMotor2 = new WPI_VictorSPX(6); //Same assumptions    


    /* ************************* *
     *      Event Variables
     * ************************* */
    /** A list of button event handlers that get called if a button is down in {@link Robot#teleopPeriodic()} */
    private final ArrayList<EventHandler<ButtonEvent>> buttonHandlers = new ArrayList<>();
    /** A list of dpad event handlers that get called if the dpad isn't in its default position. Gets called in {@link Robot#teleopPeriodic()} */
    private final ArrayList<EventHandler<DpadEvent>> dpadHandlers = new ArrayList<>();
    /** A map of integers that represent button indexes on a joystick. These are tested with {@link Joystick#getRawButton(int)}, if the method returns true (the button is down) then the event handler receives a ButtonEvent. Gets called in {@link Robot#teleopPeriodic()}  */
    private final HashMap<Integer, EventHandler<ButtonEvent>> eventMap = new HashMap<>();
    /** An event handler that receives a SingleStickEvent whenever the left joystick on the controller isn't in its original position. Gets called in {@link Robot#teleopPeriodic()} */
    private EventHandler<SingleStickEvent> leftStickHandler;
    /** An event handler that receives a SingleStickEvent whenever the right joystick on the controller isn't in its original position. Gets called in {@link Robot#teleopPeriodic()}  */
    private EventHandler<SingleStickEvent> rightStickHandler;
    /** An event handler that receives a DpadEvent whenever the dpad on the joystick isn't in its original position. Gets called in {@link Robot#teleopPeriodic()} */
    private EventHandler<DpadEvent> dpadHandler;

    /** The list of components which the robot delegates work to, see: {@link Robot}, {@link Robot#addComponent(ComponentBase)} and {@link ComponentBase} */
    private final ArrayList<ComponentBase> components = new ArrayList<>();

    private boolean disabled = true;

    /**
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */
    @Override
    public void robotInit() {
        m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
        m_chooser.addOption("My Auto", kCustomAuto);
        SmartDashboard.putData("Auto choices", m_chooser);
    }

    /**
     * This function is called every robot packet, no matter the mode. Use this for
     * items like diagnostics that you want ran during disabled, autonomous,
     * teleoperated and test.
     *
     * <p>
     * This runs after the mode specific periodic functions, but before LiveWindow
     * and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        if (disabled) {
            disabled = false;
            for (ComponentBase component : components) {
                component.enabled();
            }
        }
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable chooser
     * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
     * remove all the chooser code and uncomment the getString line to get the
     * auto name from the text box below the Gyro
     *
     * <p>
     * You can add additional auto modes by adding additional comparisons to the
     * switch structure below with additional strings. If using the SendableChooser
     * make sure to add them to the chooser code above as well.
     */
    @Override
    public void autonomousInit() {
        m_autoSelected = m_chooser.getSelected();
        // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
        System.out.println("Auto selected: " + m_autoSelected);
        components.forEach(ComponentBase::autonomousInit);
    }

    /** This function is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {
        components.forEach(ComponentBase::autonomousPeriodic);
        switch (m_autoSelected) {
            case kCustomAuto:
                // Put custom auto code here
                break;
            case kDefaultAuto:
            default:
                // Put default auto code here
                break;
        }
    }

    /** This function is called once when teleop is enabled. */
    @Override
    public void teleopInit() {
        components.forEach(ComponentBase::teleopInit);
    }

    /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {

        // This loop is used to run the teleopPeriodic method in the ComponentBases
        // stored in the components.
        components.forEach(ComponentBase::teleopPeriodic);

        // This loop is used to properly activate the event handlers in the button map.
        for (int key : eventMap.keySet()) {
            if (stick.getRawButton(key)) {
                eventMap.get(key).receive(new ButtonEvent(this, stick, key));
            } else {
                eventMap.get(key).otherwise();
            }
        }
        if (!buttonHandlers.isEmpty()) {
            boolean executed = false;
            for (int i = 0; i < stick.getButtonCount(); i++) {
                if (stick.getRawButton(i)) {
                    int finalI = i;
                    buttonHandlers.forEach(handler -> handler.receive(new ButtonEvent(this, stick, finalI)));
                    executed = true;
                }
            }
            if (!executed) {
                buttonHandlers.forEach(EventHandler::otherwise);
            }
        }
        if (!dpadHandlers.isEmpty()) {
            boolean execute = stick.getPOV() != -1;
            DpadEvent event = new DpadEvent(this, getJoystick(), stick.getPOV());
            for (EventHandler<DpadEvent> eventHandler : dpadHandlers) {
                if (event.isConsumed() || !execute) {
                    eventHandler.otherwise();
                } else {
                    eventHandler.receive(event);
                }
            }
        }

        if (leftStickHandler != null) {
            if (stick.getY() != 0 || stick.getX() != 0) {
                leftStickHandler.receive(new SingleStickEvent(this, stick, stick.getY(), stick.getX()));
            } else {
                leftStickHandler.otherwise();
            }
        }

        if (rightStickHandler != null) {
            if (stick.getY() != 0 || stick.getX() != 0) {
                rightStickHandler.receive(
                        new SingleStickEvent(this, stick, stick.getY(), stick.getX()));
            } else {
                rightStickHandler.otherwise();
            }
        }

        if (dpadHandler != null) {
            if (stick.getPOV() != -1) {
                dpadHandler.receive(new DpadEvent(this, stick, stick.getPOV()));
            } else {
                dpadHandler.otherwise();
            }
        }

    }

    /** This function is called once when the robot is disabled. */
    @Override
    public void disabledInit() {
        disabled = true;
        components.forEach(ComponentBase::disabledInit);
    }

    /** This function is called periodically when disabled. */
    @Override
    public void disabledPeriodic() {
        components.forEach(ComponentBase::disabledPeriodic);
    }

    /** This function is called once when test mode is enabled. */
    @Override
    public void testInit() {
        components.forEach(ComponentBase::testInit);
    }

    /** This function is called periodically during test mode. */
    @Override
    public void testPeriodic() {
        components.forEach(ComponentBase::testPeriodic);
    }

    public void initJoystickSlots(int slot1, int slot2) {
        stick = new Joystick(slot1);

        xboxController1 = new XboxController(slot1);
        xboxController2 = new XboxController(slot2);
    }

    /**
     * This method is used to add a component to the Robot. The added component will have its methods called by the equivalent methods in Robot.java.
     * @param component - The component you are adding to the robot.
     */
    public void addComponent(ComponentBase component) {
        components.add(component);
    }

    /**
     * This method moves the robot using the DifferentialDrive by the given x and y
     * @param moveAmount the amount the robot will move
     * @param rotation   the amount the robot will rotate
     */
    public void move(double moveAmount, double rotation) {
        robotDrive.arcadeDrive(moveAmount, rotation);
    }

    /**
     * @return The joystick which is created from the slot: {@link Robot#DEFAULT_JOYSTICK_SLOT}
     */
    public Joystick getJoystick() {
        return stick;
    }

    /**
     * @return The xbox controller created from the slot: {@link Robot#DEFAULT_JOYSTICK_SLOT_1}, will probably be removed in a later version.
     */
    public XboxController getXboxController1() {
        return xboxController1;
    }

    /**
     * @return The xbox controller created from the slot: {@link Robot#DEFAULT_JOYSTICK_SLOT_2}, will probably be removed in a later version.
     */
    public XboxController getXboxController2() {
        return xboxController2;
    }

    /**
     * @return The Differential Drive containing the drive base's left and right
     *         motors.
     */
    public DifferentialDrive getDifferentialDrive() {
        return robotDrive;
    }

    /**
     * @return The robot's left motor controller.
     */
    public MotorControllerGroup getLeftMotor() {
        return leftMotor;
    }

    /**
     * @return The robot's right motor controller.
     */
    public MotorControllerGroup getRightMotor() {
        return rightMotor;
    }

        /**
     * 
     * @return These are the getter methods for the tower motors.
     */
     
    public WPI_VictorSPX getShooterMotor1() {
        return shooterMotor1;
    }

    public WPI_VictorSPX getShooterMotor2() {
        return shooterMotor2;
    }

    public WPI_VictorSPX getTowerMotor1() {
        return towerMotor1;
    }

    public WPI_VictorSPX getTowerMotor2() {
        return towerMotor2;
    }


    /**
     * @return The robot's servos. These servos are used to control the linear actuators that move the CLP up and down. The returned object's class contains a list of servos as well as some convenience methods for running actions on all the servos in its list.
     */
    public Servo getIntakeServo() {
        return intakeServo;
    }

    /**
     * This method is effectively the {@link HashMap#put(Object, Object)} method for the robot's {@link Robot#eventMap}
     * @param button the button which needs to be pressed to activate the inputted event handler.
     * @param handler the event handler which gets activated when the inputted button gets pressed.
     */
    public void setOnButtonPressed(int button, EventHandler<ButtonEvent> handler) {
        eventMap.put(button, handler);
    }

    /**
     *
     * @param button the key of the {@link Robot#eventMap} which is checked for an EventHandler.
     * @return Either: an optional containing the button in the inputted key of the {@link Robot#eventMap}, or an empty Optional.
     */
    public Optional<EventHandler<ButtonEvent>> getOnButtonPressed(int button) {
        return eventMap.containsKey(button) ? Optional.of(eventMap.get(button)) : Optional.empty();
    }

    /**
     * @param handler the event handler which will be called whenever the left joystick on the controller is pressed.
     */
    public void setOnLeftStickMoved(EventHandler<SingleStickEvent> handler) {
        this.leftStickHandler = handler;
    }

    /**
     * @return the current value of the {@link Robot#leftStickHandler} variable. This can be null if the handler has been set to null or was never set.
     */
    public EventHandler<SingleStickEvent> getOnLeftStickMoved() {   
        return leftStickHandler;
    }

    /**
     * @param handler the event handler which will be called whenever the right joystick on the controller is pressed.
     */
    public void setOnRightStickMoved(EventHandler<SingleStickEvent> handler) {
        this.rightStickHandler = handler;
    }

    /**
     * @return the current value of the {@link Robot#rightStickHandler} variable. This can be null if the handler has been set to null or was never set.
     */
    public EventHandler<SingleStickEvent> getOnRightStickMoved() {
        return rightStickHandler;
    }

    /**
     * @param dpadEventHandler the event handler which will be called whenever the dpad isn't in its original position and the robot's {@link Robot#teleopPeriodic()} method is called.
     */
    public void setOnDpadMoved(EventHandler<DpadEvent> dpadEventHandler) {
        this.dpadHandler = dpadEventHandler;
    }

    /**
     * @return the current value of the {@link Robot#dpadHandler} variable. This can be null if the handler has been set to null or was never set.
     */
    public EventHandler<DpadEvent> getOnDpadMoved() {
        return dpadHandler;
    }

    /**
     * Adds an event handler to the robot's {@link Robot#buttonHandlers} list.
     * @param handler the handler which gets added to the Robot's buttonHandlers.
     */
    public void addButtonHandler(EventHandler<ButtonEvent> handler) {
        buttonHandlers.add(handler);
    }

    /**
     * Removes an event handler from the robot's {@link Robot#buttonHandlers} list.
     * @param handler the handler which gets removed from the Robot's buttonHandlers.
     */
    public void removeButtonHandler(EventHandler<ButtonEvent> handler) {
        buttonHandlers.remove(handler);
    }

    /**
     * Adds an event handler to the robot's {@link Robot#dpadHandler} list.
     * @param handler the handler which gets added to the Robot's dpadHandlers.
     */
    public void addDpadHandler(EventHandler<DpadEvent> handler) {
        dpadHandlers.add(handler);
    }

    /**
     * Removes an event handler from the robot's {@link Robot#dpadHandler} list.
     * @param handler the handler which gets removed from the Robot's dpadHandler.
     */
    public void removeDpadHandler(EventHandler<DpadEvent> handler) {
        dpadHandlers.remove(handler);
    }

    public Talon getIntakeMotor() {
        return intakeMotor;
    }

    public Talon getClimberMotor() {
        return climberMotor;
    }

    public DutyCycleEncoder getClimberEncoder() {
        return climberEncoder;
    }
}
