package com.androb4.frc.lib;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SpeedController;

public class CheesySpeedController implements SpeedController {
	
	protected SpeedController[] speedControllers;
	protected PowerDistributionPanel pdp;
	protected int[] pdpSlots;
	protected boolean invert = false;
	
	public CheesySpeedController(SpeedController speedController, PowerDistributionPanel pdp, int pdpSlot) {
		speedControllers = new SpeedController[]{speedController};
		this.pdp = pdp;
        pdpSlots = new int[]{pdpSlot};
	}
	
	public CheesySpeedController(SpeedController speedController, PowerDistributionPanel pdp, int[] pdpSlots) {
		speedControllers = new SpeedController[]{speedController};
		this.pdp = pdp;
        this.pdpSlots = pdpSlots;
	}
	
	public CheesySpeedController(SpeedController[] speedControllers, PowerDistributionPanel pdp, int[] pdpSlots) {
		this.speedControllers = speedControllers;
		this.pdp = pdp;
        this.pdpSlots = pdpSlots;
	}
	
	public double getCurrent() {
        double current = 0.0;
        for (int slot : pdpSlots) {
            current += pdp.getCurrent(slot);
        }
        return current;
    }
	
	public void setInverted(boolean inverted) {
		this.invert = inverted;
	}
	
	public boolean getInverted() {
		return this.invert;
	}
	
	protected double sign() {
        return (invert ? -1.0 : 1.0);
    }
	
	@Override
	public void set(double speed, byte syncGroup) {
		for (SpeedController controller : speedControllers) {
            controller.set(speed * sign(), syncGroup);
        }
	}

	@Override
	public void set(double speed) {
		for (SpeedController controller : speedControllers) {
            controller.set(speed * sign());
        }
	}
	
	@Override
	public double get() {
		return speedControllers[0].get() * sign();
	}

	@Override
	public void disable() {
		for (SpeedController speedController : speedControllers) {
            speedController.disable();
        }
	}
	
	@Override
	public void pidWrite(double output) {
		for (SpeedController speedController : speedControllers) {
            speedController.pidWrite(sign() * output);
        }
	}

}