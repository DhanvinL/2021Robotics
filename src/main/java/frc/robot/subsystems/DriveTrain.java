package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase
{

    
    private  SpeedControllerGroup left, right;
    private  DifferentialDrive drive;

    public DriveTrain(SpeedController left2, SpeedController right2, DifferentialDrive drive)
         {
        this.left = (SpeedControllerGroup) left2;
        this.right = (SpeedControllerGroup) right2;
        this.drive = drive;

    }

    public void takeJoystickInputs( Joystick joy) {

        drive.arcadeDrive(joy.getY(),joy.getZ());
    }

    public void stop() {
        left.set(0);
        right.set(0);
    }

    public void tankDrive( double leftSpeed,  double rightSpeed)
{
    left.set(leftSpeed);
    left.set(rightSpeed);
}

    @Override
public void periodic()
{

}

}