package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.autonomous.ClimberAction;

public class ClimberComponent extends ComponentBase {

    public static final double MIN_POSITION = 0;
    public static final double MAX_POSITION = 15;

    private static final boolean USE_BREAK_MODE = false;

    /**
     * Generates a new component and initializes it with a Robot.
     *
     * @param robot The robot which this component will be attached to.
     */
    public ClimberComponent(Robot robot) {
        super(robot);

        if (USE_BREAK_MODE) {
            robot.getClimberMotor().setNeutralMode(NeutralMode.Brake);
        } else {
            robot.getClimberMotor().setNeutralMode(NeutralMode.Coast);
        }
    }

    public void teleopPeriodic() {
        XboxController gamepad = robot.getXboxController2();

        double motorPower = 0;
        if (gamepad.getPOV() == 0) {
            robot.getClimberMotor().set(-0.6);
            motorPower = -0.6;
        } else if (gamepad.getPOV() == 180) {
            robot.getClimberMotor().set(0.6);
            motorPower = 0.6;
        } else {
            robot.getClimberMotor().set(0);
        }
        if (robot.isRecording()) {
            robot.addAction(new ClimberAction(motorPower));
        }
    }

}
