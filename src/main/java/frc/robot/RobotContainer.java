/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.MoveHatch;
import frc.robot.commands.MoveIntake;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private SpeedController leftUp, leftDown, rightUp, rightDown;
  private SpeedController left, right;
  private DifferentialDrive drive;
  private static DriveTrain driveTrain;

  private SpeedController intakeLeft, intakeRight;
  private Intake intake;

  private static Joystick joy;

  private Button intakeIn, intakeOut;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    leftUp = new SteelTalonsController(Constants.LEFT_UP_PORT, false, 0);
    leftDown = new SteelTalonsController(Constants.LEFT_DOWN_PORT, false, 0);
    rightUp = new SteelTalonsController(Constants.RIGHT_UP_PORT, false, 0);
    leftDown = new SteelTalonsController(Constants.RIGHT_DOWN_PORT, false, 0);

    left = new SpeedControllerGroup(leftUp, leftDown);
    right = new SpeedControllerGroup(rightUp, rightDown);

    drive = new DifferentialDrive(left, right);

    driveTrain = new DriveTrain(left, right, drive);
    driveTrain.setDefaultCommand(new DriveWithJoystick());

    intakeLeft = new SteelTalonsController(4, false, 1);
    intakeRight = new SteelTalonsController(5, false, 1);

    new Intake(intakeLeft, intakeRight);

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    joy = new Joystick(0);
    intakeIn = new JoystickButton(joy, Constants.INTAKE_IN_BUTTON);
    intakeOut = new JoystickButton(joy, Constants.INTAKE_OUT_BUTTON);
    hatch = new JoystickButton(joy, Constants.HATCH_MOVE_BUTTON);

    intakeIn.whileHeld(new MoveIntake(Constants.INTAKE_IN_SPEED));
    intakeOut.whenHeld(new MoveIntake(Constants.INTAKE_OUT_SPEED));
    hatch.whileHeld(new MoveHatch(hatch));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }

  public static DriveTrain getDriveTrain() {
    return driveTrain;
  }

  public Intake getIntake() {
    return intake;
  }



  public static Joystick getJoystick() {
    return joy;
  }


}
