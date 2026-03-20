package frc.robot.subsystems.hood;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.units.measure.Angle;
import frc.robot.Constants;

public class HoodIOKraken implements HoodIO {

  private TalonFX hoodMotor;
  private TalonFXConfiguration configuration;

  // Stuff
  private StatusSignal<Angle> hoodEncoder;

  public HoodIOKraken() {
    hoodMotor = new TalonFX(Constants.OuttakeConstants.HoodMotorID);

    configuration = new TalonFXConfiguration();
    configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

    hoodMotor.getConfigurator().apply(configuration);
  }

  @Override
  public void rotate(double value) {
    hoodMotor.set(value);
  }
}
