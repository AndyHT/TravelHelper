package entity;

public class Schedule {
	private int schedule_id;
	private String destination;
	private String picture;
	private String strat_date;
	private String end_date;
	public int getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(int schedule_id) {
		this.schedule_id = schedule_id;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getStrat_date() {
		return strat_date;
	}
	public void setStrat_date(String strat_date) {
		this.strat_date = strat_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	
}
