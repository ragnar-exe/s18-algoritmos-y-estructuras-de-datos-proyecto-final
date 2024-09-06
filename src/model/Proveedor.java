package model;

public class Proveedor extends Persona {
    private int idProveedor;
    private String telefono;
    private int idPersona;

    public Proveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Proveedor(String telefono, int idPersona) {
        this.telefono = telefono;
        this.idPersona = idPersona;
    }

    public Proveedor(int idProveedor, String telefono) {
        this.idProveedor = idProveedor;
        this.telefono = telefono;
    }

    public Proveedor(int idProveedor, String telefono, int idPersona) {
        this.idProveedor = idProveedor;
        this.telefono = telefono;
        this.idPersona = idPersona;
    }

    public Proveedor(int idProveedor, String nombres, String apellidos, String correo, String telefono) {
        super(nombres, apellidos, correo);
        this.idProveedor = idProveedor;
        this.telefono = telefono;
    }
    
    

    public Proveedor(String nombres, String apellidos) {
        super(nombres, apellidos);
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
    
}
