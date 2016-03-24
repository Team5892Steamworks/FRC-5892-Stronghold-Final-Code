package com.team5892.frc2016.commands;

import com.team5892.frc2016.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDeadReckoning extends Command {

	private double left;
	private double right;
	private double milliseconds;
	
    public DriveDeadReckoning(double left, double right, double milliseconds) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
        this.left = -left;
        this.right = -right;
        this.milliseconds = milliseconds;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.tankDrive(this.left, this.right);
    	Timer.delay(milliseconds);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.tankDrive(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drive.tankDrive(0.0, 0.0);
    }
}
