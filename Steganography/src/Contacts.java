import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Contacts extends JFrame {

	private JPanel contentPane;
	private File file=new File(System.getProperty("user.home")+"\\Desktop\\File transfer\\List.sky");
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Contacts frame = new Contacts();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Contacts() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 340, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 137, 322, 289);
		contentPane.add(scrollPane);
		
		JButton btnAddNewContacts = new JButton("Add new Contacts");
		btnAddNewContacts.setBounds(161, 99, 135, 25);
		contentPane.add(btnAddNewContacts);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(12, 13, 56, 16);
		contentPane.add(lblName);
		
		JLabel lblIpAddress = new JLabel("IP address :");
		lblIpAddress.setBounds(12, 42, 69, 16);
		contentPane.add(lblIpAddress);
		
		textField = new JTextField();
		textField.setBounds(110, 10, 186, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(110, 39, 186, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
	}


	public void list(String contact){
		FileWriter fw;
		try {
			fw = new FileWriter(file,true);
			BufferedWriter bw=new BufferedWriter(fw);
			PrintWriter pw=new PrintWriter(bw);
			pw.write(contact);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
