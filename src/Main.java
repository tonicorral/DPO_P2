import Business.GameModel;
import Business.IAModel;
import Business.TableroModel;
import Business.UserModel;
import Persistance.*;
import Presentation.Controllers.*;
import Presentation.MainView;
import Presentation.Views.*;

import javax.swing.*;


public class Main {

    public static void main(String[] args) {

        UserDAO userDAO = new UserSQL();
        GameDAO gameDAO = new GameSQL();
        SignUpGUI signUpGUI = new SignUpGUI();
        LoginGUI loginGUI = new LoginGUI();
        LogoutGUI logoutGUI = new LogoutGUI();
        DeleteUserGUI deleteUserGUI = new DeleteUserGUI();
        MenuGUI menuGUI = new MenuGUI();
        SetupStageGUI setupStageGUI = new SetupStageGUI();
        GameStageGUI gameStageGUI = new GameStageGUI();



        IAModel iaModel = new IAModel();
        TableroModel tableroModel = new TableroModel();
        StartGUI startGUI = new StartGUI();

        //setupStageGUI.setVisible(true);
        //gameStageGUI.setVisible(true);

        StatisticsGUI statisticsGUI = new StatisticsGUI();



        GameModel gameModel = new GameModel(iaModel,tableroModel);
        UserModel userModel = new UserModel(userDAO);
        MainView mainView = new MainView(loginGUI, signUpGUI, menuGUI, startGUI, logoutGUI, deleteUserGUI,setupStageGUI, gameStageGUI, statisticsGUI);
        SaveGame saveGame = new SaveGame(gameDAO, "");

        StartController startController = new StartController(startGUI, mainView);
        LoginController loginController = new LoginController(loginGUI,mainView,userModel, saveGame);
        SignUpController signUpController = new SignUpController(signUpGUI, mainView, userModel);
        MenuController menuController = new MenuController(menuGUI, mainView);
        LogoutController logoutController = new LogoutController(logoutGUI, mainView);
        DeleteUserController deleteUserController = new DeleteUserController(deleteUserGUI, mainView, userModel);
        GameStageController gameStageController = new GameStageController(gameStageGUI,mainView, gameModel, saveGame);
        SetUpController setUpController = new SetUpController(setupStageGUI, mainView,iaModel,gameModel, gameStageController);
        mainView.setVisible(true);


    }
}

