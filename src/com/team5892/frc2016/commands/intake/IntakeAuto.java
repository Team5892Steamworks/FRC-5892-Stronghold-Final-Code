package com.team5892.frc2016.commands.intake;

import com.team5892.frc2016.Robot;
import com.team5892.frc2016.subsystems.RIOPixel.Pattern;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeAuto extends Command {

	private boolean isDone = false;
	
    public IntakeAuto() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.rioPixel.setLights(3);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!Robot.intake.isBallPresent()) {
    		Robot.intake.setUnsafePower(0.5);
    		Robot.intake.setHarvesterUnsafe(0.5);
    	}
    	else {
    		Robot.intake.setUnsafePower(0.0);
    		Robot.intake.setHarvesterUnsafe(0.0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.oi.pilot.getRawAxis(3) > 0.5 || Robot.oi.pilot.getRawAxis(2) > 0.5) {
    		isDone = true;
    	}
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.setUnsafePower(0.0);
    	Robot.intake.setHarvesterUnsafe(0.0);
    	//Robot.rioPixel.setLights(4);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intake.setUnsafePower(0.0);
    	Robot.intake.setHarvesterUnsafe(0.0);
    }
}
