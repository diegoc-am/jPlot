
public class ListaEnlazada<E> {
	private NodoLE<E> inicio, fin;
	private int size;
	
	public ListaEnlazada(){
		this.size=0;
		this.inicio=this.fin=null;
	}
	public ListaEnlazada(E [] a){
		this();
		for (int i = 0; i < a.length;i++){
			this.insertarFin(a[i]);
		}
	}
	public void insertarInicio(E valor){
		NodoLE<E> nvo = new NodoLE<E>(valor,this.inicio);
		this.inicio = nvo;
		if(this.size++ == 0){
			this.fin = nvo;
		}
	}
	public E borrarInicio(){
		try{
			E tmp = this.inicio();
			this.inicio = this.inicio.getNext();
			if (--this.size == 0){
				this.fin = null;
			}
			return tmp;
		}
		catch(NullPointerException e){
			throw new ListaVaciaException();
		}
	}
	public void insertarFin(E valor){
		NodoLE<E> nvo = new NodoLE<E>(valor);
		if(this.size != 0){
			this.fin.setNext(nvo);
			this.fin = nvo;
		}else{
			this.fin = this.inicio = nvo;
		}
		size++;
	}
        
        
	public E borrarFin() throws ListaVaciaException{
		try{
			if(this.size > 1){
				E tmp = this.fin();
				NodoLE<E> aux = this.inicio;
				for(int i = 1; i<this.size-1;i++){
					aux = aux.getNext();
				}
				this.size--;
				aux.setNext(null);
				this.fin = aux;
				return tmp;
			}else{ 
				return borrarInicio();
			}
		}catch(NullPointerException e){
			throw new ListaVaciaException();
		}
	}
	public E borrarEn(int pos){
		try{
			if (pos==0){
				return this.borrarInicio();
			}
			else if (pos==this.size()-1){
				return this.borrarFin();
			}
			else {
				E val = this.inicio.getDato();
				NodoLE<E> current = this.inicio;
				for (int i = 0 ; i<pos; i++){
					current = current.getNext();
				}
				val = current.getDato();
				current.setDato(current.getNext().getDato());
				current.setNext(current.getNext());
				this.size--;
				return val;	
			}
		}
		catch(NullPointerException e){
			throw new ListaVaciaException();
		}
	}
	public void insertarEn(int pos, E valor){
		
		if(pos > this.size || pos < 0){
		//	throw new IndexOutOfBoundsException("No se puede insertar en la posicion "+ pos + " en una lista de tama;o " + this.size);
		}else if(pos == 0){
			this.insertarInicio(valor);
		}else if(pos == this.size){
			this.insertarFin(valor);
		}else{
			this.size++;
			NodoLE<E> aux = this.inicio;
			for(int i =1; i< pos;i++){
				aux= aux.getNext();
			}
			aux.setNext(new NodoLE<E>(valor,aux.getNext()));
		}
	}
	public E getEn(int pos){
		if(pos>this.size-1 || pos<0){
			throw new IndexOutOfBoundsException("No se puede leer en la posicion "+ pos + " en una lista de tama;o " + this.size);
		}else{
			NodoLE<E> current = this.inicio;
			for(int i = 0; i<pos;i++){
				current = current.getNext();
			}
			return current.getDato();
		}
	}
        
        public NodoLE getNodo(int pos){
            if(pos>this.size-1 || pos<0){
			throw new IndexOutOfBoundsException("No se puede leer en la posicion "+ pos + " en una lista de tama;o " + this.size);
		}else{
			NodoLE<E> current = this.inicio;
			for(int i = 0; i<pos;i++){
				current = current.getNext();
			}
			return current;
		}
        }
	public int size(){
		return this.size;
	}
	public boolean isEmpty(){
		return this.inicio==null;
	}
	public E inicio(){
		try{
			return this.inicio.getDato();
		}
		catch(NullPointerException e){
			throw new ListaVaciaException("No se puede obtener el primer elemento de una lista vacia");
		}
	}
	public NodoLE getInicio(){
		return this.inicio;
	}
	public NodoLE getFin(){
		return this.fin;
	}
	public E fin(){
		try{
			return this.fin.getDato();
		}
		catch(NullPointerException e){
			throw new ListaVaciaException("No se puede obtener el ultimo elemento de una lista vacia");
		}
	}
	public String toStrign(){
		NodoLE<E> temp = this.inicio;
		String salida = "";
		while(temp!=this.fin){
			salida += temp+", ";
			temp = temp.getNext();
		}
		salida += temp;
		return salida;
	}
}