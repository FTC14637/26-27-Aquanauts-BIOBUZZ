package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.teamcode.robot.Robot;

//Ivy
import com.pedropathing.ivy.Command;
import static com.pedropathing.ivy.commands.Commands.*;

//Hardware
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

//TODO add second launcher logic
//The two launchers on the back of the robot.
public class Launcher {

    //------------------Configuration------------------//
    private final int targetVelocity = 5000; // Target velocity for the motors
    private final int tolerance = 100; // Tolerance for the target velocity allowed for launch
    private final int inTime = 250; //(milliseconds) How long the launcher must be within the target velocity tolerance to launch
    private final int timeout = 10; // (seconds) How long the until the launcher returns to IDLE with no input
    //------------------------------------------------//

    //Declarations
    private final DcMotorEx launcherFlyWheel;
    public Launcher(Robot robot) {
        launcherFlyWheel = robot.hardwareMap.get(DcMotorEx.class, "launcherFlyWheel");
    }

    //States
    public enum State {IDLE, SPEEDING_UP, READY, LAUNCHING}
    public State state = State.IDLE;

    //Timer
    private final ElapsedTime timer = new ElapsedTime();

    //Commands
    public Command on() {return instant(() -> state= State.SPEEDING_UP).requiring(launcherFlyWheel);};
    public Command launch() {return instant(() -> state= State.LAUNCHING).requiring(launcherFlyWheel);};
    public Command off() {return instant(() -> state= State.IDLE).requiring(launcherFlyWheel);};

    //Main Loop
    public Command loop() {
        return infinite(() -> {
            switch (state) {
                case IDLE:
                    launcherFlyWheel.setPower(0);
                    //TODO add close gate to stop balls from launching
                    break;

                case SPEEDING_UP:
                    timer.reset();
                    launcherFlyWheel.setVelocity(targetVelocity);

                    //Check if flywheel is fast enough (with a small tolerance)
                    if (Math.abs(launcherFlyWheel.getVelocity() - targetVelocity) <= tolerance) {
                        //If the flywheel has been stable in range for long enough move to ready
                        if (timer.milliseconds() >= inTime) {
                            state = State.READY;
                        }
                    } else {
                        timer.reset();
                        //If it fails it restarts the timer
                    }
                    break;

                case READY:
                    timer.reset();
                    launcherFlyWheel.setVelocity(targetVelocity);

                    //Timeout
                    if (timer.seconds() >= timeout) {
                        state = State.IDLE;
                    }

                    break;

                case LAUNCHING:
                    launcherFlyWheel.setVelocity(targetVelocity);
                    //TODO open gate

                    break;
            }
        });
    }
}