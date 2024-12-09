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
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import javax.swing.JOptionPane;
import model.Compra;
import model.Contiene;
import model.DetalleCompra;
import model.Nodo;

public class DetalleCompraDaoImpl implements IDaoGenerico<DetalleCompra> {

    private static final String FILE_DCOMPRA = "dcompra.txt";
    private static final String FILE_IDSDCOMPRA = "idsdcompra.txt";
    ContieneDaoImpl IDaoContiene = new ContieneDaoImpl();
//    Comparator<DetalleCompra> porCantidad = Comparator.comparing(DetalleCompra::getCantidad);

    private Queue<DetalleCompra> dCompras = new PriorityQueue<>();

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

    @Override
    public boolean actualizar(DetalleCompra obj) {
        for (DetalleCompra dc : dCompras) {
            if (dc != null && dc.getIdDCompra() == obj.getIdDCompra()) {
                Nodo temp = IDaoContiene.inicio;
                byte stockActualDCompras = (byte) dc.getCantidad();
                byte stockActualizar = (byte) obj.getCantidad();
                byte stockCalcular = (byte) (stockActualizar - stockActualDCompras);
                if (stockCalcular > 0) {
                    while (temp != null) {
                        if (temp.getContiene().getIdProducto() == obj.getIdProducto()
                                && temp.getContiene().getIdMarca() == obj.getIdMarca()
                                && temp.getContiene().getIdTalla() == obj.getIdTalla()
                                && temp.getContiene().getIdColor() == obj.getIdColor()
                                && temp.getContiene().getPrecio() == obj.getPrecio()) {
                            byte stockContiene = (byte) (temp.getContiene().getStock() + stockCalcular);
                            IDaoContiene.actualizar(new Contiene(temp.getContiene().getIdContiene(),
                                    obj.getIdProducto(),
                                    obj.getIdTalla(),
                                    temp.getContiene().getIdColor(),
                                    obj.getIdMarca(),
                                    obj.getPrecio(),
                                    stockContiene));
                            IDaoContiene.guardarEnArchivo();
                            dc.setIdProducto(obj.getIdProducto());
                            dc.setIdMarca(obj.getIdMarca());
                            dc.setIdTalla(obj.getIdTalla());
                            dc.setIdColor(obj.getIdColor());
                            dc.setPrecio(obj.getPrecio());
                            dc.setCantidad(obj.getCantidad());
                            dc.setTotal(obj.getTotal());
                            dc.setIdCompra(obj.getIdCompra());
                            return true;
                        }
                        temp = temp.getSiguiente();
                    }
                } else if (stockCalcular < 0) {
                    while (temp != null) {
                        if (temp.getContiene().getIdProducto() == obj.getIdProducto()
                                && temp.getContiene().getIdMarca() == obj.getIdMarca()
                                && temp.getContiene().getIdTalla() == obj.getIdTalla()
                                && temp.getContiene().getIdColor() == obj.getIdColor()
                                && temp.getContiene().getPrecio() == obj.getPrecio()) {
                            byte stockContiene = (byte) (temp.getContiene().getStock() + stockCalcular);
                            IDaoContiene.actualizar(new Contiene(temp.getContiene().getIdContiene(),
                                    temp.getContiene().getIdProducto(),
                                    temp.getContiene().getIdTalla(),
                                    temp.getContiene().getIdColor(),
                                    temp.getContiene().getIdMarca(),
                                    temp.getContiene().getPrecio(),
                                    stockContiene));
                            IDaoContiene.guardarEnArchivo();
                            dc.setIdProducto(obj.getIdProducto());
                            dc.setIdMarca(obj.getIdMarca());
                            dc.setIdTalla(obj.getIdTalla());
                            dc.setIdColor(obj.getIdColor());
                            dc.setPrecio(obj.getPrecio());
                            dc.setCantidad(obj.getCantidad());
                            dc.setTotal(obj.getTotal());
                            return true;
                        }
                        temp = temp.getSiguiente();
                    }
                } else {
                    dc.setIdProducto(obj.getIdProducto());
                    dc.setIdMarca(obj.getIdMarca());
                    dc.setIdTalla(obj.getIdTalla());
                    dc.setIdColor(obj.getIdColor());
                    dc.setPrecio(obj.getPrecio());
                    dc.setCantidad(obj.getCantidad());
                    dc.setTotal(obj.getTotal());
                    return true;
                }
            }
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
                writer.write(dc.getIdDCompra() + ";" + dc.getIdProducto() + ";" + dc.getIdMarca() + ";" + dc.getIdTalla() + ";" + dc.getIdColor() + ";" + dc.getPrecio() + ";" + dc.getCantidad() + ";" + dc.getTotal() + ";" + dc.getIdCompra());
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
                    int idDCompra = Integer.parseInt(datos[0]);
                    int idProducto = Integer.parseInt(datos[1]);
                    int idMarca = Integer.parseInt(datos[2]);
                    int idTalla = Integer.parseInt(datos[3]);
                    int idColor = Integer.parseInt(datos[4]);
                    float precio = Float.parseFloat(datos[5]);
                    int cantidad = Integer.parseInt(datos[6]);
                    float total = Float.parseFloat(datos[7]);
                    int idCompra = Integer.parseInt(datos[8]);
                    dCompras.offer(new DetalleCompra(idDCompra, idProducto, idMarca, idTalla, idColor, precio, cantidad, total, idCompra));
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

        // Leer el ultimo codigo del archivo
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
            JOptionPane.showMessageDialog(null, "Error al agregar el codigo de producto", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        Nodo temp = IDaoContiene.inicio;
        while (temp != null) {
            if (temp.getContiene().getIdProducto() == obj.getIdProducto()
                    && temp.getContiene().getIdMarca() == obj.getIdMarca()
                    && temp.getContiene().getIdTalla() == obj.getIdTalla()
                    && temp.getContiene().getIdColor() == obj.getIdColor()
                    && temp.getContiene().getPrecio() == obj.getPrecio()) {
                byte stockActualizado = (byte) (temp.getContiene().getStock() + obj.getCantidad());
                IDaoContiene.actualizar(new Contiene(temp.getContiene().getIdContiene(),
                        temp.getContiene().getIdProducto(),
                        temp.getContiene().getIdTalla(),
                        temp.getContiene().getIdColor(),
                        temp.getContiene().getIdMarca(),
                        temp.getContiene().getPrecio(),
                        stockActualizado));
                IDaoContiene.guardarEnArchivo();
                dCompras.offer(obj);
                return true;
            }
            temp = temp.getSiguiente();
        }
        IDaoContiene.agregar(new Contiene(IDaoContiene.obtenerUltimoId(),
                obj.getIdProducto(),
                obj.getIdTalla(),
                obj.getIdColor(),
                obj.getIdMarca(),
                obj.getPrecio(),
                (byte) obj.getCantidad()));
        IDaoContiene.guardarEnArchivo();
        return dCompras.offer(obj);
    }

    public Queue<DetalleCompra> listarDetalle() {
        guardarEnArchivo();
        Queue<DetalleCompra> ordenados = new PriorityQueue<>(
                Comparator.comparingInt(DetalleCompra::getCantidad)
        );
        ordenados.clear();
        ordenados.addAll(dCompras);
        return ordenados;
    }

    public List<DetalleCompra> listar(String texto) {
        List<DetalleCompra> resultado = new LinkedList<>();
//        String valorBuscar = texto.toLowerCase();
////      IDaoObtenerLista<Cliente> idaoCliente =  (IDaoObtenerLista<Cliente>) new ClienteDaoImpl();
//        IDaoObtenerLista<Producto> idaoProducto = new ProductoDaoImpl();
//
//        for (DetalleCompra dc : dCompras) {
//            if (dc != null) {  // Verificaci�n para evitar el NullPointerException
//                boolean coincideConId = String.valueOf(dc.getIdContiene()).contains(valorBuscar);
////                String producto = idaoProducto.obtenerNombre(dc.getId());
////                boolean coincideConProducto = producto != null && producto.contains(valorBuscar);
//
//                if (coincideConId || coincideConProducto) {
//                    resultado.add(dc);
//                }
//            }
//        }
        return resultado;
    }

    public float calcularTotal(int idCompra) {
        DecimalFormat df = new DecimalFormat("0.00");
        double total = 0.00;
        for (DetalleCompra dc : dCompras) {
            if (dc != null && dc.getIdCompra() == idCompra) {
                total += (dc.getPrecio() * dc.getCantidad());
            }
        }
        return Float.parseFloat(df.format(total));
    }

    public void clearIdCompra(Compra Obj) {
        for (DetalleCompra detalleCom : dCompras) {
            if (detalleCom != null && detalleCom.getIdCompra() == Obj.getIdCompra()) {
                System.out.println("conficonal if for dao");
                dCompras.remove(Obj);
            }
        }
    }

    public List<DetalleCompra> listarPorIdCompra(int idCompra) {
        List<DetalleCompra> detallesCompra = new LinkedList<>();

        for (DetalleCompra dc : dCompras) {
            if (dc != null && dc.getIdCompra() == idCompra) {
                detallesCompra.add(dc);
            }
        }

        return detallesCompra;
    }
    
    public int buscarIdCompra(int id){
        int idEncontrado= -1;
        for (DetalleCompra dc : dCompras) {
            if (dc != null && dc.getIdDCompra()== id) {
                idEncontrado = dc.getIdCompra();
                break;
            }
        }
        return idEncontrado;
    }

}
