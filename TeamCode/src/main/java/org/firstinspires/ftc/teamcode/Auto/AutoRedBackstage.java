package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ComplexRobots.CenterStageRobot;

@Autonomous(name="Red - Backstage")
public class AutoRedBackstage extends LinearOpMode {
    CenterStageRobot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addLine("Initializing, please wait...");
        telemetry.update();
        robot = new CenterStageRobot(hardwareMap, new Pose2d(0,0,0), this);
        telemetry.addLine("Ready.");
        telemetry.update();
        waitForStart();
        robot.slowStartSlowStop(1, 0.7, 1.5, this);
        sleep(500);
        robot.intakeMotor.setPower(-1);
        sleep(1000);
        robot.intakeMotor.setPower(0);
    }
}
