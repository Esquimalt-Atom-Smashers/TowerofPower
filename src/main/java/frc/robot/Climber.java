package frc.robot;

public class Climber extends ComponentBase {

    /**
     * Generates a new component and initializes it with a Robot.
     *
     * @param robot The robot which this component will be attached to.
     */
    public Climber(Robot robot) {
        super(robot);

        robot.addDpadHandler(event -> {
            if (event.getValue() == 0) {
                robot.getClimberMotor().set(2);
            } else if (event.getValue() == 180) {
                robot.getClimberMotor().set(1);
            } else {
                robot.getClimberMotor().set(1.513);
            }
        });
    }

}
