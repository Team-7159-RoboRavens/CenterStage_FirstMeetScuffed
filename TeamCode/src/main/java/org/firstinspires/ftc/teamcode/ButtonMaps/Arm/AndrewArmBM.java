package org.firstinspires.ftc.teamcode.ButtonMaps.Arm;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ComplexRobots.CenterStageRobot;

@Config
public class AndrewArmBM extends AbstractButtonMap {
    private double power = 0.5;

    //TODO: Magic Numbers!!!
    private double holdPower = 0.07;
    @Override
    public void loop(CenterStageRobot robot, OpMode opMode) {
        /*
            Button Map
            Y - Extend the Linear Slides
            A - Detract the Linear Slides
            B - Release Pixel(Maybe recoil as well
            D-Pad - Release airplane (single press)
            Right Bumper - Intake inward (takes in the pixel) (hold)
            Left Bumper - Intake outward (spits out the pixel, if in case we need to) (hold)
         */

        /* Linear Slides */
        if (opMode.gamepad2.y) {
            if (robot.linearSlidesMotor1.getCurrentPosition() < -5 || robot.linearSlidesMotor2.getCurrentPosition() < -5) {
                opMode.telemetry.addData("LS Direction", "INHIBIT DOWN");
                robot.linearSlidesMotor1.setPower(0);
                robot.linearSlidesMotor2.setPower(0);
            } else {
                opMode.telemetry.addData("LS Direction", "DOWN");
                robot.linearSlidesMotor1.setPower(-opMode.gamepad2.right_trigger);
                robot.linearSlidesMotor2.setPower(-opMode.gamepad2.right_trigger);
            }
        } else if (opMode.gamepad2.a) {
            opMode.telemetry.addData("LS Direction", "UP");
            robot.linearSlidesMotor1.setPower(opMode.gamepad2.left_trigger);
            robot.linearSlidesMotor2.setPower(opMode.gamepad2.left_trigger);
        } else {
            opMode.telemetry.addData("LS Direction", "OFF");
            //Small amount of power for hold mode
            robot.linearSlidesMotor1.setPower(holdPower);
            robot.linearSlidesMotor2.setPower(holdPower);
        }


        /* Release Pixel */
        if(opMode.gamepad2.b){
            
        }
    }
}
