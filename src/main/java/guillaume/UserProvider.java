package guillaume;

import static guillaume.Configuration.USERS_FILE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.commons.lang3.RandomStringUtils;

public class UserProvider {

	private Properties configuration;
	
	public UserProvider(Properties properties) {
		this.configuration = properties;
	}
	
	public List<User> getUsers() throws IOException {
		List<User> users = new ArrayList<User>();
		File file = new File(configuration.getProperty(USERS_FILE.toString()));
		
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);

		String line;
		do {
			
			line = br.readLine();
			
			if (line != null) {
				users.add(getUser(line));
			}
			
		} while (line != null);
		
		return users;
	}

	private User getUser(String line) {

		StringTokenizer st = new StringTokenizer(line, ",");
		User u = new User();
		u.setName(st.nextToken());
		u.setEmail(st.nextToken());
		u.setLogin(getLogin(u.getEmail()));
		u.setPassword(RandomStringUtils.randomAlphabetic(8));
		
		return u;
	}

	private String getLogin(String email) {
		return email.substring(0, email.indexOf('@'));
	}
	
}
