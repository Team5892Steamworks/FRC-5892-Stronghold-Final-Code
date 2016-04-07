package com.team5892.frc2016.commands.autonomous;

import com.team5892.frc2016.commands.drive.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveToHighGoal extends CommandGroup {
    
    public DriveToHighGoal(int position) {
    	if(position == 1) {
    		
    	}
    	else if(position == 2) {
    		addSequential(new DriveCurveGyro(0.5, 2.0, -50.0));
    	}
    	else if(position == 3) {
    		addSequential(new DriveCurveGyro(0.5, 1.0, -20.0));
    		addSequential(new DriveCurveGyro(0.5, 1.0, 20.0));
    	}
    	else if(position == 4) {
    		addSequential(new DriveCurveGyro(0.5, 1.0, -15.0));
    		addSequential(new DriveCurveGyro(0.5, 1.0, 15.0));
    	}
    	else if(position == 5) {
    		addSequential(new DriveStraightGyro(0.5, 0.5));
    		addSequential(new DriveCurveGyro(0.5, 0.7, 50.0));
    	}
    }
}
