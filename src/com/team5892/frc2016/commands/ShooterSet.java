package com.team5892.frc2016.commands;

import com.team5892.frc2016.Robot;
import com.team5892.frc2016.subsystems.RIOPixel.Pattern;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterSet extends Command {

    public ShooterSet() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.rioPixel.setLights(Pattern.FlywheelSpinUp);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.set(1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooter.set(0);
    }
}
