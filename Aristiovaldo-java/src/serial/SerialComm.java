package serial;

import gnu.io.CommPortIdentifier;

import gnu.io.NoSuchPortException;

import gnu.io.SerialPort;

import gnu.io.SerialPortEvent;

import gnu.io.SerialPortEventListener;

import java.io.IOException;

import java.io.InputStream;

import java.io.OutputStream;

import java.sql.ResultSet;

import java.util.ArrayList;

import java.util.Iterator;

import javax.swing.JOptionPane;

public class SerialComm implements Runnable, SerialPortEventListener {

	protected String selectedPort;
	private int baudrate;
	private int timeout;
	private CommPortIdentifier commPortIdentifier;
	private SerialPort port;
	private OutputStream serialOutput;
	private InputStream serialInput;
	private boolean portOk;
	private boolean reading = false;
	private boolean writing = false;

	public SerialComm(String PortName, int baudrateSet, int timeoutSet) {
		this.selectedPort = PortName;
		this.baudrate = baudrateSet;
		this.timeout = timeoutSet;
	}

	private void initialize() {
		try {
			CommPortIdentifier portId = null;
			try {
				portId = CommPortIdentifier.getPortIdentifier(this.selectedPort);
			} catch (NoSuchPortException npe) {
				JOptionPane.showMessageDialog(null, "Porta comm não encontrada");
			}

			this.port = (SerialPort) portId.open("Serial Comm", this.baudrate);
			serialOutput = port.getOutputStream();
			serialInput = port.getInputStream();
			port.setSerialPortParams(this.baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);
			port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
			JOptionPane.showMessageDialog(null, "Conectado com sucesso");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void close() {
		try {
			this.port.close();
			JOptionPane.showMessageDialog(null, "Desconectado com sucesso");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public void open() {
		this.initialize();
	}

	public void sendData(int data) {
		writing = true;
		if (writing == true) {
			try {
				
				serialOutput = port.getOutputStream();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
			try {
				serialOutput.write(data);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}

	}

	public void sendData(String data) {
		writing = true;
		if (writing == true) {
			System.out.println("writing:" + data);
			try {
				serialOutput = port.getOutputStream();
				System.out.println("SerialOutput:" + data);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
			try {
				serialOutput.write(data.getBytes());
				System.out.println("Bytes:" + data.getBytes());				
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}

	}

	public void readData() {
		if (writing == false) {
			try {
				serialInput = port.getInputStream();
				System.out.println("serialInput:");		
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
			try {
				port.addEventListener(this);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}

		}
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		// TODO Auto-generated method stub
		StringBuffer readBuffer = new StringBuffer();
		int newData = 0;
		
		switch (event.getEventType()) {
		case SerialPortEvent.DATA_AVAILABLE:
			while (newData != -1) {
				try {
					newData = serialInput.read();
					if(newData == -1) {
						break;
					}
					if('\r' == (char)newData) {
						readBuffer.append('\n');
					} else {
						readBuffer.append((char)newData);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
			JOptionPane.showMessageDialog(null, new String(readBuffer));
			break;

		default:
			break;
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {

			Thread.sleep(5);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
