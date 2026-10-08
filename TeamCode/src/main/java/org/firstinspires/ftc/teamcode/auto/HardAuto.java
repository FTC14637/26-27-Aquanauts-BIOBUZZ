package org.firstinspires.ftc.teamcode.auto;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Hardware;

@Autonomous(name = "HardAuto", group = "Aqua")
public class HardAuto extends LinearOpMode {

    Hardware hardware = new Hardware();

    @Override
    public void runOpMode() {

        hardware.init(hardwareMap);

        waitForStart();
        if(isStopRequested()) return; // Stops if stopped is pressed

        while(opModeIsActive()) {
            stright(1, 750);
            sideLeft(1,750);
            back(1, 750);

            telemetry.update();
        }
    }

    private void stright(double speed, long time) {
        hardware.frontLeft.setPower(speed);
        hardware.frontRight.setPower(speed);
        hardware.backLeft.setPower(speed);
        hardware.backRight.setPower(speed);
        sleep(time);
        hardware.frontLeft.setPower(0);
        hardware.frontRight.setPower(0);
        hardware.backLeft.setPower(0);
        hardware.backRight.setPower(0);
    }

    private void back(double speed, long time) {
        hardware.frontLeft.setPower(-speed);
        hardware.frontRight.setPower(-speed);
        hardware.backLeft.setPower(-speed);
        hardware.backRight.setPower(-speed);
        sleep(time);
        hardware.frontLeft.setPower(0);
        hardware.frontRight.setPower(0);
        hardware.backLeft.setPower(0);
        hardware.backRight.setPower(0);
    }

    private void sideRight(double speed, long time) {
        hardware.frontLeft.setPower(-speed);
        hardware.frontRight.setPower(speed);
        hardware.backLeft.setPower(-speed);
        hardware.backRight.setPower(speed);
        sleep(time);
        hardware.frontLeft.setPower(0);
        hardware.frontRight.setPower(0);
        hardware.backLeft.setPower(0);
        hardware.backRight.setPower(0);
    }

    private void sideLeft(double speed, long time) {
        hardware.frontLeft.setPower(speed);
        hardware.frontRight.setPower(-speed);
        hardware.backLeft.setPower(speed);
        hardware.backRight.setPower(-speed);
        sleep(time);
        hardware.frontLeft.setPower(0);
        hardware.frontRight.setPower(0);
        hardware.backLeft.setPower(0);
        hardware.backRight.setPower(0);
    }
}

