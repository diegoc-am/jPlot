class NodoLE<E>{
	E dato;
	NodoLE<E> next;
	public NodoLE(E dato){
		this.dato = dato;
		this.next=null;
	}
	public NodoLE(E dato, NodoLE<E> sig){
		this.dato = dato;
		this.next = sig;
	}
	public E getDato() {
		return dato;
	}
	public void setDato(E dato) {
		this.dato = dato;
	}
	public NodoLE<E> getNext() {
		return next;
	}
	public void setNext(NodoLE<E> sig) {
		this.next = sig;
	}
	public String toString(){
		return this.dato.toString();
	}
}