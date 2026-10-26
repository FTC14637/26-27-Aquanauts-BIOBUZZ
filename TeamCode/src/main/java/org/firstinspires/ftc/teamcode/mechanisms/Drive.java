package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.robot.Alliance;
import org.firstinspires.ftc.teamcode.robot.CustomOpMode;
import org.firstinspires.ftc.teamcode.robot.Robot;

//Hardware
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

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

    //------------------Pose Configuration------------------//
    public Pose poseStorage = new Pose(0, 0, 0); //Stores pose from Autonomous to transfer to TeleOp
    private final Pose redStartPose = p.of(58.86, 9.22, 0);
    private final Pose blueStartPose = p.of(0, 0, 0);
    private final Pose redParkingPose = p.of(0, 0, 0);
    private final Pose blueParkingPose = p.of(0, 0, 0);
    private final Pose startPose = ((CustomOpMode.currentAlliance == Alliance.RED) ? redStartPose : blueStartPose);
    private final Pose parkingPose = ((CustomOpMode.currentAlliance == Alliance.RED) ? redParkingPose : blueParkingPose);
    //-----------------------------------------------------//

    //Declarations
    public Follower follower;
    private Telemetry telemetry;
    public IMU imu;

    /*
    public static IMU.Parameters imuConf = new IMU.Parameters(
            new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.FORWARD, RevHubOrientationOnRobot.UsbFacingDirection.UP)
    );
     */

    public Drive(Robot robot) {
        follower = Constants.create(robot.hardwareMap);
        telemetry = robot.telemetry;
        imu = robot.hardwareMap.get(IMU .class, "imu");
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
