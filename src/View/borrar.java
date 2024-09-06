package View;

import daoImpl.ColorDaoImpl;
import dataSource.Conexion;

public class borrar {
    public static void main(String[] args) {
        ColorDaoImpl color = new ColorDaoImpl();
        Conexion ocnx=Conexion.getInstancia() ;
        if (ocnx.conectar()!=null) {
            System.out.println("Conectado");
        } else {
            System.out.println("Desconectado");
        }
        //System.out.println(color.obtenerId("Blanco"));
        //System.out.println(color.obtenerNombre(1));
        //System.out.println(color.actualizar(new Color(4,"Verde")));
        //System.out.println(color.eliminar(new Color(4)));
        //System.out.println(color.listar());
        String h = "holaa";
        String c = "";
        if (h.length()==4) {
            System.out.println("S�");
        }
        if (c.isEmpty()) {
            System.out.println("a");
        } else {
            System.out.println("x");
        }
        System.out.println(color.total());
    }  
}
