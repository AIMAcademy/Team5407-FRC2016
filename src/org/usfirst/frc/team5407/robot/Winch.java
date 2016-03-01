package org.usfirst.frc.team5407.robot;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.VictorSP;

public class Winch {
	
	Servo serv_WinchBrake;
	Servo serv_LiftRelease;
	
	VictorSP mot_LiftWinchPower; 
	
	double d_WinchBrake;	
	double d_LiftWinchPower;
	double d_LiftRelease;
	
	
	public Winch(int PWMConnector_WinchBrake, int PWMConnector_LiftWinchPower, int PWMConnector_LiftRelease){
		
		serv_WinchBrake = new Servo(PWMConnector_WinchBrake); 
		mot_LiftWinchPower = new VictorSP(PWMConnector_LiftWinchPower);
		serv_LiftRelease = new Servo(PWMConnector_LiftRelease);
		
		//make sure all motors are stopped 
		serv_WinchBrake.set(0.0);
		serv_LiftRelease.set(0.0);
		mot_LiftWinchPower.set(0.0);
		
		}
	
		public void update(Inputs inputs){
			
	    	if(inputs.b_WinchBrake == true){
	    		d_WinchBrake = .8;
	    	} else {
	    		d_WinchBrake = 0;
	    	}
	    	
	    	//this is just for testing servo values can be tuned 
	    	if(inputs.b_LiftRelease == true){
	    		d_LiftRelease = .8;
	    	} else {
	    		d_LiftRelease = 0;
	    	}
	    	
	    	//this is just for testing can be motor speed can be tuned
	    	if(inputs.b_LiftWinchPower == true){
	    		d_LiftWinchPower = 80;
	    	} else {
	    		d_LiftWinchPower = 0;
	    	}
			serv_WinchBrake.set(d_WinchBrake);
			serv_LiftRelease.set(d_LiftRelease);
			mot_LiftWinchPower.set(d_LiftWinchPower);

		}

}
