package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.*;

@TeleOp(name="TeleOp")
public class AARoverRuckusTeleOp extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor backRightDrive = null;


    private CRServo intake = null;//power pos is outtake, power neg is intake
    private boolean outTakeOn = false;
    private boolean inTakeOn = false;
    //both false means off
    //outTakeOn true, inTakeOn false, servo is intaking
    //outTakeOn false, inTakeOn true, servo is outaking
    //both true means code has broken
    //private CRServo upDownBoi = null;

    private DcMotor upDownBoi = null;
    private double intakePower = 0;
    private AnalogInput pot;
    private int intakeState = 2;

    private DcMotor horiSlide = null;
    private DcMotor vertSlide = null;
    private Servo dump = null;

    private DcMotor actuator = null;


    private double turnNum = 0.0;
    private boolean dumpPressPos = false;
    private boolean dumpPressNeg = false;

    //Drive code stuff
    double r;
    double robotAngle;
    double rightX;
    double fL;
    double fR;
    double bL;
    double bR;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void runOpMode() throws InterruptedException {
        frontLeftDrive = hardwareMap.get(DcMotor.class, "fleft");
        frontRightDrive = hardwareMap.get(DcMotor.class, "fright");
        backLeftDrive = hardwareMap.get(DcMotor.class, "bleft");
        backRightDrive = hardwareMap.get(DcMotor.class, "bright");
        upDownBoi = hardwareMap.get(DcMotor.class, "upDownBoi");
        pot = hardwareMap.get(AnalogInput.class, "pot");
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);
        upDownBoi.setDirection(DcMotor.Direction.FORWARD);
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);

        intake = hardwareMap.get(CRServo.class, "IntakeServo");
        //upDownBoi = hardwareMap.get(CRServo.class, "UpDownBoi");


        horiSlide = hardwareMap.get(DcMotor.class, "HoriSlide");
        horiSlide.setDirection(DcMotor.Direction.REVERSE);
        vertSlide = hardwareMap.get(DcMotor.class, "VertSlide");
        vertSlide.setDirection(DcMotor.Direction.FORWARD);


        dump = hardwareMap.get(Servo.class, "Dump");
        dump.setPosition(.055);
        //dump.setDirection(Servo.Direction.FORWARD);


        actuator = hardwareMap.get(DcMotor.class, "Actuator");
        actuator.setDirection(DcMotor.Direction.FORWARD);


        telemetry.addData("Robot", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();


        //PLAY
        while (opModeIsActive()) {
            //DONT TOUCH THIS
            r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            robotAngle = Math.atan2(gamepad1.left_stick_y, q-gamepad1.left_stick_x) - Math.PI / 4;
            rightX = .6 * -gamepad1.right_stick_x;
            fL = r * Math.cos(robotAngle) + rightX;
            fR = r * Math.sin(robotAngle) - rightX;
            bL = r * Math.sin(robotAngle) + rightX;
            bR = r * Math.cos(robotAngle) - rightX;
            frontLeftDrive.setPower(fL * .75);
            frontRightDrive.setPower(fR * .75);
            backLeftDrive.setPower(bL * .75);
            backRightDrive.setPower(bR * .75);
            telemetry.addData("Motors", "front left (%.2f), front right (%.2f), back left (%.2f), back right (%.2f)", frontLeftDrive.getPower(), frontRightDrive.getPower(), backLeftDrive.getPower(), backRightDrive.getPower());
            telemetry.addData("Stick", "Left stick y (%.2f), Left stick x (%.2f), Right stick x (%.2f) Right Stick Value (%.2f)", -gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x, rightX);
            telemetry.addData("Direct Values", "fL (%.2f), fR (%.2f), bL (%.2f), bR (%.2f)", fL, fR, bL, bR);
            telemetry.addData("Angle", "Angle (%.2f)", robotAngle / Math.PI);
            telemetry.addData("Pot Reading", pot.getVoltage());
            telemetry.addData("Intake State", intakeState);
            //OK YOU GOOD NOW


            //Intake
            if (gamepad1.a) {//intake
                if (!inTakeOn && !outTakeOn) {//if servo off, intake
                    intake.setPower(-1.0);
                    inTakeOn = true;
                }
                if (outTakeOn) {//if servo outtaking and press a, then intake
                    intake.setPower(-1.0);
                    inTakeOn = true;
                    outTakeOn = false;
                }
            }
            if (gamepad1.b) {//outtake
                if (!inTakeOn && !outTakeOn) {//if servo off, intake
                    intake.setPower(1.0);
                    outTakeOn = true;
                }
                if (inTakeOn) {//if servo intaking and press b, then outtake
                    intake.setPower(1.0);
                    inTakeOn = false;
                    outTakeOn = true;
                }
            }

            if (gamepad1.x) {//stop
                intake.setPower(0);
                inTakeOn = false;
                outTakeOn = false;
            }


            //up down boi
            /**
             if (gamepad1.right_bumper || gamepad1.left_bumper) {
             if (gamepad1.left_bumper) {//neg
             upDownBoi.setPower(-1);
             }
             if (gamepad1.right_bumper) {//pos
             upDownBoi.setPower(1);
             }
             } else {
             upDownBoi.setPower(0);
             }
             **/
            /**
            if (gamepad1.right_bumper || gamepad1.left_bumper) {
                if (gamepad1.left_bumper) {//neg
                    intakeState = 0;
                }
                if (gamepad1.right_bumper) {//pos
                    intakeState = 1;
                }
            }
            if(gamepad1.y){
                intakeState = 2;
            }



            if(intakeState == 1) {

                if(pot.getVoltage() < 1.6){
                    upDownBoi.setPower(.5);
                }

                else if (pot.getVoltage() < 2.75){
                    upDownBoi.setPower(-.02);
                }

                else if(pot.getVoltage() > 2.7 && pot.getVoltage() < 2.87) {
                    upDownBoi.setPower(-.2);
                }

                else if(pot.getVoltage() > 2.87){
                    upDownBoi.setPower(-.6);
                }
            }
            if(intakeState == 0){
                if (pot.getVoltage() > 1.3) {
                    upDownBoi.setPower(-.8);
                }
                else if(pot.getVoltage() > .85){
                    upDownBoi.setPower(-.1);
                }
                else{
                    upDownBoi.setPower(0);
                }
            }
            if(intakeState == 2) {
                upDownBoi.setPower(0);
            }
             **/

            if (pot.getVoltage() > 1.2) {
                if (gamepad1.left_bumper) {//neg
                    upDownBoi.setPower(-.6);
                }
                else if (gamepad1.right_bumper) {//pos
                    upDownBoi.setPower(0);
                }
                else{
                    upDownBoi.setPower(-.1);
                }
            }
            else{
                if(gamepad1.left_bumper){
                    upDownBoi.setPower(-.2);
                }
                else if(gamepad1.right_bumper){
                    upDownBoi.setPower(.35);
                }
                else{
                    upDownBoi.setPower(0);
                }
            }






                //horizontal slide
                if (gamepad1.right_trigger > 0 || gamepad1.left_trigger > 0) {
                    if (gamepad1.right_trigger > 0) {
                        horiSlide.setPower(gamepad1.right_trigger);

                    }
                    if (gamepad1.left_trigger > 0) {
                        horiSlide.setPower(-
                                gamepad1.left_trigger);

                    }

                } else {
                    horiSlide.setPower(0);
                }


                //actuator
                if (gamepad1.dpad_up || gamepad1.dpad_down) {
                    if (gamepad1.dpad_up) {
                        actuator.setPower(-1.0);//neg robot go down
                    }
                    if (gamepad1.dpad_down) {
                        actuator.setPower(1.0);//pos robot go up
                    }
                } else {
                    actuator.setPower(0);
                }


                //VertLift
                if (gamepad2.dpad_up || gamepad2.dpad_down) {
                    if (gamepad2.dpad_up) {
                        vertSlide.setPower(1.0);//pos go up
                    }
                    if (gamepad2.dpad_down) {
                        vertSlide.setPower(-1.0);//neg go down
                    }
                } else {
                    vertSlide.setPower(0);
                }

                //dumps

                if (gamepad2.a) {
                    dump.setPosition(.49);
                }
                if (gamepad2.b) {
                    dump.setPosition(.055);
                }

                if (gamepad2.y) {
                    dump.setPosition(.03);
                }


                telemetry.addData("DumpAngle", turnNum);
                telemetry.addData("IntakePower", intakePower);

                telemetry.update();
            }
        }
    }


