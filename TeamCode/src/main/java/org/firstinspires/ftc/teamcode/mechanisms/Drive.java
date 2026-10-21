package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.AllianceCheck;

//Ivy
import com.pedropathing.ivy.Command;
import static com.pedropathing.ivy.commands.Commands.*;
import static com.pedropathing.ivy.pedro.PedroCommands.*;

//Pedro
import org.firstinspires.ftc.teamcode.pedro.Constants;
import com.pedropathing.follower.Follower;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

public class Drive {
    private final PoseFactory p = PoseFactory.degrees();

    if (AllianceCheck.)

    //------------------Pose Configuration------------------//
    public Pose poseStorage = new Pose(0, 0, 0); //Stores pose from Autonomous to transfer to TeleOp
    private final Pose redStartPose = p.of(58.86, 9.22, 0);
    private final Pose blueStartPose = p.of(0, 0, 0);
    private final Pose redParking = p.of(0, 0, 0);
    private final Pose blueParking = p.of(0, 0, 0);
    private Pose startPose = p.of(0, 0, 0); //TODO check some sort of alliance
    private Pose parkingPose = p.of(0, 0, 0); //TODO check some sort of alliance
    //-----------------------------------------------------//

    //Declarations
    public Follower follower;
    private Telemetry telemetry;

    public Drive(Robot robot) {
        follower = Constants.create(robot.hardwareMap);
        telemetry = robot.telemetry;
    }

    //Commands
    public void setStartPose(Pose pose) {follower.setPose(pose);} //This can be used to reline the robot mid tele op

    public Command followPath(Path path) {return follow(follower, path);}

    public Command holdPose(Pose pose) {return hold(follower, pose);} //Tells the follower to not allow the robot to move

    //Main Loop
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
