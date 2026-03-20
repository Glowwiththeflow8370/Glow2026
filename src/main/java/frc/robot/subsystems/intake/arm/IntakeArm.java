// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake.arm;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.LoggedTunableNumber;

public class IntakeArm extends SubsystemBase {

  private IntakeArmIO intake;

  private LoggedTunableNumber IntakeP = new LoggedTunableNumber("Intake/kP", 0.01);
  private LoggedTunableNumber IntakeI = new LoggedTunableNumber("Intake/kI", 0);
  private LoggedTunableNumber IntakeD = new LoggedTunableNumber("Intake/kD", 0);

  private PIDController IntakePID;

  /** Creates a new Intake. */
  public IntakeArm(IntakeArmIO intake) {
    this.intake = intake;
    IntakePID = new PIDController(0.1, 0, 0);
    // give this a tolerance of 3 degrees
    IntakePID.setTolerance(3);
  }

  public void run(double value) {
    intake.run(value);
  }

  public void runSetpoint(double target) {
    double currentPos = intake.getAngle();

    double output = IntakePID.calculate(currentPos, target);

    intake.run(output);

    // System.out.println("running");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    intake.log();
    intake.updateValues();

    LoggedTunableNumber.ifChanged(
        hashCode(),
        thing -> IntakePID.setPID(thing[0], thing[1], thing[2]),
        IntakeP,
        IntakeI,
        IntakeD);

    SmartDashboard.putNumber("Intake Angle", intake.getAngle());
  }
}
