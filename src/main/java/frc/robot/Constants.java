// Copyright (c) 2021-2026 Littleton Robotics
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by a BSD
// license that can be found in the LICENSE file
// at the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.RobotBase;

/**
 * This class defines the runtime mode used by AdvantageKit. The mode is always "real" when running
 * on a roboRIO. Change the value of "simMode" to switch between "sim" (physics sim) and "replay"
 * (log replay from a file).
 */
public final class Constants {
  public static final Mode simMode = Mode.SIM;
  public static final Mode currentMode = RobotBase.isReal() ? Mode.REAL : simMode;

  public static enum Mode {
    /** Running on a real robot. */
    REAL,

    /** Running a physics simulator. */
    SIM,

    /** Replaying from a log file. */
    REPLAY
  }

  public static boolean tuningMode = true;

  public final class ButtonboxIds {
    // Button Stuffs
    public static final int CLIMB_READY = 1;
    public static final int CLIMB_ACTIVATE = 9;
    public static final int INTAKE_STOW = 25;
    public static final int INTAKE_DEPLOY = 17;
    public static final int INTAKE_IN = 19;
    public static final int INTAKE_OUT = 30;
    public static final int TRANS_FEED = 24;
    public static final int TRANS_REV = 28;
    public static final int SHOOTER_FERR = 2;
    public static final int SHOOTER_SHOOT = 10;
  }

  public final class DriveConstants {
    public static final String DriveTrainLimelight = "limelight-drive";
    public static final double DriveTrainLimelightAngle = 19;

    // Stator Limits
    public static final double DriveTrainDriveMotorStatorLimit = 80;
    public static final double DriveTrainTurnMotorStatorLimit = 40;
    // Supply Limits
    public static final double DriveTrainDriveMotorSupplyLimit = 60;
    public static final double DriveTrainTurnMotorSupplyLimit = 60;

    // Measurement stuffs
    public static final double GOAL_HEIGHT = Units.inchesToMeters(44.25);
    public static final double LIMELIGHT_SHOOTER_HEIGHT = Units.inchesToMeters(0);
    public static final double LIMELIGHT_SHOOTER_ANGLE = 16;
  }

  public final class IntakeConstants {

    public static final int IntakePivotMotorID = 17;
    public static final int IntakeRollerMotorID = 19;
  }

  public final class IndexingConstants {

    public static final int IndexerMotor1ID = 20;
    public static final int IndexerMotor2ID = 16;
    // public static final int StageMotorID = 20;

    public static final double StageIdleAngle = -198.10546875;
    public static final double StageActiveAnge = 1267.3828125;
  }

  public final class OuttakeConstants {
    public static final int HoodMotorID = 18;

    public static final int ShootMotorID = 14;
    public static final int ShootFollowerID = 15;
  }
}
