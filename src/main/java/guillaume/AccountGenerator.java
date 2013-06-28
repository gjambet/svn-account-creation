package guillaume;

import static guillaume.Configuration.HTPASSWORD_EXEC_PATH;
import static guillaume.Configuration.PASSWORDS_FILE;

import java.io.IOException;
import java.util.Properties;

public class AccountGenerator {
	
	private Properties configuration;
	
	public AccountGenerator(Properties properties) {
		this.configuration = properties;
	}

	
	public void create(User user) {
		
		String command = getCreationCommand(user);
		
		execute(command);
		
	}
	
	// htpasswd.exe -s -b titi.pwd test2 pipo
	public String getCreationCommand(User user){
		StringBuilder sb =  new StringBuilder();
		sb.append(configuration.getProperty(HTPASSWORD_EXEC_PATH.toString()));
		sb.append(" -b"); // no prompts
		sb.append(" " + configuration.getProperty(PASSWORDS_FILE.toString()));
		sb.append(" " + user.getLogin());
		sb.append(" " + user.getPassword());
		return sb.toString();
	}
	
	
	public void execute(String command){
		try {
			Process p = Runtime.getRuntime().exec(command);
			int i = p.waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
