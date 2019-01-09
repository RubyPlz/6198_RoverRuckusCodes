package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="REEE")
@Disabled
public class REEE extends LinearOpMode{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private Servo dump = null;

        @Override
        public void runOpMode() {
            //initialize


            dump = hardwareMap.get(Servo.class,"Dump");
            dump.setDirection(Servo.Direction.FORWARD);

            telemetry.addData("Robot", "Initialized");
            telemetry.update();





            // Wait for the game to start (driver presses PLAY)
            waitForStart();
            runtime.reset();



            dump.setPosition(0);
            sleep(2000);
            dump.setPosition(180);




            telemetry.addData("Status", "Run Time: " + runtime.toString());
                telemetry.update();

        }


}

