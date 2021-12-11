package com.team2073.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.team2073.common.periodic.AsyncPeriodicRunnable;
import com.team2073.robot.ApplicationContext;
import edu.wpi.first.wpilibj.Joystick;

import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.util.TimerTask;

import com.team2073.common.util.*;


public class SimpleSubsystem implements AsyncPeriodicRunnable {
    private final ApplicationContext appCTX = ApplicationContext.getInstance();
    private final Joystick controller =  appCTX.getController();
    private final CANSparkMax motor = appCTX.getMotor();

    private SimpleSubsystemState currentState = SimpleSubsystemState.AXIS;

    private double output = 0;
    Timer timer = new Timer();
    public SimpleSubsystem() {
        autoRegisterWithPeriodicRunner();
    }
    @Override
    public void onPeriodicAsync() {
        output = getAxisOutput();
        switch (currentState) {
            case AXIS:
                output = getAxisOutput();
                double brakes = controller.getRawAxis(2);
                if (brakes > 0) {
                    output = output -brakes;
                }
                double speeed = controller.getRawAxis(3);
                if (speeed > 0) {
                    output = output + speeed;
                }
                break;
            case HALF_POWER:
                output = 0.5;
                 brakes = controller.getRawAxis(2);
                if (brakes > 0) {
                    output = output -brakes;
                }
                speeed = controller.getRawAxis(3);
                if (speeed > 0) {
                    output = output + speeed;
                }
                break;
            case STOP:
                output = 0;
                break;
            case PULSE:
                System.out.println("Estoy goose");
                if (controller.getRawButtonPressed(5)) {
                    timer.start();
                }
                System.out.println(timer.getElapsedTime());
                if (timer.getElapsedTime() >= 2000 && 5000 >= timer.getElapsedTime()){
                    output = 0.5;
                }
                if (timer.getElapsedTime() > 5000){
                    timer.start();
                }
                break;
            case Cruise:
                double CruiseOutput = output;
                boolean cruising = true;
                System.out.println("monkey nuts");

                   while (cruising){
                       motor.set(CruiseOutput);
                       if (controller.getRawButton(4)){
                           cruising = false;
                           break;
                       } else if (getAxisOutput() > CruiseOutput){
                           break;
                       }
                   cruising = false;
                       motor.set(output);
                   }

        }
        if (output > 0.8) {
            output = 0.8;
        } else if (output < 0.2) {
            output = 0;
        }
        System.out.println(output);
        motor.set(output);
    }

    public void setCurrentState(SimpleSubsystemState currentState) {
        this.currentState = currentState;
    }

    private double getAxisOutput() {
        return -controller.getRawAxis(1);
    }

    public enum SimpleSubsystemState {
        AXIS,
        HALF_POWER,
        STOP,
        Cruise,
        PULSE

    }
}
