package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import org.firstinspires.ftc.teamcode.ActionOpMode;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="U Drive", group="Autonomous")
public class UDrive extends ActionOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        waitForStart();

        runBlocking(
            drive.actionBuilder(drive.pose)
                    .splineTo(new Vector2d(32,0),0)
                    .setTangent(-Math.PI/2)
                    .splineToConstantHeading(new Vector2d(32,32),-Math.PI)
                    .setTangent(-Math.PI)
                    .splineToConstantHeading(new Vector2d(0,32),-Math.PI)
                    .build()
        );
    }
}
