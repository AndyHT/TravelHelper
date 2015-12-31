package entity;

import java.util.Date;

public class Tip {
	private String tip_content;
	private Date tip_time;
	private String tip_description;
	private String tip_type;

	public Tip() {

	}

	public Tip(String tip_content, Date tip_time,
			String tip_description, String tip_type) {
		this.tip_content = tip_content;
		this.tip_time = tip_time;
		this.tip_description = tip_description;
		this.tip_type = tip_type;
	}

	public String getTip_content() {
		return tip_content;
	}

	public void setTip_content(String tip_content) {
		this.tip_content = tip_content;
	}

	public Date getTip_time() {
		return tip_time;
	}

	public void setTip_time(Date tip_time) {
		this.tip_time = tip_time;
	}

	public String getTip_description() {
		return tip_description;
	}

	public void setTip_description(String tip_description) {
		this.tip_description = tip_description;
	}

	public String getTip_type() {
		return tip_type;
	}

	public void setTip_type(String tip_type) {
		this.tip_type = tip_type;
	}

	@Override
	public String toString() {
		return "Tip [tip_content=" + tip_content
				+ ", tip_time=" + tip_time + ", tip_description="
				+ tip_description + ", tip_type=" + tip_type + "]";
	}

}
