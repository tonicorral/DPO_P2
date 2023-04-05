package Presentation;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {

    JButton b1;
    JPanel newPanel,panel2,panel1,panel3,panel4;
    JLabel userLabel, passLabel,title;
    JTextField  textField1;
    JTextField textField2;


    public LoginGUI(){
        this.setSize(819,642);
        this.setTitle("Log in");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }

    public void showLogin(){

        Color buttonColor = new Color(124,136,248);

        title = new JLabel("INICIAR SESSIÓ");
        title.setFont(new Font("Inter", Font.PLAIN, 48));

        userLabel = new JLabel("Nom d'usuari o correu");
        textField1 = new JTextField();


        passLabel = new JLabel("Contrasenya");
        textField2 = new JPasswordField();


        b1 = new JButton("Iniciar sessió");
        //b1.setPreferredSize(new Dimension(150,50));
        b1.setBackground(buttonColor);
        b1.setFont(new Font("Inter",Font.PLAIN,24));
        b1.setForeground(Color.white);



        newPanel = new JPanel(new GridLayout(6, 1,10,10));

        panel1 = new JPanel(new FlowLayout());
        panel1.add(title);
        newPanel.add(panel1);

        panel3 = new JPanel(new GridLayout(2,1,5,5));
        panel3.add(userLabel);
        panel3.add(textField1);
        newPanel.add(panel3);

        panel3 = new JPanel(new GridLayout(2,1,5,5));
        panel3.add(passLabel);
        panel3.add(textField2);
        newPanel.add(panel3);

        panel2 = new JPanel(new FlowLayout());



        panel2.add(b1);
        newPanel.add(panel2);
        newPanel.setBorder(BorderFactory.createEmptyBorder(100,200,20,200));



        add(newPanel, BorderLayout.CENTER);

        setVisible(true);

        /*JPanel panel1 = new JPanel();
        BoxLayout boxLayout1 = new BoxLayout(panel1, BoxLayout.Y_AXIS);
        panel1.setLayout(boxLayout1);
        panel1.setPreferredSize(new Dimension(400,400));
        panel1.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));

        title = new JLabel("Iniciar sessió");


        user = new JLabel("Nom d'usuari o correu");
        userField = new JTextField();

        password = new JLabel("Contrasenya");
        passwordField = new JPasswordField();

        loginButton = new JButton("Iniciar sessió");


        panel1.add(title);
        panel1.add(user);
        panel1.add(userField);
        panel1.add(password);
        panel1.add(passwordField);

        panel1.add(loginButton);

        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(panel1, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);*/
    }


}
