package org.firstinspires.ftc.teamcode.robot;

import static com.pedropathing.ivy.Scheduler.schedule;

import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class RobotOpMode extends OpMode {

    protected Robot robot;

    @Override
    public void init(){
        //Scheduler.reset();
        robot = new Robot(this);
    }

    @Override
    public void loop() {
        robot.telemetry.update();
    }

}