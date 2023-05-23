package Business;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeThread extends Thread {
    private boolean running;
    private Date currentTime;

    public TimeThread() {
        running = true;
    }

    public void stopThread() {
        running = false;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    @Override
    public void run() {
        while (running) {
            currentTime = new Date();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}