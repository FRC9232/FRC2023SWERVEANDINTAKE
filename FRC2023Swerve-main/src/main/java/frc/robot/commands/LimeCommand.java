package frc.robot.commands;
import frc.robot.subsystems.LimeVisionSubsystem;
public class LimeCommand {
    
    private LimeVisionSubsystem limeSubsystem = new LimeVisionSubsystem();
    
    public double aimOmega;
    public double distanceVx;
    private static final double KLIMEAIM = -0.075; //aim alırken 0 a yaklaştıkça yavaşlaması için kullanılan kat sayı değeri
    private static final double KLIMEDIS = -0.6; //robot apriltag e olan uzaklığını ayarlarken 0 a yaklaştıkça yavaşlaması için kullanılan kat sayı değeri
    private static final double LIME_AIM_LIMIT = 5; // aim almayı hangi açıya gelince bırakacağı limit
    private static final double LIME_DISTANCE_LIMIT_METERS = 1; // Robotun apriltag e ne kadar uzakta durması istenen değişken
    private static final double LIME_MOUNT_ANGLE_DEGREES = 20; //limelightın monte edilme açısı
    private static final double LIME_LENS_MOUNT_HEIGHT_METERS = 0.20; //limelight ın lensinin yerden yüksekliği
    private static final double GOAL_HEIGHT_METERS = 1.445; // hedefin yerden yüksekliği(speakerın apriltagleri için bu değer 1.445)
    
    //limelight'ın aim alırken msDrive'ın omega değerinin ne olması gerektiğini belirleyen
    public double getAimOmega() {
        
        if (limeSubsystem.getXOffset() > LIME_AIM_LIMIT){
            
            aimOmega = limeSubsystem.getXOffset()*KLIMEAIM;
        }
        else if (limeSubsystem.getXOffset() < -1 * LIME_AIM_LIMIT){
            
            aimOmega = limeSubsystem.getXOffset()*KLIMEAIM;
        }
        else{
            
            aimOmega = 0;
        
        }
        
        return aimOmega;
    }
    
    // limelight'ın hedefe aim alıp almadığını kontrol eden method
    public boolean isLimeAimed(){
        if (limeSubsystem.getXOffset() < LIME_AIM_LIMIT && limeSubsystem.getXOffset() > -1 * LIME_AIM_LIMIT ){
            
            return true;

        }
        
        else{
            return false;
        }
    }
    
    //Robotun AprilTag e olan uzaklığını hesaplayan method
    
    public double getLimeDistance(){

        double angleToGoalRadians = (LIME_MOUNT_ANGLE_DEGREES + limeSubsystem.getYOffset()) * (3.14159 / 180.0);
        double distanceFromLimelightToGoalMeters = (GOAL_HEIGHT_METERS - LIME_LENS_MOUNT_HEIGHT_METERS) / Math.tan(angleToGoalRadians);
        
        return distanceFromLimelightToGoalMeters;

    }
    //Robotun AprilTag e olan uzaklığını msDrive için vX e çeviren method
    
    public double getLimeDistanceVx(){
        
        if (getLimeDistance() > (LIME_DISTANCE_LIMIT_METERS + 0.1)){
            
            distanceVx = getLimeDistance()*KLIMEDIS;
        }
        else if (limeSubsystem.getXOffset() < (LIME_DISTANCE_LIMIT_METERS - 0.1)){
            
            distanceVx = getLimeDistance()*KLIMEDIS * -1;
        }
        else{
            
            distanceVx = 0;
        
        }
        
        return distanceVx;


    }


}
