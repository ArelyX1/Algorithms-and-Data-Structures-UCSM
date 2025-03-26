package Actividad;

public class TestGen {
    public static void main(String[] args) {
        Cajoneria<Golosina> cajoneriaGolosinas = new Cajoneria<>();
        cajoneriaGolosinas.addCaja(new Caja<>(new Golosina("gummy", 0.5)));
        cajoneriaGolosinas.addCaja(new Caja<>(new Golosina("lollipop", 0.2)));
        cajoneriaGolosinas.addCaja(new Caja<>(new Golosina("jellybean", 0.3)));
        cajoneriaGolosinas.addCaja(new Caja<>(new Golosina("caramel", 0.4)));
        cajoneriaGolosinas.addCaja(new Caja<>(new Golosina("chocolate", 0.6)));

        System.out.println(cajoneriaGolosinas);

        System.out.println("Existencia de 'gummy': " + cajoneriaGolosinas.search(new Golosina("gummy", 0.5)));
        System.out.println("Eliminando 'lollipop': " + cajoneriaGolosinas.delete(new Golosina("lollipop", 0.2)));
        System.out.println(cajoneriaGolosinas);

        Cajoneria<Chocolatina> cajoneriaChocolatinas = new Cajoneria<>();
        cajoneriaChocolatinas.addCaja(new Caja<>(new Chocolatina("milka")));
        cajoneriaChocolatinas.addCaja(new Caja<>(new Chocolatina("ferrero")));
        cajoneriaChocolatinas.addCaja(new Caja<>(new Chocolatina("hershey")));
        cajoneriaChocolatinas.addCaja(new Caja<>(new Chocolatina("nestle")));
        cajoneriaChocolatinas.addCaja(new Caja<>(new Chocolatina("cadbury")));

        System.out.println(cajoneriaChocolatinas);

        System.out.println("Existencia de 'milka': " + cajoneriaChocolatinas.search(new Chocolatina("milka")));
        System.out.println("Eliminando 'ferrero': " + cajoneriaChocolatinas.delete(new Chocolatina("ferrero")));
        System.out.println(cajoneriaChocolatinas);
    }
}