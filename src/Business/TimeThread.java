package Business;

import Presentation.Controllers.GameStageController;

import java.text.SimpleDateFormat;
import java.util.Date;



public class TimeThread extends Thread {


    private boolean running;
    private int m;
    private int s;
    private GameStageController gameStageController;

    public TimeThread(GameStageController gameStageController){
        this.gameStageController = gameStageController;
    }

    @Override
    public void run() {
        while(running){
            try {
                sleep(1000);
                this.s++;
                gameStageController.updateTimer();
                if(s >= 60){
                    this.m++;
                    this.s = 0;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        super.run();
    }

    public void startTimer(){
        this.m = 0;
        this.s = 0;
        this.running = true;
    }

    public void stopTimer(){
        this.running = false;
    }

    public int getM(){
        return m;
    }

    public void setS(int s){
        this.s = s;
    }

    public int getS(){
        return s;
    }


}