package org.firstinspires.ftc.teamcode.robot;

import static com.pedropathing.ivy.Scheduler.schedule;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class CustomOpMode extends OpMode {

    protected Robot robot;

    @Override
    public void init(){
        robot = new Robot(this);
    }

    @Override
    public void loop() {
        robot.telemetry.update();
    }

}