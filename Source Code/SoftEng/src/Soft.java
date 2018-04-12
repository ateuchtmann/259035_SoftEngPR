import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JTextArea;
import java.awt.SystemColor;
public class Soft {

	private JFrame frame;

	//launch the application
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Soft window = new Soft();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//coordinates for panels
	
	static int y = 101;
	static int x = 15;
	
    // list of frames behind the edits
	EditProjectClass editClass;
	static Map<Integer, EditProjectClass> editFrames = new HashMap <>();


	// create the application
	
	public Soft() {
		initialize();
	}
	

	// contents of the frame
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
		frame.setBounds(0, 0, 1920, 1080);
		frame.getContentPane().setBackground(new Color(102, 153, 204));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Start-Button to make new Project
		
		JButton btnCreateProject = new JButton("Neues Projekt erstellen");
		btnCreateProject.setBounds(767, 13, 362, 57);
		
		btnCreateProject.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		btnCreateProject.setBackground(SystemColor.LIGHT_GRAY);
		Border b1 = new MatteBorder(3,3,4,3,Color.BLACK);
		btnCreateProject.setBorder(b1);
		
		// Action after pressing create project button
		
		btnCreateProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				editClass = new EditProjectClass();  //make new edit class frame
					
						
				JPanel panel = new JPanel();           //panel for the project 
				panel.setBackground(Color.LIGHT_GRAY);
				panel.setBounds(x, y, 343, 240);
				frame.getContentPane().add(panel);
				panel.setLayout(null);
				Border projectBorder = new MatteBorder(3,3,4,3,Color.BLACK);
				panel.setBorder(projectBorder);
				frame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
				
				frame.repaint();	
				
				
				
				editFrames.put(panel.hashCode(), editClass);  //****************************************************
				
				
				JLabel lblName = new JLabel("Name:");   // adding indication of "name"
				lblName.setBounds(15, 19, 69, 20);
				panel.add(lblName);
				
				JLabel textField = new JLabel("Projektname"); // adding space to hold the input name
				textField.setBounds(110, 16, 166, 26);
				panel.add(textField);
				
				
				JLabel lblBeschreibung = new JLabel("Beschreibung:");  //adding indication of "description"
				lblBeschreibung.setBounds(15, 65, 114, 20);
				panel.add(lblBeschreibung);
				
				JTextArea textArea = new JTextArea();  //adding area to input description of project
				textArea.setBounds(110, 65, 210, 102);
				textArea.setLineWrap(true);
				Border textAreaBorder = new MatteBorder(3,3,4,3,Color.GRAY);
				textArea.setBorder(textAreaBorder);
				panel.add(textArea);
				
				JButton btnEdit = new JButton("Edit");  // button for editing the project (Aufgabenbereiche etc.)
				btnEdit.setBounds(111, 195, 115, 29);
				panel.add(btnEdit);
				
				JButton buttonEditName = new JButton("..."); //button for editing name of project
				buttonEditName.setBounds(280, 16, 40, 26);
				
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
						inputNameArea.setBounds(114, 33, 150, 24);
						inputNameArea.setFont(new Font("Verdana", Font.PLAIN, 15));
						contentPane.add(inputNameArea);
						
						//creating button to save name and close second frame 
			
						JButton btnNewButton_3 = new JButton("ok");
						btnNewButton_3.setBounds(271, 30, 57, 35);
						contentPane.add(btnNewButton_3);
						btnNewButton_3.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								String str = inputNameArea.getText();
								textField.setText(str);  
								frame2.dispose();
							}
						});
						
						
						// specifying second frame attributes
						
						frame2.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
						frame2.setBounds(700, 400, 350,169);
						frame2.getContentPane().setBackground(new Color(102, 153, 204));
						frame2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
						frame2.getContentPane().add(contentPane);
						frame2.setVisible(true);
				        
						
					}
				}); //buttonEditName
				
				panel.add(buttonEditName); // adding the EDIT button to the panel of the project
				
				
				btnEdit.addActionListener(new ActionListener() {  
					public void actionPerformed(ActionEvent arg0) {
						
						// specifying the editing of a project (aufgabenbereiche etc.)
						
						JFrame frame3 = editFrames.get(panel.hashCode()).getFrame();
						frame3.setVisible(true);
						
					}
				});
				
				
				
				// calculating correct position of every project 
				
				if(y < 661){
					y = y + 280;
				}else{
					y = 101;
					x = x + 380;
				}
				
			} // createProjectButton (actionPerformed)	
		}); // createProjectButton (Listener - he has only one method: action performed (bzw. the above one))
		
		frame.getContentPane().setLayout(null); 
		frame.getContentPane().add(btnCreateProject);
		
	}
}