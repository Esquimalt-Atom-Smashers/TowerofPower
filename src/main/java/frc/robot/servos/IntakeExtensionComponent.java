package frc.robot.servos;

import frc.robot.ComponentBase;
import frc.robot.Robot;

public class IntakeExtensionComponent extends ComponentBase {

    private static final double MAX_ANGLE = 145;
    private static final double MIN_ANGLE = 30;
    private static final double MOVE_AMOUNT = 0.2;

    public IntakeExtensionComponent(Robot robot) {
        super(robot);

        robot.getIntakeServos().setAngle(30);
        
        robot.setOnDpadMoved(event -> {
            double eventVal = event.getValue();
            double angle = robot.getIntakeServos().getAngle();

            if (eventVal == 0) {
                System.out.println("2");

                if (angle < MAX_ANGLE) {
                    System.out.println(robot.getIntakeServos());
                    robot.getIntakeServos().setAngle(robot.getIntakeServos().getAngle() + MOVE_AMOUNT);
                }

            } else if (eventVal == 180 && angle > MIN_ANGLE) {

                System.out.println("2" + robot.getIntakeServos());
                robot.getIntakeServos().setAngle(robot.getIntakeServos().getAngle() - MOVE_AMOUNT);

            } else {
                System.out.println("1: " + eventVal);
            }
        });

    }

    
}
