package com.team5892.frc2016.commands.hanger;

import com.team5892.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HangerRest extends Command {

    public HangerRest() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.hanger);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.hanger.setPivotAngle(2.0);
    	//Robot.hanger.setArmLength(0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.hanger.getLeftAngle() < 2.5 && Robot.hanger.getLeftAngle() > -2.5;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
