package org.firstinspires.ftc.teamcode.ButtonMaps;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ComplexRobots.CenterStageRobot;

public abstract class AbstractButtonMap {

    /**
     * Called once per TeleOp loop.
     * RESTRICTIONS: No loops, delay statements, and telemetry.update().
     * Only use ONE gamepad: gamepad1 for drivetrain, gamepad2 for arm/claw/etc.
     * @param robot The CenterStageRobot instance for that TeleOp
     * @param opMode Literally "this" - The TeleOp instance
     */
    public abstract void loop(CenterStageRobot robot, OpMode opMode);
}
