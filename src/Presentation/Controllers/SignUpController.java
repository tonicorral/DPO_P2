package Presentation.Controllers;

import Business.SaveGame;
import Business.UserModel;

import Presentation.MainView;
import Presentation.Views.SignUpGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Business.UserModel.*;

public class SignUpController implements ActionListener{
    private SignUpGUI signupView;
    private UserModel userModel;

    private MainView mainView;

    private SaveGame saveGame;

    public SignUpController(SignUpGUI signUpGUI,MainView mainView, UserModel userModel, SaveGame saveGame){
        this.signupView = signUpGUI;
        this.mainView = mainView;
        this.userModel = userModel;
        this.saveGame = saveGame;

        mainView.setListeners(this);
    }

    public void actionPerformed(ActionEvent e){
        String user = signupView.getUser();
        String pass = signupView.getPassword();
        String passConfirm = signupView.getPasswordConfirm();
        String email = signupView.getEmail();

        switch (e.getActionCommand()) {
            case SignUpGUI.SIGNUP_BTN:
                switch (userModel.signUp(user, pass, passConfirm, email)) {

                    case EVERYTHING_OK:
                        saveGame.setUser(user);
                        mainView.switchView(MainView.MENU_VIEW);
                        break;
                case EMPTY_FIELD:
                    mainView.showError("There is an empty field!");
                    break;
                case ERROR_SAVE:
                    mainView.showError("There has been an error on creating the account!");
                    break;

                case INCORRECT_MAIL:
                    mainView.showError("That is not a valid mail!");
                    break;

                case INCORRECT_PASS :
                    mainView.showError("That is not a valid password!");
                    break;

                case MISMATCHING_PASS :
                    mainView.showError("The passwords do not match! Try again!");
                    break;

                case DUPLICATED_LOGIN :
                    mainView.showError("This username/mail already exist");
                    break;

            }

                break;
            case SignUpGUI.SIGNUP_BACK_BTN:
                mainView.switchView(MainView.LOGIN_VIEW);
                break;
        }



    }


}
