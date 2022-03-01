package frc.robot.servos;

import frc.robot.ComponentBase;
import frc.robot.Robot;

public class IntakeExtensionComponent extends ComponentBase {

    private static final double MAX_ANGLE = 145;
    private static final double MIN_ANGLE = 30;
    private static final double MOVE_AMOUNT = 0.2;

    public IntakeExtensionComponent(Robot robot) {
        super(robot);

        robot.setOnButtonPressed(1, event -> {
            robot.getIntakeServos().setAngle(robot.getIntakeServos().getAngle() + MOVE_AMOUNT);
        });
        
        robot.setOnButtonPressed(2, event -> {
            robot.getIntakeServos().setAngle(robot.getIntakeServos().getAngle() - MOVE_AMOUNT);
        });

    }
    
}
