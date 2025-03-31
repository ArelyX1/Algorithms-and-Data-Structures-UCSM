package Ejercicio;

import java.util.Scanner;

public class Main {

    public static void Menu(){
        System.out.println("1. Suma");
        System.out.println("2. Resta");
        System.out.println("3. Multiplicacion");
        System.out.println("4. Division");
        System.out.println("5. Potencia");
        System.out.println("6. Raiz Cuadrada");
        System.out.println("7. Raiz Cubica");
        System.out.println("8. Salir");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OperaMatInteger operaInt = new OperaMatInteger();
        OperaMatDouble operaDouble = new OperaMatDouble();

        while(true){
            Menu();
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            if(opcion == 8) {
                System.out.println("Saliendo...");
                break;
            }

            System.out.print("Ingrese el primer número: ");
            double num1 = scanner.nextDouble();
            double num2 = 0;
            if(opcion != 6 && opcion != 7) {
                System.out.print("Ingrese el segundo número: ");
                num2 = scanner.nextDouble();
            }

            switch(opcion) {
                case 1:
                    System.out.println("Resultado: " + operaDouble.suma(num1, num2));
                    break;
                case 2:
                    System.out.println("Resultado: " + operaDouble.resta(num1, num2));
                    break;
                case 3:
                    System.out.println("Resultado: " + operaDouble.multiplicacion(num1, num2));
                    break;
                case 4:
                    System.out.println("Resultado: " + operaDouble.division(num1, num2));
                    break;
                case 5:
                    System.out.println("Resultado: " + operaDouble.potencia(num1, num2));
                    break;
                case 6:
                    System.out.println("Resultado: " + operaDouble.raizCuadrada(num1));
                    break;
                case 7:
                    System.out.println("Resultado: " + operaDouble.raizCubica(num1));
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        scanner.close();
    }
}
