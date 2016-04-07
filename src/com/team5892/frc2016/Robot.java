package com.team5892.frc2016;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.TrajectoryGenerator;
import com.team254.lib.trajectory.WaypointSequence;
import com.team5892.frc2016.commands.autonomous.*;
import com.team5892.frc2016.commands.drive.*;
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
	public static CameraServer ballCam;
	public static RIOPixel rioPixel;
	public static TrajectoryGenerator.Config config;
	public static Path path;
	public static WaypointSequence waypoints;
	
	public static BuiltInAccelerometer accel;
	public static double accelOffset = 0.0;
	public static double maxAccel = 0.0;
	
    Command autonomousCommand;
	private static SendableChooser autoPosition;
    public static SendableChooser autoRoutine1;
    public static SendableChooser autoRoutine2;
    public static SendableChooser autoRoutine3;

    public void robotInit() {
    	drive = new Drive();
    	hanger = new Hanger();
    	shooter = new Shooter();
    	intake = new Intake();
		oi = new OI();
		pdp = new PowerDistributionPanel();
		ballCam = CameraServer.getInstance();
		rioPixel = new RIOPixel();
		
		accel = new BuiltInAccelerometer();
		
		ballCam.setQuality(50);
		ballCam.setSize(0);
		//ballCam.startAutomaticCapture("cam1");
		
		autoPosition = new SendableChooser();
        autoRoutine1 = new SendableChooser();
        autoRoutine2 = new SendableChooser();
        autoRoutine3 = new SendableChooser();
        
        autoPosition.addDefault("Position 1", 1);
        autoPosition.addObject("Position 2", 2);
        autoPosition.addObject("Position 3", 3);
        autoPosition.addObject("Position 4", 4);
        autoPosition.addObject("Position 5", 5);
        autoPosition.addObject("Spy Bot", 0);
        SmartDashboard.putData("Auto Poistion", autoPosition);
        
        autoRoutine1.addDefault("None", AutoCompiler.Routines.None); // Test null condition!
        autoRoutine1.addObject("Cross Simple", AutoCompiler.Routines.AutoCrossSimple);
        autoRoutine1.addObject("Cross Low Bar", AutoCompiler.Routines.CrossLowBar);
        autoRoutine1.addObject("Spy Bot", AutoCompiler.Routines.Spybot);
        //autoRoutine1.addObject("DriveCurveGyro", new DriveCurveGyro(0.6, 1.0, -45.0));
        SmartDashboard.putData("Auto Routine 1", autoRoutine1);
        
        autoRoutine2.addDefault("None", AutoCompiler.Routines.None); // Test null condition!
        autoRoutine2.addObject("High Goal", AutoCompiler.Routines.HighGoal);
        autoRoutine2.addObject("LowGoal", AutoCompiler.Routines.LowGoal);
        autoRoutine2.addObject("Return", AutoCompiler.Routines.Return);
        SmartDashboard.putData("Auto Routine 2", autoRoutine2);
        
        autoRoutine3.addDefault("None", AutoCompiler.Routines.None); // Test null condition!
        autoRoutine3.addObject("Return", AutoCompiler.Routines.Return);
        SmartDashboard.putData("Auto Routine 3", autoRoutine3);
        
        SmartDashboard.putData(Scheduler.getInstance());
        
        config = new TrajectoryGenerator.Config();
        config.dt = 0.001;
        config.max_vel = 12.4 * 12.0;
        config.max_acc = 32.15 * 12.0;
        config.max_jerk = 96.85 * 12.0;
        
        //config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.02, 12.4*12.0, /*6.5616*/ 10.0*12.0, 196.85*12.0);
        
        //modifier = new TankModifier(trajectory).modify(0.685);
        
    }
	
    public void disabledInit(){
    	
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
	}

    public void autonomousInit() {
    	hanger.setBrake(false);
    	hanger.ptoSet(false);
    	
		autonomousCommand = new AutoCompiler();
        if (autonomousCommand != null) autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        updateSmartDashboard();
    }

    public void teleopInit() {

    	Robot.drive.resetGyro();
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
		SmartDashboard.putNumber("Heading", drive.getHeading());
    }
}
