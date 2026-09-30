package org.firstinspires.ftc.teamcode.mechanisms;


import org.firstinspires.ftc.teamcode.robot.Robot;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.pedropathing.ivy.Command;
import static com.pedropathing.ivy.commands.Commands.*;



public class Intake {

    private DcMotor intake;
    public Intake(Robot robot) {
        intake = robot.hardwareMap.get(DcMotor.class, "intake");
    }

    private boolean INTAKE_ON = false;
    Command intakeOn = infinite(() -> intake.setPower(1));

}