package org.firstinspires.ftc.teamcode.ButtonMaps.Arm;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ComplexRobots.CenterStageRobot;

public class ArianaArmBm extends AbstractButtonMap {
    public static double slideUpPower = 0.45;
    public static double slideDownPower = 0.25;
    public static double intakePower = 0.5;
    public static double linearSlidesDownMultiplier = 0.25;
    public static double linearSlidesUpMultiplier = 0.65;
    private ElapsedTime et = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private double intakeOutTime = 0;
    private boolean intakeOut = false;
    @Override
    public void loop(CenterStageRobot robot, OpMode opMode) {
        //Linear Slides (on triggers)
        //Copy+Paste from last year lol
        if (opMode.gamepad2.left_trigger > 0.1) {
            if (robot.linearSlidesMotor1.getCurrentPosition() < -5 || robot.linearSlidesMotor2.getCurrentPosition() < -5) {
                opMode.telemetry.addData("LS Direction", "INHIBIT DOWN");
                robot.linearSlidesMotor1.setPower(0);
                robot.linearSlidesMotor2.setPower(0);
            } else {
                opMode.telemetry.addData("LS Direction", "DOWN");
                robot.linearSlidesMotor1.setPower(-linearSlidesDownMultiplier* opMode.gamepad2.right_trigger);
                robot.linearSlidesMotor2.setPower(-linearSlidesDownMultiplier * opMode.gamepad2.right_trigger);
            }
        } else if (opMode.gamepad2.right_trigger > 0.1) {
            opMode.telemetry.addData("LS Direction", "UP");
            robot.linearSlidesMotor1.setPower(linearSlidesUpMultiplier * opMode.gamepad2.left_trigger);
            robot.linearSlidesMotor2.setPower(linearSlidesUpMultiplier * opMode.gamepad2.left_trigger);
        } else {
            opMode.telemetry.addData("LS Direction", "OFF");
            robot.linearSlidesMotor1.setPower(0);
            robot.linearSlidesMotor2.setPower(0);
        }
        if (opMode.gamepad2.right_bumper) {
             robot.outputServo.setPosition(1);
             intakeOut = true;
             intakeOutTime = et.time();
        }
        if (intakeOut && et.time()-intakeOutTime > 500) {
            robot.outputServo.setPosition(0);
            intakeOut = false;
        }
        if (opMode.gamepad2.b) {
            robot.intakeMotor.setPower(intakePower);
        } else if (opMode.gamepad2.x) {
            robot.intakeMotor.setPower(-intakePower);
        }
        if (opMode.gamepad2.y) {
            robot.airplaneServo.setPosition(0);
        }
    }
}
