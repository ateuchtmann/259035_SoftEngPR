package Views;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import Daten.Aufgabenbereich;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CreateAufgabenbereichView {

	private JFrame createAufgabenbereichFrame;
	protected int xCoordinate = 20; // coordinate for moving aufgabenbereich to the right
	
	/**
	 * Launch the application.
	 */

	
	public JFrame getFrame() {
		return createAufgabenbereichFrame;
	}
	
	public int getX() {
		return xCoordinate;
	}

	/**
	 * Create the application.
	 */
	public CreateAufgabenbereichView() {
		initialize();
	}

	/**
s	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		createAufgabenbereichFrame = new JFrame();
		createAufgabenbereichFrame.setBounds(0, 0, 1920, 1080);
		createAufgabenbereichFrame.getContentPane().setBackground(new Color(102, 153, 204));
		createAufgabenbereichFrame.getContentPane().setLayout(null);
		createAufgabenbereichFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JButton btnCreateAufgabenbereich = new JButton("Neuen Aufgabenbereich erstellen");
		btnCreateAufgabenbereich.setBounds(767, 13, 362, 57);
		
		btnCreateAufgabenbereich.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		btnCreateAufgabenbereich.setBackground(SystemColor.LIGHT_GRAY);
		Border borderBtnCreateAB = new MatteBorder(3,3,4,3,Color.BLACK);
		btnCreateAufgabenbereich.setBorder(borderBtnCreateAB);
		
		//specifying what happens after we press "create new aufgabenbsereich" 
		
		btnCreateAufgabenbereich.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				Aufgabenbereich aufgabenB = new Aufgabenbereich();
				AufgabenbereichView aufgabenbereichView = new AufgabenbereichView(createAufgabenbereichFrame, aufgabenB, xCoordinate);
				xCoordinate = xCoordinate + 385;
			}
		});
		
		createAufgabenbereichFrame.getContentPane().add(btnCreateAufgabenbereich);
			
	}//initialize
}//CreateAufgabenbereichClass
