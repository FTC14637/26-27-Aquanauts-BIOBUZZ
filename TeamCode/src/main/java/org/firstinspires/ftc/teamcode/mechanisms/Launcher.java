package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
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
    private final int targetVelocity = 2000; // Target velocity for the motors
    private final int tolerance = 100; // Tolerance for the target velocity allowed for launch
    private final int inTime = 250; //(milliseconds) How long the launcher must be within the target velocity tolerance to launch returns to IDLE with no input
    //------------------------------------------------//

    public double a, b, c;

    //Declarations
    private final DcMotorEx launcherFlyWheel;
    //private final Servo gate;
    private final ElapsedTime timer = new ElapsedTime();
    public Launcher(Robot robot) {
        launcherFlyWheel = robot.hardwareMap.get(DcMotorEx.class, "launcherFlyWheel");
        launcherFlyWheel.setDirection(DcMotorEx.Direction.REVERSE);
        launcherFlyWheel.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        launcherFlyWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        //gate = robot.hardwareMap.get(Servo.class, "gate");
    }

    //States
    public enum State {OFF, SPINNING_UP, READY, ON, REVERSE}
    public State state = State.OFF;

    //Commands
    public Command launcherToggle() {
        return Command.build()
                .setExecute(() -> {
                    if (state == State.OFF) {
                        a = launcherFlyWheel.getVelocity();
                        state = State.ON;
                        launcherFlyWheel.setVelocity(targetVelocity);
                    } else if (state == State.ON) {
                        launcherFlyWheel.setPower(0);
                        state = State.OFF;
                    }
                })
                .setDone(() -> state == State.ON || state == State.OFF)
                .requiring(launcherFlyWheel)
                .setPriority(2);
    }
}