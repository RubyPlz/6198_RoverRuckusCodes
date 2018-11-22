package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="GoldDetectionTest")

public class GoldDetectionTest extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor backRightDrive = null;

    private CRServo intake = null;
    private CRServo upDownBoi = null;
    private GoldAlignDetector detector;

    private double power = .2;

    private boolean golded = false;


    @Override
    public void runOpMode() {

        frontLeftDrive = hardwareMap.get(DcMotor.class, "fleft");frontRightDrive = hardwareMap.get(DcMotor.class, "fright");
        backLeftDrive = hardwareMap.get(DcMotor.class, "bleft");backRightDrive = hardwareMap.get(DcMotor.class, "bright");
        frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);backLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);backRightDrive.setDirection(DcMotor.Direction.REVERSE);
        frontLeftDrive.setPower(0);frontRightDrive.setPower(0);backLeftDrive.setPower(0);backRightDrive.setPower(0);
        intake = hardwareMap.get(CRServo.class, "IntakeServo");
        upDownBoi = hardwareMap.get(CRServo.class, "UpDownBoi");
        intake.setDirection(CRServo.Direction.FORWARD);


        telemetry.addData("Robot", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeftDrive.setPower(power);
        frontRightDrive.setPower(power);
        backLeftDrive.setPower(power);
        backRightDrive.setPower(power);
        telemetry.addData("Status", "DogeCV 2018.0 - Gold Align Example");

        detector = new GoldAlignDetector();
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        detector.useDefaults();

        // Optional Tuning
        detector.alignSize = 200; // How wide (in pixels) is the range in which the gold object will be aligned. (Represented by green bars in the preview)
        detector.alignPosOffset = 0; // How far from center frame to offset this alignment zone.
        detector.downscale = 0.4; // How much to downscale the input frames

        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        //detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
        detector.maxAreaScorer.weight = 0.005;

        detector.ratioScorer.weight = 5;
        detector.ratioScorer.perfectRatio = 1.0;

        detector.enable();

        //yes
        move(350, 1500);
        turn(520,1500);
        sleep(1000);
        if(detector.getAligned()){
            turn(-520, 1500);
            move(350, 1500);
            move(-350, 1500);
            golded = true;
        }
        else{
            move(-410, 1500);
            sleep(1000);
            if(detector.getAligned() && !golded) {
                turn(-520, 1500);
                move(350, 1500);
                move(-350, 1500);
            }
            else{
                move(830, 2000);
                turn(-520, 1500);
                move(350, 1500);
                move(-350, 1500);
            }
        }

        //touch

        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        sleep(10000);

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();

    }


    public void turn(int value, int sleepNum){
        frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeftDrive.setTargetPosition(-value);
        frontRightDrive.setTargetPosition(value);
        backLeftDrive.setTargetPosition(-value);
        backRightDrive.setTargetPosition(value);
        sleep(sleepNum);

    }

    public void move(int value,int sleepNum){
        frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeftDrive.setTargetPosition(value);
        frontRightDrive.setTargetPosition(value);
        backLeftDrive.setTargetPosition(value);
        backRightDrive.setTargetPosition(value);
        sleep(sleepNum);

    }
}
