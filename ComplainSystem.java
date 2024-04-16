import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class ComplainSystem extends JFrame {
    private String selectedImagePath = "";
    private JButton button, button2;
    private ArrayList<Office> offices = new ArrayList<>();

    private ArrayList<Student> students = new ArrayList<>();

    public ComplainSystem() {

        //creating offices
        createOfficesFromCSV("offices.csv");

        //creating students
        createStudentsFromCSV("students.csv");

        setTitle("Main Frame");
        setSize(700, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(20));
        JLabel ask = new JLabel("Enter user type");
        ask.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(ask);
        add(Box.createVerticalStrut(25));

        //to add a panel so that we have two buttons, students and stuff login
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        button = new JButton("STUDENT");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(button);
        //skip some space
        mainPanel.add(Box.createVerticalStrut(20));
        //creating stuff button
        button2 = new JButton("STUFF");     //to add action to this button
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(button2);

        //adding panel to the frame
        add(mainPanel);

        //action setting for button2
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent n) {
                //upon selecting this, main frame should be hidden
                setVisible(false);

                //creating new frame, login for stuff
                JFrame stuffFrame = new JFrame("STUFF LOGIN");
                stuffFrame.setSize(700, 300);
                stuffFrame.setLocationRelativeTo(ComplainSystem.this);
                stuffFrame.setLayout(new FlowLayout());
                stuffFrame.getContentPane().setBackground(Color.decode("#ADD8E6"));
                JPasswordField passwordField = new JPasswordField();
                passwordField.setEchoChar('*');

                //adding fields to the frame
                JLabel stuffNameLab = new JLabel("Department Name :");
                JTextField stuffName = new JTextField(20);
                //String username = JOptionPane.showInputDialog("Enter your username");
                JLabel passwordLab = new JLabel("Password :");
                JTextField stuffPassword = new JTextField(20);

                JPanel stuffLogPanel = new JPanel();
                stuffLogPanel.setOpaque(false);
                stuffLogPanel.setLayout(new BoxLayout(stuffLogPanel, BoxLayout.Y_AXIS));
                
                JLabel msg = new JLabel("LOGIN HERE");
                stuffLogPanel.add(msg); // upon selecting this  button, user should see their menu

                stuffLogPanel.add(Box.createVerticalStrut(10));
        
                //adding to panel
                stuffLogPanel.add(stuffNameLab);
                stuffLogPanel.add(stuffName);
                stuffLogPanel.add(Box.createVerticalStrut(10));
                stuffLogPanel.add(passwordLab);
                stuffLogPanel.add(stuffPassword);
                stuffLogPanel.add(Box.createVerticalStrut(10));

                //adding panel to frame
                // stuffFrame.add(stuffLogPanel); 
                
                //button in the login
                //setting the button action
                JButton stuffOK = new JButton("OK");
                stuffOK.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent s){
                        
                        //creating a stuff menu frame
                        stuffFrame.setVisible(false);
                        //creating new frame
                        JFrame stuffMenu = new JFrame("Stuff Menu");
                        //aligning the frame to center
                        // studentsMenu.setLocationRelativeTo(null);
                        stuffMenu.setLocation(340, 250);

                        stuffMenu.setSize(700, 300);
                        stuffMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        stuffMenu.getContentPane().setBackground(Color.decode("#ADD8E6"));
                        stuffMenu.setLayout(new FlowLayout());
                        //other stuff in the frame
                        JPanel stuffMenuPanel = new JPanel();
                        stuffMenuPanel.setOpaque(false); //make panel transparent
                        stuffMenuPanel.setLayout(new BoxLayout(stuffMenuPanel, BoxLayout.Y_AXIS));
                        
                        Office chosenOffice = null;
                        for (Office off : offices){
                            if (off.getDepartmentName().equals(stuffName.getText()) && off.getPassword().equals(stuffPassword.getText())){
                                chosenOffice = off;
                                break;
                            }
                        }
                        if (chosenOffice == null) {
                            JOptionPane.showMessageDialog(stuffOK, "Invalid department name or password");
                            System.exit(0);
                        }
                        final Office chosenOfficeFinal = chosenOffice;
                        JPanel inboxPanel = new JPanel();
                        inboxPanel.setLayout(new BoxLayout(inboxPanel, BoxLayout.Y_AXIS));
                        //variable to store the formated string
                        String inbox = "Inbox Messages: \n";
                        for (Message msg : chosenOfficeFinal.viewMessages("inbox")) {
                            String curr = String.format("From: %s\nMessage: %s\n", msg.getSender().getUserName(), msg.getText());
                            displayImage(msg.getImage(), inboxPanel);
                            JButton response = new JButton("Response");
                            response.setAlignmentX(Component.CENTER_ALIGNMENT);
                            response.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent f) {
                                    chosenOfficeFinal.respond((Complaint) msg, JOptionPane.showInputDialog("Enter response"));
                                    JOptionPane.showMessageDialog(response, "Response sent");
                                }
                            });
                            JLabel currLabel = new JLabel(curr);
                            currLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                            inboxPanel.add(currLabel);
                            inboxPanel.add(response);
                        }
                        inboxPanel.setOpaque(false);
                        stuffMenuPanel.add(inboxPanel);
                        stuffMenu.add(stuffMenuPanel);
                        stuffMenu.setVisible(true);
                        stuffMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only the new frame
                        stuffMenu.addWindowListener(new WindowAdapter() {
                            public void windowClosed(WindowEvent g) {
                                stuffFrame.setVisible(true); // Show the main frame when the new frame is closed
                            }
                        });
                    }
                });

                stuffLogPanel.add(stuffOK);
                stuffFrame.add(stuffLogPanel); 
                

                stuffFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only the new frame
                stuffFrame.addWindowListener(new WindowAdapter() {
                    public void windowClosed(WindowEvent n) {
                        setVisible(true); // Show the main frame when the new frame is closed
                    }
                });
                //making frame visible
                stuffFrame.setVisible(true);
                //to add the next frame with the methods in the stuff/offices class
                
            }
        });


        //action setting for button
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Hide the main frame
                
                JFrame newFrame = new JFrame("LOGIN");
                newFrame.setSize(700, 300);
                newFrame.setLocationRelativeTo(ComplainSystem.this);
                newFrame.setLayout(new FlowLayout());
                newFrame.getContentPane().setBackground(Color.decode("#ADD8E6"));
                JPasswordField passField = new JPasswordField();
                passField.setEchoChar('*');

                // login labels
                JLabel userLab = new JLabel("Username :");
                JTextField studentName = new JTextField(20);
                //String username = JOptionPane.showInputDialog("Enter your username");
                JLabel passLab = new JLabel("Password :");
                JTextField studentPassword = new JTextField(20);
                //String password = JOptionPane.showInputDialog(passField, "Enter Password");
                // JPasswordField passwordField = new JPasswordField();
                // int choice = JOptionPane.showConfirmDialog(null, passwordField, "Enter Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                //login panel
                JPanel loginPanel = new JPanel();
                loginPanel.setOpaque(false);
                loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
                //loginPanel.setBackground(Color.BLACK);
                

                JLabel mes = new JLabel("LOGIN HERE");
                loginPanel.add(mes); // upon selecting this  button, user should see their menu

                loginPanel.add(Box.createVerticalStrut(10));

                //adding to panel
                loginPanel.add(userLab);
                loginPanel.add(studentName);
                loginPanel.add(Box.createVerticalStrut(10));
                loginPanel.add(passLab);
                loginPanel.add(studentPassword);
                loginPanel.add(Box.createVerticalStrut(10));
                
                // button inside new window
                JButton submit = new JButton("OK");

                //setting the button action
                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent a){
                        //instantiate a student object
                        newFrame.setVisible(false);
                        Student studentTemp = null;
                        for (Student s : students){
                            if (s.getUserName().equals(studentName.getText()) && s.getPassword().equals(studentPassword.getText())){
                                studentTemp = s;
                                break;
                            }
                        }
                        if (studentTemp == null) {
                            JOptionPane.showMessageDialog(submit, "Invalid username or password");
                            System.exit(0);
                        }
                        final Student student = studentTemp;
                        JOptionPane.showMessageDialog(submit, String.format("Welcome %s", student.getName()));
                        //this button should lead to the Students menu 
                        //creating a students menu frame
                        newFrame.setVisible(false);
                        //creating new frame
                        JFrame studentsMenu = new JFrame("Student's Menu");
                        //aligning the frame to center
                        // studentsMenu.setLocationRelativeTo(null);
                        studentsMenu.setLocation(340, 250);

                        studentsMenu.setSize(700, 300);
                        studentsMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        studentsMenu.getContentPane().setBackground(Color.decode("#ADD8E6"));
                        studentsMenu.setLayout(new FlowLayout());
                        studentsMenu.addWindowListener(new WindowAdapter() {
                            public void windowClosed(WindowEvent n) {
                                newFrame.setVisible(true); // Show the main frame when the new frame is closed
                            }
                        });
                        //other stuff in the frame
                        JPanel menuPanel = new JPanel();
                        menuPanel.setOpaque(false); //make panel transparent
                        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
                        //send message button
                        JButton sendMesg = new JButton("Message Here");
                        sendMesg.setPreferredSize(new Dimension(100, 100));
                        
                        //button action
                        sendMesg.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent x) {
                                studentsMenu.setVisible(false);
                                JFrame newFrame = new JFrame("SEND");
                                newFrame.setSize(1200, 600);
                                newFrame.setLocationRelativeTo(ComplainSystem.this);
                                newFrame.setLayout(new FlowLayout());
                                newFrame.getContentPane().setBackground(Color.decode("#ADD8E6"));
                                JPanel sendPanel = new JPanel();
                                JScrollPane scrollPane = new JScrollPane(sendPanel);
                                scrollPane.setPreferredSize(new Dimension(1000, 500));
                                scrollPane.setOpaque(false);
                                scrollPane.getViewport().setOpaque(false);
                                sendPanel.setLayout(new BoxLayout(sendPanel, BoxLayout.Y_AXIS));
                                // JOptionPane.showMessageDialog(Test.this, "Button clicked!");
                                JLabel messageLabel = new JLabel("Enter Message Here");
                                sendPanel.add(messageLabel);
                                JTextArea message = new JTextArea(10,10);
                                message.setLineWrap(true);
                                JScrollPane scroll = new JScrollPane(message);
                                sendPanel.add(scroll);
                                //storing the available office 
                                String[] officeNames = new String[offices.size()];
                                for (Office o : offices){
                                    officeNames[offices.indexOf(o)] = o.getDepartmentName();
                                }
                                //select office dropdown field in the GUI
                                JComboBox<String> office = new JComboBox<String>(officeNames);
                                sendPanel.add(office);
                                
                                //select category dropdown field in the GUI
                                JComboBox<Message.Category> category = new JComboBox<Message.Category>(Message.Category.values());
                                sendPanel.add(category);
                                Message.Category selectedCategory = (Message.Category) category.getSelectedItem();
                                //select image field in the GUI
                                JButton image = new JButton("Select Image");
                                image.setAlignmentX(Component.CENTER_ALIGNMENT);
                                sendPanel.add(image);
                                String[] selectedImage = {""}; // Initialize with an empty string

                                // Select image button action listener
                                image.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent c) {
                                        JFileChooser fileChooser = new JFileChooser();
                                        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
                                        fileChooser.setFileFilter(filter);
                        
                                        int result = fileChooser.showOpenDialog(null);
                                        if (result == JFileChooser.APPROVE_OPTION) {
                                            File selectedFile = fileChooser.getSelectedFile();
                                            selectedImagePath = selectedFile.getAbsolutePath(); // Update the selected image path
                                            displayImage(selectedImagePath, sendPanel); // Display the selected image
                        
                                            // Perform any operations that rely on the selected path here
                                            System.out.println(selectedImagePath); // Print the selected image path
                                              //send message to the office
                                            JButton send = new JButton("Send");
                                            send.setAlignmentX(Component.CENTER_ALIGNMENT);
                                            send.setPreferredSize(new Dimension(100, 50));
                                            sendPanel.add(send);
                                            Office chosenOffice = null;
                                            for (Office o : offices) {
                                                if (o.getDepartmentName().equals(office.getSelectedItem())) {
                                                    chosenOffice = o;
                                                    break;
                                                }
                                            }
                                            System.out.printf("It's me %s\n",selectedImage[0]);
                                            final Office chosenOfficeFinal = chosenOffice;
                                            final Message.Category selectedCategoryFinal = selectedCategory;
                                            final String selectedImageFinal = getSelectedImagePath();
                                            System.out.printf("Ndini %s\n",selectedImageFinal);
                                            final JTextArea messageFinal = message;
                                            send.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent v) {
                                                    // JOptionPane.showMessageDialog(Test.this, "Button clicked!");
                                                    student.send(chosenOfficeFinal, messageFinal.getText(), selectedImageFinal, selectedCategoryFinal);
                                                    JOptionPane.showMessageDialog(send, String.format("Message sent to %s", chosenOfficeFinal.getDepartmentName()));
                                                    Office office = chosenOfficeFinal;
                                                }
                                            });
                                        }
                                    }
                                });


                              
                                sendPanel.setOpaque(false);
                                newFrame.add(scrollPane);
                                newFrame.setVisible(true);
                                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only the new frame
                                newFrame.addWindowListener(new WindowAdapter() {
                                    public void windowClosed(WindowEvent b) {
                                        studentsMenu.setVisible(true); // Show the newFrame when the studentsMenu frame is closed
                                    }
                                });

                            }
                        });

                        // view inbox button
                        JButton viewInbox = new JButton("Inbox");
                        viewInbox.setPreferredSize(new Dimension(100, 100));
                        viewInbox.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent d) {
                                studentsMenu.setVisible(false);
                                JFrame newFrame = new JFrame("SEND");
                                newFrame.setSize(1200, 600);
                                newFrame.setLayout(new FlowLayout());
                                newFrame.getContentPane().setBackground(Color.decode("#ADD8E6"));
                                JPanel inboxPanel = new JPanel();
                                inboxPanel.setLayout(new BoxLayout(inboxPanel, BoxLayout.Y_AXIS));
                                // JOptionPane.showMessageDialog(Test.this, "Button clicked!");
                                //inbox display
                                //variable to store the formated string
                                String inbox = "Inbox Messages: \n";
                                for (Message msg : student.viewMessages("inbox")) {
                                    String curr = String.format("From: %s\nMessage: %s\n", msg.getSender().getUserName(), msg.getText());
                                    JButton followUp = new JButton("Follow Up");
                                    followUp.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent f) {
                                            student.followUp(msg);
                                            JOptionPane.showMessageDialog(followUp, "Follow up message sent");
                                        }
                                    });
                                    inboxPanel.add(new JLabel(curr));
                                    inboxPanel.add(followUp);
                                }
                                inboxPanel.setOpaque(false);
                                newFrame.add(inboxPanel);
                                newFrame.setVisible(true);

                                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only the new frame
                                newFrame.addWindowListener(new WindowAdapter() {
                                    public void windowClosed(WindowEvent b) {
                                        studentsMenu.setVisible(true); // Show the newFrame when the studentsMenu frame is closed
                                    }
                                });
                            }
                        });

                        //view outbox button
                        // view inbox button
                        JButton viewOutbox = new JButton("Outbox");
                        viewOutbox.setPreferredSize(new Dimension(100, 100));
                        viewOutbox.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent f) {
                                studentsMenu.setVisible(false);
                                //outbox display
                                JFrame newFrame = new JFrame("SEND");
                                newFrame.setSize(1200, 600);
                                newFrame.setLayout(new FlowLayout());
                                newFrame.getContentPane().setBackground(Color.decode("#ADD8E6"));
                                JPanel outboxPanel = new JPanel();
                                outboxPanel.setLayout(new BoxLayout(outboxPanel, BoxLayout.Y_AXIS));

                                for (Message msg : student.viewMessages("outbox")) {
                                    String curr = String.format("To: %s\nMessage: %s\nCategory: %s\n", msg.getRecipient().getUserName(), msg.getText(), ((Complaint) msg).getCategory());
                                    outboxPanel.add(new JLabel(curr));
                                    System.out.println(msg.getImage());
                                    displayImage(msg.getImage(), outboxPanel);
                                }
                                outboxPanel.setOpaque(false);
                                newFrame.add(outboxPanel);
                                newFrame.setVisible(true);

                                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only the new frame
                                newFrame.addWindowListener(new WindowAdapter() {
                                    public void windowClosed(WindowEvent b) {
                                        studentsMenu.setVisible(true); // Show the newFrame when the studentsMenu frame is closed
                                    }
                                });
                            }
                        });

                        //labeling the menu frame
                        JLabel menuLabel = new JLabel("MENU");
                        menuLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        menuPanel.add(Box.createVerticalStrut(20));

                        //aligning items to the center
                        viewInbox.setAlignmentX(Component.CENTER_ALIGNMENT);
                        sendMesg.setAlignmentX(Component.CENTER_ALIGNMENT);
                        viewOutbox.setAlignmentX(Component.CENTER_ALIGNMENT);
                        //skipping spaces and adding buttons
                        menuPanel.add(menuLabel);
                        menuPanel.add(Box.createVerticalStrut(20));
                        menuPanel.add(sendMesg);
                        menuPanel.add(Box.createVerticalStrut(20));
                        menuPanel.add(viewInbox);
                        menuPanel.add(Box.createVerticalStrut(20));
                        menuPanel.add(viewOutbox);

                        //adding panel to frame
                        studentsMenu.add(menuPanel);


                        studentsMenu.addWindowListener(new WindowAdapter() {
                            public void windowClosed(WindowEvent b) {
                                newFrame.setVisible(true); // Show the newFrame when the studentsMenu frame is closed
                            }
                        });
                        studentsMenu.setVisible(true);
                    }
                });

                loginPanel.add(submit);

                newFrame.add(loginPanel);

                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only the new frame
                newFrame.addWindowListener(new WindowAdapter() {
                    public void windowClosed(WindowEvent e) {
                        setVisible(true); // Show the main frame when the new frame is closed
                    }
                });
                newFrame.setVisible(true);
            }
        });
    }

    private void displayImage(String image, JPanel panel) {
        try{
            //display the image in the panel
            ImageIcon oldImage = new ImageIcon(image);
            Image scaledImage = oldImage.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(imageLabel);
            panel.revalidate();
            panel.repaint();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error displaying image: " + e.getMessage());
        }
    }
    
    // Method to access the selected image path from outside the ActionListener
    public String getSelectedImagePath() {
        return selectedImagePath;
    }

    public void createOfficesFromCSV(String filename) {
        // Read the CSV file and create Office objects
        File file = new File(filename);
        //Read the header
        try{ 
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            // Add the Office objects to the offices array
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                Office office = new Office(data[0], data[1], data[2],data[3]);
                offices.add(office);
            }
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void createStudentsFromCSV(String filename) {
        // Read the CSV file and create Student objects
        File file = new File(filename);
        //Read the header
        try{
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            // Add the Student objects to the students array
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                Student student = new Student(data[0], data[1], data[2], data[3], data[4]);
                students.add(student);
            }
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ComplainSystem frame = new ComplainSystem();
        frame.setVisible(true);

    }
}


