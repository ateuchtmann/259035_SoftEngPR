package Views;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class AufgabenbereichView {

	private static JFrame frame;
	private static Map<Integer, Integer> yList = new HashMap<>();
	private JTextField textField;
	private JTextField textField2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AufgabenbereichView window = new AufgabenbereichView(frame);
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
	public AufgabenbereichView(JFrame frame) {
		this.frame = frame;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		JPanel aufBereichPanel = new JPanel();    
		yList.put(aufBereichPanel.hashCode(), 100); 
		
		aufBereichPanel.setBackground(Color.LIGHT_GRAY);
		aufBereichPanel.setBounds(CreateAufgabenbereichView.x, 120, 355, 780);
		frame.getContentPane().add(aufBereichPanel);
		Border projectBorder = new MatteBorder(2,2,3,2,Color.BLACK);
		aufBereichPanel.setBorder(projectBorder);
		aufBereichPanel.setLayout(null);
		
		JLabel aufBereichName = new JLabel("Default Name");
		aufBereichName.setBackground(new Color(255, 255, 255));
		aufBereichName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		aufBereichName.setBounds(12, 13, 294, 34);
		aufBereichPanel.add(aufBereichName);
		
		 // new panel behind name (for better view)
		
		JPanel contentPaneName = new JPanel();
		contentPaneName.setBorder(new EmptyBorder(22,22,22,22));
		contentPaneName.setBackground(Color.WHITE);
		contentPaneName.setBounds(0, 0, 355, 63);
		Border paneNameBorder = new MatteBorder(3,3,4,3,Color.DARK_GRAY);
		contentPaneName.setBorder(paneNameBorder);
		contentPaneName.setLayout(null);
		
		JButton buttonEditName = new JButton("..."); //button for editing name of project
		buttonEditName.setBounds(318, 19, 25, 25);
	
		
		// specifying the action after pressing the button for name edit
		
		buttonEditName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//creating second frame (window) to make input when editing name of project
				
				JFrame frame2 = new JFrame(); 
		
				JPanel contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				contentPane.setLayout(null);
				
				//adding "set name:" next to input
				
				JLabel lblNewLabel2 = new JLabel("Set name: ");
				lblNewLabel2.setFont(new Font("Verdana", Font.PLAIN, 15));
				lblNewLabel2.setBounds(31, 30, 113, 25);
				contentPane.add(lblNewLabel2);
				
				//adding area to input the name
				
				JTextArea inputNameArea = new JTextArea();
				inputNameArea.setBounds(114, 30, 320, 25);
				inputNameArea.setFont(new Font("Verdana", Font.PLAIN, 15));
				contentPane.add(inputNameArea);
				
				//creating button to save name and close second frame 
	
				JButton btnNewButton_3 = new JButton("ok");
				btnNewButton_3.setBounds(450, 30, 57, 35);
				contentPane.add(btnNewButton_3);
				btnNewButton_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String str = inputNameArea.getText();
						aufBereichName.setText(str);  
						frame2.dispose();
					}
				});
				
				
				// specifying second frame attributes
				
				frame2.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
				frame2.setBounds(700, 400, 550,169);
				frame2.getContentPane().setBackground(new Color(102, 153, 204));
				frame2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				frame2.getContentPane().add(contentPane);
				frame2.setVisible(true);
		        
				
			}
		}); //buttonEditName
		
		aufBereichPanel.add(buttonEditName);
		aufBereichPanel.add(contentPaneName);
		
		
		JButton btnAufgabeHinzufgen = new JButton("Aufgabe Hinzuf\u00FCgen");
		btnAufgabeHinzufgen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			   AufgabeView aufgabe = new AufgabeView(frame, aufBereichPanel, yList);
			   
			}
		});
		btnAufgabeHinzufgen.setBounds(105, 62, 147, 25);
		aufBereichPanel.add(btnAufgabeHinzufgen);
		
		
		CreateAufgabenbereichView.x = CreateAufgabenbereichView.x + 375;

	}

}
