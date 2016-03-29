package com.team5892.frc2016.commands.autonomous;

import com.team5892.frc2016.commands.*;
import com.team5892.frc2016.commands.drive.DriveDeadReckoning;
import com.team5892.frc2016.commands.hanger.*;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ClassBDCross extends CommandGroup {
    
    public  ClassBDCross() {
    	addSequential(new DriveDeadReckoning(0.65, 0.65, 3.5));
    }
}
