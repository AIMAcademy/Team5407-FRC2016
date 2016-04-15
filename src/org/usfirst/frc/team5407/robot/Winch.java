package org.usfirst.frc.team5407.robot;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;

public class Winch {
	
	Servo serv_WinchBrake;
	Servo serv_LiftRelease;
	
	VictorSP mot_LiftWinchPower; 
	
	double d_WinchBrake;	
	double d_LiftWinchPower;
	double d_LiftRelease;
	
	int i_periodicCounter;
		
	public Winch(int PWMConnector_WinchBrake, int PWMConnector_LiftWinchPower, int PWMConnector_LiftRelease){
		
		serv_WinchBrake = new Servo(PWMConnector_WinchBrake); 
		mot_LiftWinchPower = new VictorSP(PWMConnector_LiftWinchPower);
		serv_LiftRelease = new Servo(PWMConnector_LiftRelease);
		
		//make sure all motors are stopped 
		serv_WinchBrake.set(0.0);
		serv_LiftRelease.set(0.8);
		mot_LiftWinchPower.set(0.0);
		
		// zero counter
		i_periodicCounter = 0;
		
	}
	
		public void update(Inputs inputs, Solenoids solenoids){
			
//		d_LiftWinchPower = 0;
//			
//	    	if(inputs.b_WinchBrake == true){
//	    		d_WinchBrake = 0.8;
//	    	} else {
//	    		d_WinchBrake = 0;
//	    	}
//	    	
//	    	//this is just for testing servo values can be tuned 
	    	if(inputs.b_ScissorLift == true){
//	    		d_LiftRelease = 0.2;
	    		 {
	    			solenoids.b_ScissorLift = true;
	    		}
	    	} else {
//	    		d_LiftRelease = 0.8;
    			solenoids.b_ScissorLift = false;
	    	}
//	     	
//	    	//this is just for testing can be motor speed can be tuned
//	    	if(inputs.b_LiftWinchPowerUp == true){
//	    		d_LiftWinchPower = 0.80;
//	    	} else if(inputs.b_LiftWinchPowerDown == true) {
//	    		d_LiftWinchPower = -0.20;
//	    	}
//	    	
//			serv_WinchBrake.set(d_WinchBrake);
//			serv_LiftRelease.set(d_LiftRelease);
//			mot_LiftWinchPower.set(d_LiftWinchPower);
//
		}
		
		public void zeroInputs(){
			i_periodicCounter = 0;
		}
		

}
