package com.team5892.frc2016.commands.hanger;

import com.team5892.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HangerHang extends Command {

	private boolean isDone = false;
	
    public HangerHang() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.hanger);
        requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drive.tankDrive(Robot.oi.copilot.getRawAxis(5), Robot.oi.copilot.getRawAxis(5));
		Robot.hanger.setWinchPower(-Robot.oi.copilot.getRawAxis(5)*2, -Robot.oi.copilot.getRawAxis(5)*2);
		Robot.hanger.ptoSet(true);
		if(Robot.oi.copilot.getRawAxis(3) > 0.5) {
			Robot.hanger.disablePID();
			Robot.hanger.setBrake(true);
			isDone = true;
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
