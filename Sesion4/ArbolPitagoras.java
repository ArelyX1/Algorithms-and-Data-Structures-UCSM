package Sesion4;

import javax.swing.*;
import java.awt.*;

public class ArbolPitagoras extends JPanel {

    private int depth;

    public ArbolPitagoras(int depth) {
        this.depth = depth;
        setPreferredSize(new Dimension(800, 800));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.GREEN);

        // Iniciar desde la parte inferior central y ángulo corregido (90° hacia arriba)
        drawTree(g2d, 400, 700, 100, 90, depth);
    }

    private void drawTree(Graphics2D g2d, double x1, double y1, double length, double angle, int level) {
        if (level <= 0) return;
    
        // Calcular punto final de la línea actual
        double x2 = x1 + Math.cos(Math.toRadians(angle)) * length;
        double y2 = y1 - Math.sin(Math.toRadians(angle)) * length;
    
        // Dibujar línea principal
        g2d.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
    
        // Calcular nuevas ramas con ángulo recto
        double newLength = length * Math.sqrt(2)/2; // Factor 1/√2
        drawTree(g2d, x2, y2, newLength, angle + 45, level - 1); // Rama derecha
        drawTree(g2d, x2, y2, newLength, angle - 45, level - 1); // Rama izquierda
    }

}
