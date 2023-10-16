package org.firstinspires.ftc.teamcode.ButtonMaps;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.ComplexRobots.CenterStageRobot;

public class EitanNadavDriveBM extends AbstractButtonMap{

    private final double triggerMultipler = 1;
    private boolean buttonPressed = false;

    private MotorPowers mp;

    @Override
    public void loop(CenterStageRobot robot, OpMode opMode) {
        buttonPressed = false;

        //Field Oriented Drive (joysticks), 3rd priority
        MotorPowers fodMotorPowers = FieldOrientedDrive.fieldOrientedDrive(opMode.gamepad1, robot.imu, 0.8);
        if(fodMotorPowers.isNotZero()){
            buttonPressed = true;
            mp = fodMotorPowers;
        }

        //Pivot turn is combined with triggers, 2nd priority
        MotorPowers pivotTurnMotorPowers = robot.pivotTurn(0.7, opMode.gamepad1.right_bumper, opMode.gamepad1.left_bumper);
        MotorPowers triggerMotorPowers = new MotorPowers(0);
        if(opMode.gamepad1.right_trigger > 0.1){
            triggerMotorPowers = new MotorPowers(opMode.gamepad1.right_trigger*triggerMultipler);
        }else if(opMode.gamepad1.left_trigger > 0.1){
            triggerMotorPowers = new MotorPowers(opMode.gamepad1.right_trigger*triggerMultipler);
        }
        if(triggerMotorPowers.isNotZero() || pivotTurnMotorPowers.isNotZero()){
            buttonPressed = true;
            triggerMotorPowers.combineWith(pivotTurnMotorPowers);
            mp = triggerMotorPowers;
        }

        //DPads OctoStrafe, 1st priority
        MotorPowers dpadMotorPowers = DPadControl.dpadStrafe(opMode.gamepad1, 0.7);
        if(dpadMotorPowers.isNotZero()){
            buttonPressed = true;
            mp = dpadMotorPowers;
        }
        
        if(!buttonPressed){
            mp = new MotorPowers(0);
        }
        robot.setMotorPowers(mp);
    }
}
