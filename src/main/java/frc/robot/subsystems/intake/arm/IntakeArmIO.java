package frc.robot.subsystems.intake.arm;

import org.littletonrobotics.junction.Logger;

public interface IntakeArmIO {

  public default void run(double value) {
    // System.out.println("This is unpaid labor at its finest");
  }

  public default double getAngle() {
    return 0.0;
  }

  public default void log() {
    Logger.recordOutput("Intake Angle", 0.0);
    Logger.recordOutput("Intake Speed", 0.0);
  }

  public default void updateValues() {}
}
