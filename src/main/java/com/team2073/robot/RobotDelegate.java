package com.team2073.robot;

import com.team2073.common.robot.AbstractRobotDelegate;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.team2073.common.periodic.AsyncPeriodicRunnable;
import com.team2073.robot.ApplicationContext;
import com.team2073.robot.Subsystems.SimpleSubsystem;
import edu.wpi.first.wpilibj.Joystick;

import com.team2073.common.util.*;

import java.util.Base64;

public class RobotDelegate extends AbstractRobotDelegate {
    public RobotDelegate(double period) {
        super(period);
    }

    private final ApplicationContext appCTX = ApplicationContext.getInstance();
    private final CANSparkMax motor = appCTX.getMotor();
    @Override
    public void robotInit() {
        OperatorInterface oi = new OperatorInterface();
        oi.init();
        motor.getEncoder().setPosition(0);
    }

    @Override
    public void robotPeriodic() {

    }
}
