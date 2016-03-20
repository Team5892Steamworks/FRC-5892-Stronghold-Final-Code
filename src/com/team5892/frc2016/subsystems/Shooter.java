package com.team5892.frc2016.subsystems;

import com.androb4.frc.lib.CheesySpeedController;
import com.team5892.frc2016.Robot;
import com.team5892.frc2016.RobotMap;
import com.team5892.frc2016.commands.ShooterStow;

import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class Shooter extends PIDSubsystem {

	VictorSP m_shooter_left = new VictorSP(RobotMap.pwm_shooter_left);
	VictorSP m_shooter_right = new VictorSP(RobotMap.pwm_shooter_right);
	
	private CheesySpeedController m_shooter = new CheesySpeedController(
			new SpeedController[]{
					m_shooter_left,
					m_shooter_right},
			Robot.pdp, new int[]{
					RobotMap.pdp_shooter_left,
					RobotMap.pdp_shooter_right});
	
	private Solenoid shooterHood = new Solenoid(RobotMap.solenoid_shooter_hood);
	
	private AnalogTrigger tach_trigger = new AnalogTrigger(RobotMap.ai_shooter_tach);
	private Counter tach = new Counter(tach_trigger);
	
	public Shooter() {
		super("Shooter", RobotMap.kFlywheelP, RobotMap.kFlywheelI, RobotMap.kFlywheelD, RobotMap.kFlywheelF);
		m_shooter_left.setInverted(true);
		m_shooter_right.setInverted(true);
		tach_trigger.setLimitsVoltage(RobotMap.kTachAnalogTriggerLow, RobotMap.kTachAnalogTriggerHigh);
		tach.setUpDownCounterMode();
	}
	
	protected void initDefaultCommand() {
		setDefaultCommand(new ShooterStow());
		
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
	
	public void setShooterHood(boolean open) {
		shooterHood.set(open);
	}
	
	public boolean isShooterHoodOpen() {
		return shooterHood.get();
	}
	
}
