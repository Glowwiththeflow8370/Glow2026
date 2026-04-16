// Copyright (c) 2021-2026 Littleton Robotics
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by a BSD
// license that can be found in the LICENSE file
// at the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.Seconds;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import edu.wpi.first.units.measure.Time;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import frc.robot.commands.DriveCommands;
import frc.robot.commands.IndexingCommands;
import frc.robot.commands.IntakeCommands;
import frc.robot.commands.OuttakeCommands;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.drive.GyroIO;
import frc.robot.subsystems.drive.GyroIOPigeon2;
import frc.robot.subsystems.drive.ModuleIO;
import frc.robot.subsystems.drive.ModuleIOSim;
import frc.robot.subsystems.drive.ModuleIOTalonFX;
import frc.robot.subsystems.hood.Hood;
import frc.robot.subsystems.hood.HoodIO;
import frc.robot.subsystems.hood.HoodIOKraken;
import frc.robot.subsystems.hood.HoodIOSim;
import frc.robot.subsystems.indexer.Indexer;
import frc.robot.subsystems.indexer.IndexerIO;
import frc.robot.subsystems.indexer.IndexerIOKraken;
import frc.robot.subsystems.indexer.IndexerIOSim;
import frc.robot.subsystems.intake.arm.IntakeArm;
import frc.robot.subsystems.intake.arm.IntakeArmIO;
import frc.robot.subsystems.intake.arm.IntakeArmIOKraken;
import frc.robot.subsystems.intake.arm.IntakeArmIOSim;
import frc.robot.subsystems.intake.roller.IntakeRoller;
import frc.robot.subsystems.intake.roller.IntakeRollerIO;
import frc.robot.subsystems.intake.roller.IntakeRollerKraken;
import frc.robot.subsystems.intake.roller.IntakeRollerSim;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.ShooterIO;
import frc.robot.subsystems.shooter.ShooterIOKraken;
import frc.robot.subsystems.shooter.ShooterIOSim;
import frc.robot.util.LimelightHelpers;
import org.littletonrobotics.junction.networktables.LoggedDashboardChooser;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // Subsystems
  private final Drive drive;
  private final IntakeArm intakeArm;
  private final IntakeRoller intakeRoller;
  private final Indexer indexer;
  private final Hood hood;
  private final Shooter shooter;

  // Controller
  private final CommandXboxController controller = new CommandXboxController(0);
  // Button Box? Do we even need this atp?
  private final CommandGenericHID buttonbox = new CommandGenericHID(1);

  // Dashboard inputs
  private final LoggedDashboardChooser<Command> autoChooser;

  // Commands
  private Command intakeForward;
  private Command intakeReverse;
  private Command idleIntake;

  private Command intakeIntake;
  private Command intakeOuttake;
  private Command idleIntakeRoller;

  private Command runIndexer;
  private Command reverseIndexer;
  private Command idleIndexer;

  private Command fowardHood;
  private Command reverseHood;
  private Command idleHood;

  // private Command activeStage;
  // private Command inactiveStage;

  // // Shooter commands
  private Command idleShooter;
  private Command activeShooter;
  private Command reverseShooter;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    switch (Constants.currentMode) {
      case REAL:
        // Real robot, instantiate hardware IO implementations
        // ModuleIOTalonFX is intended for modules with TalonFX drive, TalonFX turn, and
        // a CANcoder
        drive =
            new Drive(
                new GyroIOPigeon2(),
                new ModuleIOTalonFX(TunerConstants.FrontLeft),
                new ModuleIOTalonFX(TunerConstants.FrontRight),
                new ModuleIOTalonFX(TunerConstants.BackLeft),
                new ModuleIOTalonFX(TunerConstants.BackRight));
        intakeArm = new IntakeArm(new IntakeArmIOKraken());
        intakeRoller = new IntakeRoller(new IntakeRollerKraken());
        indexer = new Indexer(new IndexerIOKraken());
        hood = new Hood(new HoodIOKraken());
        shooter = new Shooter(new ShooterIOKraken());
        // The ModuleIOTalonFXS implementation provides an example implementation for
        // TalonFXS controller connected to a CANdi with a PWM encoder. The
        // implementations
        // of ModuleIOTalonFX, ModuleIOTalonFXS, and ModuleIOSpark (from the Spark
        // swerve
        // template) can be freely intermixed to support alternative hardware
        // arrangements.
        // Please see the AdvantageKit template documentation for more information:
        // https://docs.advantagekit.org/getting-started/template-projects/talonfx-swerve-template#custom-module-implementations
        //
        // drive =
        // new Drive(
        // new GyroIOPigeon2(),
        // new ModuleIOTalonFXS(TunerConstants.FrontLeft),
        // new ModuleIOTalonFXS(TunerConstants.FrontRight),
        // new ModuleIOTalonFXS(TunerConstants.BackLeft),
        // new ModuleIOTalonFXS(TunerConstants.BackRight));
        break;

      case SIM:
        // Sim robot, instantiate physics sim IO implementations
        drive =
            new Drive(
                new GyroIO() {},
                new ModuleIOSim(TunerConstants.FrontLeft),
                new ModuleIOSim(TunerConstants.FrontRight),
                new ModuleIOSim(TunerConstants.BackLeft),
                new ModuleIOSim(TunerConstants.BackRight));
        intakeArm = new IntakeArm(new IntakeArmIOSim());
        intakeRoller = new IntakeRoller(new IntakeRollerSim());
        indexer = new Indexer(new IndexerIOSim());
        hood = new Hood(new HoodIOSim());
        shooter = new Shooter(new ShooterIOSim());
        break;

      default:
        // Replayed robot, disable IO implementations
        drive =
            new Drive(
                new GyroIO() {},
                new ModuleIO() {},
                new ModuleIO() {},
                new ModuleIO() {},
                new ModuleIO() {});
        intakeArm = new IntakeArm(new IntakeArmIO() {});
        intakeRoller = new IntakeRoller(new IntakeRollerIO() {});
        indexer = new Indexer(new IndexerIO() {});
        hood = new Hood(new HoodIO() {});
        shooter = new Shooter(new ShooterIO() {});
        break;
    }

    // Set up auto routines
    autoChooser = new LoggedDashboardChooser<>("Auto Choices", AutoBuilder.buildAutoChooser());

    // Set up SysId routines
    autoChooser.addOption(
        "Drive Wheel Radius Characterization", DriveCommands.wheelRadiusCharacterization(drive));
    autoChooser.addOption(
        "Drive Simple FF Characterization", DriveCommands.feedforwardCharacterization(drive));
    autoChooser.addOption(
        "Drive SysId (Quasistatic Forward)",
        drive.sysIdQuasistatic(SysIdRoutine.Direction.kForward));
    autoChooser.addOption(
        "Drive SysId (Quasistatic Reverse)",
        drive.sysIdQuasistatic(SysIdRoutine.Direction.kReverse));
    autoChooser.addOption(
        "Drive SysId (Dynamic Forward)", drive.sysIdDynamic(SysIdRoutine.Direction.kForward));
    autoChooser.addOption(
        "Drive SysId (Dynamic Reverse)", drive.sysIdDynamic(SysIdRoutine.Direction.kReverse));

    // Configure commands
    configureCommands();
    configureNamedCommands();

    intakeArm.setDefaultCommand(idleIntake);
    intakeRoller.setDefaultCommand(idleIntakeRoller);
    indexer.setDefaultCommand(idleIndexer);
    hood.setDefaultCommand(idleHood);
    shooter.setDefaultCommand(idleShooter);
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Default command, normal field-relative drive
    drive.setDefaultCommand(
        DriveCommands.joystickDrive(
            drive,
            () -> -controller.getLeftY(),
            () -> -controller.getLeftX(),
            () -> -controller.getRightX()));

    // Align to limelight when the A button is held (Testing, hopefully I will have time to
    // actually use it during comp)
    controller
        .a()
        .whileTrue(
            DriveCommands.pointDrive(
                drive,
                drive.getPose(),
                LimelightHelpers.getBotPose2d(Constants.DriveConstants.DriveTrainLimelight)));

    // Lock to 0° when A button is held
    // controller
    //     .a()
    //     .whileTrue(
    //         DriveCommands.joystickDriveAtAngle(
    //             drive,
    //             () -> -controller.getLeftY(),
    //             () -> -controller.getLeftX(),
    //             () -> Rotation2d.kZero));

    // Switch to X pattern when X button is pressed
    // controller.x().onTrue(Commands.runOnce(drive::stopWithX, drive));

    // Reset gyro to 0° when B button is pressed
    // controller
    //     .b()
    //     .onTrue(
    //         Commands.runOnce(
    //                 () ->
    //                     drive.setPose(
    //                         new Pose2d(drive.getPose().getTranslation(), Rotation2d.kZero)),
    //                 drive)
    //             .ignoringDisable(true));

    controller.x().whileTrue(intakeForward);
    controller.a().whileTrue(intakeReverse);

    controller.b().whileTrue(intakeIntake);
    controller.a().whileTrue(intakeOuttake);

    // controller.leftTrigger().whileTrue(runIndexer);
    // controller.rightTrigger().whileTrue(reverseIndexer);

    buttonbox.button(Constants.ButtonboxIds.CLIMB_READY).whileTrue(reverseHood);
    buttonbox.button(Constants.ButtonboxIds.CLIMB_ACTIVATE).whileTrue(fowardHood);
    buttonbox.button(Constants.ButtonboxIds.TRANS_FEED).whileTrue(runIndexer);
    buttonbox.button(Constants.ButtonboxIds.SHOOTER_FERR).whileTrue(reverseShooter);
    // buttonbox.button(Constants.ButtonboxIds.TRANS_REV).whileTrue(inactiveStage);

    buttonbox.button(Constants.ButtonboxIds.INTAKE_IN).whileTrue(intakeIntake);
    buttonbox.button(Constants.ButtonboxIds.INTAKE_OUT).whileTrue(intakeOuttake);

    buttonbox.button(Constants.ButtonboxIds.INTAKE_DEPLOY).whileTrue(intakeForward);
    buttonbox.button(Constants.ButtonboxIds.INTAKE_STOW).whileTrue(intakeReverse);

    buttonbox.button(Constants.ButtonboxIds.TRANS_REV).whileTrue(reverseIndexer);
    buttonbox.button(Constants.ButtonboxIds.SHOOTER_SHOOT).whileTrue(activeShooter);
  }

  public void configureNamedCommands() {
    // Here, all of the named commands from pathplanner will be declared
    NamedCommands.registerCommand("Just Send It", activeShooter);
    NamedCommands.registerCommand("Feed", reverseIndexer);
  }

  public void configureCommands() {
    // All commands will be created here (unless the program crashes on me)
    intakeForward = IntakeCommands.rotateIntake(intakeArm, 0.3);
    intakeReverse = IntakeCommands.rotateIntake(intakeArm, -0.2);
    idleIntake = IntakeCommands.rotateIntake(intakeArm, 0);

    intakeIntake = IntakeCommands.runIntake(intakeRoller, -0.7);
    intakeOuttake = IntakeCommands.runIntake(intakeRoller, 0.3);
    idleIntakeRoller = IntakeCommands.runIntake(intakeRoller, 0);

    runIndexer = IndexingCommands.runIndexer(indexer, 0.8);
    reverseIndexer = IndexingCommands.runIndexer(indexer, -0.8);
    idleIndexer = IndexingCommands.runIndexer(indexer, 0);

    fowardHood = OuttakeCommands.rotateHood(hood, 0.05);
    reverseHood = OuttakeCommands.rotateHood(hood, -0.05);
    idleHood = OuttakeCommands.rotateHood(hood, 0);

    activeShooter = OuttakeCommands.runShooter(shooter, -0.6);
    reverseShooter = OuttakeCommands.runShooter(shooter, 0.6);
    idleShooter = OuttakeCommands.runShooter(shooter, 0);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return basicThing();
  }

  // This is probably the only auton i can get working with the
  // time ive been given lol
  public Command basicThing() {
    return activeShooter.alongWith(
        new WaitCommand(Time.ofBaseUnits(5, Seconds)).andThen(runIndexer));
  }

  //   ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠤⠒⠒⠒⠒⠒⠠⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
  // ⠀⠀⠀⠀⠀⢀⡞⡽⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢯⢳⡀⠀⠀⠀⠀⠀⠀⠀
  // ⠀⠀⠀⠀⢀⣔⣻⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢾⣿⠢⡀⠀⠀⠀⠀
  // ⠀⠀⢀⣾⣿⣶⡆⠀⠀⢐⡄⠀⠀⠀⠀⠐⠳⡀⠀⢸⣇⣧⡐⠀⠀⠀⠀⠀⠀
  // ⠀⢠⠏⠀⢹⣿⡇⠀⠀⠇⢀⢂⠀⠀⠀⡃⢀⠀⠘⣦⢸⡿⡟⠋⠁⡀⠀⠀⠀
  // ⠀⡋⠀⠀⠀⢹⣷⡀⡸⠀⠻⠀⠈⠒⠤⠃⠿⠀⢀⠻⣼⡇⠀⠀⠀⠀⢂⠀⠀
  // ⢀⠀⠀⠀⠀⠈⣿⠱⣿⣅⠀⠀⠸⣉⡹⠀⠀⢀⠞⡼⠀⣿⡀⠀⠀⠀⠀⠐⠀
  // ⠀⠀⠀⠀⠀⠀⣿⠀⢣⠀⠍⢶⣦⠤⢤⣖⠾⠠⣀⠇⠀⢹⡇⠀⠀⠀⠀⠀⠡
  // ⡁⠀⠀⠀⢀⡴⣏⣴⡑⣀⣴⠂⢸⠤⠼⡀⠱⡤⡨⢳⣦⣸⣷⠀⠀⠀⠀⠀⢡
  // ⠅⠀⠀⣴⡟⢷⣾⣿⡷⠳⠃⢠⠃⠀⠀⢣⣀⠡⡙⣿⣿⣿⠛⣦⡀⠀⠀⠀⢰
  // ⠂⠀⣼⡯⠃⠢⠽⠋⡴⠧⣀⡣⡀⠀⠀⢈⢄⣭⣇⠘⠿⠕⠥⠜⠻⠦⣀⠀⡇
  // ⠀⠉⠁⠀⠀⠀⡊⠉⢙⣿⡾⡶⠾⠶⣾⢕⢿⠟⠀⢠⠀⠀⠀⠀⠀⠀⠀⠀⠉
  // ⠀⠀⠀⠀⠀⠀⠐⢀⠼⠃⠀⠉⠉⠀⠂⠀⠈⠣⠄⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀
  // ⠀⠀⠀⠀⠀⠀
  //       ♡⠀miku miku oo ee oo ♡⠀

  // Blake will hate me for this lmao

}
