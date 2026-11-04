package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.robot.CustomOpMode;
import org.firstinspires.ftc.teamcode.robot.Robot;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;


import com.pedropathing.ivy.Command;
import static com.pedropathing.ivy.groups.Groups.*;

@Disabled
@Autonomous(name = "BlueAuto", group = "Aqua")
public class BlueAuto extends CustomOpMode {

    private Command autoRoutine() {
        return sequential(
                robot.drive.followPath(robot.drive.startToPark()),
                robot.drive.followPath(robot.drive.startToPark())
        );
    }

    @Override
    public void init() {
        //robot.drive.imu.initialize(Drive.imuConf);
    }

    @Override
    public void start() {
        autoRoutine().schedule();
    }

    @Override
    public void loop() {

    }

    @Override
    public void stop() {
        robot.drive.poseStorage = robot.drive.follower.pose();
    }
}
