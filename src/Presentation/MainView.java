package Presentation;

import Presentation.Views.DeleteUserGUI;
import Presentation.Views.LoginGUI;

import javax.swing.*;

import Presentation.Views.LoginGUI;
import Presentation.Views.SignUpGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame implements ActionListener {

        LoginGUI loginGUI = new LoginGUI();

        SignUpGUI signUpGUI = new SignUpGUI();

        JPanel loginPanel = new JPanel();
        JPanel cardPanel = new JPanel();
        JPanel signupPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();

        public MainView() {
            this.setSize(819, 642);
            this.setTitle("Battleships");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);

            loginPanel.add(loginGUI);
            signupPanel.add(signUpGUI);
            cardPanel.setLayout(cardLayout);

            cardPanel.add(signupPanel,"SIGN UP");
            cardPanel.add(loginPanel,"LOGIN");

            this.add(cardPanel);

            this.setVisible(true);
        }



        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(SignUpGUI.LOGIN_BACK_BTN)) {
                cardLayout.show(loginGUI,"LOGIN");
            }
        }

}

