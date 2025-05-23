package Actividad;

public class Principal {
    public static void main(String[] args) {
        Bolsa<Chocolatina> bolsaCho = new Bolsa<>(10);
        Chocolatina c = new Chocolatina("milka");
        Chocolatina c1 = new Chocolatina("milka");
        Chocolatina c2 = new Chocolatina("ferrero");
        bolsaCho.add(c);
        bolsaCho.add(c1);
        bolsaCho.add(c2);
        for (Chocolatina chocolatina : bolsaCho) {
            System.out.println(chocolatina.getMarca());
        }

        Bolsa<Golosina> bolsaGolo = new Bolsa<>(10);
        Golosina g = new Golosina("gummy", 0.5);
        Golosina g1 = new Golosina("lollipop", 0.2);
        Golosina g2 = new Golosina("jellybean", 0.3);
        bolsaGolo.add(g);
        bolsaGolo.add(g1);
        bolsaGolo.add(g2);
        for (Golosina golosina : bolsaGolo) {
            System.out.println(golosina);
        }

        Cajoneria<Golosina> cajoneriaGolosinas = new Cajoneria<>();
        cajoneriaGolosinas.addCaja(new Caja<>(new Golosina("gummy", 0.5), "Rojo"));
        cajoneriaGolosinas.addCaja(new Caja<>(new Golosina("lollipop", 0.2), "Amarillo"));
        cajoneriaGolosinas.addCaja(new Caja<>(new Golosina("jellybean", 0.3), "Verde"));
        cajoneriaGolosinas.addCaja(new Caja<>(new Golosina("caramel", 0.4), "Azul"));
        cajoneriaGolosinas.addCaja(new Caja<>(new Golosina("chocolate", 0.6), "Naranja"));

        System.out.println(cajoneriaGolosinas);
    }
}