package com.team2073.robot.Subsystems;


import com.revrobotics.CANSparkMax;
import com.team2073.common.periodic.AsyncPeriodicRunnable;
import com.team2073.robot.ApplicationContext;
import edu.wpi.first.wpilibj.Joystick;

public class RobotTest implements  AsyncPeriodicRunnable {
    private final ApplicationContext appCTX = ApplicationContext.getInstance();
    private final Joystick controller =  appCTX.getController();
    private final CANSparkMax motor = appCTX.getMotor();
    private final CANSparkMax smotor = appCTX.SMotor();
    private RobotTest.RobotTestState currentState = RobotTestState.RobotTest;
    double TestSpeed = 0;

    public RobotTest() {autoRegisterWithPeriodicRunner();}
    @Override
    public void onPeriodicAsync() {
        switch (currentState) {
            case RobotTest:
              TestSpeed = 1;
                System.out.println("testing");
              break;
            case STOP:
                TestSpeed = 0;
                break;
        }
        motor.set(TestSpeed);
        smotor.set(-TestSpeed);
    }
    public void setCurrentState(RobotTest.RobotTestState currentState) {
        this.currentState = currentState;
    }

    private double getAxisOutput() {
        return -controller.getRawAxis(1);
    }

    public enum RobotTestState {
        RobotTest,
        STOP

    }





}
