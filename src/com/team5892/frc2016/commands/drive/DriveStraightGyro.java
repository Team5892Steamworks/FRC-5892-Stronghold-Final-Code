package com.team5892.frc2016.commands.drive;

import com.team5892.frc2016.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightGyro extends Command {

	Timer timer;
	private double speed;
	private double seconds;	
	private double kP = 0.015;
	private boolean isDone = false;
	
    public DriveStraightGyro(double speed, double seconds) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
        this.speed = speed;
        this.seconds = seconds;
        timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.resetGyro();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(timer.hasPeriodPassed(seconds)) {
    		isDone = true;
    	}
    	Robot.drive.arcadeDrive(speed, kP * -Robot.drive.getHeading());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.arcadeDrive(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drive.arcadeDrive(0.0, 0.0);
    }
}
