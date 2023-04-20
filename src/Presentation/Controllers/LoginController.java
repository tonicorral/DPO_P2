package Presentation.Controllers;

import Business.UserModel;
import Presentation.MainView;
import Presentation.Views.LoginGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {

    private LoginGUI loginGUI;
    private UserModel userModel;
    private MainView mainView;
    //private MainController mainController;

    public LoginController(LoginGUI loginGUI,MainView mainView){
        this.loginGUI = loginGUI;
        //this.userModel = userModel;
        this.mainView = mainView;

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
                /*switch (userModel.login(user, pass)){
                    case UserOption.EVERYTHING_OK:
                        mainView.switchView(MainView.MENU_VIEW);
                    case UserOption.EMPTY_FIELD:
                        mainView.showError("Empty field");
                    case UserOption.INCORRECT_LOGIN:
                        mainView.showError("User or password incorrect");
                }
                break;*/
        }



    }





}

