package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.teamcode.robot.Robot;

//Ivy
import com.pedropathing.ivy.Command;
import static com.pedropathing.ivy.commands.Commands.*;

//Hardware
import com.pedropathing.ivy.behaviors.BlockedBehavior;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


//This is the main spinning intake mechanism.
public class Intake {

    //Declarations
    private final DcMotorEx intake;
    public Intake(Robot robot) {
        intake = robot.hardwareMap.get(DcMotorEx.class, "intake");
        intake.setDirection(DcMotorSimple.Direction.REVERSE);
        intake.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
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
                        intake.setVelocity(60000);
                        state = State.ON;
                    } else if (state == State.ON || state == State.REVERSE) {
                        intake.setPower(0);
                    }
                })
                .requiring(intake)
                .setPriority(2);
    }

    public Command reverseIntake() {
        return Command.build()
                .setExecute(() -> {
                    if (state == State.OFF || state == State.ON) {
                        intake.setVelocity(-1000);
                        state = State.REVERSE;
                    }
                })
                .requiring(intake)
                .setBlockedBehavior(BlockedBehavior.CANCEL)
                .setPriority(1);
    }
}