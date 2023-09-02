package periodtracker.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PeriodTrackerApp extends Application {

	/**
	 * Method used by tests for ui module
	 */
	public static void supportHeadless() {
		if (Boolean.getBoolean("headless")) {
			System.setProperty("testfx.robot", "glass");
			System.setProperty("testfx.headless", "true");
			System.setProperty("prism.order", "sw");
			System.setProperty("prism.text", "t2k");
			System.setProperty("java.awt.headless", "true");
		}
	}

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Start.fxml"));
		fxmlLoader.setController(new StartController());
		Parent parent = fxmlLoader.load();
		stage.setScene(new Scene(parent));
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
