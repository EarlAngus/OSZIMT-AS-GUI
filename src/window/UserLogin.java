package window;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.codejava.swing.hyperlink.JHyperlink;

/**
 * The primary window of the application. It provides login capabilities for the
 * user prior to granting access to the application.
 * 
 * @author Arne, Max
 *
 */
@SuppressWarnings("serial")
public class UserLogin extends JFrame {

	private JPanel contentPane;
	private JPasswordField fieldPassword;
	private JTextField fieldUsername;

	/**
	 * Launch the application.
	 * 
	 * @param args the arguments passed to the application.
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
		initialize();
		// initialize database
		Database.db();
	}

	private void initialize() {
		initGlobalParameters();
		initStaticLabels();
		initButtons();
		initFields();
	}

	private void initGlobalParameters() {
		setTitle("Applikation X");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1400, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	private void initStaticLabels() {
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(183, 124, 68, 14);
		contentPane.add(lblPassword);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(183, 68, 68, 14);
		contentPane.add(lblUsername);

		JLabel lblWelcome = new JLabel("Willkommen bei Applikation X");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(121, 22, 192, 14);
		contentPane.add(lblWelcome);
	}

	private void initButtons() {
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(172, 213, 89, 23);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		contentPane.add(btnLogin);
	}

	private void initFields() {
		fieldPassword = new JPasswordField();
		fieldPassword.setBounds(145, 149, 144, 20);
		contentPane.add(fieldPassword);

		fieldUsername = new JTextField();
		fieldUsername.setBounds(145, 93, 144, 20);
		contentPane.add(fieldUsername);

		JHyperlink lblHyperlink = new JHyperlink("Open phpMyAdmin");
		String url = ""; // "https://" + Database.db().getDbURL() + "/mysqladmin/";
		lblHyperlink.setURL(url);
		lblHyperlink.setToolTipText("Go to " + url);
		lblHyperlink.setBounds(10, 236, 114, 14);
		contentPane.add(lblHyperlink);
	}

	private void login() {
		if (isValidLogin()) {
			System.out.println("Login erfolgreich"); // for DEBUG
			this.dispose();
			Telephonebook tb = new Telephonebook();
			tb.setVisible(true);
		} else {
			System.out.println("Login fehlgeschlagen");// for DEBUG
			JOptionPane.showMessageDialog(null, "Falsche Logindaten, bitte erneut versuchen.");
		}
	}

	private boolean isValidLogin() {
		Database db = Database.db(); // Die Datenbank in der die Logindaten stehen

		// Logindaten des Benutzers auslesen
		String pw = new String(fieldPassword.getPassword());
		String name = fieldUsername.getText();

		return db.validateCredentials(name, pw);
	}
}
