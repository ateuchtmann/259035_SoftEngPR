package views;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import models.Activity;
import models.Person;
import models.Project;
import models.Time;

public class ProjectBarChart extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Project prjct;

    public ProjectBarChart(Project prjct, String applicationTitle, String chartTitle) {
        super(applicationTitle);
        this.prjct = prjct;

        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,           
                "Person(en)",            
                "Stunden",            
                createDataset(),          
                PlotOrientation.VERTICAL,           
                true, true, false);
        
        ChartPanel chartPanel = new ChartPanel (barChart);        
        chartPanel.setPreferredSize(new java.awt.Dimension(560,367) );        
        setContentPane( chartPanel ); 
        
    }
    
    private CategoryDataset createDataset() {
    	
        DefaultCategoryDataset result = new DefaultCategoryDataset( );  
        
        for(Person p : prjct.getPersonList()) {
        	
        	for (int month = 1; month<13; month++) {
        	
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
	        	
	        	if (investTime != 0) {
	        	result.addValue(investTime, p.getLastName(), "Monat als String");
	        	}
	        	
	        	month++;
	        	
        	} 	
        	
        }
        
        return result;            
        
     }

}
