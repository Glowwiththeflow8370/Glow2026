package frc.robot.subsystems.intake.roller;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkFlexConfig;
import frc.robot.Constants;

public class IntakeRollerKraken implements IntakeRollerIO {

  private SparkFlex IntakeRollerMotor;
  private SparkFlexConfig config;

  public IntakeRollerKraken() {
    IntakeRollerMotor =
        new SparkFlex(Constants.IntakeConstants.IntakeRollerMotorID, MotorType.kBrushless);

    config = new SparkFlexConfig();
    config.smartCurrentLimit(40, 40);
  }

  public void run(double value) {
    IntakeRollerMotor.set(value);
  }
}
