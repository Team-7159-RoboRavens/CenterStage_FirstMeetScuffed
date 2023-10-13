package org.firstinspires.ftc.teamcode.ButtonMaps;

public class MotorPowers {
    public double leftFront, rightFront, leftBack, rightBack;

    public MotorPowers(double leftFront, double rightFront, double leftBack, double rightBack){
        this.leftFront = leftFront;
        this.rightFront = rightFront;
        this.leftBack = leftBack;
        this.rightBack = rightBack;
    }

    public void setMotorPowers(double leftFront, double rightFront, double leftBack, double rightBack){
        this.leftFront = leftFront;
        this.rightFront = rightFront;
        this.leftBack = leftBack;
        this.rightBack = rightBack;
    }
}

