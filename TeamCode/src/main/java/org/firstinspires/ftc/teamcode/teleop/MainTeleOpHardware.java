package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MainTeleOpHardware {

    // Motors
    public DcMotorEx frontLeft, frontRight, backLeft, backRight, launcherFlyWheel;

    public CRServo crServo; //Servo testing

    // Initialize hardware
    public void init(HardwareMap hardwareMap) {

        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");

        launcherFlyWheel = hardwareMap.get(DcMotorEx.class, "launcherFlyWheel");

        crServo = hardwareMap.get(CRServo.class, "intake");

        //TODO set directions
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        launcherFlyWheel.setDirection(DcMotor.Direction.FORWARD);

        //Break behavior (BREAK, FLOAT)
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        launcherFlyWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        launcherFlyWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //TODO REMOVE
        crServo.setDirection(CRServo.Direction.FORWARD);
    }
}
