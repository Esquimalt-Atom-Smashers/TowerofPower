package frc.robot.autonomous.pattern.pathfinding;

import edu.wpi.first.wpilibj.drive.Vector2d;
import frc.robot.Robot;
import frc.robot.autonomous.pattern.Instruction;

public class PathfindingInstruction extends Instruction {

    public static float ANGLE = 0.0f;
    public static int CURRENT_ROW = 0;
    public static int CURRENT_COLUMN = 0;

    private static final int ROWS = 7;
    private static final int COLUMNS = 14;
    private static final float SIDE_LENGTH = 2.5f;
    private static final double MOVE_AMOUNT = 0.1;

    private double rotation;
    private double distance;

    public PathfindingInstruction(int currentRow, int currentColumn, int x, int y, float angle) {
        super();
        this.rotation = 0.0;

        Vector2d direction = new Vector2d(x - currentRow, y - currentColumn);
        rotation = angle - Math.toDegrees(Math.atan2(direction.x, direction.y));
        distance = Math.sqrt(Math.pow(direction.x, 2) + Math.pow(direction.y, 2));
        ANGLE = (float) Math.toDegrees(Math.atan2(direction.x, direction.y));

        CURRENT_ROW = x;
        CURRENT_COLUMN = y;
    }

    @Override
    public boolean isFinished() {
        return rotation == 0 && distance == 0;
    }

    @Override
    public void activate(Robot robot) {
        if (rotation > 0) {
            robot.move(0, MOVE_AMOUNT);
            rotation--;
        } else if (distance > 0) {
            robot.move(MOVE_AMOUNT,0);
            distance--;
        }
    }

}
