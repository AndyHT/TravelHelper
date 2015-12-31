package entity;

import java.util.Date;

public class Bill {
	
	private double value;
	private String bill_description;
	private String bill_type;
	private Date bill_time;

	public Bill() {

	}

	public Bill(double value, String bill_description,
			String bill_type, Date bill_time) {
		this.value = value;
		this.bill_description = bill_description;
		this.bill_type = bill_type;
		this.bill_time = bill_time;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getBill_description() {
		return bill_description;
	}

	public void setBill_description(String bill_description) {
		this.bill_description = bill_description;
	}

	public String getBill_type() {
		return bill_type;
	}

	public void setBill_type(String bill_type) {
		this.bill_type = bill_type;
	}

	public Date getBill_time() {
		return bill_time;
	}

	public void setBill_time(Date bill_time) {
		this.bill_time = bill_time;
	}

	@Override
	public String toString() {
		return "Bill [value=" + value
				+ ", bill_description=" + bill_description + ", bill_type="
				+ bill_type + ", bill_time=" + bill_time + "]";
	}

}
