package frc.robot.events;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robot;

// TODO javadoc comments for this class
public class SingleStickEvent extends JoystickEvent {

    private final double up;
    private final double down;

    public SingleStickEvent(Robot robot, Joystick stick, double up, double down) {
        super(robot, stick);
        this.up = up;
        this.down = down;
    }

    public double getUp() {
        return up;
    }
    public double getDown() {
        return down;
    }
    
}
