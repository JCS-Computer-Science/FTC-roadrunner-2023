package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import org.firstinspires.ftc.teamcode.ActionOpMode;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Steps to push code / connect to robot:
 * 1. Turn robot on
 * 2. Wait for control hub to go green
 * 3. Connect to the REV hub wifi (16871-RC)
 * 4. Open a terminal
 * 5. type 'adb connect 192.168.43.1:5555'
 * 6. Once the green U-turn box arrow on the right hand side appears click it to push new code
 * */

@Config
@Autonomous(name="Box Drive", group="Autonomous")
public class BoxDrive extends ActionOpMode {

	private static double FORWARD_DISTANCE = 10; // in
	private static double STRAFE_DISTANCE = 10; // in

	@Override
	public void runOpMode() throws InterruptedException {
		MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
		waitForStart();

		runBlocking(
				drive.actionBuilder(drive.pose)
						// forward - backward
						.splineToConstantHeading(new Vector2d(FORWARD_DISTANCE, 0), 0)
						.setTangent(Math.PI)
						.splineToConstantHeading(new Vector2d(0, 0), 0)

						// strafe left
						.setTangent(Math.PI/2)
						.splineToConstantHeading(new Vector2d(0, STRAFE_DISTANCE), 0)

						// forward - backward
						.setTangent(0)
						.splineToConstantHeading(new Vector2d(FORWARD_DISTANCE, STRAFE_DISTANCE), 0)
						.setTangent(Math.PI)
						.splineToConstantHeading(new Vector2d(0, STRAFE_DISTANCE), 0)

						// strafe right
						.setTangent(-Math.PI/2)
						.splineToConstantHeading(new Vector2d(0, -STRAFE_DISTANCE), 0)

						// forward - backward
						.setTangent(0)
						.splineToConstantHeading(new Vector2d(FORWARD_DISTANCE, -STRAFE_DISTANCE), 0)
						.setTangent(Math.PI)
						.splineToConstantHeading(new Vector2d(0, -STRAFE_DISTANCE), 0)
						.build()
		);
	}
}
