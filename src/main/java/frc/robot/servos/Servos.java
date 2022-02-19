package frc.robot.servos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import edu.wpi.first.wpilibj.Servo;

// TODO javadoc comments for this class
public class Servos {
    
    private final List<Servo> servos = new ArrayList<>();

    public Servos(Servo... servos) {
        this(Arrays.asList(servos));
    }
    public Servos(int... ports) {
        List<Servo> servos = new ArrayList<>();
        for (int port : ports) {
            servos.add(new Servo(port));
        }
        this.servos.addAll(servos);
    }

    public Servos(Collection<Servo> servos) {
        this.servos.addAll(servos);
    }

    public void set(double value) {
        servos.forEach(servo -> servo.set(value));
    }

    public void setAngle(double value) {
        servos.forEach(servo -> servo.setAngle(value));
    }

    public double get(int servo) {
        return servos.get(servo).get();
    }
    public double get() {
        return getAverage();
    }
    public double getAverage() {
        double total = 0;
        for (Servo servo : servos) {
            total += servo.get();
        }
        return total / servos.size();
    }

    public double getAngle(int servo) {
        return servos.get(servo).getAngle();
    }
    public double getAngle() {
        return getAngleAverage();
    }
    public double getAngleAverage() {
        double total = 0;
        for (Servo servo : servos) {
            total += servo.getAngle();
        }
        return total / servos.size();
    }

    public Servo getServo(int i) {
        return servos.get(i);
    }
    public void addServo(Servo servo) {
        servos.add(servo);
    }
    public List<Servo> getServos() {
        return servos;
    }

}
