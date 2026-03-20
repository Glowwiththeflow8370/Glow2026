package frc.robot.subsystems.indexer;

public interface IndexerIO {

  public default void run(double value) {}

  public default void runVoltage(double voltage) {}

  public default double getVelocity() {
    return 0.0;
  }

  public default double getAcceleration() {
    return 0.0;
  }

  public default double getVoltage() {
    return 0.0;
  }
}
