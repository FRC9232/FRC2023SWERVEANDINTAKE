package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ColorSubsystem;
public class IntakeSetCommand extends CommandBase{
    
    private final IntakeSubsystem intakeSubsystem;
    private final ColorSubsystem colorSubsystem;
    private  double open;
    
    public IntakeSetCommand(IntakeSubsystem intakeSubsystem,ColorSubsystem colorSubsystem, double open){
        this.intakeSubsystem = intakeSubsystem;
        this.colorSubsystem = colorSubsystem;
        this.open= open;
        addRequirements(intakeSubsystem, colorSubsystem);
    }
    public void execute(){
        if (open == 1 && colorSubsystem.hasIntakeTaken()){
            open = 0;
        }
        
        
        intakeSubsystem.setIntake(open);
        
    }
    public void end(){
        intakeSubsystem.setIntake(0);
    }
}
