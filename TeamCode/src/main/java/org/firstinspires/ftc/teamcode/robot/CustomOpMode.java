package org.firstinspires.ftc.teamcode.robot;

import static com.pedropathing.ivy.Scheduler.schedule;

import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.robot.Robot;

public class CustomOpMode extends OpMode {

    protected Robot robot;

    @Override
    public void init(){
        robot = new Robot(this);

        Scheduler.schedule(
                robot.intake.loop()
        );
    }

    @Override
    public void loop() {
        robot.telemetry.update();
    }

}