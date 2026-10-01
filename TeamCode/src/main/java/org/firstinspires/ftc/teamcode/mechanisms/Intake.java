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

    public Command on() {
        return infinite(() -> intake.setPower(1)).requiring(intake);
    };

    public Command off() {
        return infinite(() -> intake.setPower(0)).requiring(intake);
    };

    public Command reverse() {
        return infinite(() -> intake.setPower(-1)).requiring(intake);
    };

    public Command loop() {
        return infinite(() -> {

        }
        )
    }
}