package org.firstinspires.ftc.teamcode.ButtonMaps;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ComplexRobots.CenterStageRobot;

public class KrishDriveBM extends AbstractButtonMap{

    private final double fastStrafePower = 0.8;
    private final double mediumStrafePower = 0.5;
    private final double slowStrafePower = 0.2;

    private boolean buttonPressed = false;
    private boolean motorBrake = false;

    private double currentStrafeMotorPower;
    private MotorPowers mp;

    @Override
    public void loop(CenterStageRobot robot, OpMode opMode) {

        //Speed Settings
        if(opMode.gamepad1.left_bumper){
            currentStrafeMotorPower = slowStrafePower;
        }else if(opMode.gamepad1.right_bumper){
            currentStrafeMotorPower = fastStrafePower;
        }else{
            currentStrafeMotorPower = mediumStrafePower;
        }

        //DPads OctoStrafe and Trigger Arc Turn, 2nd priority
        MotorPowers dpadMotorPowers = DPadControl.dpadStrafe(opMode.gamepad1, currentStrafeMotorPower);
        MotorPowers triggerMotorPowers = new MotorPowers(0);
        if(opMode.gamepad1.right_trigger > 0.1){
            //Forward
            triggerMotorPowers = new MotorPowers(0, opMode.gamepad1.right_trigger*currentStrafeMotorPower, 0, opMode.gamepad1.right_trigger*currentStrafeMotorPower);
        }else if(opMode.gamepad1.left_trigger > 0.1){
            //Backward
            triggerMotorPowers = new MotorPowers(opMode.gamepad1.left_trigger*currentStrafeMotorPower, 0, opMode.gamepad1.left_trigger*currentStrafeMotorPower, 0);
        }
        if(dpadMotorPowers.isNotZero() || triggerMotorPowers.isNotZero()){
//            buttonPressed = true;
            mp = dpadMotorPowers;
            mp.combineWith(triggerMotorPowers);
        }

        //Field Oriented Drive (joysticks), 1st priority
        MotorPowers fodMotorPowers = FieldOrientedDrive.fieldOrientedDrive(opMode.gamepad1, robot.imu, currentStrafeMotorPower);
        if(fodMotorPowers.isNotZero()){
//            buttonPressed = true;
            mp = fodMotorPowers;
        }

//        if(!buttonPressed){
//            mp = new MotorPowers(0);
//        }
        robot.setMotorPowers(mp);
    }
}
