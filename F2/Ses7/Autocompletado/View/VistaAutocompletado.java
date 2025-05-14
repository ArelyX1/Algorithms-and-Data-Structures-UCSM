// vista/VistaAutocompletado.java
package Autocompletado.View;

import javax.swing.*;
import java.awt.*;

public class VistaAutocompletado extends JFrame {
    private JTextField campoTexto;
    private JList<String> listaSugerencias;
    private DefaultListModel<String> modeloLista;
    private JButton btnVerAprendidas;

    public VistaAutocompletado() {
        configurarComponentes();
    }

    private void configurarComponentes() {
        setTitle("Autocompletado AVL Pro");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        campoTexto = new JTextField();
        modeloLista = new DefaultListModel<>();
        listaSugerencias = new JList<>(modeloLista);
        btnVerAprendidas = new JButton("Ver aprendidas");

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(campoTexto, BorderLayout.CENTER);
        panelSuperior.add(btnVerAprendidas, BorderLayout.EAST);

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(listaSugerencias), BorderLayout.CENTER);
    }

    public JTextField getCampoTexto() {
        return campoTexto;
    }

    public JButton getBtnVerAprendidas() {
        return btnVerAprendidas;
    }

    public void actualizarSugerencias(java.util.List<String> sugerencias) {
        modeloLista.clear();
        sugerencias.forEach(modeloLista::addElement);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
