package com.team5892.frc2016;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	// PWM
	public static int pwm_drive_left = 0;
	public static int pwm_drive_right = 1;
	public static int pwm_shooter_left = 2;
	public static int pwm_shooter_right = 3;
	public static int pwm_intake_1 = 8;
	public static int pwm_hanger_pivot_left = 4;
	public static int pwm_hanger_pivot_right = 5;
	public static int pwm_hanger_winch_left = 6;
	public static int pwm_hanger_winch_right = 7;
	
	// PDP
	public static int pdp_drive_left_1 = 0;
	public static int pdp_drive_left_2 = 1;
	public static int pdp_drive_right_1 = 2;
	public static int pdp_drive_right_2 = 3;
	public static int pdp_shooter_left = 4;
	public static int pdp_shooter_right = 5;
	public static int pdp_intake_1 = 6;
	public static int pdp_hanger_pivot_left = 7;
	public static int pdp_hanger_pivot_right = 8;
	public static int pdp_hanger_winch_left = 9;
	public static int pdp_hanger_winch_right = 10;
	
	// DIO
	
	
	// AI
	public static int ai_shooter_tach = 0;
	public static int ai_hanger_angle_left = 1;
	public static int ai_hanger_angle_right = 2;
	
	// Solenoid
	public static int solenoid_hanger_pto = 0;
	
	public static double kTachAnalogTriggerLow = 0;
	public static double kTachAnalogTriggerHigh = 0;
	
	public static double kHangerLeftPivotTheta = 0.0;
	public static double kHangerRightPivotTheta = 0.0;
	
	public static double kFlywheelP = 0;
	public static double kFlywheelI = 0;
	public static double kFlywheelD = 0;
	public static double kFlywheelF = 0;
	public static double kFlywheelTicksPerRev = 360.0;
}
