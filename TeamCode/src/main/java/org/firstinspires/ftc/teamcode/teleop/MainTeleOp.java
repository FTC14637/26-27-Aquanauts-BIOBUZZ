package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Launcher;

@TeleOp(name = "TelOpV1", group = "Aquanauts")
public class MainTeleOp extends LinearOpMode {

    private double slowModeMultiplier = 0.25; // Multiplier for slow mode speed
    private boolean slowMode = false;
    private boolean invertMode = false;

    //IMPORT HARDWARE
    Hardware hardware = new Hardware();

    //IMPORT LAUNCHER
    Launcher launcher;
    Intake intake;


    public void runOpMode() {

        //DEFINE HARDWARE NAME
        hardware.init(hardwareMap);
        launcher = new Launcher(hardware);
        intake = new Intake(hardware);

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

            launcher.runLauncher();


            // Left bumper enables slow mode (DRIVER)
            if (gamepad1.rightBumperWasPressed()) {
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

            /*
            // Right trigger sets a servo power (OPERATOR)
            if(gamepad2.right_trigger_pressed) {
                hardware.crServo.setPower(100);
            }

             */

            if(gamepad2.right_bumper) {
                launcher.shoot();
            }

            if(gamepad2.left_trigger_pressed) {
                intake.intake();
            }

        }
    }
}


