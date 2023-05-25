package Business;

import java.text.SimpleDateFormat;
import java.util.Date;



public class TimeThread extends Thread {
    private boolean running;
    private Date currentTime;
    private long startTime;

    public TimeThread() {
        running = true;
        this.startTime = 0;
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
            try {
                Thread.sleep(1000);
                listener.notify()
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}