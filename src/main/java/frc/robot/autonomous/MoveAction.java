package frc.robot.autonomous;

import frc.robot.Robot;

import java.util.Objects;

public class MoveAction extends Action {

    private final double move;
    private final double rotate;

    public MoveAction(double move, double rotate) {
        super(Type.MOVE);
        this.move = move;
        this.rotate = rotate;
    }

    public double getMove() {
        return move;
    }

    public double getRotate() {
        return rotate;
    }

    @Override
    public String toString() {
        return "MoveAction{" +
                "move=" + move +
                ", rotate=" + rotate +
                '}';
    }

    @Override
    public void run(Robot robot) {
        robot.move(rotate, move);
    }

    @Override
    protected double[] getValues() {
        return new double[]{move, rotate};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoveAction that = (MoveAction) o;
        return Double.compare(that.move, move) == 0 && Double.compare(that.rotate, rotate) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(move, rotate);
    }
}
