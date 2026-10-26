package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.robot.CustomOpMode;

@Disabled
@Autonomous(name = "RedAuto", group = "Aqua")
public class RedAuto extends CustomOpMode {

    @Override
    public void init() {
        //robot.drive.imu.initialize(Drive.imuConf);
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {

    }

    @Override
    public void stop() {
        robot.drive.poseStorage = robot.drive.follower.pose();
    }
}
