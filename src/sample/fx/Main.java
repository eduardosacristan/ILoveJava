package sample.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the class containing the methods to launch the app
 * @author Eduardo Sacristán Beltri
 * @version 1.0
 */
public class Main extends Application {
    /**
     * This method starts the JavaFX proyect
     * @param primaryStage Tha name of the Stage class object
     * @throws Exception To manage the possible errors.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Vegetable Garden HAL 9000");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    /**
     * The main method
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
