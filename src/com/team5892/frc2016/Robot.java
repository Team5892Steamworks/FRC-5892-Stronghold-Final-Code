
package com.team5892.frc2016;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import com.team5892.frc2016.commands.autonomous.ClassBDCross;
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
	
    Command autonomousCommand;
    SendableChooser chooser;

    public void robotInit() {
    	drive = new Drive();
    	hanger = new Hanger();
    	shooter = new Shooter();
    	intake = new Intake();
		oi = new OI();
		pdp = new PowerDistributionPanel();
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", new ClassBDCross());
//        chooser.addObject("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto mode", chooser);
        SmartDashboard.putData(Scheduler.getInstance());
    }
	
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Angle Pivot Right", hanger.getRightAngle());
	}

    public void autonomousInit() {
        
		autonomousCommand = new ClassBDCross();
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        hanger.setBrake(false);
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putNumber("Angle Right", hanger.getRightAngle());
        SmartDashboard.putNumber("Right Arm Length", hanger.encoderWinchRight.getDistance());
    } 
    
    public void testPeriodic() {
        //LiveWindow.run();
    }
}
