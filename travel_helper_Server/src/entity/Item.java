package entity;

public class Item {
	private String item_type;
	private String item_description;
	private String item_name;
	private int item_num;

	public Item() {

	}

	public Item(String item_type, String item_description,
			String item_name, int item_num) {
		this.item_type = item_type;
		this.item_description = item_description;
		this.item_name = item_name;
		this.item_num = item_num;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	public String getItem_description() {
		return item_description;
	}

	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public int getItem_num() {
		return item_num;
	}

	public void setItem_num(int item_num) {
		this.item_num = item_num;
	}

	@Override
	public String toString() {
		return "Item [item_type=" + item_type
				+ ", item_description=" + item_description + ", item_name="
				+ item_name + ", item_num=" + item_num + "]";
	}

}
