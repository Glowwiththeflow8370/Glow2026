// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

  ShooterIO shooter;

  /** Creates a new Shooter. */
  public Shooter(ShooterIO shooter) {
    this.shooter = shooter;
  }

  public void run(double value) {
    shooter.run(value);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
