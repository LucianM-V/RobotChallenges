package com.team2073.robot.Commands;

import com.team2073.common.command.AbstractLoggingCommand;
import com.team2073.robot.ApplicationContext;
import com.team2073.robot.Subsystems.RobotTest;
import com.team2073.robot.Subsystems.SimpleSubsystem;

public class TestCommand extends AbstractLoggingCommand {
    ApplicationContext appCTX = ApplicationContext.getInstance();
    RobotTest robotTest = appCTX.getRobotTest();

    @Override
    protected void initializeDelegate() {
        robotTest.setCurrentState(RobotTest.RobotTestState.RobotTest);
    }

    @Override
    protected void endDelegate() {
        robotTest.setCurrentState(RobotTest.RobotTestState.STOP);
    }

    @Override
    protected boolean isFinishedDelegate() {
        return false;
    }
}
