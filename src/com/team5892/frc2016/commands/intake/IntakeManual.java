package com.team5892.frc2016.commands.intake;

import com.team5892.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeManual extends Command {

    public IntakeManual() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.pilot.getRawButton(6)) {
    		Robot.intake.setUnsafePower(0.7);
    	}
    	else if(Robot.oi.pilot.getRawAxis(3) > 0.5) {
    		Robot.intake.setUnsafePower(-0.7);
    	}
    	else {
    		Robot.intake.setUnsafePower(0.0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.setUnsafePower(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intake.setUnsafePower(0.0);
    }
}
