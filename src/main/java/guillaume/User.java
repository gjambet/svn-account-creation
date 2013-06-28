package guillaume;

public class User {

	private String email;
	private String group;
	private String name;

	private String login;
	private String password;

	public User() {

	}

	public String getPassword() {
		return password;
	}

	public String getLogin() {
		return login;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
