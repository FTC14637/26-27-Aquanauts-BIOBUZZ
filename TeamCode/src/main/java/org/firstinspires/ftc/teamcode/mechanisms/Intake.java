package org.firstinspires.ftc.teamcode.mechanisms;


import org.firstinspires.ftc.teamcode.Hardware;

public class Intake {

    private Hardware hardware;

    public Intake(Hardware hardware) {
        this.hardware = hardware;
    }

    private boolean INTAKE_ON = false;


    // Call this method from TeleOp when a button is pressed
    public void intake() {
        INTAKE_ON = !INTAKE_ON;
    }

    public void runIntake() {
        if (INTAKE_ON) {
            hardware.intake.setVelocity(1500);
        } else {
            hardware.intake.setPower(0);
        }
    }
}