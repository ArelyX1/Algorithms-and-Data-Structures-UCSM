package Sesion1v2;

import javax.swing.*;
import java.awt.*;

interface Figura {
    double getBase();
    double getAlto();
}

public class Rectangulo implements Figura {
    private Coordenada punto1, punto2;

    public Rectangulo(Coordenada pun_a, Coordenada pun_b) {
        this.punto1 = pun_a;
        this.punto2 = pun_b;
    }

    public double getBase() {
        return (punto2.getX() - punto1.getX());
    }

    public double getAlto() {
        return (punto2.getY() - punto1.getY());
    }

    public static double area(Rectangulo rect) {
        return rect.getBase() * rect.getAlto();
    }

    public Coordenada getPunto1() {
        return punto1;
    }

    public Coordenada getPunto2() {
        return punto2;
    }

    @Override
    public String toString() {
        return "[" + punto1 + ", " + punto2 + "]";
    }
}

class DibujoRectangulo extends JPanel {
    private FigureContainer rectangulos;
    private FigureContainer ListaIntersectados;

    public DibujoRectangulo(FigureContainer rectangulos) {
        this.rectangulos = rectangulos;
        this.ListaIntersectados = new FigureContainer(); // Inicializar ListaIntersectados
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (int a = 0; a < rectangulos.size(); a++) {
            Rectangulo rect = (Rectangulo) rectangulos.get(a);
            int x = (int) Math.round(rect.getPunto1().getX());
            int y = (int) Math.round(rect.getPunto1().getY());
            int ancho = (int) Math.round(rect.getBase());
            int alto = (int) Math.round(rect.getAlto());
            g2d.drawRect(x, y, ancho, alto);
        }

        // Comparar todos los elementos entre sí
        for (int a = 0; a < rectangulos.size(); a++) {
            Rectangulo rectA = (Rectangulo) rectangulos.get(a);
            for (int b = a + 1; b < rectangulos.size(); b++) {
                Rectangulo rectB = (Rectangulo) rectangulos.get(b);
                if (Verificador.isSuperposicion(rectA, rectB)) {
                    ListaIntersectados.add(new Rectangulo(
                        Verificador.getPunto1Interseccion(rectA, rectB),
                        Verificador.getPunto2Interseccion(rectA, rectB)
                    ));
                    System.out.println("Intersectan: " + rectA + " y " + rectB);
                    System.out.println("Area de interseccion: " + Rectangulo.area(new Rectangulo(
                        Verificador.getPunto1Interseccion(rectA, rectB),
                        Verificador.getPunto2Interseccion(rectA, rectB)
                    )));
                }
                System.out.println("Son disjuntos: " + rectA + " y " + rectB);
            }
        }

        g2d.setColor(Color.RED);
        for (int i = 0; i < ListaIntersectados.size(); i++) {
            Rectangulo rect = (Rectangulo) ListaIntersectados.get(i);
            int x = (int) Math.round(rect.getPunto1().getX());
            int y = (int) Math.round(rect.getPunto1().getY());
            int ancho = (int) Math.round(rect.getBase());
            int alto = (int) Math.round(rect.getAlto());
            g2d.fillRect(x, y, ancho, alto);
        }
    }
}