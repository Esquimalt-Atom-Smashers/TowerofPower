package frc.robot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

// TODO javadoc comments for this class
public class TowerMotors {

    private final List<WPI_VictorSPX> motors = new ArrayList<>();

    public TowerMotors(int... ports) {
        List<WPI_VictorSPX> motors = new ArrayList<>();
        for (int port : ports) {
            motors.add(new WPI_VictorSPX(port));
        }
        this.motors.addAll(motors);
    }
    public TowerMotors(WPI_VictorSPX... motors) {
        this(Arrays.asList(motors));
    }
    public TowerMotors(Collection<WPI_VictorSPX> motors) {
        this.motors.addAll(motors);
    }

    public void set(double value) {
        this.motors.forEach(motor -> motor.set(value));
    }

    public void set(int index, double value) {
        this.motors.get(index).set(value);
    }
    
}
