package periodtracker.ui;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import periodtracker.core.Person;

public class LoginControllerTest extends ApplicationTest {
  
  RestModel restModel = new RestModel();

	@FXML
	protected TextField logInNameField;

	@FXML
	protected GridPane logInPane;

	@BeforeEach
	public void setupHeadless() {
		PeriodTrackerApp.supportHeadless();
    loginController.setRestModel(restModel);
	}

	private LoginController loginController;
	HashMap<String, List<String>> emmaMap = new HashMap<String, List<String>>();
	Person person = new Person("Katty", 23, emmaMap);

	@Override
	public void start(final Stage stage) throws Exception {
		final FXMLLoader loader = new FXMLLoader(getClass().getResource("LogIn.fxml"));
		loginController = new LoginController();
    loginController.setRestModel(restModel);
		loader.setController(loginController);
		stage.setScene(new Scene(loader.load()));
		stage.show();
	}

	@Test
	public void testEmptyNameInput() {
		clickOn(LabeledMatchers.hasText("Log in person!"));
		FxAssert.verifyThat("OK", NodeMatchers.isVisible());
		clickOn(LabeledMatchers.hasText("OK"));
	}

	@Test
	public void testInvalidName() {
		clickOn("#logInNameField").write("LiseNordmann");
		clickOn(LabeledMatchers.hasText("Log in person!"));
		FxAssert.verifyThat("OK", NodeMatchers.isVisible());
		clickOn(LabeledMatchers.hasText("OK"));
	}

	@Test
	public void testInvalidNameTwo() {
		clickOn("#logInNameField").write("OlaNordmann");
		clickOn(LabeledMatchers.hasText("Log in person!"));
		FxAssert.verifyThat("OK", NodeMatchers.isVisible());
		clickOn(LabeledMatchers.hasText("OK"));

	}

	@Test
	public void testGoBackButton() {
		clickOn(LabeledMatchers.hasText("Back"));
		FxAssert.verifyThat("Log in", NodeMatchers.isVisible());
	}

	@Test
	public void testValidPerson() {
    restModel.addPerson(person.getName(), person.getAge());
		clickOn("#logInNameField").write("Katty");
		clickOn(LabeledMatchers.hasText("Log in person!"));
	}

}
