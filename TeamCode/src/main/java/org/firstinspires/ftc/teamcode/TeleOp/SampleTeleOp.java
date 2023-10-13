package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TrajectoryBuilder;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ButtonMaps.SampleButtonMap;
import org.firstinspires.ftc.teamcode.ComplexRobots.CenterStageRobot;

//EXAMPLE TELEOP WITH NEW SYSTEM
//@TeleOp(name="Example")
public class SampleTeleOp extends OpMode {
    //Global Variables
    CenterStageRobot robot;

    //Button Maps
    AbstractButtonMap buttonMapArm;

    //Setup all global variables here
    @Override
    public void init() {
        robot = new CenterStageRobot(hardwareMap, new Pose2d(0,0,0), this);
        buttonMapArm = new SampleButtonMap(5.5);
    }

    //The only thing that should need to be done here is call the loop methods, then telemetry.update();
    @Override
    public void loop() {
        buttonMapArm.loop(robot, this);
        telemetry.update();
    }
}
