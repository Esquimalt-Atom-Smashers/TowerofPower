package frc.robot.events;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robot;

// TODO javadoc comments for this class
public class DpadEvent extends JoystickEvent {

    private final double value;

    public DpadEvent(Robot robot, Joystick stick, double value) {
        super(robot, stick);
        this.value = value;
    }

    public double getValue() {
        return value;
    }
    
}
