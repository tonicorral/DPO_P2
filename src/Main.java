import Business.UserModel;
import Persistance.UserDAO;
import Persistance.UserSQL;
import Presentation.Controllers.*;
import Presentation.MainController;
import Presentation.MainView;
import Presentation.Views.*;


public class Main {

    public static void main(String[] args) {

        UserDAO userdao = new UserSQL();
        SignUpGUI signUpGUI = new SignUpGUI();
        LoginGUI loginGUI = new LoginGUI();
        LogoutGUI logoutGUI = new LogoutGUI();
        DeleteUserGUI deleteUserGUI = new DeleteUserGUI();
        MenuGUI menuGUI = new MenuGUI();


        StartGUI startGUI = new StartGUI();

        MainView mainView = new MainView(loginGUI, signUpGUI, menuGUI, startGUI, logoutGUI, deleteUserGUI);
        MainController mainController = new MainController(mainView);//mainview
        UserModel userModel = new UserModel(userdao);
        LoginController loginController = new LoginController(loginGUI,mainView,mainController,userModel);
        SignUpController signUpController = new SignUpController(signUpGUI, mainView, userModel, mainController);
        MenuController menuController = new MenuController(menuGUI, mainController, mainView);
        LogoutController logoutController = new LogoutController(logoutGUI, mainController, mainView);
        DeleteUserController deleteUserController = new DeleteUserController(deleteUserGUI, mainController, mainView);
        //mainView.setListeners(mainView);
        mainView.setVisible(true);


       

    }
}

