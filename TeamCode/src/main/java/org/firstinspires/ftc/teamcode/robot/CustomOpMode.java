package org.firstinspires.ftc.teamcode.robot;

import static com.pedropathing.ivy.Scheduler.schedule;

import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class CustomOpMode extends OpMode {

    protected Robot robot;
    //private Telemetry telemetry;


    @Override
    public void init(){
        robot = new Robot(this);

        Scheduler.reset();

        Scheduler.schedule(
                robot.intake.loop()
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