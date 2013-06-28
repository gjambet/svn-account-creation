package guillaume;

import static guillaume.Configuration.HTPASSWORD_EXEC_PATH;
import static guillaume.Configuration.PASSWORDS_FILE;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.Properties;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountGeneratorTest {

	@Mock private Properties configuration;
	
	@Mock private User user;

	@Test
	public void shouldBuildCreationCommandSucessfully() {

		when(configuration.getProperty(HTPASSWORD_EXEC_PATH.toString())).thenReturn("E:\\Coding\\tools\\Apache2.2\\bin\\htpasswd.exe");
		when(configuration.getProperty(PASSWORDS_FILE.toString())).thenReturn("E:\\Coding\\tools\\Apache2.2\\bin\\titi.pwd");

		when(user.getLogin()).thenReturn("test-user");
		when(user.getPassword()).thenReturn("test-password");
		
		AccountGenerator generator = new AccountGenerator(configuration);
		
		String expected = "E:\\Coding\\tools\\Apache2.2\\bin\\htpasswd.exe -b E:\\Coding\\tools\\Apache2.2\\bin\\titi.pwd test-user test-password";
		
		assertThat(generator.getCreationCommand(user)).isEqualTo(expected);

	}
	
	@Test
	@Ignore
	public void shouldExecuteCommandSuccessFully(){
		File passwords = new File("E:\\Coding\\tools\\Apache2.2\\bin\\titi.pwd"); 
		when(configuration.getProperty(PASSWORDS_FILE.toString())).thenReturn(passwords.getAbsolutePath());

		AccountGenerator generator = new AccountGenerator(configuration);
		
		final long previous = passwords.length();
		
		generator.execute("E:\\Coding\\tools\\Apache2.2\\bin\\htpasswd.exe -b E:\\Coding\\tools\\Apache2.2\\bin\\titi.pwd m2-isc-2012 titine");
		
		assertThat(passwords.length()).isGreaterThan(previous);
		
	}
	

}
