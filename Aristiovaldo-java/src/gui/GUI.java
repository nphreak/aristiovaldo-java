package gui;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import serial.Serial;
import serial.SerialComm;

public class GUI implements ActionListener {
	Serial serial = new Serial();
	SerialComm serialComm;
	String selectedPort = "";
	String[] portas = serial.ObterPortas();

	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton buttonOn = new JButton("Liga");
	JButton buttonOff = new JButton("Desliga");
	JButton buttonToggle = new JButton("Alternar");
	JButton buttonConnect = new JButton("Conectar");
	JButton buttonDisconnect = new JButton("Desconectar");
	
	JComboBox<String> selectCOM = new JComboBox<>(portas);
	
		
	public GUI() {
		
		
		
		
		
		buttonOn.addActionListener(this);
		buttonOff.addActionListener(this);
		buttonToggle.addActionListener(this);
		buttonConnect.addActionListener(this);
		buttonDisconnect.addActionListener(this);
		selectCOM.addActionListener(this);
		
		panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		panel.setLayout(new GridLayout(0, 1));
		panel.add(selectCOM);
		panel.add(buttonConnect);
		panel.add(buttonDisconnect);
		panel.add(buttonToggle);
		panel.add(buttonOn);
		panel.add(buttonOff);
		
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Aristiovaldo, O falador");
		frame.pack();
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Liga":
			ledOn();
			break;
		case "Desliga":
			ledOff();
			break;
		case "Conectar":
			connect(selectedPort);
			break;
		case "Desconectar":
			disconnect();
			break;
		case "Alternar":
			ledToggle();
			break;
		case "comboBoxChanged":
			selectedPort = selectCOM.getSelectedItem().toString();
			
		default:
			break;
		}
		
	}
	
	public void connect(String selectedPort) {
		serialComm = new SerialComm(selectedPort, 9600, 100);
		serialComm.open();
	}
	public void disconnect() {
		serialComm.close();
	}
	
	public void ledOn() {
		serialComm.sendData("1");
	}
	public void ledOff() {
		serialComm.sendData("0");
	}
	public void ledToggle() {
		serialComm.sendData("a");
	}

}
