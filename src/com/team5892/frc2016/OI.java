package com.team5892.frc2016;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import com.team5892.frc2016.commands.*;
import com.team5892.frc2016.commands.hanger.*;
import com.team5892.frc2016.commands.intake.IntakeAuto;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	public Joystick pilot = new Joystick(0);
	public Joystick copilot = new Joystick(1);
	
	//public Button shooterStart = new JoystickButton(copilot, 1);
	public Button HangerInit = new JoystickButton(copilot, 5);
	public Button hangerHang = new JoystickButton(copilot, 6);
	public Button hangerHome = new JoystickButton(copilot, 8);
	public Button hangerRest = new JoystickButton(copilot, 1);
	public Button hangerDrawbridgePre = new JoystickButton(copilot, 4);
	public Button hangerChevalPre = new JoystickButton(copilot, 3);
	public Button hangerSallyPortPre = new JoystickButton(copilot, 2);
	
	public Button shooterPrep = new JoystickButton(pilot, 5);
	public Button shooterStow = new JoystickButton(pilot, 6);
	public Button intakeAuto = new JoystickButton(pilot, 1);
	
	public OI() {
		//shooterStart.toggleWhenPressed(new ShooterSet());
		hangerHome.whileHeld(new HangerHome());
		hangerRest.whileHeld(new HangerRest());
		hangerDrawbridgePre.whileHeld(new HangerDrawbridgePre());
		hangerChevalPre.whileHeld(new HangerChevalPre());
		hangerSallyPortPre.whileHeld(new HangerSallyPre());
		
		shooterPrep.toggleWhenPressed(new ShooterShootPrep());
		//shooterStow.cancelWhenPressed(new ShooterShootPrep());
		intakeAuto.toggleWhenPressed(new IntakeAuto());
		HangerInit.whenPressed(new HangerInitHang());
		hangerHang.whenPressed(new HangerHang());
	} 
}
