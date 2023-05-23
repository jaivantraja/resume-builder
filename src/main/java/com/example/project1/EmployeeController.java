package com.example.project1;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class EmployeeController {
    @FXML
    private Button cancelbutton;
    @FXML
    private Label info;
    @FXML
    private Label empname;
    @FXML
    private ListView list;
    @FXML
    private Label nofile;
    public String name;
    /*-------------------EXIT BUTTON-------------------*/
    public void exit(ActionEvent event) throws IOException
    {
        File file = new File("D:\\userlogin.txt");
        if(file.delete())
            System.out.println(file.getName() + " is deleted!");
        else
            System.out.println("Delete operation is failed.");
        cancelbutton.getScene().getWindow().hide();
    }
    /*-------------------MY RESUMES BUTTON-------------------*/
    public void myresume(ActionEvent event)throws IOException
    {
        LoginPageController m2 = new LoginPageController();
        FileReader fr = new FileReader("D:\\userlogin.txt");
        BufferedReader br = new BufferedReader(fr);
        String user;
        user = br.readLine();
        System.out.println(user);
        empname.setText(user);
        br.close();
        fr.close();
        File user_file = new File("D:\\Resumes\\"+user);
        if (!user_file.exists()) {
            if (user_file.mkdir())
            {
                System.out.println("Directory is created!");
            }
            else
            {
                System.out.println("Failed to create directory!");
            }
        }
        String folderPath = "D:\\Resumes\\" + user + "\\";
        list.getItems().clear();
        // Get a list of all the files in the folder
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        // Iterate over the files and read the images
        if (folder.listFiles().length == 0)
        {
            nofile.setText("No Resumes");
        }
        else
        {
            for (File file : files)
            {
                if (file.isFile())
                {
                    String fname = file.getName();
                    list.getItems().add(fname.substring(0, fname.indexOf(".")));
                }
            }
         }
    }
    /*-------------------OPEN BUTTON-------------------*/
    public void openbtn(ActionEvent event)throws IOException
    {
        FileReader fr = new FileReader("D:\\userlogin.txt");
        BufferedReader br = new BufferedReader(fr);
        String user;
        user = br.readLine();
        br.close();
        fr.close();
        Desktop d = Desktop.getDesktop();
        ObservableList selectedIndices = list.getSelectionModel().getSelectedItems();
        for(Object o : selectedIndices)
        {
            File file = new File("D:\\Resumes\\"+user+"\\"+o.toString()+".docx");
            if(file.exists())
            {
                d.open(file);
                info.setText("");
            }
            else
            {
                info.setText("select a file before opening");
            }
        }
    }
    /*-------------------CREATE RESUME BUTTON-------------------*/
    public void createresume(ActionEvent event)throws IOException
    {
        LoginPageController m2=new LoginPageController();
        m2.changeScene2("CreateResumefxml.fxml");
    }
    public void delete(ActionEvent event)throws IOException
    {
        FileReader fr = new FileReader("D:\\userlogin.txt");
        BufferedReader br = new BufferedReader(fr);
        String user;
        user = br.readLine();
        br.close();
        fr.close();
        Desktop d = Desktop.getDesktop();
        ObservableList selectedIndices = list.getSelectionModel().getSelectedItems();
        for(Object o : selectedIndices)
        {
            File file = new File("D:\\Resumes\\"+user+"\\"+o.toString()+".docx");
            if(file.exists())
            {
                file.delete();
                info.setText("");
            }
            else if(o.toString().isEmpty())
            {
                info.setText("select a file before deleting");
            }
        }
        String folderPath = "D:\\Resumes\\" + user + "\\";
        list.getItems().clear();
        // Get a list of all the files in the folder
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        // Iterate over the files and read the images
        if (folder.listFiles().length == 0)
        {
            nofile.setText("No Resumes");
        }
        else
        {
            for (File file : files)
            {
                if (file.isFile())
                {
                    String fname = file.getName();
                    list.getItems().add(fname.substring(0, fname.indexOf(".")));
                }
            }
        }
    }
    /*-------------------SEND RESUME BUTTON-------------------*/
    public void sendresume(ActionEvent event)throws IOException
    {
        LoginPageController m2=new LoginPageController();
        m2.changeScene2("sendresumefxml.fxml");
    }
}

