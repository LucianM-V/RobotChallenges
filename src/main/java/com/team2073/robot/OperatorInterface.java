package com.team2073.robot;

import com.team2073.robot.Commands.Cruise;
import com.team2073.robot.Commands.HalfPowerCommand;
import com.team2073.robot.Commands.PULSE;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OperatorInterface {
    private final ApplicationContext appCTX = ApplicationContext.getInstance();
    private final Joystick controller = appCTX.getController();

    private final JoystickButton a = new JoystickButton(controller, 1 );
    private final JoystickButton b = new JoystickButton(controller, 5);
    private final JoystickButton c = new JoystickButton(controller,4);

    public void init(){
        a.whileHeld(new HalfPowerCommand());
        b.whileHeld(new PULSE());
        c.toggleWhenPressed(new Cruise());

    }


}
