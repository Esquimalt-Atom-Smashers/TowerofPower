package frc.robot.autonomous;

import frc.robot.Robot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AutonomousFrame {

    private int runTimes = 1;

    private final List<Action> actions = new ArrayList<>();

    public AutonomousFrame() {

    }
    public AutonomousFrame(int runTimes, List<Action> actions) {
        this.runTimes = runTimes;
        this.actions.addAll(actions);
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    public boolean isEmpty() {
        return actions.isEmpty();
    }

    public int getRunTimes() {
        return runTimes;
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

    public String toSaveString() {
        StringBuilder builder = new StringBuilder();
        builder.append(runTimes).append(":\n");
        for (Action action : actions) {
            builder.append(action.toSaveString()).append("\n");
        }
        return builder.toString();
    }

    public void run(Robot robot) {
        actions.forEach(action -> action.run(robot));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutonomousFrame frame = (AutonomousFrame) o;
        return runTimes == frame.runTimes && Objects.equals(actions, frame.actions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(runTimes, actions);
    }

    public static AutonomousFrame fromString(String[] lines) {
        String header = lines[0];

        int times = Integer.parseInt(header.substring(0, header.length() - 1));
        List<Action> actions = new ArrayList<>();
        for (int i = 1; i < lines.length; i++) {
            actions.add(Action.fromString(lines[i]));
        }

        return new AutonomousFrame(times, actions);
    }

    public static List<AutonomousFrame> readFile(String fileText) {
        List<AutonomousFrame> frames = new ArrayList<>();
        String[] lines = fileText.split("[\n\r]");

        int lastFrameEnd = -1;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].endsWith(":")) {
                if (lastFrameEnd != i) {
                    frames.add(AutonomousFrame.fromString(Arrays.copyOfRange(lines, lastFrameEnd, i - 1)));
                }
                lastFrameEnd = i;
            }
        }

        if (lastFrameEnd != lines.length - 1 && lastFrameEnd != -1) {
            frames.add(AutonomousFrame.fromString(Arrays.copyOfRange(lines, lastFrameEnd, lines.length - 1)));
        }

        return frames;
    }

    public static String framesToSaveString(List<AutonomousFrame> frames) {
        return frames.stream().map(AutonomousFrame::toSaveString).collect(Collectors.joining("\n"));
    }

}
