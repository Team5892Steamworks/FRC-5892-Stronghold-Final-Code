package com.team5892.frc2016.commands;

import com.team5892.frc2016.Robot;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearShiftSet extends Command {

    public GearShiftSet() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.hanger);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.hanger.ptoSet(true);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.hanger.ptoSet(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.hanger.ptoSet(false);
    }
}
