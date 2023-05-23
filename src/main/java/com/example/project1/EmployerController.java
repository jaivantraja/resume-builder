package com.example.project1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javax.mail.*;
import javax.mail.search.OrTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public class EmployerController {
    @FXML
    private ListView list;
    @FXML
    private Label empname;
    @FXML
    private Label info;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button cancelbutton;
    @FXML
    private CheckBox viewbtn;

    public void exit(ActionEvent event) throws IOException {
        File file = new File("D:\\userlogin.txt");
        File file2 = new File("D:\\useremail.txt");
        File file3 = new File("D:\\usermail.txt");
        if(file3.delete())
            System.out.println(file.getName() + " is deleted!");
        else
            System.out.println("Delete operation is failed.");
        if (file.delete())
            System.out.println(file.getName() + " is deleted!");
        else
            System.out.println("Delete operation is failed.");
        if (file2.delete())
            System.out.println(file.getName() + " is deleted!");
        else
            System.out.println("Delete operation is failed.");
        cancelbutton.getScene().getWindow().hide();
    }
    public void resume(ActionEvent event) throws IOException, MessagingException {
        if(username.getText().isEmpty()||password.getText().toString().isEmpty())
        {
            info.setText("Invalid username or password");
        }
        else
        {
            info.setText("Loading.....");
            LoginPageController m2 = new LoginPageController();
            FileReader fr = new FileReader("D:\\userlogin.txt");
            BufferedReader br = new BufferedReader(fr);
            String user;
            user = br.readLine();
            System.out.println(user);
            empname.setText(user);
            br.close();
            fr.close();
            File f=new File("D:\\useremail.txt");
            if(f.exists())
                f.delete();
            FileWriter writer = new FileWriter("D:\\useremail.txt");
            // Set up the properties for connecting to the Gmail SMTP server
            info.setText("Fetching through the mails");
            Properties props = new Properties();
            props.put("mail.store.protocol", "imap");
            props.put("mail.imap.host", "imap.gmail.com");
            props.put("mail.imap.socketFactory.port", "993");
            props.put("mail.imap.ssl.enable", "true");
            props.put("mail.imap.auth", "true");
            props.put("mail.imap.starttls.enable", "true");
            //props.put("mail.smtp.starttls.required", "true");
            props.put("mail.imap.ssl.protocols", "TLSv1.2");
            props.put("mail.imap.port", "465");
            props.put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            Session session = Session.getDefaultInstance(props);
            // Connect to the IMAP store
            Store store = session.getStore("imap");
            store.connect("imap.gmail.com", username.getText(), password.getText());
            // Open the inbox folder
            Folder inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_ONLY);
            // Search for emails with the keyword "example" in the subject
            SubjectTerm term = new SubjectTerm("Job");
            SubjectTerm term1 = new SubjectTerm("Resume");
            SubjectTerm term2 = new SubjectTerm("job");
            SubjectTerm term3 = new SubjectTerm("resume");
            SearchTerm searchTerm=new OrTerm(term,term1);
            SearchTerm searchTerm2=new OrTerm(term2,term3);
            SearchTerm searchTerm3=new OrTerm(searchTerm,searchTerm2);
            Message[] messages = inbox.search(searchTerm3);
            System.out.println(messages.length);
            if(messages.length==0)
            {
                info.setText("No mails found!");
            }
            // SEARCHING THROUGH MAILS FOR ATTACHMENTS
            for (Message message : messages) {
                //search
                if (!inbox.isOpen()) {
                    inbox.open(Folder.READ_ONLY);
                }
                System.out.println(message.getReceivedDate());
                if (message.isMimeType("multipart/*")) {
                    Multipart multipart = (Multipart) message.getContent();
                    for (int i = 0; i < multipart.getCount(); i++) {
                        BodyPart bodyPart = multipart.getBodyPart(i);
                        if (!Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())) {
                            continue;
                        }
                        System.out.println(bodyPart.getFileName());
                        list.getItems().add(i+bodyPart.getFileName());
                        writer.write(bodyPart.getFileName());
                    }
                    if(multipart.getCount()==0)
                    {
                        info.setText("NO ATTACHMENTS FOUND!");
                    }
                    else{
                        info.setText("Click attachment to view ");
                    }
                    // Close the folder and store
                    inbox.close(false);
                    store.close();
                }
            }
            writer.close();
            /*------------------------ PRESS O TO OPEN A RESUME----------------------*/
            list.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode() == KeyCode.O) {
                        String selectedItem = list.getSelectionModel().getSelectedItem().toString();
                        if (selectedItem != null) {
                            try {
                                FileReader fr = new FileReader("D:\\useremail.txt");
                                String filePath = "D:\\useremail.txt";
                                // Read all lines from the file
                                List<String> lines = Files.readAllLines(Paths.get(filePath));
                                // Print the lines
                                for (String line : lines) {
                                    if(line.contains(selectedItem.toString()))
                                    {
                                        System.out.println(line);
                                    }
                                }
                                // Open the selected file
                                String fname=selectedItem.toString().substring(1);
                                File file = new File(fname);
                                Desktop.getDesktop().open(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
            /*------------------------ PRESS R TO REPLY TO A RESUME----------------------*/
            /*
            list.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode() == KeyCode.R) {
                        String selectedItem = list.getSelectionModel().getSelectedItem().toString();
                        if (selectedItem != null) {
                            try {
                                MimeMessage reply = new MimeMessage(session);
                                reply.setSubject("Re: " + message.getSubject());
                                reply.setFrom(new InternetAddress("sender@example.com"));
                                reply.setRecipient(Message.RecipientType.TO, message.getFrom()[0]);
                                reply.setText("Thank you for your email. I have received your message and will respond as soon as possible.");
                                // Send the reply
                                Transport.send(reply);
                                // Open the selected file
                                File file = new File(selectedItem);
                                Desktop.getDesktop().open(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (AddressException e) {
                                throw new RuntimeException(e);
                            } catch (MessagingException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            });*/
        }
    }
    public void view(ActionEvent event)throws IOException{
        if (viewbtn.isSelected()&&password.getText().toString().isEmpty()==false)
        {
            info.setText("password:"+password.getText().toString());
        }
        else if (password.getText().toString().isEmpty()==true)
        {
            info.setText("Empty Field");
        }
        else
        {
            info.setText("");
        }
    }
}
