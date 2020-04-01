/**
 * 
 */
package window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;

/**
 * @author mkoppe
 *
 */
public class ActionOnLogin implements ActionListener {
	
	String statischerName = "Max";
	String statischesPw = "123";

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton button = (JButton)e.getSource();
		JPanel panel = (JPanel) button.getParent();
		JLayeredPane pane = (JLayeredPane) panel.getParent();
		JRootPane root = (JRootPane) pane.getParent();
		UserLogin ulogin = (UserLogin) root.getParent();
		
		JPasswordField fieldPassword = ulogin.getFieldPassword();
		JTextField fieldUsername = ulogin.getFieldUsername();
		
		if (statischerName.equals(fieldUsername.getText())) {
			System.out.println("Name ist OK");
		}
		String pw = new String(fieldPassword.getPassword());
		if (statischesPw.equals(pw)) {
			System.out.println("Passwort ist OK");
		}
	}

}
