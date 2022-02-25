package frc.robot.events;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * <p>
 *     A functional interface used to handle events on the robot. Event handlers need to be added to the robot to be called.
 * </p>
 * <p>
 *     To add an EventHandler that gets called whenever a specific button is pressed on the robot's controller
 *     use {@link frc.robot.Robot#setOnButtonPressed(int, EventHandler)}.
 * </p>
 * @see Event
 * @param <T> the type of Events being handled.
 */
@FunctionalInterface
public interface EventHandler<T extends Event> {

    void receive(T event);

    default void otherwise() {

    }

    default EventHandler<T> delay(int delay) {
        return Events.delay(delay, this);
    }
    default EventHandler<T> filter(Predicate<T> tester) {
        return Events.filter(tester, this);
    }
    default EventHandler<T> combine(EventHandler<T>... others) {
        EventHandler<T>[] all = Arrays.copyOf(others, others.length + 1);
        all[others.length] = this;
        return Events.combine(all);
    }

    static <T extends Event> EventHandler<T> combineAndCreate(Consumer<T> receiver, Runnable otherwise) {
        return new EventHandler<>(){

            @Override
            public void receive(T event) {
                receiver.accept(event);
            }

            @Override
            public void otherwise() {
                otherwise.run();
            }
            
        };
    }
    
}
