package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.autonomous.IntakeAction;

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

        double motorPower = gamepad.getRightBumper() ? -1 : gamepad.getRightTriggerAxis();
        robot.getIntakeMotor().set(motorPower);

        if (robot.isRecording()) {
            robot.addAction(new IntakeAction(motorPower));
        }
    }

}
