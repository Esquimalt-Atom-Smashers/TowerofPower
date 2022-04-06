package frc.robot.autonomous;

import frc.robot.Robot;

public class TowerAction extends Action {

    private final double shooterPower;
    private final double towerPower;

    public TowerAction(double shooterPower, double towerPower) {
        super(Type.TOWER);
        this.shooterPower = shooterPower;
        this.towerPower = towerPower;
    }

    @Override
    public String toString() {
        return "TowerAction{" +
                "shooterPower=" + shooterPower +
                ", towerPower=" + towerPower +
                '}';
    }

    @Override
    public void run(Robot robot) {
        robot.getTowerMotor1().set(towerPower);
        robot.getTowerMotor2().set(towerPower);
        robot.getShooterMotor1().set(shooterPower);
        robot.getShooterMotor2().set(-shooterPower);
    }

    @Override
    protected double[] getValues() {
        return new double[]{ shooterPower, towerPower };
    }
}
