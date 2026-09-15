package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TelOpV1", group = "Aquanauts")
public class MainTeleOp extends LinearOpMode {

    private double slowModeMultiplier = 0.25; // Multiplier for slow mode speed
    private double nonSlowModeMultiplier = 1.0;
    private boolean slowMode = false;

//TODO REMOVE
    private int servoSpeed = 200; //Servo testing yeah yeah

    //IMPORT HARDWARE
    MainTeleOpHardware hardware = new MainTeleOpHardware();


    public void runOpMode() {

        //DEFINE HARDWARE NAME
        hardware.init(hardwareMap);

        telemetry.addLine("Initialized, waiting for start...");
        telemetry.update();

        waitForStart();
        if(isStopRequested()) return; // Stops if stopped is pressed

        // Controls
        while(opModeIsActive()) {

            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;

            y = y * (slowMode ? slowModeMultiplier : nonSlowModeMultiplier);
            x = x * (slowMode ? slowModeMultiplier : nonSlowModeMultiplier);
            rx = rx * (slowMode ? slowModeMultiplier : nonSlowModeMultiplier);

            double frontLeftPower = y + x + rx;
            double backLeftPower = y - x + rx;
            double frontRightPower = y - x - rx;
            double backRightPower = y + x - rx;

            hardware.frontLeft.setPower(frontLeftPower);
            hardware.backLeft.setPower(backLeftPower);
            hardware.frontRight.setPower(frontRightPower);
            hardware.backRight.setPower(backRightPower);

            telemetry.addData("Slow Mode Multiplier", slowModeMultiplier);
            telemetry.addData("Speed", servoSpeed);
            telemetry.update();


            // Right bumper enables slow mode
            if (gamepad1.rightBumperWasPressed()) {
                slowMode = !slowMode;
            }


            // X: Higher slow mode speed
            if (gamepad1.dpadUpWasPressed()) {
                slowModeMultiplier += 0.25;
                if (slowModeMultiplier > 1) {
                    slowModeMultiplier = 1;
                }
            }


            // D Pad Down: Lower slow mode speed
            if (gamepad1.dpadDownWasPressed()) {
                slowModeMultiplier -= 0.25;
                if (slowModeMultiplier < 0.25) {
                    slowModeMultiplier = 0.25;
                }
            }


            if(gamepad1.right_trigger_pressed) {
                hardware.crServo.setPower(servoSpeed);
            }


            if (gamepad1.leftBumperWasPressed()) {
                servoSpeed += 100;
            }
        }
    }
}


