package com.team5892.frc2016.commands.drive;

import com.team5892.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTurnInPlace extends Command {

	private double heading;
	private double timeout = -1.0;
	private double someConstantThatNeedsToBeTuned = 0.05;
	
    public DriveTurnInPlace(double heading) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
        this.heading = heading;
    }
    
    public DriveTurnInPlace(double heading, double timeout) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
        this.heading = heading;
        this.timeout = timeout;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drive.arcadeDrive(0.0, someConstantThatNeedsToBeTuned * (heading - Robot.drive.getHeading()));
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
