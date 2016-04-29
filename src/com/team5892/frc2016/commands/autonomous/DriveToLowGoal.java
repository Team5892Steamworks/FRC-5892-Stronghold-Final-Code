package com.team5892.frc2016.commands.autonomous;

import com.team5892.frc2016.commands.drive.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveToLowGoal extends CommandGroup {
    
    public DriveToLowGoal(int position) {
    	if(position == 1) {
    		addSequential(new DriveCurveGyro(-0.4, 1.3, -50));
    	}
    }
}