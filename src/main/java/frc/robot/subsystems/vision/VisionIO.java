package frc.robot.subsystems.vision;

public interface VisionIO {

  public default double getDistance() {
    return 0;
  }
}
