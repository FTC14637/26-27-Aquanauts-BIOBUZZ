package org.firstinspires.ftc.teamcode.robot;

//Ivy
import com.pedropathing.ivy.Scheduler;

//FTC
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

//Alliance
import com.qualcomm.robotcore.hardware.ColorSensor;

public class CustomOpMode extends OpMode {

    protected Robot robot;
    public static Alliance currentAlliance;

    @Override
    public void init(){
        robot = new Robot(this);

        currentAlliance = Alliance.fromSensor(robot.hardwareMap.get(ColorSensor.class, "colorSensor"));

        if (currentAlliance == Alliance.UNKNOWN) {
            telemetry.addData("| URGET!!!", "UNKNOWN ALLIANCE, STARTING AS RED.");
            currentAlliance = Alliance.RED;
            telemetry.update();
        }

        Scheduler.reset();

        Scheduler.schedule(
                robot.intake.loop(),
                robot.wiper.loop(),
                robot.launcher.loop(),
                robot.drive.loop()
        );
    }

    @Override
    public void init_loop() {
        Scheduler.execute();
    }

    @Override
    public void loop() {
        Scheduler.execute();
        robot.telemetry.update();
    }

}