package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="REEE")
public class REEE extends LinearOpMode{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor actuator = null;
    private boolean actBoolA = false;
    private boolean actBoolB = false;


    private boolean dPadUpPress = false;
    private boolean dPadDownPress = false;
    private boolean dPadLeftPress = false;
    private boolean dPadRightPress = false;

    private int ticNum = 0;
    private int sleepNum = 0;
    private boolean leftStickOn = false;

        @Override
        public void runOpMode() {
            //initialize

            actuator = hardwareMap.get(DcMotor.class,"Actuator");
            actuator.setDirection(DcMotor.Direction.FORWARD);
            actuator.setPower(0);



            telemetry.addData("Robot", "Initialized");
            telemetry.update();





            // Wait for the game to start (driver presses PLAY)
            waitForStart();
            runtime.reset();
            while (opModeIsActive()) {

                if (gamepad1.dpad_up && !dPadUpPress) {
                    ticNum += 500;
                    dPadUpPress = true;
                } else if (!gamepad1.dpad_up) {
                    dPadUpPress = false;
                }
                if (gamepad1.dpad_down && !dPadDownPress) {
                    ticNum -= 500;
                    dPadDownPress = true;
                } else if (!gamepad1.dpad_down) {
                    dPadDownPress = false;
                }
                if (gamepad1.dpad_right && !dPadRightPress) {
                    ticNum += 50;
                    dPadRightPress = true;
                } else if (!gamepad1.dpad_right) {
                    dPadRightPress = false;
                }
                if (gamepad1.dpad_left && !dPadLeftPress) {
                    ticNum -= 50;
                    dPadLeftPress = true;

                } else if (!gamepad1.dpad_left) {
                    dPadLeftPress = false;
                }



                if(gamepad1.left_stick_y > 0 && !leftStickOn){
                    leftStickOn = true;
                    sleepNum = sleepNum + 100;
                }else if (gamepad1.left_stick_y == 0){
                    leftStickOn = false;
                }

                if(gamepad1.left_stick_y < 0 && !leftStickOn){
                    leftStickOn = true;
                    sleepNum = sleepNum - 100;
                }else if (gamepad1.left_stick_y == 0){
                    leftStickOn = false;
                }



                if (gamepad1.a && !actBoolA) {
                    actBoolA = true;
                    actuator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    actuator.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    actuator.setTargetPosition(ticNum);
                    sleep(sleepNum);
                    actuator.setPower(1.0);

                } else if (!gamepad1.a) {
                    actBoolA = false;
                }

                if (gamepad1.b && !actBoolB) {
                    actBoolB = true;
                    actuator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    actuator.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    actuator.setTargetPosition(-ticNum);
                    sleep(sleepNum);
                    actuator.setPower(1.0);

                } else if (!gamepad1.b) {
                    actBoolB = false;
                }
                actuator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                telemetry.addData("ticNum ", ticNum);
                telemetry.addData("SleepNum",sleepNum);
                telemetry.addData("Status", "Run Time: " + runtime.toString());
                telemetry.update();
            }

        }


}

