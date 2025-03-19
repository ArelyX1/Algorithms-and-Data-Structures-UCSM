package Ejercicio;

class OperaMatInteger implements Operable<Integer>{
    @Override
    public Integer suma(Integer a, Integer b) {
        return a + b;
    }
    @Override
    public Integer resta(Integer a, Integer b) {
        return a - b;
    }
    @Override
    public Integer multiplicacion(Integer a, Integer b) {
        return a * b;
    }
    @Override
    public Integer division(Integer a, Integer b) {
        return a / b;
    }
    @Override
    public Integer potencia(Integer a, Integer b) {
        return (int) Math.pow(a, b);
    }
    @Override
    public Integer raizCuadrada(Integer a) {
        return (int) Math.sqrt(a);
    }
    @Override
    public Integer raizCubica(Integer a) {
        return (int) Math.cbrt(a);
    }
}

class OperaMatDouble implements Operable<Double>{
    @Override
    public Double suma(Double a, Double b) {
        return a + b;
    }
    @Override
    public Double resta(Double a, Double b) {
        return a - b;
    }
    @Override
    public Double multiplicacion(Double a, Double b) {
        return a * b;
    }
    @Override
    public Double division(Double a, Double b) {
        return a / b;
    }
    @Override
    public Double potencia(Double a, Double b) {
        return Math.pow(a, b);
    }
    @Override
    public Double raizCuadrada(Double a) {
        return Math.sqrt(a);
    }
    @Override
    public Double raizCubica(Double a) {
        return Math.cbrt(a);
    }
}