package Actividad;

import java.util.ArrayList;

public class Cajoneria<T> {
    private ArrayList<Caja<T>> cajas;

    public Cajoneria() {
        this.cajas = new ArrayList<>();
    }

    public void addCaja(Caja<T> caja) {
        cajas.add(caja);
    }

    public int search(T elemento) {
        for (int i = 0; i < cajas.size(); i++) {
            if (cajas.get(i).getObjeto().equals(elemento)) {
                return i;
            }
        }
        return -1;
    }

    public T delete(T elemento) {
        int index = search(elemento);
        if (index != -1) {
            return cajas.remove(index).getObjeto();
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Posicion \tColorCaja \tObjeto\n");
        for (int i = 0; i < cajas.size(); i++) {
            sb.append("\t")
            .append(i + 1).append("\t")
              .append(cajas.get(i).getColor()).append("\t\t")
              .append(cajas.get(i).getObjeto().toString())
              .append("\n");
        }
        return sb.toString();
    }
}