package frc.robot.subsystems.indexer;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.Constants;

public class IndexerIOKraken implements IndexerIO {

  private TalonFX indexMotor1, indexMotor2;
  private TalonFXConfiguration indexMotorConfig1, indexMotorConfig2;
  // private Slot0Configs slot0Configs;

  public IndexerIOKraken() {
    indexMotor1 = new TalonFX(Constants.IndexingConstants.IndexerMotor1ID);
    indexMotor2 = new TalonFX(Constants.IndexingConstants.IndexerMotor2ID);

    indexMotorConfig1 = new TalonFXConfiguration();
    indexMotorConfig1.MotorOutput.NeutralMode = NeutralModeValue.Coast;
    indexMotorConfig1.CurrentLimits.StatorCurrentLimit = 40;

    indexMotorConfig2 = new TalonFXConfiguration();
    indexMotorConfig1.CurrentLimits.StatorCurrentLimit = 40;

    indexMotor1.getConfigurator().apply(indexMotorConfig1);
    indexMotor2.getConfigurator().apply(indexMotorConfig2);

    indexMotor2.setControl(new Follower(indexMotor1.getDeviceID(), MotorAlignmentValue.Aligned));
  }

  @Override
  public void run(double value) {
    indexMotor1.set(value);
  }

  @Override
  public double getVelocity() {
    return indexMotor1.getVelocity().getValueAsDouble();
  }

  @Override
  public double getAcceleration() {
    return indexMotor1.getAcceleration().getValueAsDouble();
  }

  @Override
  public double getVoltage() {
    return indexMotor1.getMotorVoltage().getValueAsDouble();
  }
}
