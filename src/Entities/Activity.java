package Entities;

public class Activity {
	
	private int id;
	private String desc;
	public Activity() {
	
	}

	public Activity(int id, String desc) {
		super();
		this.id = id;
		this.desc = desc;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return "Activitats [id=" + id + ", desc=" + desc + "]";
	}	
}