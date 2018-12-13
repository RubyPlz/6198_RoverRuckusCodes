package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

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
    private CRServo upDownBoi = null;

    private DcMotor horiSlide = null;
    private DcMotor vertSlide = null;
    private Servo dump = null;

    private DcMotor actuator = null;


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

        intake = hardwareMap.get(CRServo.class,"IntakeServo");
        upDownBoi = hardwareMap.get(CRServo.class, "UpDownBoi");

        horiSlide = hardwareMap.get(DcMotor.class, "HoriSlide");
        horiSlide.setPower(0);

        vertSlide = hardwareMap.get(DcMotor.class,"VertSlide");
        vertSlide.setDirection(DcMotor.Direction.FORWARD);

        dump = hardwareMap.get(Servo.class,"Dump");
        dump.setDirection(Servo.Direction.FORWARD);

        actuator = hardwareMap.get(DcMotor.class,"Actuator");
        actuator.setDirection(DcMotor.Direction.FORWARD);





        telemetry.addData("Robot", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();


        //PLAY
        while (opModeIsActive()) {
            //DONT TOUCH THIS
            double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = .6 * gamepad1.right_stick_x;
            double fL = r * Math.cos(robotAngle) + rightX;
            double fR = r * Math.sin(robotAngle) - rightX;
            double bL = r * Math.sin(robotAngle) + rightX;
            double bR = r * Math.cos(robotAngle) - rightX;
            frontLeftDrive.setPower(fL*.75);
            frontRightDrive.setPower(fR*.75);
            backLeftDrive.setPower(bL*.75);
            backRightDrive.setPower(bR*.75);
            telemetry.addData("Motors", "front left (%.2f), front right (%.2f), back left (%.2f), back right (%.2f)", frontLeftDrive.getPower(), frontRightDrive.getPower(), backLeftDrive.getPower(), backRightDrive.getPower());
            telemetry.addData("Stick", "Left stick y (%.2f), Left stick x (%.2f), Right stick x (%.2f) Right Stick Value (%.2f)", -gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x, rightX);
            telemetry.addData("Direct Values", "fL (%.2f), fR (%.2f), bL (%.2f), bR (%.2f)", fL, fR, bL, bR);
            telemetry.addData("Angle", "Angle (%.2f)" ,robotAngle/Math.PI);
            //OK YOU GOOD NOW



            //Intake
            if(gamepad1.a){//intake
                if(!inTakeOn && !outTakeOn){//if servo off, intake
                    intake.setPower(-1.0);
                    inTakeOn = true;
                }
                if(outTakeOn) {//if servo outtaking and press a, then intake
                    intake.setPower(-1.0);
                    inTakeOn = true;
                    outTakeOn = false;
                }
            }
            if(gamepad1.b){//outtake
                if(!inTakeOn && !outTakeOn){//if servo off, intake
                    intake.setPower(1.0);
                    outTakeOn = true;
                }
                if(inTakeOn){//if servo intaking and press b, then outtake
                    intake.setPower(1.0);
                    inTakeOn = false;
                    outTakeOn = true;
                }
            }
            if(gamepad1.x){//stop
                intake.setPower(0);
                inTakeOn = false;
                outTakeOn = false;
            }




            //up down boi
            if(gamepad1.right_bumper || gamepad1.left_bumper) {
                if (gamepad1.left_bumper) {//neg
                    upDownBoi.setPower(-1);
                }
                if (gamepad1.right_bumper) {//pos
                    upDownBoi.setPower(1);
                }
            }else{
                upDownBoi.setPower(0);
            }





            //horizontal slide
            if(gamepad1.right_trigger>0 || gamepad1.left_trigger>0){
                if(gamepad1.right_trigger>0){
                    horiSlide.setPower(-gamepad1.right_trigger);

                }
                if(gamepad1.left_trigger>0){
                    horiSlide.setPower(gamepad1.left_trigger);

                }

            }else{
                horiSlide.setPower(0);
            }


            //TURBO MODE
            if(gamepad1.y) {
                frontLeftDrive.setPower(1);
                frontRightDrive.setPower(1);
                backLeftDrive.setPower(1);
                backRightDrive.setPower(1);
            }



            //latch
            if(gamepad1.dpad_up || gamepad1.dpad_down) {
                if(gamepad1.dpad_up) {
                    actuator.setPower(-1.0);//neg robot go down
                }
                if(gamepad1.dpad_down) {
                    actuator.setPower(1.0);//pos robot go up
                }
            }else{
                actuator.setPower(0);
            }





            telemetry.update();
        }
    }
}