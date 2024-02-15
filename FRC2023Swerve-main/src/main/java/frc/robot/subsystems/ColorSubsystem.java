package frc.robot.subsystems;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.ColorSensorV3;
public class ColorSubsystem extends SubsystemBase{
    
    private final I2C.Port i2cport = I2C.Port.kOnboard;

    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cport);
    
    public void execute(){
        Color detecedColor = m_colorSensor.getColor();

        double IR = m_colorSensor.getIR();

        SmartDashboard.putNumber("Red", detecedColor.red);
        SmartDashboard.putNumber("Green", detecedColor.green);
        SmartDashboard.putNumber("Blue", detecedColor.blue);
        SmartDashboard.putNumber("IR", IR);

        int proximity = m_colorSensor.getProximity();
        SmartDashboard.putNumber("Proximity", proximity);

    }
    
    public boolean hasIntakeTaken(){
        boolean intake;
        if(m_colorSensor.getProximity() < 4){
            intake = true;
        }
        else{
            intake = false;
        }
        return intake;
    }
    

}
