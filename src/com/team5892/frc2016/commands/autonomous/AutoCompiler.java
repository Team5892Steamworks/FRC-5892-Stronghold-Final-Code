package com.team5892.frc2016.commands.autonomous;

import com.team5892.frc2016.Robot;
import com.team5892.frc2016.commands.ShooterSet;
import com.team5892.frc2016.commands.intake.IntakeSet;
import com.team5892.frc2016.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCompiler extends CommandGroup {
	
	public static AutoCompiler auto;
	private int position;
	private Routines[] routines = {null, null, null};
	
	public AutoCompiler() {
		position = (int) Robot.autoPosition.getSelected();
		routines[0] = (Routines) Robot.autoRoutine1.getSelected();
		routines[1] = (Routines) Robot.autoRoutine2.getSelected();
		routines[2] = (Routines) Robot.autoRoutine3.getSelected();
		
		// Do stuff with the auto position
		
		if(routines[0] == Routines.CrossSimple) {
			addSequential(new CrossSimple());
		}
		else if(routines[0] == Routines.CrossLowBar) {
			addSequential(new CrossLowBar());
		}
		else if(routines[0] == Routines.CrossCDF) {
			addSequential(new AutoDoNothing());
		}
		
		if(routines[1] == Routines.HighGoal) {
			addParallel(new ShooterSet());
			addSequential(new DriveToHighGoal(position));
			addSequential(new IntakeSet(Intake.State.Intake));
		}
		else if(routines[1] == Routines.LowGoal) {
			addSequential(new DriveToLowGoal(position));
			addSequential(new IntakeSet(Intake.State.Exhaust));
		}
		else if(routines[1] == Routines.Return) {
			addSequential(new AutoDoNothing());
		}
		else if(routines[1] == Routines.None) {
			addSequential(new AutoDoNothing());
		}
		
		if(routines[2] == Routines.None) {
			addSequential(new AutoDoNothing());
		}
		else if(routines[2] == Routines.Return) {
			addSequential(new AutoDoNothing());
		}
	}
	
	public enum Routines {
		CrossSimple, CrossLowBar, CrossCDF, Spybot, HighGoal, LowGoal, Return, None
	}
	
	public static Command getAuto() {
		System.out.println(Robot.autoRoutine1.getSelected());
		return null;
	}
	
}
