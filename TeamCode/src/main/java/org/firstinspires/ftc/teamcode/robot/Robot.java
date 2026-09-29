package org.firstinspires.ftc.teamcode.robot;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.bylazar.fullpanels.*;

import com.pedropathing.ivy.Command;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.mechanisms.Launcher;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;


public class Robot {

    public final HardwareMap hardwareMap;
    public Intake intake;
    public Launcher launcher;
    //public Limelight limelight;

    public Telemetry telemetry;

    public Robot(OpMode opMode) {
        hardwareMap = opMode.hardwareMap;

        intake = new Intake(this);
        launcher = new Launcher(this);
        //limelight = new Limelight(this);
    }

}