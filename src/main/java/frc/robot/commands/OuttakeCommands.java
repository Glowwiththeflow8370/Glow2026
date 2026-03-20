package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.hood.Hood;

public class OuttakeCommands {

  public static Command rotateHood(Hood hood, double value) {
    return Commands.run(
        () -> {
          hood.rotate(value);
        },
        hood);
  }
}
