package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class TowerComponent extends ComponentBase {
    //Asumming 1 is clockwise and -1 is counterclockwise for now - need to make CLOCK WISE and COUNTER CLOCKWISE CONSTANTS
    private static final int SHOOT_HIGH_BUTTON = 0; //Placeholder for now
    private static final int SHOOT_LOW_BUTTON = 1; //Placeholder for now
    private static final int INDEX_DOWN_BUTTON = 4; 
    private static final int SHOOT_LOW = 1; // Placeholder for now
    private static final double HIGH_POWER_MULT = 0.8; //80 %
    private static final double LOW_POWER_MULT = 0.6; //60 % 
    private static final double HIGH_POWER_FORWARD = 1 * HIGH_POWER_MULT; //80 %
    private static final double HIGH_POWER_BACKWARDS = -1 * HIGH_POWER_MULT; 
    private static final double LOW_POWER_BACKWARDS = -1 * LOW_POWER_MULT;
    private static final double LOW_POWER_FORWARD = 1 * LOW_POWER_MULT;

    //Tower power always 100%

    public TowerComponent(Robot robot) {

        super(robot);
        //Shooters at high speed
        robot.setOnButtonPressed(SHOOT_HIGH_BUTTON, event -> {
            //Am I supposed to be using is reversed or not?????
            robot.getShooterMotor1().set(HIGH_POWER_FORWARD); //Clockwise
            robot.getShooterMotor2().set(HIGH_POWER_BACKWARDS); //Counter clockwise
        });
        // Shoooters at low speed
        robot.setOnButtonPressed(SHOOT_LOW_BUTTON, event -> {
            robot.getShooterMotor1().set(LOW_POWER_FORWARD);
            robot.getShooterMotor2().set(LOW_POWER_BACKWARDS);
        });
        // Indexing the balls downwards
        robot.setOnButtonPressed(INDEX_DOWN_BUTTON, event -> {
            robot.getTowerMotor1().set(-1);
            robot.getTowerMotor2().set(1);
        });
    } 

    @Override
    public void teleopPeriodic() {
        //Spped is 100% for now
        //Indexing the balls upwards in the tower
        XboxController gamepad1 = robot.getXboxController1();
        if (gamepad1.getRawAxis(2) == 1) {
            robot.getTowerMotor1().set(1);
            robot.getTowerMotor2().set(-1);
        }
    }

    

}
