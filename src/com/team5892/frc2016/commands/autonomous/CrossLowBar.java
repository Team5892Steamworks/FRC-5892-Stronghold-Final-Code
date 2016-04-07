package com.team5892.frc2016.commands.autonomous;

import com.team5892.frc2016.commands.drive.*;
import com.team5892.frc2016.commands.hanger.HangerRest;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossLowBar extends CommandGroup {
    
    public CrossLowBar() {
        addSequential(new HangerRest());
    	addSequential(new DriveStraightGyro(-0.5, 1.8));
    }
}
