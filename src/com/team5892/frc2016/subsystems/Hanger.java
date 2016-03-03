package com.team5892.frc2016.subsystems;

import com.androb4.frc.lib.CheesySpeedController;
import com.team5892.frc2016.Robot;
import com.team5892.frc2016.RobotMap;
import com.team5892.frc2016.commands.hanger.*;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Solenoid;
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
    
    private AnalogPotentiometer ai_angle_left = new AnalogPotentiometer(RobotMap.ai_hanger_angle_left, RobotMap.kHangerLeftPivotKTheta);
    private AnalogPotentiometer ai_angle_right = new AnalogPotentiometer(RobotMap.ai_hanger_angle_right, RobotMap.kHangerRightPivotKTheta);
    
    private Solenoid ptoSolenoid = new Solenoid(RobotMap.solenoid_hanger_pto);
    
	public DigitalInput switchLeft = new DigitalInput(0);
	public DigitalInput switchRight = new DigitalInput(1);
	
	PIDController pivotLeftController = new PIDController(1.0, 0.0, 0.0, ai_angle_left, m_pivot_left);
	PIDController pivotRightController = new PIDController(1.0, 0.0, 0.0, ai_angle_right, m_pivot_right);
    
    public Hanger() {
    	m_pivot_right.setInverted(true);
    	m_winch_right.setInverted(true);
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new HangerManual());
    }
    
    public void setAngle() {
    	
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
    	ptoSolenoid.set(shift);
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
    	if(!pivotLeftController.isEnabled())
    		pivotLeftController.enable();
    	pivotLeftController.setSetpoint(angle);
    	
    	if(!pivotRightController.isEnabled())
    		pivotRightController.enable();
    	pivotRightController.setSetpoint(angle);
    }
}

