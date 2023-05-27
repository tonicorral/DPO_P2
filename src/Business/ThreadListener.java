package Business;

public interface ThreadListener {

    void stopAction();

    boolean correctPosition(int fila, int columna, int attacker);

    int notifyAttack(int fila,int columna,int attacker);

}
