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

        LoginGUI loginGUI = new LoginGUI();
        LogoutGUI logoutGUI = new LogoutGUI();
        MenuGUI menuGUI = new MenuGUI();
        SignUpGUI signUpGUI = new SignUpGUI();
        StartGUI startGUI = new StartGUI();


       // UserDAO userdao = new UserSQL();
       // UserModel userModel = new UserModel(userdao,userOption);

        MainView mainView = new MainView(loginGUI, signUpGUI, menuGUI, startGUI);
        MainController mainController = new MainController(mainView);//mainview
        LoginController loginController = new LoginController(loginGUI,mainView);
        SignUpController signUpController = new SignUpController(signUpGUI,mainView);
        //mainView.setListeners(mainView);
        mainView.setVisible(true);


       

    }
}

