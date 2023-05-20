import Business.GameModel;
import Business.IAModel;
import Business.TableroModel;
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
        SetupStageGUI setupStageGUI = new SetupStageGUI();
        GameStageGUI gameStageGUI = new GameStageGUI();
        StatisticsGUI statisticsGUI = new StatisticsGUI();
        IAModel iaModel = new IAModel();
        TableroModel tableroModel = new TableroModel();

        //setupStageGUI.setVisible(true);
        //gameStageGUI.setVisible(true);

        StartGUI startGUI = new StartGUI();
        GameModel gameModel = new GameModel(iaModel,tableroModel);

        MainView mainView = new MainView(loginGUI, signUpGUI, menuGUI, startGUI, logoutGUI, deleteUserGUI,setupStageGUI, gameStageGUI, statisticsGUI);
        MainController mainController = new MainController(mainView);
        UserModel userModel = new UserModel(userdao);
        LoginController loginController = new LoginController(loginGUI,mainView,mainController,userModel);
        SignUpController signUpController = new SignUpController(signUpGUI, mainView, userModel, mainController);
        MenuController menuController = new MenuController(menuGUI, mainController, mainView);
        LogoutController logoutController = new LogoutController(logoutGUI, mainController, mainView);
        DeleteUserController deleteUserController = new DeleteUserController(deleteUserGUI, mainController, mainView, userModel);
        GameStageController gameStageController = new GameStageController(gameStageGUI,mainView, mainController, gameModel);
        SetUpController setUpController = new SetUpController(setupStageGUI, mainView,mainController,iaModel,gameModel, gameStageController);
        mainView.setVisible(true);


    }
}

