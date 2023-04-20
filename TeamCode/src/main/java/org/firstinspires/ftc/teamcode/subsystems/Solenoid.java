package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.hardware.DigitalChannel;

public class Solenoid {
    private DigitalChannel solenoid;

    public Solenoid(DigitalChannel s) {
        this.solenoid =  s;

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
