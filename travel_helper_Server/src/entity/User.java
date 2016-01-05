package entity;

public class User {
	private String userName;
	private String gender;
	private String password;
	private String mail;
	private String profile;

	public User() {

	}

	public User(String userName, String gender, String password, String mail,
			String profile) {
		this.userName = userName;
		this.gender = gender;
		this.password = password;
		this.mail = mail;
		this.profile = profile;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName
				+ ", gender=" + gender + ", password=" + password + ", mail="
				+ mail + ", profile=" + profile + "]";
	}

	

}
