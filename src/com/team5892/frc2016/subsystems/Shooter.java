package com.team5892.frc2016.subsystems;

import com.androb4.frc.lib.CheesySpeedController;
import com.team5892.frc2016.Robot;
import com.team5892.frc2016.RobotMap;
import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class Shooter extends PIDSubsystem {

	private CheesySpeedController m_shooter = new CheesySpeedController(
			new SpeedController[]{
					new VictorSP(RobotMap.pwm_shooter_left),
					new VictorSP(RobotMap.pwm_shooter_right)},
			Robot.pdp, new int[]{
					RobotMap.pdp_shooter_left,
					RobotMap.pdp_shooter_right});
	private AnalogTrigger tach_trigger = new AnalogTrigger(RobotMap.ai_shooter_tach);
	Counter tach = new Counter(tach_trigger);
	
	public Shooter() {
		super("Shooter", RobotMap.kFlywheelP, RobotMap.kFlywheelI, RobotMap.kFlywheelD, RobotMap.kFlywheelF);
		tach_trigger.setLimitsVoltage(RobotMap.kTachAnalogTriggerLow, RobotMap.kTachAnalogTriggerHigh);
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
		return getVelocity();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		
	}
	
	public double getVelocity() {
		return (60.0/tach.getPeriod())/RobotMap.kFlywheelTicksPerRev;
	}
	
}
