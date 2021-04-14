package sk.kosickaakademia.kolesarova.exchange1.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainGui extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root= FXMLLoader.load(getClass().getClassLoader().getResource("Exchange.fxml"));
        primaryStage.setTitle("Exchange");
        primaryStage.setScene(new Scene(root, 450, 300));
        primaryStage.show();
    }
}
