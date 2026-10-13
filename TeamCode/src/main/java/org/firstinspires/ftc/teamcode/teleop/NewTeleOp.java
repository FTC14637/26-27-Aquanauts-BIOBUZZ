package org.firstinspires.ftc.teamcode.teleop;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.robot.CustomOpMode;
import org.firstinspires.ftc.teamcode.pedro.Constants;

@Disabled
@TeleOp(name = "TeleOp", group = "Advanced")
public class NewTeleOp extends CustomOpMode {

    private double slowModeMultiplier = 0.25; // Multiplier for slow mode speed
    private boolean slowMode = false;
    private boolean invertMode = false;

    private Follower follower;

    @Override
    //Runs when you click it
    public void init() {
        super.init();
        follower = Constants.create(robot.hardwareMap);
    }

    @Override
    //Runs on start
    public void start() {

    }

    @Override
    //Main loop
    public void loop() {

        follower.manual(
                (invertMode ? 1 : -1) *  (slowMode ? slowModeMultiplier : 1) * gamepad1.left_stick_y,
                (slowMode ? slowModeMultiplier : 1) * gamepad1.left_stick_x,
                (slowMode ? slowModeMultiplier : 1) * gamepad1.right_stick_x
        );
        follower.update();

        if (gamepad2.right_trigger_pressed && robot.intake.state != Intake.State.OFF) {
            robot.intake.on();
        };

        super.loop();
    }
}