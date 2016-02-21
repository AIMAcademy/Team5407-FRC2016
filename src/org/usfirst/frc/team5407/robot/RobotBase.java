package org.usfirst.frc.team5407.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotBase {

	Talon mot_LeftDriveMotor;
	Talon mot_RightDriveMotor; 
	
	double d_LeftDrivePower;
	double d_RightDrivePower; 
	
	double d_gyroAngle;
	Gyro shooterGyro;
	
	   /**
     * This function is run when this class is first created used for any initialization code.
     */
    public RobotBase(int PWMConnector_LeftDriveMotor,
		    		 int PWMConnector_RightDriveMotor) {
    	
    	mot_LeftDriveMotor = new Talon(PWMConnector_LeftDriveMotor);
    	mot_RightDriveMotor = new Talon(PWMConnector_RightDriveMotor);
    
    	
    	// Make sure motors are stopped
    	mot_LeftDriveMotor.set(0.0);  
    	mot_RightDriveMotor.set(0.0); 
    	
		shooterGyro = new AnalogGyro(1);    	
    	
    //	this.bp_AllowedToDrive = true;					//this is a safety mode so robot will not move 
    }
    
    public void reset(){
		shooterGyro.reset();
    }
	
	
    public void update(){

    	/* Motors on one side are flipped over (inverted) so that if we apply the + power the robot goes in what you consider forward.  
    	 * In the case below we flip the Right. If it turns out that your robot is going backwards then you
    	 * would remove * -1 from right and put it on left. Just negating  as in -d_LeftFrontDrivePower does the same thing.      
    	 */
    	mot_LeftDriveMotor.set(d_LeftDrivePower);
    	mot_RightDriveMotor.set(d_RightDrivePower*-1.0);
    	
		d_gyroAngle = shooterGyro.getAngle();
		SmartDashboard.putNumber("Gyro Angle", d_gyroAngle);

    }
	
	
	
	
	
	
}
