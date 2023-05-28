package Presentation.Controllers;

import Presentation.MainView;
import Presentation.Views.StartGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlar la funcionalidad de la vista del inicio
 */
public class StartController implements ActionListener {
    private StartGUI startGUI;

    private MainView mainView;

    /**
     * Constructor de la vista de inicio
     * @param startGUI contiene la infomración de la vista de inicio
     * @param mainView contine la informacion de la clase de las vistas principales
     */
    public StartController(StartGUI startGUI,MainView mainView){
        this.startGUI = startGUI;
        this.mainView = mainView;

        mainView.setListeners(this);
    }

    /**
     * Muestra mensajes dependiendo de la accion y el evento en la pantalla de inicio
     * @param e variable para controlar la acción
     */
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case StartGUI.START_LOGIN:
                mainView.switchView(MainView.LOGIN_VIEW);
                break;
            case StartGUI.START_SIGNUP:
                mainView.switchView(MainView.SIGNUP_VIEW);
                break;
        }
    }


}
