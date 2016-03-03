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
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.hanger.setPivotPower(0.4, 0.4);
    	//Timer.delay(.5);
    	//Robot.hanger.setPivotPower(0.0, 0.0);
    	Robot.hanger.setPivotAngle(100.0);
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
    	Robot.hanger.setPivotPower(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.hanger.setPivotPower(0.0, 0.0);
    }
    
    
}
