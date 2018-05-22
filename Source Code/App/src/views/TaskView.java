package views;
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
import files.*;

/* Classname: TaskView
*
* Programmers/Authors: 
* 
*  1.Milos Tomic
*  2.Maja Dusanic 
*  3.Alexander Teuchtmann 
*  4.Andrea Aistleithner 
*  5.Christopher Huber 
* 
*  Date: 22.05.2018
*  Version: 1.0.20
*
* Copyright notice
* - Programm is being build by the above mentioned programmers
* 
* Purpose of program: 
* - Time scheduling of projects, tasks etc.
*/

public class TaskView {

	private static JFrame tskFrame;
	private static JPanel tskGroupPanel;
	private JTextField fldPlanTime;
	private static Map<Integer, Integer> yCoorList = new HashMap<>();
	private static Task tsk;
	private static Project prjct;
	double planHour;
	double planMin;
	
	private static CreateActivityView createActView;
	
	
	
	/**
	 * Create the application.
	 * @param projekt 
	 */
	public TaskView(JFrame frame, JPanel tskGroupPanel, Map<Integer, Integer> list, Task tsk, Project prjct) {
		TaskView.tskFrame = frame;
		TaskView.tskGroupPanel = tskGroupPanel;
		TaskView.tsk = tsk;
		TaskView.yCoorList = list;
		
		this.prjct = prjct;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	

	static Map<JButton, CreateActivityView> createActivityViewMap = new HashMap<>();

	
	
	private void initialize() {
		
		

		//adding panel behind input area (better look)
		
		JPanel taskPanel = new JPanel();
		taskPanel.setBounds(12, yCoorList.get(tskGroupPanel.hashCode()), 331, 100);
		taskPanel.setLayout(null);
		
		
		
		//adding input area for task text
		
		JTextArea fldTaskDescr = new JTextArea();
		fldTaskDescr.setBounds(0, 0, 331, 74);
		taskPanel.add(fldTaskDescr);
		
		//adding planTime
		
		JLabel lblPlanTime = new JLabel("Sollzeit:");
		lblPlanTime.setBounds(10, 81, 56, 16);
		taskPanel.add(lblPlanTime);
		
		fldPlanTime = new JTextField("00.00");
		fldPlanTime.setBounds(60, 78, 45, 22);
		taskPanel.add(fldPlanTime);
		fldPlanTime.setColumns(10);
		
	
		createActView = new CreateActivityView(prjct);
		
		//adding button planTime
		
		JButton btnCurTime = new JButton("Zeiterfassung");
		btnCurTime.setBounds(180, 81, 120, 16);
		
		createActivityViewMap.put(btnCurTime, createActView);
		
		
		btnCurTime.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent arg0) {
			
				CreateActivityView newCreateActView =  createActivityViewMap.get(btnCurTime);
				
				//saving/parsing planedTime
				String typedPlanHour = fldPlanTime.getText().substring(0,2);
				String typedPlanMin = fldPlanTime.getText().substring(3,5);
				planHour = Double.parseDouble(typedPlanHour);
				planMin = Double.parseDouble(typedPlanMin);
				
				newCreateActView.setPlanHour(planHour);
				newCreateActView.setPlanMin(planMin);
			
				JFrame createActFrame = newCreateActView.getFrame();
				createActFrame.setVisible(true);
				
			}
		});
		
		
		
		taskPanel.add(btnCurTime);
		
	
		
		
		tskGroupPanel.add(taskPanel);
		tskFrame.repaint();
		tsk.setDescr(fldPlanTime.getText());

		//adding to the y coordinate
		yCoorList.put(tskGroupPanel.hashCode(), yCoorList.get(tskGroupPanel.hashCode()) + 110);
		

		
	}//initialize

}
