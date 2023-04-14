package Presentation;

import Presentation.Views.*;

import javax.swing.*;

import Presentation.Views.LoginGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {


       private LoginGUI loginGUI;
       private SignUpGUI signUpGUI;
       private MenuGUI menuGUI;
       private JPanel mainViewPanel;
       private CardLayout cardLayout;

        public MainView() {
            this.setSize(819, 642);
            this.setTitle("Battleships");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);

            mainViewPanel = new JPanel();
            cardLayout = new CardLayout();
            mainViewPanel.setLayout(cardLayout);

            loginGUI = new LoginGUI(this);
            JPanel card1 = new JPanel();
            card1.add(loginGUI);
            mainViewPanel.add(card1,"LOGIN");

            signUpGUI = new SignUpGUI(this);
            JPanel card2 = new JPanel();
            card2.add(signUpGUI);
            mainViewPanel.add(card2,"SIGNUP");

            add(mainViewPanel,BorderLayout.CENTER);

            cardLayout.show(mainViewPanel,"LOGIN");

            setVisible(true);


        }

        public void showSignUp(){
            cardLayout.show(mainViewPanel,"SIGNUP");
        }

        public void showLogin(){
            cardLayout.show(mainViewPanel,"LOGIN");
        }






}

