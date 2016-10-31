package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/sample.fxml"));
        primaryStage.setTitle("Calc");
        primaryStage.setScene(new Scene(root, 350, 482));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(getClass().getResource("/images/16.png").toString()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
