package Presentation.Controllers;

import Business.SaveGame;
import Presentation.MainView;
import Presentation.Views.LoadGameGUI;
import Presentation.Views.StatisticsMenuGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Business.SaveGame.*;

public class LoadGameController implements ActionListener {

    private MainView mainView;

    private SaveGame saveGame;
    private LoadGameGUI loadGameGUI;

    public LoadGameController( MainView mainView, SaveGame saveGame,LoadGameGUI loadGameGUI){

        this.mainView = mainView;
        this.saveGame = saveGame;
        this.loadGameGUI = loadGameGUI;

        mainView.setListeners(this);
    }


    public void actionPerformed(ActionEvent e) {


        switch (e.getActionCommand()) {
            case LoadGameGUI.CARGAR_BTN:

                break;

        }

    }
}
