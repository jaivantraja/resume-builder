package com.example.project1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Project");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        Label info= new Label("");
        grid.add(info, 1, 6);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField passwordBox = new PasswordField();
        grid.add(passwordBox, 1, 2);

        Button btn = new Button("Sign in");
        Button btn1=new Button("Create account");
        HBox hbBtn = new HBox(10);
        HBox hbBtn1=new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn1.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(btn);
        //grid.add(hbBtn, 1, 4);
        grid.add(btn1, 1, 4);
        grid.add(btn, 0, 4);
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

      /*  btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try
                {
                    Scene scene=
                    btn1.setOnAction(event -> primaryStage.setScene(scene));
                    ConnectionClass connectionClass=new ConnectionClass();
                    Connection connection=connectionClass.getConnection();
                    Statement stmt = connection.createStatement();
                    ResultSet rs;
                    String search= "select * from USER where Name="+"'"+userName.getText().toString()+"'"+" AND Password="+"'"+pw.getText().toString()+"'";
                    rs=stmt.executeQuery(search);
                }catch(Exception E) {}

            }
        });
*/
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try
                {
                    ConnectionClass connectionClass=new ConnectionClass();
                    Connection connection=connectionClass.getConnection();
                    Statement stmt = connection.createStatement();
                    ResultSet rs;
                    String search= "select * from USER where Name="+"'"+userTextField.getText().toString()+"'"+" AND Password="+"'"+passwordBox.getText().toString()+"'";
                    rs=stmt.executeQuery(search);
                    //actiontarget.setFill(Color.FIREBRICK);
                    //actiontarget.setText("Sign in button pressed");
                    String username = userTextField.getText().toString();
                    String password = passwordBox.getText().toString();
                    if(username.isEmpty()||password.isEmpty())
                        info.setText("Don't leave empty fields");
                    else if(rs.next())
                        info.setText("Logged In");
                    else
                        info.setText("Invalid username or password");
                }catch(Exception E) {}
            }
        });
        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}