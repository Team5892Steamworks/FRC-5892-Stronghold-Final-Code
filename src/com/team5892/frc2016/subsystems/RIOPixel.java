package com.team5892.frc2016.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RIOPixel extends Subsystem {
    
	private SerialPort rioPixelSerial;
	
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
		}
		catch(Exception e) {
			System.err.println(e);
		}
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setLights(Pattern pattern) {
		try {
			rioPixelSerial.write(new byte[]{(byte) pattern.getControlByte()}, 1);
		}
		catch(Exception e) {
			System.err.println(e);
		}
    }
}

