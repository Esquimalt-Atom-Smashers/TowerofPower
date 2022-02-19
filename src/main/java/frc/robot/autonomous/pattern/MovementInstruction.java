package frc.robot.autonomous.pattern;

import frc.robot.Robot;

// TODO javadoc comments for this class
public class MovementInstruction extends Instruction {

    private double moveAmount;
    private double rotateAmount;

    public MovementInstruction(double moveAmount) {
        this(moveAmount, 0);
    }
    public MovementInstruction(double moveAmount, double rotateAmount) {
        this.moveAmount = moveAmount;
        this.rotateAmount = rotateAmount;
    }

    @Override
    public boolean isFinished() {
        return moveAmount == 0 && rotateAmount == 0;
    }

    @Override
    public void activate(Robot robot) {
        double moveAmount = Math.min(this.moveAmount, InstructionAutonomous.MOVE_AMOUNT);
        double rotateAmount = Math.min(this.rotateAmount, InstructionAutonomous.MOVE_AMOUNT);
        this.moveAmount -= moveAmount;
        this.rotateAmount -= rotateAmount;
        robot.move(moveAmount, rotateAmount);
    }

    public double getRotateAmount() {
        return rotateAmount;
    }

    public double getMoveAmount() {
        return moveAmount;
    }
}
