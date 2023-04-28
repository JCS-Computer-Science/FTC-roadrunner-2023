package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.canvas.Canvas;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Twist2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    private DcMotorEx intakeMotor0;
    private DcMotorEx intakeMotor1;

    public Intake(HardwareMap hardwareMap) {
        this.intakeMotor0 = hardwareMap.get(DcMotorEx.class, "intakeMotor0");
        this.intakeMotor1 = hardwareMap.get(DcMotorEx.class, "intakeMotor1");

        this.intakeMotor1.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        this.intakeMotor0.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        this.intakeMotor1.setDirection(DcMotorEx.Direction.REVERSE);
    }

    /**
     * Sets the power of the intake motors.
     *
     * @param power The power to set the motors to.
     */
    public void setPower(double power) {
        intakeMotor0.setPower(power);
        intakeMotor1.setPower(power);
    }

    public Action stop() {
        return new Action() {
            @Override
            public boolean run(TelemetryPacket telemetryPacket) {
                setPower(0);
                return false;
            }

            @Override
            public void preview(Canvas canvas) {

            }
        };
    }
}
