package frc.robot.autonomous.pattern;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

import edu.wpi.first.wpilibj.Filesystem;
import frc.robot.ComponentBase;
import frc.robot.Robot;

/**
 * This class takes a list of {@link frc.robot.autonomous.pattern.Instruction} in its
 * constructor then works its way through them until there are none left.
 */
public class InstructionAutonomous extends ComponentBase {

    private static final int START_BUTTON = 2; // 2 = A button
    public static final double MOVE_AMOUNT = 1;

    private final ArrayList<Instruction> originalInstructions = new ArrayList<>();
    private final ArrayList<Instruction> instructions = new ArrayList<>();

    private Instruction currentInstruction;

    public InstructionAutonomous(Robot bot, Instruction... defaultInstructions) {
        super(bot);
        originalInstructions.addAll(Arrays.asList(defaultInstructions));
    }

    @Override
    public void teleopPeriodic() {
        if (robot.getJoystick().getRawButton(START_BUTTON)) {
            instructions.clear();
            instructions.addAll((Collection<Instruction>) originalInstructions.clone());
        }
        if (currentInstruction == null && instructions.size() > 0) {
            currentInstruction = instructions.get(0);
            instructions.remove(0);
            if (currentInstruction == null) {
                // If the currentInstruction in slot 0 is null then we need to re-run this to
                // until either we get one which isn't null or the list is emptied.
                teleopPeriodic();
                return;
            }
        } else if (currentInstruction == null) {
            // If we have ran out of instructions then we shouldn't run anymore code.
            return;
        }

        if (currentInstruction.isFinished()) {
            currentInstruction = null;
            if (instructions.size() > 0)
                teleopPeriodic();
        } else {
            currentInstruction.activate(robot);
        }
    }

    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    public static InstructionAutonomous createFromInstructions(String instructions, Robot bot) {
        instructions = instructions.replaceAll("(#|\\/\\/)[^\n]+", "");
        System.out.println(instructions);
        ArrayList<Instruction> instructionsList = new ArrayList<>();
        for (String line : instructions.split("\n")) {
            Instruction instruction = Instruction.fromLine(line);
            instructionsList.add(instruction);
        }
        return new InstructionAutonomous(bot, instructionsList.toArray(new Instruction[0]));
    }

    public static InstructionAutonomous createFromFile(String name, Robot bot) throws FileNotFoundException {
        File file = new File(Filesystem.getDeployDirectory().getPath() + "/" + name);
        Scanner scanner = new Scanner(file);
        StringBuilder text = new StringBuilder();
        while (scanner.hasNextLine()) {
            text.append("\n").append(scanner.nextLine());
        }
        return createFromInstructions(text.substring(1), bot);
    }
    
}
