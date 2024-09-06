package model;

public class Contiene {
    private int idContiene;
    private int idProducto;
    private int idTalla;
    private int idColor;
    private int idMarca;
    private float precio;
    private byte stock;

    public Contiene() {
    }

    public Contiene(int idContiene) {
        this.idContiene = idContiene;
    }

    public Contiene(int idProducto, int idTalla, int idColor, int idMarca, float precio, byte stock) {
        this.idProducto = idProducto;
        this.idTalla = idTalla;
        this.idColor = idColor;
        this.idMarca = idMarca;
        this.precio = precio;
        this.stock = stock;
    }

    public Contiene(int idContiene, int idProducto, int idTalla, int idColor, int idMarca, float precio, byte stock) {
        this.idContiene = idContiene;
        this.idProducto = idProducto;
        this.idTalla = idTalla;
        this.idColor = idColor;
        this.idMarca = idMarca;
        this.precio = precio;
        this.stock = stock;
    }

    public int getIdContiene() {
        return idContiene;
    }

    public void setIdContiene(int idContiene) {
        this.idContiene = idContiene;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdTalla() {
        return idTalla;
    }

    public void setIdTalla(int idTalla) {
        this.idTalla = idTalla;
    }

    public int getIdColor() {
        return idColor;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public byte getStock() {
        return stock;
    }

    public void setStock(byte stock) {
        this.stock = stock;
    }

}
