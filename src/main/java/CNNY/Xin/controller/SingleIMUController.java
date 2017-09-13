package CNNY.Xin.controller;

import CNNY.Xin.action.SingleIMUAction;
import CNNY.Xin.event.IMUDataUpdateEventListener;
import CNNY.Xin.model.IMUDataModel;
import CNNY.Xin.model.SingleIMUModel;
import CNNY.Xin.view.SingleIMUPanel;

public class SingleIMUController implements IMUDataUpdateEventListener {

	public SingleIMUAction action;
	public SingleIMUModel model;
	public SingleIMUPanel panel;
	
	// serial port MVA
	private SingleIMUSerialPortController serialPortController;
	
	// data display MVA
	private SingleIMUDataDisplayController dataDisplayController;
	
	// phase plain MVA
	private SingleIMUPhasePlainController phasePlainController;

	/**
	 *	Function Info:
	 *		Initialization 
	 */
	public SingleIMUController(
			SingleIMUModel singleIMUModel,
			SingleIMUPanel singleIMUPanel) {

		this.model = singleIMUModel;
		this.panel = singleIMUPanel;
		this.action = new SingleIMUAction(singleIMUModel, singleIMUPanel);

		// serial port
		this.serialPortController = 
				new SingleIMUSerialPortController(
						singleIMUModel.serialPortModel, 
						singleIMUPanel.serialPortPanel);
		
		this.serialPortController.action.imuDataUpdateEventManager.addListener(this);
		
		// data display
		this.dataDisplayController = 
				new SingleIMUDataDisplayController(
						singleIMUModel.dataDisplayModel,
						singleIMUPanel.dataDisplayPanel);
		
		// phase plain
		this.phasePlainController = 
				new SingleIMUPhasePlainController(
						singleIMUModel.phasePlainModel, 
						singleIMUPanel.phasePlainPanel);
		
		initActionListener();
	}

	/**
	 *	Function Info:
	 *		Initiate Action Listeners 
	 */
	private void initActionListener() {
	}

	/**
	 *	Func Info:
	 *		imu data update event listen 
	 */
	@Override
	public void imuDataUpdate(IMUDataModel imuDataModel) {

		dataDisplayController.imuDataUpdate(imuDataModel);
		phasePlainController.imuDataUpdate(imuDataModel);
	}

}
