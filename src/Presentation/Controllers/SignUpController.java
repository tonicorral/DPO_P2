package Presentation.Controllers;

import Business.UserModel;
import Presentation.MainController;
import Presentation.MainView;
import Presentation.Views.SignUpGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Business.UserModel.*;

public class SignUpController implements ActionListener{
    private SignUpGUI signupView;
    private UserModel userModel;

    private MainController mainController;
    private MainView mainView;
  //  private MainController mainController;

    public SignUpController(SignUpGUI signUpGUI,MainView mainView, UserModel userModel, MainController mainController){
        this.signupView = signUpGUI;
        this.mainView = mainView;
        this.userModel = userModel;
        this.mainController = mainController;
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

                case EVERYTHING_OK -> mainView.switchView(MainView.LOGIN_VIEW);
                case EMPTY_FIELD -> mainController.showError("There is an empty field!");
                case ERROR_SAVE -> mainController.showError("There has been an error on creating the account!");
                case INCORRECT_MAIL -> mainController.showError("That is not a valid mail!");
                case INCORRECT_PASS -> mainController.showError("That is not a valid password!");
                case MISMATCHING_PASS -> mainController.showError("The passwords do not match! Try again!");
                case DUPLICATED_LOGIN -> mainController.showError("This username/mail already exist");
            }

                break;
            case SignUpGUI.SIGNUP_BACK_BTN:
                mainView.switchView(MainView.LOGIN_VIEW);
                break;
        }



    }


  /*  @Override
    public void actionPerformed(ActionEvent e) {
        String user = signupView.getUser();
        String pass = signupView.getPassword();
        String passConfirm = signupView.getPasswordConfirm();
        String email = signupView.getEmail();

        switch (userModel.signUp(user, pass, passConfirm, email)) {

            case UserOption.EVERYTHING_OK:
             //   mainController.switchView(MainView.LOGIN_VIEW);
            case UserOption.EMPTY_FIELD:
                mainController.showError("There is an empty field!");
            case UserOption.ERROR_SAVE:
                mainController.showError("There has been an error on creating the account!");
            case UserOption.INCORRECT_PASS:
                mainController.showError("That is not a valid password!");
            case UserOption.MISMATCHING_PASS:
                mainController.showError("The passwords do not match! Try again!");
            case UserOption.DUPLICATED_LOGIN:
                mainController.showError("This username/mail already exist");
            case UserOption.INCORRECT_MAIL:
                mainController.showError("That is not a valid mail!");

        }
    }*/

}
