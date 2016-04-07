package com.team5892.frc2016.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RIOPixel extends Subsystem {
    
	private SerialPort rioPixelSerial;
	
	public enum Lights {
		noComm(0), disabled(1), autoIntake(2), spinUp(3), shoot(4), scalingMode(5), scaled(6);
		
		private int value;
		
		private Lights(int value) {
		      this.value = value;
		}

	    public int getValue() {
	      return this.value;
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
    
    public void setLights(Lights pattern) {
    	
    }
}

