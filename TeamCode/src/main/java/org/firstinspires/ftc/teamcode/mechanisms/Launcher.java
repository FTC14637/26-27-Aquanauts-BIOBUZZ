package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.teamcode.robot.Robot;

//Ivy
import com.pedropathing.ivy.Command;
import static com.pedropathing.ivy.commands.Commands.*;

//Hardware
import com.pedropathing.ivy.behaviors.BlockedBehavior;
import com.pedropathing.ivy.behaviors.InterruptedBehavior;
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
    public enum State {OFF, SPINNING_UP, READY, ON, REVERSE}
    public State state = State.OFF;

    //Commands
    public Command intLauncher() {
        return Command.build()
                .setStart(timer::reset)
                .setExecute(() -> {
                    state = State.SPINNING_UP;

                    launcherFlyWheel.setVelocity(targetVelocity);
                    if (Math.abs(launcherFlyWheel.getVelocity() - targetVelocity) <= tolerance) {
                        if (timer.milliseconds() >= inTime) {
                            state = State.READY;
                        }
                    } else {
                        timer.reset();
                    }
                })
                .setDone(() -> state == State.READY)
                .setEnd(endCondition -> state=State.ON)
                .requiring(launcherFlyWheel)
                .setBlockedBehavior(BlockedBehavior.CANCEL)
                .setPriority(2);
    }

    public Command launcherOff() {
        return Command.build()
                .setStart(timer::reset)
                .setExecute(() -> {
                    launcherFlyWheel.setPower(0);
                    state=State.OFF;
                })
                .requiring(launcherFlyWheel)
                .setInterruptedBehavior(InterruptedBehavior.END)
                .setPriority(3);
    }

    public Command launcherReverse() {
        return Command.build()
                .setStart(timer::reset)
                .setExecute(() -> {
                    launcherFlyWheel.setPower(-0.1);
                    state = State.REVERSE;
                })
                .requiring(launcherFlyWheel)
                .setBlockedBehavior(BlockedBehavior.CANCEL)
                .setPriority(1);
    }
}