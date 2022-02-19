package frc.robot.autonomous;

import edu.wpi.first.wpilibj.AnalogInput;
import frc.robot.ComponentBase;
import frc.robot.Robot;

/*
    I realize we might be using multiple of these so maybe better make a creator for them
*/

public class UltrasonicComponent extends ComponentBase {

    public UltrasonicComponent(Robot robot) {
        super(robot);
    }
    
    final int distanceCM = 500; // Max distance ultrasonic is suggested for in CM
    final int maxUltraVal = 4095; // Value of ultrasonic analog read when using 5V (Probably 4095)
    double distConst = maxUltraVal/distanceCM;

    AnalogInput ultrasonic = new AnalogInput(0); // Analog port number
    


    @Override
    public void teleopPeriodic() {
        double ultraVal = ultrasonic.getValue(); // Gets distance val from ultrasonic between 0-4095 (0V-5V)
        double currentDistance = ultraVal / distConst; // Gives dist in CM
        System.out.println(currentDistance);
    }
    
}
