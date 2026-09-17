package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.util.ElapsedTime;

public class Launcher {

    MainTeleOpHardware hardware = new MainTeleOpHardware();
    public enum LauncherState {
        IDLE,
        SPEEDING_UP,
        LAUNCHING
    }
    private LauncherState state = LauncherState.IDLE;

    private final ElapsedTime inToleranceTimer = new ElapsedTime();

    private final int TARGET_LAUNCHER_RPM = 1500; // Target RPM for both launcher motors
    private final int LAUNCHER_RPM_TOLERANCE = 100; // Tolerance of RPM required for launch
    private final int LAUNCHER_RPM_IN_RANGE_TIME = 250; // How long the launcher must be within the target RPM tolerance to launch (milliseconds)

    private int launches = 0;

    public void runLauncher() {
        switch(state) {
            case IDLE:
                hardware.launcherFlyWheel.setPower(0);
                //TODO add close gate to stop balls from launching
                break;

            case SPEEDING_UP:

                ha

                break;

            case LAUNCHING:

                break;
        }}
        }
    }
}
