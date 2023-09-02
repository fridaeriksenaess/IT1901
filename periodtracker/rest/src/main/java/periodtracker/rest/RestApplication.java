package periodtracker.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;

/**
 * Application class for our springboot webserver.
 */
@SpringBootApplication
public class RestApplication implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

	private static int portNumber;
  
  public static void isTest(boolean test) {
    if (test) {
      portNumber = 8042;
    } else {
      portNumber = 8080;
    }
  }

	/**
	* Main method for the springboot application.
	* @param args
	*/
	public static void main(String[] args) {
    isTest(false);
		SpringApplication.run(RestApplication.class, args);
	}


	/**
	 * From the interface.
	 * WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>.
	 */
	@Override
	public void customize(ConfigurableServletWebServerFactory factory) {
		factory.setPort(portNumber);

	}



}
