package org.firstinspires.ftc.teamcode.ComplexRobots;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.BasicRobots.MecanumDrive;

public class CenterStageRobot extends MecanumDrive {
    //Motors
    public final DcMotorEx armMotor;

    //Sensors


    public CenterStageRobot(HardwareMap hardwareMap, Pose2d pose, OpMode opMode) {
        super(hardwareMap, pose, opMode);
        armMotor = null;
    }


}
