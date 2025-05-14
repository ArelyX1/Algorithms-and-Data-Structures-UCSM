package Autocompletado;

import Autocompletado.Model.ArbolAVL;
import Autocompletado.View.VistaAutocompletado;
import Autocompletado.Controller.ControladorAutocompletado;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArbolAVL modelo = new ArbolAVL();
            VistaAutocompletado vista = new VistaAutocompletado();
            new ControladorAutocompletado(modelo, vista);
            vista.setVisible(true);
        });
    }
}
