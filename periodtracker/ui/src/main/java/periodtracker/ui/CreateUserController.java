package periodtracker.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import periodtracker.core.Person;
import javafx.scene.Node;

/**
 * Controller for the createUser page.
 */
public class CreateUserController extends LoginController {

  @FXML
  protected TextField nameInput, ageInput;

  @FXML
  protected GridPane createUserPane;

  private CalendarController calendarController;
  private RestModel restModel = new RestModel();

  /**
   * ActionEvent for when user confirm creating new user.
   * 
   * @param actionEvent
   */
  @FXML
  public void createUserButton(ActionEvent actionEvent) {
    try {
      Person person = new Person(nameInput.getText(), Integer.parseInt(ageInput.getText()), new HashMap<String, List<String>>());
      // restModel.getPerson(person.getName()).getName();
      String s = restModel.addPerson(person.getName(), person.getAge());
      if ("200 OK".equals(s)) { 
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("Calendar.fxml")));
        calendarController = new CalendarController(person);
        loader.setController(calendarController);
        loadCalendarView(actionEvent, loader);
        }
        else { 
          createAlert("Name already used", "Unvalid name", "This name is already taken, please chose another");
        }
    } catch (Exception e) {
      e.printStackTrace();
      createAlert("Name or age invalid", "Name or age is invalid",
      "Name is already used or invalid, or age is invalid. Name must be between A and Z and age must be between 10 and 70");
    }
}

  /**
   * Help method for loading calendar page.
   * 
   * @param actionEvent
   * @param loader
   */

  protected void loadCalendarView(ActionEvent actionEvent, FXMLLoader loader) throws IOException {
    loader.setController(calendarController);
    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    Scene scene = new Scene(loader.load());
    stage.setScene(scene);
    stage.show();
  }
}
