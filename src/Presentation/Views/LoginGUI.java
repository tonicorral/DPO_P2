package Presentation.Views;

import Presentation.Controllers.LoginController;
import Presentation.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class LoginGUI extends JPanel {

    private boolean goodLogin = true;
    private  JButton b1,textR;
    private JPanel generalPanel,titlePanel,userPanel,passwordPanel,buttonPanel,borderPanel;
    private JLabel userLabel, passwordLabel,titleLabel,badPassword;
    private JTextField  userField;
    private JPasswordField passwordField;

    private ActionListener loginListener;

    private MainView mainView;

    private LoginController loginController;

    public static final String LOGIN_BTN = "LOGIN_BTN";
    public static final String LOGIN_BACK_BTN = "LOGIN_BACK_BTN";

    public LoginGUI(){

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
        b1.setActionCommand("LOGIN_BTN");

        textR = new JButton("Not registered?");
        textR.setOpaque(false);
        textR.setContentAreaFilled(false);
        textR.setBorder(null);
        textR.setFont(new Font("Inter",Font.PLAIN,14));
        textR.setActionCommand("LOGIN_BACK_BTN");



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
        passwordPanel = new JPanel(new GridLayout(3,1,5,5));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        generalPanel.add(passwordPanel);


        //Button
        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(b1);
        generalPanel.add(buttonPanel);
        generalPanel.setBorder(BorderFactory.createEmptyBorder(100,200,20,200));


        borderPanel = new JPanel(new BorderLayout());
        borderPanel.add("East",textR);
        generalPanel.add(borderPanel);



        //Center the GridLayout
        add(generalPanel, BorderLayout.CENTER);





        setVisible(true);
    }

    public String getUser(){
        return userField.getText();
    }

    public String getPassword(){
        return passwordField.getText();
    }

    public void LoginController(ActionListener listener) {
        this.b1.addActionListener(listener);
    }

    public void registerController(ActionListener listener) {
        this.textR.addActionListener(listener);
    }

    public void clear () {
        this.userField.setText("");
        this.passwordField.setText("");
    }




}
