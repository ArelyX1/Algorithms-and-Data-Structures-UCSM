package Actividad;

public class Caja<T> {
    private T objeto;
    private String color;

    public Caja(T objeto, String color) {
        this.objeto = objeto;
        this.color = color;
    }
    public Caja(T objeto) {
        this.objeto = objeto;
        this.color = "Estandar";
    }

    public T getObjeto() {
        return objeto;
    }

    public void setObjeto(T objeto) {
        this.objeto = objeto;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}