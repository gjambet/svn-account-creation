package guillaume;


public enum Configuration {

	PASSWORDS_FILE("passwords-file"), USERS_FILE("users-file"), HTPASSWORD_EXEC_PATH(
			"htpassword-exec-path");

	private String value;

	private Configuration(String value) {
		this.value = value;
	}
	
	public String toString(){
		return value;
	}

}
