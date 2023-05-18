package Presentation.Controllers;

import Presentation.MainController;
import Presentation.MainView;
import Presentation.Views.MenuGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener {
    private MenuGUI menuGUI;

    private MainController mainController;
    private MainView mainView;

    public MenuController(MenuGUI menuGUI, MainController mainController, MainView mainView) {
        this.menuGUI = menuGUI;
        this.mainView = mainView;
        this.mainController = mainController;
        mainView.setListeners(this);
    }

    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case MenuGUI.NEW_GAME_BTN:
                mainView.switchView(MainView.SETUP_VIEW);
                break;

            case MenuGUI.LOGOUT_MENU_BTN :
                mainView.switchView(MainView.LOGOUT_VIEW);
                break;

            case MenuGUI.DELETE_MENU_BTN:
                mainView.switchView(MainView.DELETE_VIEW);
                break;

            case MenuGUI.STATS_BTN:
                mainView.switchView(MainView.STATISTICS_VIEW);
                break;



        }
    }



}
