/**
 * @filename JHyperlink.java
 * @created 2019-07-06
 * @author Written by Nam Ha Minh and published at www.codejava.net
 */
package net.codejava.swing.hyperlink;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * A hyperlink component that is based on JLabel.
 * 
 * @author www.codejava.net
 *
 */
@SuppressWarnings("serial")
public class JHyperlink extends JLabel {
	private String url;
	private String html = "<html><a href=''>%s</a></html>";

	/**
	 * Creates a hyperlink without specifying a target; usable within a JFrame;
	 * 
	 * @param text the displayed text.
	 */
	public JHyperlink(String text) {
		this(text, null, null);
	}

	/**
	 * Creates a hyperlink; usable within a JFrame;
	 * 
	 * @param text the displayed text.
	 * @param url  the target url.
	 */
	public JHyperlink(String text, String url) {
		this(text, url, null);
	}

	/**
	 * Sets the target url for the hyperlink
	 * 
	 * @param url the target url; i.e. a http/https link or a mailto link.
	 */
	public void setURL(String url) {
		this.url = url;
	}

	/**
	 * Creates a hyperlink with a tooltip; usable within a JFrame;
	 * 
	 * @param text    the displayed text.
	 * @param url     the target url.
	 * @param tooltip the tooltip that will be displayed when the cursor hovers over
	 *                the link.
	 */
	public JHyperlink(String text, String url, String tooltip) {
		super(text);
		this.url = url;

		setForeground(Color.BLUE.darker());

		setToolTipText(tooltip);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				setText(String.format(html, text));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setText(text);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				try {

					Desktop.getDesktop().browse(new URI(JHyperlink.this.url));

				} catch (IOException | URISyntaxException e1) {
					JOptionPane.showMessageDialog(JHyperlink.this,
							"Could not open the hyperlink. Error: " + e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		});

	}
}
