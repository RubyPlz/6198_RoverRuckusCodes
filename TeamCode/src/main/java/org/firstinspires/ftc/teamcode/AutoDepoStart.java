package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="DepoStart")
public class AutoDepoStart extends LinearOpMode{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor backRightDrive = null;

    private CRServo intake = null;
    private CRServo upDownBoi = null;

    private double power = .2;

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


            move(1340,3000);
            turn(520,3000);
            move(2400,3000);


            frontLeftDrive.setPower(0);
            frontRightDrive.setPower(0);
            backLeftDrive.setPower(0);
            backRightDrive.setPower(0);
            sleep(10000);


                telemetry.addData("Status", "Run Time: " + runtime.toString());
                telemetry.update();

        }

    public void turn(int value,int sleepNum){
        frontLeftDrive.setTargetPosition(-value);
        frontRightDrive.setTargetPosition(value);
        backLeftDrive.setTargetPosition(-value);
        backRightDrive.setTargetPosition(value);
        sleep(sleepNum);
    }

    public void move(int value,int sleepNum){
        frontLeftDrive.setTargetPosition(value);
        frontRightDrive.setTargetPosition(value);
        backLeftDrive.setTargetPosition(value);
        backRightDrive.setTargetPosition(value);
        sleep(sleepNum);

    }
    public void goldFinder(){
        //do a thing

    }
}
