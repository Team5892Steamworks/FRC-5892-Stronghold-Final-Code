package com.team5892.frc2016.subsystems;

import com.androb4.frc.lib.CheesySpeedController;
import com.team5892.frc2016.Robot;
import com.team5892.frc2016.RobotMap;
import com.team5892.frc2016.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem {
    
	CheesySpeedController m_drive_left = new CheesySpeedController(
			new VictorSP(RobotMap.pwm_drive_left),
			Robot.pdp, new int[]{
					RobotMap.pdp_drive_left_1,
					RobotMap.pdp_drive_left_2});
	CheesySpeedController m_drive_right = new CheesySpeedController(
			new VictorSP(RobotMap.pwm_drive_right),
			Robot.pdp, new int[]{
					RobotMap.pdp_drive_right_1,
					RobotMap.pdp_drive_right_2});
	
	public Drive() {
		m_drive_right.setInverted(true);
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveWithJoysticks());
    }
    
    public void cheesyDrive(double throttle, double turn, boolean quickTurn) {
    	turn = turn * -1.5;
    	if (!quickTurn)
    		turn = turn * Math.abs(throttle);
    	double t_left = throttle + turn;
    	double t_right = throttle - turn;

    	double left = t_left + skim(t_right);
    	double right = t_right + skim(t_left);
    	
    	m_drive_left.set(left*0.5);
    	m_drive_right.set(right*0.5);
    }
    
    public void arcadeDrive(double throttle, double turn) {
    	m_drive_left.set(throttle+turn);
    	m_drive_right.set(throttle-turn);
    }
    
    public void tankDrive(double left, double right) {
    	m_drive_left.set(left);
    	m_drive_right.set(right);
    }
    
    public double skim(double v) {
    	double gain = 0.001;
    	// gain determines how much to skim off the top
    	if (v > 1.0)
    		return -((v - 1.0) * gain);
    	else if (v < -1.0)
    		return -((v + 1.0) * gain);
    	return 0.0;
    }
}