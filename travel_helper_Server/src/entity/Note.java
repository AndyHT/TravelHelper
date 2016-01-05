package entity;

import java.util.Date;

public class Note {
	private String content;
	private Date time;

	public Note() {

	}

	public Note(String content, Date time) {
		this.content = content;
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Note [content=" + content + ", time="
				+ time + "]";
	}

}
