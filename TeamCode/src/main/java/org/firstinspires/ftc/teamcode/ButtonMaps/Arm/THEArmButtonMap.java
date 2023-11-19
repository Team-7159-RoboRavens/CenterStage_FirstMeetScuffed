package org.firstinspires.ftc.teamcode.ButtonMaps.Arm;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ComplexRobots.CenterStageRobot;

@Config
public class THEArmButtonMap extends AbstractButtonMap {
    public static double armMotorMultiplier = 0.07;
    public static double holdModePower = 0.001;

    private ElapsedTime et = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private double servoTime = 0;
    private boolean holdMode = false;
    private double holdModeToggleTime = 0;
    //0 = idle, -1 = closing, 1 = opening
    private int servoTransit = 0;
    private double servoStepTime = 0;

    @Override
    public void loop(CenterStageRobot robot, OpMode opMode) {
        if(opMode.gamepad2.dpad_up && et.time() - servoTime >= 50){
            robot.tiltServo.setPosition(robot.tiltServo.getPosition() + 0.05);
            servoTime = et.time();
        }else if(opMode.gamepad2.dpad_down && et.time() - servoTime >= 50){
            robot.tiltServo.setPosition(robot.tiltServo.getPosition() - 0.05);
            servoTime = et.time();
        }

        //TODO: find 0/1 open/close
        if(opMode.gamepad2.a /*&& servoTransit != -1*/){
            //Close/Grab
//            servoTransit = -1;
            robot.clawServo.setPosition(0.5);
        }else if(opMode.gamepad2.b /* && servoTransit != 1 */){
            //Open/Release for INTAKE
//            servoTransit = 1;
            robot.clawServo.setPosition(0);
        }else if(opMode.gamepad2.x){
            //Open/Release for OUTTAKE
            robot.clawServo.setPosition(1);
        }
//        if(servoTransit == 1 && et.time() - servoStepTime >= 50){
//            //In Transit, step it open-ward
//            robot.tiltServo.setPosition(robot.tiltServo.getPosition() + 0.05);
//            if(robot.tiltServo.getPosition() >= 0.99){
//                servoTransit = 0;
//            }else{
//                servoStepTime = et.time();
//            }
//        }else if(servoTransit == -1 && et.time() - servoStepTime >= 50){
//            //In Transit, step it inner-ward
//            robot.tiltServo.setPosition(robot.tiltServo.getPosition() - 0.05);
//            if(robot.tiltServo.getPosition() <= 0.01){
//                servoTransit = 0;
//            }else{
//                servoStepTime = et.time();
//            }
//        }


        //Raise/Lower Arm
        if(opMode.gamepad2.left_trigger > 0.1){
            //Raise
            robot.armMotor.setPower(opMode.gamepad2.left_trigger * armMotorMultiplier);
        }else if(opMode.gamepad2.right_trigger > 0.1){
            //Lower
            robot.armMotor.setPower(-opMode.gamepad2.right_trigger * armMotorMultiplier);
        }else{
            //Stop
            if(holdMode){
                robot.armMotor.setPower(holdModePower);
            }else{
                robot.armMotor.setPower(0);
            }
        }
        //Toggle Hold Mode (300ms cooldown)
        if(opMode.gamepad2.left_bumper && et.time() - holdModeToggleTime >= 300){
            holdMode = !holdMode;
            holdModeToggleTime = et.time();
        }

        //Airplane
        if(opMode.gamepad2.back){
            robot.airplaneServo.setPosition(0);
        }
    }
}
