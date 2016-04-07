package com.team5892.frc2016.commands.autonomous;

import com.team5892.frc2016.commands.drive.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCrossSimple extends CommandGroup {
    
    public  AutoCrossSimple() {
        addSequential(new DriveStraightGyro(0.5, 1 /*3.0*/)); // Cross defense
    }
}
