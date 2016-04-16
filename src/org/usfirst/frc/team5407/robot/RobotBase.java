package org.usfirst.frc.team5407.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotBase {

	Talon mot_LeftDriveMotor;
	Talon mot_RightDriveMotor;
	VictorSP mot_BackArmMotor; 
	
	double d_LeftDrivePower;
	double d_RightDrivePower; 
	
	boolean b_BackArmUp;
	boolean b_BackArmDown;
	
	double d_gyroAngle;
	Gyro shooterGyro;
	
	 /**
     * This function is run when this class is first created used for any initialization code.
     */
    public RobotBase(int PWMConnector_LeftDriveMotor,
		    		 int PWMConnector_RightDriveMotor,
		    		 int PWMConnector_BackArmMotor) {
    	
    	mot_LeftDriveMotor = new Talon(PWMConnector_LeftDriveMotor);
    	mot_RightDriveMotor = new Talon(PWMConnector_RightDriveMotor);
    	mot_BackArmMotor = new VictorSP (PWMConnector_BackArmMotor);
    	
    
    	// Make sure motors are stopped
    	mot_LeftDriveMotor.set(0.0);  
    	mot_RightDriveMotor.set(0.0); 
    	mot_BackArmMotor.set(0.0);
    	
    	shooterGyro = new AnalogGyro(0);
    	
    //	this.bp_AllowedToDrive = true;					//this is a safety mode so robot will not move 
    }
	
    public void reset(){
		shooterGyro.reset();
    }
	
    public void update(Inputs inputs){

    	/* Motors on one side are flipped over (inverted) so that if we apply the + power the robot goes in what you consider forward.  
    	 * In the case below we flip the Right. If it turns out that your robot is going backwards then you
    	 * would remove * -1 from right and put it on left. Just negating  as in -d_LeftFrontDrivePower does the same thing.      
    	 */
    	if(inputs.b_WinchSwitch == false){
	    	mot_LeftDriveMotor.set(d_LeftDrivePower);
	    	mot_RightDriveMotor.set(d_RightDrivePower*-1.0);
    	} else if(inputs.b_WinchSwitch == true){
    		mot_LeftDriveMotor.set(0);
        	mot_RightDriveMotor.set(0);
    	}
    	
    	if(inputs.b_BackArmUp == true){
	    	mot_BackArmMotor.set(0.25);
    	} else if(inputs.b_BackArmDown == true){
    		mot_BackArmMotor.set(-0.25);
    	} else {
    		mot_BackArmMotor.set(0);
    	}
    	
    }    	
    
    public void gyroUpdate(){
		// d_gyroAngle = shooterGyro.getAngle();
		// SmartDashboard.putNumber("Gyro Angle", d_gyroAngle);
    }
	
}
