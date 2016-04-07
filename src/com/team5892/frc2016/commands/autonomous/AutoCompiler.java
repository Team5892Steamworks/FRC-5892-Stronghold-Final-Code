package com.team5892.frc2016.commands.autonomous;

import com.team5892.frc2016.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCompiler extends CommandGroup {
	
	public static AutoCompiler auto;
	
	public AutoCompiler() {
		
		// Do stuff with the auto position
		
		if(Robot.autoRoutine1.getSelected() == Routines.AutoCrossSimple) {
			addSequential(new AutoCrossSimple());
		}
		else if(Robot.autoRoutine1.getSelected() == Routines.CrossLowBar) {
			addSequential(new CrossLowBar());
		}
		else if(Robot.autoRoutine1.getSelected() == Routines.CrossCDF) {
			addSequential(new CrossLowBar());
		}
		
		if(Robot.autoRoutine2.getSelected() == Routines.HighGoal) {
			addSequential(new AutoCrossSimple());
		}
		else if(Robot.autoRoutine2.getSelected() == Routines.LowGoal) {
			addSequential(new AutoCrossSimple());
		}
		else if(Robot.autoRoutine2.getSelected() == Routines.Return) {
			addSequential(new AutoCrossSimple());
		}
		else if(Robot.autoRoutine2.getSelected() == Routines.None) {
			addSequential(new AutoCrossSimple());
		}
		
		if(Robot.autoRoutine3.getSelected() == Routines.None) {
			addSequential(new AutoCrossSimple());
		}
		else if(Robot.autoRoutine3.getSelected() == Routines.Return) {
			addSequential(new AutoCrossSimple());
		}
	}
	
	public static Command[] routines = {null, null, null};
	
	public enum Routines {
		AutoCrossSimple, CrossLowBar, CrossCDF, Spybot, HighGoal, LowGoal, Return, None
	}

	public static void addRoutine(Command routine) {
		for(Command r: routines) {
			if(r == null) {
				r = routine;
			}
		}
	}
	
	public static Command getAuto() {
		System.out.println(Robot.autoRoutine1.getSelected());
		return null;
	}
	
}
