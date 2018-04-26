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

public class AufgabeView {

	private static JFrame frame;
	private static JPanel aufBereichPanel;
	private JTextField textField;
	private JTextField textField2;
	private static Map<Integer, Integer> yList = new HashMap<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AufgabeView window = new AufgabeView(frame, aufBereichPanel, yList);
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
	public AufgabeView(JFrame frame, JPanel panel, Map list) {
		this.frame = frame;
		this.aufBereichPanel = panel;
		AufgabeView.yList = list;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		//adding panel behind input area (better look)
		
		JPanel aufgabePanel = new JPanel();
		aufgabePanel.setBounds(12, yList.get(aufBereichPanel.hashCode()), 331, 100);
		aufgabePanel.setLayout(null);
		
		//adding input area for aufgabe text
		
		JTextArea aufgabeArea = new JTextArea();
		aufgabeArea.setBounds(0, 0, 331, 74);
		aufgabePanel.add(aufgabeArea);
		
		//adding sollzeit
		
		JLabel aufgabeSollzeit = new JLabel("Sollzeit:");
		aufgabeSollzeit.setBounds(10, 81, 56, 16);
		aufgabePanel.add(aufgabeSollzeit);
		
		textField = new JTextField();
		textField.setBounds(60, 78, 45, 22);
		aufgabePanel.add(textField);
		textField.setColumns(10);
		
		//adding istzeit
		
		JLabel aufgabeIstzeit = new JLabel("Istzeit:");
		aufgabeIstzeit.setBounds(210, 81, 56, 16);
		aufgabePanel.add(aufgabeIstzeit);
		
		textField2 = new JTextField();
		textField2.setBounds(253, 78, 45, 22);
		aufgabePanel.add(textField2);
		textField2.setColumns(10);
		
		
		aufBereichPanel.add(aufgabePanel);
		frame.repaint();

		//adding to the y coordinate
		yList.put(aufBereichPanel.hashCode(), yList.get(aufBereichPanel.hashCode()) + 110);
		
	}//initialize

}
