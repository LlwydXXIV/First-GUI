package FinalPT;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class LoginMenu extends ShopInventoryWindow {

    private String Username = "TESTSHOP",Password = "12345";
    String inputUsername,inputPassword;
    int attempt = 0;

    void loginMenu() {

        System.out.println("SYSTEM: Class \"LoginMenu\" STARTED.");

            JFrame frame = new JFrame();

            final int vertSize = 300, horSize = 200;

            frame.setSize(vertSize,horSize);
            frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);

        try {
            frame.setIconImage((Image) ImageIO.read(getClass().getResourceAsStream("icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

            JLabel labelUsername = new JLabel("Username:");
            labelUsername.setBounds(50, 20, 100, 25);
            JLabel labelPassword = new JLabel("Password:");
            labelPassword.setBounds(50, 70, 100, 25);
            JLabel labelattempt = new JLabel(" attempt: " + attempt);
            labelattempt.setBackground(Color.WHITE);
            labelattempt.setBounds(110,100,70,25);

            JTextField textFieldInputUsername = new JTextField();
            textFieldInputUsername.setBounds(120, 20, 120, 25);
            JTextField textFieldInputPassword = new JTextField();
            textFieldInputPassword.setBounds(120, 70, 120, 25);

            JButton Enter = new JButton("Enter");
            Enter.setBounds(90, 125, 100, 30);
            Enter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.err.println("\nSYSTEM: BUTTON GOT CLICKED!!!");
                    attempt++;

                    inputUsername = textFieldInputUsername.getText().toUpperCase();
                    inputPassword = textFieldInputPassword.getText();
                    System.out.println("SYSTEM: Username & Password entered:(" + inputUsername + "," + inputPassword + ")");
                    System.out.println();

                    if (inputUsername.equals(Username) && inputPassword.equals(Password)) {
                        System.out.println("SYSTEM: Logged-in.");
                        frame.dispose();
                        System.out.println("SYSTEM: Frame Disposed.\n");
                        shopInventoryWindow();
                    }

                    if (attempt == 1) {
                        labelattempt.setText("attempt: " + attempt);
                        textFieldInputPassword.setText("");
                        textFieldInputUsername.setText("");
                    } else if (attempt == 2) {
                        labelattempt.setText("attempt: " + attempt);
                        textFieldInputPassword.setText("");
                        textFieldInputUsername.setText("");
                    } else if (attempt == 3) {
                        labelattempt.setFont(new Font("Arial",Font.BOLD,14));
                        labelattempt.setText("attempt: " + attempt + " (last attempt)");
                        labelattempt.setForeground(Color.RED);
                        labelattempt.setBounds(60, 100, 250, 25);
                    } else if (attempt == 4) {
                        System.out.println("SYSTEM: " + attempt + " attempts.");
                        frame.dispose();
                    }

                }
            });

            JPanel panel = new JPanel();
            panel.setBackground(Color.lightGray);
            panel.setLayout(null);

            panel.add(labelUsername);
            panel.add(labelPassword);
            panel.add(textFieldInputUsername);
            panel.add(textFieldInputPassword);
            panel.add(labelattempt);
            panel.add(Enter);

            frame.add(panel);
            frame.revalidate();
            frame.revalidate();
            frame.setVisible(true);
        }

        //main method
        public void main(String[] args) {
            loginMenu();
        }
}
