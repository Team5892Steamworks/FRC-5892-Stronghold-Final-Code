package com.team5892.frc2016.commands;

import com.team5892.frc2016.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class ShooterShoot extends Command {

    public ShooterShoot() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooter);
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.intake.isBallPresent()) {
    		Timer.delay(1.5);
    		cancel();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Scheduler.getInstance().add(new ShooterStow());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Scheduler.getInstance().add(new ShooterStow());
    }
}
