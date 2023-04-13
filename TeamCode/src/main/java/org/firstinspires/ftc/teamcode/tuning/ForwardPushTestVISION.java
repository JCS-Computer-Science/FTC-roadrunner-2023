package org.firstinspires.ftc.teamcode.tuning;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Time;
import com.acmerobotics.roadrunner.Twist2dIncrDual;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.util.Encoder;
import org.firstinspires.ftc.teamcode.vision.FieldNav;

import java.util.List;

@Config
public final class ForwardPushTestVISION extends LinearOpMode {

//    private final FtcDashboard dash = FtcDashboard.getInstance();

    private static double avgPos(List<? extends Encoder> es) {
        double avgPos = 0;
        for (Encoder e : es) {
            avgPos += e.getPositionAndVelocity().position;
        }
        return avgPos / es.size();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        //DriveView view = new DriveView(hardwareMap);
        FieldNav fieldNav = new FieldNav(hardwareMap);

//        TelemetryPacket p = new TelemetryPacket();
        fieldNav.Init();
//        for (DcMotorEx m : view.motors) {
//            m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//        }
        Pose2d initPose = fieldNav.GetTransform();

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0,0,0));
        drive.leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        drive.leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        drive.leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        drive.leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

//        p.put("current Pose", initPose);
//        dash.sendTelemetryPacket(p);

        telemetry.addData("current Pose", initPose);
        telemetry.update();

        waitForStart();



       // double initAvgPos = avgPos(view.forwardEncs);

        while (!isStopRequested()) {
//            double inTraveled = (avgPos(view.forwardEncs) - initAvgPos) * view.inPerTick;
            Pose2d currentPose = fieldNav.GetTransform();
            double visionXdiff = currentPose.minus(initPose).transIncr.x;
            double visionYdiff = currentPose.minus(initPose).transIncr.y;

            Twist2dIncrDual<Time> incr= drive.localizer.updateAndGetIncr();
            drive.pose = drive.pose.plus(incr.value());
            double encXdiff = drive.pose.minus(new Pose2d(0,0,0)).transIncr.x;
            double encYdiff = drive.pose.minus(new Pose2d(0,0,0)).transIncr.y;


            //calculate percent error
            double percentError = (encYdiff - visionYdiff) / visionYdiff;

//            double newInPerTick = view.inPerTick * (1 + percentError);

            telemetry.addData("Vision Y:", currentPose.trans.y);
            telemetry.addData("Vision X:", currentPose.trans.x);
            telemetry.addData("Vision Heading:",  Math.toDegrees(currentPose.rot.log()));
            telemetry.addData("Vision X Diff", visionXdiff);
            telemetry.addData("Vision Y Diff", visionYdiff);

            telemetry.addLine("");

            telemetry.addData("Enc Y:", drive.pose.trans.y);
            telemetry.addData("Enc X:", drive.pose.trans.x);
            telemetry.addData("Enc Heading:",  Math.toDegrees(drive.pose.rot.log()));
            telemetry.addData("Enc X Diff", encXdiff);
            telemetry.addData("Enc Y Diff", encYdiff);
            telemetry.addLine("");
            telemetry.addData("percent error", percentError);
            //telemetry.addData("new in per tick", newInPerTick);
            telemetry.update();
        }
        fieldNav.disableTracking();
    }

}
