package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class ClimberComponent extends ComponentBase {

    /**
     * Generates a new component and initializes it with a Robot.
     *
     * @param robot The robot which this component will be attached to.
     */
    public ClimberComponent(Robot robot) {
        super(robot);
    }

    public void teleopPeriodic() {
        XboxController gamepad = robot.getXboxController2();

        // This could destroy the motors if used without encoders!
        
        /*
        if (gamepad.getPOV() == 0) {
            robot.getClimberMotor().set(2);
        } else if (gamepad.getPOV() == 180) {
            robot.getClimberMotor().set(1);
        } else {
            robot.getClimberMotor().set(1.513);
        }
        */
    }

}
