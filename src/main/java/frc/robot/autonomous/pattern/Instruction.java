package frc.robot.autonomous.pattern;

import frc.robot.Robot;
import frc.robot.autonomous.pattern.pathfinding.PathfindingInstruction;

/**
 * This class is used to create an instruction which can be loaded into the PatternAutonomous class which will execute it.
 */
public abstract class Instruction {

    public abstract boolean isFinished();
    public abstract void activate(Robot robot);

    public static Instruction fromLine(String line) {
        line = line.trim().toLowerCase();
        double moveAmount = 0;
        double rotateAmount = 0;
        int wait = 0;
        if (line.contains("move")) {
            String portion = line.substring(line.indexOf("move") + 4);
            Double amount = firstNumber(portion);
            if (amount != null) {
                moveAmount = amount;
            }
        }
        if (line.contains("rotate")) {
            String portion = line.substring(line.indexOf("rotate") + 6);
            Double amount = firstNumber(portion);
            if (amount != null) {
                rotateAmount = amount;
            }
        }
        if (line.contains("wait")) {
            String portion = line.substring(line.indexOf("wait") + 4);
            Double amount = firstNumberParsingTime(portion);
            if (amount != null) {
                wait = amount.intValue();
            }
        }
        if (line.startsWith("goto ")) {
            String portion = line.substring(5);
            Double amount1 = firstNumber(portion);
            Double amount2 = lastNumber(portion);
            if (amount1 != null && amount2 != null) {
                return new PathfindingInstruction(
                        PathfindingInstruction.CURRENT_ROW, PathfindingInstruction.CURRENT_COLUMN, amount1.intValue(), amount2.intValue(), PathfindingInstruction.ANGLE);
            }
        }
        return Instruction.from(moveAmount, rotateAmount, wait, 0);
    }

    private static Instruction from(double moveAmount, double rotateAmount, int wait, int waitAfter) {
        if (moveAmount > 0 || rotateAmount > 0) {
            return new MovementInstruction(moveAmount, rotateAmount);
        } else {
            return new WaitInstruction(wait + waitAfter);
        }
    }

    private static Double firstNumberParsingTime(String line) {
        if (line.endsWith("ms")) {
            Double result = firstNumber(line.substring(0, line.indexOf("ms")));
            if (result == null) {
                return 0D;
            }
            return result / 2;
        } else if (line.endsWith("s")) {
            Double result = firstNumber(line.substring(0, line.indexOf("s")));
            if (result == null) {
                return 0D;
            }
            return result * 30;
        } else {
            return firstNumber(line);
        }
    }

    private static Double firstNumber(String line) {
        line = line.trim();
        String[] spaceSplit = line.split(" ");
        if (spaceSplit.length >= 1) {
            try {
                return Double.parseDouble(spaceSplit[0]);
            } catch (Exception ignore) {
                return null;
            }
        }
        return null;
    }
    private static Double lastNumber(String line) {
        line = line.trim();
        String[] spaceSplit = line.split(" ");
        if (spaceSplit.length >= 1) {
            try {
                return Double.parseDouble(spaceSplit[spaceSplit.length - 1]);
            } catch (Exception ignore) {
                return null;
            }
        }
        return null;
    }

}


