package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.Launcher;

@TeleOp(name = "TelOpV1", group = "Aquanauts")
public class MainTeleOp extends LinearOpMode {

    private double slowModeMultiplier = 0.25; // Multiplier for slow mode speed
    private boolean slowMode = false;
    private boolean invertMode = false;

    //IMPORT HARDWARE
    MainTeleOpHardware hardware = new MainTeleOpHardware();

    //IMPORT LAUNCHER
    Launcher launcher = new Launcher();


    public void runOpMode() {

        //DEFINE HARDWARE NAME
        hardware.init(hardwareMap);

        telemetry.addLine("Initialized, waiting for start...");
        telemetry.update();

        waitForStart();
        if(isStopRequested()) return; // Stops if stopped is pressed

        // Controls
        while(opModeIsActive()) {

            double y = (invertMode ? 1 : -1) * gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;

            y = y * (slowMode ? slowModeMultiplier : 1.0);
            x = x * (slowMode ? slowModeMultiplier : 1.0);
            rx = rx * (slowMode ? slowModeMultiplier : 1.0);

            double frontLeftPower = y + x + rx;
            double backLeftPower = y - x + rx;
            double frontRightPower = y - x - rx;
            double backRightPower = y + x - rx;

            hardware.frontLeft.setPower(frontLeftPower);
            hardware.backLeft.setPower(backLeftPower);
            hardware.frontRight.setPower(frontRightPower);
            hardware.backRight.setPower(backRightPower);

            telemetry.addData("Invert mode", invertMode);
            telemetry.addData("Slow Mode", slowMode);
            telemetry.addData("Slow Mode Multiplier", slowModeMultiplier);
            telemetry.update();


            // Left bumper enables slow mode (DRIVER)
            if (gamepad1.leftBumperWasPressed()) {
                slowMode = !slowMode;
            }

            // Right Bumper Invert (DRIVER)
            if (gamepad1.leftBumperWasPressed()) {
                invertMode = !invertMode;
            }

            // D pad Up: Higher slow mode speed (DRIVER)
            if (gamepad1.dpadUpWasPressed()) {
                slowModeMultiplier += 0.25;
                if (slowModeMultiplier > 1) {
                    slowModeMultiplier = 1;
                }
            }


            // D Pad Down: Lower slow mode speed (DRIVER)
            if (gamepad1.dpadDownWasPressed()) {
                slowModeMultiplier -= 0.25;
                if (slowModeMultiplier < 0.25) {
                    slowModeMultiplier = 0.25;
                }
            }

            // Right trigger sets a servo power (OPERATOR)
            if(gamepad2.right_trigger_pressed) {
                hardware.crServo.setPower(100);
            }

            if(gamepad2.right_trigger_pressed) {
                launcher.shoot();
            }

        }
    }
}


