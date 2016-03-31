package com.team5892.frc2016.commands;

import com.team5892.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterShootPrep extends Command {

    public ShooterShootPrep() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.set(1);
    	Robot.shooter.setShooterHood(true);
    	SmartDashboard.putBoolean("Shooter Status", true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putBoolean("Shooter Status", false);
    	//Scheduler.getInstance().add(new ShooterShoot());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	SmartDashboard.putBoolean("Shooter Status", false);
    	//Scheduler.getInstance().add(new ShooterShoot());
    }
}
