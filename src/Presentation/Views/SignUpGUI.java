package Presentation.Views;

import Presentation.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpGUI extends JPanel{
    private JTextField userText;
    private JPasswordField passwordText;
    private JTextField emailText;
    private JPasswordField passwordConfirmText;
    private JButton signUp;
    private JButton goBack;
    public static final String SIGNUP_BTN = "SIGNUP_BTN";
    public static final String SIGNUP_BACK_BTN = "SIGNUP_BACK_BTN";

    public SignUpGUI() {

        JPanel panel = new JPanel(new BorderLayout());

        this.signUp = new JButton("Sign up");
        this.signUp.setBackground(new Color(124,136,248));
        this.signUp.setFont(new Font("Inter",Font.PLAIN,12));
        this.signUp.setForeground(Color.white);
        this.signUp.setActionCommand("SIGNUP_BTN");

        this.goBack = new JButton("Go to Login");
        this.goBack.setBackground(new Color(124,136,248));
        this.goBack.setFont(new Font("Inter",Font.PLAIN,12));
        this.goBack.setForeground(Color.white);
        this.goBack.setActionCommand("SIGNUP_BACK_BTN");

        //JLABEL
        JLabel title = new JLabel("REGISTER FORM", 0);
        JLabel name = new JLabel("Name", 4);
        JLabel email = new JLabel("Email", 4);
        JLabel passwordConfirm = new JLabel("Password Confirmation", 4);
        JLabel password = new JLabel("Password", 4);

        title.setFont(new Font("Inter", Font.BOLD, 48));
        title.setForeground(Color.DARK_GRAY);
        name.setFont(new Font("Inter", Font.BOLD, 13));
        email.setFont(new Font("Inter", Font.BOLD, 13));
        passwordConfirm.setFont(new Font("Inter", Font.BOLD, 13));
        password.setFont(new Font("Inter", Font.BOLD, 13));
        this.goBack.setFont(new Font("Inter", Font.BOLD, 13));
        this.signUp.setFont(new Font("Inter", Font.BOLD, 13));

        this.userText = new JTextField();
        this.emailText = new JTextField();
        this.passwordText = new JPasswordField();
        this.passwordConfirmText = new JPasswordField();
        //this.setBackground(Color.DARK_GRAY);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.setBackground(Color.DARK_GRAY);
        bottomPanel.add(this.signUp);
        bottomPanel.add(this.goBack);


        JPanel gridJPanel = new JPanel();
        gridJPanel.setLayout(new GridLayout(4, 2, 5, 5));
        //gridJPanel.setBackground(Color.LIGHT_GRAY);
        gridJPanel.add(name);
        gridJPanel.add(this.userText);
        gridJPanel.add(email);
        gridJPanel.add(this.emailText);
        gridJPanel.add(password);
        gridJPanel.add(this.passwordText);
        gridJPanel.add(passwordConfirm);
        gridJPanel.add(this.passwordConfirmText);

        panel.add(gridJPanel, "Center");
        panel.add(bottomPanel, "South");
        panel.add(title, "North");
        this.add(panel, "Center");

        setVisible(true);

         /** this.goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showLogin();
            }
        })   ;**/
    }

    public void registerSignUpController(ActionListener listener) {
        this.signUp.addActionListener(listener);
    }

    public void registerController(ActionListener listener) {
        this.goBack.addActionListener(listener);
    }
    public String getUser() {
        return this.userText.getText();
    }

    public String getEmail() {
        return this.emailText.getText();
    }

    public String getPassword() {
        return this.passwordText.getText();
    }

    public String getPasswordConfirm() {
        return this.passwordConfirmText.getText();
    }

    public void clear() {
        this.userText.setText("");
        this.emailText.setText("");
        this.passwordText.setText("");
        this.passwordConfirmText.setText("");
    }



}
