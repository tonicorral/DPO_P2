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

    private GameStageController gameStageController;

    public LoadGameController( MainView mainView, SaveGame saveGame,LoadGameGUI loadGameGUI, GameStageController gameStageController){

        this.mainView = mainView;
        this.saveGame = saveGame;
        this.loadGameGUI = loadGameGUI;
        this.gameStageController = gameStageController;

        mainView.setListeners(this);
    }


    public void actionPerformed(ActionEvent e) {


        switch (e.getActionCommand()) {
            case LoadGameGUI.CARGAR_BTN:

                String partidaSeleccionada = loadGameGUI.getPartidaJList();
                mainView.switchView(MainView.GAME_STAGE_VIEW);
                gameStageController.loadSaveTable(saveGame.leerPartidaString(partidaSeleccionada));
                break;

        }

    }

}
