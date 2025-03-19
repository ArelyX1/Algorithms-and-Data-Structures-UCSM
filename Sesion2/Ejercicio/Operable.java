package Ejercicio;

public interface Operable <N extends Number>{
    public N suma(N a, N b);
    public N resta(N a, N b);
    public N multiplicacion(N a, N b);
    public N division(N a, N b);
    public N potencia(N a, N b);
    public N raizCuadrada(N a);
    public N raizCubica(N a);
}
