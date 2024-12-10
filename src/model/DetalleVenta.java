package model;

public class DetalleVenta {
    private int idDVenta;
    private int idProducto;
    private int cantidad;
    private float precio;
    private float total;
    private int idVenta;
    
    public DetalleVenta() {
    }

    public DetalleVenta(int idDVenta, int idProducto, int cantidad, float precio,float total, int idVenta) {
        this.idDVenta = idDVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
        this.idVenta = idVenta;
    }

    public DetalleVenta(int idDVenta, int idProducto, int cantidad, float precio) {
        this.idDVenta = idDVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public DetalleVenta(int idDVenta) {
        this.idDVenta = idDVenta;
    }


    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdDVenta() {
        return idDVenta;
    }

    public void setIdDVenta(int idDVenta) {
        this.idDVenta = idDVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    public void agregarCantidad(int cantidad) {
        this.cantidad += cantidad;
    }
    
}
