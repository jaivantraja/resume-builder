package com.example.project1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
public class FrontPageController {
    @FXML
    private Button mainloginbtn;
    @FXML
    private Button createbtn;
    /*-------------------Login button-------------------*/
    public void login(ActionEvent event) throws IOException
    {
        try {
            ResumeApplication m1=new ResumeApplication();
            /*Parent root = FXMLLoader.load(getClass().getResource("LoginPagefxml.fxml"));
            Stage loginstage = new Stage();
            loginstage.initStyle(StageStyle.UNDECORATED);
            loginstage.setScene(new Scene(root));
            loginstage.setTitle("second window");
            loginstage.show()*/
            m1.changeScene("LoginPagefxml.fxml");
        }

        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
    /*-------------------Create button-------------------*/
    public void create(ActionEvent event) throws IOException {
        ResumeApplication m1 = new ResumeApplication();
        m1.changeScene("CreatePagefxml.fxml");
    }
}
