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
     * Generates a new component and initializes it with a Robot.
     * @param robot The robot which this component will be attached to.
     */
    public ComponentBase(Robot robot) {
        this.robot = robot;
    }

    /**
     * @return The robot associated with this robot.
     */
    public Robot getRobot() {
        return robot;
    }

    /**
     * This method will get called by the associated Robot whenever its teleopPeriodic method is called.
     * @see Robot#teleopPeriodic()
     */
    public void teleopPeriodic() {}
    /**
     * This method will get called by the associated Robot whenever its autonomousPeriodic method is called.
     * @see Robot#autonomousPeriodic()
     */
    public void autonomousPeriodic() {}
    /**
     * This method will get called by the associated Robot whenever its disabledPeriodic method is called.
     * @see Robot#disabledPeriodic()
     */
    public void disabledPeriodic() {}
    /**
     * This method will get called by the associated Robot whenever its testPeriodic method is called.
     * @see Robot#testPeriodic()
     */
    public void testPeriodic() {}

    /**
     * This method will get called by the associated Robot whenever its teleopInit method is called.
     * @see Robot#teleopInit()
     */
    public void teleopInit() {}
    /**
     * This method will get called by the associated Robot whenever its autonomousInit method is called.
     * @see Robot#autonomousInit()
     */
    public void autonomousInit() {}
    /**
     * This method will get called by the associated Robot whenever its disabledInit method is called.
     * @see Robot#disabledInit()
     */
    public void disabledInit() {}
    /**
     * This method will get called by the associated Robot whenever its testInit method is called.
     * @see Robot#testInit()
     */
    public void testInit() {}

    /**
     * This method will get called once when the program starts and then once every time the robot gets re-enabled.
     */
    public void enabled() {}

    public void attach() {
        robot.addComponent(this);
    }

}
