package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ButtonMaps.Arm.THEArmButtonMap;
import org.firstinspires.ftc.teamcode.ButtonMaps.Drive.NoamAndrewDriveBM;
import org.firstinspires.ftc.teamcode.ComplexRobots.CenterStageRobot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="THE TeleOp: Noam/Andrew Drive, THE Arm")
public class TeleOp extends OpMode {
    //Global Variables
    CenterStageRobot robot;

    //Button Maps
    AbstractButtonMap buttonMap;
    AbstractButtonMap slidesButtonMap;
    @Override
    public void init() {
        telemetry.addLine("Initializing, please wait...");
        telemetry.update();
        robot = new CenterStageRobot(hardwareMap, new Pose2d(0,0,0), this);
        buttonMap = new NoamAndrewDriveBM();
        slidesButtonMap = new THEArmButtonMap();
        telemetry.addLine("Ready.");
        telemetry.update();
    }

    @Override
    public void loop() {
        buttonMap.loop(robot, this);
        slidesButtonMap.loop(robot, this);
        telemetry.update();
    }
}
