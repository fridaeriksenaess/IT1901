package periodtracker.ui;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StartControllerTest extends ApplicationTest {

  RestModel restModel = new RestModel();

	@FXML
	protected Button logIn, newUser;

	@FXML
	protected GridPane startPane;

	@BeforeAll
	public static void setupHeadless() {
		PeriodTrackerApp.supportHeadless();
	}

	private StartController startController;

	@Override
	public void start(final Stage stage) throws Exception {
		final FXMLLoader loader = new FXMLLoader(getClass().getResource("Start.fxml"));
		startController = new StartController();
		loader.setController(startController);
		stage.setScene(new Scene(loader.load()));
		stage.show();
	}

	@Test
	public void testGoToLogin() {
		try {
			clickOn("#logIn");
		} catch (Exception e) {
			throw new NullPointerException("Log in button not clicked");
		}
	}

	@Test
	public void testGoToCreateuser() {
		try {
			clickOn("#createUser");
		} catch (Exception e) {
			throw new NullPointerException("Create user button not clicked");
		}
	}

	@Test
	public void testPersonServiceNotNull() {
		assertNotNull(this.restModel);
	}

}
