package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
/**
 * Component to move the robot when the controller joysticks are moved.
 */
public class MovementComponent extends ComponentBase {

    public static final double SPEED_MULT = 0.8;
    public static final double ROTATE_MULT = 0.65;

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
        XboxController gamepad1 = robot.getXboxController1();
        
        // Axis 0 is the left joystick left and right
        // Axis 1 is the left joystick up and down
        // Axis 2 is the left trigger
        // Axis 3 is the right trigger
        // Axis 4 is the right joystick left and right
        // Axis 5 is the right joystick up and down

        // Move the robot based on the inputs of the drive controller
        robot.move(ROTATE_MULT * gamepad1.getRawAxis(4), SPEED_MULT * -gamepad1.getRawAxis(1));
    }

}
