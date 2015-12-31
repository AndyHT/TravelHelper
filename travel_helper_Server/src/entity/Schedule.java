package entity;

import java.util.Date;

public class Schedule {
	private int plan_num;
	private Date strat_date;
	private Date end_date;
	private String description;

	public Schedule() {

	}

	public Schedule(int plan_num, Date strat_date,
			Date end_date, String description) {
		this.plan_num = plan_num;
		this.strat_date = strat_date;
		this.end_date = end_date;
		this.description = description;
	}

	public int getPlan_num() {
		return plan_num;
	}

	public void setPlan_num(int plan_num) {
		this.plan_num = plan_num;
	}

	public Date getStrat_date() {
		return strat_date;
	}

	public void setStrat_date(Date strat_date) {
		this.strat_date = strat_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Schedule [plan_num="
				+ plan_num + ", strat_date=" + strat_date + ", end_date="
				+ end_date + ", description=" + description + "]";
	}

}
