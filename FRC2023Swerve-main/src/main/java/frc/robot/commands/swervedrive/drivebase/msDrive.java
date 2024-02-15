// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.swervedrive.drivebase;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import swervelib.SwerveController;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import frc.robot.subsystems.LimeVisionSubsystem;

/**
 * An example command that uses an example subsystem.
 */
public class msDrive extends CommandBase
{

  private final SwerveSubsystem  swerve;
  private final DoubleSupplier   vX;
  private final DoubleSupplier   vY;
  private final DoubleSupplier   omega;
  private final BooleanSupplier  driveMode;
  private final boolean          isOpenLoop;
  public final SwerveController controller;


  /**
   * Creates a new ExampleCommand.
   *
   * @param swerve The subsystem used by this command.
   */
  public msDrive(SwerveSubsystem swerve, DoubleSupplier vX, DoubleSupplier vY, DoubleSupplier omega,
                     BooleanSupplier driveMode, boolean isOpenLoop)
  {
    this.swerve = swerve;
    this.vX = vX;
    this.vY = vY;
    this.omega = omega;
    this.driveMode = driveMode;
    this.isOpenLoop = isOpenLoop;
    this.controller = swerve.getSwerveController();
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(swerve);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    LimeVisionSubsystem lsbs = new LimeVisionSubsystem();
    double xVelocity   = vX.getAsDouble();
    double yVelocity   = vY.getAsDouble();
    double angVelocity = omega.getAsDouble();
    SmartDashboard.putNumber("vX_ms", xVelocity);
    SmartDashboard.putNumber("vY_ms", yVelocity);
    SmartDashboard.putNumber("omega_ms", angVelocity);
    SmartDashboard.putNumber("LimeLightX: ", lsbs.getXOffset());
    swerve.drive(
        new Translation2d(
            xVelocity,
            yVelocity),
        angVelocity,
        driveMode.getAsBoolean(),
        isOpenLoop);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    return false;
  }
}
