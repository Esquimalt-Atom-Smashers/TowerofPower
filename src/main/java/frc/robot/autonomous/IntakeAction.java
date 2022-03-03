package frc.robot.autonomous;

import frc.robot.Robot;

public class IntakeAction extends Action {
    private final double power;

    public IntakeAction(double power) {
        super(Type.INTAKE);

        this.power = power;
    }

    @Override
    public void run(Robot robot) {
        robot.getIntakeMotor().set(power);
    }

    @Override
    protected double[] getValues() {
        return new double[]{power};
    }
}
