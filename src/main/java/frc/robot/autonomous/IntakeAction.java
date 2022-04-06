package frc.robot.autonomous;

import frc.robot.Robot;

import java.util.Objects;

public class IntakeAction extends Action {

    private final double power;

    public IntakeAction(double power) {
        super(Type.INTAKE);

        this.power = power;
    }

    @Override
    public String toString() {
        return "IntakeAction{" +
                "power=" + power +
                '}';
    }

    @Override
    public void run(Robot robot) {
        robot.getIntakeMotor().set(power);
    }

    @Override
    protected double[] getValues() {
        return new double[]{power};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntakeAction that = (IntakeAction) o;
        return Double.compare(that.power, power) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(power);
    }
}
