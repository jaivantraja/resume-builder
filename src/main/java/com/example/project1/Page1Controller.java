package com.example.project1;

import javafx.event.ActionEvent;

import java.io.IOException;
public class Page1Controller {

    /*-------------------Login button-------------------*/
    public void login(ActionEvent event) throws IOException
    {
        try {
            Database m1=new Database();
            m1.changeScene("Page2.fxml");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
    /*-------------------Create button-------------------*/
    public void create(ActionEvent event) throws IOException {
        Database m1 = new Database();
        m1.changeScene("Page3.fxml");
    }
}
