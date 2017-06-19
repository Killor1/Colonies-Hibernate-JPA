package Entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Camp {
	private int id;
	private String description;
	private String location;
	private Date startDate;
	private Date endDate;
	private Set<Activity> activities = new HashSet<Activity>();
	private Set<Child> children = new HashSet<Child>();
	
	public Camp() {
		
	}
	
	public Camp(int id, String description, String location, Date startDate, Date endDate) {
		super();
		this.id = id;
		this.description = description;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Camp(int id, String description, String location, Set<Activity> activities) {
		super();
		this.id = id;
		this.description = description;
		this.location = location;
		this.activities = activities;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}

	public Set<Child> getChildren() {
		return children;
	}

	public void setChildren(Set<Child> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Camp [id=" + id + ", description=" + description + ", location=" + location + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", activities=" + activities + ", children=" + children + "]";
	}


}
