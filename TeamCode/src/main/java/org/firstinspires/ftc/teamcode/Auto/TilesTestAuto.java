package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ComplexRobots.CenterStageRobot;

@Autonomous(name="test auto")
public class TilesTestAuto extends LinearOpMode {
    CenterStageRobot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addLine("Initializing, please wait...");
        telemetry.update();
        robot = new CenterStageRobot(hardwareMap, new Pose2d(0,0,0), this);
        telemetry.addLine("Ready.");
        telemetry.update();
        waitForStart();

        robot.slowStartSlowStop(0, 0.5, 1, this);
        telemetry.addData("ticks", robot.rightBack.getCurrentPosition());
        telemetry.update();
        sleep(5000);
    }
}
