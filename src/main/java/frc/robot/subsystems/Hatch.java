package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Hatch extends SubsystemBase
{
    private final Solenoid solenoid = new Solenoid(Constants.Hatch_PCM);
    private final boolean hatchDirection;
    public Hatch(Solenoid solenoid, boolean hatchDirection)
    {
        this.solenoid = solenoid;
        this.hatchDirection = hatchDirection;
    }

    public void HatchForward()
    {
            solenoid.set(hatchDirection);
    }

	public void HatchMove() {
	}
}