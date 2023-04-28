package org.firstinspires.ftc.teamcode.tuning;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;

import org.firstinspires.ftc.teamcode.ActionOpMode;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.TankDrive;

public final class ManualFeedbackTuner extends ActionOpMode {
	public static double DISTANCE = 32;

	@Override
	public void runOpMode() throws InterruptedException {
		if (TuningOpModes.DRIVE_CLASS.equals(MecanumDrive.class)) {
			MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));

			waitForStart();

			while (opModeIsActive() && !isStopRequested()) {
//                runBlocking(
//                    drive.actionBuilder(drive.pose)
//                            .lineToX(DISTANCE)
//                            .lineToX(0)
//                            .build());
				runBlocking(
						drive.actionBuilder(drive.pose)
								.splineTo(new Vector2d(DISTANCE, 0), 0)
								.setReversed(true)
								.splineTo(new Vector2d(0, 0), Math.PI)
								.build());
//                runBlocking(
//                        drive.actionBuilder(drive.pose)
//                                .splineTo(new Vector2d(DISTANCE,0),0)
//                                .turn(-Math.PI/2)
//                                .setTangent(-Math.PI)
//                                .splineToConstantHeading(new Vector2d(0,0),-Math.PI)
//                                .turn(Math.PI/2)
//                                .build()
//                );
			}
		} else if (TuningOpModes.DRIVE_CLASS.equals(TankDrive.class)) {
			TankDrive drive = new TankDrive(hardwareMap, new Pose2d(0, 0, 0));

			waitForStart();

			while (opModeIsActive()) {
				runBlocking(
						drive.actionBuilder(drive.pose)
								.lineToX(DISTANCE)
								.lineToX(0)
								.build());
			}
		} else {
			throw new AssertionError();
		}
	}
}
