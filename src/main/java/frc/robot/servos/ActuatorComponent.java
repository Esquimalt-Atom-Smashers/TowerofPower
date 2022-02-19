package frc.robot.servos;

import frc.robot.ComponentBase;
import frc.robot.Robot;
import frc.robot.events.Events;

public class ActuatorComponent extends ComponentBase {

    private static final double MAX_ANGLE = 145;
    private static final double MIN_ANGLE = 30;
    private static final double MOVE_AMOUNT = 0.2;
    private static final int    DELAY = 6;

    public ActuatorComponent(Robot robot) {
        super(robot);

        robot.getActuatorServos().setAngle(30);

        // robot.setOnDpadMoved(Events.delay(DELAY * 30, event -> {
        //     double eventVal = event.getValue();
        //     double angle = robot.getActuatorServos().getAngle();
        //     if (eventVal == 0) {
        //         if (angle < MAX_ANGLE) {
        //             robot.getActuatorServos().setAngle(robot.getActuatorServos().getAngle() + MOVE_AMOUNT);
        //         }
        //     } else if (eventVal == 90 && angle > MIN_ANGLE) {
        //         robot.getActuatorServos().setAngle(robot.getActuatorServos().getAngle() - MOVE_AMOUNT);
        //     }
        // }));
        robot.setOnDpadMoved(event -> {
            double eventVal = event.getValue();
            double angle = robot.getActuatorServos().getAngle();
            if (eventVal == 0) {
                System.out.println("2");
                if (angle < MAX_ANGLE) {
                    System.out.println(robot.getActuatorServos());
                    robot.getActuatorServos().setAngle(robot.getActuatorServos().getAngle() + MOVE_AMOUNT);
                }
            } else if (eventVal == 180 && angle > MIN_ANGLE) {
                System.out.println("2" + robot.getActuatorServos());
                robot.getActuatorServos().setAngle(robot.getActuatorServos().getAngle() - MOVE_AMOUNT);
            } else {
                System.out.println("1: " + eventVal);
            }
        });

    }

    @Override
    public void teleopPeriodic() {
        
    }

    
}
