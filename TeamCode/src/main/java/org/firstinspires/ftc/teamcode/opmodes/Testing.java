package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.subsystems.Solenoid;


public final class Testing extends LinearOpMode {

    private static Solenoid solenoid0;
    private static Solenoid solenoid1;

//    private TelemetryPacket packet = new TelemetryPacket();

    @Override
    public void runOpMode() throws InterruptedException {
        solenoid0 = new Solenoid(hardwareMap.get(DigitalChannel.class, "solenoid0"));
        solenoid1 = new Solenoid(hardwareMap.get(DigitalChannel.class, "solenoid1"));

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                active(true);
            } else if (gamepad1.b) {
                active(false);
            }


//            packet.put("Solenoid 1", solenoid0.getState());
//            packet.put("Solenoid 2", solenoid1.getState());
//            telemetry.update();
        }
    }

    /**
     * Sets both solenoids to the input state.
     * @param state The state to set the solenoids to.
     */
    private void active(boolean state) {
        solenoid0.setState(state);
        solenoid1.setState(state);
    }
}