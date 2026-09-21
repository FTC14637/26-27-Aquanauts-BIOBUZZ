package org.firstinspires.ftc.teamcode.test;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mechanisms.Launcher;
import org.firstinspires.ftc.teamcode.teleop.MainTeleOpHardware;

@TeleOp(name = "LauncherTest", group = "Aquanauts")

public class LauncherTest extends LinearOpMode {

    private DcMotorEx launcherFlyWheel;

    //IMPORT LAUNCHER
    Launcher launcher = new Launcher();


    public void runOpMode() {

        launcherFlyWheel = hardwareMap.get(DcMotorEx.class, "launcherFlyWheel");
        launcherFlyWheel.setDirection(DcMotor.Direction.FORWARD);
        launcherFlyWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        launcherFlyWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addLine("Initialized, waiting for start...");
        telemetry.addData("Velocity:", launcherFlyWheel.getVelocity());
        telemetry.update();

        waitForStart();
        if (isStopRequested()) return; // Stops if stopped is pressed

        // Controls
        while (opModeIsActive()) {
            if (gamepad1.right_trigger_pressed) {
                launcher.shoot();
            }
        }
    }
}