package com.team2073.robot.Subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.team2073.common.periodic.AsyncPeriodicRunnable;
import com.team2073.robot.ApplicationContext;
import edu.wpi.first.wpilibj.Joystick;

import com.team2073.common.util.*;


public class SimpleSubsystem implements AsyncPeriodicRunnable {
    private final ApplicationContext appCTX = ApplicationContext.getInstance();
    private final Joystick controller =  appCTX.getController();
    private final CANSparkMax motor = appCTX.getMotor();
    private SimpleSubsystemState currentState = SimpleSubsystemState.AXIS;

    public double CruiseOutput = 0;
    public double output = 0;
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
                System.out.println("Pulse is on");
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
                System.out.println("Cruise is On");
                output = CruiseOutput;
                System.out.println(CruiseOutput);
                double Override = Math.abs(getAxisOutput());
                if (Override > CruiseOutput){
                    output = Override;
                }
                break;
            case Revolutions:
                System.out.println("Revolutions is on");
                double EwwwBusinessTeam = motor.getEncoder().getPosition();
                output = 0.5;
                if (EwwwBusinessTeam >= 3000 && 3500>= EwwwBusinessTeam){
                    output = 0;
                    System.out.println("ur mom");
                }
                System.out.println(EwwwBusinessTeam + "revs");
                break;
            case Reset:
                System.out.println("resetting");
                System.out.println(motor.getEncoder().getPosition() + "position");
                if (motor.getEncoder().getPosition() > 0) {
                    output = -0.3;
                    if (Math.abs(getAxisOutput()) > 0.1){
                        output = getAxisOutput();
                        System.out.println("user set speed");
                    }
                    if (motor.getEncoder().getPosition() <= 0 && -10 <= motor.getEncoder().getPosition()){
                        output = 0;
                        System.out.println("ethan is dumb");
                    }
                    break;
                }
            }
        if (0.2 > Math.abs(output)) {
            output = 0;
        }else if (output < -0.8) {
            output = -0.8;
        }else if (output > 0.8) {
            output = 0.8;
        }
        System.out.println(output);
        //motor.set(output);
    }

    public void setCurrentState(SimpleSubsystemState currentState) {
        this.currentState = currentState;
    }

    private double getAxisOutput() {
        return -controller.getRawAxis(1);
    }

    public void setCruiseOutput() {
        CruiseOutput = output;
    }

    public enum SimpleSubsystemState {
        AXIS,
        HALF_POWER,
        STOP,
        Cruise,
        Revolutions,
        Reset,
        PULSE

    }
}
