package frc.robot.subsystems.intake.arm;

import frc.robot.util.LoggedTunableNumber;
import org.littletonrobotics.junction.mechanism.LoggedMechanism2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

public class IntakeArmIOSim implements IntakeArmIO {

  private LoggedMechanism2d intakeMech;
  private LoggedMechanismLigament2d intakeArmThing;
  private LoggedMechanismRoot2d intakeRoot;

  // private LoggedTunableNumber IntakeP =
  //     new LoggedTunableNumber("Intake/kP", Constants.INTAKE_PID.kP());
  // private LoggedTunableNumber IntakeI =
  //     new LoggedTunableNumber("Intake/kI", Constants.INTAKE_PID.kI());
  // private LoggedTunableNumber IntakeD =
  //     new LoggedTunableNumber("Intake/kD", Constants.INTAKE_PID.kD());

  private LoggedTunableNumber SimIntakePosX = new LoggedTunableNumber("Intake/SimPosX", 1.2);
  private LoggedTunableNumber SimIntakePosY = new LoggedTunableNumber("Intake/SimPosY", 0.2);

  // private DCMotorSim intakeMotor;

  // public IntakeArmIOSim() {

  //   // intakeMotor = new DCMotorSim(LinearSystemId
  //   //   .createDCMotorSystem(DCMotor.getKrakenX60(1), 10.71));

  //   intakeMech = new LoggedMechanism2d(3, 3);
  //   intakeRoot =
  //       intakeMech.getRoot("intake_root", SimIntakePosX.getAsDouble(),
  // SimIntakePosY.getAsDouble());

  //   intakeArmThing = intakeRoot.append(new LoggedMechanismLigament2d("Intake Arm", 0.35, 90));

  //   Logger.recordOutput("intake mechanism", intakeMech);
  // }

  // public void run() {
  //   intakeArmThing.setAngle(10);
  // }

  // public double getAngle() {
  //   return intakeMotor.getAngularPositionRad();
  // }

  // public void log() {
  //   Logger.recordOutput("intake mechanism", intakeMech);
  // }

  // public void updateValues() {
  //   LoggedTunableNumber.ifChanged(
  //       hashCode(), pos -> intakeRoot.setPosition(pos[0], pos[1]), SimIntakePosX, SimIntakePosY);
  // }
}
