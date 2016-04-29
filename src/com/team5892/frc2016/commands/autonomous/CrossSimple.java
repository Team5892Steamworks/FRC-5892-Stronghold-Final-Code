package com.team5892.frc2016.commands.autonomous;

import com.team5892.frc2016.commands.drive.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossSimple extends CommandGroup {
    
    public  CrossSimple() {
        addSequential(new DriveStraightGyro(0.4, 2.0)); // Drive 134 inches. Cross defense
    }
}
