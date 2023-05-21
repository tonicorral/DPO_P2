package Presentation.Controllers;

import Presentation.MainView;
import Presentation.Views.StartGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartController implements ActionListener {
    private StartGUI startGUI;

    private MainView mainView;


    public StartController(StartGUI startGUI,MainView mainView){
        this.startGUI = startGUI;
        this.mainView = mainView;

        mainView.setListeners(this);
    }

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
