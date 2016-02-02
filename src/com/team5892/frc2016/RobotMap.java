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
	public static int pwm_intake_1 = 4;
	public static int pwm_hanging_arm_pivot_1 = 5;
	public static int pwm_hanging_arm_pivot_2 = 6;
	
	// PDP
	public static int pdp_drive_left_1 = 0;
	public static int pdp_drive_left_2 = 1;
	public static int pdp_drive_right_1 = 2;
	public static int pdp_drive_right_2 = 3;
	public static int pdp_shooter_left = 4;
	public static int pdp_shooter_right = 5;
	public static int pdp_intake_1 = 6;
	public static int pdp_hanging_arm_pivot_1 = 7;
	public static int pdp_hanging_arm_pivot_2 = 8;
	
	// AI
	public static int ai_shooter_tach = 0;
	
	public static double kTachAnalogTriggerLow = 0;
	public static double kTachAnalogTriggerHigh = 0;
	
	public static double kFlywheelP = 0;
	public static double kFlywheelI = 0;
	public static double kFlywheelD = 0;
	public static double kFlywheelF = 0;
	public static double kFlywheelTicksPerRev = 360.0;
}
