package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    // use two motors for intake, treat them as one but one of them should be reversed
    private DcMotorEx intakeMotor0;
    private DcMotorEx intakeMotor1;

    public Intake(HardwareMap hardwareMap) {
        this.intakeMotor0 = hardwareMap.get(DcMotorEx.class, "intakeMotor0");
        this.intakeMotor1 = hardwareMap.get(DcMotorEx.class, "intakeMotor1");
    }

    /**
     * Sets the power of the intake motors.
     * @param power The power to set the motors to.
     */
    public void setPower(double power) {
        intakeMotor0.setPower(power);
        intakeMotor1.setPower(power);

    }
}
