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
import models.TaskGroup;
import models.Project;
import models.Task;
import models.Time;

public class TaskGroupPieChart extends JFrame {

	private static final long serialVersionUID = 1L;
	private TaskGroup tskgrp;
	private Project prjct;
	private Task tsk;

	public TaskGroupPieChart(Project prjct, TaskGroup tskgrp, String applicationTitle, String chartTitle) {
		super(applicationTitle);
		this.tskgrp = tskgrp;
		this.prjct = prjct;
		// This will create the dataset
		PieDataset dataset = createDataset();
		// based on the dataset we create the chart
		JFreeChart chart = createChart(dataset, chartTitle);
		// we put the chart into a panel
		ChartPanel chartPanel = new ChartPanel(chart);
		// default size
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 400));
		// add it to our application
		setContentPane(chartPanel);

	}

	private PieDataset createDataset() {
		DefaultPieDataset result = new DefaultPieDataset();

		double leftTime = prjct.getPlanTime(tskgrp);

		for (Person p : prjct.getPersonList()) {

			double investTime = 0;

			for (Task t : tskgrp.getTaskList()) {

				for (Activity a : t.getActivities()) {

					if (p.getFirstName().equals(a.getPerson().getFirstName())
							&& p.getLastName().equals(a.getPerson().getLastName())) {

						Time start = a.getStart();
						Time end = a.getEnd();

						double time = 0;
						double sumStartTime = start.getHour() + (start.getMin() / 60);
						double sumEndTime = end.getHour() + (end.getMin() / 60);
						time = sumEndTime - sumStartTime;

						investTime += time;

					}

				}

			}

			leftTime -= investTime;
			
			if (investTime != 0){
				result.setValue(p.getLastName() + " " + (investTime / prjct.getPlanTime(tskgrp) * 100) + "%",
						(investTime / prjct.getPlanTime(tskgrp) * 100));
			}

		}

		if (leftTime > 0)
			result.setValue("Noch Offen = " + (leftTime / prjct.getPlanTime(tskgrp) * 100) + "%",
					(leftTime / prjct.getPlanTime(tskgrp) * 100));

		return result;

	}// createDataset

	private JFreeChart createChart(PieDataset dataset, String title) {

		JFreeChart chart = ChartFactory.createPieChart(title, // chart title
				dataset, // data
				true, // include legend
				true, false);

		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		return chart;

	}

}