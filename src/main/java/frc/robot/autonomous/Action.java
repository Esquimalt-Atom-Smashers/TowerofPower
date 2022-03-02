package frc.robot.autonomous;

import frc.robot.Robot;

public abstract class Action {

    private final Type type;
    private int runTimes = 1;

    public Action(Type type) {
        this.type = type;
    }

    public void setRunTimes(int runTimes) {
        this.runTimes = runTimes;
    }

    public void incrementRunTimes() {
        this.runTimes++;
    }
    public void decrementRunTimes() {
        this.runTimes--;
    }

    public int getRunTimes() {
        return runTimes;
    }

    public Type getType() {
        return type;
    }

    public abstract void run(Robot robot);

    public enum Type {
        MOVE, DELAY, INTAKE, CLIMBER;
    }

}
