package login;

public class Login {

	private String username;
	private String password;
	private boolean isLogged;

	public Login(String u, String p) {
		this.username = u;
		this.password = p;
		isLogged = false;
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

	public boolean isLogged() {
		return isLogged;
	}

	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}

}
