package model;

public class Nodo {
    private Marca marca;
    public Nodo siguiente;
    public Nodo anterior;
    private Contiene contiene;

    public Nodo() {
    }

    public Nodo(Marca marca, Nodo siguiente) {
        this.marca = marca;
        this.siguiente = siguiente;
    }

    public Nodo(Nodo siguiente, Nodo anterior, Contiene contiene) {
        this.siguiente = siguiente;
        this.anterior = anterior;
        this.contiene = contiene;
    }

    public Nodo getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }

    public Contiene getContiene() {
        return contiene;
    }

    public void setContiene(Contiene contiene) {
        this.contiene = contiene;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    
}
