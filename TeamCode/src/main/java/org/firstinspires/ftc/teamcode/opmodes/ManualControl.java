 package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Twist2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ActionOpMode;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Launcher;

@Config
@TeleOp(name = "Manual Control", group = "drive")
public class ManualControl extends ActionOpMode {

    public static double MOTOR_POWER = 0.9;
    private static Intake intake;
    private static Launcher launcher;
    private boolean launcherState = false;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(
                telemetry,
                FtcDashboard.getInstance().getTelemetry());

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));

        intake = new Intake(hardwareMap);
        launcher = new Launcher(hardwareMap);

        telemetry.addLine("Press play to start Driver Controlled");
        telemetry.update();

        waitForStart();

        if (isStopRequested())
            return;

        telemetry.clearAll();
        telemetry.setDisplayFormat(Telemetry.DisplayFormat.HTML);

        while (opModeIsActive() && !isStopRequested()) {
            telemetry.addData("Left Stick X", gamepad1.left_stick_x);
            telemetry.addData("Left Stick Y", gamepad1.left_stick_y);
            telemetry.addData("Right Stick X", gamepad1.right_stick_x);
            telemetry.addData("Right Stick Y", gamepad1.right_stick_y);

            drive.setDrivePowers(
                new Twist2d(
                    new Vector2d(
                            -smooth(gamepad1.left_stick_y)*MOTOR_POWER,
                            -smooth(gamepad1.left_stick_x)*MOTOR_POWER
                    ), -smooth(gamepad1.right_stick_x)*MOTOR_POWER
                )
            );
            if(!launcherState && gamepad1.a){
                runBlocking(new SequentialAction(
                        launcher.setIndexer(true),
                        new SleepAction(1000),
                        launcher.setIndexer(false),
                        launcher.setShooter(true),
                        new SleepAction(1000),
                        launcher.setShooter(false)
                ));
            }

            launcherState = gamepad1.a;

            //launcher.setState(gamepad1.a, gamepad1.b);

            intake.setPower(smooth(gamepad1.right_trigger) - smooth(gamepad1.left_trigger));

            telemetry.update();
        }
    }

    private double smooth(double input) {
        double minInputValue = 0.05;

        input = Math.abs(input) > minInputValue ? input : 0;
        return Math.copySign(Math.pow(input, 2), input);
    }

}
