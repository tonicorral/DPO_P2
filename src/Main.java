import Business.UserModel;
import Persistance.UserDAO;
import Persistance.UserSQL;
import Presentation.Controllers.LoginController;
import Presentation.Controllers.SignUpController;
import Presentation.MainController;
import Presentation.MainView;
import Presentation.Views.*;


public class Main {

    public static void main(String[] args) {

        UserDAO userdao = new UserSQL();
        SignUpGUI signUpGUI = new SignUpGUI();
        LoginGUI loginGUI = new LoginGUI();
        LogoutGUI logoutGUI = new LogoutGUI();
        MenuGUI menuGUI = new MenuGUI();

        StartGUI startGUI = new StartGUI();

        MainView mainView = new MainView(loginGUI, signUpGUI, menuGUI, startGUI);
        MainController mainController = new MainController(mainView);//mainview
        UserModel userModel = new UserModel(userdao);
        LoginController loginController = new LoginController(loginGUI,mainView);
        SignUpController signUpController = new SignUpController(signUpGUI, mainView, userModel, mainController);
        //mainView.setListeners(mainView);
        mainView.setVisible(true);


       

    }
}

