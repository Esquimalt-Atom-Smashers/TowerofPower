package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
/**
 * Component to move the robot when the controller joysticks are moved.
 */
public class MovementComponent extends ComponentBase {

    public static final double SPEED_MULT = 0.8;
    public static final double ROTATE_MULT = 0.8;

    /** Controls if we use the second joystick to rotate the robot. */
    private static final boolean ALTERNATE_ROTATE = true;

    /** Creates and initialize the component with the given Robot.
     * @see ComponentBase#ComponentBase(Robot)
     */
    public MovementComponent(Robot robot) {
        super(robot);
    }

    /**
     * Moves the robot based on if the Robot's joystick is being moved. If {@link MovementComponent#ALTERNATE_ROTATE}
     * is true then both joysticks on the controller are used to move the Robot. Otherwise, just one joystick is used.
     * @see Robot#getJoystick()
     */
    @Override
    public void teleopPeriodic() {
        XboxController gamepad = robot.getXboxController();
        //  -stick.getY() gives an error:
        //CTR: Firm Vers could not be retrieved. Use Phoenix Tuner to check ID and firmware(CRF) version.
        
        // Stick X is the left joystick up and down
        // Stick Y is the left joystick left and right

        // Stick Z is the left trigger?

        // This is the line that works with the fancy plane joystick, it drives correctly and turns correctly
        // robot.move(stick.getZ(), ALTERNATE_ROTATE ? -stick.getY() : stick.getX()); //Wwhat is an HID?
        
        // Stick 0 is the left joystick left and right
        // Stick 1 is the left joystick up and down
        // Stick 2 is the left trigger
        // Stick 3 is the right trigger
        // Stick 4 is the right joystick left and right
        // Stick 5 is the right joystick up and down

        // THIS LINE WORKS
        robot.move( ROTATE_MULT * (ALTERNATE_ROTATE ? gamepad.getRawAxis(4) : gamepad.getRawAxis(0)), SPEED_MULT * -gamepad.getRawAxis(1));
    }

}
