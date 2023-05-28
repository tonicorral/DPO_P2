package Presentation.Controllers;


import Presentation.MainView;
import Presentation.Views.LogoutGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlar la funcionalidad del logout
 */
public class LogoutController implements ActionListener {
    private LogoutGUI logoutGUI;
    private MainView mainView;

    /**
     * Contructor del logout
     * @param logoutGUI contiene la información del logout
     * @param mainView contine la informacion de la clase de las vistas principales
     */
    public LogoutController(LogoutGUI logoutGUI, MainView mainView) {
        this.logoutGUI = logoutGUI;
        this.mainView = mainView;

        mainView.setListeners(this);
    }


    /**
     * Muestra mensajes dependiendo de la accion y el evento del logout
     * @param e variable para controlar la acción
     */
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case LogoutGUI.BUTTON_LOGOUT:
                mainView.switchView(MainView.START_VIEW);

                break;
            case LogoutGUI.BUTTON_CANCEL:
                mainView.switchView(MainView.MENU_VIEW);
                break;
        }
    }

}
