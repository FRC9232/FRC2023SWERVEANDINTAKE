// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import frc.robot.commands.IntakeSetCommand;
import frc.robot.commands.LimeCommand;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
//import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
//import frc.robot.commands.LimeVisionCommand;
import frc.robot.commands.swervedrive.auto.Autos;
//import frc.robot.commands.swervedrive.drivebase.AbsoluteDrive;
import frc.robot.commands.swervedrive.drivebase.TeleopDrive;
import frc.robot.commands.swervedrive.drivebase.msDrive;
import frc.robot.subsystems.LimeVisionSubsystem;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import frc.robot.subsystems.ColorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
//import pabeles.concurrency.ConcurrencyOps.NewInstance;
import java.io.File;
//import java.util.function.DoubleSupplier;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very
 * little robot logic should actually be handled in the {@link Robot} periodic
 * methods (other than the scheduler calls).
 * Instead, the structure of the robot (including subsystems, commands, and
 * trigger mappings) should be declared here.
 */
public class RobotContainer {

  // The robot's subsystems and commands are defined here...
  private final SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(), "swerve"));
  // CommandJoystick rotationController = new CommandJoystick(1);
  // Replace with CommandPS4Controller or CommandJoystick if needed 
  LimeCommand limeCommand = new LimeCommand();
  LimeVisionSubsystem limeSubsystem = new LimeVisionSubsystem();
  ColorSubsystem colorSensore = new ColorSubsystem();
  IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  CommandJoystick driverController = new CommandJoystick(0);
  CommandJoystick throttleController = new CommandJoystick(2);
  // CommandJoystick driverController = new
  // CommandJoystick(3);//(OperatorConstants.DRIVER_CONTROLLER_PORT);
  //XboxController driverXbox = new XboxController(0);

  /**
   * 
   * h
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    double min = 0.4; // possibly up the min Minimum hız
    double max = 1.4;// maximum hız değeri
    // min, max

   /*  AbsoluteDrive closedAbsoluteDrive = new AbsoluteDrive(drivebase,
        // Applies deadbands and inverts controls because joysticks
        // are back-right positive while robot
        // controls are front-left positive
        () -> (Math.abs(driverXbox.getLeftY()) > OperatorConstants.LEFT_Y_DEADBAND)
            ? -driverXbox.getLeftY()
            : 0,
        () -> (Math.abs(driverXbox.getLeftX()) > OperatorConstants.LEFT_X_DEADBAND)
            ? -driverXbox.getLeftX()
            : 0,
        () -> -driverXbox.getRightX(),
        () -> -driverXbox.getRightY(),
        false); */

    TeleopDrive closedFieldRel = new TeleopDrive(
        drivebase,
        () -> (Math.abs(driverController.getRawAxis(1)) > OperatorConstants.LEFT_Y_DEADBAND)
            ? driverController.getRawAxis(1) * (((max - min) / 2) * ((-1) * throttleController.getRawAxis(0) + 1) + min)
            : 0,
        () -> (Math.abs(driverController.getRawAxis(0)) > OperatorConstants.LEFT_X_DEADBAND)
            ? driverController.getRawAxis(0) * (((max - min) / 2) * ((-1) * throttleController.getRawAxis(0) + 1) + min)
            : 0,
        () -> (Math.abs(driverController.getRawAxis(2)) > .12) ? -driverController.getRawAxis(2)
            * (((max - min) / 2) * ((-1) * throttleController.getRawAxis(0) + 1) + min) : 0,
        () -> true, false);

    drivebase.setDefaultCommand(closedFieldRel);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary predicate, or via the
   * named factories in
   * {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses
   * for
   * {@link CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller PS4}
   * controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick
   * Flight joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    // driverXbox.setDefaultCommand(new TeleopDrive(drivebase, null, null, null,
    // null, false));
    Trigger ilerigit = driverController.pov(0);
    ilerigit.whileTrue(new msDrive(drivebase,
    () -> -0.7,
    () -> 0,
    () -> 0,
    () -> true, false));
    Trigger gerigit = driverController.pov(180);
    gerigit.whileTrue(new msDrive(drivebase,
    () -> 0.7,
    () -> 0,
    () -> 0,
    () -> true, false));
    Trigger sagagit = driverController.pov(90);
    sagagit.whileTrue(new msDrive(drivebase,
    () -> 0,
    () -> 0.7,
    () -> 0,
    () -> true, false));
    Trigger solagit = driverController.pov(270);
    solagit.whileTrue(new msDrive(drivebase,
    () -> 0,
    () -> -0.7,
    () -> 0,
    () -> true, false));
    //Trigger ileridon = driverController.button(3);
   // ileridon.whileTrue( Autos.driveAndSpin(drivebase));
    Trigger don = driverController.button(4);
    don.whileTrue(new msDrive(drivebase,
    () -> -1.8,
    () -> 0,
    () -> 3,
    () -> true, false));

    Trigger a = driverController.button(3);
    a.whileTrue(new msDrive(drivebase,
    () -> 0,
    () -> 0,
    () -> limeCommand.getAimOmega(),
    () -> true, false));
    Trigger lime = driverController.button(3);
    if (lime.getAsBoolean() == true){
      limeSubsystem.setPipeline(1);
    }
    lime.whileTrue(new msDrive(drivebase,
    () -> 0,
    () -> 0,
    () -> limeCommand.getAimOmega(),
    () -> true, false));
    
    /*
    lime.onTrue(new msDrive(drivebase,
    () -> 0,
    () -> 0,
    () -> limeCommand.getAimOmega(),
    () -> true, limeCommand.isLimeAimed()));
    */
    
    Trigger limeileri = driverController.button(4);
    limeileri.whileTrue(new msDrive(drivebase,
    () -> limeCommand.getLimeDistanceVx(),
    () -> 0,
    () -> 0,
    () -> true, false));
      
  
    Trigger intakeTrigger = driverController.button(6);
    Trigger intakeReversedTrigger = driverController.button(8);
    intakeTrigger.whileTrue(new IntakeSetCommand(intakeSubsystem,colorSensore, 1));
    intakeTrigger.whileFalse(new IntakeSetCommand(intakeSubsystem,colorSensore, 0));
    intakeReversedTrigger.whileTrue(new IntakeSetCommand(intakeSubsystem,colorSensore, 2));
    intakeReversedTrigger.whileFalse(new IntakeSetCommand(intakeSubsystem,colorSensore, 0));
    
    // double min = 0.4; // possibly up the min
    //double max = 1.4;
    
  /*   new Trigger(() -> Math.abs(throttleController.getRawAxis(3)) > 0.5 || (Math.abs(throttleController.getRawAxis(4)) > 0.5 ))
        .whileTrue((new AbsoluteDrive(drivebase,
        // Applies deadbands and inverts controls because joysticks
        // are back-right positive while robot
        // controls are front-left positive
        () -> (Math.abs(driverController.getRawAxis(1)) > OperatorConstants.LEFT_Y_DEADBAND)
            ? driverController.getRawAxis(1) * (((max - min) / 2) * ((-1) * throttleController.getRawAxis(0) + 1) + min)
            : 0,
        () -> (Math.abs(driverController.getRawAxis(0)) > OperatorConstants.LEFT_X_DEADBAND)
            ? driverController.getRawAxis(0) * (((max - min) / 2) * ((-1) * throttleController.getRawAxis(0) + 1) + min)
            : 0,
        () -> -throttleController.getRawAxis(4),
        () -> -throttleController.getRawAxis(3),
        false)));
    //new JoystickButton(driverController, 1).onTrue((new InstantCommand(drivebase::zeroGyro)));
    //new JoystickButton(driverController, 1).onTrue((new InstantCommand(drivebase::zeroGyro)));
    //new JoystickButton(driverController, 0).whileTrue(new RepeatCommand(new InstantCommand(drivebase::lock, drivebase)));
    */
    Trigger b1Trigger = driverController.button(2);
    b1Trigger.onTrue((new InstantCommand(drivebase::zeroGyro)));
    Trigger b2Trigger = driverController.button(1);
    b2Trigger.whileTrue(new RepeatCommand(new InstantCommand(drivebase::lock, drivebase)));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(drivebase);
  }

  public void setDriveMode() {
    // drivebase.setDefaultCommand();
  }

  public void setMotorBrake(boolean brake) {
    drivebase.setMotorBrake(brake);
  }
}
