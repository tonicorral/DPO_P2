package Presentation.Controllers;

import Business.Boat;
//import Business.JugadorHumano;
import Business.Player;
import Presentation.MainController;
import Presentation.MainView;
import Presentation.Views.SetupStageGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SetUpController implements ActionListener, MouseListener {

    private SetupStageGUI setUpGUI;

    private boolean mouseClicked = false;

    private int positionBoat;

    private String positionBoatTable;

    private boolean rotation = false;
    private boolean isClickedPorta = false,isClickedDestructor=false,isClickedSubmari=false,isClickedSubmari2=false,isClickedLlanxa=false;

    private MainView mainView;
    private MainController mainController;

    private  ArrayList<Boat> boats;

    private ArrayList<Player> players;

    private int[] positionPortaX,positionDestructorX,positionSubmariX,positionSubmari2X,positionLlanxaX;

    private int[] positionPortaY,positionDestructorY,positionSubmariY,positionSubmari2Y,positionLlanxaY;

    private int[][] positionPorta,positionDestructor,positionSubmari,positionSubmari2,positionLlanxa;
    public SetUpController(SetupStageGUI setUpGUI, MainView mainView, MainController mainController) {
        this.setUpGUI = setUpGUI;
        this.mainView = mainView;
        this.mainController = mainController;

        mainView.setActionMouseListeners(this, this);

        boats = new ArrayList<>();
        for(int i =0;i<5;i++){
            boats.add(null);
        }
        players = new ArrayList<>();
        for(int i =0;i< setUpGUI.getNumPlayers();i++){
            players.add(null);
        }
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand().equals(SetupStageGUI.ROTATE));

        if (e.getActionCommand().startsWith("cell") && mouseClicked) {
            positionBoatTable = e.getActionCommand();
            showTable(positionBoat,positionBoatTable,rotation);
            rotation = false;
        }
        mouseClicked = false;

        switch (e.getActionCommand()) {
            //case SetupStageGUI.BEGIN_BUTTON -> mainView.switchView(MainView.GAME_STAGE_VIEW);
            case SetupStageGUI.BEGIN_BUTTON:
                if(!checkAllBoatsPut()){
                    mainController.showError("Put all boats in the table before starting the game!");
                }else{
                    savePlayer(boats);
                    createRandomBoatPositions();
                    mainView.switchView(MainView.GAME_STAGE_VIEW);
                }
                    break;

            case SetupStageGUI.LOGOUT_BTN:
                mainView.switchView(MainView.LOGOUT_VIEW);
                break;
            case SetupStageGUI.DELETE_BTN:
                mainView.switchView(MainView.DELETE_VIEW);
                break;
            case SetupStageGUI.ROTATE:
                rotation = true;
                break;
            case SetupStageGUI.ELIMINATE:
                eliminateBoats();
                break;

        }


    }



    @Override
    public void mouseClicked(MouseEvent e) {
        mouseClicked = true;
        positionBoat = e.getComponent().getY();
        System.out.println("mouse click");
        System.out.println(e.getComponent());

    }



    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    private void isClickedBoat(String boat){

        switch(boat){
            case "PortaAvions" -> isClickedPorta = true;
            case "Destructor" -> isClickedDestructor = true;
            case "Submari" -> isClickedSubmari = true;
            case "Submari2" -> isClickedSubmari2 = true;
            case "Llanxa" -> isClickedLlanxa = true;
        }
    }

    private void showTable(int positionBoat, String positionBoatTable, boolean rotation){
        int size = 0;
        String boat = "boat";
        String text = "text";
        Color boatColor = Color.white;
        switch (positionBoat) {
            case 55,54 -> {
                size = 5;
                boat = "PortaAvions";
                text = "P";
                boatColor = new Color(124,136,248);

            }
            case 149,148 -> {
                size = 4;
                boat = "Destructor";
                text = "D";
                boatColor = Color.yellow;

            }
            case 243,242 -> {
                size = 3;
                boat = "Submari";
                text = "S";
                boatColor = Color.pink;

            }
            case 311,310 -> {
                size = 3;
                boat = "Submari2";
                text = "S";
                boatColor = Color.pink;

            }
            case 405,404 -> {
                size = 2;
                boat = "Llanxa";
                text = "L";
                boatColor = Color.green;

            }
        }
        char letter = positionBoatTable.charAt(4);
        int positionLetter = letter - 'A' + 1;
        int number = Integer.parseInt(positionBoatTable.substring(5));
        if (rotation && positionLetter+size-1 > 15){

            mainController.showError("Place the boat in the table please!");
        }
        else if (!rotation && number+size-1 > 15){

            mainController.showError("Place the boat in the table please!");
        } else if (isClickedPorta && boat.equals("PortaAvions")) {
            mainController.showError("Porta avions already used!");

        }
        else if (isClickedDestructor && boat.equals("Destructor")) {
            mainController.showError("Destructor already used!");

        }
        else if (isClickedSubmari && boat.equals("Submari")) {
            mainController.showError("Submari already used!");

        }
        else if (isClickedSubmari2 && boat.equals("Submari2")) {
            mainController.showError("Submari2 already used!");

        }
        else if (isClickedLlanxa && boat.equals("Llanxa")) {
            mainController.showError("Llanxa already used!");

        }else if (!badPositionBoat(number,positionLetter,size,rotation)) {
            mainController.showError("Cell already occupied!");

        }else{
            if (rotation){
                for (int i = 0; i < size; i++){
                    setUpGUI.paintUsedBoats(i,boat);
                    setUpGUI.paintBoatVertical(number,positionLetter,i,boatColor,text);
                }
                saveBoatPosition(boat, true,number,positionLetter,size,text);
            }
            else{
                for (int i = 0; i < size; i++){
                    setUpGUI.paintUsedBoats(i,boat);
                    setUpGUI.paintBoatHorizontal(number,positionLetter,i,boatColor,text);
                }
                saveBoatPosition(boat, false,number,positionLetter,size,text);
            }
            isClickedBoat(boat);
        }
    }

    private boolean badPositionBoat(int number,int positionLetter,int size,boolean rotation){
        boolean goodPosition = true;
        if (rotation) {
            for (int i = 0; i < size; i++) {
                if (!setUpGUI.checkCellVertical(number,positionLetter,i)) {
                    goodPosition = false;
                    break;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (!setUpGUI.checkCellHorizontal(number,positionLetter,i)) {
                    goodPosition = false;
                    break;
                }
            }
        }

        return goodPosition;
    }

    private void saveBoatPosition(String boat, Boolean orientation, int number, int positionLetter, int size, String text){

       switch(boat){
            case "PortaAvions" -> {
                Boat boat1 = new Boat(boat,size,text,number,positionLetter,orientation);
                boats.set(0,boat1);
            }
            case "Destructor" -> {
                Boat boat2 = new Boat(boat,size,text,number,positionLetter,orientation);
                boats.set(1,boat2);
            }
            case "Submari" ->{
                Boat boat3 = new Boat(boat,size,text,number,positionLetter,orientation);
                boats.set(2,boat3);
            }
            case "Submari2" -> {
                Boat boat4 = new Boat(boat,size,text,number,positionLetter,orientation);
                boats.set(3,boat4);
            }
            case "Llanxa" -> {
                Boat boat5 = new Boat(boat,size,text,number,positionLetter,orientation);
                boats.set(4,boat5);
            }
        }

    }

    private void savePlayer(ArrayList<Boat> boats){
        int randomNumber = 0;
        int numPlayers = setUpGUI.getNumPlayers()+1;
        int[] numbers = new int[numPlayers+1];
        for(int i = 0;i<numPlayers+1;i++){
            numbers[i] = i+1;
        }
        Random random = new Random();
        randomNumber = random.nextInt(numPlayers) + 1;
        //JugadorHumano player1 = new JugadorHumano(boats,new int[0],new int[0],new Tablero(),numbers[randomNumber]);
        //players.set(0,player1);
        System.out.println(numbers[randomNumber]-1);
        numbers[randomNumber] = 0;
        for (int i = 0;i<numPlayers-1;i++){
            do{
                randomNumber = random.nextInt(numPlayers) + 1;
            }while(numbers[randomNumber] == 0);
            //JugadorIA playerIA = new JugadorIA(boats,new int[0],new int[0],new Tablero(),numbers[randomNumber]);
            //players.set(i+1,playerIA);
            System.out.println(numbers[randomNumber]-1);
            numbers[randomNumber] = 0;
        }

        for (Boat boat : boats) {
            System.out.printf(boat.getName());
            System.out.printf(boat.getReferenceName());
            System.out.println(boat.getPositionX());
            System.out.println(boat.getPositionY());
            System.out.println(boat.getSize());
            System.out.println(boat.getOrientation());
            System.out.println(numPlayers);
            System.out.println(randomNumber);
        }
    }

    private void eliminateBoats(){
        isClickedDestructor = false;
        isClickedLlanxa = false;
        isClickedPorta = false;
        isClickedSubmari = false;
        isClickedSubmari2 = false;
        setUpGUI.unPaintTable();
        setUpGUI.unPaintBoats();
    }

    private boolean checkAllBoatsPut(){
        return isClickedSubmari2 && isClickedSubmari && isClickedPorta && isClickedLlanxa && isClickedDestructor;
    }

    private void createRandomBoatPositions(){
        int positionRandomX,positionRandomY;
        //for (int i = 0;i<5;i++){
            Random random = new Random();
            positionRandomX = random.nextInt(15)+1;
            positionRandomY = random.nextInt(15)+1;
            positionPorta = createPortaAvions(positionRandomX,positionRandomY,randomRotation());
            do{
                positionRandomX = random.nextInt(15)+1;
                positionRandomY = random.nextInt(15)+1;
                positionDestructor = createDestructor(positionRandomX,positionRandomY,randomRotation());
            } while(!checkDestructorIA(positionPorta,positionRandomX,positionRandomY,randomRotation()));
        //}
        System.out.println(Arrays.deepToString(positionPorta));
        System.out.println(Arrays.deepToString(positionDestructor));
    }

    private boolean randomRotation(){
        int rotation;
        Random random = new Random();
        rotation = random.nextInt(2) + 1;
        return rotation == 2;
    }

    private int[][] createPortaAvions(int positionRandomX,int positionRandomY,boolean rotation){
        positionPortaX = new int[5];
        positionPortaY = new int[5];

        for (int i = 0;i<5;i++){
            if(rotation){
                positionPortaX[i] = positionRandomX;
                positionPortaY[i] = positionRandomY + i;
            }
            else{
                positionPortaX[i] = positionRandomX + i;
                positionPortaY[i] = positionRandomY;
            }
        }

        return new int[][]{positionPortaX,positionPortaY};
    }

    private int[][] createDestructor(int positionRandomX, int positionRandomY, boolean rotation){
        positionDestructorX = new int[5];
        positionDestructorY = new int[5];
        for (int i = 0;i<4;i++){
            if(rotation){
                positionDestructorX[i] = positionRandomX;
                positionDestructorY[i] = positionRandomY + i;
            }
            else{
                positionDestructorX[i] = positionRandomX + i;
                positionDestructorY[i] = positionRandomY;
            }
        }
        return new int[][]{positionDestructorX,positionDestructorY};
    }

    private void createSubmari(int positionRandomX,int positionRandomY,boolean rotation){
        positionSubmariX = new int[5];
        positionSubmariY = new int[5];
        for (int i = 0;i<3;i++){
            if(rotation){
                positionSubmariX[i] = positionRandomX;
                positionSubmariY[i] = positionRandomY + i;
            }
            else{
                positionSubmariX[i] = positionRandomX + i;
                positionSubmariY[i] = positionRandomY;
            }
        }
    }

    private void createSubmari2(int positionRandomX,int positionRandomY,boolean rotation){
        positionSubmari2X = new int[5];
        positionSubmari2Y = new int[5];
        for (int i = 0;i<3;i++){
            if(rotation){
                positionSubmari2X[i] = positionRandomX;
                positionSubmari2Y[i] = positionRandomY + i;
            }
            else{
                positionSubmari2X[i] = positionRandomX + i;
                positionSubmari2Y[i] = positionRandomY;
            }
        }
    }

    private void createLlanxa(int positionRandomX,int positionRandomY,boolean rotation){
        positionLlanxaX = new int[5];
        positionLlanxaY = new int[5];
        for (int i = 0;i<2;i++){
            if(rotation){
                positionLlanxaX[i] = positionRandomX;
                positionLlanxaY[i] = positionRandomY + i;
            }
            else{
                positionLlanxaX[i] = positionRandomX + i;
                positionLlanxaY[i] = positionRandomY;
            }
        }
    }

    private boolean checkDestructorIA(int[][] positionPorta,int positionRandomX,int positionRandomY,boolean rotation){
        boolean ok1 = false,ok2 = false,ok3 = false,ok4 = false,ok5 = false,ok6 = false,ok7 = false,ok8=false,ok9=false;
        int[] positionPortaX = positionPorta[0];
        int[] positionPortaY = positionPorta[1];



                if(rotation){
                    for(int i = 0;i<4;i++) {
                        if (positionRandomX - 1 < 0) {
                            ok1 = true;
                        } else {
                            for (int j = 0; j < 5; j++) {
                                ok1 = positionRandomX - 1 == positionPortaX[j] && positionRandomY + i == positionPortaY[j];
                                if (ok1) {
                                    break;
                                }
                            }
                        }
                        if (positionRandomY - 1 < 0) {
                            ok2 = true;
                        } else {
                            for (int j = 0; j < 5; j++) {
                                ok2 = positionRandomX == positionPortaX[j] && positionRandomY + i - 1 == positionPortaY[j];
                                if (ok2) {
                                    break;
                                }
                            }
                        }
                        if (positionRandomX + 1 > 15) {
                            ok3 = true;
                        } else {
                            for (int j = 0; j < 5; j++) {
                                ok3 = positionRandomX + 1 == positionPortaX[j] && positionRandomY + i == positionPortaY[j];
                                if (ok3) {
                                    break;
                                }
                            }
                        }
                        if (positionRandomY + 1 > 15) {
                            ok4 = true;
                        } else {
                            for (int j = 0; j < 5; j++) {
                                ok4 = positionRandomX == positionPortaX[j] && positionRandomY + i + 1 == positionPortaY[j];
                                if (ok4) {
                                    break;
                                }
                            }
                        }
                        if (positionRandomY + 1 > 15 && positionRandomX + 1 > 15) {
                            ok5 = true;
                        } else {
                            for (int j = 0; j < 5; j++) {
                                ok5 = positionRandomX + 1 == positionPortaX[j] && positionRandomY + i + 1 == positionPortaY[j];
                                if (ok5) {
                                    break;
                                }
                            }
                        }
                        if (positionRandomY + 1 > 15 && positionRandomX - 1 < 0) {
                            ok6 = true;
                        } else {
                            for (int j = 0; j < 5; j++) {
                                ok6 = positionRandomX - 1 == positionPortaX[j] && positionRandomY + i + 1 == positionPortaY[j];
                                if (ok6) {
                                    break;
                                }
                            }
                        }
                        if (positionRandomY - 1 < 0 && positionRandomX - 1 < 0) {
                            ok7 = true;
                        } else {
                            for (int j = 0; j < 5; j++) {
                                ok7 = positionRandomX - 1 == positionPortaX[j] && positionRandomY + i - 1 == positionPortaY[j];
                                if (ok7) {
                                    break;
                                }
                            }
                        }
                        if (positionRandomY - 1 < 0 && positionRandomX + 1 > 15) {
                            ok8 = true;
                        } else {
                            for (int j = 0; j < 5; j++) {
                                ok8 = positionRandomX + 1 == positionPortaX[j] && positionRandomY + i - 1 == positionPortaY[j];
                                if (ok8) {
                                    break;
                                }
                            }
                        }
                        for (int j = 0; j < 5; j++) {
                            ok9 = positionRandomX == positionPortaX[j] && positionRandomY + i == positionPortaY[j];
                            if (ok9) {
                                break;
                            }
                        }
                    }
                    return ok1 && ok2 && ok3 && ok4 && ok5 && ok6 && ok7 && ok8 && ok9;
                }
                else{
                    for(int i = 0;i<4;i++) {
                    if(positionRandomX-1 < 0){
                        ok1 = true;
                    }else{
                        for(int j =0;j<5;j++){
                            ok1 = positionRandomX+i-1 == positionPortaX[j] && positionRandomY == positionPortaY[j];
                            if(ok1){
                                break;
                            }
                        }
                    }
                    if(positionRandomY-1 <0) {
                        ok2 = true;
                    }
                    else{
                        for(int j =0;j<5;j++){
                            ok2 = positionRandomX+i == positionPortaX[j] && positionRandomY-1 == positionPortaY[j];
                            if(ok2){
                                break;
                            }
                        }
                    }
                    if(positionRandomX+1 > 15){
                        ok3 = true;
                    }else{
                        for(int j =0;j<5;j++){
                            ok3 = positionRandomX+i+1 == positionPortaX[j] && positionRandomY == positionPortaY[j];
                            if(ok3){
                                break;
                            }
                        }
                    }
                    if(positionRandomY+1 > 15){
                        ok4 = true;
                    } else{
                        for(int j =0;j<5;j++){
                            ok4 = positionRandomX+i == positionPortaX[j] && positionRandomY+1 == positionPortaY[j];
                            if(ok4){
                                break;
                            }
                        }
                    }
                    if(positionRandomY+1 > 15 && positionRandomX+1>15){
                        ok5 = true;
                    } else{
                        for(int j =0;j<5;j++){
                            ok5 = positionRandomX+1+i == positionPortaX[j] && positionRandomY+1 == positionPortaY[j];
                            if(ok5){
                                break;
                            }
                        }
                    }
                    if(positionRandomY+1 > 15 && positionRandomX-1<0){
                        ok6 = true;
                    } else{
                        for(int j =0;j<5;j++){
                            ok6 = positionRandomX+i-1 == positionPortaX[j] && positionRandomY+1 == positionPortaY[j];
                            if(ok6){
                                break;
                            }
                        }
                    }
                    if(positionRandomY-1 < 0 && positionRandomX - 1 < 0){
                        ok7 = true;
                    } else{
                        for(int j =0;j<5;j++){
                            ok7 = positionRandomX+i-1 == positionPortaX[j] && positionRandomY-1 == positionPortaY[j];
                            if(ok7){
                                break;
                            }
                        }
                    }
                    if(positionRandomY-1 < 0 && positionRandomX+1 > 15){
                        ok8 = true;
                    } else{
                        for(int j =0;j<5;j++){
                            ok8 = positionRandomX+1+i == positionPortaX[j] && positionRandomY-1 == positionPortaY[j];
                            if(ok8){
                                break;
                            }
                        }
                    }
                    for(int j =0;j<5;j++){
                        ok9 = positionRandomX+i == positionPortaX[j] && positionRandomY == positionPortaY[j];
                        if(ok9){
                            break;
                        }
                    }

                }
                    return ok1&&ok2&&ok3&&ok4&&ok5&&ok6&&ok7&&ok8&&ok9;
            }
    }




}



