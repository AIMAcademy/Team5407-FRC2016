package org.usfirst.frc.team5407.robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.FlipAxis;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.AxisCamera;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	// RobotDrive myRobot;
	// Joystick joy_RightDriveStick;
	// Joystick joy_LeftWeaponsStick; 
	int autoLoopCounter;
	RobotBase robotbase; 
	Inputs inputs; 
	Solenoids solenoids; 
	Shooter shooter; 
	Winch winch;
	Auton auton; 
	
	// CameraServer server;
    int session;
    Image frame;
    AxisCamera camera;
    int autonSelector; 

    public Robot() {
    	
    	// USB camera code
        //server = CameraServer.getInstance();
        //server.setQuality(50);
        //the camera name (ex "cam0") can be found through the roborio web interface
        //server.startAutomaticCapture("cam0");
        
    	try {
    	// IP Camera Code
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        camera = new AxisCamera("10.54.07.10");
    	}
    	catch (Exception e) {
    		
    	}
    }

	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
    	robotbase = new RobotBase(0,1);
    	inputs = new Inputs(0,1,2);
    	shooter = new Shooter(2,5,1,2);
    	winch = new Winch(3,7,6);
    	     
    	
    	// Instructions to add a new solenoid:
    	// 1) Declare solenoids below.
    	// 2) Add the inputs to set the solenoid variables
    	// 3) Setup the solenoids objects in the Solenoids class
    	// 4) Set the solenoid variables to equal the input variables in RobotThink below
    	
    	solenoids = new Solenoids(0,	// Drive train shift gears
    							  1,	// Shooter kicker 
    							  2,	// Shooter arm 
    							  3,	// Shooter Extension
    							  4		// Scissors Lift
    							  );
    }
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit() {
    	autoLoopCounter = 0;
    	autonSelector = (int) SmartDashboard.getNumber("Auton Selector",0);
    	System.out.println("Auton Selector: " + autonSelector);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autonSelector){
    		case 1:{
    			if(autoLoopCounter < 150) //Check if we've completed 100 loops (approximately 2 seconds)
				{
    				robotbase.mot_LeftDriveMotor.set(0.75);
    				robotbase.mot_RightDriveMotor.set(0.75);
					} else {
					robotbase.mot_LeftDriveMotor.set(0);
					robotbase.mot_RightDriveMotor.set(0);
				}
    			break;
    		}
    		 case 2 :{
     			if(autoLoopCounter < 92) //Check if we've completed 100 loops (approximately 2 seconds)
 				{
     				shooter.mot_ShooterWinch.set(-1);
 					} else {
 	     			shooter.mot_ShooterWinch.set(0);
 				}
     			if(autoLoopCounter > 100 && autoLoopCounter < 300) //Check if we've completed 100 loops (approximately 2 seconds)
 				{
    				robotbase.mot_LeftDriveMotor.set(0.50);
    				robotbase.mot_RightDriveMotor.set(0.50);
					} else {
					robotbase.mot_LeftDriveMotor.set(0);
					robotbase.mot_RightDriveMotor.set(0);
 				} 
    		}
    		default:{
    		
    		}
    		
    	}
    	autoLoopCounter++;
    }
    
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    public void teleopInit(){
    	// robotbase.reset();
    	// Timer.delay(2);
    	winch.zeroInputs();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        // myRobot.arcadeDrive(joy_RightDriveStick);
    	
    	//robotbase.reset();	// resets gyro
    	//Timer.delay(2);		// waits for gyro to reset
    	
    	// IP Camera Code
        NIVision.Rect rect = new NIVision.Rect(227, 307, 50, 50);
        
        while (isOperatorControl() && isEnabled()) {
        	
        	// IP Camera Code
        	try {
        		
            camera.getImage(frame);
            NIVision.imaqFlip(frame, frame, FlipAxis.HORIZONTAL_AXIS);
            NIVision.imaqDrawShapeOnImage(frame, frame, rect, DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);
            CameraServer.getInstance().setImage(frame);
        		
        	}
        	catch (Exception e) {
        		
        	}
        	
            /** robot code here! **/
            inputs.readValues();
            robotbase.update();
            solenoids.update();
            shooter.update(inputs, solenoids);
            shooter.readValues();
            winch.update(inputs, solenoids);
            robotThink();
            
            // robotbase.gyroUpdate();
                        
            // Timer.delay(0.005);		// wait for a motor update time
        }

    }
    
    public void robotThink() {
    	robotbase.d_LeftDrivePower = inputs.d_PowerArcadeDrive - inputs.d_TurnArcadeDrive;
    	robotbase.d_RightDrivePower = inputs.d_PowerArcadeDrive + inputs.d_TurnArcadeDrive;
    	solenoids.b_ShiftGears = inputs.b_ShiftGears;
    	solenoids.b_ShooterKicker = inputs.b_ShooterKicker;
    	solenoids.b_ShooterExtension = inputs.b_ShooterExtension;
    	  	
    	// testing using potentiometer as limit switch
    	if(shooter.d_WinchPotentiometer < 1.0 && inputs.d_ShooterWinch > 0.1){
    		shooter.d_ShooterWinch = -0.5;
    		shooter.d_ShooterWinch = 0;
    	} else if (shooter.d_WinchPotentiometer > 3.0 && inputs.d_ShooterWinch < -0.1){
    		shooter.d_ShooterWinch = 0.5;
    		shooter.d_ShooterWinch = 0;
    	} else {
    		shooter.d_ShooterWinch = inputs.d_ShooterWinch;
    	}
    	
    	// Obsolete:
    	// solenoids.b_ShooterArm = inputs.b_ShooterArm; <-- not needed - using method inside shooter class instead
    	// solenoids.b_ScissorLift = inputs.b_ScissorLift; <-- not needed - using method inside winch class instead
    	
    }
    
    
    /**
     *
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
    
}
