package org.usfirst.frc.team237.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.*;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
/**
 *
 */
public class Pod extends Subsystem {
	public CANTalon drive;
	public CANTalon steer; 
	public double targetPosition; 
	public double targetSpeed; 
	public int podNumber; 
	public Pod(int driveTalon, int steeringTalon, int podNumber){	
		drive = new CANTalon(driveTalon);
		steer = new CANTalon(steeringTalon);
		steer.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		steer.reverseSensor(false);
		steer.setP(0.2);
		steer.setI(0);
		steer.setD(0);
		steer.setF(0);
		steer.setProfile(0);
		steer.configNominalOutputVoltage(+ 0.0, - 0.0);
		steer.configPeakOutputVoltage(+ 3f, - 3f);
		steer.setAllowableClosedLoopErr(0);
		zeroSensorsAndThrottle(); 
		drive.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		drive.configNominalOutputVoltage(+ 0.0, - 0.0);
		drive.configPeakOutputVoltage(+ 12.0, - 0.0);
		drive.setProfile(0);
		drive.setP(0.2);
		drive.setI(0);
		drive.setD(0);
		drive.setF(0);
		drive.reverseSensor(false);
		this.podNumber = podNumber; 
		
		
	}
	public void zeroSensorsAndThrottle(){
		steer.setPosition(0);
		targetPosition = 0; 
		steer.changeControlMode(TalonControlMode.PercentVbus);
		steer.set(0);
		drive.setPosition(0);
		targetSpeed = 0;
		drive.changeControlMode(TalonControlMode.PercentVbus);
		drive.set(0);
		
	
	}
	public void enableClosedLoopSpeed(){
		drive.setVoltageRampRate(0);
		drive.changeControlMode(TalonControlMode.Speed);
		drive.set(targetSpeed);
		
	}
	public void enableClosedLoopAngle(){
		steer.setVoltageRampRate(0);
		steer.changeControlMode(TalonControlMode.Position);
		steer.set(targetPosition);
	
	}
    public void setSteeringAngle(double angle) {
    	targetPosition = angle;
    	enableClosedLoopAngle();
    	SmartDashboard.putNumber("Pod" + podNumber + "/Current Angle", steer.get());
    	SmartDashboard.putNumber("Pod" + podNumber + "/Tagret Angle", targetPosition);
    	
    }

	public void setWheelSpeed(double speed){
		SmartDashboard.putNumber("Pod" + podNumber + "/Motor Speed", drive.getSpeed());
		SmartDashboard.putNumber("Pod" + podNumber + "/Target Speed", targetSpeed);
		targetSpeed = speed;
		enableClosedLoopSpeed();
		
	}

	public void intiDefaultCommand() {
		
	}

@Override
protected void initDefaultCommand() {
	// TODO Auto-generated method stub
	
}
}