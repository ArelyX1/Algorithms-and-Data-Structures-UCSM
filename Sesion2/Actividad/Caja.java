package Actividad;

public class Caja<T> {
    private T objeto;

    public void setObjeto(T objeto) {
        this.objeto = objeto;
    }

    public T getObjeto() {
        return objeto;
    }
}
