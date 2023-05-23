package com.example.project1;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Database extends Application {

    @FXML
    private BorderPane parentContainer;
    private static Stage stg1;
    @Override
    /*---------------------------------LOADING THE FIRST PAGE OF APPLICATION--------------------*/
    public void start(Stage stage) throws Exception {
        try {
            stg1=stage;
            stg1.setResizable(false);
            FXMLLoader fxmlLoader = new FXMLLoader(ResumeApplication.class.getResource("Page1.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600,400 );
            stage.setTitle("Student Database");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    /*--------------------TO CHANGE SCENE -----------------------*/
    public void changeScene(String fxml) throws IOException
    {
        Parent pane =FXMLLoader.load(getClass().getResource(fxml));
        stg1.getScene().setRoot(pane);
    }
    /*----------------------MAIN FUNCTION -----------------------*/
    public static void main(String[] args) {
        launch();
    }
}
