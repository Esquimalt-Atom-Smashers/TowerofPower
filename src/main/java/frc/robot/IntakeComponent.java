package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class Intake extends ComponentBase {

    /**
     * Generates a new component and initializes it with a Robot.
     *
     * @param robot The robot which this component will be attached to.
     */
    public Intake(Robot robot) {
        super(robot);
    }

    @Override
    public void teleopPeriodic() {
        XboxController gamepad = robot.getXboxController2();

        robot.getIntakeMotor().set(gamepad.getRightTriggerAxis() + 1.513);

        if (gamepad.getRightBumper()) robot.getIntakeMotor().set(1);
    }

}
