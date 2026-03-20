// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.indexer;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;

public class Indexer extends SubsystemBase {

  private IndexerIO indexer;

  private SimpleMotorFeedforward feedforward;
  private PIDController pidController;

  private double currAccel;
  private double currVel;
  private double targetVoltage;
  // private double currentVoltage;

  SysIdRoutine quasForward;

  /** Creates a new Indexer. */
  public Indexer(IndexerIO indexer) {
    this.indexer = indexer;
    feedforward = new SimpleMotorFeedforward(.01, 0.01, 0.01);
    pidController = new PIDController(0.01, 0.0, 0.0);
  }

  public void rampUpThing() {
    targetVoltage = feedforward.calculate(50, 10);
    // currentVoltage = indexer.getVoltage();
    currVel = indexer.getVelocity();
    indexer.runVoltage(pidController.calculate(currVel, 50) + targetVoltage);
  }

  public void run(double value) {
    indexer.run(value);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
