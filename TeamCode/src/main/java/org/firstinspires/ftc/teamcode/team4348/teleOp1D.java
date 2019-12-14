package org.firstinspires.ftc.teamcode.team4348;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.team4348.bot.IdealBot;

/**
 * This is the teleOp2D class. This is where your bot variable will go to access hardware.
 * The init function is called first. It is mostly used to reset the bot from the autonomous phase.
 * You will also use gamepads to program input in the loop function.
 * The loop function is then called. Use of telemetry is highly recommended so you know what's happening with the bot.
 * If you would like samples, check FTCRobotController>src>main>java>...>external.samples
 * There you will find well documented sample pieces of code showing general form.
 */
@TeleOp(name="1DTeleOp")
public class teleOp1D extends OpMode
{
    //IdealBot used as a container for all the bot hardware
    private IdealBot bot = new IdealBot();

    private static final double stickThresh = 0.125;
    /**
     * This is the init function. It is used to initialize the bot for the teleop phase.
     * Use it to move servos into position, etc.
     */
    @Override
    public void init()
    {
        bot.init(hardwareMap);
        bot.lClamp.setPosition(0.05);
    }

    /**
     * This is the loop function. It is repeatedly called during the teleop phase.
     * Most of the robot's functionality comes from this function, and it should get full attention.
     */

    public void loop()
    {
        double lStick1 = -gamepad1.left_stick_y;
        double rStick1 = gamepad1.right_stick_y;

        double lStick2 = -gamepad2.left_stick_y;
        double rStick2 = gamepad2.right_stick_y;

        if(Math.abs(lStick1)>stickThresh)
        {
            bot.lMotor.setPower(lStick1);
        }else
            {
                bot.lMotor.setPower(0);
        }


        if(Math.abs(rStick1)>stickThresh)
        {
            bot.rMotor.setPower(rStick1);
        }else
            {
            bot.rMotor.setPower(0);
        }

        if(Math.abs(rStick2) > stickThresh)
        {
            bot.lLift.setPower(rStick2 * 0.5);
            bot.rLift.setPower(-rStick2 * 0.5);
        }else {
            bot.rLift.setPower(0);
            bot.lLift.setPower(0);
        }

        if(gamepad2.x)
        {
            bot.lClamp.setPosition(0.8);
        }

        if(gamepad2.b)
        {
            bot.lClamp.setPosition(0.05);
        }

        telemetry.addData("IMU Value: ", bot.imu.normalizeValue(bot.imu.getValue()));
        telemetry.update();

    }
}

