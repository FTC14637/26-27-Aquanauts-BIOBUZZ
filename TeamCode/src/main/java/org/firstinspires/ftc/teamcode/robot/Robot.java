package org.firstinspires.ftc.teamcode.robot;

//Panels

//FTC
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

//Mechanisms
import org.firstinspires.ftc.teamcode.mechanisms.Launcher;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Wiper;
import org.firstinspires.ftc.teamcode.mechanisms.Drive;

//Alliance
import org.firstinspires.ftc.teamcode.robot.Alliance;

public class Robot {

    public final HardwareMap hardwareMap;
    public Telemetry telemetry;

    public Alliance alliance;
    public Intake intake;
    public Launcher launcher;
    public Wiper wiper;
    public Drive drive;

    public Robot(OpMode opMode) {
        hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;

        //alliance = new alli(this);

        intake = new Intake(this);
        launcher = new Launcher(this);
        wiper = new Wiper(this);
        drive = new Drive(this);
    }

}