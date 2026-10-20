package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Launcher;
import org.firstinspires.ftc.teamcode.robot.CustomOpMode;

@TeleOp(name = "TelOpV1", group = "Aquanauts")

public class MainTeleOp extends CustomOpMode {

    Hardware hardware = new Hardware();

    double slowModeMultiplier = 0.25; // Multiplier for slow mode speed
    boolean slowMode = false;
    boolean invertMode = false;


    @Override
    public void init() {
        super.init();

        hardware.init(hardwareMap);

        telemetry.addLine("Initialized, waiting for start...");
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        super.loop();

        double y = (invertMode? 1 : -1)*gamepad1.left_stick_y;
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

        telemetry.addData("Invert Mode: ", invertMode);
        telemetry.addData("Slow Mode: ", slowMode);
        telemetry.addData("Intake: ", robot.intake.state);
        telemetry.addData("Launcher: ", robot.launcher.state);
        telemetry.addData("FLP: ", frontLeftPower);
        telemetry.addData("BLP: ", backLeftPower);
        telemetry.addData("FRP: ", frontRightPower);
        telemetry.addData("BRP: ,", backRightPower);

        if (gamepad2.leftBumperWasPressed() && robot.launcher.state == Launcher.State.IDLE) {
            robot.launcher.on();
            //launcher on
        }

        if (gamepad2.leftTriggerWasPressed() && robot.launcher.state == Launcher.State.READY) {
            //launcher lauch
            robot.launcher.launch();
        }

        if (gamepad1.bWasPressed()) {
            //extend
            robot.wiper.extend().schedule();
        }

        if (gamepad1.xWasPressed()) {
            //retract wiper
            robot.wiper.retract().schedule();
        }

        if (gamepad1.rightTriggerWasPressed()) {
            //slow mode
            slowMode = !slowMode;
        }

        if (gamepad1.aWasPressed()) {
            //invert mode
            invertMode =!invertMode;
        }

        if (gamepad2.rightBumperWasPressed()) {
            //intake toggle
            if(robot.intake.state == Intake.State.OFF) {
                robot.intake.on().schedule();
            } else if (robot.intake.state ==  Intake.State.ON || robot.intake.state == Intake.State.REVERSE) {
                robot.intake.off().schedule();
            }
        }

        if (gamepad2.yWasPressed() &&  (robot.intake.state == Intake.State.OFF || robot.intake.state == Intake.State.ON)) {
            //reverse intake
            robot.intake.reverse().schedule();
        }
    }
}


