/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

// import necessary packages
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {
  // instantiate subsystems
  public static DriveSubsystem2 s_drive = new DriveSubsystem2();
  public static PneumaticSubsystem s_hatchExtend = new PneumaticSubsystem();
  public static HatchIntakeSubsystem s_hatch = new HatchIntakeSubsystem();
  public static CargoSubsystem s_cargo = new CargoSubsystem();
  public static VisionSubsystem s_vision = new VisionSubsystem();
  public static VisionAlignSubsystem s_visionAlign = new VisionAlignSubsystem();
  public static ExampleSubsystem s_subsystem = new ExampleSubsystem();

  // instantiate commands
  public static Drive2 c_drive = new Drive2();
  public static ActuatePneumatic c_hatchExtend = new ActuatePneumatic();
  public static HatchIntake c_hatch = new HatchIntake();
  public static CargoIntake c_cargo = new CargoIntake();
  public static Vision c_vision = new Vision();
  public static VisionAlign c_visionAlign = new VisionAlign();

  public static OI oi;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    oi = new OI();
    m_chooser.setDefaultOption("default auto", new ExampleCommand());
    SmartDashboard.putData("auto mode", m_chooser);
  
    //s_vision = new VisionSubsystem();
    //c_vision = new Vision();
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();

    c_drive.start();
    c_hatchExtend.start();
    c_vision.start();
    c_visionAlign.start();
    c_hatch.start();
    c_cargo.start();
  }

  @Override
  public void teleopInit() {
    // cancel autonomous when teleop enables
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

    c_drive.start();
    c_hatchExtend.start();
    c_vision.start();
    c_visionAlign.start();
    c_hatch.start();
    c_cargo.start();
  }

  @Override
  public void testPeriodic() {
  }
}
