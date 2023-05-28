import Business.*;
import Persistance.*;
import Presentation.Controllers.*;
import Presentation.MainView;
import Presentation.Views.*;

/**
 * Realizaremos todas las comandas para poder ejecutar el programa
 */

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

        StartGUI startGUI = new StartGUI();

        //setupStageGUI.setVisible(true);
        //gameStageGUI.setVisible(true);

        SaveGame saveGame = new SaveGame(gameDAO, "");
        StatisticsMenuGUI statisticsMenuGUI= new StatisticsMenuGUI(saveGame);
        StatisticsGUI statisticsGUI = new StatisticsGUI(saveGame);
        LoadGameGUI loadGameGUI = new LoadGameGUI( saveGame);
        PlayerModel playerModel = new PlayerModel();
        GameModel gameModel = new GameModel(iaModel,playerModel);
        UserModel userModel = new UserModel(userDAO);
        MainView mainView = new MainView(loginGUI, signUpGUI, menuGUI, startGUI, logoutGUI, deleteUserGUI,setupStageGUI, gameStageGUI, statisticsMenuGUI, statisticsGUI, loadGameGUI);


        StartController startController = new StartController(startGUI, mainView);
        LoginController loginController = new LoginController(loginGUI,mainView,userModel, saveGame);
        SignUpController signUpController = new SignUpController(signUpGUI, mainView, userModel, saveGame);
        LogoutController logoutController = new LogoutController(logoutGUI, mainView);
        DeleteUserController deleteUserController = new DeleteUserController(deleteUserGUI, mainView, userModel);
        GameStageController gameStageController = new GameStageController(gameStageGUI,mainView, gameModel, saveGame);
        SetUpController setUpController = new SetUpController(setupStageGUI, mainView,gameModel, gameStageController);
        MenuController menuController = new MenuController(menuGUI, mainView,setUpController);
        gameModel.registerController(gameStageController);
        iaModel.registerGameModel(gameModel);
        StatisticsController statisticsController = new StatisticsController(statisticsGUI, mainView);
        StatsMenuController statsMenuController= new StatsMenuController(statisticsMenuGUI, mainView, saveGame);
        LoadGameController loadGameController = new LoadGameController(mainView, saveGame, loadGameGUI, gameStageController);
        mainView.setVisible(true);


    }
}

