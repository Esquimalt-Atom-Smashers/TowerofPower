package frc.robot.events;

import frc.robot.Robot;

// TODO javadoc comments for this class
public abstract class Event {
    
    private final Robot robot;
    private boolean consumed = false;

    Event(Robot robot) {
        this.robot = robot;
    }

    public Robot getRobot() {
        return robot;
    }

    public boolean isConsumed() {
        return consumed;
    }

    public void consumed() {
        this.consumed = true;
    }
}
