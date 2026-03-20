package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.intake.arm.IntakeArm;
import frc.robot.subsystems.intake.roller.IntakeRoller;

public class IntakeCommands {

  public static Command rotateIntake(IntakeArm intakeArm, double value) {
    return Commands.run(
        () -> {
          intakeArm.run(value);
        },
        intakeArm);
  }

  public static Command runIntake(IntakeRoller intakeRoller, double value) {
    return Commands.run(
        () -> {
          intakeRoller.run(value);
        },
        intakeRoller);
  }
}
