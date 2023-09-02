package periodtracker.ui;

import java.io.IOException;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javafx.scene.Node;
import periodtracker.core.Person;

/**
 * Controller for the logIn page.
 */
public class LoginController {

	@FXML
	protected TextField logInNameField;

	@FXML
	protected GridPane logInPane;
	private StartController startController;
	private CalendarController calendarController;

	private RestModel restModel = new RestModel();

	/**
	 * Method for going back to the start page.
	 * 
	 * @param actionevent is the event of pressing the buttton.
	 */
	@FXML
	public void goBackToFront(ActionEvent actionEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("Start.fxml"));
		startController = new StartController();
		loadStartView(actionEvent, loader);
	}

	/**
	 * Method for logging in a person.
	 * x
	 * @param actionevent is the event of pressing the buttton.
	 */
	@FXML
	public void logInPerson(ActionEvent actionEvent) {
		try {
			Person person = restModel.getPerson(logInNameField.getText());
      // restModel.getPerson(person.getName()).getName();
      // if (!(person.equals(null) || person.getName().isEmpty()))
      if (!(person.getName().isEmpty())) { 
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("Calendar.fxml")));
        calendarController = new CalendarController(person);
        loader.setController(calendarController);
        loadCalendarView(actionEvent, loader);
      }
		} catch (Exception e) {
			createAlert("No user exist with this name", "User not found",
					"You need to write another name because the name you wrote does not exist");
		}
	}

	/**
	 * Help method for creating an alert when something goes wrong in the
	 * application
	 * 
	 * @param title
	 * @param headerText
	 * @param content
	 */
	protected void createAlert(String title, String headerText, String content) {
		Alert message = new Alert(AlertType.INFORMATION);
		message.setTitle(title);
		message.setHeaderText(headerText);
		message.setContentText(content);
		message.showAndWait();
	}

	/**
	 * Method for setting the view to calendar
	 * 
	 * @param actionevent is the event of pressing the buttton.
	 * @param loader      is the FXMLLoader
	 */
	protected void loadCalendarView(ActionEvent actionEvent, FXMLLoader loader) throws IOException {
		loader.setController(calendarController);
		Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		Scene scene = new Scene(loader.load());
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Method for setting the view to calendar
	 * 
	 * @param actionevent is the event of pressing the buttton.
	 * @param loader      is the FXMLLoader
	 */
	protected void loadStartView(ActionEvent actionEvent, FXMLLoader loader) throws IOException {
		loader.setController(startController);
		Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		Scene scene = new Scene(loader.load());
		stage.setScene(scene);
		stage.show();
	}
  
    public void setRestModel(RestModel restModel) {
    this.restModel = restModel;
  }


}
