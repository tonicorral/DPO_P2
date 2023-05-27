package Presentation.Controllers;


import Presentation.MainView;
import Presentation.Views.MenuGUI;
import Presentation.Views.SetupStageGUI;
import Presentation.Views.StatisticsMenuGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controla la funcionalidad de la vista del menu
 */

public class MenuController implements ActionListener {
    private MenuGUI menuGUI;
    private MainView mainView;
    private SetUpController setUpController;


    /**
     * Contructor del menu
     * @param menuGUI contiene la info del menu
     * @param mainView contine la informacion de la clase de las vistas principales
     */
    public MenuController(MenuGUI menuGUI, MainView mainView, SetUpController setUpController) {
        this.menuGUI = menuGUI;
        this.mainView = mainView;
        this.setUpController = setUpController;

        mainView.setListeners(this);
    }

    /**
     * Muestra mensajes dependiendo de la accion y el evento del menu
     * @param e variable para controlar la acción
     */
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case MenuGUI.NEW_GAME_BTN:
                setUpController.eliminateBoats();
                mainView.switchView(MainView.SETUP_VIEW);
                break;

            case MenuGUI.LOGOUT_MENU_BTN :
                mainView.switchView(MainView.LOGOUT_VIEW);
                break;

            case MenuGUI.DELETE_MENU_BTN:
                mainView.switchView(MainView.DELETE_VIEW);
                break;

            case MenuGUI.STATS_BTN:

                mainView.switchView(MainView.STATISTICS_MENU_VIEW);
                break;



        }
    }



}
