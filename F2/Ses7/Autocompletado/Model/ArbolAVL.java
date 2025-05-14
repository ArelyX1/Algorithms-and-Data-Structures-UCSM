package Autocompletado.Model;

import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

public class ArbolAVL {
    protected static class NodoAVL {
        String palabra;
        int altura;
        NodoAVL izquierda, derecha;

        public NodoAVL(String palabra) {
            this.palabra = palabra.toLowerCase();
            this.altura = 1;
        }
    }

    protected NodoAVL raiz;
    public Set<String> aprendidas = new HashSet<>();
    protected Set<String> porDefecto = new HashSet<>();
    private static final String ARCHIVO_APRENDIZAJE = "aprendizaje_avl.dat";
    private static final String DICCIONARIO_WEB = "https://raw.githubusercontent.com/dwyl/english-words/master/words.txt";

    public void cargarDatosIniciales() {
        cargarDesdeSistema();
        //cargarDesdeWeb();
        cargarAprendizajePrevisto();
    }

    private void cargarDesdeSistema() {
        Path rutaSistema = Paths.get("/usr/share/dict/words");
        if (Files.exists(rutaSistema)) {
            try (Stream<String> lineas = Files.lines(rutaSistema)) {
                lineas.forEach(linea -> {
                    String palabra = linea.trim().toLowerCase();
                    if (!palabra.isEmpty()) {
                        insertar(palabra);
                        porDefecto.add(palabra);
                    }
                });
            } catch (IOException e) {
                System.err.println("Error cargando diccionario del sistema");
            }
        }
    }

    private void cargarDesdeWeb() {
        try {
            URL url = new URL(DICCIONARIO_WEB);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String palabra;
            while ((palabra = reader.readLine()) != null) {
                palabra = palabra.trim().toLowerCase();
                if (!palabra.isEmpty()) {
                    insertar(palabra);
                    porDefecto.add(palabra);
                }
            }
        } catch (IOException e) {
            System.err.println("Error cargando diccionario web");
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarAprendizajePrevisto() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_APRENDIZAJE))) {
            aprendidas = (Set<String>) ois.readObject();
            aprendidas.forEach(this::insertar);
            /*
                * Es quivalente a esto:
                for (String palabra : aprendidas) {
                    this.insertar(palabra);
                }
             */
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se encontró aprendizaje previo");
        }
    }

    public void guardarAprendizaje() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_APRENDIZAJE))) {
            oos.writeObject(aprendidas);
        } catch (IOException e) {
            System.err.println("Error guardando aprendizaje");
        }
    }

    private int altura(NodoAVL nodo) {
        return nodo == null ? 0 : nodo.altura;
    }

    private int balanceFactor(NodoAVL nodo) {
        return altura(nodo.derecha) - altura(nodo.izquierda);
    }

    private NodoAVL rotarDerecha(NodoAVL y) {
        NodoAVL x = y.izquierda;
        NodoAVL T2 = x.derecha;

        x.derecha = y;
        y.izquierda = T2;

        y.altura = Math.max(altura(y.izquierda), altura(y.derecha)) + 1;
        x.altura = Math.max(altura(x.izquierda), altura(x.derecha)) + 1;

        return x;
    }

    private NodoAVL rotarIzquierda(NodoAVL x) {
        NodoAVL y = x.derecha;
        NodoAVL T2 = y.izquierda;

        y.izquierda = x;
        x.derecha = T2;

        x.altura = Math.max(altura(x.izquierda), altura(x.derecha)) + 1;
        y.altura = Math.max(altura(y.izquierda), altura(y.derecha)) + 1;

        return y;
    }

    public void insertar(String palabra) {
        raiz = insertarRec(raiz, palabra.toLowerCase());
        //System.out.println("Palabra insertada: " + palabra);
    }

    private NodoAVL insertarRec(NodoAVL nodo, String palabra) {
        if (nodo == null) return new NodoAVL(palabra);

        int cmp = palabra.compareTo(nodo.palabra);
        if (cmp < 0) nodo.izquierda = insertarRec(nodo.izquierda, palabra);
        else if (cmp > 0) nodo.derecha = insertarRec(nodo.derecha, palabra);
        else return nodo;

        nodo.altura = 1 + Math.max(altura(nodo.izquierda), altura(nodo.derecha));

        int balance = balanceFactor(nodo);

        if (balance < -1 && palabra.compareTo(nodo.izquierda.palabra) < 0)
        /*
         * hay dos posibles casos:
         * 1. caso izquierdo izquierdo (rotación simple a la derecha)
         * 2. caso izquierdo derecho (rotación doble: izquierda-derecha)

            !Si palabra.compareTo(nodo.izquierda.palabra) < 0:

                La palabra va antes (alfabéticamente) que la del hijo izquierdo.

                Por lo tanto, se insertó en el subárbol izquierdo del hijo izquierdo (caso LL).

            !Si palabra.compareTo(nodo.izquierda.palabra) > 0:

                La palabra va después que la del hijo izquierdo.

                Por lo tanto, se insertó en el subárbol derecho del hijo izquierdo (caso LR).
                        "c"
                       /
                     "b"
                    /
                   "a"

         */
            return rotarDerecha(nodo);
        if (balance > 1 && palabra.compareTo(nodo.derecha.palabra) > 0)
            return rotarIzquierda(nodo);
        if (balance < -1 && palabra.compareTo(nodo.izquierda.palabra) > 0) {
            //caso LR
            /*
                "c"
                /
               "b"
                 \
                 "bb"

             */
            nodo.izquierda = rotarIzquierda(nodo.izquierda);
            return rotarDerecha(nodo);
        }
        if (balance > 1 && palabra.compareTo(nodo.derecha.palabra) < 0) {
            nodo.derecha = rotarDerecha(nodo.derecha);
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    public List<String> autocompletar(String prefijo) {
        List<String> sugerencias = new ArrayList<>();
        autocompletarRec(raiz, prefijo.toLowerCase(), sugerencias);
        
        // Priorizar palabras aprendidas
        List<String> resultados = new ArrayList<>();
        aprendidas.stream()
            .filter(p -> p.startsWith(prefijo))
            .forEach(resultados::add);
            /*
             * Toma el conjunto aprendidas (palabras que el usuario enseñó al sistema).
             * Usa un stream para filtrar solo las que empiezan con el prefijo que el usuario está escribiendo.
             * Las agrega a la lista resultados.
             * Toma el conjunto aprendidas (palabras que el usuario enseñó al sistema).
             * Usa un stream para filtrar solo las que empiezan con el prefijo que el usuario está escribiendo.
             * Las agrega a la lista resultados.
             */
        
        sugerencias.stream()
            .filter(p -> !aprendidas.contains(p))
            .forEach(resultados::add);
        /*
         * Se filtran para no agregar palabras que ya están en aprendidas (evita duplicados).
         * Se agregan al final de la lista resultados.
         */
        return resultados.stream()
            .distinct() //  Si por alguna razón una palabra aparece dos veces (por ejemplo, si estaba en ambas listas), solo se queda una.
            .limit(10) // *Solo se devuelven las primeras 10 sugerencias).
            .toList();
    }

    private void autocompletarRec(NodoAVL nodo, String prefijo, List<String> resultados) {
        /*if (nodo != null) {
            if (nodo.palabra.startsWith(prefijo)) resultados.add(nodo.palabra);
            autocompletarRec(nodo.izquierda, prefijo, resultados);
            autocompletarRec(nodo.derecha, prefijo, resultados);
        }*/
        if (nodo == null) return;

        int cmp = prefijo.compareTo(nodo.palabra);
        // Si el prefijo es menor o igual, buscar en el subárbol izquierdo
        if (cmp <= 0) autocompletarRec(nodo.izquierda, prefijo, resultados);
        if (cmp <= 0 || nodo.palabra.startsWith(prefijo)) resultados.add(nodo.palabra);
        if (cmp >= 0) autocompletarRec(nodo.derecha, prefijo, resultados);
    }

    public boolean existeEnPorDefecto(String palabra) {
        return porDefecto.contains(palabra.toLowerCase());
    }

    public void agregarAprendida(String palabra) {
        aprendidas.add(palabra.toLowerCase());
    }
}
