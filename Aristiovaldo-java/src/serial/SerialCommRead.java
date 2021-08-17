package serial;

import gnu.io.CommPortIdentifier;

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

public class SerialCommRead implements Runnable, SerialPortEventListener {

	public String readData;
	public int nodeBytes;
	private int baudrate;
	private int timeout;
	private CommPortIdentifier cp;
	private SerialPort port;
	private OutputStream outputStream;
	private InputStream inputStream;
	private Thread readThread;;
	private boolean PortOK;
	private boolean reading;
	private boolean writing;
	private String Port;
	protected String baud;

	public SerialCommRead(String port, int baudrate, int timeout) {

		this.Port = port;

		this.baudrate = baudrate;

		this.timeout = timeout;

	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		// TODO Auto-generated method stub
		StringBuffer bufferRead = new StringBuffer();

		int newData = 0;

		switch (event.getEventType()) {

		case SerialPortEvent.BI:

		case SerialPortEvent.OE:

		case SerialPortEvent.FE:

		case SerialPortEvent.PE:

		case SerialPortEvent.CD:

		case SerialPortEvent.CTS:

		case SerialPortEvent.DSR:

		case SerialPortEvent.RI:

		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:

			break;

		case SerialPortEvent.DATA_AVAILABLE:

			while (newData != -1) {

				try {

					newData = inputStream.read();

					if (newData == -1) {

						break;

					}

					if ('\r' == (char) newData) {

						bufferRead.append('\n');

					} else {

						bufferRead.append((char) newData);

					}

				} catch (IOException ioe) {

				}

			}

			setBaud(new String(bufferRead));

			System.out.println(getBaud());

			break;

		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {

			Thread.sleep(5);

		} catch (Exception e) {

		}
	}

	public void setBaud(String baud) {

		this.baud = baud;

	}

	public String getBaud() {

		return baud;

	}

	public void startReading() {
		reading = true;
		writing = false;
	}

	public void startWriting() {
		reading = false;
		writing = true;
	}

	public void openPort() {

		try {

			port = (SerialPort) cp.open("SerialComLeitura", timeout);

			PortOK = true;

			port.setSerialPortParams(baudrate,

					port.DATABITS_8,

					port.STOPBITS_1,

					port.PARITY_NONE);

			port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);

		} catch (Exception e) {

			PortOK = false;

			JOptionPane.showMessageDialog(null, e);

		}

	}

	public void readData() {

		if (writing == false) {

			try {

				inputStream = port.getInputStream();

			} catch (Exception e) {

			}

			try {

				port.addEventListener(this);

			} catch (Exception e) {

			}

			port.notifyOnDataAvailable(true);

			try {

				readThread = new Thread(this);
				readThread.start();
				run();

			} catch (Exception e) {

			}

		}

	}

	public void EnviarUmaString(String msg) {

		if (writing == true) {

			try {

				outputStream = port.getOutputStream();

				System.out.println("FLUXO OK!");

			} catch (Exception e) {

				System.out.println("Erro.STATUS: " + e);

			}

			try {

				System.out.println("Enviando um byte para " + Port);

				System.out.println("Enviando : " + msg);

				outputStream.write(msg.getBytes());

				Thread.sleep(100);

				outputStream.flush();

			} catch (Exception e) {

				System.out.println("Houve um erro durante o envio. ");

				System.out.println("STATUS: " + e);

				System.exit(1);

			}

		} else {

			System.exit(1);

		}

	}

	
	public String obterPorta() {

		return Port;

	}

	public int obterBaudrate() {

		return baudrate;

	}
	public void closeCom() {
		port.close();
	}

}
