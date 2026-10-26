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
