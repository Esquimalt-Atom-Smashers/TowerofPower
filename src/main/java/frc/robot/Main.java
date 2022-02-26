// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;
import frc.robot.autonomous.pattern.InstructionAutonomous;
import frc.robot.clp.CLPComponent;
import frc.robot.servos.IntakeExtensionComponent;

import java.io.FileNotFoundException;
import java.util.function.Supplier;

/**
 * WPILib doesn't want you to add stuff to here, that's because they do many
 * things behind-the-scenes before starting the program. If you want to do code
 * in here do so under Main -> Robot#startRobot(Supplier)
 */
public final class Main {

    // Set the controller ports here
    public static final int CONTROLLER_PORT_1 = 0;
    public static final int CONTROLLER_PORT_2 = 4;

    private Main() {
    }

    /**
     * Main initialization function.
     */
    public static void main(String... args) {

        RobotBase.startRobot(() -> {
            Robot robot = new Robot();
            robot.initJoystickSlots(CONTROLLER_PORT_1, CONTROLLER_PORT_2);

            robot.addComponent(new MovementComponent(robot));

            robot.addComponent(new IntakeExtensionComponent(robot));

            //robot.addComponent(new ActuatorComponent(robot));
            //robot.addComponent(new CLPComponent(robot));
            /*
            try {
                robot.addComponent(InstructionAutonomous.createFromFile("simple instsructions.txt", robot));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            */
            return robot;
            
        });

    }
}
