package com.team5892.frc2016.commands.drive;

import com.team5892.frc2016.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTurnInPlace extends Command {

	private double heading;
	private double timeout = -1.0;
	private double kP = 0.018;
	private double kD = 0.002;
	private double error;
	private double errorLast = 0.0;
	private double errorDeriv;
	private int onTargetCounter = 0;
	private boolean isDone = false;
	
	Timer timer = new Timer();
	double dt = 0.0;
	
    public DriveTurnInPlace(double heading) {
        this(heading, -1.0);
    }
    
    public DriveTurnInPlace(double heading, double timeout) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
        this.heading = heading;
        this.timeout = timeout;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(error <= 2.0 && error >= -2.0) {
    		onTargetCounter++;
    	}
    	else {
    		onTargetCounter = 0;
    	}
    	dt = timer.get() - dt;
    	error = heading - Robot.drive.getYaw();
    	errorDeriv = error - errorLast;
    	Robot.drive.arcadeDrive(0.0, kP * error + kD * (errorDeriv/dt));
    	SmartDashboard.putNumber("Dt", dt);
    	errorLast = error;
    	SmartDashboard.putNumber("Heading Error", error);
    	Timer.delay(0.001);
    	dt = timer.get();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return onTargetCounter == 1500;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drive.arcadeDrive(0, 0);
    }
}
