package com.team5892.frc2016.commands;

import com.team5892.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterStow extends Command {

    public ShooterStow() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.set(0.0);
    	Robot.shooter.setShooterHood(false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.set(0.0);
    	Robot.shooter.setShooterHood(false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.set(0.0);
    	Robot.shooter.setShooterHood(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooter.set(0.0);
    	Robot.shooter.setShooterHood(false);
    }
}
