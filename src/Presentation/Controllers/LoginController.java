package Presentation.Controllers;

import Business.UserModel;
import Business.UserOption;
import Presentation.MainController;
import Presentation.Views.LoginGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {

    private UserModel userModel;
    private LoginGUI loginGUI;
    private MainController mainController;

    public LoginController(UserModel userModel, LoginGUI loginGUI, MainController mainController) {
        this.userModel = userModel;
        this.loginGUI = loginGUI;
        this.mainController = mainController;
        registerLoginListener();
    }

    public void registerLoginListener() {
        loginGUI.getB1().addActionListener(this);
    }


}

