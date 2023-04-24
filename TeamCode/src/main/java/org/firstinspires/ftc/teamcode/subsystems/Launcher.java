package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DigitalChannel;

public class Launcher {

    private Solenoid solenoid0;
    private Solenoid solenoid1;

    public Launcher(HardwareMap hardwareMap) {
        this.solenoid0 = new Solenoid(hardwareMap.get(DigitalChannel.class, "solenoid0"));
        this.solenoid1 = new Solenoid(hardwareMap.get(DigitalChannel.class, "solenoid1"));
    }

    public void setState(boolean state) {
        solenoid0.setState(state);
        solenoid1.setState(state);
    }
}
