package daoImpl;

import dao.IDaoGenerico;
import dao.IDaoObtenerLista;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import javax.swing.JOptionPane;
import model.DetalleCompra;
import model.Producto;

public class DetalleCompraDaoImpl implements IDaoGenerico<DetalleCompra> {
    
    private static final String FILE_DCOMPRA = "dcompra.txt";
    private static final String FILE_IDSDCOMPRA = "idsdcompra.txt";
    
    private Queue<DetalleCompra> dCompras = new PriorityQueue<>(
    (a, b) -> {
        int priorityComparison = Integer.compare(a.getIdDCompra(), b.getIdDCompra());
        if (priorityComparison == 0) {
            // Ajusta aquí si quieres otro criterio de prioridad secundario.
            return Float.compare(a.getPrecio(), b.getPrecio());
        }
        return priorityComparison;
    }
);

    public DetalleCompraDaoImpl() {
        cargarDatos();
    }

    public void enqueue(DetalleCompra dc) {
        dCompras.offer(dc);
    }

    public void dequeue() {
        dCompras.poll();
    }

    public void clear() {
        dCompras.clear();
    }

    public boolean isEmpty() {
        return dCompras.isEmpty();
    }

    public int size() {
        return dCompras.size();
    }

    public int obtenerId(String texto) {
        int id = 1;

        // Verificar si el archivo existe
        if (!Files.exists(Paths.get(FILE_IDSDCOMPRA))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_IDSDCOMPRA))) {
                writer.write("0\n");
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al crear el archivo idsproductos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Leer el último código del archivo
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_IDSDCOMPRA))) {
            List<String> lines = Files.readAllLines(Paths.get(FILE_IDSDCOMPRA));
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
public boolean actualizar(DetalleCompra obj) {
    if (eliminar(obj)) {
        return dCompras.offer(obj); // Reinsertar para respetar el orden de prioridad.
    }
    return false;
}


    @Override
    public boolean eliminar(DetalleCompra obj) {
        return dCompras.remove(obj);
    }

    @Override
    public void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_DCOMPRA))) {
            for (DetalleCompra dc : dCompras) {
                writer.write(dc.getIdDCompra()+ dc.getIdContiene()+ ";" + dc.getIdColor());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar las compras", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void cargarDatos() {
        File fileDCompra = new File(FILE_DCOMPRA);
        if (fileDCompra.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileDCompra))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(";");
                    System.out.println(datos.length+"----");
                    int idDCompra = Integer.parseInt(datos[0]);
                    int idCategoria = Integer.parseInt(datos[1]);
                    int idProducto = Integer.parseInt(datos[2]);
                    int idMarca = Integer.parseInt(datos[3]);
                    int idTalla = Integer.parseInt(datos[4]);
                    int idColor = Integer.parseInt(datos[5]);
                    float precio = Float.parseFloat(datos[6]);
                    int cantidad = Integer.parseInt(datos[7]);
                    float total = Float.parseFloat(datos[8]);
//                    int idDCompra, int idCategoria, int idProducto, int idMarca, int idTalla, int idColor, float precio, int cantidad, float total
                    this.enqueue(new DetalleCompra(idDCompra, idCategoria, idProducto, idMarca, idTalla, idColor, precio, cantidad,total));
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al cargar el archivo " + FILE_DCOMPRA, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public int total() {
        return size();
    }

    @Override
    public int obtenerUltimoId() {
        int id = 1;

        // Verificar si el archivo existe
        if (!Files.exists(Paths.get(FILE_IDSDCOMPRA))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_IDSDCOMPRA))) {
                writer.write("0\n");
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al crear el archivo idsproductos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Leer el último código del archivo
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_IDSDCOMPRA))) {
            List<String> lines = Files.readAllLines(Paths.get(FILE_IDSDCOMPRA));
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
    public boolean agregar(DetalleCompra obj) {
        try (BufferedWriter codigos = new BufferedWriter(new FileWriter(FILE_IDSDCOMPRA, true))) {
            codigos.write(obj.getIdDCompra() + "\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar el codigo de producto", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return dCompras.offer(obj);
    }

    public Queue<DetalleCompra> listarDetalle() {
        guardarEnArchivo();
        return dCompras ;
    }

    public List<DetalleCompra> listar(String texto) {
        List<DetalleCompra> resultado = new LinkedList<>();
        String valorBuscar = texto.toLowerCase();
//      IDaoObtenerLista<Cliente> idaoCliente =  (IDaoObtenerLista<Cliente>) new ClienteDaoImpl();
        IDaoObtenerLista<Producto> idaoProducto = new ProductoDaoImpl();

        for (DetalleCompra dc : dCompras) {
            if (dc != null) {  // Verificación para evitar el NullPointerException
                boolean coincideConId = String.valueOf(dc.getIdProducto()).contains(valorBuscar);
                String producto = idaoProducto.obtenerNombre(dc.getIdProducto());
                boolean coincideConProducto = producto != null && producto.contains(valorBuscar);

                if (coincideConId || coincideConProducto) {
                    resultado.add(dc);
                }
            }
        }
        return resultado;
    }
}