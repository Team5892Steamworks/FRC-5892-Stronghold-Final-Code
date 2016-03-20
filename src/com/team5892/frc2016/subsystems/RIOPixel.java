package com.team5892.frc2016.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RIOPixel extends Subsystem {
    
	private SerialPort rioPixelSerial = new SerialPort(115200, SerialPort.Port.kUSB);
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

