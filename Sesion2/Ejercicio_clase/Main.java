package Ejercicio_clase;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Bolsa<?>> bolsas = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String op;

        for (int i = 0; i < 3; i++) {
            System.out.println("Bolsa de enteros, floats o de strings? i/f/s");
            Bolsa<?> bolsa;
            op = sc.nextLine();

            if (op.equals("i")) {
                bolsa = new BolsaNum<Integer>();
                System.out.println("Ingrese números enteros (escriba 'fin' para terminar):");
                while (true) {
                    String entrada = sc.nextLine();
                    if (entrada.equalsIgnoreCase("fin")) {
                        break;
                    }
                    try {
                        ((BolsaNum<Integer>) bolsa).add(Integer.parseInt(entrada));
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida");
                    }
                }
            } else if (op.equals("f")) {
                bolsa = new BolsaNum<Float>();
                System.out.println("Ingrese números flotantes (escriba 'fin' para terminar):");
                while (true) {
                    String entrada = sc.nextLine();
                    if (entrada.equalsIgnoreCase("fin")) {
                        break;
                    }
                    try {
                        ((BolsaNum<Float>) bolsa).add(Float.parseFloat(entrada));
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida");
                    }
                }
            } else if (op.equals("s")) {
                bolsa = new Bolsa<String>();
                System.out.println("Ingrese una cadena (escriba 'fin' para terminar):");
                while (true) {
                    String cadena = sc.nextLine();
                    if (cadena.equalsIgnoreCase("fin")) {
                        break;
                    }
                    ((Bolsa<String>) bolsa).add(cadena);
                }
            } else {
                System.out.println("Opción no válida");
                sc.close();
                return;
            }

            System.out.println("Antes de ordenar:");
            for (Object obj : bolsa) {
                System.out.println(obj);
            }

            if (bolsa instanceof BolsaNum) {
                ((BolsaNum<?>) bolsa).sort();
            }

            System.out.println("Después de ordenar:");
            for (Object obj : bolsa) {
                System.out.println(obj);
            }

            bolsas.add(bolsa);
        }

        System.out.println("Contenido de todas las bolsas:");
        for (Bolsa<?> bolsa : bolsas) {
            for (Object obj : bolsa) {
                System.out.println(obj);
            }
            System.out.println("---");
        }

        sc.close();
    }
}
