package org.firstinspires.ftc.teamcode.tuning;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ComplexRobots.CenterStageRobot;

@TeleOp
public class ArmTuner extends OpMode {
    CenterStageRobot robot;
    ElapsedTime et;
    double servoTime;
    @Override
    public void init() {
        robot = new CenterStageRobot(hardwareMap, new Pose2d(0,0,0), this);
        et = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        servoTime = 0;
        telemetry.addLine("redeee");
        telemetry.update();
    }

    @Override
    public void loop() {
        if(et.time()-servoTime > 100){
            if(gamepad1.a){
                robot.clawServo.setPosition(robot.clawServo.getPosition() + 0.05);
                servoTime = et.time();
            }else if(gamepad1.b){
                robot.clawServo.setPosition(robot.clawServo.getPosition() - 0.05);
                servoTime = et.time();
            }/*else{
                robot.outputServo.setPower(-1);
            }*/
            if(gamepad1.x){
                robot.tiltServo.setPosition(robot.tiltServo.getPosition()+0.05);
                servoTime = et.time();
            } else if (gamepad1.y) {
                robot.tiltServo.setPosition(robot.tiltServo.getPosition()-0.05);
                servoTime = et.time();
            }
        }
//        if(gamepad1.left_stick_y > 0.1 || gamepad1.left_stick_y < -0.1){
//            robot.armMotor.setPower(-gamepad1.left_stick_y*0.8);
//        }else{
//            robot.armMotor.setPower(0);
//        }
//        telemetry.addData("Intake Power", -gamepad1.left_stick_y);
        telemetry.addData("Output Servo Pos", robot.tiltServo.getPosition());
        telemetry.addData("Airplane Servo Pos", robot.airplaneServo.getPosition());
        telemetry.update();
    }
}
