package guillaume;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import static guillaume.Configuration.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserProviderTest {
	
	@Mock private Properties configuration;
	
	@Test
	public void shouldGetUsers() throws IOException {
		
		when(configuration.getProperty(USERS_FILE.toString())).thenReturn(new File("src/test/resources/Students - Dashboard - Sheet1.csv").getAbsolutePath());

		UserProvider provider = new UserProvider(configuration);
		
		assertThat(provider.getUsers()).isNotEmpty();
	}
	
}
