package frc.robot.events;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robot;

/**
 * Superclass for all other joystick events.
 * @see Event
 * @see DpadEvent
 * @see ButtonEvent
 * @see SingleStickEvent
 */
public abstract class JoystickEvent extends Event {

    /** The joystick which the event came from */
    private final Joystick stick;

    /**
     * Simple constructor that takes in a robot and a joystick.
     */
    JoystickEvent(Robot robot, Joystick stick) {
        super(robot);
        this.stick = stick;
    }

    /** @see JoystickEvent#stick */
    public Joystick getStick() {
        return stick;
    }
    
}
