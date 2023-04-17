import Presentation.MainController;
import Presentation.MainView;
import Presentation.Views.*;

import java.awt.*;


public class Main {

    public static void main(String[] args) {

        LoginGUI loginGUI = new LoginGUI();
        LogoutGUI logoutGUI = new LogoutGUI();
        MenuGUI menuGUI = new MenuGUI();
        SignUpGUI signUpGUI = new SignUpGUI();
        StartGUI startGUI = new StartGUI();

        MainView mainView = new MainView(loginGUI, signUpGUI, menuGUI, startGUI);
        MainController mainController = new MainController(mainView);//mainview
        mainView.setVisible(true);


       

    }
}

