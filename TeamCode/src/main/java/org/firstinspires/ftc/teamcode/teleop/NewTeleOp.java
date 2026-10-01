package org.firstinspires.ftc.teamcode.teleop;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.robot.CustomOpMode;
import org.firstinspires.ftc.teamcode.pedro.Constants;

@TeleOp(name = "TeleOp", group = "Advanced")
public class NewTeleOp extends CustomOpMode {

    private double slowModeMultiplier = 0.25; // Multiplier for slow mode speed
    private boolean slowMode = false;
    private boolean invertMode = false;

    private Follower follower;

    @Override
    //Runs when you click it
    public void init() {
        follower = Constants.create(hardwareMap);
        super.init();
    }

    @Override
    //Runs on start
    public void start() {
        //TODO limelight
    }

    @Override
    //Main loop
    public void loop() {

        follower.manual(
                (invertMode ? 1 : -1) * gamepad1.left_stick_y,
                gamepad1.left_stick_x,
                gamepad1.right_stick_x
        );
        follower.update();
        t

        if (gamepad2.right_trigger_pressed && robot.intake.state != Intake.State.OFF) {
            robot.intake.on();
        };

        super.loop();
    }
}