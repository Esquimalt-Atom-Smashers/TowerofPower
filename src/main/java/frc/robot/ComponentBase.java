package frc.robot;

/**
 * <p>
 *     This class is used to create a component which interacts with the Robot specified in the constructor.
 * </p>
 * <p>
 *     To implement this class create a class which extends this and implement all the methods which you will use.
 *     These methods will be called when their corresponding methods are called on the Robot class.
 * </p>
 * <p>
 *     You can also implement functionality onto the robot with the {@link Robot}'s event system.
 *     If you wish to do so, you should probably do so in the constructor.
 * </p>
 */
public abstract class ComponentBase {

    /** The robot defined in the constructor. */
    protected final Robot robot;

    /**
     *
     * @param robot The robot which this will be attached to.
     */
    public ComponentBase(Robot robot) {
        this.robot = robot;
    }

    /**
     * @return The robot defined in the constructor.
     */
    public Robot getRobot() {
        return robot;
    }

    /**
     * This method will get called by the Robot.java class whenever its teleopPeriodic methods is called.
     */
    public void teleopPeriodic() {}
    public void autonomousPeriodic() {}
    public void disabledPeriodic() {}
    public void testPeriodic() {}

    public void teleopInit() {}
    public void autonomousInit() {}
    public void disabledInit() {}
    public void testInit() {}

    /**
     * This method will get called once when the program starts and then once every time the robot gets re-enabled.
     */
    public void enabled() {}

    public void attach() {
        robot.addComponent(this);
    }
    
}
