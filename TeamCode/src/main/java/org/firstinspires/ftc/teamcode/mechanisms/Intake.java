package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.teamcode.robot.Robot;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.pedropathing.ivy.Command;
import static com.pedropathing.ivy.commands.Commands.*;

public class Intake {

    private final DcMotor intake;
    public Intake(Robot robot) {
        intake = robot.hardwareMap.get(DcMotor.class, "intake");
    }

    public enum State {ON, OFF, REVERSE}
    public State state = State.OFF;

    public Command on() {
        return instant(() -> state=State.ON).requiring(intake);
    };

    public Command off() {
        return instant(() -> state=State.OFF).requiring(intake);
    };

    public Command reverse() {
        return instant(() -> state=State.REVERSE).requiring(intake);
    };

    public Command loop() {
        return infinite(() -> {
            switch(state){
                case ON:
                    intake.setPower(1);
                    break;
                case OFF:
                    intake.setPower(0);
                    break;
                case REVERSE:
                    intake.setPower(-1);
                    break;
            }
        });
    }



}