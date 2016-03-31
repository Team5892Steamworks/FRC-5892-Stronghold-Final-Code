package com.team5892.frc2016.subsystems;

import com.androb4.frc.lib.CheesySpeedController;
import com.team5892.frc2016.Robot;
import com.team5892.frc2016.RobotMap;
import com.team5892.frc2016.commands.intake.IntakeManual;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	
	private CheesySpeedController m_intake = new CheesySpeedController(
			new VictorSP(RobotMap.pwm_intake_1),
			Robot.pdp,
			RobotMap.pdp_intake_1);
    
	public AnalogInput ball_sensor = new AnalogInput(RobotMap.ai_intake_ball_sensor);
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new IntakeManual());
    }
    
    public void setUnsafePower(double speed) {
    	m_intake.set(speed);
    }
    
    public boolean isBallPresent() {
    	return ball_sensor.getVoltage() < RobotMap.kIntakeBallThreshold;
    }
}

