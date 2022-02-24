package frc.robot.events;

import frc.robot.Robot;

/**
 * When specific events occur on the robot it will fire an event to all of that event's corresponding event handlers.
 * The information for the corresponding event is contained in an Event object. Since different types of events contain
 * different information there are other classes which extend this one to represent those events. These include: {@link DpadEvent}
 * for events on the DPad, {@link SingleStickEvent} for when a single stick on the joystick is moved, and {@link ButtonEvent} for
 * when a button on the joystick is pressed.
 * @see Robot
 * @see EventHandler
 * @see Events
 * @see Filters
 */
public abstract class Event {
    
    private final Robot robot;
    private boolean consumed = false;

    protected Event(Robot robot) {
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
