package model;

public class Nodo {
    private Marca marca;
    public Nodo siguiente;

    public Nodo() {
    }

    public Nodo(Marca marca, Nodo siguiente) {
        this.marca = marca;
        this.siguiente = siguiente;
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
