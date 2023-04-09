package Presentation;

import Presentation.Views.LoginGUI;
import Presentation.Views.SignUpGUI;

public class MainController {


    public void run(){
        //LoginGUI loginGUI = new LoginGUI();
      //  loginGUI.showLogin();
        SignUpGUI signUpGUI = new SignUpGUI();
        signUpGUI.showSignUp();
    }

}
