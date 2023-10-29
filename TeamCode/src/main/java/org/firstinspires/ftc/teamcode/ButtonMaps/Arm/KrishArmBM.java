package org.firstinspires.ftc.teamcode.ButtonMaps.Arm;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ComplexRobots.CenterStageRobot;

@Config
public class KrishArmBM extends AbstractButtonMap {
    public static double linearSlidesDownMultiplier = 0.25;
    public static double linearSlidesUpMultiplier = 0.8;
    public static double holdModePower = 0.07;

    private boolean holdMode = false;
    private ElapsedTime et = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private double lsToggleTime = 0;

    @Override
    public void loop(CenterStageRobot robot, OpMode opMode) {
        //Linear Slides (on triggers)
        //Copy+Paste from last year lol
        if (opMode.gamepad2.right_trigger > 0.1) {
            if (robot.linearSlidesMotor1.getCurrentPosition() < -5 || robot.linearSlidesMotor2.getCurrentPosition() < -5) {
                opMode.telemetry.addData("LS Direction", "INHIBIT DOWN");
                robot.linearSlidesMotor1.setPower(0);
                robot.linearSlidesMotor2.setPower(0);
            } else {
                opMode.telemetry.addData("LS Direction", "DOWN");
                robot.linearSlidesMotor1.setPower(-linearSlidesDownMultiplier* opMode.gamepad2.right_trigger);
                robot.linearSlidesMotor2.setPower(-linearSlidesDownMultiplier * opMode.gamepad2.right_trigger);
            }
        } else if (opMode.gamepad2.left_trigger > 0.1) {
            opMode.telemetry.addData("LS Direction", "UP");
            robot.linearSlidesMotor1.setPower(linearSlidesUpMultiplier * opMode.gamepad2.left_trigger);
            robot.linearSlidesMotor2.setPower(linearSlidesUpMultiplier * opMode.gamepad2.left_trigger);
        } else {
            opMode.telemetry.addData("LS Direction", "OFF");
            if (holdMode) {
                //Small amount of power for hold mode
                robot.linearSlidesMotor1.setPower(holdModePower);
                robot.linearSlidesMotor2.setPower(holdModePower);
            } else {
                robot.linearSlidesMotor1.setPower(0);
                robot.linearSlidesMotor2.setPower(0);
            }
        }
        //Linear Slide Hold Mode
        if (opMode.gamepad2.left_bumper && et.time()-lsToggleTime > 500) {
            holdMode = !holdMode;
            lsToggleTime = et.time();
        }
        opMode.telemetry.addData("LS Hold Mode", holdMode);
        opMode.telemetry.update();
    }
}
