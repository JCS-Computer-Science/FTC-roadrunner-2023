package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.canvas.Canvas;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

public class Launcher {
    private Solenoid solenoid0;
    private Solenoid solenoid1;
    private Servo servo;

    public Launcher(HardwareMap hardwareMap) {
        this.solenoid0 = new Solenoid(hardwareMap.get(DigitalChannel.class, "solenoid0"));
        this.solenoid1 = new Solenoid(hardwareMap.get(DigitalChannel.class, "solenoid1"));
        this.servo = hardwareMap.get(Servo.class, "loadServo");
    }

    public void setState(boolean fireState, boolean loadState) {
        solenoid0.setState(fireState);
        solenoid1.setState(fireState);

        servo.setPosition(loadState ? 0.5 : 0);
    }

    public Action setShooter(boolean state) {
        return new Action() {
            @Override
            public boolean run(TelemetryPacket telemetryPacket) {
                solenoid0.setState(state);
                solenoid1.setState(state);
                return false;
            }

            @Override
            public void preview(Canvas canvas) {

            }


        };
    }

    public Action setIndexer(boolean state) {
        return new Action() {
            @Override
            public boolean run(TelemetryPacket telemetryPacket) {
                servo.setPosition(state ? 0.25 : -0.25);
                return false;
            }

            @Override
            public void preview(Canvas canvas) {

            }
        };
    };
}