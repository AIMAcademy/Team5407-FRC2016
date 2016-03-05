package org.usfirst.frc.team5407.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter{
	
	Talon mot_ShooterPower; 
	AnalogInput ana_WinchPotentiometer; 
	AnalogInput ana_ShooterHallEffectSensor; 
	
	VictorSP mot_ShooterWinch;
		
	double d_ShooterPower;	
	double d_ShooterWinch;
	
	// PID System Variables
    final double setPoints[] = {1.0, 2.6, 4.3}; //bottom, middle, and top elevator setpoints
    PIDController pidController;
    	//proportional, integral, and derivative speed constants; motor inverted 
    	//DANGER: when tuning PID constants, high/inappropriate values for pGain, iGain,
    	//and dGain may cause dangerous, uncontrollable, or undesired behavior!
    final double pGain = -5.0, iGain = -0.02, dGain = -2.0; //these may need to be positive for a non-inverted motor
  	
	int i_WinchPotentiometer;
	int i_ShooterHallEffectSensor;
	
	public Shooter(int PWMConnector_ShooterPower, int PWMConnector_ShooterWinch, int ANAConnector_WinchPotentiometer, int ANAConnector_ShooterHallEffectSensor){
			
		mot_ShooterPower = new Talon(PWMConnector_ShooterPower); 
		mot_ShooterWinch = new VictorSP(PWMConnector_ShooterWinch);
		ana_WinchPotentiometer = new AnalogInput(ANAConnector_WinchPotentiometer);
		ana_ShooterHallEffectSensor = new AnalogInput(ANAConnector_ShooterHallEffectSensor);
		
		//make sure all motors are stopped 
		mot_ShooterPower.set(0.0);
		mot_ShooterWinch.set(0.0);
		
		// PID
		pidController = new PIDController(pGain, iGain, dGain, ana_WinchPotentiometer, mot_ShooterPower);
		
	}
		
	public void readValues(){
		i_WinchPotentiometer = ana_WinchPotentiometer.getAverageValue();
		i_ShooterHallEffectSensor = ana_ShooterHallEffectSensor.getAverageValue();
		SmartDashboard.putNumber("Winch PID", i_WinchPotentiometer);
		SmartDashboard.putNumber("Hall Effect Sensor", i_ShooterHallEffectSensor);
	}
	
	public void pidSystem(){
		pidController.setInputRange(0, 5); //0 to 5V 
		pidController.setSetpoint(setPoints[0]); //set to first setpoint
		
        pidController.enable(); //begin PID control
        
    	int index = 0;
        
    	pidController.setSetpoint(setPoints[index]);

	}
		
	public void update(Inputs inputs, Solenoids solenoids){
		
		d_ShooterPower = 0;
		
		// Test spinning up shooter wheel
		if(inputs.b_ShooterPower == true){
			d_ShooterPower = -.65;
    	}
		
		// Intake mechanism
		if(inputs.b_ShooterArm == false){
			d_ShooterPower = .50;
			solenoids.b_ShooterArm = true;
    	} else {
    		solenoids.b_ShooterArm = false;
    	}
		
		// Test Low and High Shot Buttons
		if(inputs.b_LowShot == true){
			
		}
		
		mot_ShooterPower.set(d_ShooterPower);
		mot_ShooterWinch.set(d_ShooterWinch);
	}

}
