import Business.GameModel;
import Business.IAModel;
import Business.TableroModel;
import Business.UserModel;
import Persistance.UserDAO;
import Persistance.UserSQL;
import Presentation.Controllers.*;
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
        StartGUI startGUI = new StartGUI();

        //setupStageGUI.setVisible(true);
        //gameStageGUI.setVisible(true);




        GameModel gameModel = new GameModel(iaModel,tableroModel);
        UserModel userModel = new UserModel(userdao);

        MainView mainView = new MainView(loginGUI, signUpGUI, menuGUI, startGUI, logoutGUI, deleteUserGUI,setupStageGUI, gameStageGUI, statisticsGUI);

        StartController startController = new StartController(startGUI, mainView);
        LoginController loginController = new LoginController(loginGUI,mainView,userModel);
        SignUpController signUpController = new SignUpController(signUpGUI, mainView, userModel);
        MenuController menuController = new MenuController(menuGUI, mainView);
        LogoutController logoutController = new LogoutController(logoutGUI, mainView);
        DeleteUserController deleteUserController = new DeleteUserController(deleteUserGUI, mainView, userModel);
        GameStageController gameStageController = new GameStageController(gameStageGUI,mainView, gameModel);
        SetUpController setUpController = new SetUpController(setupStageGUI, mainView,iaModel,gameModel, gameStageController);
        mainView.setVisible(true);


    }
}

