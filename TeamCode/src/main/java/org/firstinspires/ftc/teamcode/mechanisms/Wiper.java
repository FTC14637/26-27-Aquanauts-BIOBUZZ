package org.firstinspires.ftc.teamcode.mechanisms;

import com.pedropathing.ivy.Command;
import static com.pedropathing.ivy.commands.Commands.*;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.robot.Robot;

public class Wiper {

    private final Servo wiper;

    public Wiper(Robot robot) {
        wiper = robot.hardwareMap.get(Servo.class, "wiper");
    }

    public enum State {RETRACTED, EXTENDED, IDLE}
    public State state = State.IDLE;

    public Command extend() {
        return instant(() -> state=State.EXTENDED).requiring(wiper);
    };

    public Command retract() {
        return instant(() -> state=State.RETRACTED).requiring(wiper);
    };

    public Command loop() {
        return infinite(() -> {
            switch(state){
                case EXTENDED:
                    wiper.setPosition(Servo.MAX_POSITION);
                    break;
                case RETRACTED:
                    wiper.setPosition(Servo.MIN_POSITION);
                    break;
                case IDLE:
                    break;
            }
        });
    }
}
