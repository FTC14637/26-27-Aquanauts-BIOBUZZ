package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.teamcode.robot.Robot;

//Ivy
import com.pedropathing.ivy.Command;
import static com.pedropathing.ivy.commands.Commands.*;

//Hardware
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

//The launcher on the back of the robot.
public class Launcher {

    //------------------Configuration------------------//
    private final int targetVelocity = 2500; // Target velocity for the motors
    private final int tolerance = 100; // Tolerance for the target velocity allowed for launch
    private final int inTime = 250; //(milliseconds) How long the launcher must be within the target velocity tolerance to launch returns to IDLE with no input
    //------------------------------------------------//

    //Declarations
    private final DcMotorEx launcherFlyWheel;
    //private final Servo gate;
    private final ElapsedTime timer = new ElapsedTime();
    public Launcher(Robot robot) {
        launcherFlyWheel = robot.hardwareMap.get(DcMotorEx.class, "launcherFlyWheel");
        launcherFlyWheel.setDirection(DcMotorEx.Direction.REVERSE);
        launcherFlyWheel.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        //gate = robot.hardwareMap.get(Servo.class, "gate");
    }

    //States
    public enum State {OFF, SPEED_UP, ON, REVERSE}
    public State state = State.OFF;

    //Commands
    public Command on() {
        return instant(() -> state= Launcher.State.ON).requiring(launcherFlyWheel);
    };
    public Command off() {
        return instant(() -> state= Launcher.State.OFF).requiring(launcherFlyWheel);
    };

    public Command reverse() {
        return instant(() -> state= State.REVERSE).requiring(launcherFlyWheel);
    };

    //Main Loop
    public Command loop() {
        return infinite(() -> {
            switch (state) {
                case OFF:
                    //gate.setPosition(Servo.MIN_POSITION);
                    launcherFlyWheel.setPower(0);

                    break;
                case SPEED_UP:
                    launcherFlyWheel.setVelocity(targetVelocity);
                    //gate.setPosition(Servo.MIN_POSITION);

                    //Check if flywheel is fast enough (with a small tolerance)
                    if (Math.abs(launcherFlyWheel.getVelocity() - targetVelocity) <= tolerance) {
                        //If the flywheel has been stable in range for long enough move to ready
                        if (timer.milliseconds() >= inTime) {
                            state = State.ON;
                        }
                    } else {
                        timer.reset();
                        //If it fails it restarts the timer
                    }

                    break;
                case ON:
                    launcherFlyWheel.setVelocity(targetVelocity);
                    //gate.setPosition(Servo.MAX_POSITION);

                    break;
                case REVERSE:
                    //gate.setPosition(Servo.MAX_POSITION);
                    launcherFlyWheel.setPower(-0.1);

                    break;
            }
        });
    }
}