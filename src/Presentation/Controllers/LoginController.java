package Presentation.Controllers;

import Business.UserModel;

import Presentation.MainController;

import Presentation.MainView;
import Presentation.Views.LoginGUI;

import java.awt.event.ActionEvent;

import Business.UserOption;

public class LoginController {

    private LoginGUI loginGUI;
    private UserModel userModel;
    private MainController mainController;

    public void actionPerformed(ActionEvent e){
        String user = loginGUI.getUser();
        String pass = loginGUI.getPassword();


        switch (userModel.login(user, pass)){
            case UserOption.EVERYTHING_OK:
                mainController.switchView(MainView.MENU_VIEW);
            case UserOption.EMPTY_FIELD:
                mainController.showError("Empty field");
            case UserOption.INCORRECT_LOGIN:
                mainController.showError("User or password incorrect");
        }

    }





}

