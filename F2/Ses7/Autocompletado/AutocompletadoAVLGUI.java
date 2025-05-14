package Autocompletado;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

public class AutocompletadoAVLGUI extends JFrame {
    private JTextField campoTexto;
    private JList<String> listaSugerencias;
    private DefaultListModel<String> modeloLista;
    private JButton botonVerAprendidas;
    private JButton botonBorrarAprendidas;
    private NodoAVL raiz;
    private Set<String> aprendidas = new HashSet<>();
    private Set<String> porDefecto = new HashSet<>();
    private static final String ARCHIVO_APRENDIZAJE = "aprendizaje_avl.dat";
    private static final String DICCIONARIO_WEB = "https://raw.githubusercontent.com/dwyl/english-words/master/words.txt";

    class NodoAVL {
        String palabra;
        int altura;
        NodoAVL izquierda, derecha;

        public NodoAVL(String palabra) {
            this.palabra = palabra.toLowerCase();
            this.altura = 1;
        }
    }

    public AutocompletadoAVLGUI() {
        super("Autocompletado AVL Pro");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        initComponentes();
        cargarDatosIniciales();
    }

    private void initComponentes() {
        campoTexto = new JTextField();
        modeloLista = new DefaultListModel<>();
        listaSugerencias = new JList<>(modeloLista);
        botonVerAprendidas = new JButton("Ver aprendidas");
        botonBorrarAprendidas = new JButton("Borrar aprendidas");

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(campoTexto, BorderLayout.CENTER);
        panelSuperior.add(botonVerAprendidas, BorderLayout.EAST);
        panelSuperior.add(botonBorrarAprendidas, BorderLayout.WEST);

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(listaSugerencias), BorderLayout.CENTER);

        // Actualizar sugerencias al escribir
        campoTexto.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { actualizarSugerencias(); }
            public void removeUpdate(DocumentEvent e) { actualizarSugerencias(); }
            public void changedUpdate(DocumentEvent e) {}
        });

        // Autocompletar al hacer clic
        listaSugerencias.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String seleccion = listaSugerencias.getSelectedValue();
                if (seleccion != null) campoTexto.setText(seleccion);
            }
        });

        // Aprender palabra al presionar Enter
        campoTexto.addActionListener(e -> {
            String texto = campoTexto.getText().trim().toLowerCase();
            if (!texto.isEmpty() && !porDefecto.contains(texto) && !aprendidas.contains(texto)) {
                insertar(texto);
                aprendidas.add(texto);
                guardarAprendizaje(); // Guarda inmediatamente
                actualizarSugerencias();
                JOptionPane.showMessageDialog(this,
                    "¡Palabra aprendida y guardada: " + texto + "!",
                    "Aprendizaje",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });



        // Mostrar palabras aprendidas al presionar el botón
        botonVerAprendidas.addActionListener(e -> {
            if (aprendidas.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay palabras aprendidas aún.");
            } else {
                StringBuilder sb = new StringBuilder("Palabras aprendidas:\n");
                aprendidas.stream().sorted().forEach(p -> sb.append(p).append("\n"));
                JOptionPane.showMessageDialog(this, sb.toString());
            }
        });

    }

    private void cargarDatosIniciales() {
        new Thread(() -> {
            // 1. Cargar desde sistema
            cargarDesdeSistema();

            // 2. Cargar desde web
            cargarDesdeWeb();

            // 3. Cargar aprendizaje previo
            cargarAprendizajePrevisto();
        }).start();
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

    @SuppressWarnings("deprecation")
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
    private void cargarAprendizajePrevisto() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_APRENDIZAJE))) {
            aprendidas = (Set<String>) ois.readObject();
            aprendidas.forEach(this::insertar);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se encontró aprendizaje previo");
        }
    }

    private void guardarAprendizaje() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_APRENDIZAJE))) {
            oos.writeObject(aprendidas);
        } catch (IOException e) {
            System.err.println("Error guardando aprendizaje");
        }
    }

    // ========== OPERACIONES AVL ==========
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

    private void insertar(String palabra) {
        raiz = insertarRec(raiz, palabra.toLowerCase());
    }

    private NodoAVL insertarRec(NodoAVL nodo, String palabra) {
        if (nodo == null) return new NodoAVL(palabra);

        int cmp = palabra.compareTo(nodo.palabra);
        if (cmp < 0) nodo.izquierda = insertarRec(nodo.izquierda, palabra);
        else if (cmp > 0) nodo.derecha = insertarRec(nodo.derecha, palabra);
        else return nodo;

        nodo.altura = 1 + Math.max(altura(nodo.izquierda), altura(nodo.derecha));

        int balance = balanceFactor(nodo);

        // Balancear según los 4 casos
        if (balance < -1 && palabra.compareTo(nodo.izquierda.palabra) < 0)
            return rotarDerecha(nodo);
        if (balance > 1 && palabra.compareTo(nodo.derecha.palabra) > 0)
            return rotarIzquierda(nodo);
        if (balance < -1 && palabra.compareTo(nodo.izquierda.palabra) > 0) {
            nodo.izquierda = rotarIzquierda(nodo.izquierda);
            return rotarDerecha(nodo);
        }
        if (balance > 1 && palabra.compareTo(nodo.derecha.palabra) < 0) {
            nodo.derecha = rotarDerecha(nodo.derecha);
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    private void actualizarSugerencias() {
    SwingUtilities.invokeLater(() -> {
        String texto = campoTexto.getText().trim().toLowerCase();
        modeloLista.clear();

        if (!texto.isEmpty()) {
            List<String> sugerencias = new ArrayList<>();

            // 1. Palabras aprendidas primero
            aprendidas.stream()
                .filter(p -> p.startsWith(texto))
                .sorted()
                .forEach(sugerencias::add);

            // 2. Luego las demás (por defecto, que no estén en aprendidas)
            List<String> otras = new ArrayList<>();
            autocompletarRec(raiz, texto, otras);
            otras.removeIf(aprendidas::contains); // Elimina duplicados
            otras.stream()
                .sorted()
                .forEach(sugerencias::add);

            sugerencias.stream()
                .limit(10)
                .forEach(modeloLista::addElement);
        }
    });
}


    private void autocompletarRec(NodoAVL nodo, String prefijo, List<String> resultados) {
        if (nodo != null) {
            if (nodo.palabra.startsWith(prefijo)) resultados.add(nodo.palabra);
            autocompletarRec(nodo.izquierda, prefijo, resultados);
            autocompletarRec(nodo.derecha, prefijo, resultados);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AutocompletadoAVLGUI gui = new AutocompletadoAVLGUI();
            gui.setVisible(true);

            // Guardar aprendizaje al cerrar
            gui.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    gui.guardarAprendizaje();
                }
            });
        });
    }
}
