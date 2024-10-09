package model;

import java.util.Objects;

public class Marca {
    private int idMarca;
    private String nombre;

    public Marca() {
    }

    public Marca(int idMarca) {
        this.idMarca = idMarca;
    }

    public Marca(String nombre) {
        this.nombre = nombre;
    }

    public Marca(int idMarca, String nombre) {
        this.idMarca = idMarca;
        this.nombre = nombre;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Marca other = (Marca) obj;
        if (this.idMarca != other.idMarca) {
            return false;
        }
        return Objects.equals(this.nombre, other.nombre);
    }
    
}
