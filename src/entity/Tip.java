package entity;

public class Tip {
	private String content;
	private String destination;
	private String type;
	private String time;
	private String picture;
	
	public Tip() {
		super();
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	@Override
	public String toString() {
		return "Tip [content=" + content + ", destination=" + destination
				+ ", type=" + type + ", time=" + time + ", picture=" + picture
				+ "]";
	}
	
	
}