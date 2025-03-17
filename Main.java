package Sesion1v2;

import java.util.Scanner;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Ingrese las coordenadas para los rectangulos y/o ingrese -1 para terminar");
        int i = 1;
        FigureContainer contenedor = new FigureContainer();

        while(!(1==0)){
            double x1 = 0.0, y1 = 0.0, x2 = 0.0, y2 =0.0;
            System.out.println("Ingrese las coordenadas para el rectangulo n°: " + i++);
            System.out.println("Ingrese la coordenada x1: ");
            x1 = scanner.nextDouble(); if (x1 == -1) break;
            System.out.println("Ingrese la coordenada y1: ");
            y1 = scanner.nextDouble(); if (y1 == -1) break;
            System.out.println("Ingrese la coordenada x2: ");
            x2 = scanner.nextDouble(); if (x2 == -1) break;
            System.out.println("Ingrese la coordenada y2: ");
            y2 = scanner.nextDouble(); if (y2 == -1) break;
            new Rectangulo(new Coordenada(Math.abs(x1), Math.abs(y1)), new Coordenada(Math.abs(x2), Math.abs(y2))).toString();
            contenedor.add(new Rectangulo(new Coordenada(x1, y1), new Coordenada(x2, y2)));
        }

        //Rectangulo r1 = new Rectangulo(new Coordenada(0, 0), new Coordenada(40, 30));
        //Rectangulo r2 = new Rectangulo(new Coordenada(60, 0), new Coordenada(100, 30));

        //System.out.println("Esta super puesto? " + Verificador.isSuperposicion(r1, r2));
        //Rectangulo r_interseccion = new Rectangulo(Verificador.getPunto1Interseccion(r1, r2), Verificador.getPunto2Interseccion(r1, r2));
        //System.out.println("Area de la interseccion: " + Rectangulo.area(r_interseccion));

        //Rectangulo r3 = new Rectangulo(new Coordenada(10, 50), new Coordenada(50, 90));
        //Rectangulo r4 = new Rectangulo(new Coordenada(30, 70), new Coordenada(70, 110));

        //System.out.println("Area de la interseccion: " + Rectangulo.area(r_interseccion));

        //Rectangulo r_interseccion2 = new Rectangulo(Verificador.getPunto1Interseccion(r3, r4), Verificador.getPunto2Interseccion(r3, r4));
        //System.out.println("Area de la interseccion: " + Rectangulo.area(r_interseccion2));

        //Rectangulo r5 = new Rectangulo(new Coordenada(120, 0), new Coordenada(160, 40));
        //Rectangulo r6 = new Rectangulo(new Coordenada(60, 30), new Coordenada(140, 80));

        //Rectangulo r_interseccion3 = new Rectangulo(Verificador.getPunto1Interseccion(r5, r6), Verificador.getPunto2Interseccion(r5, r6));

        JFrame f = new JFrame("Los rectángulos papus");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400, 400);
        DibujoRectangulo dibujo = new DibujoRectangulo(contenedor);

        f.add(dibujo);
        f.setVisible(true);
    }
}

/* 
https://www.naukri.com/code360/library/extending-interfaces
https://keepcoding.io/blog/como-pasar-de-double-a-int-en-java/
*/
