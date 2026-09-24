package org.firstinspires.ftc.teamcode.test;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.mechanisms.Launcher;
import org.firstinspires.ftc.teamcode.teleop.MainTeleOpHardware;



@TeleOp(name = "LauncherTest", group = "Aquanauts")
public class LauncherTest extends LinearOpMode {


    private Launcher launcher;
    private MainTeleOpHardware hardware;


    @Override
    public void runOpMode() {

        hardware = new MainTeleOpHardware();
        hardware.launcherFlyWheel = hardwareMap.get(DcMotorEx.class, "launcherFlyWheel");
        //hardware.launcherFlyWheel.setMode(com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_USING_ENCODER);

        launcher = new Launcher(hardware);


        telemetry.addLine("Initialized, waiting for start...");
        telemetry.update();

        waitForStart();

        if (isStopRequested()) {
            return;
        }

        while (opModeIsActive()) {
            if (gamepad1.right_trigger_pressed) {
                launcher.shoot();
            }

            // This must run every loop.
            launcher.runLauncher();

            telemetry.addData ("Status", "Running");
            telemetry.addData("Launcher State Machine", launcher.getState()); // Shows IDLE, SPEEDING_UP, or LAUNCHING
            telemetry.addData("Current Velocity", hardware.launcherFlyWheel.getVelocity());
            telemetry.addData("Current Position", hardware.launcherFlyWheel.getCurrentPosition());
            telemetry.update();
        }
    }
}