package files;

import java.util.ArrayList;
import java.util.List;

import db_load.LoadActivity;
import db_load.LoadPerson;
import db_load.LoadProject;
import db_load.LoadTask;
import db_load.LoadTaskGroup;

public class Test {
	public static void main(String[] args) {

		ProjectList prjctList;
		List<Project> prjctListFiles;

		prjctList = new ProjectList();
		prjctListFiles = prjctList.getProjectList();

		prjctListFiles = new LoadProject().allProjects();
		for (Project p : prjctListFiles) {

			p.setName(new LoadProject().projectName(p));
			p.setDescr(new LoadProject().projectDescription(p));
			prjctList.addProject(p);

			List<Person> ProjectPersons = new LoadProject().projectPersons(p);

			for (Person person : ProjectPersons) {
				p.addPerson(person);
			}

			List<TaskGroup> taskGroupList = new LoadProject().projectTaskGroups(p);

			for (TaskGroup tg : taskGroupList) {

				tg.setName(new LoadTaskGroup().taskGroupName(tg));

				List<Person> taskGroupPerson = new LoadTaskGroup().taskGroupPersons(tg);

				for (Person person : taskGroupPerson) {
					tg.addPerson(person);
				}
				p.addTaskGroup(tg);

				List<Task> taskList = new LoadTaskGroup().taskGroupTasks(tg);

				for (Task task : taskList) {

					task.setName(new LoadTask().taskName(task));
					task.setPlanTime(new LoadTask().taskPlanTime(task));

					List<Person> taskPerson = new LoadTask().taskPersons(task);

					for (Person person : taskPerson) {
						task.addPerson(person);
					}

					tg.addTsk(task);

					List<Activity> activityList = new LoadTask().taskActivities(task);
					for (Activity activity : activityList) {

						activity.setDescr(new LoadActivity().activityDescription(activity));
						activity.setStart(new LoadActivity().activityStart(activity));
						activity.setEnd(new LoadActivity().activityEnd(activity));
						
						Person per = new LoadActivity().activityPerson(activity); 
						per.setFirstName(new LoadPerson().personFirstname(per));
						per.setLastname(new LoadPerson().personLastname(per));
						activity.addPerson(per);
						
						System.out.println(activity.getDescrn() + "-" + activity.getId());

						task.addActivity(activity);
					}
					
	
				}
			}
		}
	}
}
