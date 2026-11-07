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


        /*
        if (gamepad2.leftBumperWasPressed() && robot.launcher.state == Launcher.State.IDLE) {
            robot.launcher.on();
            //launcher on
        }
         */

        if (gamepad2.leftTriggerWasPressed() && robot.launcher.state == Launcher.State.OFF) {
            //launcher on
            robot.launcher.on();
        }

        if (gamepad2.leftBumperWasPressed() && robot.launcher.state == Launcher.State.ON) {
            //launcher off
            robot.launcher.off();
        }

        if (gamepad1.bWasPressed()) {
            //extend
            robot.wiper.extend().schedule();
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

        telemetry.addData("Invert Mode: ", invertMode);
        telemetry.addData("Slow Mode: ", slowMode);
        telemetry.addData("Intake: ", robot.intake.state);
        telemetry.addData("Launcher: ", robot.launcher.state);
    }
}