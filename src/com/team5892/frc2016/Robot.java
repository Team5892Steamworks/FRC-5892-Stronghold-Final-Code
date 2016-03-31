package com.team5892.frc2016;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import com.team5892.frc2016.commands.autonomous.*;
import com.team5892.frc2016.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	//public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static Drive drive;
	public static Hanger hanger;
	public static Shooter shooter;
	public static Intake intake;
	public static OI oi;
	public static PowerDistributionPanel pdp;
	public static CameraServer fpvCam;
	public static RIOPixel rioPixel;
	
    Command autonomousCommand;
    SendableChooser chooser;

    public void robotInit() {
    	drive = new Drive();
    	hanger = new Hanger();
    	shooter = new Shooter();
    	intake = new Intake();
		oi = new OI();
		pdp = new PowerDistributionPanel();
		fpvCam = CameraServer.getInstance();
		//rioPixel = new RIOPixel();
		
		fpvCam.setQuality(50);
		fpvCam.setSize(0);
		fpvCam.startAutomaticCapture("cam1");
		
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", new ClassBDCross());
        //chooser.addObject("None", new CrossSimpleScoreHigh(1));
        chooser.addObject("Cross Simple Pos. 2", new CrossSimpleScoreHigh(2));
        chooser.addObject("Cross Simple Pos. 3", new CrossSimpleScoreHigh(3));
        chooser.addObject("Cross Simple Pos. 4", new CrossSimpleScoreHigh(4));
        chooser.addObject("Cross Simple Pos. 5", new CrossSimpleScoreHigh(5));
        SmartDashboard.putData("Auto mode", chooser);
        SmartDashboard.putData(Scheduler.getInstance());
    }
	
    public void disabledInit(){
    	hanger.setBrake(false);
        hanger.ptoSet(false);
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
	}

    public void autonomousInit() {
    	hanger.setBrake(false);
    	hanger.ptoSet(false);
    	
		autonomousCommand = new ClassBDCross();
        if (autonomousCommand != null) autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        updateSmartDashboard();
    }

    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
        
        hanger.setBrake(false);
        hanger.ptoSet(false);
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateSmartDashboard();
    } 
    
    public void testPeriodic() {
        //LiveWindow.run();
    }
    
    public void updateSmartDashboard() {
    	SmartDashboard.putNumber("Angle Pivot Left", hanger.getLeftAngle());
		SmartDashboard.putNumber("Angle Pivot Right", hanger.getRightAngle());
		SmartDashboard.putNumber("Arm Length Left", hanger.encoderWinchLeft.getDistance());
		SmartDashboard.putNumber("Arm Length Right", hanger.encoderWinchRight.getDistance());
		SmartDashboard.putNumber("Ball Sensor", intake.ball_sensor.getVoltage());
    }
}
