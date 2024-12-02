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
import model.Compra;

public class CompraDaoImpl implements IDaoGenerico<Compra> {

    ArrayList<Compra> compras = new ArrayList<>();
    private static final String FILE_COMPRAS = "compras.txt";
    private static final String FILE_IDSCOMPRAS = "idscompras.txt";
    private DetalleCompraDaoImpl detalleCompraDao = new DetalleCompraDaoImpl();

    public CompraDaoImpl() {
        cargarDatos();
    }

    public Compra obtenerPorId(int idCompra) {
        Compra compraEncontrada = null;
        for (Compra compra : compras) { // listaDeCompras es tu colección de objetos Compra
            if (compra != null && compra.getIdCompra() == idCompra) {
                compraEncontrada = compra;
                break;
            }
        }
        return compraEncontrada;
    }

    @Override
    public int obtenerUltimoId() {
        int id = 1;

        // Verificar si el archivo existe
        if (!Files.exists(Paths.get(FILE_IDSCOMPRAS))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_IDSCOMPRAS))) {
                writer.write("0\n");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al crear el archivo idscompras", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Leer el ï¿½ltimo cï¿½digo del archivo
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_IDSCOMPRAS))) {
            List<String> lines = Files.readAllLines(Paths.get(FILE_IDSCOMPRAS));
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
    public boolean agregar(Compra obj) {
        try (BufferedWriter codigos = new BufferedWriter(new FileWriter(FILE_IDSCOMPRAS, true))) {
            codigos.write(obj.getIdCompra() + "\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar el codigo de compra", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        System.out.println("agregaaaaaaaaaaaaaaaaaaaaaaa");
        return compras.add(obj);
    }

    @Override
    public boolean actualizar(Compra obj) {
        for (int i = 0; i < compras.size(); i++) {
            if (compras.get(i).getIdCompra() == obj.getIdCompra()) {
                compras.set(i, obj);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(Compra obj) {
        for (Compra com : compras) {
            if (com.getIdCompra() == obj.getIdCompra()) {
                com.setEstado(false);
                return true;
            }
        }
        return false;
    }

    @Override
    public void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_COMPRAS))) {
            for (Compra compra : compras) {
                writer.write(compra.getIdCompra() + ";" + compra.getFecha() + ";" + compra.getIdProveedor() + ";" + compra.getTotal() + ";" + compra.getImpuestoTotal() + ";" + compra.getSubTotal() + ";" + compra.isEstado());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar las compras", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void cargarDatos() {
        File fileCompras = new File(FILE_COMPRAS);
        if (fileCompras.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileCompras))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(";");
                    int id = Integer.parseInt(datos[0]);
                    String fecha = datos[1];
                    int idProveedor = Integer.parseInt(datos[2]);
                    float total = Float.parseFloat(datos[3]);
                    float impuesTotal = Float.parseFloat(datos[4]);
                    float subTotal = Float.parseFloat(datos[5]);
                    boolean estado = Boolean.parseBoolean(datos[6]);
                    compras.add(new Compra(id, fecha, idProveedor, total, impuesTotal, subTotal, estado));
                }
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al cargar las categorias", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public int total() {
        return compras.size();
    }

    public List<Compra> listar() {
        guardarEnArchivo();
        return compras;
    }

    public int obtenerIdProveedor(int id) {
        int idPro = -1;
        for (Compra com : compras) {
            if (com != null && com.getIdCompra()== id) {
                idPro = com.getIdProveedor();
                break;
            }
        }
        return idPro;
    }
}
