package frc.robot.events;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robot;

// TODO javadoc comments for this class
public abstract class JoystickEvent extends Event {

    private final Joystick stick;

    JoystickEvent(Robot robot, Joystick stick) {
        super(robot);
        this.stick = stick;
    }

    public Joystick getStick() {
        return stick;
    }
    
}
