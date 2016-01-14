package entity;

public class User {
	private String userName;
	private String gender;
	private String password;
	private String email;
	private String profile;
	private String registerDate;

	public User() {

	}

	public User(String userName, String gender, String password, String email,
			String profile, String registerDate) {
		this.userName = userName;
		this.gender = gender;
		this.password = password;
		this.email = email;
		this.profile = profile;
		this.registerDate = registerDate;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	
}
