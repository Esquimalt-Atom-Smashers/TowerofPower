package frc.robot.autonomous;

import frc.robot.Robot;

public class ClimberAction extends Action {

    private final double extension;

    public ClimberAction(double extension) {
        super(Type.CLIMBER);
        this.extension = extension;
    }

    public double getExtension() {
        return extension;
    }

    @Override
    public void run(Robot robot) {
        robot.getClimberMotor().set(extension);
    }

    @Override
    protected double[] getValues() {
        return new double[]{ extension };
    }
}
