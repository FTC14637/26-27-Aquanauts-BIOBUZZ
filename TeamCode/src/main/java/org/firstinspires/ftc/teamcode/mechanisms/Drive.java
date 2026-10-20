package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.robot.Robot;

//IVY
import com.pedropathing.ivy.Command;
import static com.pedropathing.ivy.commands.Commands.*;

//PEDRO
import com.pedropathing.follower.Follower;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import static com.pedropathing.ivy.pedro.PedroCommands.hold;

public class Drive {
    public Follower follower;
    private Telemetry telemetry;
    private final PoseFactory p = PoseFactory.degrees();


    public Pose poseStorage = new Pose(0, 0, 0);
    private final Pose startPose = p.of(0, 0, 0); //TODO check some sort of alliance
    private final Pose parkingPose = p.of(0, 0, 0); //TODO check some sort of alliance

    public Drive(Robot robot) {
        follower = Constants.create(robot.hardwareMap);
        telemetry = robot.telemetry;
    }

    public void setStartPose(Pose pose) {
        follower.setPose(pose);
    }
    //This can be used to reline the robot mid tele op

    /*
    public Command followPath(Path path) {
        return follow(follower, path);
    }
    This is redundant as ivy has its own command. (I think)
     */

    public Command holdPose(Pose pose) {
        return hold(follower, pose);
    }

    public Command loop() {
        return infinite(() -> {
            follower.update();

            telemetry.addData("Velocity: ", follower.velocity());
            telemetry.addData("Pose X: ", follower.pose().x());
            telemetry.addData("Pose Y: ", follower.pose().y());
            telemetry.addData("Pose Angle: ", follower.pose().heading()+"*");
            telemetry.addData("Current Path: ", follower.currentPath());
        });
    }
}
