package com.example.project1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Page2Controller {
    @FXML
    private Button cancelbtn;
    @FXML
    private Label seenpass;
    @FXML
    private Label welcomeText;
    @FXML
    private CheckBox showpass;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label wronglogin;
    @FXML
    private ComboBox combobox;
    ObservableList<String> list= FXCollections.observableArrayList("CSE","ECE","MECH","CIVIL");
    /*----------------Login Validation----------------*/
    public void userlogin(ActionEvent event) throws IOException {
        try
        {
            ResumeApplication m1=new ResumeApplication();
            EmployeeController det1=new EmployeeController();
            String cbox="";
            cbox+=combobox.getValue();
            ConnectionClass connectionClass=new ConnectionClass();
            Connection connection=connectionClass.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs;
            String search= "select * from user where Name="+"'"+username.getText().toString()+"'"+" AND TYPE="+"'"+cbox.toString()+"'"+" AND Password="+"'"+password.getText().toString()+"'";
            rs=stmt.executeQuery(search);
            if(username.getText().toString().isEmpty()||password.getText().toString().isEmpty())
                wronglogin.setText("Don't leave empty fields");
            else if(rs.next())
            {
                wronglogin.setText("Logged In");
                System.out.println("------DETAILS------");
                System.out.println("Name:"+rs.getString(1));
                System.out.println("Branch:"+rs.getString(2));
                System.out.println("Password:"+rs.getString(3));
            }
            else
                wronglogin.setText("Invalid username or password");
        }
        catch(SQLException e){};
    }
    /*---------------------Show Password-------------------*/
    public void showpass(ActionEvent event)throws IOException{
        if (showpass.isSelected()&&password.getText().toString().isEmpty()==false)
            seenpass.setText(password.getText().toString());
        else if (password.getText().toString().isEmpty()==true)
            seenpass.setText("Empty Field");
        else
            seenpass.setText("");
    }
    /*-------------------Exit button-------------------*/
    public void exit(ActionEvent event)throws IOException
    {
        try {
            Database m1=new Database();
            m1.changeScene("Page1.fxml");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void initialize()
    {
        combobox.setItems(list);
    }
    public String info()
    {
        System.out.println(username.getText().toString());
        return null;
    }
}
