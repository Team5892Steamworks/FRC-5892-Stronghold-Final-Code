package com.team5892.frc2016.commands.autonomous;

import com.team5892.frc2016.commands.drive.DriveStraightGyro;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoReturn extends CommandGroup {
    
    public  AutoReturn(int position) {
    	if(position == 1) {
    		//addSequential(new DriveStraightGyro(0.5, 1.5));
    	}
    }
}
