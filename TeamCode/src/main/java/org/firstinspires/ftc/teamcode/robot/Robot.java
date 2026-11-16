package org.firstinspires.ftc.teamcode.robot;

//Panels

//FTC
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

//Mechanisms
import org.firstinspires.ftc.teamcode.mechanisms.Launcher;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Gates;
import org.firstinspires.ftc.teamcode.mechanisms.Drive;

//Alliance


public class Robot {

    public final HardwareMap hardwareMap;
    public Telemetry telemetry;

    public Alliance alliance;
    public Intake intake;
    public Launcher launcher;
    public Gates gates;
    public Drive drive;

    public Robot(OpMode opMode) {
        hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;

        intake = new Intake(this);
        launcher = new Launcher(this);
        gates = new Gates(this);
        //drive = new Drive(this);
    }

}