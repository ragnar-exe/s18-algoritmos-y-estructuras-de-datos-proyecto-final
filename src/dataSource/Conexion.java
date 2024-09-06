package dataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    private final String URL="jdbc:mysql://localhost:3306/novedades";
    private final String USER="root";
    private final String PASSWORD=""; 
    public static Conexion instancia;
    public Connection conexion; 
    private Conexion() {    
    }    
    public  Connection conectar(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conexion=DriverManager.getConnection(URL, USER, PASSWORD);
            return conexion;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error en la conexión");
            return null;
        }      
    }    
    public void desconectar(){
        try {
            this.conexion.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }  
     public synchronized static Conexion getInstancia(){
       if (instancia==null){
            instancia=new Conexion();
        }
        return instancia;
    }
}
