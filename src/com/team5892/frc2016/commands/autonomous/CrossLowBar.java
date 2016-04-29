package com.team5892.frc2016.commands.autonomous;

import com.team5892.frc2016.commands.drive.*;
import com.team5892.frc2016.commands.hanger.HangerReleaseIntake;
import com.team5892.frc2016.commands.hanger.HangerRest;
import com.team5892.frc2016.commands.intake.IntakeSet;
import com.team5892.frc2016.subsystems.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossLowBar extends CommandGroup {
    
    public CrossLowBar() {
    	addSequential(new HangerReleaseIntake());
        addSequential(new HangerRest());
    	addSequential(new DriveStraightGyro(-0.4, 1.8));
    }
}
