package frc.robot.events;

import frc.robot.Robot;

import java.util.function.Predicate;

// TODO javadoc comments for this class
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
