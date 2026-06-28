package gui;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Prueba JavaFX");
        primaryStage.setScene(new Scene(new StackPane(new Label("¡Funciona!")), 300, 200));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}