package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.hood.Hood;
import frc.robot.subsystems.indexer.Indexer;
import frc.robot.subsystems.shooter.Shooter;

public class OuttakeCommands {

  public static Command rotateHood(Hood hood, double value) {
    return Commands.run(
        () -> {
          hood.rotate(value);
        },
        hood);
  }

  public static Command runShooter(Shooter shooter, double value) {
    return Commands.run(
        () -> {
          shooter.run(value);
          // System.out.println("Running Shooter");
        },
        shooter);
  }

  // This should be set up in a way that it adjusts its
  // angle based on it's position
  public static Command aimShooter(Hood hood) {
    return Commands.run(
        () -> {
          hood.rotate(0);
        },
        hood);
  }

  public static Command stationaryShot(Shooter shooter, Indexer indexer) {
    return Commands.run(() -> {}, shooter, indexer);
  }
}
