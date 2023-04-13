package Presentation;

import Presentation.Views.LoginGUI;

import javax.swing.*;

import Presentation.Views.LoginGUI;
import Presentation.Views.SignUpGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

        LoginGUI loginGUI = new LoginGUI();

        SignUpGUI signUpGUI = new SignUpGUI();

        public MainView() {
            this.setSize(819, 642);
            this.setTitle("Battleships");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        }



        public void showView(){
            JPanel loginPanel = new JPanel();
            JPanel cardPanel = new JPanel();
            JPanel signupPanel = new JPanel();
            CardLayout cardLayout = new CardLayout();
            loginPanel.add(loginGUI);
            signupPanel.add(signUpGUI);
            cardPanel.setLayout(cardLayout);
            cardPanel.add(signupPanel,"SIGN UP");
            cardPanel.add(loginPanel,"LOGIN");
            this.add(cardPanel);
            setVisible(true);
        }

}

