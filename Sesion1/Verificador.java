package Sesion1;

public class Verificador {
    public static Boolean isSuperposicion(Rectangulo r1, Rectangulo r2) {
        Coordenada r1_a = r1.getPunto1();
        Coordenada r1_b = r1.getPunto2();
        Coordenada r2_a = r2.getPunto1();
        Coordenada r2_b = r2.getPunto2();
        Boolean condicion1 = (r1_b.getX() >= r2_a.getX() && r1_a.getX() <= r2_b.getX());
        Boolean condicion2 = (r1_b.getY() >= r2_a.getY() && r1_a.getY() <= r2_b.getY());
        return condicion1 && condicion2;
    }

    public static Boolean isJunto(Rectangulo r1, Rectangulo r2) {
        if (!isSuperposicion(r1, r2)) return false;
        if (Rectangulo.area(new Rectangulo(getPunto1Interseccion(r1, r2), getPunto2Interseccion(r1, r2))) != 0) return false;
        return true;
    }

    public static Rectangulo rectanguloSobre(Rectangulo r1, Rectangulo r2) {
        if (!isSuperposicion(r1, r2)) return null;
        return new Rectangulo(getPunto1Interseccion(r1, r2), getPunto2Interseccion(r1, r2));
    }

    public static Coordenada getPunto1Interseccion(Rectangulo r1, Rectangulo r2) {
        Coordenada r1_a = r1.getPunto1();
        Coordenada r2_a = r2.getPunto1();
        return new Coordenada(Math.max(r1_a.getX(), r2_a.getX()), Math.max(r1_a.getY(), r2_a.getY()));
    }

    public static Coordenada getPunto2Interseccion(Rectangulo r1, Rectangulo r2) {
        Coordenada r1_b = r1.getPunto2();
        Coordenada r2_b = r2.getPunto2();
        return new Coordenada(Math.min(r1_b.getX(), r2_b.getX()), Math.min(r1_b.getY(), r2_b.getY()));
    }
}
