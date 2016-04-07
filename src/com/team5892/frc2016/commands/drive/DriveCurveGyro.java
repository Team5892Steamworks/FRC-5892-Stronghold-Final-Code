package com.team5892.frc2016.commands.drive;

import com.team5892.frc2016.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveCurveGyro extends Command {

	private double speed;
	private double seconds;
	private double theta;
	private double slope;
	private double kP = 0.01;
	private double error;
	private double errorLast;
	private double errorDeriv;
	
	private Timer timer;
	
    public DriveCurveGyro(double speed, double seconds, double theta) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
        this.speed = speed;
        this.seconds = seconds;
        this.theta = theta;
        this.slope = this.theta/this.seconds;
        timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.resetGyro();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(timer.hasPeriodPassed(this.seconds)) {
    		cancel();
    		super.cancel();
    	}
    	Robot.drive.arcadeDrive(speed, kP * ((slope * timer.get()) - Robot.drive.getHeading()));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
