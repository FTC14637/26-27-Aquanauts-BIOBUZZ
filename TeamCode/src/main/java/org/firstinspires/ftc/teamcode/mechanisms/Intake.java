package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.teamcode.robot.Robot;

//Ivy
import com.pedropathing.ivy.Command;
import static com.pedropathing.ivy.commands.Commands.*;

//Hardware
import com.pedropathing.ivy.behaviors.BlockedBehavior;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


//This is the main spinning intake mechanism.
public class Intake {

    //Declarations
    private final DcMotorEx intake;
    public Intake(Robot robot) {
        intake = robot.hardwareMap.get(DcMotorEx.class, "intake");
        intake.setDirection(DcMotorSimple.Direction.REVERSE);
        intake.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        intake.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    //States
    public enum State {ON, OFF, REVERSE}
    public State state = State.OFF;

    //Commands
    public Command toggleIntake() {
        return Command.build()
                .setExecute(() -> {
                    if (state == State.OFF) {
                        state = State.ON;
                        intake.setPower(1);
                    } else if (state == State.ON || state == State.REVERSE) {
                        intake.setPower(0);
                        state = State.OFF;
                    }
                })
                .setDone(() -> state == State.ON || state == State.OFF)
                .requiring(intake)
                .setPriority(2);
    }

    public Command reverseIntake() {
        return Command.build()
                .setExecute(() -> {
                    if (state == State.OFF || state == State.ON) {
                        intake.setPower(-1);
                        state = State.REVERSE;
                    }
                })
                .requiring(intake)
                .setDone(() -> state == State.REVERSE)
                .setBlockedBehavior(BlockedBehavior.CANCEL)
                .setPriority(1);
    }
}