package Business;


/**
 * Interfaz del threadListener
 */
public interface ThreadListener {

    void startAction();

    void stopAction();

    boolean correctPosition(int fila, int columna, int attacker);

    int notifyAttack(int fila,int columna,int attacker);

}
