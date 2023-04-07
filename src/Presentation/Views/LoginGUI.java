package Presentation.Views;

import javax.swing.*;
import java.awt.*;


public class LoginGUI extends JFrame {

    JButton b1;
    JPanel generalPanel,titlePanel,userPanel,passwordPanel,buttonPanel;
    JLabel userLabel, passwordLabel,titleLabel;
    JTextField  userField;
    JPasswordField passwordField;


    public LoginGUI(){
        this.setSize(819,642);
        this.setTitle("Log in");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);


        this.setVisible(true);
    }

    public void showLogin(){

        //Creation

        //Title: Iniciar Sessió
        titleLabel = new JLabel("INICIAR SESSIÓ");
        titleLabel.setFont(new Font("Inter", Font.BOLD, 48));

        //User
        userLabel = new JLabel("Nom d'usuari o correu");
        userLabel.setFont(new Font("Inter", Font.BOLD, 13));
        userField = new JTextField();

        //Password
        passwordLabel = new JLabel("Contrasenya");
        passwordLabel.setFont(new Font("Inter", Font.BOLD, 13));
        passwordField = new JPasswordField();

        //Button
        b1 = new JButton("Iniciar sessió");
        b1.setBackground(new Color(124,136,248));
        b1.setFont(new Font("Inter",Font.PLAIN,24));
        b1.setForeground(Color.white);


        //Add to the layouts
        generalPanel = new JPanel(new GridLayout(6, 1,20,20));


        //Title
        titlePanel = new JPanel(new FlowLayout());
        titlePanel.add(titleLabel);
        generalPanel.add(titlePanel);

        //User
        userPanel = new JPanel(new GridLayout(2,1,5,5));
        userPanel.add(userLabel);
        userPanel.add(userField);
        generalPanel.add(userPanel);

        //Password
        passwordPanel = new JPanel(new GridLayout(2,1,5,5));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        generalPanel.add(passwordPanel);

        //Button
        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(b1);
        generalPanel.add(buttonPanel);
        generalPanel.setBorder(BorderFactory.createEmptyBorder(100,200,20,200));


        //Center the GridLayout
        add(generalPanel, BorderLayout.CENTER);

        setVisible(true);
    }


}
