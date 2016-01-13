package entity;

public class Bill {
	
	private int bill_id;
	private double value;
	private String bill_description;
	private String bill_type;
	private String bill_time;
	public int getBill_id() {
		return bill_id;
	}
	public void setBill_id(int bill_id) {
		this.bill_id = bill_id;
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
	public String getBill_time() {
		return bill_time;
	}
	public void setBill_time(String bill_time) {
		this.bill_time = bill_time;
	}
	@Override
	public String toString() {
		return "Bill [bill_id=" + bill_id + ", value=" + value
				+ ", bill_description=" + bill_description + ", bill_type="
				+ bill_type + ", bill_time=" + bill_time + "]";
	}

	
}
