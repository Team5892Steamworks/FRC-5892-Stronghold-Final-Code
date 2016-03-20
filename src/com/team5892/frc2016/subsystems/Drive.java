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
	
	private double throttleDeadband = 0.02;
	private double wheelDeadband = 0.02;
	double oldWheel, quickStopAccumulator;
	
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
    
    public void cheesyDrive2(double throttle, double wheel, boolean isQuickTurn) {

        double wheelNonLinearity;

        wheel = handleDeadband(wheel, wheelDeadband);
        throttle = handleDeadband(throttle, throttleDeadband);

        double negInertia = wheel - oldWheel;
        oldWheel = wheel;

        wheelNonLinearity = 0.5;
            // Apply a sin function that's scaled to make it feel better.
            wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel)
                    / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
            wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel)
                    / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
            wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel)
                    / Math.sin(Math.PI / 2.0 * wheelNonLinearity);

        double leftPwm, rightPwm, overPower;
        double sensitivity;

        double angularPower;
        double linearPower;

        // Negative inertia!
        double negInertiaAccumulator = 0.0;
        double negInertiaScalar;

            if (wheel * negInertia > 0) {
                negInertiaScalar = 2.5;
            } else {
                if (Math.abs(wheel) > 0.65) {
                    negInertiaScalar = 5.0;
                } else {
                    negInertiaScalar = 3.0;
                }
            }
            sensitivity = .85; // Constants.sensitivityLow.getDouble();
            
        double negInertiaPower = negInertia * negInertiaScalar;
        negInertiaAccumulator += negInertiaPower;

        wheel = wheel + negInertiaAccumulator;
        if (negInertiaAccumulator > 1) {
            negInertiaAccumulator -= 1;
        } else if (negInertiaAccumulator < -1) {
            negInertiaAccumulator += 1;
        } else {
            negInertiaAccumulator = 0;
        }
        linearPower = throttle;

        // Quickturn!
        if (isQuickTurn) {
            if (Math.abs(linearPower) < 0.2) {
                double alpha = 0.1;
                quickStopAccumulator = (1 - alpha) * quickStopAccumulator
                        + alpha * ((Math.abs(wheel) < 1.0) ? wheel : 1.0 * (wheel < 0 ? -1 : 1)) * 5;
            }
            overPower = 1.0;
            sensitivity = 1.0;
            angularPower = wheel;
        } else {
            overPower = 0.0;
            angularPower = Math.abs(throttle) * wheel * sensitivity
                    - quickStopAccumulator;
            if (quickStopAccumulator > 1) {
                quickStopAccumulator -= 1;
            } else if (quickStopAccumulator < -1) {
                quickStopAccumulator += 1;
            } else {
                quickStopAccumulator = 0.0;
            }
        }

        rightPwm = leftPwm = linearPower;
        leftPwm += angularPower;
        rightPwm -= angularPower;

        if (leftPwm > 1.0) {
            rightPwm -= overPower * (leftPwm - 1.0);
            leftPwm = 1.0;
        } else if (rightPwm > 1.0) {
            leftPwm -= overPower * (rightPwm - 1.0);
            rightPwm = 1.0;
        } else if (leftPwm < -1.0) {
            rightPwm += overPower * (-1.0 - leftPwm);
            leftPwm = -1.0;
        } else if (rightPwm < -1.0) {
            leftPwm += overPower * (-1.0 - rightPwm);
            rightPwm = -1.0;
        }
        m_drive_left.set(leftPwm);
        m_drive_right.set(rightPwm);
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
    
    public double handleDeadband(double val, double deadband) {
        return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
    }
}