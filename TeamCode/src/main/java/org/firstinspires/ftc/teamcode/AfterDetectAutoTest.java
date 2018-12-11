package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;




    @TeleOp(name="AfterDetectAutoTest")
    public class AfterDetectAutoTest extends LinearOpMode {
        // Declare OpMode members.
        private ElapsedTime runtime = new ElapsedTime();
        private DcMotor frontLeftDrive = null;
        private DcMotor frontRightDrive = null;
        private DcMotor backLeftDrive = null;
        private DcMotor backRightDrive = null;
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
        private int tic1 = 2000;
        private int tic2 = 2000;
        private int tic3 = 2000;
        private int ticCounter = 1;

        private int sleepNum = 2000;
        

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
            
            telemetry.addData("Robot", "Initialized");
            telemetry.update();
            // Wait for the game to start (driver presses PLAY)
            waitForStart();
            runtime.reset();


            //PLAY
            while (opModeIsActive()) {
                
                telemetry.addData("Tic 1", tic1);
                telemetry.addData("Tic 2", tic2);
                telemetry.addData("Tic 3", tic3);
                telemetry.addData("Tic:", ticCounter);
                telemetry.addData("Power Level", "Power Level (%.2f)", powerLevel);
                telemetry.addData("Sleep Number",sleepNum);
                
                //buttons
                if (gamepad1.y && !yPress) {
                    yPress = true;
                    stopMotors();
                    frontLeftDrive.setPower(powerLevel);//at max speed (-1 to 1)
                    frontRightDrive.setPower(powerLevel);
                    backLeftDrive.setPower(powerLevel);
                    backRightDrive.setPower(powerLevel);
                    move(350, 1500);
                    turnL();
                    turnR();
                    move(350, 1500);
                    move(-350, 1500);
                    turnL();
                    move(tic1, sleepNum);
                    turnHL();
                    move(tic2, sleepNum);
                    move(-tic3, sleepNum);
                }
                else if(!gamepad1.y) {
                    yPress = false;
                }

                if (gamepad1.x && !xPress) {
                    xPress = true;
                    stopMotors();
                    frontLeftDrive.setPower(powerLevel);//at max speed (-1 to 1)
                    frontRightDrive.setPower(powerLevel);
                    backLeftDrive.setPower(powerLevel);
                    backRightDrive.setPower(powerLevel);
                    move(350, 1500);
                    turnL();
                    move(-410, 1500);
                    move(900, 2000);
                    turnR();
                    move(350, 1500);
                    move(-350, 1500);
                    turnL();
                    move(tic1, sleepNum);
                    turnHL();
                    move(tic2, sleepNum);
                    move(-tic3, sleepNum);
                }else if(!gamepad1.x) {
                    xPress = false;
                }

                if (gamepad1.b && !bPress) {
                    bPress = true;
                    stopMotors();
                    frontLeftDrive.setPower(powerLevel);//at max speed (-1 to 1)
                    frontRightDrive.setPower(powerLevel);
                    backLeftDrive.setPower(powerLevel);
                    backRightDrive.setPower(powerLevel);
                    move(350, 1500);
                    turnL();
                    move(-410, 1500);
                    turnR();
                    move(350, 1500);
                    move(-350, 1500);
                    turnL();
                    move(tic1, sleepNum);
                    turnHL();
                    move(tic2, sleepNum);
                    move(-tic3, sleepNum);
                }else if(!gamepad1.b) {
                    bPress = false;
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
                
                if(gamepad1.a && !aPress && ticCounter < 3){
                    ticCounter++;
                    aPress = true;
                }
                else if(gamepad1.a && !aPress && ticCounter == 3){
                    ticCounter = 1;
                    aPress = true;
                } else if (!gamepad1.a) {
                    aPress = false;
                }
                
                if(ticCounter == 1){
                    //dpad
                    if (gamepad1.dpad_up && !dPadUpPress) {
                        tic1 += 500;
                        dPadUpPress = true;
                    } else if (!gamepad1.dpad_up) {
                        dPadUpPress = false;
                    }
                    if (gamepad1.dpad_down && !dPadDownPress) {
                        tic1 -= 500;
                        dPadDownPress = true;
                    } else if (!gamepad1.dpad_down) {
                        dPadDownPress = false;
                    }
                    if (gamepad1.dpad_right && !dPadRightPress) {
                        tic1 += 50;
                        dPadRightPress = true;
                    } else if (!gamepad1.dpad_right) {
                        dPadRightPress = false;
                    }
                    if (gamepad1.dpad_left && !dPadLeftPress) {
                        tic1 -= 50;
                        dPadLeftPress = true;

                    }else if (!gamepad1.dpad_left) {
                        dPadLeftPress = false;
                    }



                    //Bumper
                    if (gamepad1.right_bumper && !rBump) {
                        tic1 += 10;
                        rBump = true;
                    }else if (!gamepad1.right_bumper) {
                        rBump = false;
                    }

                    if (gamepad1.left_bumper && !lBump) {
                        tic1 -= 10;
                        lBump = true;
                    }else if (!gamepad1.left_bumper) {
                        lBump = false;
                    }

                }
                else if(ticCounter == 2){
                    //dpad
                    if (gamepad1.dpad_up && !dPadUpPress) {
                        tic2 += 500;
                        dPadUpPress = true;
                    } else if (!gamepad1.dpad_up) {
                        dPadUpPress = false;
                    }
                    if (gamepad1.dpad_down && !dPadDownPress) {
                        tic2 -= 500;
                        dPadDownPress = true;
                    } else if (!gamepad1.dpad_down) {
                        dPadDownPress = false;
                    }
                    if (gamepad1.dpad_right && !dPadRightPress) {
                        tic2 += 50;
                        dPadRightPress = true;
                    } else if (!gamepad1.dpad_right) {
                        dPadRightPress = false;
                    }
                    if (gamepad1.dpad_left && !dPadLeftPress) {
                        tic2 -= 50;
                        dPadLeftPress = true;

                    }else if (!gamepad1.dpad_left) {
                        dPadLeftPress = false;
                    }



                    //Bumper
                    if (gamepad1.right_bumper && !rBump) {
                        tic2 += 10;
                        rBump = true;
                    }else if (!gamepad1.right_bumper) {
                        rBump = false;
                    }

                    if (gamepad1.left_bumper && !lBump) {
                        tic2 -= 10;
                        lBump = true;
                    }else if (!gamepad1.left_bumper) {
                        lBump = false;
                    }

                }
                else if(ticCounter == 3) {
                    //dpad
                    if (gamepad1.dpad_up && !dPadUpPress) {
                        tic3 += 500;
                        dPadUpPress = true;
                    } else if (!gamepad1.dpad_up) {
                        dPadUpPress = false;
                    }
                    if (gamepad1.dpad_down && !dPadDownPress) {
                        tic3 -= 500;
                        dPadDownPress = true;
                    } else if (!gamepad1.dpad_down) {
                        dPadDownPress = false;
                    }
                    if (gamepad1.dpad_right && !dPadRightPress) {
                        tic3 += 50;
                        dPadRightPress = true;
                    } else if (!gamepad1.dpad_right) {
                        dPadRightPress = false;
                    }
                    if (gamepad1.dpad_left && !dPadLeftPress) {
                        tic3 -= 50;
                        dPadLeftPress = true;

                    }else if (!gamepad1.dpad_left) {
                        dPadLeftPress = false;
                    }



                    //Bumper
                    if (gamepad1.right_bumper && !rBump) {
                        tic3 += 10;
                        rBump = true;
                    }else if (!gamepad1.right_bumper) {
                        rBump = false;
                    }

                    if (gamepad1.left_bumper && !lBump) {
                        tic3 -= 10;
                        lBump = true;
                    }else if (!gamepad1.left_bumper) {
                        lBump = false;
                    }

                }
                telemetry.update();
            }
        }
        public void turn(int value, int sleepNum){
            setPower(.2);
            frontLeftDrive.setTargetPosition(-value);
            frontRightDrive.setTargetPosition(value);
            backLeftDrive.setTargetPosition(-value);
            backRightDrive.setTargetPosition(value);
            frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(sleepNum);
            stopMotors();

        }

        public void move(int value,int sleepNum){
            frontLeftDrive.setTargetPosition(value);
            frontRightDrive.setTargetPosition(value);
            backLeftDrive.setTargetPosition(value);
            backRightDrive.setTargetPosition(value);
            frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(sleepNum);
            stopMotors();

        }
        public void stopMotors() {
            frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        public void turnL(){
            turn(520,1100);
        }
        public void turnR(){
            turn(-520,1100);
        }
        public void turnHL(){
            turn(280,1000);
        }
        public void turnHR(){
            turn(-280,1000);
        }
        public void setPower(double p){
            frontLeftDrive.setPower(p);//at max speed (-1 to 1)
            frontRightDrive.setPower(p);
            backLeftDrive.setPower(p);
            backRightDrive.setPower(p);
        }
    }

