package model;

public class DetalleCompra implements Comparable<DetalleCompra>{
    private int idDCompra;
    private int idProducto;
    private int idMarca;
    private int idTalla;
    private int idColor;
    private float precio;
    private int cantidad;
    private float total;
    private int idCompra;

    public DetalleCompra() {
    }
    
    public DetalleCompra(int idDCompra) {
        this.idDCompra = idDCompra;
    }

    public DetalleCompra(int idDCompra, int idProducto, int idMarca, int idTalla, int idColor, float precio, int cantidad, float total, int idCompra) {
        this.idDCompra = idDCompra;
        this.idProducto = idProducto;
        this.idMarca = idMarca;
        this.idTalla = idTalla;
        this.idColor = idColor;
        this.precio = precio;
        this.cantidad = cantidad;
        this.total = total;
        this.idCompra = idCompra;
    }

    public int getIdDCompra() {
        return idDCompra;
    }

    public void setIdDCompra(int idDCompra) {
        this.idDCompra = idDCompra;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
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

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.idDCompra;
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
        final DetalleCompra other = (DetalleCompra) obj;
        if (this.idDCompra != other.idDCompra) {
            return false;
        }
        if (this.idProducto != other.idProducto) {
            return false;
        }
        if (this.idMarca != other.idMarca) {
            return false;
        }
        if (this.idTalla != other.idTalla) {
            return false;
        }
        if (this.idColor != other.idColor) {
            return false;
        }
        if (Float.floatToIntBits(this.precio) != Float.floatToIntBits(other.precio)) {
            return false;
        }
        if (this.cantidad != other.cantidad) {
            return false;
        }
        if (Float.floatToIntBits(this.total) != Float.floatToIntBits(other.total)) {
            return false;
        }
        return this.idCompra == other.idCompra;
    }

    @Override
    public int compareTo(DetalleCompra o) {
        return Integer.compare(this.cantidad, o.cantidad);
    }

}
