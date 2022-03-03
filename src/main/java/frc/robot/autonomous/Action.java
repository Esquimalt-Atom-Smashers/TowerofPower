package frc.robot.autonomous;

import frc.robot.Robot;

import java.util.Arrays;
import java.util.stream.Collectors;

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

    protected abstract double[] getValues();

    public String toSaveString() {
        return type.name().charAt(0) + " " + runTimes + " " + Arrays.stream(getValues()).mapToObj(String::valueOf).collect(Collectors.joining(" "));
    }

    public static Action fromString(String line) {
        Action action;

        // M        1       0.2 0.3
        // Command runTimes values[]
        String[] parts = line.split(" ");
        if (parts.length < 2) return null;
        int runTimes = Integer.parseInt(parts[1]);
        Double[] values = parts.length == 2 ? new Double[0] :
                Arrays.stream(Arrays.copyOfRange(parts, 2, parts.length))
                        .map(Double::parseDouble)
                        .toArray(Double[]::new);

        switch (line.charAt(0)) {
            case 'M':
                action = new MoveAction(values[0], values[1]);
                break;
            case 'D':
                action = new DelayAction();
                break;
            case 'I':
                action = new IntakeAction(values[0]);
                break;
            case 'C':
                action = new ClimberAction(values[0]);
                break;
            default: return null;
        }

        action.setRunTimes(runTimes);

        return action;
    }

    public enum Type {
        MOVE, DELAY, INTAKE, CLIMBER;
    }

}
