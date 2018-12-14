package org.firstinspires.ftc.teamcode;

        import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.CRServo;
        import com.qualcomm.robotcore.hardware.ColorSensor;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.DcMotorSimple;
        import com.qualcomm.robotcore.hardware.DistanceSensor;
        import com.qualcomm.robotcore.hardware.I2cDevice;
        import com.qualcomm.robotcore.hardware.Servo;
        import com.qualcomm.robotcore.util.ElapsedTime;
        import com.qualcomm.robotcore.util.Range;
        import com.disnodeteam.dogecv.CameraViewDisplay;
        import com.disnodeteam.dogecv.DogeCV;
        import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
        import com.disnodeteam.dogecv.detectors.roverrukus.SamplingOrderDetector;
        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@Autonomous(name="CraterStart")
public class AutoCraterStart extends LinearOpMode{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor backRightDrive = null;

    private CRServo intake = null;
    private CRServo upDownBoi = null;
    private GoldAlignDetector detector;

    private double power = .2;

    private DcMotor actuator = null;


        @Override
        public void runOpMode() {
            //initialize
            frontLeftDrive = hardwareMap.get(DcMotor.class, "fleft");frontRightDrive = hardwareMap.get(DcMotor.class, "fright");
            backLeftDrive = hardwareMap.get(DcMotor.class, "bleft");backRightDrive = hardwareMap.get(DcMotor.class, "bright");
            frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);backLeftDrive.setDirection(DcMotor.Direction.FORWARD);
            frontRightDrive.setDirection(DcMotor.Direction.REVERSE);backRightDrive.setDirection(DcMotor.Direction.REVERSE);
            frontLeftDrive.setPower(0);frontRightDrive.setPower(0);backLeftDrive.setPower(0);backRightDrive.setPower(0);
            intake = hardwareMap.get(CRServo.class, "IntakeServo");
            upDownBoi = hardwareMap.get(CRServo.class, "UpDownBoi");
            intake.setDirection(CRServo.Direction.FORWARD);
            actuator = hardwareMap.get(DcMotor.class,"Actuator");
            actuator.setDirection(DcMotor.Direction.FORWARD);
            telemetry.addData("Robot", "Initialized");
            telemetry.update();





            // Wait for the game to start (driver presses PLAY)
            waitForStart();
            runtime.reset();


            //startup
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




            //Gold stuff
            telemetry.addData("Status", "DogeCV 2018.0 - Gold Align Example");
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
            telemetry.addData("IsAligned" , detector.getAligned());
            telemetry.update();




            //move down
            actuator.setPower(-1.0);
            sleep(8000);
            actuator.setPower(0.0);
            turn(140,300);




            /*main
            move(760,3000);
            move(-350,3000);
            turn(520,3000);
            move(1350,3000);
            turn(280,3000);
            move(1100,3000);
            move(-2100,3000);
            */


            //stop
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

