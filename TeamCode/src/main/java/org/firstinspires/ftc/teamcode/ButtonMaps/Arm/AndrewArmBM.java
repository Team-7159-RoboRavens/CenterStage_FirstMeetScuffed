package org.firstinspires.ftc.teamcode.ButtonMaps.Arm;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ComplexRobots.CenterStageRobot;

@Config
public class AndrewArmBM extends AbstractButtonMap {
    public static double slideUpPower = 0.5;
    public static double slideDownPower = 0.35;
    public static double intakePower = 0.35;
    public static double holdPower = 0.04;
    public static double outputRetractTime = 1000;

    //TODO: Magic Numbers!!!
    private ElapsedTime et = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private double intakeOutTime = 0;
    private boolean intakeOut = false;

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
                robot.linearSlidesMotor1.setPower(-slideDownPower);
                robot.linearSlidesMotor2.setPower(-slideDownPower);
            }
        } else if (opMode.gamepad2.a) {
            opMode.telemetry.addData("LS Direction", "UP");
            robot.linearSlidesMotor1.setPower(slideUpPower);
            robot.linearSlidesMotor2.setPower(slideUpPower);
        } else {
            opMode.telemetry.addData("LS Direction", "OFF+HOLD");
            //Small amount of power for hold mode
            robot.linearSlidesMotor1.setPower(holdPower);
            robot.linearSlidesMotor2.setPower(holdPower);
        }

        /* Release Pixel */
        if(opMode.gamepad2.b){
            robot.outputServo.setPosition(0);
            intakeOut = true;
            intakeOutTime = et.time();
        }
        //Automatically retracts intake after 1000 ms
        if(intakeOut && et.time()-intakeOutTime > outputRetractTime){
            robot.outputServo.setPosition(1);
            intakeOut = false;
        }

        //Intake Inward/Forward (RB) + Outward/Reverse (LB)
        if(opMode.gamepad2.right_bumper){
            robot.intakeMotor.setPower(intakePower);
        }else if(opMode.gamepad2.left_bumper) {
            robot.intakeMotor.setPower(-intakePower);
        }else{
            robot.intakeMotor.setPower(0);
        }

        //Plane Servo (dpad)
        //TODO: find position
        if(opMode.gamepad2.dpad_up || opMode.gamepad2.dpad_down || opMode.gamepad2.dpad_left || opMode.gamepad2.dpad_right){
            robot.airplaneServo.setPosition(0);
        }
    }
}
