package Sesion1v2;

import javax.swing.*;
import java.awt.*;

interface Figura {
    double getBase();
    double getAlto();
}

public class Rectangulo implements Figura {
    private Coordenada punto1, punto2;
    public static int numRect = 0;
    public Rectangulo(Coordenada p1, Coordenada p2) {
        double minX = Math.min(p1.getX(), p2.getX());
        double minY = Math.min(p1.getY(), p2.getY());
        double maxX = Math.max(p1.getX(), p2.getX());
        double maxY = Math.max(p1.getY(), p2.getY());
        numRect ++;
        this.punto1 = new Coordenada(minX, minY);
        this.punto2 = new Coordenada(maxX, maxY);
    }
    

    public double getBase() {
        return Math.abs(punto2.getX() - punto1.getX());
    }

    public double getAlto() {
        return Math.abs(punto2.getY() - punto1.getY());
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

    public static double calcularDistancia(Rectangulo rect) {
        Coordenada c1 = rect.getPunto1();
        Coordenada c2 = rect.getPunto2();
        return Math.sqrt(Math.pow(c2.getX() - c1.getX(), 2) + Math.pow(c2.getY() - c1.getY(), 2));
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
        this.ListaIntersectados = new FigureContainer();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (int a = 0; a < rectangulos.size(); a++) {
            Rectangulo rect = (Rectangulo) rectangulos.get(a);
            g2d.drawRect(
                (int) Math.round(rect.getPunto1().getX()), 
                (int) Math.round(rect.getPunto1().getY()), 
                (int) Math.round(rect.getBase()), 
                (int) Math.round(rect.getAlto())
            );
        }

        // Se comparan, VIVA EL OPEN SOURCE
        for (int a = 0; a < rectangulos.size(); a++) {
            Rectangulo rectA = (Rectangulo) rectangulos.get(a);
            for (int b = a + 1; b < rectangulos.size(); b++) {
                Rectangulo rectB = (Rectangulo) rectangulos.get(b);
                if (Verificador.isSuperposicion(rectA, rectB)) {
                    //agregando el rectangulo interseccion a la lista y y
                    ListaIntersectados.add(new Rectangulo(
                        Verificador.getPunto1Interseccion(rectA, rectB),
                        Verificador.getPunto2Interseccion(rectA, rectB)
                    )); Rectangulo.numRect--;
                    System.out.println("Intersectan: " + rectA + " y " + rectB);
                    Rectangulo interseccion = new Rectangulo(
                        Verificador.getPunto1Interseccion(rectA, rectB),
                        Verificador.getPunto2Interseccion(rectA, rectB)
                    );
                    System.out.println((Verificador.isJunto(rectA, rectB)) ? "Estan juntos" : "Area de interseccion: " + Rectangulo.area(interseccion));
                } else {
                    System.out.println("Son disjuntos: " + rectA + " y " + rectB);
                }
            }
        }

        g2d.setColor(Color.MAGENTA);
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