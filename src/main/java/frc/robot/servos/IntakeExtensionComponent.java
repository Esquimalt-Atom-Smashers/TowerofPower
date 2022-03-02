package frc.robot.servos;

import frc.robot.ComponentBase;
import frc.robot.Robot;

public class IntakeExtensionComponent extends ComponentBase {

    private static final double OPEN_ANGLE = 90;
    private static final double CLOSED_ANGLE = 0;

    public IntakeExtensionComponent(Robot robot) {
        super(robot);

        robot.getIntakeServo().setAngle(OPEN_ANGLE);

        robot.setOnButtonPressed(1, event -> {
            robot.getIntakeServo().setAngle(OPEN_ANGLE);
        });
        
        robot.setOnButtonPressed(2, event -> {
            robot.getIntakeServo().setAngle(CLOSED_ANGLE);
        });

    }
    
}
