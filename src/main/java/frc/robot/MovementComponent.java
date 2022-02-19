package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class MovementComponent extends ComponentBase {

    /** Controls if we use the second joystick to rotate the robot. */
    private static final boolean ALTERNATE_ROTATE = true;

    public MovementComponent(Robot robot) {
        super(robot);
    }

    @Override
    public void teleopPeriodic() {
        Joystick stick = robot.getJoystick();
        //  -stick.getY() gives an error:
        //CTR: Firm Vers could not be retrieved. Use Phoenix Tuner to check ID and firmware(CRF) version.
        robot.move(stick.getY(), ALTERNATE_ROTATE ? stick.getZ() : stick.getX());

        
    }
    
}
