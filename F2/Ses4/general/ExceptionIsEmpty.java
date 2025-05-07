package general;

public class ExceptionIsEmpty extends Exception{
    public ExceptionIsEmpty(){
        super();
    }
    public ExceptionIsEmpty(String smg){
        super(smg);
    }
}