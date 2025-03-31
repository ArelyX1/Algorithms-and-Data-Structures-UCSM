package Actividad;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public MainFrame() {
        setTitle("Chocolatinas y Golosinas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnNames = {"Tipo", "Nombre/Marca", "Peso"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));

        JLabel tipoLabel = new JLabel("Tipo:");
        JComboBox<String> tipoComboBox = new JComboBox<>(new String[]{"Chocolatina", "Golosina"});
        JLabel nombreLabel = new JLabel("Nombre/Marca:");
        JTextField nombreField = new JTextField();
        JLabel pesoLabel = new JLabel("Peso:");
        JTextField pesoField = new JTextField();
        JButton addButton = new JButton("Agregar");

        inputPanel.add(tipoLabel);
        inputPanel.add(tipoComboBox);
        inputPanel.add(nombreLabel);
        inputPanel.add(nombreField);
        inputPanel.add(pesoLabel);
        inputPanel.add(pesoField);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(addButton, BorderLayout.SOUTH);

        add(panel);


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = (String) tipoComboBox.getSelectedItem();
                String nombre = nombreField.getText();
                String peso = pesoField.getText();

                if (tipo.equals("Chocolatina")) {
                    tableModel.addRow(new Object[]{tipo, nombre, ""});
                } else {
                    tableModel.addRow(new Object[]{tipo, nombre, peso});
                }

                nombreField.setText("");
                pesoField.setText("");
            }
        });
    }
}