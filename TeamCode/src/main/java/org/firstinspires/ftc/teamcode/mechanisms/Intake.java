package org.firstinspires.ftc.teamcode.mechanisms;


import org.firstinspires.ftc.teamcode.robot.Robot;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Intake {

    private final DcMotorEx intake;
    public Intake(Robot robot) {

        intake = robot.hardwareMap.get(DcMotorEx.class, "intake");

    }

    private boolean INTAKE_ON = false;


    // Call this method from TeleOp when a button is pressed
    public void intake() {
        INTAKE_ON = !INTAKE_ON;
    }

    public void runIntake() {
        if (INTAKE_ON) {
            intake.setVelocity(1500);
        } else {
            intake.setPower(0);
        }
    }
}