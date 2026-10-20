package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.teamcode.robot.Robot;

//Ivy
import com.pedropathing.ivy.Command;
import static com.pedropathing.ivy.commands.Commands.*;

//Hardware
import com.qualcomm.robotcore.hardware.Servo;

//The wiper/arm on the back of the robot.
public class Wiper {

    //Declarations
    private final Servo wiper;
    public Wiper(Robot robot) {
        wiper = robot.hardwareMap.get(Servo.class, "wiper");
    }

    //States
    public enum State {RETRACTED, EXTENDED, IDLE}
    public State state = State.IDLE;

    //Commands
    public Command extend() {
        return instant(() -> state=State.EXTENDED).requiring(wiper);
    };
    public Command retract() {
        return instant(() -> state=State.RETRACTED).requiring(wiper);
    };

    //Main loop
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
