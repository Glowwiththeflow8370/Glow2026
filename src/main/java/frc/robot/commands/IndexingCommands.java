package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.indexer.Indexer;

public class IndexingCommands {

  public static Command runIndexer(Indexer indexer, double value) {
    return Commands.run(
        () -> {
          indexer.run(value);
          // System.out.println("Running Indexer");
          // stage.run(-value);
          // indexer.rampUpThing();
        },
        indexer);
  }
}
