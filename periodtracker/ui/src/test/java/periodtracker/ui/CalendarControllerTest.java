package periodtracker.ui;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import periodtracker.core.CalendarLogic;
import periodtracker.core.Person;
import periodtracker.storage.FileHandling;


public class CalendarControllerTest extends ApplicationTest {

	@FXML
	protected Button logOut;

	@FXML
	protected GridPane calendarPane, theCalendar, symptomsGrid;

	@FXML
	protected Label monthShow, activeUser;

	CalendarLogic logic = new CalendarLogic();
	StartController startController;
	CalendarController calendarController;
	LoginController loginController;
	CreateUserController createUserController;
	LocalDate today = LocalDate.now();
  static RestModel restModel = new RestModel(); 
	FileHandling fileHandling = new FileHandling(true);

	@BeforeEach
	public void setupHeadless() {
		PeriodTrackerApp.supportHeadless();
	}

	@BeforeAll
	public static void createUser() {
		HashMap<String, List<String>> emmaMap = new HashMap<String, List<String>>();
		Person person = new Person("Minni", 23, emmaMap);
    restModel.addPerson(person.getName(), person.getAge());

	}

	@Override
	public void start(final Stage stage) throws Exception {
		final FXMLLoader loader = new FXMLLoader(getClass().getResource("Calendar.fxml"));
    loader.setController(new CalendarController(restModel));
		stage.setScene(new Scene(loader.load()));
		stage.show();
	}

	@Test
	public void testRestModelNotNull() {
		assertNotNull(restModel);
	}

	@Test
	public void testClickOnDate() {
		FxAssert.verifyThat("1", NodeMatchers.isVisible());
		clickOn(LabeledMatchers.hasText("1"));
		FxAssert.verifyThat("Small-bleedings", NodeMatchers.isVisible());
	}

	@Test
	public void testLogOut() {
		clickOn(LabeledMatchers.hasText("Log out"));
		FxAssert.verifyThat("Log in", NodeMatchers.isVisible());
	}

	@Test
	public void testAddSymptoms() {
		clickOn(LabeledMatchers.hasText((Integer.toString(today.getDayOfMonth() - 2))));
		clickOn(LabeledMatchers.hasText("Small-bleedings"));
		clickOn(LabeledMatchers.hasText("Submit symptoms"));
	}

	@Test
	public void testAddFutureSymptoms() {
		clickOn(LabeledMatchers.hasText((Integer.toString(today.getDayOfMonth() + 3))));
		FxAssert.verifyThat("OK", NodeMatchers.isVisible());
		clickOn(LabeledMatchers.hasText("OK"));
	}

	@Test
	public void testRemoveSymptoms() {
		clickOn(LabeledMatchers.hasText((Integer.toString(today.getDayOfMonth() - 1))));
		clickOn(LabeledMatchers.hasText("Headache"));
		clickOn(LabeledMatchers.hasText("Submit symptoms"));
		clickOn(LabeledMatchers.hasText((Integer.toString(today.getDayOfMonth() - 1))));
		FxAssert.verifyThat("Headache", NodeMatchers.isEnabled());
		clickOn(LabeledMatchers.hasText("Headache"));
		clickOn(LabeledMatchers.hasText("Submit symptoms"));
		clickOn(LabeledMatchers.hasText((Integer.toString(today.getDayOfMonth() - 1))));
		FxAssert.verifyThat("Headache", NodeMatchers.isVisible());
	}

	@Test
	public void chekckIfSymptomsAdded() {
		clickOn(LabeledMatchers.hasText((Integer.toString(today.getDayOfMonth() - 4))));
		clickOn(LabeledMatchers.hasText("Cramps"));
		clickOn(LabeledMatchers.hasText("Submit symptoms"));
		clickOn(LabeledMatchers.hasText((Integer.toString(today.getDayOfMonth() - 4))));
		FxAssert.verifyThat("Cramps", NodeMatchers.isEnabled());
		clickOn(LabeledMatchers.hasText("Exit"));
	}

	@Test
	public void checkGoBackward() {
		clickOn(LabeledMatchers.hasText("<"));
		FxAssert.verifyThat(today.getMonth().minus(1).toString() + ", " + ((Integer) today.minusDays(30).getYear()).toString(), NodeMatchers.isVisible());

	}

	@Test
	public void checkGoForward() {
		clickOn(LabeledMatchers.hasText(">"));
		FxAssert.verifyThat(today.getMonth().plus(1).toString() + ", " + ((Integer) today.plusDays(30).getYear()).toString(), NodeMatchers.isVisible());
	}

 }
