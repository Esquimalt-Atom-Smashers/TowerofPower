package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
/**
 * Component to move the robot when the controller joysticks are moved.
 */
public class MovementComponent extends ComponentBase {

    public static final double SPEED_MULT = 0.8;
    public static final double ROTATE_MULT = 0.65;

    public static final double ROTATION_CORRECTION = 0.3;

    /** Creates and initialize the component with the given Robot.
     * @see ComponentBase#ComponentBase(Robot)
     */
    public MovementComponent(Robot robot) {
        super(robot);
    }

    /**
     * Moves the robot based on if the Robot's joystick is being moved. Both joysticks on the controller are used to move the Robot.
     * @see Robot#getJoystick()
     */
    @Override
    public void teleopPeriodic() {
        XboxController gamepad = robot.getXboxController2();
        
        // Axis 0 is the left joystick left and right
        // Axis 1 is the left joystick up and down
        // Axis 2 is the left trigger
        // Axis 3 is the right trigger
        // Axis 4 is the right joystick left and right
        // Axis 5 is the right joystick up and down

        // Move the robot based on the inputs of the drive controller
        double rotate = ROTATE_MULT * gamepad.getRawAxis(2);
        double drive = SPEED_MULT * -gamepad.getRawAxis(1);
        if (rotate == 0 && drive < 0) robot.move(rotate - 0.33, drive);
        else if (rotate == 0 && drive > 0) robot.move(rotate + 0.2, drive);
        else robot.move(rotate, drive);
    }

}
