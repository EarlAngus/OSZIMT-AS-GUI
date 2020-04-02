package window;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JScrollBar;

public class Telephonebook extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public Telephonebook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 669, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		table = new JTable();
		table.setBounds(10, 11, 616, 464);
		contentPane.add(table);
		// Database readout 
		TableModel tm = table.getModel();
		tm = Database.db().readTable();
		table.setModel(tm);
		

		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(636, 11, 17, 477);
		contentPane.add(scrollBar);
	}
}
