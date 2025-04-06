package actividades;

public interface Actividad5 {
    public static void main(String[] args) {
        System.out.println("Potencia de 2^3: " + potencia(2, 3));
        System.out.println("Potencia de 2^4: " + potencia(2, 4));
    }

        public static double potencia(double x, int y){
            double t;                               // Cost 1
            if(y==0) return 1.;                     // Cost 1
            if(y%2==1) return x*potencia(x, y-1);   //Cost T(y - 1)
            else {
                t = potencia(x, y/2);               // Cost T(y/2)
                return t*t;                         // Cost 1
            }
        }
}
