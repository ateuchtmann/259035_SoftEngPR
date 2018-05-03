package Views;
import java.awt.EventQueue;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Daten.Aufgabe;

public class AufgabeView {

	private static JFrame aufgabeFrame;
	private static JPanel aufBereichPanel;
	private JTextField fldIstZeit;
	private JTextField fldSollZeit;
	private static Map<Integer, Integer> yCoordinateList = new HashMap<>();
	private static Aufgabe aufgabe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AufgabeView window = new AufgabeView(aufgabeFrame, aufBereichPanel, yCoordinateList, aufgabe);
					AufgabeView.aufgabeFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AufgabeView(JFrame frame, JPanel panel, Map<Integer, Integer> list, Aufgabe aufgabe) {
		AufgabeView.aufgabeFrame = frame;
		AufgabeView.aufBereichPanel = panel;
		AufgabeView.aufgabe = aufgabe;
		AufgabeView.yCoordinateList = list;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		//adding panel behind input area (better look)
		
		JPanel aufgabePanel = new JPanel();
		aufgabePanel.setBounds(12, yCoordinateList.get(aufBereichPanel.hashCode()), 331, 100);
		aufgabePanel.setLayout(null);
		
		//adding input area for aufgabe text
		
		JTextArea fldAufgabeBeschreibung = new JTextArea();
		fldAufgabeBeschreibung.setBounds(0, 0, 331, 74);
		aufgabePanel.add(fldAufgabeBeschreibung);
		
		//adding sollzeit
		
		JLabel lblSollzeit = new JLabel("Sollzeit:");
		lblSollzeit.setBounds(10, 81, 56, 16);
		aufgabePanel.add(lblSollzeit);
		
		fldIstZeit = new JTextField();
		fldIstZeit.setBounds(60, 78, 45, 22);
		aufgabePanel.add(fldIstZeit);
		fldIstZeit.setColumns(10);
		
		//adding istzeit
		
		JLabel fldIstZeit = new JLabel("Istzeit:");
		fldIstZeit.setBounds(210, 81, 56, 16);
		aufgabePanel.add(fldIstZeit);
		
		fldSollZeit = new JTextField();
		fldSollZeit.setBounds(253, 78, 45, 22);
		aufgabePanel.add(fldSollZeit);
		fldSollZeit.setColumns(10);
		
		
		aufBereichPanel.add(aufgabePanel);
		aufgabeFrame.repaint();
		aufgabe.setBeschreibung(fldIstZeit.getText());

		//adding to the y coordinate
		yCoordinateList.put(aufBereichPanel.hashCode(), yCoordinateList.get(aufBereichPanel.hashCode()) + 110);
		
	}//initialize

}
