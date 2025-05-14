package Autocompletado.Controller;

import Autocompletado.Model.ArbolAVL;
import Autocompletado.View.VistaAutocompletado;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;

public class ControladorAutocompletado {
    private ArbolAVL modelo;
    private VistaAutocompletado vista;

    public ControladorAutocompletado(ArbolAVL modelo, VistaAutocompletado vista) {
        this.modelo = modelo;
        this.vista = vista;
        configurarEventos();
        modelo.cargarDatosIniciales();
    }

    private void configurarEventos() {
        // Eventos de texto
        vista.getCampoTexto().getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { actualizarSugerencias(); }
            public void removeUpdate(DocumentEvent e) { actualizarSugerencias(); }
            public void changedUpdate(DocumentEvent e) {}
        });

        // Evento Enter para aprender palabras
        vista.getCampoTexto().addActionListener(e -> aprenderPalabra());

        // Botón ver aprendidas
        vista.getBtnVerAprendidas().addActionListener(e -> mostrarAprendidas());

        // Guardar al cerrar
        vista.addWindowListener(new WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                modelo.guardarAprendizaje();
            }
        });
    }

    private void actualizarSugerencias() { //O(1) amortizado
        String texto = vista.getCampoTexto().getText().trim();
        if (!texto.isEmpty()) {
            java.util.List<String> sugerencias = modelo.autocompletar(texto);
            vista.actualizarSugerencias(sugerencias);
        }
    }

    private void aprenderPalabra() {
        String texto = vista.getCampoTexto().getText().trim().toLowerCase();
        if (!texto.isEmpty() && !modelo.existeEnPorDefecto(texto) && !modelo.aprendidas.contains(texto)) {
            modelo.insertar(texto);
            modelo.agregarAprendida(texto);
            modelo.guardarAprendizaje();
            vista.mostrarMensaje("Palabra aprendida: " + texto);
            actualizarSugerencias();
        }
    }

    private void mostrarAprendidas() {
        if (modelo.aprendidas.isEmpty()) {
            vista.mostrarMensaje("No hay palabras aprendidas aún.");
        } else {
            StringBuilder sb = new StringBuilder("Palabras aprendidas:\n");
            modelo.aprendidas.stream().sorted().forEach(p -> sb.append(p).append("\n"));
            vista.mostrarMensaje(sb.toString());
        }
    }
}
