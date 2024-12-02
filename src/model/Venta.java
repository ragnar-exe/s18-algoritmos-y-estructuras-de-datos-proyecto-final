package model;

import java.time.LocalDateTime;

public class Venta {
    private int idVenta;
    private int idCliente;
    private float total;
    private float subtotal;
    private float impuestoTotal;
    private String fecha;
    private boolean estado;

    public Venta() {
    }

    public Venta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Venta(int idVenta, int idCliente, float total, float subtotal, float impuestoTotal, String fecha, boolean estado) {
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.total = total;
        this.subtotal = subtotal;
        this.impuestoTotal = impuestoTotal;
        this.fecha = fecha;
        this.estado = estado;
    }    

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
    
}
