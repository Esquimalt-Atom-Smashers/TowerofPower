package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class IntakeComponent extends ComponentBase {

    /**
     * Generates a new component and initializes it with a Robot.
     *
     * @param robot The robot which this component will be attached to.
     */
    public IntakeComponent(Robot robot) {
        super(robot);
    }

    @Override
    public void teleopPeriodic() {
        XboxController gamepad = robot.getXboxController1();

        robot.getIntakeMotor().set(gamepad.getRightTriggerAxis() + 1.513);

        if (gamepad.getRightBumper()) robot.getIntakeMotor().set(1);
    }

}
