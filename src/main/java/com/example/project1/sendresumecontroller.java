package com.example.project1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class sendresumecontroller
{
    @FXML
    private ComboBox combox;
    @FXML
    private Label info;
    @FXML
    private TextField sender;
    @FXML
    private TextField password;
    @FXML
    private TextField recipient;
    @FXML
    private TextArea subject;
    @FXML
    private TextArea body;
    public void initialize() throws IOException {
        FileReader fr = new FileReader("D:\\userlogin.txt");
        BufferedReader br = new BufferedReader(fr);
        String user;
        user = br.readLine();
        System.out.println(user);;
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
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        // Iterate over the files and read the images
        if (folder.listFiles().length == 0)
        {
            System.out.println("no files");
        }
        else
        {
            for (File file : files)
            {
                if (file.isFile())
                {
                    String fname = file.getName();
                    combox.getItems().add(fname);
                }
            }
        }
        //String emptype=combobox.getValue().toString();
    }
    public void back(ActionEvent event) throws IOException
    {
        LoginPageController m2=new LoginPageController();
        m2.changeScene2("Employeefxml.fxml");
    }
    public void send(ActionEvent event) throws IOException
    {
        info.setText("Make sure your account has its app access turned on");
        if(sender.getText().isEmpty())
        {
            info.setText("Enter a sender's email id");
        }
        else if(password.getText().isEmpty())
        {
            info.setText("Enter a valid password");
        }
        else if(recipient.getText().isEmpty())
        {
            info.setText("Enter a recipient's email id");
        }
        else if(combox.getValue()==null)
        {
            info.setText("Select a resume to send");
        }
        else
        {
            // Set up the properties for connecting to the Gmail SMTP server
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.ssl.enable","true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            //props.put("mail.smtp.starttls.required", "true");
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            // Connect to the Gmail SMTP server using a session
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(sender.getText(), password.getText());
                        }
                    });
            session.setDebug(true);
            try {
                FileReader fr = new FileReader("D:\\userlogin.txt");
                BufferedReader br = new BufferedReader(fr);
                String user;
                user = br.readLine();
                br.close();
                fr.close();
                // Create a new email message
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(sender.getText()));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient.getText()));
                message.setSubject(subject.getText());
                // Create the message body part
                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setText(body.getText());
                // Create the multipart container for the attachment
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                // Create the attachment body part
                messageBodyPart = new MimeBodyPart();
                String filename = "D:\\Resumes\\"+user+"\\"+combox.getValue().toString();
                DataSource source = new FileDataSource(filename);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(filename);
                multipart.addBodyPart(messageBodyPart);
                // Set the multipart as the message's content
                message.setContent(multipart);
                // Send email.
                Transport.send(message);
                info.setText("Mail sent successfully!");
                System.out.println("Mail successfully sent");
                LoginPageController m2=new LoginPageController();
                m2.changeScene2("Employeefxml.fxml");
            } catch (AddressException ex) {
                info.setText(ex.getMessage());
                ex.printStackTrace();
            } catch (MessagingException ex) {
                info.setText(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}


