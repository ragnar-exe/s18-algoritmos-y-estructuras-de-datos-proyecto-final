package model;

public class Talla {
    private int idTalla;
    private byte numero;
    private String descripcion;

    public Talla() {
    }

    public Talla(int idTalla) {
        this.idTalla = idTalla;
    }

    public Talla(byte numero) {
        this.numero = numero;
    }

    public Talla(int idTalla, byte numero, String descripcion) {
        this.idTalla = idTalla;
        this.numero = numero;
        this.descripcion = descripcion;
    }

    public int getIdTalla() {
        return idTalla;
    }

    public void setIdTalla(int idTalla) {
        this.idTalla = idTalla;
    }

    public byte getNumero() {
        return numero;
    }

    public void setNumero(byte numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}