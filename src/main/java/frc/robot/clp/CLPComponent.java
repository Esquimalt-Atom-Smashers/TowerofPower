package frc.robot.clp;

import frc.robot.ComponentBase;
import frc.robot.Robot;
import frc.robot.events.EventHandler;

public class CLPComponent extends ComponentBase {

    public CLPComponent(Robot robot) {
        super(robot);
        // Button 8 is right trigger
        robot.setOnButtonPressed(8, EventHandler.combineAndCreate(event -> {
            robot.getClpMotors().set(-1);
            robot.getReversedMotors().set(1);
        }, () -> {
            robot.getClpMotors().set(0);
            if (!robot.getJoystick().getRawButton(4)) {
                robot.getReversedMotors().set(0);
            }
        }));
        
        robot.setOnButtonPressed(4, event -> {
            robot.getReversedMotors().set(0, -1);
        });
        // robot.setOnButtonPressed(8, event -> { //trigger should be 8 but not sure
        //     robot.getShooterMotor().set(1);
        // });
        robot.setOnButtonPressed(7, event -> { //left trigger should be 7 but not sure 
            robot.getIntakeMotor1().set(1);
            robot.getIntakeMotor2().set(-1);
        });
    }
    
}
