package com.team5892.frc2016.subsystems;

import com.androb4.frc.lib.CheesySpeedController;
import com.team5892.frc2016.Robot;
import com.team5892.frc2016.RobotMap;
import com.team5892.frc2016.commands.*;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hanger extends Subsystem {
    
    CheesySpeedController m_pivot_left = new CheesySpeedController(
    		new VictorSP(RobotMap.pwm_hanger_pivot_left),
    		Robot.pdp, RobotMap.pdp_hanger_pivot_left);
    
    CheesySpeedController m_pivot_right = new CheesySpeedController(
    		new VictorSP(RobotMap.pwm_hanger_pivot_right),
    		Robot.pdp, RobotMap.pdp_hanger_pivot_right);
    
    CheesySpeedController m_winch_left = new CheesySpeedController(
    		new VictorSP(RobotMap.pwm_hanger_winch_left),
    		Robot.pdp, RobotMap.pdp_hanger_winch_left);
    
    CheesySpeedController m_winch_right = new CheesySpeedController(
    		new VictorSP(RobotMap.pwm_hanger_winch_right),
    		Robot.pdp, RobotMap.pdp_hanger_winch_right);
    
    AnalogInput ai_angle_left = new AnalogInput(RobotMap.ai_hanger_angle_left);
    AnalogInput ai_angle_right = new AnalogInput(RobotMap.ai_hanger_angle_right);
    
    Solenoid ptoSolenoid = new Solenoid(RobotMap.solenoid_hanger_pto);
    
    public Hanger() {
    	m_pivot_left.setInverted(true);
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new HangerManual());
    }
    
    public void setPivotPower(double left, double right) {
    	m_pivot_left.set(left);
    	m_pivot_right.set(right);
    }
    
    public void setWinchPower(double left, double right) {
    	m_winch_left.set(left);
    	m_winch_right.set(right);
    }
    
    public void ptoSet(boolean shift) {
    	ptoSolenoid.set(shift);
    }
    
    public double getLeftAngle() {
    	return ai_angle_left.getVoltage() * RobotMap.kHangerLeftPivotTheta;
    }
    
    public double getRightAngle() {
    	return ai_angle_right.getVoltage() * RobotMap.kHangerRightPivotTheta;
    }
    
}

