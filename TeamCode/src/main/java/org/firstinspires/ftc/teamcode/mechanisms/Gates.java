package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.teamcode.robot.Robot;

//Ivy
import com.pedropathing.ivy.Command;
import static com.pedropathing.ivy.commands.Commands.*;

//Hardware
import com.qualcomm.robotcore.hardware.Servo;

public class Gates {

    //Declarations
    private final Servo gateL, gateR;
    private double gateRPos = 0;

    public Gates(Robot robot) {
        gateL = robot.hardwareMap.get(Servo.class, "gateL");
        gateR = robot.hardwareMap.get(Servo.class, "gateR");

        Command rightGate = Command.build()
                .setStart(() -> {
                    gateRPos = gateR.getPosition();
                })
                .setExecute(() -> {
                    if (gateRPos < 0.5) {
                        gateR.setPosition(0.5);
                    } else {
                        gateR.setPosition(0.5);
                    }
                })
                .requiring(gateR)
                .setInterruptedBehavior(/* interrupted behavior */)
                .setBlockedBehavior(/* blocked behavior */)
                .setConflictBehavior(/* conflict behavior */)
                .setPriority(/* priority */);

    }

    //Commands


}
