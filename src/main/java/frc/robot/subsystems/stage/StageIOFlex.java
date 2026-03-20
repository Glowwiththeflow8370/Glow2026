package frc.robot.subsystems.stage;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.units.measure.Angle;
import frc.robot.Constants;

public class StageIOFlex implements StageIO {

  private StatusSignal<Angle> encoder;
  private TalonFX stageMotor;
  private TalonFXConfiguration config;
  // private DutyCycleEncoder encoder;

  public StageIOFlex() {
    stageMotor = new TalonFX(Constants.IndexingConstants.StageMotorID);

    config = new TalonFXConfiguration();
    config.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    // stageMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    stageMotor.getConfigurator().apply(config);

    // encoder = new DutyCycleEncoder(1);
    encoder = stageMotor.getPosition();
  }

  public void run(double value) {
    stageMotor.set(value);
  }

  public double getAngle() {
    return encoder.getValueAsDouble() * 360;
  }

  public void updateValues() {
    encoder = stageMotor.getPosition();
  }
}
