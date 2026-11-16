package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.teamcode.robot.Robot;

//Ivy
import com.pedropathing.ivy.Command;
import static com.pedropathing.ivy.commands.Commands.*;

//Hardware
import com.pedropathing.ivy.behaviors.BlockedBehavior;
import com.qualcomm.robotcore.hardware.Servo;

public class Gates {

    //Declarations
    private final Servo gateL, gateR;
    private double gateRPos = 0, gateLPos = 0;

    public Gates(Robot robot) {
        gateL = robot.hardwareMap.get(Servo.class, "gateL");
        gateR = robot.hardwareMap.get(Servo.class, "gateR");
    }

    //Commands
    public Command rightGate() {
        return Command.build()
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
                .setBlockedBehavior(BlockedBehavior.CANCEL)
                .setPriority(1);
    }
    public Command leftGate() {
        return Command.build()
                .setStart(() -> {
                    gateLPos = gateL.getPosition();
                })
                .setExecute(() -> {
                    if (gateLPos < 0.5) {
                        gateL.setPosition(0.5);
                    } else {
                        gateL.setPosition(0.5);
                    }
                })
                .requiring(gateL)
                .setBlockedBehavior(BlockedBehavior.CANCEL)
                .setPriority(1);
    }
    public Command toggleGates() {
       return Command.build()
                .setExecute(() -> {
                    if (gateLPos < 0.5) {
                        gateL.setPosition(0.5);
                        gateL.setPosition(0.5);
                    }
                })
                .requiring(gateL, gateR)
                .setPriority(2);
    }
}
