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
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Contiene;
import model.DetalleCompra;
import model.DetalleVenta;
import model.Nodo;
import model.Producto;

public class DetalleVentaDaoImpl implements IDaoGenerico<DetalleVenta> {

    DetalleVenta[] dVentas = new DetalleVenta[500];
    ContieneDaoImpl IDaoContiene = new ContieneDaoImpl();

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
            if (dv != null && dv.getIdDVenta() != obj.getIdDVenta()) {
                JOptionPane.showMessageDialog(null, "Etoy aqui");
                return false;
            }
        }

        for (int i = 0; i < dVentas.length; i++) {
            if (dVentas[i] != null && dVentas[i].getIdDVenta() == obj.getIdDVenta()) {
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
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_DVENTA))) {
            for (int i = 0; i < dVentas.length; i++) {
                if (dVentas[i] != null) {
                    writer.write(dVentas[i].getIdDVenta() + ";" + dVentas[i].getIdProducto() + ";" + dVentas[i].getIdCliente() + ";" + dVentas[i].getCantidad() + ";" + dVentas[i].getPrecio() + ";" + dVentas[i].getTotal());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar los colores", "ERROR", JOptionPane.ERROR_MESSAGE);
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
                    int idCliente = Integer.parseInt(datos[2]);
                    int cantidad = Integer.parseInt(datos[3]);
                    Float precio = Float.parseFloat(datos[4]);
                    Float total = Float.parseFloat(datos[5]);
                    this.enqueue(new DetalleVenta(idDVenta, id, idCliente, cantidad, precio, total));
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al cargar el archivo " + FILE_DVENTA, "ERROR", JOptionPane.ERROR_MESSAGE);
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
//        // Verificar si hay stock suficiente
//        if (productoInventario.getStock() >= obj.getCantidad()) {
//            // Reducir el stock y actualizar el inventario
//            byte stockNuevo = (byte) (productoInventario.getStock() - obj.getCantidad());
//            System.out.println(stockNuevo);
//            System.out.println("" + productoInventario.getIdContiene());
////            int idContiene, int idProducto, int idTalla, int idColor, int idMarca, float precio, byte stock
//            if (contieneDao.actualizar(new Contiene(productoInventario.getIdContiene(), productoInventario.getIdProducto(), productoInventario.getIdTalla(), productoInventario.getIdColor(), productoInventario.getIdMarca(), productoInventario.getPrecio(), stockNuevo))) {
//                System.out.println("se agrego correcto");
//            }else{
//                System.out.println("NO SE PUDO AGREGAR");
//            }

        // Agregar el detalle de venta y guardar el código
        try (BufferedWriter codigos = new BufferedWriter(new FileWriter(FILE_IDSDVENTA, true))) {
            codigos.write(obj.getIdDVenta() + "\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar el codigo de producto", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (size < dVentas.length) {
            dVentas[end] = obj;
            end = (end + 1) % dVentas.length;
            size++;
            Nodo temp = IDaoContiene.inicio;
            while (temp != null) {
//                if (temp.getContiene().getIdProducto() == obj.get)
                temp = temp.siguiente;
            }
            IDaoContiene.guardarEnArchivo();
            return true;
        } else {
            System.out.println("NO SE PUDO AGREGAR");
            return false;
        }
//        } else {
//            JOptionPane.showMessageDialog(null, "Stock insuficiente para el producto " + obj.getIdProducto(), "Error de stock", JOptionPane.WARNING_MESSAGE);
//            return false;
//        }

    }

    public DetalleVenta[] listarDetalle() {
        guardarEnArchivo();
        return dVentas;
    }

    public List<DetalleVenta> listar(String texto) {
        List<DetalleVenta> resultado = new LinkedList<>();
        String valorBuscar = texto.toLowerCase();
        IDaoObtenerLista<Cliente> idaoCliente = (IDaoObtenerLista<Cliente>) new ClienteDaoImpl();
        IDaoObtenerLista<Producto> idaoProducto = new ProductoDaoImpl();

        for (DetalleVenta dv : dVentas) {
            if (dv != null) {  // Verificación para evitar el NullPointerException
                boolean coincideConId = String.valueOf(dv.getIdProducto()).contains(valorBuscar);
                String cliente = idaoCliente.obtenerNombre(dv.getIdCliente());
                String producto = idaoProducto.obtenerNombre(dv.getIdProducto());
                boolean coincideConCliente = cliente != null && cliente.contains(valorBuscar);
                boolean coincideConProducto = producto != null && producto.contains(valorBuscar);

                if (coincideConId || coincideConCliente || coincideConProducto) {
                    resultado.add(dv);
                }
            }
        }
        return resultado;

    }

    public double calcularTotal() {
        DecimalFormat df = new DecimalFormat("0.00");
        double total = 0.00;
        for (DetalleVenta dv : dVentas) {
            if (dv != null) {
                total += (dv.getPrecio() * dv.getCantidad());
            }
        }
        return Double.parseDouble(df.format(total));
    }

    public int obtenerIdProducto(int idDetalle) {
        int id = -1;
        for (int i = 0; i < dVentas.length; i++) {
            if (dVentas[i] != null && dVentas[i].getIdDVenta() == idDetalle) {
                id = dVentas[i].getIdProducto();
            }
        }
        return id;
    }
    
    public int obtenerIdCliente(int idDetalle) {
        int id = -1;
        for (int i = 0; i < dVentas.length; i++) {
            if (dVentas[i] != null && dVentas[i].getIdDVenta() == idDetalle) {
                id = dVentas[i].getIdCliente();
            }
        }
        return id;
    }
}
