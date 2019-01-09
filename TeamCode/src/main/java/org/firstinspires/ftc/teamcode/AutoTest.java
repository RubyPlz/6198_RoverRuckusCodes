package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.disnodeteam.dogecv.detectors.roverrukus.SamplingOrderDetector;

@TeleOp(name="AutoTestTeleOp")
public class AutoTest extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor backRightDrive = null;
    private Servo dump = null;
    private boolean dumpPressPos = false;
    private boolean dumpPressNeg = false;
    private double position = 0.0;

    private boolean dPadUpPress = false;
    private boolean dPadDownPress = false;
    private boolean dPadLeftPress = false;
    private boolean dPadRightPress = false;
    private boolean rBump = false;
    private boolean lBump = false;
    private boolean yPress = false;
    private boolean aPress = false;
    private boolean xPress = false;
    private boolean bPress = false;
    private boolean rtPress = false;
    private boolean ltPress = false;
    private boolean leftStickOn = false;

    private double powerLevel = 0.2;
    private int ticNum = 2240;

    private int sleepNum = 2000;

    private GoldAlignDetector detector;



    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void runOpMode() throws InterruptedException {
        frontLeftDrive = hardwareMap.get(DcMotor.class, "fleft");
        frontRightDrive = hardwareMap.get(DcMotor.class, "fright");
        backLeftDrive = hardwareMap.get(DcMotor.class, "bleft");
        backRightDrive = hardwareMap.get(DcMotor.class, "bright");
        frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);

        dump = hardwareMap.get(Servo.class,"Dump");
        dump.setDirection(Servo.Direction.FORWARD);





        detector = new GoldAlignDetector();
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        detector.useDefaults();

        // Optional Tuning
        detector.alignSize = 100; // How wide (in pixels) is the range in which the gold object will be aligned. (Represented by green bars in the preview)
        detector.alignPosOffset = 0; // How far from center frame to offset this alignment zone.
        detector.downscale = 0.4; // How much to downscale the input frames

        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        //detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
        detector.maxAreaScorer.weight = 0.005;

        detector.ratioScorer.weight = 5;
        detector.ratioScorer.perfectRatio = 1.0;

        detector.enable();


        telemetry.addData("Robot", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();


        //PLAY
        while (opModeIsActive()) {


            telemetry.addData("Tic Number", ticNum);
            telemetry.addData("Power Level", "Power Level (%.2f)", powerLevel);
            telemetry.addData("Sleep Number",sleepNum);
            telemetry.addData("Aligned with Gold", detector.getAligned());
            telemetry.addData("X POSITION", detector.getXPosition());






            //buttons
            if (gamepad1.y && !yPress) {
                yPress = true;
                frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);



                frontLeftDrive.setTargetPosition(ticNum);//move to pos 10
                frontRightDrive.setTargetPosition(ticNum);
                backLeftDrive.setTargetPosition(ticNum);
                backRightDrive.setTargetPosition(ticNum);

                frontLeftDrive.setPower(powerLevel);//at max speed (-1 to 1)
                frontRightDrive.setPower(powerLevel);
                backLeftDrive.setPower(powerLevel);
                backRightDrive.setPower(powerLevel);
                sleep(sleepNum);
            }
            else if(!gamepad1.y) {
                yPress = false;
            }
            if (gamepad1.a && !aPress) {
                aPress = true;
                frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                frontLeftDrive.setTargetPosition(-ticNum);//move to pos 10
                frontRightDrive.setTargetPosition(-ticNum);
                backLeftDrive.setTargetPosition(-ticNum);
                backRightDrive.setTargetPosition(-ticNum);

                frontLeftDrive.setPower(powerLevel);//at max speed (-1 to 1)
                frontRightDrive.setPower(powerLevel);
                backLeftDrive.setPower(powerLevel);
                backRightDrive.setPower(powerLevel);
                sleep(sleepNum);
            }else if(!gamepad1.a) {
                aPress = false;
            }

            if (gamepad1.x && !xPress) {
                xPress = true;
                frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                frontLeftDrive.setTargetPosition(-520);//move to pos 10
                frontRightDrive.setTargetPosition(520);
                backLeftDrive.setTargetPosition(-520);
                backRightDrive.setTargetPosition(520);

                frontLeftDrive.setPower(powerLevel);//at max speed (-1 to 1)
                frontRightDrive.setPower(powerLevel);
                backLeftDrive.setPower(powerLevel);
                backRightDrive.setPower(powerLevel);
                sleep(sleepNum);
            }else if(!gamepad1.x) {
                xPress = false;
            }

            if (gamepad1.b && !bPress) {
                bPress = true;
                frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                frontLeftDrive.setTargetPosition(520);//move to pos 10
                frontRightDrive.setTargetPosition(-520);
                backLeftDrive.setTargetPosition(520);
                backRightDrive.setTargetPosition(-520);

                frontLeftDrive.setPower(powerLevel);//at max speed (-1 to 1)
                frontRightDrive.setPower(powerLevel);
                backLeftDrive.setPower(powerLevel);
                backRightDrive.setPower(powerLevel);
                sleep(sleepNum);
            }else if(!gamepad1.b) {
                bPress = false;
            }







            //triggers
            if(gamepad1.right_trigger>0 && !rtPress){
                rtPress = true;
                frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                frontLeftDrive.setTargetPosition(280);//move to pos 10
                frontRightDrive.setTargetPosition(-280);
                backLeftDrive.setTargetPosition(280);
                backRightDrive.setTargetPosition(-280);

                frontLeftDrive.setPower(powerLevel);//at max speed (-1 to 1)
                frontRightDrive.setPower(powerLevel);
                backLeftDrive.setPower(powerLevel);
                backRightDrive.setPower(powerLevel);
                sleep(sleepNum);

            }else if(gamepad1.right_trigger==0){
                rtPress = false;

            }

            if(gamepad1.left_trigger>0 && !ltPress){
                ltPress = true;
                frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                frontLeftDrive.setTargetPosition(-280);//move to pos 10
                frontRightDrive.setTargetPosition(280);
                backLeftDrive.setTargetPosition(-280);
                backRightDrive.setTargetPosition(280);

                frontLeftDrive.setPower(powerLevel);//at max speed (-1 to 1)
                frontRightDrive.setPower(powerLevel);
                backLeftDrive.setPower(powerLevel);
                backRightDrive.setPower(powerLevel);
                sleep(sleepNum);

            }else if(gamepad1.left_trigger==0){
                ltPress = false;

            }




            //stick buttons
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



            //dpad
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

            }else if (!gamepad1.dpad_left) {
                dPadLeftPress = false;
            }



            //Bumper
            if (gamepad1.right_bumper && !rBump) {
                ticNum += 10;
                rBump = true;
            }else if (!gamepad1.right_bumper) {
                rBump = false;
            }

            if (gamepad1.left_bumper && !lBump) {
                ticNum -= 10;
                lBump = true;
            }else if (!gamepad1.left_bumper) {
                lBump = false;
            }




            //dump
            if(gamepad2.a && !dumpPressPos){
                position += .05;
                dumpPressPos = true;
            }else if (!gamepad2.a){
                dumpPressPos = false;
            }

            if(gamepad2.b && !dumpPressNeg){
                position -= .05;
                dumpPressNeg = true;
            }else if(!gamepad2.b){
                dumpPressNeg = false;
            }

            if(gamepad2.y){
                position = 0.0;
                dump.setPosition(position);

            }
            if(gamepad2.x){
                dump.setPosition(position);
            }



            telemetry.addData("Position", position);

            telemetry.update();
        }
    }
}