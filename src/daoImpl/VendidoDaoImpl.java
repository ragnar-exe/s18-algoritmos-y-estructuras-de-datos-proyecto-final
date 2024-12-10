package daoImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import model.DetalleVenta;

public class VendidoDaoImpl {
    List<DetalleVenta> detallesDatos = new ArrayList<>();
    HashMap<String, DetalleVenta> agrupadosDatos = new HashMap<>();

    public VendidoDaoImpl() {
        cargarDatos();
    }
    
    public void cargarDatos() {
        File fileDVenta = new File(DetalleVentaDaoImpl.FILE_DVENTA);
        if (fileDVenta.exists()) {

            try (BufferedReader reader = new BufferedReader(new FileReader(fileDVenta))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(";");
                    int idDVenta = Integer.parseInt(datos[0]);
                    int idProducto = Integer.parseInt(datos[1]);
                    int cantidad = Integer.parseInt(datos[2]);
                    float precio = Float.parseFloat(datos[3]);
                    detallesDatos.add(new DetalleVenta(idDVenta, idProducto, cantidad, precio));
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,"Error al cargar el archivo de detalle de ventas para vendidos.","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }
    
    public HashMap<String, DetalleVenta> agruparDatos() {
        for (DetalleVenta detalle : detallesDatos) {
            // Crear una clave única combinando el ID del producto y el precio
            String clave = detalle.getIdProducto() + "_" + detalle.getPrecio();
            if (agrupadosDatos.containsKey(clave)) {
                // Si ya existe, agregar la cantidad al detalle existente
                DetalleVenta existente = agrupadosDatos.get(clave);
                existente.agregarCantidad(detalle.getCantidad());
            } else {
                // Si no existe, agregar un nuevo detalle al mapa
                agrupadosDatos.put(clave, new DetalleVenta(0, detalle.getIdProducto(), detalle.getCantidad(), detalle.getPrecio()));
            }
        }
        return agrupadosDatos;
    }
    
    public List<DetalleVenta> listar() {
        agruparDatos();
        List<DetalleVenta> listaDetalles = new ArrayList<>(agrupadosDatos.values());
        listaDetalles.sort((d1, d2) -> Integer.compare(d2.getCantidad(), d1.getCantidad()));
        return listaDetalles;
    }
}
