package org.firstinspires.ftc.teamcode.unused;

import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.robot.Robot;

public class LimeLight {

    private final Limelight3A limelight;
    private Telemetry telemetry;

    private final int aprilTagPipe = 0;

    private LimeLight(Robot robot) {
        limelight = robot.hardwareMap.get(Limelight3A.class, "limelight");
        telemetry = robot.telemetry;
    }
}
