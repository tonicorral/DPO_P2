package Business;

public interface ThreadListener {

    boolean correctPosition(int fila,int columna,int attacker);

    int notifyAttack(int fila,int columna,int attacker);

}
