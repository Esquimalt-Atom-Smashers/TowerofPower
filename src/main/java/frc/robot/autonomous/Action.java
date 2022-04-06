package frc.robot.autonomous;

import frc.robot.Robot;

import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class Action {

    private final Type type;

    public Action(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public abstract void run(Robot robot);

    protected abstract double[] getValues();

    public String toSaveString() {
        return type.name().charAt(0) + " " + Arrays.stream(getValues()).mapToObj(String::valueOf).collect(Collectors.joining(" "));
    }

    public static Action fromString(String line) {
        Action action;

        // M       0.2 0.3
        // Command values[]
        String[] parts = line.split(" ");
        if (parts.length < 1) return null;
        Double[] values = parts.length == 1 ? new Double[0] :
                Arrays.stream(Arrays.copyOfRange(parts, 1, parts.length))
                        .map(Double::parseDouble)
                        .toArray(Double[]::new);

        switch (line.charAt(0)) {
            case 'M':
                action = new MoveAction(values[0], values[1]);
                break;
            case 'I':
                action = new IntakeAction(values[0]);
                break;
            case 'C':
                action = new ClimberAction(values[0]);
                break;
            case 'T':
                action = new TowerAction(values[0], values[1]);
                break;
            default: return null;
        }

        return action;
    }

    public enum Type {
        MOVE, TOWER, INTAKE, CLIMBER;
    }

}
