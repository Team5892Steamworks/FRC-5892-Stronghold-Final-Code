package com.team5892.frc2016.commands.intake;

import com.team5892.frc2016.Robot;
import com.team5892.frc2016.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeSet extends Command {

	private Intake.State state;
	
    public IntakeSet(Intake.State state) {
        requires(Robot.intake);
        this.state = state;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.set(state);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.set(Intake.State.Off);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intake.set(Intake.State.Off);
    }
}
