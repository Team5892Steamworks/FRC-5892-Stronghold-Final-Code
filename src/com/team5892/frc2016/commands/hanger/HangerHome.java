package com.team5892.frc2016.commands.hanger;

import com.team5892.frc2016.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HangerHome extends Command {

    public HangerHome() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.hanger);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.hanger.setPivotAngle(1);
    	// Retract Arm
    	Robot.hanger.setWinchPower(-0.25, -0.25);
    	Timer.delay(2.0);
    	Robot.hanger.setWinchPower(0.0, 0.0);
    	Robot.hanger.encoderWinchLeft.reset();
    	Robot.hanger.encoderWinchRight.reset();
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
