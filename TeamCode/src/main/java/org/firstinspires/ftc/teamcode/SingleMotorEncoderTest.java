package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Encoder Test")
public class SingleMotorEncoderTest extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motor = null;
    private int count = 0;
    private int ticNum = 1170;
    private boolean dPadUpPress = false;
    private boolean dPadDownPress = false;
    private boolean dPadLeftPress = false;
    private boolean dPadRightPress = false;
    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.get(DcMotor.class, "Actuator");
        motor.setDirection(DcMotor.Direction.FORWARD);
        motor.setPower(0);
        telemetry.addData("Robot", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();
        while (opModeIsActive()) {

            if (gamepad1.dpad_up && !dPadUpPress) {
                count += 50;
                dPadUpPress = true;

                motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                motor.setTargetPosition(50);//move to pos 10

                motor.setPower(1);//at max speed (-1 to 1)

                sleep(10);
            } else if (!gamepad1.dpad_up)
                dPadUpPress = false;


            if (gamepad1.dpad_down && !dPadDownPress) {
                count -= 50;
                dPadDownPress = true;

                motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                motor.setTargetPosition(-50);//move to pos 10

                motor.setPower(1);//at max speed (-1 to 1)

                sleep(10);
            } else if (!gamepad1.dpad_down){
                dPadDownPress = false;
            }



            if (gamepad1.dpad_right && !dPadRightPress) {
                ticNum += 10;
                dPadRightPress = true;
            } else if (!gamepad1.dpad_right) {
                dPadRightPress = false;
            }
            if (gamepad1.dpad_left && !dPadLeftPress) {
                ticNum -= 10;
                dPadLeftPress = true;
            }else if (!gamepad1.dpad_left) {
                dPadLeftPress = false;
            }



            if(gamepad1.a) {
                motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                motor.setTargetPosition(ticNum);//move to pos 10

                motor.setPower(1);//at max speed (-1 to 1)

                sleep(3000);
            }

            if(gamepad1.b) {
                motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                motor.setTargetPosition(-ticNum);//move to pos 10

                motor.setPower(1);//at max speed (-1 to 1)

                sleep(3000);
            }

            telemetry.addData("Encoder Count:", count);
            telemetry.addData("Tic Num:", ticNum);
            telemetry.update();
        }
    }
}

