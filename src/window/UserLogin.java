package window;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;

public class UserLogin extends JFrame {

	private JPanel contentPane;
	private JPasswordField fieldPassword;
	private JTextField fieldUsername;

	public JPasswordField getFieldPassword() {
		return fieldPassword;
	}

	public JTextField getFieldUsername() {
		return fieldUsername;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLogin frame = new UserLogin();
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
	public UserLogin() {
		// Globale Parameter des Frames
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(2500, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Statische Labels		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(170, 146, 144, 14);
		contentPane.add(lblPassword);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(170, 71, 63, 14);
		contentPane.add(lblUsername);
		
		JLabel lblWelcome = new JLabel("Willkommen bei Applikation X");
		lblWelcome.setBounds(125, 22, 155, 14);
		contentPane.add(lblWelcome);
		
		// Buttons und Textfelder
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(157, 202, 89, 23);
		contentPane.add(btnLogin);
		// Meine Logik fuer den Button
		ActionListener actionOnClick = new ActionOnLogin();
		btnLogin.addActionListener(actionOnClick);
		
		fieldPassword = new JPasswordField();
		fieldPassword.setBounds(125, 171, 144, 20);
		contentPane.add(fieldPassword);
		
		fieldUsername = new JTextField();
		fieldUsername.setBounds(125, 96, 144, 20);
		contentPane.add(fieldUsername);
		fieldUsername.setColumns(10);
	}
}
