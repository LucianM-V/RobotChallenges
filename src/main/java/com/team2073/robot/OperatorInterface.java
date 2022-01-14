package com.team2073.robot;

import com.team2073.robot.Commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OperatorInterface {
    private final ApplicationContext appCTX = ApplicationContext.getInstance();
    private final Joystick controller = appCTX.getController();

    private final JoystickButton a = new JoystickButton(controller, 1 );
    private final JoystickButton b = new JoystickButton(controller, 5);
    private final JoystickButton Y = new JoystickButton(controller,4);
    private final JoystickButton d = new JoystickButton(controller,2);
    private final JoystickButton e = new JoystickButton(controller,3);
    private final JoystickButton Rbumper = new JoystickButton(controller, 6);

    public void init(){
        a.whileHeld(new HalfPowerCommand());
        b.whileHeld(new PULSE());
        Y.toggleWhenPressed(new Cruise());
        d.toggleWhenPressed(new Revolutions());
        e.toggleWhenPressed(new Reset());
        Rbumper.whileHeld(new TestCommand());

    }


}
