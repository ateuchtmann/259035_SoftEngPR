package models;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name="activity")
public class Activity {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="activityID")
	private int activityID;
	@Column(name="activityName")
	private String activityName;
	@Column(name="description")
	private String description;
	@Column(name="startTimeStamp")
	private String startTimeStamp;
	@Column(name="endTimeStamp")
	private String endTimeStamp;
	@Column(name="taskID")
	private int taskID;
	@Column(name="userID")
	private int userID;
	@Transient
	private long duration;
	@Transient
	private LocalDateTime startDateTime;
	@Transient
	private LocalDateTime endDateTime;
	//ObservableListItems
	@Transient
	private String activityOwnerString;
	@Transient
	private String durationString;

	//Constructers
	public Activity() {
	}

	public Activity(String activityName, String description, String startTimeStamp, String endTimeStamp, int taskID, int userID) {
		this.activityName = activityName;
		this.description = description;
		this.taskID = taskID;
		this.userID = userID;
		this.startTimeStamp = startTimeStamp;
		this.endTimeStamp = endTimeStamp;
		this.startDateTime = Lists.getInstance().parseStringToDateTime(startTimeStamp);
		this.endDateTime = Lists.getInstance().parseStringToDateTime(endTimeStamp);
		this.duration = Lists.getInstance().calculateTimeDuration(startDateTime,endDateTime);
		this.activityOwnerString = "";
		this.durationString = "";
	}

	public Activity(String activityName, String description, int taskID, int userID, LocalDateTime startDateTime, LocalDateTime endDateTime) {
		this.activityName = activityName;
		this.description = description;
		this.taskID = taskID;
		this.userID = userID;
		this.startTimeStamp = Lists.getInstance().parseDateTimeToString(startDateTime);
		this.endTimeStamp = Lists.getInstance().parseDateTimeToString(endDateTime);
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.duration = Lists.getInstance().calculateTimeDuration(startDateTime,endDateTime);
		this.activityOwnerString = "";
		this.durationString = "";
	}

	//Methods

	//Getter&Setter
		//Individual Setter
	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
		this.startTimeStamp = Lists.getInstance().parseDateTimeToString(startDateTime);
		this.duration = Lists.getInstance().calculateTimeDuration(startDateTime,this.endDateTime);
	}

	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
		this.endTimeStamp = Lists.getInstance().parseDateTimeToString(endDateTime);
		this.duration = Lists.getInstance().calculateTimeDuration(this.startDateTime,endDateTime);
	}

	public void setStartTimeStamp(String startTimeStamp) {
		this.startTimeStamp = startTimeStamp;
		this.startDateTime = Lists.getInstance().parseStringToDateTime(startTimeStamp);
		this.duration = Lists.getInstance().calculateTimeDuration(this.startDateTime,this.endDateTime);
	}

	public void setEndTimeStamp(String endTimeStamp) {
		this.endTimeStamp = endTimeStamp;
		this.endDateTime = Lists.getInstance().parseStringToDateTime(endTimeStamp);
		this.duration = Lists.getInstance().calculateTimeDuration(this.startDateTime,this.endDateTime);
	}

	public void updateDuration() {
		this.duration = Lists.getInstance().calculateTimeDuration(this.startDateTime,this.endDateTime);
	}

	public void initializeLocalDateTimes() {
		this.startDateTime = Lists.getInstance().parseStringToDateTime(startTimeStamp);
		this.endDateTime = Lists.getInstance().parseStringToDateTime(endTimeStamp);
	}

	public void initalizeTimeStamps() {
		this.startTimeStamp = Lists.getInstance().parseDateTimeToString(startDateTime);
		this.endTimeStamp = Lists.getInstance().parseDateTimeToString(endDateTime);
	}

		//Classic Getter&Setter


	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getActivityOwnerString() {
		return activityOwnerString;
	}

	public void setActivityOwnerString(String activityOwnerString) {
		this.activityOwnerString = activityOwnerString;
	}

	public String getDurationString() {
		return durationString;
	}

	public void setDurationString(String durationString) {
		this.durationString = durationString;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public int getActivityID() {
		return activityID;
	}

	public void setActivityID(int activityID) {
		this.activityID = activityID;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTaskID() {
		return taskID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getStartTimeStamp() {
		return startTimeStamp;
	}

	public String getEndTimeStamp() {
		return endTimeStamp;
	}

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}

	public long getDuration() {
		return duration;
	}
}


	
	
