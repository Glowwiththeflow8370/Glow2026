package frc.robot.subsystems.stage;

public interface StageIO {
  public default void run(double value) {}

  public default double getAngle() {
    return 0.0;
  }

  public default void updateValues() {}
}
