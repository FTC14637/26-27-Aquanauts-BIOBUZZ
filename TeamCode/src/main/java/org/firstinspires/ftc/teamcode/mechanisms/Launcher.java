package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware;

public class Launcher {

    private Hardware hardware;

    public Launcher(Hardware hardware) {
        this.hardware = hardware;
    }

    public enum LauncherState {IDLE, SPEEDING_UP, LAUNCHING}

    private LauncherState state = LauncherState.IDLE;

    // GET METHOD
    public LauncherState getState() {
        return state;
    }

    private final ElapsedTime inToleranceTimer = new ElapsedTime();

    private final int TARGET_LAUNCHER_VELOCITY = 5000; // Target RPM for both launcher motors
    private final int LAUNCHER_TOLERANCE = 100; // Tolerance of RPM required for launch
    private final int LAUNCHER_IN_RANGE_TIME = 250; // How long the launcher must be within the target RPM tolerance to launch (milliseconds)

    // Call this method from TeleOp when a button is pressed
    public void shoot() {
        if (state == LauncherState.IDLE) {
            state = LauncherState.SPEEDING_UP;
            inToleranceTimer.reset();
        }
    }

    public void runLauncher() {
        switch (state) {
            case IDLE:
                hardware.launcherFlyWheel.setPower(0);
                //TODO add close gate to stop balls from launching
                break;

            case SPEEDING_UP:
                hardware.launcherFlyWheel.setVelocity(TARGET_LAUNCHER_VELOCITY);

                // Check if flywheel is fast enough (with a small tolerance)
                if (Math.abs(hardware.launcherFlyWheel.getVelocity() - TARGET_LAUNCHER_VELOCITY) <= LAUNCHER_TOLERANCE) {
                    // If the flywheel has been stable in range for long enough, transition to launch
                    if (inToleranceTimer.milliseconds() >= LAUNCHER_IN_RANGE_TIME) {
                        state = LauncherState.LAUNCHING;
                        inToleranceTimer.reset(); // Reuse the timer to track how long the gate stays open
                    }
                } else {
                    inToleranceTimer.reset();
                }
                break;

            case LAUNCHING:
                hardware.launcherFlyWheel.setVelocity(TARGET_LAUNCHER_VELOCITY);

                //TODO open gate

                break;

        }
    }
}