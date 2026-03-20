// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake.roller;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeRoller extends SubsystemBase {

  IntakeRollerIO intakeRoller;

  /** Creates a new IntakeRoller. */
  public IntakeRoller(IntakeRollerIO intakeRoller) {
    this.intakeRoller = intakeRoller;
  }

  public void run(double value) {
    intakeRoller.run(value);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
