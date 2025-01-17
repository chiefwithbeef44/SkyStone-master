package org.firstinspires.ftc.teamcode.team4348.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

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
        bot.lMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        bot.lClamp.setPosition(0.2);
    }

    /**
     * This is the loop function. It is repeatedly called during the teleop phase.
     * Most of the robot's functionality comes from this function, and it should get full attention.
     */

    public void loop()
    {
        double lStick1 = -gamepad1.left_stick_y;
        double rStick1 = -gamepad1.right_stick_y;


        if(Math.abs(lStick1)>stickThresh)
        {
            bot.lMotor.setPower(lStick1 * 0.55);
        }else
            {
                bot.lMotor.setPower(0);
        }


        if(Math.abs(rStick1)>stickThresh)
        {
            bot.rMotor.setPower(rStick1 * 0.55);
        }else
            {
            bot.rMotor.setPower(0);
        }

        if(gamepad1.dpad_up)
        {
            bot.lLift.setPower(1);
            bot.rLift.setPower(1);
        }else {
            bot.rLift.setPower(0);
            bot.lLift.setPower(0);
        }

        if(gamepad1.dpad_down)
        {
            bot.lLift.setPower(-1);
            bot.rLift.setPower(-1);
        }else {
            bot.rLift.setPower(0);
            bot.lLift.setPower(0);
        }

        if(Math.abs(gamepad1.left_stick_x) > stickThresh)
        {
            bot.slide.setPower(gamepad1.left_stick_x * 0.55);
        }else{
            bot.slide.setPower(0);

        }

        if(gamepad1.x)
        {
            bot.lClamp.setPosition(0.7);
        }

        if(gamepad1.b)
        {
            bot.lClamp.setPosition(0.2);
        }

        if(gamepad1.y)
        {
            bot.lFound.setPosition(0.3);
            bot.rFound.setPosition(0.7);
        }

        if(gamepad1.a)
        {
            bot.rFound.setPosition(0);
            bot.lFound.setPosition(0.9);
        }

        telemetry.addData("Heading: ", bot.imu.normalizeValue(bot.imu.getZAxisValue()));

         telemetry.update();

    }
}


