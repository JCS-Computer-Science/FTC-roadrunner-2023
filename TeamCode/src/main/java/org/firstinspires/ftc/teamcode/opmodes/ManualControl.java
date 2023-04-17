package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Twist2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.MecanumDrive;

@Config
@TeleOp(name = "Manual Control", group = "drive")
public class ManualControl extends LinearOpMode {

    public static double MOTOR_POWER = 0.9;

    public boolean DRIVE_MODE = false;
    public boolean ONE_PERSON_MODE = false;

    public static double LIFT_POWER = 0.7;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(
                telemetry,
                FtcDashboard.getInstance().getTelemetry());

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));

        telemetry.addLine("Press play to start Driver Controlled");
        telemetry.update();

        waitForStart();

        if (isStopRequested())
            return;

        telemetry.clearAll();
        telemetry.setDisplayFormat(Telemetry.DisplayFormat.HTML);

        while (opModeIsActive() && !isStopRequested()) {
            // Log the current sticks positions to the telemetry
            telemetry.addData("Left Stick X", gamepad1.left_stick_x);
            telemetry.addData("Left Stick Y", gamepad1.left_stick_y);
            telemetry.addData("Right Stick X", gamepad1.right_stick_x);
            telemetry.addData("Right Stick Y", gamepad1.right_stick_y);

            drive.setDrivePowers(
                    new Twist2d(
                    new Vector2d(
                            smooth(gamepad1.left_stick_x),
                            smooth(gamepad1.left_stick_y)
                         ), new Double(smooth(gamepad1.right_stick_x)
                    )
            ));

            // Update the telemetry
            telemetry.addData(
                    "Drive Mode",
                    DRIVE_MODE ? "Field Centric" : "Robot Centric");
            telemetry.addData("Operation Mode", ONE_PERSON_MODE ? "One Person" : "Two Person");
            telemetry.update();
        }
    }

    private double smooth(double input) {
        double minInputValue = 0.05;

        input = Math.abs(input) > minInputValue ? input : 0;
        return Math.copySign(Math.pow(input, 2), input);
    }

}
