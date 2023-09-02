package periodtracker.ui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import periodtracker.core.CalendarLogic;
import periodtracker.core.Person;
import javafx.scene.Node;

/**
 * Controller for the calendar page.
 */
public class CalendarController extends LoginController implements Initializable {

  @FXML
  protected Button logOut, backButton, forwardButton;

  @FXML
  protected GridPane calendarPane, theCalendar, symptomsGrid;

  @FXML
  protected Label monthShow, activeUser;

  private CalendarLogic logic = new CalendarLogic();
  private StartController startController;

  private int month = logic.getMonth() + 1;
  private int year = logic.getYear();

  private Person person = new Person();
  private RestModel restModel = new RestModel();


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.createCalendar();
    activeUser.setText("User: " + person.getName());
  }

  /**
   * Constructor for CalendarController.
   * 
   * @param person 
   */

  public CalendarController(Person person) {
    this.person = person;
  }
  /**
   * Another constructor for CalendarController that takes in RestModel.
   * 
   * @param restModel 
   */

  public CalendarController(RestModel restModel) {
    this.restModel = restModel;
  }

  /**
   * Method for showing the next month in the calendar.
   * 
   */
  @FXML
  private void forward() {
    if (this.month == 12) {
      this.year++;
      this.month = 0;
    }
    this.month++;
    createCalendar();

  }

  /**
   * Method for showing the previous month in the calendar.
   */
  @FXML
  private void backward() {
    if (this.month <= 1) {
      this.year--;
      this.month = 13;
    }
    this.month--;
    createCalendar();

  }

  /**
   * Method for loggin out person, en returning to the start page.
   * 
   * @param actionEvent
   */
  @FXML
  private void logOut(ActionEvent actionEvent) throws IOException {
    // restModel.savePersons();
    FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("Start.fxml")));
    startController = new StartController();
    loader.setController(startController);
    loadStartView(actionEvent, loader);
  }

  /**
   * Method for creating the calendar.
   *
   */
  @FXML
  protected void createCalendar() {

    LocalDate date = LocalDate.of(year, month, 1);
    int dateNum = date.getDayOfWeek().getValue() - 1;

    while (date.getMonth().getValue() == this.month) {
      theCalendar.add(createTitledPane(date), (dateNum % 7), dateNum / 7);
      dateNum++;
      date = date.plusDays(1);
    }
    double x = theCalendar.getPrefHeight();
    double y = theCalendar.getPrefWidth();

    theCalendar.setPrefHeight(x);
    theCalendar.setPrefWidth(y);

    monthShow.setText(Month.of(this.month).toString() + ", " + Year.of(this.year).toString());
    
  }

  /**
   * Button for creating the symptomsPane. It switches the scene to the symptoms
   * checkboxes.
   * 
   * @param d is the date we want to create the pane to.
   */
  @FXML
  private void createSymptomsPane(LocalDate d) {


    CheckBox b1 = new CheckBox("Small-bleedings");
    CheckBox b2 = new CheckBox("Cramps");
    CheckBox b3 = new CheckBox("Tiredness");
    CheckBox b4 = new CheckBox("Headache");
    CheckBox b5 = new CheckBox("Intense-bleedings");
    CheckBox b6 = new CheckBox("Stress");
    CheckBox b7 = new CheckBox("Nausia");

    Button b8 = new Button("Exit");
    Button b9 = new Button("Submit symptoms");

    List<CheckBox> cList = new ArrayList<>(Arrays.asList(b1, b2, b3, b4, b5, b6, b7));

    List<String> currDaySymptoms = restModel.getPerson(person.getName()).getDateSymptom(d);

    for (CheckBox c : cList) {
      if (currDaySymptoms.contains(c.getText())) {
        c.setSelected(true);
      }
    }

    symptomsGrid.add(b1, 0, 1);
    symptomsGrid.add(b2, 0, 2);
    symptomsGrid.add(b3, 0, 3);
    symptomsGrid.add(b4, 0, 4);
    symptomsGrid.add(b5, 0, 5);
    symptomsGrid.add(b6, 0, 6);
    symptomsGrid.add(b7, 0, 7);

    symptomsGrid.add(b8, 5, 0);
    symptomsGrid.add(b9, 4, 7);

    b8.setOnMouseClicked(e -> {
      backButton.setDisable(false);
      forwardButton.setDisable(false);
      symptomsGrid.toBack();
      symptomsGrid.setOpacity(0);
      monthShow.setText(Month.of(this.month).toString() + ", " + Year.of(this.year).toString());

    });

    b9.setOnMouseClicked(e -> {
      backButton.setDisable(false);
      forwardButton.setDisable(false);
      for (CheckBox c : cList) {
        if (c.isSelected()) {
          restModel.getSymptomsOnDay(person.getName(), d.toString());
          restModel.addSymptoms(person.getName(), d.toString(), c.getText());
        } else {
          restModel.getSymptomsOnDay(person.getName(), d.toString());
          restModel.removeSymptoms(person.getName(), d.toString(), c.getText());
        }
      createCalendar();
      symptomsGrid.toBack();
      symptomsGrid.setOpacity(0);
      monthShow.setText(Month.of(this.month).toString() + ", " + Year.of(this.year).toString());

    }});
  }
    

  /**
   * Method for creating a titledPane.
   * 
   * @param date is the date.
   * @return titledPane.
   */
  private TitledPane createTitledPane(LocalDate date) {
    String dateValue = String.valueOf(date.getDayOfMonth());
    Label label = new Label();
    TitledPane titledPane = new TitledPane(dateValue, label);
    titledPane.collapsibleProperty().set(false);
  
    if (!restModel.getPerson(person.getName()).getDateSymptom(date).isEmpty()) {
      label.setText("V");
      label.setStyle("-fx-text-fill:GREEN; -fx-font-size: 18;");
    } 
    
    titledPane.setOnMouseClicked(e -> {
      try {
        if (date.isAfter(logic.getDate())) {
          createAlert("Date not valid!", "The date is in the future",
              "You can't add symptoms on a future date!");
          return;
        }
        backButton.setDisable(true);
        forwardButton.setDisable(true);
        symptomsGrid.setOpacity(1);
        symptomsGrid.toFront();
        symptomsGrid.setBackground(Background.fill(Color.WHITE));
        createSymptomsPane(LocalDate.ofEpochDay(date.toEpochDay()));
        monthShow.setText(dateValue + ". " + Month.of(month).toString());

      } catch (Exception e2) {
        throw e2;
      }
    });

    titledPane.setPrefHeight(60);
    titledPane.setPrefWidth(100);

    return titledPane;

  }

  /**
   * Button for the create user scene. Switches scene to the lcreate user.
   * 
   * @param actionEvent is the event of pressing the buttton.
   * @param loader      is the FXMLLoader.
   */
  protected void loadStartView(ActionEvent actionEvent, FXMLLoader loader) throws IOException {
    loader.setController(startController);
    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    Scene scene = new Scene(loader.load());
    stage.setScene(scene);
    stage.show();
  }

}
