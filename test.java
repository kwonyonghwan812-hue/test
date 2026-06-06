package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "basic_programing")
public class _2026_04_10_kim_tae extends LinearOpMode {
    //motor define(?)
    private DcMotor front_left;
    private DcMotor front_right;
    private DcMotor back_left;
    private DcMotor back_right;
    private DcMotor entering;

    //servo define(?)
    private Servo leftservo;
    private Servo rightservo;
    
    // 모드 설정 , 오류 시 public 적으세요
    bool pass;
    int mode = 0;
    float verticalInput, horizontalInput,rotation, maxSpeed = 0.5, sensitive = 0.5;

    
    @Override
    public void runOpMode() {
        /*
        gamepad1 = new FTCGamepad();
        keyboard = new FTCKeyboard();
        */

        //motor mapping
        front_left = hardwareMap.get(DcMotor.class, "front_left");
        front_right = hardwareMap.get(DcMotor.class, "front_right");
        back_left = hardwareMap.get(DcMotor.class, "back_left");
        back_right = hardwareMap.get(DcMotor.class, "back_right");
        entering = hardwareMap.get(DcMotor.class, "entering");
        
        //servo mapping
        leftservo = hardwareMap.get(Servo.class, "leftservo");
        rightservo = hardwareMap.get(Servo.class, "rightservo");

        waitForStart();

        // init setting
        front_left.setDirection(DcMotor.Direction.REVERSE);
        front_right.setDirection(DcMotor.Direction.REVERSE); //2026-05-30 2차, 초기 태스트때 추가됨
        
        //entering.setDirection(DcMotor.Direction.REVERSE); 2026-05-30 1차,초기 태스트때 비정상 동작
        leftservo.setDirection(Servo.Direction.REVERSE);
        
        
        leftservo.setPosition(0);
        rightservo.setPosition(0);

        while (opModeIsActive()) {
            //control
            
            //대각선
            if(mode == 0){ // 
                //오른쪽 위
                if(gamepad1.dpad_up && gamepad1.dpad_right){
                    front_left.setPower(1);
                    front_right.setPower(0);
                    back_left.setPower(0);
                    back_right.setPower(1);
                }
                //왼쪽 위
                else if(gamepad1.dpad_up && gamepad1.dpad_left){
                    front_left.setPower(0);
                    front_right.setPower(1);
                    back_left.setPower(1);
                    back_right.setPower(0);
                }
                //오른쪽 아래
                else if(gamepad1.dpad_down && gamepad1.dpad_right){
                    //2026-5-30 3차, 초기 테스트 때 왼쪽 아래와 코드 바꿈 (문제 발생됨)
                    front_left.setPower(0);
                    front_right.setPower(-1);
                    back_left.setPower(-1);
                    back_right.setPower(0);
                }
                //왼쪽 아래
                else if(gamepad1.dpad_down && gamepad1.dpad_left){
                    //2026-5-30 3차, 초기 테스트 때 오른쪽 아래와 코드 바꿈 (문제 발생됨)
                    front_left.setPower(-1);
                    front_right.setPower(0);
                    back_left.setPower(0);
                    back_right.setPower(-1);
                }

                //기본 이동

                //전진
                else if (gamepad1.dpad_up) {
                    front_left.setPower(1);
                    front_right.setPower(1);
                    back_left.setPower(1);
                    back_right.setPower(1);                
                }
                //후진
                else if (gamepad1.dpad_down) {
                    front_left.setPower((-1));
                    front_right.setPower((-1));
                    back_left.setPower(-1);
                    back_right.setPower(-1);
                }
                //좌회전
                else if (gamepad1.dpad_left) {
                    front_left.setPower((-1));
                    front_right.setPower(1);
                    back_left.setPower((-1));
                    back_right.setPower(1);
                }
                //우회전
                else if (gamepad1.dpad_right) {
                    front_left.setPower(1);
                    front_right.setPower((-1));
                    back_left.setPower(1);
                    back_right.setPower((-1));
                }
                //왼쪽 수직
                else if(gamepad1.a) {
                    front_left.setPower(-1);
                    front_right.setPower(1);
                    back_left.setPower(1);
                    back_right.setPower(-1);
                }
                //오른쪽 수직
                else if(gamepad1.b) {
                    front_left.setPower(1);
                    front_right.setPower((-1));
                    back_left.setPower(-1);
                    back_right.setPower(1);
                }
                else {
                    front_left.setPower(0);
                    front_right.setPower(0);
                    back_left.setPower(0);
                    back_right.setPower(0);
                }
            }
            
            //조
            else if(mode == 1) {
                horizontalInput = gamepad1.left_stick_x; // 수평 입력
                verticalInput = -(gamepad1.left_stick_y;) // 수직 입력 
                rotation = gamepad1.right_stick_x; // 회전 방향 입력
                /* 
                verticalnput의 값에 음수를 곱한 까닭은
                gamepad1.letf_stick_y이 위 아래가 음수가 이미 곱해진 상태로 참조되기 때문에
                음수를 다시 곱해 자동차 앞 뒤 조작을 수월히 하기 위해서이다.
                */
                

                //오른쪽 위
                if(verticalInput > sensitive && horizontalInput > sensitive){
                    front_left.setPower(1);
                    front_right.setPower(0);
                    back_left.setPower(0);
                    back_right.setPower(1);
                }
                //왼쪽 위
                else if(verticalInput > sensitive && horizontalInput < sensitive){
                    front_left.setPower(0);
                    front_right.setPower(1);
                    back_left.setPower(1);
                    back_right.setPower(0);
                }
                //오른쪽 아래
                else if(verticalInput < sensitive && horizontalInput > sensitive){
                    front_left.setPower(0);
                    front_right.setPower(-1);
                    back_left.setPower(-1);
                    back_right.setPower(0);
                }
                //왼쪽 아래
                else if(verticalInput < sensitive && horizontalInput < sensitive){
                    front_left.setPower(-1);
                    front_right.setPower(0);
                    back_left.setPower(0);
                    back_right.setPower(-1);
                }

                //기본 이동

                //전진
                else if (verticalInput > sensitive) {
                    front_left.setPower(1);
                    front_right.setPower(1);
                    back_left.setPower(1);
                    back_right.setPower(1);                
                }
                //후진
                else if (verticalInput < sensitive) {
                    front_left.setPower((-1));
                    front_right.setPower((-1));
                    back_left.setPower(-1);
                    back_right.setPower(-1);
                }
                //좌회전
                else if (rotation < sensitive) {
                    front_left.setPower((-1));
                    front_right.setPower(1);
                    back_left.setPower((-1));
                    back_right.setPower(1);
                }
                //우회전
                else if (rotation > sensitive) {
                    front_left.setPower(1);
                    front_right.setPower((-1));
                    back_left.setPower(1);
                    back_right.setPower((-1));
                }
                //왼쪽 수직
                else if(horizontalInput < sensitive) {
                    front_left.setPower(-1);
                    front_right.setPower(1);
                    back_left.setPower(1);
                    back_right.setPower(-1);
                }
                //오른쪽 수직
                else if(horizontalInput > sensitive) {
                    front_left.setPower(1);
                    front_right.setPower((-1));
                    back_left.setPower(-1);
                    back_right.setPower(1);
                }
                else {
                    front_left.setPower(0);
                    front_right.setPower(0);
                    back_left.setPower(0);
                    back_right.setPower(0);
                }
            }

            //발사 코드 
            if (gamepad1.x) {
                    entering.setPower(1);
                    //entering2.setPower(1); 보조모터(필요없음)
                    sleep(3000); //모터 가속 시간

                    leftservo.setPosition(180);
                    rightservo.setPosition(180);
                    sleep(2000); //서보 조정 및 발사 시간
                    
                    entering.setPower(0);
                    //entering2.setPower(0); 보조모터(필요없음)

                    leftservo.setPosition(0);
                    rightservo.setPosition(0);
                    sleep(2000); //서보 조정 시간
            }
        }
    }
}

