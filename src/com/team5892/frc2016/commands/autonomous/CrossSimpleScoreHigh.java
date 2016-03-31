package com.team5892.frc2016.commands.autonomous;

import com.team5892.frc2016.commands.drive.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossSimpleScoreHigh extends CommandGroup {
    
    public  CrossSimpleScoreHigh(int position) {
    	addSequential(new DriveStraightGyro(0.75, 3.0));
    	
    	switch(position) {
    	case 2:
    		addSequential(new DriveTurnInPlace(50.0));
    		return;
    	case 3:
    		addSequential(new DriveTurnInPlace(20.0));
    		return;
    	case 4:
    		addSequential(new DriveTurnInPlace(-10.0));
    		return;
    	case 5:
    		addSequential(new DriveTurnInPlace(-60.0));
    		return;
    	}
    	addSequential(new DriveStraightGyro(0.5, 3.0));

    }
}
