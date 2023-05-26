package Business;

import Presentation.Controllers.GameStageController;
import Presentation.Controllers.SetUpController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GameModel{

    private IAModel iaModel;
    private PlayerModel playerModel;
    private Game game;
   private int numberPlayers = 0;
   private TimeThread timeThread;

   private int seconds=0,minuts=0;
   private String time;

   private GameStageController gameStageController;

   private long startTime = System.currentTimeMillis();

    public GameModel(IAModel iaModel,PlayerModel playerModel) {
        this.iaModel = iaModel;
        this.playerModel = playerModel;
    }

    public void startTimer(){
        this.timeThread = new TimeThread(this);
        timeThread.startTimer();
        timeThread.start();
        iaModel.start();
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
        iaModel.getGame(game);
    }

    public Game getGame(){
        return game;
    }

    public void IAAttacks(Game game){ //La ia realiza sus ataques y los mete en game

        this.game = game;
        System.out.println(game.getJugadorIA().get(0).getPositionAttackedX().size());
        this.game = updateTablero();
        gameStageController.updateTable(this.game);
    }

    public ArrayList<Integer> attackUser(String positionBoatTable){ //Transformamos string(JButton) en las posiciones de ataque del usuario

        return playerModel.saveAttack(positionBoatTable);
    }

    public Game insertAttack(Game game,ArrayList<Integer> positionsUser){ //Guardamos el ataque del usuario en game;
        this.game = playerModel.playerAttacks(game,positionsUser);
        return this.game;
    }

    private Game updateTablero(){ //Hacemos update de todos los tableros

        updateTableroIAAttackIA(); //update de los tablero de las IA cuando se atacan entre si
        updateTableroIAAttackPlayer(); //update de los tablero de las IA cuando ataca el usuario
        updateTableroPlayerAttackIA(); //update del tablero del user
        checkHundidoTableroIA(); //chequeamos barcos hundidos en los tablero de la IA
        checkHundidoTableroPlayer(); //chequemos barcos hundidos del tablero del usuario

        return game;
    }

    private void updateTableroIAAttackIA(){
        for(int i = 0;i<numberPlayers;i++){
            Player attacker = game.getJugadorIA().get(i);
            int n = attacker.getPositionAttackedX().size()-1;
            if(n > -1){
                int positionAttackedX = attacker.getPositionAttackedX().get(n);
                int positionAttackedY = attacker.getPositionAttackedY().get(n);

                for(int j=0;j<numberPlayers;j++){
                    int positionAttacked = game.getJugadorIA().get(j).getTablero().getTablero()[positionAttackedX-1][positionAttackedY-1];
                    System.out.println(positionAttacked);
                    if(j!=i){
                        if( positionAttacked == 1){
                            game.getJugadorIA().get(j).getTablero().getTablero()[positionAttackedX-1][positionAttackedY-1] = (i+2)*-1; //TABLERO DE IA TOCADO POR IA
                        }else if(positionAttacked == 0){
                            game.getJugadorIA().get(j).getTablero().getTablero()[positionAttackedX-1][positionAttackedY-1] = i+2; //TABLERO IA ATAQUE IA
                        }
                    }
                }
            }

        }
    }

    private void updateTableroIAAttackPlayer(){
        int lastAttack = game.getPlayer().getPositionAttackedX().size()-1;
        if(lastAttack > -1){
            int positionAttackedX = game.getPlayer().getPositionAttackedX().get(lastAttack);
            int positionAttackedY = game.getPlayer().getPositionAttackedY().get(lastAttack);
            for(int i = 0;i<numberPlayers;i++){
                int positionAttackedIA = game.getJugadorIA().get(i).getTablero().getTablero()[positionAttackedX-1][positionAttackedY-1];
                if(positionAttackedIA == 1){
                    game.getJugadorIA().get(i).getTablero().getTablero()[positionAttackedX-1][positionAttackedY-1] = -6; //TOCADO POR EL USER
                }else if(positionAttackedIA == 0){
                    game.getJugadorIA().get(i).getTablero().getTablero()[positionAttackedX-1][positionAttackedY-1] = 6; //ATAQUE NORMAL USER
                }
            }
        }

    }


    private void updateTableroPlayerAttackIA(){
        for(int i = 0;i<numberPlayers;i++){
            Player attackerIA = game.getJugadorIA().get(i);
            Player attacker = game.getJugadorIA().get(i);
            int n = attacker.getPositionAttackedX().size() - 1;
            if(n>-1){
                int positionAttackedX = attacker.getPositionAttackedX().get(n);
                int positionAttackedY = attacker.getPositionAttackedY().get(n);
                int positionAttackedPlayer = game.getPlayer().getTablero().getTablero()[positionAttackedX-1][positionAttackedY-1];
                if(positionAttackedPlayer == 1){
                    game.getPlayer().getTablero().getTablero()[positionAttackedX-1][positionAttackedY-1] = (i+2)*-1; //TABLERO DE JUGADOR TOCADO POR IA
                } else if (positionAttackedPlayer == 0) {
                    game.getPlayer().getTablero().getTablero()[positionAttackedX-1][positionAttackedY-1] = i+2; //TABLERO DE JUGADOR ATAQUE DE IA
                }
            }

        }
    }

    private void checkHundidoTableroPlayer(){
        for(int i=0;i<5;i++){ //Recorremos todos los barcos
            int sizeBoat = game.getPlayer().getBoats().get(i).getSize();
            int positionBoatX = game.getPlayer().getBoats().get(i).getPositionX();
            int positionBoatY = game.getPlayer().getBoats().get(i).getPositionY();
            boolean boatOrientation = game.getPlayer().getBoats().get(i).getOrientation();
            int contador = 0;
            //Recorremos el barco para ver si todas su celdas estan tocadas = hundido
            for(int j=0;j<sizeBoat;j++){
                if(boatOrientation){ //Segun la orientacion nos movemos hacia la derecha o hacia abajo
                    int positionTableroBoat = game.getPlayer().getTablero().getTablero()[positionBoatX-1][positionBoatY-1+j];
                    if(positionTableroBoat >= -6 && positionTableroBoat <= -2){
                        contador++;
                    }
                }else{
                    int positionTableroBoat = game.getPlayer().getTablero().getTablero()[positionBoatX-1+j][positionBoatY-1];
                    if(positionTableroBoat >= -6 && positionTableroBoat <= -2){
                        contador++;
                    }
                }
            }
            if(contador == sizeBoat){ //Si todas las celdas estan tocadas las pondremos como hundido
                for(int j=0;j<sizeBoat;j++){
                    if(boatOrientation){
                        game.getPlayer().getTablero().getTablero()[positionBoatX-1][positionBoatY-1+j] = (i+7)*-1;
                    }else{
                        game.getPlayer().getTablero().getTablero()[positionBoatX-1+j][positionBoatY-1] = (i+7)*-1;
                    }
                }
            }

        }
    }

    private void checkHundidoTableroIA(){
        for(int i=0;i<numberPlayers;i++){ //Recorremos todas las IA
            for(int j=0;j<5;j++){ //Recorremos todos los barcos
                int sizeBoat = game.getJugadorIA().get(i).getBoats().get(j).getSize();
                int positionBoatX = game.getJugadorIA().get(i).getBoats().get(j).getPositionX();
                int positionBoatY = game.getJugadorIA().get(i).getBoats().get(j).getPositionY();
                boolean boatOrientation = game.getJugadorIA().get(i).getBoats().get(j).getOrientation();
                int contador = 0;
                for(int m=0;m<sizeBoat;m++){
                    if(boatOrientation){ //Segun la orientacion nos movemos hacia la derecha o hacia abajo
                        int positionTableroBoat = game.getJugadorIA().get(i).getTablero().getTablero()[positionBoatX-1][positionBoatY-1+m];
                        if(positionTableroBoat >= -6 && positionTableroBoat <= -2){
                            contador++;
                        }
                    }else{
                        int positionTableroBoat = game.getJugadorIA().get(i).getTablero().getTablero()[positionBoatX-1+m][positionBoatY-1];
                        if(positionTableroBoat >= -6 && positionTableroBoat <= -2){
                            contador++;
                        }
                    }
                }
                if(contador == sizeBoat){ //Si todas las celdas estan tocadas las pondremos como hundido
                    for(int m=0;m<sizeBoat;m++){
                        if(boatOrientation){
                            game.getJugadorIA().get(i).getTablero().getTablero()[positionBoatX-1][positionBoatY-1+m] = (i+7)*-1;
                        }else{
                            game.getJugadorIA().get(i).getTablero().getTablero()[positionBoatX-1+m][positionBoatY-1] = (i+7)*-1;
                        }
                    }
                }
            }
        }
    }





    public void updateTimer(){
        minuts = timeThread.getM();
        seconds = timeThread.getS();
        if(seconds <10 && minuts<10){
            time = "0" + minuts + ":0" + seconds;
        }else if(seconds < 10){
            time = minuts + ":0" + seconds;
        }else if(minuts < 10){
            time = "0" + minuts + ":" + seconds;
        }else {
            time = minuts + ":" + seconds;
        }
        gameStageController.updateTimer(time);
    }

    public void registerController(GameStageController gameStageController){
        this.gameStageController = gameStageController;
    }






}
