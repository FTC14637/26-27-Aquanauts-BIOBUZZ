package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Launcher;
import org.firstinspires.ftc.teamcode.robot.CustomOpMode;

@TeleOp(name = "TelOpV1", group = "Aquanauts")
@Disabled
public class MainTeleOp extends CustomOpMode {

    private double slowModeMultiplier = 0.25; // Multiplier for slow mode speed
    private boolean slowMode = false;
    private boolean invertMode = false;

    //IMPORT HARDWARE
    Hardware hardware = new Hardware();

    //IMPORT LAUNCHER
    Launcher launcher;
    Intake intake;

    @Override
    public void init() {
        super.init();

        hardware.init(hardwareMap);
        launcher = new Launcher(hardware);

        telemetry.addLine("Initialized, waiting for start...");
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        super.loop();

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

        telemetry.addData("Invert Mode: ", invertMode);
        telemetry.addData("Slow Mode: ", slowMode);
    }
}


