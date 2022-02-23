package frc.robot.events;

import frc.robot.Robot;

import java.util.function.Predicate;

// TODO javadoc comments for this class

/**
 * This class contains methods intended to make it easier to create filters which can be added to Events.
 * Event filters are just a predicate which control if a given event should be executed. For more info about how event filters work see {@link Events#filter(Predicate, EventHandler)}
 * @see Predicate
 * @see Events#filter(Predicate, EventHandler)
 */
public final class Filters {

    public static <T extends ButtonEvent> Predicate<T> isButtonDown(int button) {
        return event -> event.getButton() == button;
    }

    public static Predicate<? extends Event> isConsumed() {
        return Event::isConsumed;
    }

    public static Predicate<? extends Event> isCorrectRobot(Robot compareTo) {
        return event -> event.getRobot().equals(compareTo);
    }

}
