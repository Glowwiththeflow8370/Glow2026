// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.hood;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hood extends SubsystemBase {

  private HoodIO hood;

  /** Creates the Hood Classic */
  public Hood(HoodIO hood) {
    this.hood = hood;
  }

  // Now, how do we even actuate this stuff lmao
  public void rotate(double value) {
    hood.rotate(value);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    hood.updateValues();
  }
}
