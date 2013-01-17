package test;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import MowerSystem.ChargingUnit;
import MowerSystem.Mower;

public class MowerSystemRunner implements PaintListener, Observer {

	private Mower mower1;
	private Mower mower2;
	private ChargingUnit cu;

	private Display display;
	private Shell shell;

	private Label lblCuStateDesc;
	private Label lblCuState;
	private Label lblCuCurrentMowerDesc;
	private Label lblCuCurrentMower;
	private Label lblM1StateDesc;
	private Label lblM1State;
	private Label lblM2StateDesc;
	private Label lblM2State;
	private Label lblCurrentStep;
	
	static final String DEFAULT_TXT = "n/a";
	private String currentStep;

	public MowerSystemRunner() {
		display = new Display();
		shell = new Shell(display);
		shell.setText("Mower System");
		shell.setSize(380, 200);
		shell.setLocation(300, 300);

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		shell.setLayout(gridLayout);
		lblCuStateDesc = new Label(shell, SWT.NONE);
		lblCuStateDesc.setText("ChargingUnit State:");
		lblCuState = new Label(shell, SWT.BORDER);
		lblCuState.setText(DEFAULT_TXT);
		
		lblCuCurrentMowerDesc = new Label(shell, SWT.NONE);
		lblCuCurrentMowerDesc.setText("Current Mower:");
		lblCuCurrentMower = new Label(shell, SWT.BORDER);
		lblCuCurrentMower.setText(DEFAULT_TXT);
		
		lblM1StateDesc = new Label(shell, SWT.NONE);
		lblM1StateDesc.setText("Mower1 State:");
		lblM1State = new Label(shell, SWT.BORDER);
		lblM1State.setText(DEFAULT_TXT);
		
		lblM2StateDesc = new Label(shell, SWT.NONE);
		lblM2StateDesc.setText("Mower2 State:");
		lblM2State = new Label(shell, SWT.BORDER);
		lblM2State.setText(DEFAULT_TXT);
		
		lblCurrentStep = new Label(shell, SWT.NONE);
		lblCurrentStep.setText(DEFAULT_TXT);

		shell.addPaintListener(this);
		shell.addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent e) {
				e.widget.dispose();
			}
		});

		shell.open();
		startMowerSystem();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

		shutDownMowerSystem();
	}

	private void startMowerSystem() {
		mower1 = new Mower();
		mower1.setName("Mower 1");
		mower1.addObserver(this);

		mower2 = new Mower();
		mower2.setName("Mower 2");
		mower2.addObserver(this);

		cu = new ChargingUnit();
		cu.setMowers(new Mower[] { mower1, mower2 });
		cu.addObserver(this);

		mower1.setChargingUnit(cu);
		mower2.setChargingUnit(cu);

		new Thread() {
			public void run() {
				cu.performInit();
				mower1.performInit();
				mower2.performInit();

				for (int i = 0; i < 50; i++) {
					int random = (((Double) (Math.random() * 100)).intValue() % 3);
					switch (random) {
					case (0):
						currentStep = "perform next step on ChargingUnit";
						cu.perform();
					case (1):
						currentStep = "perform next step on Mower 1";
						mower1.perform();
					case (2):
						currentStep = "perform next step on Mower 2";
						mower2.perform();
					}
				}
			}
		}.start();
	}

	private void shutDownMowerSystem() {
		display.dispose();
		shell.dispose();
		System.exit(0);
	}

	@Override
	public void paintControl(PaintEvent e) {
		if (cu != null) {
			if (cu.getCurrentState() != null) {
				lblCuState.setText(cu.getCurrentState().getClass()
						.getSimpleName());
				lblCuState.pack();
			} else {
				lblCuState.setText(DEFAULT_TXT);
			}
			if (cu.getCurrentMower() != null) {
				lblCuCurrentMower.setText(cu.getCurrentMower().getName());
				lblCuCurrentMower.pack();
			} else {
				lblCuCurrentMower.setText(DEFAULT_TXT);
			}
		}
		if (mower1 != null) {
			if (mower1.getCurrentState() != null) {
				lblM1State.setText(mower1.getCurrentState().getClass()
						.getSimpleName());
				lblM1State.pack();
			} else {
				lblM1State.setText(DEFAULT_TXT);
			}
		}
		if (mower2 != null) {
			if (mower2.getCurrentState() != null) {
				lblM2State.setText(mower2.getCurrentState().getClass()
						.getSimpleName());
				lblM2State.pack();
			} else {
				lblM2State.setText(DEFAULT_TXT);
			}
		}
		if (currentStep != null) {
			lblCurrentStep.setText(currentStep);
			lblCurrentStep.pack();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (!display.isDisposed()) {
			display.syncExec(new Runnable() {

				@Override
				public void run() {
					if (!shell.isDisposed()) {
						shell.redraw();
					}
				}
			});
		}
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new MowerSystemRunner();
	}
}
