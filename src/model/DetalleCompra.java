package model;

public class DetalleCompra {
    private int idDCompra;
    private int idContiene;
    private float precio;
    private int cantidad;
    private float total;

    public DetalleCompra() {
    }

    public DetalleCompra(int idDCompra) {
        this.idDCompra = idDCompra;
    }

    public DetalleCompra(int idDCompra, int idContiene, float precio, int cantidad, float total) {
        this.idDCompra = idDCompra;
        this.idContiene = idContiene;
        this.precio = precio;
        this.cantidad = cantidad;
        this.total = total;
    }

    public int getIdDCompra() {
        return idDCompra;
    }

    public void setIdDCompra(int idDCompra) {
        this.idDCompra = idDCompra;
    }

    public int getIdContiene() {
        return idContiene;
    }

    public void setIdContiene(int idContiene) {
        this.idContiene = idContiene;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

}
