package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DigitalChannel;

public class Solenoid {
    private static DigitalChannel solenoid;

    public Solenoid(DigitalChannel solenoid) {
        this.solenoid = solenoid;

        solenoid.setMode(DigitalChannel.Mode.OUTPUT);

        solenoid.setState(false);
    }

    /**
     * Sets the solenoid to the input state.
     * @param state The state to set the solenoid to.
     */
    public void setState(boolean state) {
        solenoid.setState(state);
    }

    public boolean getState() {
        return solenoid.getState();
    }
}
