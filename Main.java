package Sesion1v2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese las coordenadas para los rectangulos y/o ingrese -1 para terminar");
        int i = 1;
        FigureContainer contenedor = new FigureContainer();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Rectángulo #");
        tableModel.addColumn("Coordenadas");
        tableModel.addColumn("Distancia");
        tableModel.addColumn("Área");

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        while (true) {
            double x1 = 0.0, y1 = 0.0, x2 = 0.0, y2 = 0.0;
            System.out.println("Ingrese las coordenadas para el rectángulo n°: " + i);
            System.out.println("Ingrese la coordenada x1: ");
            x1 = scanner.nextDouble();
            if (x1 == -1) break;
            System.out.println("Ingrese la coordenada y1: ");
            y1 = scanner.nextDouble();
            if (y1 == -1) break;
            System.out.println("Ingrese la coordenada x2: ");
            x2 = scanner.nextDouble();
            if (x2 == -1) break;
            System.out.println("Ingrese la coordenada y2: ");
            y2 = scanner.nextDouble();
            if (y2 == -1) break;

            Rectangulo rectangulo = new Rectangulo(new Coordenada(x1, y1), new Coordenada(x2, y2));
            contenedor.add(rectangulo, Rectangulo.area(rectangulo));
            tableModel.addRow(new Object[]{i, rectangulo.toString(), Rectangulo.calcularDistancia(rectangulo), contenedor.getArea(i - 1)});
            i++;
        }
        System.out.println("Numero de rectangulos ingresados: " + Rectangulo.numRect);
        JFrame f = new JFrame("Los rectángulos papus");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1400, 900);

        JPanel panelDibujo = new JPanel(new BorderLayout());
        DibujoRectangulo dibujo = new DibujoRectangulo(contenedor);
        panelDibujo.add(dibujo, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, panelDibujo);
        splitPane.setDividerLocation(400);

        f.add(splitPane);
        f.setVisible(true);
    }
}

/*Datos de prueba:
 * 0 0 40 30
 * 60 0 100 30
 * 10 50 50 90
 * 30 70 70 110
 * 120 0 160 40
 * 60 30 140 80
 */