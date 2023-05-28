package Presentation.Controllers;


import Business.SaveGame;
import Presentation.MainView;

import Presentation.Views.StatisticsMenuGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Business.SaveGame.*;



/**
 * Controla la funcionalidad de la vista de estadisticas
 */
public class StatsMenuController implements ActionListener{

    private StatisticsMenuGUI statisticsMenuGUI;

    private MainView mainView;

    private SaveGame saveGame;

    /**
     * Contructor de las estadidsticas
     * @param statisticsMenuGUI contiene informacion de la vista de las estadisticas
     * @param mainView contine la informacion de la clase de las vistas principales
     */
    public StatsMenuController(StatisticsMenuGUI statisticsMenuGUI,MainView mainView, SaveGame saveGame){
        this.statisticsMenuGUI = statisticsMenuGUI;
        this.mainView = mainView;
        this.saveGame = saveGame;

        mainView.setListeners(this);
    }

    /**
     * Muestra mensajes dependiendo de la accion y el evento a las estadisticas
     * @param e variable para controlar la acción
     */
    public void actionPerformed(ActionEvent e) {
        String user = statisticsMenuGUI.getUser();

        switch (e.getActionCommand()) {
            case StatisticsMenuGUI.SEARCH_BTN:
                switch (saveGame.searchUser(user)) {

                    case EVERYTHING_OK:
                        saveGame.setUser(user);
                        mainView.switchView(MainView.STATISTICS_VIEW);
                        break;

                    case EMPTY_FIELD:
                        mainView.showError("There is an empty field!");
                        break;
                    case INCORRECT_USER:
                        mainView.showError("That is not a valid username!");
                        break;
                } break;
            case StatisticsMenuGUI.CANCEL_BTN:
                mainView.switchView(MainView.MENU_VIEW);
                break;

            case StatisticsMenuGUI.LOGOUT_BTN:
                mainView.switchView(MainView.LOGOUT_VIEW);
                break;

            case StatisticsMenuGUI.DELETE_BTN:
                mainView.switchView(MainView.DELETE_VIEW);
                break;

        }

    }


}