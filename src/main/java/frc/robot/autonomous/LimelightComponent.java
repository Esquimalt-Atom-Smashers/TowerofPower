package frc.robot.autonomous;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.ComponentBase;
import frc.robot.Robot;

public class LimelightComponent extends ComponentBase {

    private final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    private final NetworkTableEntry tx = table.getEntry("tx");
    private final NetworkTableEntry ty = table.getEntry("ty");
    private final NetworkTableEntry ta = table.getEntry("ta");

    public LimelightComponent(Robot robot) {
        super(robot);
    }

    @Override
    public void enabled() {
        
    }

    @Override
    public void teleopPeriodic() {
        System.out.println("X: " + tx.getDouble(0.0));
        System.out.println("Y: " + ty.getDouble(0.0));
        System.out.println("Area: " + ta.getDouble(0.0));
    }
    
}
