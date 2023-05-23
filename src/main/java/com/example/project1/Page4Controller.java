package com.example.project1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class Page4Controller {
    @FXML
    private Label name;
    @FXML
    private Label branch;
    @FXML
    private Label number;




    /*-------------------Cancel button-------------------*/
    public void cancel(ActionEvent event) throws IOException {
        Database m1=new Database();
        m1.changeScene("Page1.fxml");
    }
}
