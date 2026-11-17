package org.firstinspires.ftc.teamcode.teleop;

import com.pedropathing.follower.ManualDrive;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Launcher;
import org.firstinspires.ftc.teamcode.robot.CustomOpMode;

@Disabled
@TeleOp(name = "TeleOp", group = "Advanced")
public class NewTeleOp extends CustomOpMode {

    private double slowModeMultiplier = 0.25; // Multiplier for slow mode speed
    private boolean slowMode = false;
    private boolean invertMode = false;

    @Override
    public void init() {
        super.init();
        robot.drive.setStartPose(robot.drive.poseStorage);
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        super.loop();

        ManualDrive.driveOrHold(
                robot.drive.follower,
                (invertMode ? 1 : -1) *  (slowMode ? slowModeMultiplier : 1) * gamepad1.left_stick_y,
                (slowMode ? slowModeMultiplier : 1) * gamepad1.left_stick_x,
                (slowMode ? slowModeMultiplier : 1) * gamepad1.right_stick_x
        );






        telemetry.addData("Invert Mode: ", invertMode);
        telemetry.addData("Slow Mode: ", slowMode);
        telemetry.addData("Intake: ", robot.intake.state);
        telemetry.addData("Launcher: ", robot.launcher.state);
    }
}