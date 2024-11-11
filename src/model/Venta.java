package model;

import java.time.LocalDateTime;

public class Venta {
    private int idVenta;
    private int idCliente;
    private float total;
    private float subtotal;
    private float impuestoTotal;
    private LocalDateTime fecha;

    public Venta() {
    }

    public Venta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Venta(int idVenta, int idCliente, float total, float subtotal, float impuestoTotal, LocalDateTime fecha) {
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.total = total;
        this.subtotal = subtotal;
        this.impuestoTotal = impuestoTotal;
        this.fecha = fecha;
    }    
    

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public float getImpuestoTotal() {
        return impuestoTotal;
    }

    public void setImpuestoTotal(float impuestoTotal) {
        this.impuestoTotal = impuestoTotal;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    
    
    
    
}
