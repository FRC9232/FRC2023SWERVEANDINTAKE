package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;
public class IntakeSubsystem extends SubsystemBase {
    
    private final CANSparkMax spark1;
    private final CANSparkMax spark2;

    public IntakeSubsystem(){
        this.spark1 = new CANSparkMax(Constants.INTAKE_MOTOR1_ID, MotorType.kBrushless);
        this.spark2 = new CANSparkMax(Constants.INTAKE_MOTOR2_ID, MotorType.kBrushless);    
    }

    public void setIntake(double open){
        if (open == 1){
            spark1.set(1);
            spark2.set(1);
        }
        else if (open == 2){
            spark1.set(-1);
            spark2.set(-1);
        }
        else{
            spark1.set(0);
            spark2.set(0);
        }

    }





}
