package com.example.project1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;

public class Page3Controller {
    @FXML
    private CheckBox showpassbtn1;
    @FXML
    private Label createinfo;
    @FXML
    private Label seenpass1;
    @FXML
    private TextField username;
    @FXML
    private PasswordField pass1;
    @FXML
    private PasswordField pass2;
    @FXML
    private ComboBox combobox;
    /*-------------------Combo box values-------------------*/
    ObservableList<String> list= FXCollections.observableArrayList("CSE","ECE","MECH","CIVIL");
    /*-------------------Create validation-------------------*/
    public void create(ActionEvent event) throws Exception {
        String cbox="";
        cbox+=combobox.getValue();
        if(username.getText().toString().isEmpty()||pass1.getText().toString().isEmpty()||pass2.getText().toString().isEmpty())
            createinfo.setText("enter all fields");
        else if((pass1.getText().toString().equals(pass2.getText().toString())==false))
            createinfo.setText("Password don't match");
        else {
            Database m1=new Database();
            ConnectionClass connectionClass=new ConnectionClass();
            Connection connection=connectionClass.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs;
            String search= "select * from USER where Name="+"'"+username.getText().toString()+"'"+"AND"+"'"+cbox.toString()+"'";
            rs=stmt.executeQuery(search);
            if(rs.next())
                createinfo.setText("Account already exist with same username");
            else
            {
                String query="INSERT INTO user "+ "VALUES"+"("+"'"+username.getText().toString()+"'"+","+"'"+pass1.getText().toString()+"'"+","+"'"+cbox.toString()+"'"+")";
                int returned = stmt.executeUpdate(query);
                createinfo.setText("Successfully registered");
                m1.changeScene("Page2.fxml");
            }
        }
    }
    /*-------------------Cancel button-------------------*/
    public void cancel(ActionEvent event) throws IOException {
       Database m1=new Database();
        m1.changeScene("Page1.fxml");
    }
    /*-------------------Show password-------------------*/
    public void showpass1(ActionEvent event) {
        if (showpassbtn1.isSelected()&&pass1.getText().toString().isEmpty()==false)
            seenpass1.setText(pass1.getText().toString());
        else if (pass1.getText().toString().isEmpty()==true)
            seenpass1.setText("Enter password");
        else
            seenpass1.setText("");
    }
    /*-------------------Combobox button-------------------*/
    public void initialize()
    {
        combobox.setItems(list);
    }
}
