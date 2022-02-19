package frc.robot.events;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

/**
 * This class contains utility methods for creating event handlers.
 * <h3>Example:</h3>
 * <pre>{@code
 *     ButtonEvent.handler(() -> System.out.println("Button 3 Pressed")).delay(2).filter(Filters.isButtonDown(3));
 * }</pre>
 * <p>
 *     This example prints: "Button 3 Pressed" every time button 3 is pressed after the second time it's triggered.
 *     <table>
 *   <tr>
 *     <td> Pressed Button </td> <td> Result </td>
 *   </tr>
 *   <tr>
 *     <td> 1 </td> <td> n/a </td>
 *   </tr>
 *   <tr>
 *     <td> 3 </td> <td> n/a (delay: 1/2) </td>
 *   </tr>
 *   <tr>
 *     <td> 3 </td> <td> n/a (delay: 2/2) </td>
 *   </tr>
 *   <tr>
 *     <td> 3 </td> <td> Button 3 Pressed </td>
 *   </tr>
 *   <tr>
 *     <td> 1 </td> <td> n/a </td>
 *   </tr>
 *   <tr>
 *     <td> 3 </td> <td> Button 3 Pressed </td>
 *   </tr>
 * </table>
 * </p>
 * <p>
 *     A code snippet like the one above could be applied to the robot with the {@link frc.robot.Robot#addButtonHandler} method. This would look like:
 *     <pre>{@code
 *          robot.addButtonHandler(
 *              ButtonEvent.handler(() -> System.out.println("Button 3 Pressed")).delay(2).filter(Filters.isButtonDown(3))
 *          );
 *     }</pre>
 * </p>
 */
public final class Events {

    /**
     * This method creates a <b>new</b> event handler which will execute the inputted event handler after it itself has been executed a certain number of times (specified by {@code delay})
     * @param delay the amount of times the returned handler must be executed before beginning to execute the inputted handler.
     * @param handler the handler which gets executed after the delay has been executed.
     * @return the handler which processes the delay. If you wish to use the delay then make sure it is the result of this method that is passed on.
     */
    public static <T extends Event> EventHandler<T> delay(int delay, final EventHandler<T> handler) {
        AtomicInteger times = new AtomicInteger();
        return new EventHandler<>(){

            @Override
            public void receive(T event) {
                if (delay >= times.get()) {
                    handler.receive(event);
                } else {
                    handler.otherwise();
                    times.set(times.get() + 1);
                }
            }

            @Override
            public void otherwise() {
                handler.otherwise();
            }
            
        };
    }

    /**
     * Whenever the returned event handler receives an event it tests it by using the {@link Predicate#test(Object)} method on the inputted predicate object. If the predicate returns true, then the
     * event is passed onto the inputted event handler.
     * @param tester the functional interface which is used to validate an event.
     * @param handler the handler which gets executed should the {@code tester} accept the event.
     * @return the handler which receives all events and then passes them to the inputted event handler only if the inputted predicate returns true for the event.
     */
    public static <T extends Event> EventHandler<T> filter(Predicate<T> tester, final EventHandler<T> handler) {
        return new EventHandler<>() {

            @Override
            public void receive(T event) {
                if (tester.test(event)) {
                    handler.receive(event);
                } else {
                    handler.otherwise();
                }
            }

            @Override
            public void otherwise() {
                handler.otherwise();
            }

        };
    }

    /**
     * Whenever the returned event handler receives an event it passes them onto all the handlers. If, at any point, the event becomes consumed, all subsequent handlers won't be executed.
     * The returned event handler also passes all executions of the {@link EventHandler#otherwise()} method to the inputted event handlers.
     * @param handlers the to-be-combined handlers.
     * @return the handler that executes all the inputted handlers.
     */
    @SafeVarargs
    public static <T extends Event> EventHandler<T> combine(EventHandler<T>... handlers) {
        return new EventHandler<>() {

            @Override
            public void receive(T event) {
                for (EventHandler<T> handler : handlers) {
                    handler.receive(event);
                    if (event.isConsumed())
                        break;
                }
            }

            @Override
            public void otherwise() {
                for (EventHandler<T> handler : handlers) {
                    handler.otherwise();
                }
            }

        };
    }

    public static <T extends Event> EventHandler<T> run(Runnable runnable) {
        return event -> runnable.run();
    }
    
}
