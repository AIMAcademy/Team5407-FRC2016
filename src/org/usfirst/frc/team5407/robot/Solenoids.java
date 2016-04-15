package org.usfirst.frc.team5407.robot;

import edu.wpi.first.wpilibj.Solenoid;

public class Solenoids {
	
	Solenoid s_robotbase;
	Solenoid s_shooterkicker;
	Solenoid s_shooterarm;
	Solenoid s_shooterextension; 
	Solenoid s_scissorlift; 
	Inputs inputs;
	
	boolean b_ShiftGears;
	boolean b_ShooterKicker; 
	boolean b_ShooterArm;
	boolean b_ShooterExtension; 
	boolean b_ScissorLift; 
	
	public Solenoids(int s0,int s1,int s2,int s3, int s4){
		s_robotbase = new Solenoid(s0);
		s_shooterkicker = new Solenoid(s1);
		s_shooterarm = new Solenoid(s2);
		s_shooterextension = new Solenoid(s3);
		s_scissorlift = new Solenoid(s4);
		
	}
	
	public void update(){
		
//		if (inputs.b_ShiftGears == true){
//			s_robotbase.set(true);
//		} else if (inputs.b_ShiftGears == false){
//			s_robotbase.set(false);	
//		}
		
		
		
		s_robotbase.set(b_ShiftGears);
		s_shooterkicker.set(b_ShooterKicker);
		s_shooterarm.set(b_ShooterArm);
		s_shooterextension.set(b_ShooterExtension);
		s_scissorlift.set(b_ScissorLift);
		
	}

}
