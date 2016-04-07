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
	public static int pwm_hanger_pivot_left = 4;
	public static int pwm_hanger_pivot_right = 5;
	public static int pwm_hanger_winch_left = 6;
	public static int pwm_hanger_winch_right = 7;
	public static int pwm_intake_1 = 8;
	
	// PDP
	public static int pdp_drive_left_1 = 14;
	public static int pdp_drive_left_2 = 13;
	public static int pdp_drive_right_1 = 2;
	public static int pdp_drive_right_2 = 3;
	public static int pdp_shooter_left = 15;
	public static int pdp_shooter_right = 12;
	public static int pdp_intake_1 = 9;
	public static int pdp_hanger_pivot_left = 0;
	public static int pdp_hanger_pivot_right = 1;
	public static int pdp_hanger_winch_left = 10;
	public static int pdp_hanger_winch_right = 11;
	
	// DIO
	public static int di_hanger_winch_encoder_left_a = 0;
	public static int di_hanger_winch_encoder_left_b = 1;
	public static int di_hanger_winch_encoder_right_a = 2;
	public static int di_hanger_winch_encoder_right_b = 3;

	// AI
	public static int ai_gyro_drive = 0;
	public static int ai_hanger_angle_left = 1;
	public static int ai_hanger_angle_right = 2;
	public static int ai_intake_ball_sensor = 3;
	
	// Solenoid
	public static int solenoid_hanger_pto_a = 0;
	public static int solenoid_hanger_pto_b = 1;
	public static int solenoid_hanger_brake_a = 2;
	public static int solenoid_hanger_brake_b = 3;
	public static int solenoid_shooter_hood = 4;
	
	// Constants
	public static double kTachAnalogTriggerLow = 0;
	public static double kTachAnalogTriggerHigh = 0;
	
	public static double kHangerLeftPivotFullRange = -142.2893482;
	public static double kHangerLeftPivotOffset = 141.9356121;
	public static double kHangerRightPivotFullRange = 154.3103448;
	public static double kHangerRightPivotOffset = -37.7689552;
	
	public static double kHangerLeftBottomLimitDegrees = 2;
	public static double kHangerLeftUpLimitDegrees = 100;
	public static double kHangerRightBottomLimitDegrees = 2;
	public static double kHangerRightUpLimitDegrees = 100;
	
	public static double kIntakeBallThreshold = 3.5;
	
	public static int kDriveGyroAccumulatorCenter = 1995325;
	public static double kDriveGyroAccumulatorOffset = 0.171;
	
	public static double kFlywheelP = 0;
	public static double kFlywheelI = 0;
	public static double kFlywheelD = 0;
	public static double kFlywheelF = 0;
	public static double kFlywheelTicksPerRev = 360.0;
}
