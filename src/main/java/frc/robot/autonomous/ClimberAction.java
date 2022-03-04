package frc.robot.autonomous;

import frc.robot.Robot;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClimberAction that = (ClimberAction) o;
        return Double.compare(that.extension, extension) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(extension);
    }
}
