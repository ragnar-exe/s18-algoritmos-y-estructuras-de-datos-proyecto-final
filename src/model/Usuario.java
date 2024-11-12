package model;
public class Usuario {
    private int idUsuario;
    private String usuario;
    private String contrasena;

    public Usuario() {
    }

    public Usuario(int idUsuario, String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.idUsuario = idUsuario;
    }

    public Usuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public Usuario(int idUsuario, String usuario) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }


}