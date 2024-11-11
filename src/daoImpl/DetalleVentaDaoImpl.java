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
import java.util.List;
import javax.swing.JOptionPane;
import model.DetalleVenta;

public class DetalleVentaDaoImpl implements IDaoGenerico<DetalleVenta> {

    DetalleVenta[] dVentas = new DetalleVenta[500];
    private int size;
    private int star;
    private int end;
    private static final String FILE_DVENTA = "dventa.txt";
    private static final String FILE_IDSDVENTA = "idsdventa.txt";

    public DetalleVentaDaoImpl() {
        cargarDatos();
    }

    public void enqueue(DetalleVenta dv) {
        if (size < dVentas.length) {
            dVentas[end] = dv;
            end = (end + 1) % dVentas.length;
            size++;
        } else {
            System.out.println("NO SE PUDO AGREGAR");
        }
    }

    public void dequeue(DetalleVenta dv) {
        if (size < dVentas.length) {
            DetalleVenta d = dVentas[star];
            dVentas[star] = null;
            star = (star + 1) % dVentas.length;
            size--;
        } else {
            System.out.println("");
        }
    }

    public void clear() {
        for (int i = 0; i < dVentas.length; i++) {
            dVentas[i] = null;
        }
        star = 0;
        end = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public int obtenerId(String texto) {
        int id = 1;

        // Verificar si el archivo existe
        if (!Files.exists(Paths.get(FILE_IDSDVENTA))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_IDSDVENTA))) {
                writer.write("0\n");
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al crear el archivo idsproductos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Leer el último código del archivo
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_IDSDVENTA))) {
            List<String> lines = Files.readAllLines(Paths.get(FILE_IDSDVENTA));
            if (!lines.isEmpty()) {
                try {
                    int lastCode = Integer.parseInt(lines.get(lines.size() - 1).strip());
                    id = lastCode + 1;
                } catch (NumberFormatException e) {
                    id = 1;
                }
            }
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, "Error al obtener el ultimo ID de productos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }

    @Override
    public boolean actualizar(DetalleVenta obj) {
        for (DetalleVenta dv : dVentas) {
            if (dv.getIdDVenta() != obj.getIdDVenta()) {
                return false;
            }
        }

        for (int i = 0; i < dVentas.length; i++) {
            if (dVentas[i].getIdDVenta() == obj.getIdDVenta()) {
                dVentas[i] = obj;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(DetalleVenta obj) {
        for (int i = 0; i < dVentas.length; i++) {
            if (dVentas[i] != null && dVentas[i].getIdDVenta() == obj.getIdDVenta()) {
                dVentas[i] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_DVENTA))) {
            for (DetalleVenta dv : dVentas) {
                writer.write(dv.getIdDVenta()+ ";" + dv.getIdProducto()+ ";" + dv.getPrecio()+ ";" + dv.getCantidad() + ";" + dv.getTotal());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, "Error al guardar los productos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void cargarDatos() {
        File fileDVenta = new File(FILE_DVENTA);
        if (fileDVenta.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileDVenta))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(";");
                    int idDVenta = Integer.parseInt(datos[0]);
                    int id = Integer.parseInt(datos[1]);
                    Float precio = Float.parseFloat(datos[2]);
                    int cantidad = Integer.parseInt(datos[3]);
                    Float total = Float.parseFloat(datos[4]);
                    DetalleVentaDaoImpl venta = new DetalleVentaDaoImpl(); 
                    venta.enqueue(new DetalleVenta(idDVenta, id, cantidad, precio, total ));
                }
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al cargar los productos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public int total() {
        return size;
    }

    @Override
    public int obtenerUltimoId() {
        int id = 1;

        // Verificar si el archivo existe
        if (!Files.exists(Paths.get(FILE_IDSDVENTA))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_IDSDVENTA))) {
                writer.write("0\n");
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al crear el archivo idsproductos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Leer el último código del archivo
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_IDSDVENTA))) {
            List<String> lines = Files.readAllLines(Paths.get(FILE_IDSDVENTA));
            if (!lines.isEmpty()) {
                try {
                    int lastCode = Integer.parseInt(lines.get(lines.size() - 1).strip());
                    id = lastCode + 1;
                } catch (NumberFormatException e) {
                    id = 1;
                }
            }
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, "Error al obtener el ultimo ID de productos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }

    @Override
    public boolean agregar(DetalleVenta obj) {
        try (BufferedWriter codigos = new BufferedWriter(new FileWriter(FILE_IDSDVENTA, true))) {
            codigos.write(obj.getIdProducto() + "\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar el codigo de producto", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (size < dVentas.length) {
            dVentas[end] = obj;
            end = (end + 1) % dVentas.length;
            size++;
            return true;
        } else {
            System.out.println("NO SE PUDO AGREGAR");
            return false;
        }
    }

}
