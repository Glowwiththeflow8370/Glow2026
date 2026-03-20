// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.stage;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.LoggedTunableNumber;

public class Stage extends SubsystemBase {

  private StageIO stage;
  private PIDController pidController;

  private double currentAngle;
  private double output;

  LoggedTunableNumber p = new LoggedTunableNumber("P", 0.00009);
  LoggedTunableNumber i = new LoggedTunableNumber("K", 0.0);
  LoggedTunableNumber d = new LoggedTunableNumber("D", 0.0);

  /** Creates a new Stage. */
  public Stage(StageIO stage) {
    this.stage = stage;
    pidController = new PIDController(p.getAsDouble(), i.getAsDouble(), d.getAsDouble());
    pidController.setTolerance(10);
  }

  public void runSetpoint(double targetAngle) {
    currentAngle = stage.getAngle();

    output = pidController.calculate(currentAngle, targetAngle);

    // if ((currentAngle > (targetAngle + 50)) && (currentAngle < (targetAngle - 50))) {
    //   run(0);
    // } else {
    //   run(output * 0.09);
    // }
    run(output);
  }

  public void run(double value) {
    stage.run(value);
  }

  public double getAngle() {
    return stage.getAngle();
  }

  @Override
  public void periodic() {
    stage.updateValues();

    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Stage Angle", getAngle());

    LoggedTunableNumber.ifChanged(
        hashCode(),
        (things) -> {
          pidController.setPID(p.getAsDouble(), i.getAsDouble(), d.getAsDouble());
        },
        p,
        i,
        d);
  }
}
