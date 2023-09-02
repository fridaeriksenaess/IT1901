package periodtracker.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import periodtracker.storage.FileHandling;

public class CreateUserControllerTest extends ApplicationTest {

	@FXML
	protected TextField nameInput, ageInput;

	@FXML
	protected GridPane createUserPane;

	@FXML
	protected Button createButton;
  
  @FXML
  protected Label activeUser;

  RestModel restModel = new RestModel(); 
	FileHandling fileHandling = new FileHandling(true);

	StartController startController;
	CreateUserController createUserController;

	@BeforeEach
	public void setupHeadless() {
		PeriodTrackerApp.supportHeadless();
    createUserController.setRestModel(restModel);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		final FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateUser.fxml"));
		createUserController = new CreateUserController();
		loader.setController(createUserController);
		stage.setScene(new Scene(loader.load()));
		stage.show();
	}

	@Test
	public void invalidAge() {
		clickOn("#nameInput").write("marty");
		clickOn("#ageInput").write("100");
		clickOn("#createButton");

		FxAssert.verifyThat("OK", NodeMatchers.isVisible());
		clickOn(LabeledMatchers.hasText("OK"));
	}

	@Test
	public void invalidAgeLetters() {
		clickOn("#nameInput").write("heidi");
		clickOn("#ageInput").write("wrong");
		clickOn("#createButton");

		FxAssert.verifyThat("OK", NodeMatchers.isVisible());
		clickOn(LabeledMatchers.hasText("OK"));
	}

	@Test
	public void invalidNameLetters() {
		clickOn("#nameInput").write("øæå");
		clickOn("#ageInput").write("23");
		clickOn("#createButton");

		FxAssert.verifyThat("OK", NodeMatchers.isVisible());
		clickOn(LabeledMatchers.hasText("OK"));
	}

	@Test
	public void invalidNameNumbers() {
		clickOn("#nameInput").write("1234");
		clickOn("#ageInput").write("23");
		clickOn("#createButton");

		FxAssert.verifyThat("OK", NodeMatchers.isVisible());
		clickOn(LabeledMatchers.hasText("OK"));
	}

	@Test
	public void blank() {
		clickOn("#createButton");
		FxAssert.verifyThat("OK", NodeMatchers.isVisible());
		clickOn(LabeledMatchers.hasText("OK"));
	}

	@Test
	public void testCreateUserButton() {
		clickOn("#nameInput").write("Sofie");
		clickOn("#ageInput").write("23");
		clickOn("#createButton");
	}

	@Test
	public void testgoBack() {
		clickOn(LabeledMatchers.hasText("Back"));
	}

}
