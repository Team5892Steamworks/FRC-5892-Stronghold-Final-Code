package com.team5892.frc2016.subsystems;

import com.androb4.frc.lib.CheesySpeedController;
import com.team5892.frc2016.Robot;
import com.team5892.frc2016.RobotMap;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class Shooter extends PIDSubsystem {

	CheesySpeedController m_shooter = new CheesySpeedController(new VictorSP[]{new VictorSP(RobotMap.pwm_shooter_left), new VictorSP(RobotMap.pwm_shooter_right)}, Robot.pdp, new int[]{RobotMap.pdp_shooter_left, RobotMap.pdp_shooter_right});
	Counter tach = new Counter(RobotMap.d_shooter_tach);
	
	public Shooter() {
		super("Shooter", 1.0, 0.0, 1.0, 1.0);
		tach.setUpDownCounterMode();
	}
	
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	public void set(double speed) {
		m_shooter.set(speed);
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		
	}
	
}
