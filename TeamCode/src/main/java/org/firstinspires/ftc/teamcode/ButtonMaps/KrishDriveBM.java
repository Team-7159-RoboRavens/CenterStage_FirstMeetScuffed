package org.firstinspires.ftc.teamcode.ButtonMaps;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ComplexRobots.CenterStageRobot;

public class KrishDriveBM extends AbstractButtonMap{

    private final double fastStrafePower = 1;
    private final double mediumStrafePower = 0.6;
    private final double slowStrafePower = 0.3;

    private boolean buttonPressed = false;
    private boolean motorBrake = false;

    private double currentStrafeMotorPower;
    private MotorPowers mp;

    @Override
    public void loop(CenterStageRobot robot, OpMode opMode) {

        if(opMode.gamepad1.left_bumper){
            currentStrafeMotorPower = slowStrafePower;
        }else if(opMode.gamepad1.right_bumper){
            currentStrafeMotorPower = fastStrafePower;
        }else{
            currentStrafeMotorPower = mediumStrafePower;
        }

        //DPads OctoStrafe and Arc Turn, 2nd priority
        MotorPowers dpadMotorPowers = DPadControl.dpadStrafe(opMode.gamepad1, currentStrafeMotorPower);
        MotorPowers triggerMotorPowers = new MotorPowers(0);
        if(opMode.gamepad1.right_trigger > 0.1){
            //Forward
            triggerMotorPowers = new MotorPowers();
        }else if(opMode.gamepad1.left_trigger > 0.1){
            //Backward
            triggerMotorPowers = new MotorPowers(-opMode.gamepad1.left_trigger*triggerMultipler);
        }
        if(dpadMotorPowers.isNotZero()){
//            buttonPressed = true;
            mp = dpadMotorPowers;
            mp.combineWith(pivotTurnMotorPowers);
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
