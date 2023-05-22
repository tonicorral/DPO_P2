package Business;

import java.util.ArrayList;
import java.util.Random;

public class IAModel {

    private int positionPortaX,positionDestructorX,positionSubmariX,positionSubmari2X,positionLlanxaX;

    private int positionPortaY,positionDestructorY,positionSubmariY,positionSubmari2Y,positionLlanxaY;

    private boolean rotationPorta = false,rotationDestructor = false,rotationSubmari = false,rotationSubmari2 = false,rotationLlanxa = false;

    public JugadorIA createBoats(){
        ArrayList<Boat> boats = new ArrayList<>();

        createPortaAvions();
        Boat portaAvions = new Boat("PortaAvions",5,"P",positionPortaX,positionPortaY,rotationPorta);
        boats.add(portaAvions);
        createDestructor();
        Boat destructor = new Boat("Destructor",4,"D",positionDestructorX,positionDestructorY,rotationDestructor);
        boats.add(destructor);
        createSubmari();
        Boat submari = new Boat("Submari",3,"S",positionSubmariX,positionSubmariY,rotationSubmari);
        boats.add(submari);
        createSubmari2();
        Boat submari2 = new Boat("Submari2",3,"S",positionSubmari2X,positionSubmari2Y,rotationSubmari2);
        boats.add(submari2);
        createLlanxa();
        Boat llanxa = new Boat("Llanxa",2,"L",positionLlanxaX,positionLlanxaY,rotationLlanxa);
        boats.add(llanxa);

        return new JugadorIA(boats,new ArrayList<>(),new ArrayList<>(),new Tablero(boats));
    }

    private int randomPosition(){
        Random random = new Random();
        return random.nextInt(15)+1;
    }

    private boolean randomRotation(){
        int rotation;
        Random random = new Random();
        rotation = random.nextInt(2) + 1;
        return rotation == 2;
    }

    private void createPortaAvions(){
        do{
            positionPortaX = randomPosition(); //Extract positionX
            positionPortaY = randomPosition(); //Extract positionY
            rotationPorta = randomRotation(); //Extract orientation
        }while(checkInTable(positionPortaX,positionPortaY,rotationPorta,5));
    }

    private boolean checkInTable(int positionX,int positionY,boolean rotation,int size){

        if(rotation){
            return positionY + size > 16;
        } else{
            return positionX + size > 16;
        }
    }

    private void createDestructor(){
        do{
            positionDestructorX = randomPosition();
            positionDestructorY = randomPosition();
            rotationDestructor = randomRotation();
        }while(checkInTable(positionDestructorX,positionDestructorY,rotationDestructor,4)
                || checkBoatWithAnother(positionDestructorX,positionDestructorY,rotationDestructor,4,positionPortaX,positionPortaY,rotationPorta,5));
    }


    private void createSubmari(){
        do{
            positionSubmariX = randomPosition();
            positionSubmariY = randomPosition();
            rotationSubmari = randomRotation();
        }while (checkInTable(positionSubmariX,positionSubmariY,rotationSubmari,3)
                || checkBoatWithAnother(positionSubmariX,positionSubmariY,rotationSubmari,3,positionPortaX,positionPortaY,rotationPorta,5)
                || checkBoatWithAnother(positionSubmariX,positionSubmariY,rotationSubmari,3,positionDestructorX,positionDestructorY,rotationDestructor,4));
    }

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


    public Game makeDifferentAttack(Game game,int i){
        int fila,columna;
        Player attacker = game.getJugadorIA().get(i); //Atacante
        do{
            fila = randomPosition();
            columna = randomPosition();
        } while(!positionAttacked(fila,columna,attacker,game,i));

        game.getJugadorIA().get(i).getPositionAttackedX().add(fila);
        game.getJugadorIA().get(i).getPositionAttackedY().add(columna);

        return game;
    }

    private boolean positionAttacked(int fila,int columna,Player oponente,Game game,int attacker){
        boolean notAttacked = true;
        int numPlayers = game.getNumberPlayers();
        for(int i = 0;i<oponente.getPositionAttackedX().size();i++){
            for(int j = 0;j<numPlayers;j++){
                if(attacker != j){
                    if(game.getJugadorIA().get(j).getPositionAttackedX().get(i) == fila){
                        if(game.getJugadorIA().get(j).getPositionAttackedY().get(i) == columna){
                            notAttacked = false;
                            break;
                        }
                    }
                }

            }
        }
        return notAttacked;
    }

    public Game updateTablero(Game game){

        int numPlayers = game.getNumberPlayers();

        for(int i = 0;i<numPlayers;i++){
            Player attacker = game.getJugadorIA().get(i);
            int n = attacker.getPositionAttackedX().size() - 1;
            int positionAttackedX = attacker.getPositionAttackedX().get(n);
            int positionAttackedY = attacker.getPositionAttackedY().get(n);
            for(int j=0;j<numPlayers;j++){
                if(j!=i){
                    game.getJugadorIA().get(j).getTablero().getTablero()[positionAttackedX-1][positionAttackedY-1] = i+2;
                }
            }

        }







        return game;
    }














    /*
   public Game realizarMovimiento(Game game) {
        Player oponente = game.getJugadorIA().get(0);
        Tablero tableroOponente = oponente.getTablero();
        int fila, columna;
        boolean ataqueExitoso = false;
        boolean intentoHundir = false;


        if(oponente.getPositionAttackedX().size() <= 1){
            fila = randomPosition();
            columna = randomPosition();
        } else{
            do{
                fila = randomPosition();
                columna = randomPosition();
            } while(!positionAttacked(fila,columna,oponente) || !touchBoat(fila,columna,game,oponente));
        }
       oponente.getPositionAttackedX().add(fila);
       oponente.getPositionAttackedY().add(columna);

        game = detectAttack(game,oponente.getPositionAttackedX().get(oponente.getPositionAttackedX().size()-1),oponente.getPositionAttackedY().get(oponente.getPositionAttackedY().size()-1));

        return game;
    }


    public Game detectAttack(Game game,int fila,int columna){
        int numPlayers = game.getNumberPlayers();

        for (int i = 0;i<numPlayers;i++){
            if(game.getJugadorIA().get(i).getTablero().getTablero()[fila-1][columna-1] == 1){
                game.getJugadorIA().get(i).getTablero().setPosicion(fila-1,columna-1,-1);
            }
        }

        return game;
    }




    private boolean positionAttacked(int fila,int columna,Player oponente){
        boolean notAttacked = true;
        for(int i = 0;i<oponente.getPositionAttackedX().size();i++){
            if(oponente.getPositionAttackedX().get(i) == fila){
                if(oponente.getPositionAttackedY().get(i) == columna){
                    notAttacked = false;
                    break;
                }
            }
        }

        return notAttacked;
    }

    private boolean touchBoat(int fila,int columna,Game game,Player oponente){
        boolean done = false;
        int numPlayers = game.getNumberPlayers();
        int lastMovementX = oponente.getPositionAttackedX().get(oponente.getPositionAttackedX().size()-1);
        int lastMovementY = oponente.getPositionAttackedX().get(oponente.getPositionAttackedX().size()-1);
        int last2MovementX = oponente.getPositionAttackedX().get(oponente.getPositionAttackedX().size()-2);
        int last2MovementY = oponente.getPositionAttackedX().get(oponente.getPositionAttackedX().size()-2);

        for(int i = 0;i<numPlayers;i++){
            System.out.println("gogogo");
            System.out.println(game.getJugadorIA().get(i).getTablero().getTablero()[lastMovementX-1][lastMovementY-1]);
            System.out.println(game.getJugadorIA().get(i).getTablero().getTablero()[last2MovementX-1][last2MovementY-1]);
            if(game.getJugadorIA().get(i).getTablero().getTablero()[lastMovementX-1][lastMovementY-1] == -1 && game.getJugadorIA().get(i).getTablero().getTablero()[last2MovementY-1][last2MovementX-1] == -1){
                System.out.println("ooooooooooo");
                if(lastMovementX == last2MovementX+1 && lastMovementY == last2MovementY){
                    if(lastMovementX + 1 == fila){
                        done = true;
                        break;
                    } else{
                        done = false;
                    }
                } else if (lastMovementX == last2MovementX-1 && lastMovementY == last2MovementY) {
                    if(lastMovementX - 1 == fila){
                        done = true;
                        break;
                    } else{
                        done = false;
                    }
                } else if (lastMovementX == last2MovementX && lastMovementY == last2MovementY-1) {
                    if(lastMovementY - 1 == columna){
                        done = true;
                        break;
                    } else{
                        done = false;
                    }
                } else{
                    if(lastMovementY + 1 == columna){
                        done = true;
                        break;
                    } else{
                        done = false;
                    }
                }
            } else if (game.getJugadorIA().get(i).getTablero().getTablero()[lastMovementX-1][lastMovementY-1] == -1) {
                System.out.println("eeeeeeeeeee");
                int[][] posicionesAdyacentes = {
                        {lastMovementX - 2, lastMovementY-1}, // Arriba
                        {lastMovementX, lastMovementY-1}, // Abajo
                        {lastMovementX-1, lastMovementY - 2}, // Izquierda
                        {lastMovementX-1, lastMovementY}  // Derecha
                };
                for (int[] posicion : posicionesAdyacentes) {
                    int newFila = posicion[0];
                    int newColumna = posicion[1];

                    if(fila == newFila && columna == newColumna){
                        System.out.println(fila);
                        //le da a un adyacente
                        done = true;
                        break;
                    }

                }
            }
            else{
                done = true;
            }
        }

        return done;
    }*/

   /* private boolean hundirBarco(Game game){
        boolean hundido = false;


        return hundido;
    }*/

    /*
    public Game makeMovement(Game game) {
        Player oponente = game.getJugadorIA().get(0);
        Tablero tableroOponente = oponente.getTablero();
        int fila = 0, columna = 0;
        boolean ataqueExitoso = false;
        boolean intentoHundir = false;

        // Atacar casillas aleatoriamente hasta conseguir un impacto
        while (!ataqueExitoso) {
            if (!intentoHundir) {
                fila = randomPosition();
                columna = randomPosition();
            } else {
                // Obtener la última posición de ataque exitosa
                int ultimaFila = oponente.getPositionAttackedX().get(oponente.getPositionAttackedX().size() - 1);
                int ultimaColumna = oponente.getPositionAttackedY().get(oponente.getPositionAttackedY().size() - 1);

                // Generar las posibles casillas adyacentes al último ataque exitoso
                int[][] posicionesAdyacentes = {
                        {ultimaFila - 1, ultimaColumna}, // Arriba
                        {ultimaFila + 1, ultimaColumna}, // Abajo
                        {ultimaFila, ultimaColumna - 1}, // Izquierda
                        {ultimaFila, ultimaColumna + 1}  // Derecha
                };
                // Recorrer las posiciones adyacentes y atacar la primera casilla válida
                for (int[] posicion : posicionesAdyacentes) {
                    fila = posicion[0];
                    columna = posicion[1];

                    // Verificar si la posición está dentro del rango del tablero
                    if (fila >= 0 && fila < 15 && columna >= 0 && columna < 15) {
                        if (tableroOponente.getTablero()[fila-1][columna-1] == Tablero.AGUA || tableroOponente.getTablero()[fila-1][columna-1] == Tablero.BARCO) {
                            if (tableroOponente.getTablero()[fila-1][columna-1] == Tablero.BARCO) {
                                tableroOponente.getTablero()[fila-1][columna-1] = Tablero.TOCADO;
                                ataqueExitoso = true;
                                intentoHundir = true; // Indicar que se ha logrado un impacto y se intentará hundir el barco
                                break;
                            } else {
                                tableroOponente.getTablero()[fila-1][columna-1] = Tablero.AGUA;
                                break;
                            }
                        }
                    }
                }

                // Si no se encontró una casilla adyacente válida, realizar un ataque aleatorio
                if (!ataqueExitoso) {
                    fila = randomPosition();
                    columna = randomPosition();
                }
            }

            if (tableroOponente.getTablero()[fila-1][columna-1] == Tablero.AGUA || tableroOponente.getTablero()[fila-1][columna-1] == Tablero.BARCO) {
                if (tableroOponente.getTablero()[fila-1][columna-1] == Tablero.BARCO) {
                    tableroOponente.getTablero()[fila-1][columna-1] = Tablero.TOCADO;
                    ataqueExitoso = true;
                    intentoHundir = true; // Indicar que se ha logrado un impacto y se intentará hundir el barco
                } else {
                    tableroOponente.getTablero()[fila-1][columna-1] = Tablero.AGUA;
                }
            }
        }
        return game;
    }*/





}
