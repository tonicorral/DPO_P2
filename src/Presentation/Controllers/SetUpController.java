package Presentation.Controllers;

import Presentation.MainView;
import Presentation.Views.SetupStageGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetUpController implements ActionListener {

    private SetupStageGUI setUpGUI;

    // MainController mainController;
    private MainView mainView;

    public SetUpController(SetupStageGUI setUpGUI, MainView mainView) {
        this.setUpGUI = setUpGUI;
        this.mainView = mainView;
        //this.mainController = mainController;
        mainView.setListeners(this);
    }

    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case SetupStageGUI.BEGIN_BUTTON:
                mainView.switchView(MainView.GAME_STAGE_VIEW);
                break;

            case SetupStageGUI.LOGOUT_BTN:
                mainView.switchView(MainView.LOGOUT_VIEW);
                break;

            case SetupStageGUI.DELETE_BTN:
                mainView.switchView(MainView.DELETE_VIEW);
                break;


        }
    }
}








