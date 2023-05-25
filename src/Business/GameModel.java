package Business;

import Presentation.Controllers.GameStageController;
import Presentation.Controllers.SetUpController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GameModel implements ThreadListener{

    private SetUpController setUpController;

    private IAModel iaModel;

    private int hora=0,minuto=0,segundo=0;
    private boolean iniciaHilo = true;
    private boolean corriendo = false;
    private Game game;
    private TableroModel tableroModel;

   private int numberPlayers = 0;

   private TimeThread timeThread;

   private int seconds=0,minuts=0;

   private Date currentTime;

   private long startTime = System.currentTimeMillis();

    public GameModel(IAModel iaModel,TableroModel tableroModel) {
        this.iaModel = iaModel;
        this.tableroModel =tableroModel;

        this.currentTime = new Date();

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

    public Game IAAttacks(Game game,int i){

        game = iaModel.makeDifferentAttack(game,i);

        return game;
    }

    public Game updateTablero(Game game){

        game = iaModel.updateTablero(game);



        return game;
    }

    public ArrayList<Integer> attackUser(String positionBoatTable){
        ArrayList<Integer> positionsUser = new ArrayList<>();
        char letter = positionBoatTable.charAt(4);
        int positionLetter = letter - 'A' + 1;
        int number = Integer.parseInt(positionBoatTable.substring(5));
        positionsUser.add(number);
        positionsUser.add(positionLetter);

        return positionsUser;
    }

    public Game insertAttack(Game game,ArrayList<Integer> positionsUser){
        int positionX = positionsUser.get(0);
        int positionY = positionsUser.get(1);

        game.getPlayer().getPositionAttackedX().add(positionX);
        game.getPlayer().getPositionAttackedY().add(positionY);


        return game;
    }




    /*
    public void timer(){
        timeThread = new TimeThread();
        new Thread(timeThread).start();
    }*/


    @Override
    public boolean correctPosition(int fila, int columna, int attacker) {
        return false;
    }

    @Override
    public int notifyAttack(int fila, int columna, int attacker) {
        return 0;
    }


}
