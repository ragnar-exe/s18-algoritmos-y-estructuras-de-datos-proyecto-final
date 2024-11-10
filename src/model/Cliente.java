package model;

public class Cliente extends Persona{
    private String direccion;
    private String dni;

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Cliente(String direccion, String dni) {
        this.direccion = direccion;
        this.dni = dni;
    }

    public Cliente(int idPersona) {
        super(idPersona);
    }

    public Cliente(String dni, int idPersona, String nombres, String apellidos, String direccion, String correo) {
        super(idPersona, nombres, apellidos, correo);
        this.direccion = direccion;
        this.dni = dni;
    }
    

    public Cliente(String direccion, String dni, int idPersona) {
        super(idPersona);
        this.direccion = direccion;
        this.dni = dni;
    }



    
    
    
}
