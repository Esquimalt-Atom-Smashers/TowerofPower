package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Component to move the robot when the controller joysticks are moved.
 */
public class MovementComponent extends ComponentBase {

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
        Joystick stick = robot.getJoystick();
        robot.move(stick.getY(), ALTERNATE_ROTATE ? stick.getZ() : stick.getX());
    }

}
