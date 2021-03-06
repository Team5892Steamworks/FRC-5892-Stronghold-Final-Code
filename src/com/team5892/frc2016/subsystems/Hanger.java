package com.team5892.frc2016.subsystems;

import com.androb4.frc.lib.CheesySpeedController;
import com.team5892.frc2016.Robot;
import com.team5892.frc2016.RobotMap;
import com.team5892.frc2016.commands.hanger.*;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hanger extends Subsystem {
    
    private CheesySpeedController m_pivot_left = new CheesySpeedController(
    		new VictorSP(RobotMap.pwm_hanger_pivot_left),
    		Robot.pdp, RobotMap.pdp_hanger_pivot_left);
    
    private CheesySpeedController m_pivot_right = new CheesySpeedController(
    		new VictorSP(RobotMap.pwm_hanger_pivot_right),
    		Robot.pdp, RobotMap.pdp_hanger_pivot_right);
    
    private CheesySpeedController m_winch_left = new CheesySpeedController(
    		new VictorSP(RobotMap.pwm_hanger_winch_left),
    		Robot.pdp, RobotMap.pdp_hanger_winch_left);
    
    private CheesySpeedController m_winch_right = new CheesySpeedController(
    		new VictorSP(RobotMap.pwm_hanger_winch_right),
    		Robot.pdp, RobotMap.pdp_hanger_winch_right);
    
    private AnalogPotentiometer ai_angle_left = new AnalogPotentiometer(RobotMap.ai_hanger_angle_left, RobotMap.kHangerLeftPivotFullRange, RobotMap.kHangerLeftPivotOffset);
    private AnalogPotentiometer ai_angle_right = new AnalogPotentiometer(RobotMap.ai_hanger_angle_right, RobotMap.kHangerRightPivotFullRange, RobotMap.kHangerRightPivotOffset);
    
    public Encoder encoderWinchLeft = new Encoder(RobotMap.di_hanger_winch_encoder_left_a, RobotMap.di_hanger_winch_encoder_left_b);
    public Encoder encoderWinchRight = new Encoder(RobotMap.di_hanger_winch_encoder_right_a, RobotMap.di_hanger_winch_encoder_right_b);
    
    private DoubleSolenoid ptoSolenoid = new DoubleSolenoid(RobotMap.solenoid_hanger_pto_a, RobotMap.solenoid_hanger_pto_b);
    private DoubleSolenoid hangerBrake = new DoubleSolenoid(RobotMap.solenoid_hanger_brake_a, RobotMap.solenoid_hanger_brake_b);
    
	public DigitalInput switchLeft = new DigitalInput(5);
	public DigitalInput switchRight = new DigitalInput(6);
	
	PIDController pivotLeftController = new PIDController(0.05, 0.0, 0.01, ai_angle_left, m_pivot_left);
	PIDController pivotRightController = new PIDController(0.05, 0.0, 0.01, ai_angle_right, m_pivot_right);
	
	PIDController armLengthLeftController = new PIDController(-0.25, 0.0, 0.01, encoderWinchLeft, m_winch_left);
	PIDController armLengthRightController = new PIDController(-0.25, 0.0, 0.01, encoderWinchRight, m_winch_right);
	
	public boolean isPtoEngaged = false;
	public boolean isBrakeEngaged = false;
	public boolean isAngleAtSetpoint = false;
	
    public Hanger() {
    	// Motors
    	m_pivot_left.setInverted(false);
    	m_pivot_right.setInverted(true);
    	m_winch_left.setInverted(true);
    	m_winch_right.setInverted(false);
    	// Pivot Controllers
    	pivotLeftController.setOutputRange(-0.5, 0.5);
    	pivotRightController.setOutputRange(-0.5, 0.5);
    	pivotLeftController.setPercentTolerance(50);
    	// Arm Length Controllers
    	armLengthLeftController.setOutputRange(-0.75, 0.75);
    	armLengthRightController.setOutputRange(-0.75, 0.75);
    	// Sensors
    	encoderWinchRight.setDistancePerPulse(0.015);
    	encoderWinchLeft.setDistancePerPulse(0.015);
    	encoderWinchLeft.setReverseDirection(true);
    	encoderWinchRight.setReverseDirection(false);
    }
    
    public void initDefaultCommand() {
        //setDefaultCommand(new HangerManual());
    }
    
    /**
     * Sets the power for the hanger pivot. positive is up, negative is down.
     * 
     * @param left - Power for the left pivot motor.
     * @param right - Power for the left pivot motor.
     */
    public void setPivotPower(double left, double right) {
    	if(pivotLeftController.isEnabled())
    		pivotLeftController.disable();
    	if(pivotRightController.isEnabled())
    		pivotRightController.disable();
    	
    	if(getLeftAngle() > 110.0)
    		m_pivot_left.set(left);
    	if(getRightAngle() > 110.0)
    		m_pivot_right.set(right);
    }
    
    /**
     * Sets the power for the winch. Positive is extend, negative is retract.
     * 
     * @param left - Power for the left winch motor.
     * @param right - Power for the left winch motor.
     */
    public void setWinchPower(double left, double right) {
    	if(armLengthLeftController.isEnabled())
    		armLengthLeftController.disable();
    	if(armLengthRightController.isEnabled())
    		armLengthRightController.disable();
    	m_winch_left.set(left);
    	m_winch_right.set(right);
    }
    
    public void setUnsafePivotPower(double left, double right) {
    	m_pivot_left.set(left);
    	m_pivot_right.set(right);
    }
    
    /**
     * Shifts the drive transmission into the PTO.
     * 
     * @param shift Set to PTO
     */
    public void ptoSet(boolean shift) {
    	if(shift) {
    		ptoSolenoid.set(Value.kForward);
    	}
    	else if(!shift) {
    		ptoSolenoid.set(Value.kReverse);
    	}
    }
    
    /**
     * Returns angle of left arm in degrees. Angle is determined from the horizontal.
     * 
     * @return angle in degrees.
     */
    public double getLeftAngle() {
    	return ai_angle_left.get();
    }
    
    /**
     * Returns angle of right arm in degrees. Angle is determined from the horizontal.
     * 
     * @return angle in degrees.
     */
    public double getRightAngle() {
    	return ai_angle_right.get();
    }
    
    public void setPivotAngle(double angle) {
    	isAngleAtSetpoint = false;
    	if(!pivotLeftController.isEnabled())
    		pivotLeftController.enable();
    	pivotLeftController.setSetpoint(angle);
    	
    	if(!pivotRightController.isEnabled())
    		pivotRightController.enable();
    	pivotRightController.setSetpoint(angle);
    }
    
    public void setArmLength(double length) {
    	if(!armLengthLeftController.isEnabled())
    		armLengthLeftController.enable();
    	armLengthLeftController.setSetpoint(length);
    	
    	if(!armLengthRightController.isEnabled())
    		armLengthRightController.enable();
    	armLengthRightController.setSetpoint(length);
    }
    
    public void setBrake(boolean on) {
    	if(on) {
    		hangerBrake.set(Value.kForward);
    	}
    	else if(!on) {
    		hangerBrake.set(Value.kReverse);
    	}
    }
    
    public void enablePID() {
    	pivotLeftController.enable();
    	pivotRightController.enable();
    }
    
    public void disablePID() {
    	pivotLeftController.disable();
    	pivotRightController.disable();
    }
    
    public boolean isAngleAtSetpoint() {
    	isAngleAtSetpoint = false;
    	if(getLeftAngle() - pivotLeftController.getSetpoint() < 3.0 && getLeftAngle() - pivotLeftController.getSetpoint() > -3.0) {
    		isAngleAtSetpoint = true;
    	}
    	return isAngleAtSetpoint;
    }
}

