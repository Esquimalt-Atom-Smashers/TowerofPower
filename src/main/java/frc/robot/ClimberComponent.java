package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.XboxController;

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

        //robot.getClimberEncoder().reset();

        if (USE_BREAK_MODE) {
            robot.getClimberMotor().setNeutralMode(NeutralMode.Brake);
        } else {
            robot.getClimberMotor().setNeutralMode(NeutralMode.Coast);
        }
    }

    public void teleopPeriodic() {
        XboxController gamepad = robot.getXboxController2();

        System.out.println(robot.getClimberEncoder().getDistance());
        System.out.println(robot.getClimberEncoder().isConnected());
        System.out.println(robot.getClimberEncoder().get());

        if (gamepad.getPOV() == 0 /*&& robot.getClimberEncoder().getDistance() < MAX_POSITION */) {
            robot.getClimberMotor().set(-0.1);//was .6
        } else if (gamepad.getPOV() == 180 /*&& robot.getClimberEncoder().getDistance() > MIN_POSITION*/) {
            robot.getClimberMotor().set(0.1);
        } else {
            robot.getClimberMotor().set(0);
        }
    }

}
