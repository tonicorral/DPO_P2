package Presentation.Views;

import Presentation.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginGUI extends JPanel {

    private  JButton b1,textR;
    private JPanel generalPanel,titlePanel,userPanel,passwordPanel,buttonPanel,borderPanel;
    private JLabel userLabel, passwordLabel,titleLabel;
    private JTextField  userField;
    private JPasswordField passwordField;

    private ActionListener loginListener;

    private MainView mainView;

    public LoginGUI(MainView mainView){

        this.mainView = mainView;

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

        textR = new JButton("Not registered?");
        textR.setOpaque(false);
        textR.setContentAreaFilled(false);
        textR.setBorder(null);
        textR.setFont(new Font("Inter",Font.PLAIN,14));



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


        borderPanel = new JPanel(new BorderLayout());
        borderPanel.add("East",textR);
        generalPanel.add(borderPanel);



        //Center the GridLayout
        add(generalPanel, BorderLayout.CENTER);

        setVisible(true);

        textR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showSignUp();
            }
        });

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showMenu();
            }
        });


    }






}
