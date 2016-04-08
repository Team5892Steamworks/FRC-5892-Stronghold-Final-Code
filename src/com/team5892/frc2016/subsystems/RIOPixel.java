package com.team5892.frc2016.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RIOPixel {
    
	private SerialPort rioPixelSerial;
	byte[] controlByte = {0};
	
	public enum Pattern {
		NoCommm(0), Disabled(1), Standby(2), AutoIntake(3), IsBall(4), FlywheelSpinUp(5), Shoot(6), ScaleMode(7), Scaled(8);

		int controlByte;
		
		Pattern(int controlByte) {
			this.controlByte = controlByte;
		}
		
		int getControlByte() {
			return this.controlByte;
		}
		
	}
	
	public RIOPixel() {
		try {
			rioPixelSerial = new SerialPort(115200, SerialPort.Port.kUSB);
			System.out.println("RIOPixel Initialized");
		}
		catch(Exception e) {
			System.err.println(e);
		}
	}
    
    public void setLights(int pattern) {
		try {
			rioPixelSerial.write(controlByte, 1);
			System.out.println("RIOPixel Pattern Set");
		}
		catch(Exception e) {
			System.err.println(e);
		}
    }
}

