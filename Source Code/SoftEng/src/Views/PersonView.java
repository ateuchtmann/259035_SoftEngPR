package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;

import Daten.Person;
import Daten.Projekt;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PersonView {

	private JFrame personFrame;
	private JTextField fldFname;
	private JTextField fldSname;
	private Person prs;
	private JButton btnPrsInfo;
	static Projekt projekt;

	/**
	 * Launch the application.
	 */
	public JFrame getFrame() {
		return personFrame;
	}

	/**
	 * Create the application.
	 */
	public PersonView(Person prs, JButton btnPrsInfo, Projekt projekt) {
		this.prs = prs;
		this.btnPrsInfo = btnPrsInfo;
		this.projekt = projekt;
		initialize();
	}

	private void initialize() {
		
		personFrame = new JFrame();
		personFrame.setBounds(650, 200, 450, 300);
		personFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		JPanel personInfoPanel = new JPanel();
		personInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		personFrame.getContentPane().add(personInfoPanel, BorderLayout.CENTER);
		personInfoPanel.setLayout(null);
		
		//label saying "first name"
		JLabel lblfname = new JLabel("First name:");
		lblfname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblfname.setBounds(21, 17, 72, 16);
		
		//label saying "surname"
		JLabel lblsname = new JLabel("Surname:");
		lblsname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblsname.setBounds(31, 46, 65, 16);
		
		//textField for first name
		fldFname = new JTextField();
		fldFname.setBounds(94, 14, 167, 22);
		fldFname.setColumns(10);
		
		//textField for surname
		fldSname = new JTextField();
		fldSname.setBounds(94, 43, 167, 22);
		fldSname.setColumns(10);

		JButton btnOk = new JButton("ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String firstInitial = null;
				String secondInitial = null;
				try {
					firstInitial = fldFname.getText(0, 1);
		            secondInitial= fldSname.getText(0,1);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
				prs.setVorname(fldFname.getText());
				prs.setNachname(fldSname.getText());
				
				projekt.addPerson(prs);
				btnPrsInfo.setText(firstInitial + "." + secondInitial + ".");
				personFrame.setVisible(false);
			}
		});
		btnOk.setBounds(165, 215, 97, 25);
		
		
		//adding all components to panel
		personInfoPanel.add(lblfname);
		personInfoPanel.add(lblsname);
		personInfoPanel.add(fldFname);
		personInfoPanel.add(fldSname);
		personInfoPanel.add(btnOk);
	}
}
