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

    /** The robot that fired this event. */
    private final Robot robot;
    /** If an event is consumed then subsequent event handler's shouldn't be called. This variable keeps track of that. */
    private boolean consumed = false;

    /**
     * Basic constructor for Event class.
     * @param robot the robot that fired this event.
     */
    protected Event(Robot robot) {
        this.robot = robot;
    }

    /** @see Event#robot */
    public Robot getRobot() {
        return robot;
    }

    /** @see Event#consumed */
    public boolean isConsumed() {
        return consumed;
    }

    /**
     * Consumes the event
     * @see Event#consumed
     */
    public void consumed() {
        this.consumed = true;
    }
}
