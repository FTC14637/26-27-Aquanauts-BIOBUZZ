package org.firstinspires.ftc.teamcode.teleop;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.robot.RobotOpMode;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.pedro.Constants;

@TeleOp(name = "TeleOp", group = "Advanced")
public class NewTeleOp extends RobotOpMode {

    private double slowModeMultiplier = 0.25; // Multiplier for slow mode speed
    private boolean slowMode = false;
    private boolean invertMode = false;

    @Override
    //Runs when you click it
    public void init() {
        super.init();
        //something here
    }

    @Override
    //Runs on start
    public void start() {
        //TODO limelight
        //robot.limelight.init();
    }

    @Override
    //Main loop
    public void loop() {

        //TODO add any and all controls in here plus driving
        robot.drivetrain.follower.manual(
                (invertMode ? 1 : -1) * gamepad1.left_stick_y,
                gamepad1.left_stick_x,
                gamepad1.right_stick_x
        );

        /*
        if(gamepad1.rightBumperWasPressed() && gamepad1.leftBumperWasPressed()) {
            robot.limelight.snapshot().schedule();
            robot.limelight.aprilTag().schedule();
        }

        if(gamepad1.aWasPressed() && robot.intake.mode != Intake.Mode.ON) {
            robot.intake.on().schedule();
        }

        else if(gamepad1.aWasPressed() && robot.intake.mode != Intake.Mode.OFF) {
            robot.intake.off().schedule();
        }

        else if(gamepad1.bWasPressed() && robot.intake.mode != Intake.Mode.REVERSE) {
            robot.intake.reverse().schedule();
        }

         */

        super.loop();
    }

}