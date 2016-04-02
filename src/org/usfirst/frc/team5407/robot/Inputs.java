package org.usfirst.frc.team5407.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Inputs {
	
	Joystick joy_RightDriveStick; 
	Joystick joy_LeftWeaponsStick; 
	Joystick joy_LeftWeaponsButtons; 
	
	double d_TurnArcadeDrive;
	double d_PowerArcadeDrive; 
	double d_ShooterWinch;
	
	boolean b_ShiftGears; 
	boolean b_ShooterKicker;
	boolean b_ShooterArm;
	boolean b_ShooterExtension; 
	boolean b_ScissorLift;
	boolean b_WinchBrake;
	boolean b_LiftRelease; 
	boolean b_LiftWinchPowerUp;
	boolean b_LiftWinchPowerDown;
	boolean b_ShooterPower;
	boolean b_LowShot; 
	boolean b_HighShot;
	boolean b_PortcullisOpener;
	
	public Inputs(int USBConnector_RightDriveStick, int USBConnector_joy_LeftWeaponsStick, int USBConnector_joy_LeftWeaponsButtons){
		joy_RightDriveStick = new Joystick(USBConnector_RightDriveStick); 
		joy_LeftWeaponsStick = new Joystick(USBConnector_joy_LeftWeaponsStick); 
		joy_LeftWeaponsButtons = new Joystick(USBConnector_joy_LeftWeaponsButtons);
		zeroInputs();
	}
	
	public void readValues() {
		
		 d_PowerArcadeDrive = joy_RightDriveStick.getX() * -1 * .50;
		 d_TurnArcadeDrive = joy_RightDriveStick.getY() * -1;
		 d_ShooterWinch = joy_LeftWeaponsStick.getY();
		 
		 b_ScissorLift = joy_LeftWeaponsButtons.getRawButton(2);
		 b_ShooterPower = joy_LeftWeaponsStick.getRawButton(3);
		 b_ShiftGears = joy_RightDriveStick.getTrigger();
		 b_ShooterKicker = joy_LeftWeaponsStick.getTrigger(); 
		 b_ShooterArm = joy_LeftWeaponsButtons.getRawButton(1);
		 b_ShooterExtension = joy_RightDriveStick.getRawButton(10); 	
		 b_WinchBrake = joy_LeftWeaponsButtons.getRawButton(7);
		 b_LiftWinchPowerDown = joy_RightDriveStick.getRawButton(12);
		 b_LiftWinchPowerUp = joy_RightDriveStick.getRawButton(11);
		 b_PortcullisOpener = joy_LeftWeaponsStick.getRawButton(8); 
		 
		 // Testing buttons
		 b_LowShot = joy_LeftWeaponsButtons.getRawButton(4);
		 b_HighShot = joy_LeftWeaponsButtons.getRawButton(3);
		 
		 // Obsolete:
		 // b_LiftRelease = joy_LeftWeaponsStick.getRawButton(4);

	}
		
	public void zeroInputs(){  
		this.d_TurnArcadeDrive = 0.0;
		this.d_PowerArcadeDrive = 0.0;

	}
	
	

}
