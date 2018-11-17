package teaState.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import teaState.utility.DataSource;

public class Main extends Application {
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
        primaryStage.setTitle("Tea State Management System");
        primaryStage.setScene(new Scene(root, 600,400));
        primaryStage.getIcons().add(new Image("/teaState/green-tea-cup.png"));
        stage = primaryStage;
        stage.setResizable(false);
        primaryStage.show();
    }
    @Override
    public void init() throws Exception {
        super.init();
        if(!DataSource.getInstance().open()){
            System.out.println("ERROR: Couldn't connect the database!");
            Platform.exit();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        DataSource.getInstance().close();
        System.out.println("Database Closed.");

    }

    public static void main(String[] args) {
        launch(args);
    }
}
