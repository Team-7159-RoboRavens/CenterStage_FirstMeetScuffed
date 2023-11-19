package org.firstinspires.ftc.teamcode.ButtonMaps.Arm;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ComplexRobots.CenterStageRobot;

@Config
public class THEArmButtonMap extends AbstractButtonMap {
    public static double armMotorMultiplier = 0.5;

    private ElapsedTime et = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private double servoTime = 0;

    @Override
    public void loop(CenterStageRobot robot, OpMode opMode) {
        if(opMode.gamepad2.dpad_up && et.time() - servoTime >= 50){
            robot.tiltServo.setPosition(robot.tiltServo.getPosition() + 0.05);
            servoTime = et.time();
        }else if(opMode.gamepad1.dpad_down && et.time() - servoTime >= 50){
            robot.tiltServo.setPosition(robot.tiltServo.getPosition() - 0.05);
            servoTime = et.time();
        }

        //TODO: find 0/1 open/close
        if(opMode.gamepad2.a){
            //Close/Grab
            robot.clawServo.setPosition(0);
        }else if(opMode.gamepad2.b){
            //Open/Release
            robot.clawServo.setPosition(1);
        }

        //Raise/Lower Arm
        if(opMode.gamepad2.left_trigger > 0.1){
            //Raise
            robot.armMotor.setPower(opMode.gamepad2.left_trigger * armMotorMultiplier);
        }else if(opMode.gamepad2.left_trigger > 0.1){
            //Lower
            robot.armMotor.setPower(-opMode.gamepad2.left_trigger * armMotorMultiplier);
        }else{
            //Stop
            robot.armMotor.setPower(0);
        }
    }
}
