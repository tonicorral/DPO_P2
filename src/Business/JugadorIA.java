package Business;

import java.util.ArrayList;
import java.util.Random;

public class JugadorIA extends Player implements Runnable{
    private ThreadListener listener;

    public JugadorIA(ThreadListener listener) {
        this.listener = listener;


        //create boats
    }






    private int generarNumeroAleatorio(int max) {
        return (int) (Math.random() * max);
    }


    @Override
    public void run() {
        while(true){
            sleep()

        }
    }
}
