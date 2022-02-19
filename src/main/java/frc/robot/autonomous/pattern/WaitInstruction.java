package frc.robot.autonomous.pattern;

import frc.robot.Robot;

// TODO javadoc comments for this class
public class WaitInstruction extends Instruction {

    private int waitTime;

    public WaitInstruction(int waitTime) {
        this.waitTime = waitTime;
    }

    @Override
    public boolean isFinished() {
        return waitTime <= 0;
    }

    @Override
    public void activate(Robot robot) {
        waitTime--;
    }

    public int getWaitTime() {
        return waitTime;
    }
}
