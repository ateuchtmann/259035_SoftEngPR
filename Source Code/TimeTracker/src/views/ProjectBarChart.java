package views;

import java.util.Calendar;
import java.util.Date;

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

public class ProjectBarChart extends JFrame {

	private static final long serialVersionUID = 1L;
	private Project prjct;
	private String type;

	public ProjectBarChart(Person pers, String type, Project prjct, String applicationTitle, String chartTitle) {
		super(applicationTitle);
		this.prjct = prjct;
		this.type = type;
		
		// one bar chart for all Persons and months of 2018
		if (type == "AllMonth") {
		JFreeChart barChart = ChartFactory.createBarChart(chartTitle, "Personen", "Stunden", createDsAllMonth(),
				PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		setContentPane(chartPanel);
		}
		
		// one bar chart for all Persons and weeks of 2018
		if (type == "AllWeek") {
		JFreeChart barChart = ChartFactory.createBarChart(chartTitle, "Personen", "Stunden", createDsAllWeek(),
				PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		setContentPane(chartPanel);
		}
		
		// bar charts for every single Person and months of 2018
		if (type == "SingMonth") {
			JFreeChart barChart = ChartFactory.createBarChart(chartTitle, "Person", "Stunden", createDsSingMonth(pers),
					PlotOrientation.VERTICAL, true, true, false);

			ChartPanel chartPanel = new ChartPanel(barChart);
			chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
			setContentPane(chartPanel);
		}
		
		// bar charts for every single Person and weeks of 2018
		if (type == "SingWeek") {
				JFreeChart barChart = ChartFactory.createBarChart(chartTitle, "Person", "Stunden", createDsSingWeek(pers),
						PlotOrientation.VERTICAL, true, true, false);

				ChartPanel chartPanel = new ChartPanel(barChart);
				chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
				setContentPane(chartPanel);
		}

	}

	// Dataset for all Persons in a single graph (monthly basis)
	private CategoryDataset createDsAllMonth() {

		DefaultCategoryDataset result = new DefaultCategoryDataset();
		double investTime = 0;
		for (Person p : prjct.getPersonList()) {

			for (int month = 1; month < 13; month++) {
				
				investTime = 0;
				
				for (Activity a : db_load.LoadPerson.personActivities(p)) {

					if (db_load.LoadActivity.activityMonth(a) == month) {
					
						Time start = new Time(0, 0);
						Time end = new Time(0, 0);

						start = db_load.LoadActivity.activityStart(a);
						end = db_load.LoadActivity.activityEnd(a);

						double time = 0;
						double sumStartTime = start.getHour() + (start.getMin() / 60);
						double sumEndTime = end.getHour() + (end.getMin() / 60);
						time = sumEndTime - sumStartTime;
						investTime += time;

					}

				}
				
				String monat = "";

				if (month == 1) {
					monat = "Jänner";
				}
				if (month == 2) {
					monat = "Februar";
				}
				if (month == 3) {
					monat = "März";
				}
				if (month == 4) {
					monat = "April";
				}
				if (month == 5) {
					monat = "Mai";
				}
				if (month == 6) {
					monat = "Juni";
				}
				if (month == 7) {
					monat = "Juli";
				}
				if (month == 8) {
					monat = "August";
				}
				if (month == 9) {
					monat = "September";
				}
				if (month == 10) {
					monat = "Oktober";
				}
				if (month == 11) {
					monat = "November";
				}
				if (month == 12) {
					monat = "Dezember";
				}

				if (investTime >= 0) {
					result.addValue(investTime, p.getLastName(), monat);
				}

			}

		}

		return result;

	}
	
	// Dataset for all Persons in a single graph (weekly basis)
	@SuppressWarnings("deprecation")
	private CategoryDataset createDsAllWeek() {

		DefaultCategoryDataset result = new DefaultCategoryDataset();
		double investTime = 0;
		Date activityDate;
		Calendar cl = Calendar.getInstance();
		
		for (Person p : prjct.getPersonList()) {

			for (int week = 1; week < 53; week++) {
				
				investTime = 0;
				
				for (Activity a : db_load.LoadPerson.personActivities(p)) {
					
					
					int year = db_load.LoadActivity.activityYear(a); 
					int month = db_load.LoadActivity.activityMonth(a); 
					int day = db_load.LoadActivity.activityDay(a); 
					
					activityDate = new Date(year, month, day); 
					cl.setTime(activityDate);
					
					
					if (cl.get(Calendar.WEEK_OF_YEAR) == week) {
					
						Time start = new Time(0, 0);
						Time end = new Time(0, 0);

						start = db_load.LoadActivity.activityStart(a);
						end = db_load.LoadActivity.activityEnd(a);

						double time = 0;
						double sumStartTime = start.getHour() + (start.getMin() / 60);
						double sumEndTime = end.getHour() + (end.getMin() / 60);
						
						time = sumEndTime - sumStartTime;
						
						investTime += time;

					}

				}
				
				if (investTime != 0) {
					String woche = "KW" + week ;
					result.addValue(investTime, p.getLastName(), woche);
				}

			}

		}

		return result;

	}
	
	// Dataset for every Person in a single graph (monthly basis)
	private CategoryDataset createDsSingMonth(Person p) {

		DefaultCategoryDataset result = new DefaultCategoryDataset();
		double investTime = 0;

			for (int month = 1; month < 13; month++) {
				
				investTime = 0;
				
				for (Activity a : db_load.LoadPerson.personActivities(p)) {

					if (db_load.LoadActivity.activityMonth(a) == month) {
					
						Time start = new Time(0, 0);
						Time end = new Time(0, 0);

						start = db_load.LoadActivity.activityStart(a);
						end = db_load.LoadActivity.activityEnd(a);

						double time = 0;
						double sumStartTime = start.getHour() + (start.getMin() / 60);
						double sumEndTime = end.getHour() + (end.getMin() / 60);
						time = sumEndTime - sumStartTime;
						investTime += time;

					}

				}
				
				String monat = "";

				if (month == 1) {
					monat = "Jänner";
				}
				if (month == 2) {
					monat = "Februar";
				}
				if (month == 3) {
					monat = "März";
				}
				if (month == 4) {
					monat = "April";
				}
				if (month == 5) {
					monat = "Mai";
				}
				if (month == 6) {
					monat = "Juni";
				}
				if (month == 7) {
					monat = "Juli";
				}
				if (month == 8) {
					monat = "August";
				}
				if (month == 9) {
					monat = "September";
				}
				if (month == 10) {
					monat = "Oktober";
				}
				if (month == 11) {
					monat = "November";
				}
				if (month == 12) {
					monat = "Dezember";
				}

				if (investTime >= 0) {
					result.addValue(investTime, p.getLastName(), monat);
				}

			}

		return result;

	}
	
	// Dataset for every Person in a single graph (weekly basis)
	@SuppressWarnings("deprecation")
	private CategoryDataset createDsSingWeek(Person p) {

		DefaultCategoryDataset result = new DefaultCategoryDataset();
		double investTime = 0;
		Date activityDate;
		Calendar cl = Calendar.getInstance();
		
			for (int week = 1; week < 53; week++) {
				
				investTime = 0;
				
				for (Activity a : db_load.LoadPerson.personActivities(p)) {
					
					
					int year = db_load.LoadActivity.activityYear(a); 
					int month = db_load.LoadActivity.activityMonth(a); 
					int day = db_load.LoadActivity.activityDay(a); 
					
					activityDate = new Date(year, month, day); 
					cl.setTime(activityDate);
					
					
					if (cl.get(Calendar.WEEK_OF_YEAR) == week) {
					
						Time start = new Time(0, 0);
						Time end = new Time(0, 0);

						start = db_load.LoadActivity.activityStart(a);
						end = db_load.LoadActivity.activityEnd(a);

						double time = 0;
						double sumStartTime = start.getHour() + (start.getMin() / 60);
						double sumEndTime = end.getHour() + (end.getMin() / 60);
						
						time = sumEndTime - sumStartTime;
						
						investTime += time;

					}

				}
				
				if (investTime != 0) {
					String woche = "KW" + week ;
					result.addValue(investTime, p.getLastName(), woche);
				}

			}

		return result;

	}

}
