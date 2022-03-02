package frc.robot.autonomous;

import frc.robot.Robot;

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
    public void run(Robot robot) {
        robot.move(rotate, move);
    }
}
