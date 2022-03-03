package frc.robot.autonomous;

import frc.robot.Robot;

public class DelayAction extends Action {

    public DelayAction() {
        super(Type.DELAY);
    }

    @Override
    public void run(Robot robot) {
        
    }

    @Override
    protected double[] getValues() {
        return new double[0];
    }
}
