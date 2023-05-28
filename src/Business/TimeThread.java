package Business;


/**
 * Clase para calcular el tiempo transcurrido en partida.
 */
public class TimeThread extends Thread implements ThreadTimer {

    private boolean running;
    private int m;
    private int s;
    private GameModel gameModel;

    /**
     * Constructor para conectar la clase con gamModel.
     * @param gameModel Clase para controlar partida.
     */
    public TimeThread(GameModel gameModel){
        this.gameModel = gameModel;
    }

    /**
     * Clase que se utiliza para implementar un thread y así poder calcular el tiempo transcurrido.
     */
    @Override
    public void run() {
        while(running){
            try {
                sleep(1000);
                this.s++;
                gameModel.updateTimer();
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

    /**
     * Método para inicializar contador.
     */
    public void startTimer(){
        this.m = 0;
        this.s = 0;
        this.running = true;
    }

    /**
     * Método para parar contador.
     */
    public void stopTimer(){
        this.running = false;
    }

    /**
     * Método para consultar tiempo transcurrido.
     * @return Variable tipo int con el tiempo.
     */
    public int getM(){
        return m;
    }

    /**
     * Método para consultar tiempo transcurrido.
     * @return Variable tipo int con el tiempo.
     */
    public int getS(){
        return s;
    }


}