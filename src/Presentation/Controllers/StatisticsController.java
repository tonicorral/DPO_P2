package Presentation.Controllers;

import Presentation.MainView;
import Presentation.Views.MenuGUI;
import Presentation.Views.StatisticsGUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Controla la funcionalidad de la vista de estadisticas
 */
public class StatisticsController implements ActionListener{

    private StatisticsGUI statisticsGUI;

    private MainView mainView;

    /**
     * Contructor de las estadidsticas
     * @param statisticsGUI contiene informacion de la vista de las estadisticas
     * @param mainView contine la informacion de la clase de las vistas principales
     */
    public StatisticsController(StatisticsGUI statisticsGUI,MainView mainView){
        this.statisticsGUI = statisticsGUI;
        this.mainView = mainView;

        mainView.setListeners(this);
    }

    /**
     * Muestra mensajes dependiendo de la accion y el evento a las estadisticas
     * @param e variable para controlar la acción
     */
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

            //case StatisticsGUI.DELETE_BTN: Cuando se pulsa un usuario, actualizar la vista de stadistics
                //mainView.switchView(MainView.DELETE_VIEW);
                //break;


        }

    }


}