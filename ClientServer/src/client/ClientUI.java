package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ClientUI {

	private JFrame frame;
	private JTextField TOtextField;
	private JTextField CctextField;
	private JTextField SubjecttextField;
	private JTextField MessageTextField;
	private JTextField textField_1;
	static Client client ;
	private JTextField FromtextField;

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		client = new Client();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientUI window = new ClientUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public ClientUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 653, 461);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panelSend = new JPanel();
		frame.getContentPane().add(panelSend, "name_5481892700686");
		panelSend.setLayout(null);
		
		TOtextField = new JTextField();
		TOtextField.setBounds(149, 48, 114, 19);
		panelSend.add(TOtextField);
		TOtextField.setColumns(10);
		
		JLabel ToLabel = new JLabel("To");
		ToLabel.setBounds(53, 50, 70, 15);
		panelSend.add(ToLabel);
		
		JLabel CcLabel = new JLabel("Cc");
		CcLabel.setBounds(53, 79, 70, 15);
		panelSend.add(CcLabel);
		
		CctextField = new JTextField();
		CctextField.setColumns(10);
		CctextField.setBounds(149, 79, 114, 19);
		panelSend.add(CctextField);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setBounds(53, 122, 70, 15);
		panelSend.add(lblSubject);
		
		SubjecttextField = new JTextField();
		SubjecttextField.setBounds(149, 120, 114, 19);
		panelSend.add(SubjecttextField);
		SubjecttextField.setColumns(10);
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setBounds(53, 183, 71, 19);
		panelSend.add(lblMessage);
		
		MessageTextField = new JTextField();
		MessageTextField.setBounds(90, 228, 454, 118);
		panelSend.add(MessageTextField);
		MessageTextField.setColumns(10);
		
		String[] optioins = {"Dusuk","Normal","Yuksek"};
		JComboBox priortyBox = new JComboBox(optioins);
		priortyBox.setBounds(149, 151, 114, 24);
		panelSend.add(priortyBox);
		
		JButton SendButton = new JButton("Send");
		SendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//					System.out.println()
//				System.out.println(TOtextField.getText() + CctextField.getText() + SubjecttextField.getText() + priortyBox.getSelectedItem() + MessageTextField.getText());
				client.sendMessage(TOtextField.getText(), CctextField.getText() ,SubjecttextField.getText(), (String)priortyBox.getSelectedItem(), MessageTextField.getText() , FromtextField.getText() );
			}
		});
		SendButton.setBounds(478, 370, 117, 25);
		panelSend.add(SendButton);
		
		JLabel lblPrioirty = new JLabel("Prioirty");
		lblPrioirty.setBounds(53, 156, 70, 15);
		panelSend.add(lblPrioirty);
		
		JLabel fromlbl = new JLabel("From");
		fromlbl.setBounds(53, 19, 70, 15);
		panelSend.add(fromlbl);
		
		FromtextField = new JTextField();
		FromtextField.setBounds(149, 17, 114, 19);
		panelSend.add(FromtextField);
		FromtextField.setColumns(10);
		
		JPanel panelQueryMessage = new JPanel();
		frame.getContentPane().add(panelQueryMessage, "name_5497538613030");
		panelQueryMessage.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(25, 12, 117, 25);
		panelQueryMessage.add(btnNewButton);
		
		JPanel panel_main = new JPanel();
		frame.getContentPane().add(panel_main, "name_5519315796594");
		panel_main.setLayout(null);
		
		JButton CheckButton = new JButton("Check Messages");
		CheckButton.setBounds(75, 180, 182, 25);
		panel_main.add(CheckButton);
		
		JButton sendsButton = new JButton("Send Message");
		sendsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		sendsButton.setBounds(306, 180, 202, 25);
		panel_main.add(sendsButton);
		
		textField_1 = new JTextField();
		textField_1.setBounds(36, 66, 407, 25);
		panel_main.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Your Adress");
		lblNewLabel.setBounds(26, 39, 122, 15);
		panel_main.add(lblNewLabel);
		
		JButton saveButton = new JButton("Save");
		saveButton.setBounds(326, 95, 117, 25);
		panel_main.add(saveButton);
	}
}
