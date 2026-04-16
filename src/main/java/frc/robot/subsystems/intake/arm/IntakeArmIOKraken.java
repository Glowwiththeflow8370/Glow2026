package frc.robot.subsystems.intake.arm;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.units.measure.Angle;
import frc.robot.Constants;
import org.littletonrobotics.junction.Logger;

public class IntakeArmIOKraken implements IntakeArmIO {

  // private DutyCycleEncoder IntakeEncoder;
  private StatusSignal<Angle> IntakeEncoder;
  private TalonFX IntakeMotor;

  private TalonFXConfiguration IntakeMotorConfig;

  public IntakeArmIOKraken() {
    // IntakeEncoder = new DutyCycleEncoder(1);
    IntakeMotor = new TalonFX(Constants.IntakeConstants.IntakePivotMotorID);
    IntakeEncoder = IntakeMotor.getPosition();

    IntakeMotorConfig = new TalonFXConfiguration();
    IntakeMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    IntakeMotorConfig.CurrentLimits.StatorCurrentLimit = 40;

    IntakeMotor.getConfigurator().apply(IntakeMotorConfig);
  }

  public double getAngle() {
    return ((IntakeEncoder.getValueAsDouble() * 360));
  }

  public void run(double value) {
    IntakeMotor.set(value);
  }

  public void log() {
    Logger.recordOutput("Intake Motor Speed: ", IntakeMotor.get());
    Logger.recordOutput("Intake Angle", getAngle());
  }

  public void updateValues() {
    IntakeEncoder = IntakeMotor.getPosition();
  }

  // Need to configure networktables tuning stuff
}
