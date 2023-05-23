package com.example.project1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginPageController {
    @FXML
    private Button cancelbtn;
    @FXML
    private Label seenpass;
    @FXML
    private CheckBox showpass;
    @FXML
    private TextField username;
    @FXML
    private Button loginbtn;
    @FXML
    private PasswordField password;
    private static Stage stg2;
    @FXML
    private Label wronglogin;
    @FXML
    private ComboBox combobox;
    ObservableList<String> list= FXCollections.observableArrayList("Employee","Employer");
    /*----------------Login Validation----------------*/
    public void userlogin(ActionEvent event) throws IOException {
        try
        {
            ResumeApplication m1=new ResumeApplication();
            EmployeeController det1=new EmployeeController();
            String cbox="";
            String name=username.getText();
            cbox+=combobox.getValue();
            ConnectionClass connectionClass=new ConnectionClass();
            Connection connection=connectionClass.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs;
            String search= "select * from USER where Name="+"'"+username.getText().toString()+"'"+" AND TYPE="+"'"+cbox.toString()+"'"+" AND Password="+"'"+password.getText().toString()+"'";
            rs=stmt.executeQuery(search);
            if(username.getText().toString().isEmpty()||password.getText().toString().isEmpty())
            {
                wronglogin.setText("Don't leave empty fields");
            }
            else if(rs.next())
            {
                wronglogin.setText("Logged In");
                FileWriter writer = new FileWriter("D:\\userlogin.txt");
                writer.write(username.getText());
                writer.close();
                loginbtn.getScene().getWindow().hide();
                if(cbox.toString().equalsIgnoreCase("Employee"))
                {
                    FXMLLoader fxmlLoader = new FXMLLoader(ResumeApplication.class.getResource("Employeefxml.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 900,600 );
                    Stage stage2=new Stage();
                    stg2=stage2;
                    stg2.setResizable(false);
                    stage2.setOnCloseRequest(event1 -> {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?", ButtonType.YES, ButtonType.NO);
                        alert.showAndWait();
                        if (alert.getResult() == ButtonType.NO) {
                            event.consume();
                        }
                        else{
                            File file = new File("D:\\userlogin.txt");
                            File file2 = new File("D:\\useremail.txt");
                            File file3 = new File("D:\\usermail.txt");
                            if(file.exists())
                                file.delete();
                            if(file2.exists())
                                file.delete();
                            if(file3.exists())
                                file.delete();
                        }
                    });
                    stage2.setScene(scene);
                    stage2.show();
                }
                else
                {
                    FXMLLoader fxmlLoader = new FXMLLoader(ResumeApplication.class.getResource("Employerfxml.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 900,600 );
                    Stage stage2=new Stage();
                    stg2=stage2;
                    stg2.setResizable(false);
                    stage2.setOnCloseRequest(event1 -> {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?", ButtonType.YES, ButtonType.NO);
                        alert.showAndWait();
                        if (alert.getResult() == ButtonType.NO) {
                            event.consume();
                        }
                        else{
                            File file = new File("D:\\userlogin.txt");
                            if(file.exists())
                                file.delete();
                        }
                    });
                    stage2.setScene(scene);
                    stage2.show();
                }
            }
            else
            {
                wronglogin.setText("Invalid username or password");
            }
        }
        catch(SQLException e){};
    }
    /*---------------------Show Password-------------------*/
    public void showpass(ActionEvent event)throws IOException{
        if (showpass.isSelected()&&password.getText().toString().isEmpty()==false)
        {
            seenpass.setText(password.getText().toString());
        }
        else if (password.getText().toString().isEmpty()==true)
        {
            seenpass.setText("Empty Field");
        }
        else
        {
            seenpass.setText("");
        }
    }
    /*-------------------Exit button-------------------*/
    public void exit(ActionEvent event)throws IOException
    {
        try {
            ResumeApplication m1=new ResumeApplication();
            m1.changeScene("FrontPagefxml.fxml");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
    /*-------------------SETTING ITEMS TO COMBOX-------------------*/
    public void initialize()
    {
        combobox.setItems(list);
    }
    /*-------------------STORING USERNAME-------------------*/
    /*public String info()
    {
        System.out.println(username.getText().toString());
        return null;
    }*/
    /*-------------------CHANGING SCENE-------------------*/
    public void changeScene2(String fxml) throws IOException
    {
        Parent pane =FXMLLoader.load(getClass().getResource(fxml));
        stg2.getScene().setRoot(pane);
    }
}