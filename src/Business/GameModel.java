package Business;

import Presentation.Controllers.SetUpController;

import java.util.ArrayList;

public class GameModel {


    private SetUpController setUpController;

    private IAModel iaModel;

    private Game game;
    private TableroModel tableroModel;

   private int numberPlayers = 0;

    public GameModel(IAModel iaModel,TableroModel tableroModel) {
        this.iaModel = iaModel;
        this.tableroModel =tableroModel;
    }

    public void getNumberPlayers(int numberPlayers1){
        numberPlayers = numberPlayers1;
    }
    public ArrayList<JugadorIA> getIA(){
        ArrayList<JugadorIA> iaPlayers = new ArrayList<>();

        for(int i = 0; i<numberPlayers;i++){
            iaPlayers.add(iaModel.createBoats());
        }



        return iaPlayers;
    }

    public void createGame(Player player){
        game = new Game(player,getIA(),numberPlayers,false);
    }

    public Game getGame(){
        return game;
    }

    public Game firstMovement(Game game,int i){
        Player oponente;
        oponente = iaModel.realizarMovimiento(getIA().get(0));
        int n = oponente.getPositionAttackedX().size();
        game = iaModel.detectAttack(game,oponente.getPositionAttackedX().get(n-1),oponente.getPositionAttackedY().get(n-1));

        return game;
    }







}
