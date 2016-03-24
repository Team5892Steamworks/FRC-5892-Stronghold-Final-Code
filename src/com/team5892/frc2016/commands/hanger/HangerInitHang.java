package com.team5892.frc2016.commands.hanger;

import com.team5892.frc2016.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**	
 *
 */
public class HangerInitHang extends Command {

    public HangerInitHang() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.hanger);
        requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.hanger.setPivotAngle(95.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.copilot.getRawButton(6)) {
    		Robot.drive.tankDrive(-Robot.oi.copilot.getRawAxis(5), -Robot.oi.copilot.getRawAxis(5));
    		Robot.hanger.setWinchPower(-Robot.oi.copilot.getRawAxis(5), -Robot.oi.copilot.getRawAxis(5));
    	}
    	else {
    		Robot.hanger.setWinchPower(-Robot.oi.copilot.getRawAxis(5), -Robot.oi.copilot.getRawAxis(5));
    	}
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
