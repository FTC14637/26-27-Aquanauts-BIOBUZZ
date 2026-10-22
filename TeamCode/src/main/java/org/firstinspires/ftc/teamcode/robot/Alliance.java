package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.ColorSensor;

    public enum Alliance {
        RED,
        BLUE,
        UNKNOWN;

        public static Alliance fromSensor(ColorSensor sensor) {
            if (sensor.red() > sensor.blue()) {
                return Alliance.RED;
            } else if (sensor.blue() > sensor.red()) {
                return Alliance.BLUE;
            } else {
                return Alliance.UNKNOWN;
            }
        }
    }



/*
public class AllianceCheck {
    //private ColorSensor sensor;
    //public enum Alliances {RED, BLUE, UNKNOWN};
    //public Alliances alliance = Alliances.UNKNOWN;

    //public AllianceCheck(Robot robot) {sensor = robot.hardwareMap.get(ColorSensor.class, "colorSensor");}

    public Enum Alliances {
        RED,
        BLUE,
        UNKNOWN;
        private ColorSensor sensor;





    }
}

/*


 */
