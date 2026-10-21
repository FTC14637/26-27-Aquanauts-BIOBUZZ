package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

public class AllianceCheck {
    private ColorSensor sensor;
    public enum Alliances {RED, BLUE, UNKNOWN};
    public Alliances alliance = Alliances.UNKNOWN;

    public AllianceCheck(Robot robot) {sensor = robot.hardwareMap.get(ColorSensor.class, "colorSensor");}

    public void getAlliance() {
        if (sensor.red() > sensor.blue() && sensor.red() > sensor.green()) {
            alliance = Alliances.RED;
        } else if (sensor.blue() > sensor.red() && sensor.blue() > sensor.green()) {
            alliance = Alliances.BLUE;
        } else {
            alliance = Alliances.UNKNOWN;
        }
    }
}
