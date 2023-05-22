package Presentation.Controllers;

import Business.UserModel;

import Business.SaveGame;
import Presentation.MainView;
import Presentation.Views.LoginGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Business.UserModel.*;

public class LoginController implements ActionListener {

    private LoginGUI loginGUI;
    private UserModel userModel;
    private MainView mainView;
    private SaveGame saveGame;


    public LoginController(LoginGUI loginGUI, MainView mainView,UserModel userModel, SaveGame saveGame){
        this.loginGUI = loginGUI;
        this.userModel = userModel;
        this.mainView = mainView;
        this.saveGame = saveGame;
        mainView.setListeners(this);
    }

    public void actionPerformed(ActionEvent e){
        String user = loginGUI.getUser();
        String pass = loginGUI.getPassword();

        switch (e.getActionCommand()) {

            case LoginGUI.LOGIN_BACK_BTN:
                mainView.switchView(MainView.SIGNUP_VIEW);
                break;

            case LoginGUI.LOGIN_BTN:
                switch (userModel.login(user, pass)) {
                    case (EVERYTHING_OK) -> {
                        saveGame.setUser(user);
                        mainView.switchView(MainView.MENU_VIEW);
                    }
                    case EMPTY_FIELD -> mainView.showError("There is an empty field!");
                    case BAD_PASSWORD -> mainView.showError("Password is incorrect!");
                    case NO_USER -> mainView.showError("User does not exist");
                }

        }


    }





}

