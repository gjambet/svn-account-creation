package guillaume;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class Main {

	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		new Main().start();
	}

	private Properties config;

	public Main() throws FileNotFoundException, IOException {

		config = new Properties();

		if (!isConfigurationOk()) {
			config = null;
		}

	}

	private boolean isConfigurationOk() throws FileNotFoundException,
			IOException {
		URL location = this.getClass().getResource(
				"/AccountGenerator.properties");
		if (location == null) {
			System.err
					.println("config file : AccountGenerator.properties directory's must be in classpath");
			return false;
		}

		config.load(new FileInputStream(new File(location.getFile())));
		for (Configuration entry : Configuration.values()) {

			if (!config.containsKey(entry.toString())) {
				System.err.println("missing configuration ENTRY : "
						+ entry.toString());
				return false;

			}

			if (StringUtils.isBlank((String) config.get(entry.toString()))) {
				System.err.println("missing configuration VALUE for : "
						+ entry.toString());
				return false;

			}
		}

		return true;
	}

	private void start() throws IOException {

		if (config == null) {
			System.out.println("users : ");
			return;
		}

		List<User> users = new UserProvider(config).getUsers();

		AccountGenerator generator = new AccountGenerator(config);
		for (User user : users) {
			generator.create(user);
		}

		
		for (User user : users) {
			System.out.println("http://svn.oups.net/subversion/M2-ISC-2012/");
			System.out.println("users : " + user.getLogin());
			System.out.println("users : " + user.getPassword());
			System.out.println("");
		}
		System.out.println("");
		for (User user : users) {
			System.out.print(user.getLogin());
			System.out.print(", ");
		}
		
		
	}

}
