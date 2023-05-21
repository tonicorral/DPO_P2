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

   public Player realizarMovimiento(Player oponente) {
        Tablero tableroOponente = oponente.getTablero();
        int fila, columna;
        boolean ataqueExitoso = false;
        boolean intentoHundir = false;

        do{
            fila = randomPosition();
            columna = randomPosition();
        } while(!positionAttacked(fila,columna,oponente));

        oponente.getPositionAttackedX().add(fila);
        oponente.getPositionAttackedY().add(columna);


       System.out.println("movimiento" + oponente.getPositionAttackedX().get(0));


        return oponente;
    }


    public Game detectAttack(Game game,int fila,int columna){
        int numPlayers = game.getNumberPlayers();

        for (int i = 0;i<numPlayers;i++){
            if(game.getJugadorIA().get(i).getTablero().getTablero()[fila-1][columna-1] == 1){
                game.getJugadorIA().get(i).getTablero().getTablero()[fila-1][columna-1] = -1;
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








}
