package Business;

import java.util.Date;

public interface ThreadTimer {
        void start();
        void stopTimer();
        void startTimer();
        int getM();
        int getS();

        void setS(int s);
}
