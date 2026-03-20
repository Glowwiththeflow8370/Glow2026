package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.indexer.Indexer;
import frc.robot.subsystems.stage.Stage;

public class IndexingCommands {

  public static Command runIndexer(Indexer indexer, double value) {
    return Commands.run(
        () -> {
          indexer.run(value);
          // stage.run(-value);
          // indexer.rampUpThing();
        },
        indexer);
  }

  public static Command manualRotateStage(Stage stage, double value) {
    return Commands.run(
        () -> {
          stage.run(value);
        },
        stage);
  }

  public static Command rotateStage(Stage stage, double setpoint) {
    return Commands.run(
        () -> {
          stage.runSetpoint(setpoint);
        },
        stage);
  }
}
