package com.team5892.frc2016.commands.drive;

import com.team5892.frc2016.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightGyro extends Command {

	private double speed;
	private double seconds;
	private double someConstant = 0.05;
	
    public DriveStraightGyro(double speed, double seconds) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
        this.speed = speed;
        this.seconds = seconds;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drive.arcadeDrive(speed, someConstant * Robot.drive.getHeading());
    	Timer.delay(seconds);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
