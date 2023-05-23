package Presentation.Controllers;

import Presentation.MainView;
import Presentation.Views.MenuGUI;
import Presentation.Views.StatisticsGUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StatisticsController implements ActionListener{

    private StatisticsGUI statisticsGUI;

    private MainView mainView;


    public StatisticsController(StatisticsGUI statisticsGUI,MainView mainView){
        this.statisticsGUI = statisticsGUI;
        this.mainView = mainView;

        mainView.setListeners(this);
    }

    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case StatisticsGUI.CANCEL_BTN:
                mainView.switchView(MainView.MENU_VIEW);
                break;

            case StatisticsGUI.LOGOUT_BTN:
                mainView.switchView(MainView.LOGOUT_VIEW);
                break;

            case StatisticsGUI.DELETE_BTN:
                mainView.switchView(MainView.DELETE_VIEW);
                break;


        }

    }


}