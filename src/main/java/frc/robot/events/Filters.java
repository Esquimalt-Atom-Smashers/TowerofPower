package frc.robot.events;

import frc.robot.Robot;

import java.util.function.Predicate;

/**
 * This class contains methods intended to make it easier to create filters which can be added to Events.
 * Event filters are just a predicate which control if a given event should be executed. For more info about how event filters work see {@link Events#filter(Predicate, EventHandler)}
 * @see Predicate
 * @see Events#filter(Predicate, EventHandler)
 */
public final class Filters {

    /**
     * Generates an event filter which checks if a {@link ButtonEvent} corresponds to the correct button on the joystick.
     * @param button the button on the joystick being checked
     * @return the event filter, can be added to the event handler with {@link EventHandler#filter(Predicate)} or {@link Events#filter(Predicate, EventHandler)}
     */
    public static <T extends ButtonEvent> Predicate<T> isButtonDown(int button) {
        return event -> event.getButton() == button;
    }

    /**
     * Generates an event filter which checks in an event has been consumed.
     * @return the event filter, can be added to the event handler with {@link EventHandler#filter(Predicate)} or {@link Events#filter(Predicate, EventHandler)}
     */
    public static Predicate<? extends Event> isConsumed() {
        return Event::isConsumed;
    }

}
