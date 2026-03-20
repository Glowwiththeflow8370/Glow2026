package frc.robot.subsystems.intake.roller;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import frc.robot.Constants;

public class IntakeRollerNeo implements IntakeRollerIO {

  private SparkFlex IntakeRollerMotor;
  // private SparkFlexConfig config;

  public IntakeRollerNeo() {
    IntakeRollerMotor =
        new SparkFlex(Constants.IntakeConstants.IntakeRollerMotorID, MotorType.kBrushless);

    // config = new SparkFlexConfig();
    // config.cu
  }

  public void run(double value) {
    IntakeRollerMotor.set(value);
  }
}
