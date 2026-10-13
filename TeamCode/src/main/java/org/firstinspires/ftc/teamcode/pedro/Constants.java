package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.follower.Follower;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.tuning.autotune.Tuner;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {

    public static Follower create(HardwareMap h) {
        // return new Follower(Drivetrain, Localizer, Foresight);
        //return new Follower(null, new Mecanum(h , Constants.driveConfig), null);
        return null;
    }

    public static MecanumConfig driveConfig = new MecanumConfig(
            c -> {
                c.frontRightName.set("frontRight");
                c.frontLeftName.set("frontLeft");
                c.backRightName.set("backRight");
                c.backLeftName.set("backLeft");

                c.frontRightDirection.set(DcMotorEx.Direction.FORWARD);
                c.frontLeftDirection.set(DcMotorEx.Direction.REVERSE);
                c.backRightDirection.set(DcMotorEx.Direction.REVERSE);
                c.backLeftDirection.set(DcMotorEx.Direction.FORWARD);
            }
    );
}