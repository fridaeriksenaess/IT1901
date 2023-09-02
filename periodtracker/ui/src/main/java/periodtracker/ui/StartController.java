package periodtracker.ui;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Node;

/**
 * Controller for the startPage
 */
final class StartController implements Initializable {
  @FXML
  protected Button logIn, newUser;

  @FXML
  protected GridPane startPane;

  private LoginController loginController;
  private CreateUserController createUserController;

  private RestModel restModel;

  /**
   * Method for returning the createUserController
   * 
   * @return createUserController
   */
  public LoginController getLoginController() {
    return loginController;
  }

  /**
   * Method for returning the createUserController
   * 
   * @return createUserController
   */
  public CreateUserController getCreateUserController() {
    return createUserController;
  }
  
  public void setRestModel(RestModel restModel) {
    this.restModel = restModel;
  }

  /**
   * Button for the log in scene. Switches scene to the log in.
   *
   * @param actionEvent is the event of pressing the buttton.
   */
  @FXML
  private void goToLogin(ActionEvent actionEvent) throws IOException {
    FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("LogIn.fxml")));
    loginController = new LoginController();
    loginController.setRestModel(restModel);
    loadView(actionEvent, loader);
  }

  /**
   * Button for the create user scene. Switches scene to the create user.
   *
   * @param actionEvent is the event of pressing the buttton.
   */
  @FXML
  private void goToCreateuser(ActionEvent actionEvent) throws IOException {
    FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("CreateUser.fxml")));
    createUserController = new CreateUserController();
    createUserController.setRestModel(restModel);
    loadView1(actionEvent, loader);
  }

  /**
   * Button for the log in scene. Switches scene to the log in.
   *
   * @param actionEvent is the event of pressing the buttton.
   */
  private void loadView(ActionEvent actionEvent, FXMLLoader loader) throws IOException {
    loader.setController(loginController);
    Scene scene = new Scene(loader.load());
    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Button for the create user scene. Switches scene to the create user.
   *
   * @param actionEvent is the event of pressing the buttton.
   */
  private void loadView1(ActionEvent actionEvent, FXMLLoader loader) throws IOException {
    loader.setController(createUserController);
    Scene scene = new Scene(loader.load());
    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    restModel = new RestModel();
  }

}
