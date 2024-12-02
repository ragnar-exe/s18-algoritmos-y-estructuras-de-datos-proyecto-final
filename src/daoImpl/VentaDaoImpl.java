package daoImpl;

import dao.IDaoGenerico;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Venta;

public class VentaDaoImpl implements IDaoGenerico<Venta> {
   
    ArrayList<Venta> ventas = new ArrayList<>();
    private static final String FILE_VENTAS = "ventas.txt";
    private static final String FILE_IDSVENTAS = "idsventa.txt";
    private DetalleVentaDaoImpl detalleVentaDao = new DetalleVentaDaoImpl();

    public VentaDaoImpl() {
        cargarDatos();
    }
    

   

    public Venta obtenerPorId(int idVenta) {
        Venta ventaEncontrada = null;
        for (Venta venta : ventas) { // listaDeCompras es tu colección de objetos Compra
            if (venta != null && venta.getIdVenta()== idVenta) {
                ventaEncontrada = venta;
                break;
            }
        }
        return ventaEncontrada;
    }

    @Override
    public int obtenerUltimoId() {
        int id = 1;

        // Verificar si el archivo existe
        if (!Files.exists(Paths.get(FILE_IDSVENTAS))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_IDSVENTAS))) {
                writer.write("0\n");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al crear el archivo idscompras", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Leer el ï¿½ltimo cï¿½digo del archivo
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_IDSVENTAS))) {
            List<String> lines = Files.readAllLines(Paths.get(FILE_IDSVENTAS));
            if (!lines.isEmpty()) {
                try {
                    int lastCode = Integer.parseInt(lines.get(lines.size() - 1).strip());
                    id = lastCode + 1;
                } catch (NumberFormatException e) {
                    id = 1;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el ultimo ID de compra", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }

    @Override
    public boolean agregar(Venta obj) {
        try (BufferedWriter codigos = new BufferedWriter(new FileWriter(FILE_IDSVENTAS, true))) {
            codigos.write(obj.getIdVenta()+ "\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar el codigo de compra", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        System.out.println("agregaaaaaaaaaaaaaaaaaaaaaaa");
        return ventas.add(obj);
    }

    @Override
    public boolean actualizar(Venta obj) {
        for (int i = 0; i < ventas.size(); i++) {
            if (ventas.get(i).getIdVenta()== obj.getIdVenta()) {
                ventas.set(i, obj);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(Venta obj) {
        for (Venta com : ventas) {
        // Compara directamente los ID de la compra
        if (com.getIdVenta()== obj.getIdVenta()) {
            com.setEstado(false); // Cambia el estado
            return true;  // Se eliminó correctamente
        }
    }
    return false; // No se encontró el objeto
    }

    @Override
    public void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_VENTAS))) {
            for (Venta ven : ventas) {
                writer.write(ven.getIdVenta()+ ";" + ven.getFecha() + ";" + ven.getIdCliente()+ ";" + ven.getTotal() + ";" + ven.getImpuestoTotal() + ";" + ven.getSubtotal()+ ";" + ven.isEstado());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar las compras", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void cargarDatos() {
        File fileCompras = new File(FILE_VENTAS);
        if (fileCompras.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileCompras))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(";");
                    int id = Integer.parseInt(datos[0]);
                    String fecha = datos[1];
                    int idCliente = Integer.parseInt(datos[2]);
                    float total = Float.parseFloat(datos[3]);
                    float impuesTotal = Float.parseFloat(datos[4]);
                    float subTotal = Float.parseFloat(datos[5]);
                    boolean estado = Boolean.parseBoolean(datos[6]);
                    ventas.add(new Venta(id, idCliente, total, subTotal, impuesTotal, fecha, estado));
                }
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al cargar las categorias", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public int total() {
        return ventas.size();
    }

    public List<Venta> listar() {
        guardarEnArchivo();
        return ventas;
    }

    public int obtenerIdCliente(int id) {
        int idCli = -1;
        for (Venta com : ventas) {
            if (com != null && com.getIdVenta()== id) {
                idCli = com.getIdCliente();
                break;
            }
        }
        return idCli;
    }
    
    
}
