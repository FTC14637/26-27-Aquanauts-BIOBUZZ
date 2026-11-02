package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.teamcode.robot.Robot;

//Ivy
import com.pedropathing.ivy.Command;
import static com.pedropathing.ivy.commands.Commands.*;

//Hardware
import com.qualcomm.robotcore.hardware.DcMotorEx;


//This is the main spinning intake mechanism.
public class Intake {

    //Declarations
    private final DcMotorEx intake;
    public Intake(Robot robot) {
        intake = robot.hardwareMap.get(DcMotorEx.class, "intake");
        intake.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        intake.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    //States
    public enum State {ON, OFF, REVERSE}
    public State state = State.OFF;

    //Commands
    public Command on() {
        return instant(() -> state=State.ON).requiring(intake);
    };
    public Command off() {
        return instant(() -> state=State.OFF).requiring(intake);
    };
    public Command reverse() {
        return instant(() -> state=State.REVERSE).requiring(intake);
    };

    //Main Loop
    public Command loop() {
        return infinite(() -> {
            switch(state){
                case ON:
                    intake.setVelocity(60000);
                    break;
                case OFF:
                    intake.setPower(0);
                    break;
                case REVERSE:
                    intake.setVelocity(-1000);
                    break;
            }
        });
    }
}