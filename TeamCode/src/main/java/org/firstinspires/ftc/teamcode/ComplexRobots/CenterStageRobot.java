package org.firstinspires.ftc.teamcode.ComplexRobots;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.BasicRobots.MecanumDrive;

@Config
public class CenterStageRobot extends MecanumDrive {
    //Motors
//    public final DcMotorEx linearSlidesMotor1;
//    public final DcMotorEx linearSlidesMotor2;

    public final DcMotorEx armMotor;

    public final Servo clawServo;
    public final Servo airplaneServo;
    public final Servo tiltServo;

    //Sensors

    //Constructor
    public CenterStageRobot(HardwareMap hardwareMap, Pose2d pose, OpMode opMode) {
        super(hardwareMap, pose, opMode);

//        //Linear Slide Motors
//        linearSlidesMotor1 = hardwareMap.get(DcMotorEx.class, "linearSlidesMotor1");
//        linearSlidesMotor2 = hardwareMap.get(DcMotorEx.class, "linearSlidesMotor2");
//        //Setup
//        linearSlidesMotor1.setDirection(DcMotor.Direction.REVERSE);
//        linearSlidesMotor2.setDirection(DcMotor.Direction.FORWARD);
//        linearSlidesMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        linearSlidesMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        linearSlidesMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        linearSlidesMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
////        linearSlidesMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
////        linearSlidesMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Initialize Claw Servo
        clawServo = hardwareMap.get(Servo.class, "clawServo");
        clawServo.scaleRange(0,1);
        //Force to be in the right place
        //TODO: find start number
        clawServo.setPosition(0);

        //Initialize Tilt Servo
        tiltServo = hardwareMap.get(Servo.class, "tiltServo");
        //TODO: find scale range numbers
        tiltServo.scaleRange(0,1);
        //Force to be in the right place
        //TODO: find start number
        tiltServo.setPosition(1);

        //Initialize Intake Motor
        armMotor = hardwareMap.get(DcMotorEx.class, "armMotor");
        //Setup
        armMotor.setDirection(DcMotor.Direction.FORWARD);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Initialize Airplane Servo
        airplaneServo = hardwareMap.get(Servo.class, "airplaneServo");
        //TODO: find numbers
        airplaneServo.scaleRange(0.65,0.82);
        //Force to be in the right place
        //TODO: find number
        airplaneServo.setPosition(1);


        //TODO: Linear slide helper methods for auto (later)
    }


}
