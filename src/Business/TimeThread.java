package Business;

import java.text.SimpleDateFormat;
import java.util.Date;



public class TimeThread extends Thread {

    private boolean running;

    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(){running=false;};
}