package model;

import java.util.Objects;

public class Compra {
    private int idCompra;
    private String fecha;
    private int idProveedor;
    private float total;
    private float impuestoTotal;
    private float subTotal;
    private boolean estado;

    public Compra() {
    }

    public Compra(int idCompra) {
        this.idCompra = idCompra;
    }

    public Compra(int idCompra, String fecha, int idProveedor, float total, float impuestoTotal, float subTotal, boolean estado) {
        this.idCompra = idCompra;
        this.fecha = fecha;
        this.idProveedor = idProveedor;
        this.total = total;
        this.impuestoTotal = impuestoTotal;
        this.subTotal = subTotal;
        this.estado = estado;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getImpuestoTotal() {
        return impuestoTotal;
    }

    public void setImpuestoTotal(float impuestoTotal) {
        this.impuestoTotal = impuestoTotal;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idCompra;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Compra other = (Compra) obj;
        if (this.idCompra != other.idCompra) {
            return false;
        }
        if (this.idProveedor != other.idProveedor) {
            return false;
        }
        if (Float.floatToIntBits(this.total) != Float.floatToIntBits(other.total)) {
            return false;
        }
        if (Float.floatToIntBits(this.impuestoTotal) != Float.floatToIntBits(other.impuestoTotal)) {
            return false;
        }
        if (Float.floatToIntBits(this.subTotal) != Float.floatToIntBits(other.subTotal)) {
            return false;
        }
        if (this.estado != other.estado) {
            return false;
        }
        return Objects.equals(this.fecha, other.fecha);
    }
}
