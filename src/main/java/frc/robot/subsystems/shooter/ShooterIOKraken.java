package frc.robot.subsystems.shooter;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import frc.robot.Constants;

public class ShooterIOKraken implements ShooterIO {

  private TalonFX shooterMotor, follower;
  private TalonFXConfiguration motorConfig, followerConfig;

  public ShooterIOKraken() {
    shooterMotor = new TalonFX(Constants.OuttakeConstants.ShootMotorID);
    follower = new TalonFX(Constants.OuttakeConstants.ShootFollowerID);

    motorConfig = new TalonFXConfiguration();
    motorConfig.CurrentLimits.StatorCurrentLimit = 80;
    motorConfig.CurrentLimits.StatorCurrentLimitEnable = true;
    motorConfig.CurrentLimits.SupplyCurrentLimit = 60;
    motorConfig.CurrentLimits.SupplyCurrentLimitEnable = true;
    // motorConfig.Slot0.kP = 0.5;
    // motorConfig.Slot0.kI = 0.0;
    // motorConfig.Slot0.kD = 0.0;

    followerConfig = new TalonFXConfiguration();
    followerConfig.CurrentLimits.StatorCurrentLimit = 80;
    followerConfig.CurrentLimits.StatorCurrentLimitEnable = true;
    followerConfig.CurrentLimits.SupplyCurrentLimit = 60;
    followerConfig.CurrentLimits.SupplyCurrentLimitEnable = true;
    // followerConfig.Slot0.kP = 0.5;
    // followerConfig.Slot0.kI = 0.0;
    // followerConfig.Slot0.kD = 0.0;

    follower.setControl(
        new Follower(Constants.OuttakeConstants.ShootMotorID, MotorAlignmentValue.Opposed));
  }

  @Override
  public void run(double value) {
    shooterMotor.set(value);
  }
}
