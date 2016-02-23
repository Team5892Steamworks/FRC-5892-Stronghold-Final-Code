package com.team5892.frc2016.subsystems;

import com.androb4.frc.lib.CheesySpeedController;
import com.team5892.frc2016.Robot;
import com.team5892.frc2016.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class HangingArm extends Subsystem {
    
    CheesySpeedController m_pivot = new CheesySpeedController(
    		new SpeedController[]{
    				new VictorSP(RobotMap.pwm_hanging_arm_pivot_1),
    				new VictorSP(RobotMap.pwm_hanging_arm_pivot_2)},
    		Robot.pdp, new int[]{
    				RobotMap.pdp_hanging_arm_pivot_1,
    				RobotMap.pdp_hanging_arm_pivot_2});

    
    
    public void initDefaultCommand() {
        //setDefaultComGmand(new MySpecialCommand());
    }
}	

