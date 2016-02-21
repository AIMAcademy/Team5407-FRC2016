package org.usfirst.frc.team5407.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter{
	
	Talon mot_ShooterPower; 
		
	double d_ShooterPower;
	
	public Shooter(int PWMConnector_ShooterPower){
		mot_ShooterPower = new Talon(PWMConnector_ShooterPower);
		//make sure all motors are stopped 
		mot_ShooterPower.set(0.0);
	}

	public void update(){
		mot_ShooterPower.set(d_ShooterPower);
	}

}
