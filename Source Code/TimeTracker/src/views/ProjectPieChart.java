package views;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import models.Activity;
import models.Person;
import models.Project;
import models.Time;

public class ProjectPieChart extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Project prjct;

    public ProjectPieChart(Project prjct, String applicationTitle, String chartTitle) {
        super(applicationTitle);
        this.prjct = prjct;
        
        PieDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset, chartTitle);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500,400));
        setContentPane(chartPanel);
        
    }

  
        private  PieDataset createDataset() {
            DefaultPieDataset result = new DefaultPieDataset();
            
            double leftTime = prjct.getPlanTime();
            
            for(Person p : prjct.getPersonList()) { // goes throught the list of persons
            	
            	double investTime = 0;
         
            	Time start = new Time(0,0);
                Time end = new Time(0,0);
            	
            	for(Activity a : db_load.LoadPerson.personActivities(p)) {
            	start = db_load.LoadActivity.activityStart(a);
            	end = db_load.LoadActivity.activityEnd(a);

            	double time = 0;
        		double sumStartTime = start.getHour() + (start.getMin()/60);
        		double sumEndTime= end.getHour() + (end.getMin()/60);
        		time = sumEndTime - sumStartTime; 
        		investTime += time;
            	
        		
            	}
            	
            	leftTime -= investTime;
            	
            	if (investTime != 0){
            		result.setValue(p.getLastName() + " " + (investTime/prjct.getPlanTime()*100)+"%", 
            			       (investTime/prjct.getPlanTime()*100));
            	}
            		
            }
           
            if(leftTime > 0) result.setValue("Noch Offen = " + (leftTime/prjct.getPlanTime()*100) + "%",
            		           (leftTime/prjct.getPlanTime()*100));
            
    
            return result;

        } // createDataset
        
        private JFreeChart createChart(PieDataset dataset, String title) {

            JFreeChart chart = ChartFactory.createPieChart(
                title,                  // chart title
                dataset,                // data
                true,                   // include legend
                true,
                false
            );

            PiePlot plot = (PiePlot) chart.getPlot();
            plot.setStartAngle(290);
            plot.setDirection(Rotation.CLOCKWISE);
            plot.setForegroundAlpha(0.5f);
            return chart;
        
        }

}
