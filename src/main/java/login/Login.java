package login;

public class Login {

	private String username;
	private String password;

	public Login() {
		username = "admin";
		password = "1234";

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
