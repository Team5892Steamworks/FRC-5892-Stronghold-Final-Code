package com.team5892.frc2016.commands.hanger;

import com.team5892.frc2016.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HangerManual extends Command {

    public HangerManual() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.hanger);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.hanger.setUnsafePivotPower(-Robot.oi.copilot.getRawAxis(1)+ 0.2, -Robot.oi.copilot.getRawAxis(1)+ 0.2);   
    	Robot.hanger.setWinchPower(Robot.oi.copilot.getRawAxis(5), Robot.oi.copilot.getRawAxis(5));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.hanger.setPivotPower(0, 0);
    	Robot.hanger.setWinchPower(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.hanger.setPivotPower(0, 0);
    	Robot.hanger.setWinchPower(0, 0);
    }
}
