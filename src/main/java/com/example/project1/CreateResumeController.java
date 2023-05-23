package com.example.project1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
public class CreateResumeController {
    @FXML
    Label info;
    @FXML
    TextField name1;
    @FXML
    TextField name2;
    @FXML
    TextField email;
    @FXML
    TextField number;
    @FXML
    TextField link1;
    @FXML
    TextField link2;
    @FXML
    TextField link3;
    @FXML
    TextField address1;
    @FXML
    TextField address2;
    @FXML
    TextField address3;
    @FXML
    TextField filename;
    @FXML
    TextArea content;
    @FXML
    TextField category;

    //ObservableList<String> list= FXCollections.observableArrayList("Summary","Publications","Education","Experience","Projects","Courses","Honors","Skills");
    /*-------------------CANCEL BUTTON-------------------*/
    public void cancel(ActionEvent event) throws IOException
    {
        FileReader fr = new FileReader("D:\\userlogin.txt");
        BufferedReader br = new BufferedReader(fr);
        String user;
        user = br.readLine();
        br.close();
        fr.close();
        int res_num=0;
        String filename="resume";
        File file = new File("D:\\Resumes\\" + user +"\\"+filename+".docx");
        while(file.exists())
        {
            res_num+=1;
            filename="resume"+res_num;
            file = new File("D:\\Resumes\\" + user +"\\"+filename+".docx");
        }
        res_num-=1;
        if(res_num!=0)
        {
            filename="resume"+res_num;
        }
        else if(res_num==0)
        {
            filename="resume";
        }
        file = new File("D:\\Resumes\\" + user +"\\"+filename+".docx");
        if(file.exists())
            file.delete();
        LoginPageController m2=new LoginPageController();
        m2.changeScene2("Employeefxml.fxml");
    }
    /*-------------------NEXT BUTTON-------------------*/
    public void next(ActionEvent event) throws IOException {
        {
            try {
                // RESUME INPUTS SLOT1
                String f_name = name1.getText();
                String l_name = name2.getText();
                String email_id = email.getText();
                String ph_number = number.getText();
                String link_1 = link1.getText();
                String link_2 = link2.getText();
                String link_3 = link3.getText();
                String linkid_1 = address1.getText();
                String linkid_2 = address2.getText();
                String linkid_3 = address3.getText();
                /*-----------------------Reading username from textfile-----------------------*/
                FileReader fr = new FileReader("D:\\userlogin.txt");
                BufferedReader br = new BufferedReader(fr);
                String user;
                user = br.readLine();
                br.close();
                fr.close();
                String folderPath = "D:\\Resumes\\" + user + "\\";
                // Get a list of all the files in the folder
                File folder = new File(folderPath);
                String filename= "resume";
                int res_num=0;
                File file = new File("D:\\Resumes\\" + user + "\\"+filename+".docx");
                while(file.exists())
                {
                    res_num+=1;
                    filename="resume"+res_num;
                    file = new File("D:\\Resumes\\" + user +"\\"+filename+".docx");
                }
                /*-----------------------INSERTING BASIC INFORMATION-----------------------*/
                XWPFDocument doc = new XWPFDocument(new FileInputStream("D:\\Templates\\Template1.docx"));
                for (XWPFParagraph p : doc.getParagraphs())
                {
                    // Iterate through the runs
                    for (XWPFRun r : p.getRuns())
                    {
                        // Get the text in the run
                        String text = r.getText(0);
                        if (text != null && text.contains("FIRSTNAME")&&!f_name.isEmpty())
                        {
                            // Replace the text
                            text = text.replace("FIRSTNAME", f_name);
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("LASTNAME")&&!l_name.isEmpty())
                        {
                            text = text.replace("LASTNAME", l_name);
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("id"))
                        {
                            text = text.replace("id", email_id);
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("phnumber"))
                        {
                            text = text.replace("phnumber", ph_number);
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("LINK1"))
                        {
                            text = text.replace("LINK1", link_1);
                            r.setText(text, 0);
                        }
                        else if (text != null && text.contains("LINK1:add1")&&link_1.isEmpty())
                        {
                            text = text.replace("LINK1:add1", "");
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("LINK2"))
                        {
                            text = text.replace("LINK2", link_2);
                            r.setText(text, 0);
                        }
                        else if (text != null && text.contains(":")&&link_2.isEmpty())
                        {
                            text = text.replace("LINK2:add2", "");
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("LINK3"))
                        {
                            text = text.replace("LINK3", link_3);
                            r.setText(text, 0);
                        }
                        else if (text != null && text.contains(":")&&link_3.isEmpty())
                        {
                            text = text.replace("LINK3:add3", "");
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("add1"))
                        {
                            text = text.replace("add1", linkid_1);
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("add2"))
                        {
                            text = text.replace("add2", linkid_2);
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("add3"))
                        {
                            text = text.replace("add3", linkid_3);
                            r.setText(text, 0);
                        }
                        FileOutputStream out = new FileOutputStream("D:\\Resumes\\" + user +"\\"+filename+".docx");
                        doc.write(out);
                        out.close();
                        //change scene
                        LoginPageController m2 = new LoginPageController();
                        m2.changeScene2("CreateResume2fxml.fxml");
                        // Iterate through all the paragraphs
                    }
                }
            } catch (Exception e) {}
        }
    }
    /*-------------------ADD BUTTON TO ADD SUB HEADINGS ALONG WITH CONTENT-------------------*/
    public void add(ActionEvent event) throws IOException{
        /* READ USERNAME*/
        FileReader fr = new FileReader("D:\\userlogin.txt");
        BufferedReader br = new BufferedReader(fr);
        String user;
        user = br.readLine();
        br.close();
        fr.close();
        /*Creating file*/
        String filename="resume";
        int res_num=0;
        File file = new File("D:\\Resumes\\" + user +"\\"+filename+".docx");
        while(file.exists())
        {
            res_num+=1;
            filename="resume"+res_num;
            file = new File("D:\\Resumes\\" + user +"\\"+filename+".docx");
        }
        res_num-=1;
        if(res_num!=0)
        {
            filename="resume"+res_num;
        }
        else if(res_num==0)
        {
            filename="resume";
        }
        String category_item=category.getText();
        FileInputStream fis = new FileInputStream("D:\\Resumes\\" + user +"\\"+filename+".docx");
        XWPFDocument doc = new XWPFDocument(fis);
        XWPFParagraph p = doc.createParagraph();
        XWPFRun run1 = p.createRun();
        run1.setText(category_item);
        run1.setFontFamily("Arial");
        run1.setFontSize(9);
        run1.setBold(true);
        run1.setUnderline(UnderlinePatterns.SINGLE);
        run1.addBreak();
        XWPFRun run2 = p.createRun();
        run2.setText(content.getText());
        run2.setFontFamily("Arial");
        run2.setFontSize(9);
        info.setText("To add another heading clear ");
        FileOutputStream fos = new FileOutputStream("D:\\Resumes\\" + user +"\\"+filename+".docx");
        doc.write(fos);
        fos.close();
        fis.close();
        FileOutputStream out = new FileOutputStream("D:\\Resumes\\" + user +"\\"+filename+".docx");
        doc.write(out);
        out.close();
        content.clear();
        category.clear();
    }
    public void create(ActionEvent event)throws IOException
    {
        LoginPageController m2=new LoginPageController();
        m2.changeScene2("Employeefxml.fxml");
    }
}


