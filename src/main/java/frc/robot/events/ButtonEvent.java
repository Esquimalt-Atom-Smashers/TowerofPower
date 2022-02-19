package frc.robot.events;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robot;

// TODO javadoc comments for this class
public class ButtonEvent extends JoystickEvent {

    private final int button;

    public ButtonEvent(Robot robot, Joystick stick, int pressed) {
        super(robot, stick);
        this.button = pressed;
    }

    public static EventHandler<ButtonEvent> handler(Runnable o) {
        return event -> o.run();
    }

    public int getButton() {
        return button;
    }
    
}
