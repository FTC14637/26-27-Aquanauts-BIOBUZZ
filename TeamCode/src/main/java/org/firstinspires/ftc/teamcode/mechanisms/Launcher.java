package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.robot.Robot;

import com.pedropathing.ivy.Command;
import static com.pedropathing.ivy.commands.Commands.*;

public class Launcher {


    private final DcMotorEx launcherFlyWheel;
    public Launcher(Robot robot) {
        launcherFlyWheel = robot.hardwareMap.get(DcMotorEx.class, "launcherFlyWheel");
    }

    public enum State {IDLE, SPEEDING_UP, READY, LAUNCHING}

    private State state = State.IDLE;

    private final ElapsedTime inToleranceTimer = new ElapsedTime();

    private final int TARGET_LAUNCHER_VELOCITY = 5000; // Target RPM for both launcher motors
    private final int LAUNCHER_TOLERANCE = 100; // Tolerance of RPM required for launch
    private final int LAUNCHER_IN_RANGE_TIME = 250; // How long the launcher must be within the target RPM tolerance to launch (milliseconds)

    //Ivy Commands


    public void shoot() {
        if (state == State.IDLE) {
            state = State.SPEEDING_UP;
            inToleranceTimer.reset();
        }
    }

    public Command on() {return instant(() -> state= State.SPEEDING_UP).requiring(launcherFlyWheel);};

    public Command launch() {
        return instant(() -> state= State.LAUNCHING).requiring(launcherFlyWheel);
    };

    public Command off() {
        return instant(() -> state= State.IDLE).requiring(launcherFlyWheel);
    };



    public void runLauncher() {
        switch (state) {
            case IDLE:
                launcherFlyWheel.setPower(0);
                //TODO add close gate to stop balls from launching
                break;

            case SPEEDING_UP:
                launcherFlyWheel.setVelocity(TARGET_LAUNCHER_VELOCITY);

                // Check if flywheel is fast enough (with a small tolerance)
                if (Math.abs(launcherFlyWheel.getVelocity() - TARGET_LAUNCHER_VELOCITY) <= LAUNCHER_TOLERANCE) {
                    // If the flywheel has been stable in range for long enough, transition to launch
                    if (inToleranceTimer.milliseconds() >= LAUNCHER_IN_RANGE_TIME) {
                        state = State.READY;
                        inToleranceTimer.reset(); // Reuse the timer to track how long the gate stays open
                    }
                } else {
                    inToleranceTimer.reset();
                }
                break;

            case READY:

                launcherFlyWheel.setVelocity(TARGET_LAUNCHER_VELOCITY);
                inToleranceTimer.reset();

                break;

            case LAUNCHING:
                launcherFlyWheel.setVelocity(TARGET_LAUNCHER_VELOCITY);

                //TODO open gate

                break;

        }
    }
}