package Business;

import java.util.ArrayList;
import java.util.Random;

/**
 * Implementa las distintas acciones de la IA
 */
public class IAModel extends Thread implements ThreadListener{

    private boolean running = false;
    private GameModel gameModel;
    private int positionPortaX,positionDestructorX,positionSubmariX,positionSubmari2X,positionLlanxaX;

    private int positionPortaY,positionDestructorY,positionSubmariY,positionSubmari2Y,positionLlanxaY;

    private boolean rotationPorta = false,rotationDestructor = false,rotationSubmari = false,rotationSubmari2 = false,rotationLlanxa = false;
    private Game game;
    private int numberPlayers;
    private int counter = 1;
    private Configuration config;
    public IAModel () {
        this.config = new Configuration("Files/config.json");
    }

    /**
     * Crea los barcos para el jugador de IA.
     * @return Un objeto de tipo JugadorIA con los barcos creados y otros atributos.
     **/
    public JugadorIA createBoats(){
        ArrayList<Boat> boats = new ArrayList<>();

        createPortaAvions();
        Boat portaAvions = new Boat("PortaAvions",5,"P",positionPortaX,positionPortaY,rotationPorta,"Alive");
        boats.add(portaAvions);
        createDestructor();
        Boat destructor = new Boat("Destructor",4,"D",positionDestructorX,positionDestructorY,rotationDestructor,"Alive");
        boats.add(destructor);
        createSubmari();
        Boat submari = new Boat("Submari",3,"S",positionSubmariX,positionSubmariY,rotationSubmari,"Alive");
        boats.add(submari);
        createSubmari2();
        Boat submari2 = new Boat("Submari2",3,"S",positionSubmari2X,positionSubmari2Y,rotationSubmari2,"Alive");
        boats.add(submari2);
        createLlanxa();
        Boat llanxa = new Boat("Llanxa",2,"L",positionLlanxaX,positionLlanxaY,rotationLlanxa,"Alive");
        boats.add(llanxa);


        return new JugadorIA(boats,new ArrayList<>(),new ArrayList<>(),new Tablero(boats),true);
    }




    /**
     * Genera una posición aleatoria.
     * @return Un número entero que representa la posición aleatoria.
     **/
    private int randomPosition(){
        Random random = new Random();
        return random.nextInt(15)+1;
    }

    /**
     * Genera una rotación aleatoria para los barcos.
     * @return Un valor booleano que representa la rotación aleatoria (true: vertical, false: horizontal).
     **/
    private boolean randomRotation(){
        int rotation;
        Random random = new Random();
        rotation = random.nextInt(2) + 1;
        return rotation == 2;
    }


    /**
     * Crea la posición para el barco PortaAvions.
     * Verifica que la posición y rotación generadas no colisionen con otros barcos.
     **/
    private void createPortaAvions(){
        do{
            positionPortaX = randomPosition(); //Extract positionX
            positionPortaY = randomPosition(); //Extract positionY
            rotationPorta = randomRotation(); //Extract orientation
        }while(checkInTable(positionPortaX,positionPortaY,rotationPorta,5));
    }

    /**
     * Verifica si un barco colisiona con otros barcos en la tabla.
     * @param positionX Coordenada X de la posición inicial del barco.
     * @param positionY Coordenada Y de la posición inicial del barco.
     * @param rotation true si el barco está en posición vertical, false si está en posición horizontal.
     * @param size Longitud del barco.
     * @return true si hay colisión, false de lo contrario.
     **/
    private boolean checkInTable(int positionX,int positionY,boolean rotation,int size){

        if(rotation){
            return positionY + size > 16;
        } else{
            return positionX + size > 16;
        }
    }

    /**
     * Crea un barco Destructor en una posición aleatoria dentro del tablero.
     * Verifica que no haya colisión con otros barcos.
     **/
    private void createDestructor(){
        do{
            positionDestructorX = randomPosition();
            positionDestructorY = randomPosition();
            rotationDestructor = randomRotation();
        }while(checkInTable(positionDestructorX,positionDestructorY,rotationDestructor,4)
                || checkBoatWithAnother(positionDestructorX,positionDestructorY,rotationDestructor,4,positionPortaX,positionPortaY,rotationPorta,5));
    }


    /**
     * Crea un submarino en una posición aleatoria dentro del tablero.
     * Verifica que no haya colisión con otros barcos.
     **/
    private void createSubmari(){
        do{
            positionSubmariX = randomPosition();
            positionSubmariY = randomPosition();
            rotationSubmari = randomRotation();
        }while (checkInTable(positionSubmariX,positionSubmariY,rotationSubmari,3)
                || checkBoatWithAnother(positionSubmariX,positionSubmariY,rotationSubmari,3,positionPortaX,positionPortaY,rotationPorta,5)
                || checkBoatWithAnother(positionSubmariX,positionSubmariY,rotationSubmari,3,positionDestructorX,positionDestructorY,rotationDestructor,4));
    }

    /**
     * Crea un segundo submarino en una posición aleatoria dentro del tablero.
     * Verifica que no haya colisión con otros barcos.
     **/
    private void createSubmari2(){
        do{
            positionSubmari2X = randomPosition();
            positionSubmari2Y = randomPosition();
            rotationSubmari2 = randomRotation();
        }while (checkInTable(positionSubmari2X,positionSubmari2Y,rotationSubmari2,3)
                || checkBoatWithAnother(positionSubmari2X,positionSubmari2Y,rotationSubmari2,3,positionPortaX,positionPortaY,rotationPorta,5)
                || checkBoatWithAnother(positionSubmari2X,positionSubmari2Y,rotationSubmari2,3,positionDestructorX,positionDestructorY,rotationDestructor,4)
                || checkBoatWithAnother(positionSubmari2X,positionSubmari2Y,rotationSubmari2,3,positionSubmariX,positionSubmariY,rotationSubmari,3)
        );
    }

    /**
     * Crea una lancha en una posición aleatoria dentro del tablero.
     * Verifica que no haya colisión con otros barcos.
     **/
    private void createLlanxa(){
        do{
            positionLlanxaX = randomPosition();
            positionLlanxaY = randomPosition();
            rotationLlanxa = randomRotation();
        }while (checkInTable(positionLlanxaX,positionLlanxaY,rotationLlanxa,2)
                || checkBoatWithAnother(positionLlanxaX,positionLlanxaY,rotationLlanxa,2,positionPortaX,positionPortaY,rotationPorta,5)
                || checkBoatWithAnother(positionLlanxaX,positionLlanxaY,rotationLlanxa,2,positionDestructorX,positionDestructorY,rotationDestructor,4)
                || checkBoatWithAnother(positionLlanxaX,positionLlanxaY,rotationLlanxa,2,positionSubmariX,positionSubmariY,rotationSubmari,3)
                || checkBoatWithAnother(positionLlanxaX,positionLlanxaY,rotationLlanxa,2,positionSubmari2X,positionSubmari2Y,rotationSubmari2,3));
    }



    /**
     * Verifica si un barco está colisionando con otro barco en el tablero.
     * Comprueba las posiciones de los barcos y sus tamaños para determinar si hay colisión.
     *
     * @param positionX      La posición X del barco actual.
     * @param positionY      La posición Y del barco actual.
     * @param rotation       La rotación del barco actual.
     * @param size           El tamaño del barco actual.
     * @param positionOutX   La posición X del otro barco.
     * @param positionOutY   La posición Y del otro barco.
     * @param rotationOut    La rotación del otro barco.
     * @param sizeOut        El tamaño del otro barco.
     * @return true si hay colisión, false de lo contrario.
     **/
    private boolean checkBoatWithAnother(int positionX, int positionY, boolean rotation,int size,int positionOutX,int positionOutY,boolean rotationOut,int sizeOut){
        boolean checkBoat = false;
        if(rotation){
            if(rotationOut){
                for(int i = 0;i<size;i++){
                    for(int j = 0;j<sizeOut;j++){
                        if      (positionX-1 == positionOutX && positionY+i == positionOutY+j ||
                                positionX-1 == positionOutX && positionY+i-1 == positionOutY+j ||
                                positionX-1 == positionOutX && positionY+i+1 == positionOutY+j ||
                                positionX + 1 == positionOutX && positionY+i == positionOutY+j ||
                                positionX + 1 == positionOutX && positionY+i+1 == positionOutY+j ||
                                positionX+1 == positionOutX && positionY+i-1 == positionOutY+j){
                            checkBoat = true;
                            break;
                        }
                    }

                }
            }
            else{
                for(int i = 0;i<size;i++){
                    for(int j = 0;j<sizeOut;j++){
                        if      (positionX-1 == positionOutX+j && positionY+i == positionOutY ||
                                positionX-1 == positionOutX+j && positionY+i-1 == positionOutY ||
                                positionX-1 == positionOutX+j && positionY+i+1 == positionOutY ||
                                positionX + 1 == positionOutX+j && positionY+i == positionOutY ||
                                positionX + 1 == positionOutX+j && positionY+i+1 == positionOutY ||
                                positionX+1 == positionOutX+j && positionY+i-1 == positionOutY){
                            checkBoat = true;
                            break;
                        }
                    }

                }
            }

        }else{
            if(rotationOut){
                for(int i = 0;i<size;i++){
                    for(int j = 0;j<sizeOut;j++){
                        if      (positionX-1+i == positionOutX && positionY == positionOutY+j ||
                                positionX-1+i == positionOutX && positionY-1 == positionOutY+j ||
                                positionX-1+i == positionOutX && positionY+1 == positionOutY+j ||
                                positionX + 1+i == positionOutX && positionY == positionOutY+j ||
                                positionX + 1+i == positionOutX && positionY+1 == positionOutY+j ||
                                positionX+1+i == positionOutX && positionY-1 == positionOutY+j){
                            checkBoat = true;
                            break;
                        }
                    }

                }
            }
            else{
                for(int i = 0;i<size;i++){
                    for(int j = 0;j<sizeOut;j++){
                        if      (positionX-1+i == positionOutX+j && positionY == positionOutY ||
                                positionX-1+i == positionOutX+j && positionY-1 == positionOutY ||
                                positionX-1+i == positionOutX+j && positionY+1 == positionOutY ||
                                positionX + 1+i == positionOutX+j && positionY == positionOutY ||
                                positionX + 1+i == positionOutX+j && positionY+1 == positionOutY ||
                                positionX+1+i == positionOutX+j && positionY-1 == positionOutY){
                            checkBoat = true;
                            break;
                        }
                    }

                }
            }
        }
        return checkBoat;
    }


    /**
     * Obtiene el juego actual.
     *
     * @param game El objeto Game que representa el juego actual.
     **/
    public void getGame(Game game){
        this.game = game;
    }

    /**
     * Realiza un ataque diferente para el jugador de la IA.
     * Determina una posición de ataque basada en la estrategia de la IA y la ejecuta en el juego.
     *
     * @param i El índice del jugador de la IA.
     **/
    private synchronized void makeDifferentAttack(int i) {
        int fila=0, columna=0;
        Player attacker = game.getJugadorIA().get(i);

        if (attacker.getPositionAttackedX().isEmpty()) {
            fila = randomPosition();
            columna = randomPosition();
        } else {
            if (ataqueTocado(attacker, game, i)) {
                do {
                    int lastMovementX = attacker.getPositionAttackedX().get(attacker.getPositionAttackedX().size() - 1);
                    int lastMovementY = attacker.getPositionAttackedY().get(attacker.getPositionAttackedY().size() - 1);
                    int adyacente = randomAdyacente();
                    if(!positionAttacked(lastMovementX+1,lastMovementY,game) && !positionAttacked(lastMovementX,lastMovementY-1,game) && !positionAttacked(lastMovementX-1,lastMovementY,game) && !positionAttacked(lastMovementX,lastMovementY+1,game)){
                        fila = randomPosition();
                        columna = randomPosition();
                    }else{
                        switch (adyacente) {
                            case 1 -> {
                                fila = lastMovementX + 1;
                                columna = lastMovementY;
                            }
                            case 2 -> {
                                fila = lastMovementX;
                                columna = lastMovementY - 1;
                            }
                            case 3 -> {
                                fila = lastMovementX - 1;
                                columna = lastMovementY;
                            }
                            case 4 -> {
                                fila = lastMovementX;
                                columna = lastMovementY + 1;
                            }
                        }
                    }
                } while (!ataqueTocado(attacker, game, i) || !positionAttacked(fila, columna, game) || outTablero(fila,columna));
            } else {
                do {
                    fila = randomPosition();
                    columna = randomPosition();
                } while (!positionAttacked(fila, columna, game) /*|| !positionHit(fila, columna, attacker, game, i)*/);
            }
        }

        if(outTablero(fila,columna)){
            fila = randomPosition();
            columna = randomPosition();
        }

        attacker.getPositionAttackedX().add(fila);
        attacker.getPositionAttackedY().add(columna);

        gameModel.IAAttacks(game);
    }


    /**
     * Verifica si una posición se encuentra fuera del tablero.
     *
     * @param fila    La fila de la posición.
     * @param columna La columna de la posición.
     * @return true si la posición está fuera del tablero, false de lo contrario.
     */
    private boolean outTablero(int fila,int columna){
        return fila > 15 || columna > 15 || fila < 1 || columna < 1 ;
    }

    /**
     * Genera un número aleatorio entre 1 y 4, que representa una dirección adyacente.
     *
     * @return El número aleatorio generado.
     */
    private int randomAdyacente(){
        Random random = new Random();
        return random.nextInt(4)+1;
    }

    /**
     * Verifica si el ataque realizado ha tocado un barco en la posición más reciente.
     *
     * @param attacker El jugador atacante.
     * @param game     El objeto del juego.
     * @param i        Un número utilizado en la comparación.
     * @return true si el ataque ha tocado un barco, false de lo contrario.
     */
    private boolean ataqueTocado(Player attacker, Game game, int i){
        boolean tocado = false;
        for (int j = 0;j<numberPlayers;j++){
            int lastMovementX  = attacker.getPositionAttackedX().get(attacker.getPositionAttackedX().size() - 1) -1;
            int lastMovementY = attacker.getPositionAttackedY().get(attacker.getPositionAttackedY().size() - 1) -1;
            if(game.getJugadorIA().get(j).getTablero().getTablero()[lastMovementX][lastMovementY] == ((i+2) *-1) || game.getPlayer().getTablero().getTablero()[lastMovementX][lastMovementY] == ((i+2) *-1)){
                tocado = true;
            }
        }
        return tocado;
    }


    /**
     * Verifica si una posición ya ha sido atacada en el juego.
     *
     * @param fila    La fila de la posición.
     * @param columna La columna de la posición.
     * @param game    El objeto del juego.
     * @return true si la posición no ha sido atacada, false de lo contrario.
     */
    private boolean positionAttacked(int fila,int columna,Game game){
        boolean notAttackedIA = true,notAttackedUser = true;
        numberPlayers = game.getNumberPlayers();

        for(int i = 0;i<numberPlayers;i++){
            if(game.getJugadorIA().get(i).getPositionAttackedX().size() > 0){
                for(int j=0;j<game.getJugadorIA().get(i).getPositionAttackedX().size();j++){
                    if(game.getJugadorIA().get(i).getPositionAttackedX().get(j) == fila){
                        if(game.getJugadorIA().get(i).getPositionAttackedX().get(j) == columna){
                            notAttackedIA = false;
                        }
                    }
                }
            }
        }

        return notAttackedIA;
    }

    /**
     * Registra el modelo de juego.
     *
     * @param gameModel El modelo de juego a registrar.
     */
    public void registerGameModel(GameModel gameModel){this.gameModel = gameModel;}

    /**
     * Run de los ataques
     */
    @Override
    public void run() {
        while(true){
            if(running){
                try {
                    sleep(config.getTime());
                    if(counter < numberPlayers-1){
                        counter++;
                    }else{
                        counter = 0;
                    }
                    if(game.getJugadorIA().get(counter).isAlive()){
                        makeDifferentAttack(counter);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    /**
     * Ejecuta el bucle principal del hilo.
     * Comprueba periódicamente si es el turno de un jugador IA y realiza un ataque diferente.
     * El bucle se ejecuta continuamente hasta que se detenga el hilo.
     */
    @Override
    public synchronized void startAction() {
        running = true;
        if (!super.isAlive()) {
            super.start();
        }
    }


    /**
     * Inicia la acción del hilo.
     * Establece el estado de ejecución en activo y si el hilo no está vivo, lo inicia.
     */
    @Override
    public synchronized void stopAction() {
        running = false;
    }


    /**
     * Verifica si la posición proporcionada es correcta para el atacante especificado.
     *
     * @param fila     La fila de la posición a verificar.
     * @param columna  La columna de la posición a verificar.
     * @param attacker El identificador del atacante.
     * @return true si la posición es correcta, false en caso contrario.
     */
    @Override
    public boolean correctPosition(int fila, int columna, int attacker) {
        return false;
    }

    /**
     * Notifica un ataque en la posición especificada al jugador IA.
     *
     * @param fila     La fila de la posición atacada.
     * @param columna  La columna de la posición atacada.
     * @param attacker El identificador del atacante.
     * @return El resultado del ataque.
     */
    @Override
    public int notifyAttack(int fila, int columna, int attacker) {
        return 0;
    }


}









