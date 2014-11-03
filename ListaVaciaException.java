
public class ListaVaciaException extends RuntimeException {
	public ListaVaciaException(){
		super("No se puede borrar de una lista vacia");
	}
	public ListaVaciaException(String msg){
		super(msg);
	}
}
