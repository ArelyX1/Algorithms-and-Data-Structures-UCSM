package Sesion4;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int depth = Integer.parseInt(JOptionPane.showInputDialog("Profundidad del árbol:"));
        JFrame frame = new JFrame("Árbol de Pitágoras");
        frame.add(new ArbolPitagoras(depth));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
