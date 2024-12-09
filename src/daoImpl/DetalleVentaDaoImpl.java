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
import model.DetalleVenta;
import model.Nodo;
import model.Producto;
import model.Venta;

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

    public float calcularTotal(int idVenta) {
        DecimalFormat df = new DecimalFormat("0.00");
        double totalCal = 0.00;
        for (DetalleVenta dv : dVentas) {
            if (dv != null && dv.getIdVenta() == idVenta) {
                totalCal += (dv.getPrecio() * dv.getCantidad());
            }
        }
        return Float.parseFloat(df.format(totalCal));
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

    @Override
    public boolean actualizar(DetalleVenta obj) {
        // for (DetalleVenta dv : dVentas) {
        // if (dv != null && dv.getIdDVenta() != obj.getIdDVenta()) {
        // System.out.println(dv.getIdDVenta());
        // System.out.println(obj.getIdDVenta());
        // JOptionPane.showMessageDialog(null, "Etoy aqui");
        // return false;
        // }
        // }

        for (int i = 0; i < dVentas.length; i++) {
            if (dVentas[i] != null && dVentas[i].getIdDVenta() == obj.getIdDVenta()) {
                Nodo temp = IDaoContiene.inicio;
                byte stockActualDVenta = (byte) dVentas[i].getCantidad();
                byte stockActualizar = (byte) obj.getCantidad();
                byte stockCalcular = (byte) (stockActualizar - stockActualDVenta);
                if (stockCalcular > 0) {
                    while (temp != null) {
                        if (temp.getContiene().getIdContiene() == obj.getIdProducto()) {
                            byte stockContiene = temp.getContiene().getStock();
                            if (stockCalcular > stockContiene) {
                                JOptionPane.showMessageDialog(null, "La cantidad ingresada es mayor al stock que hay. ("
                                        + temp.getContiene().getStock() + ")");
                                return false;
                            } else {
                                stockContiene = (byte) (temp.getContiene().getStock() - stockCalcular);
                                if (IDaoContiene.actualizar(new Contiene(temp.getContiene().getIdContiene(),
                                        temp.getContiene().getIdProducto(), temp.getContiene().getIdTalla(),
                                        temp.getContiene().getIdColor(), temp.getContiene().getIdMarca(),
                                        temp.getContiene().getPrecio(), stockContiene))) {
                                    dVentas[i] = obj;
                                    IDaoContiene.guardarEnArchivo();
                                    return true;
                                }
                                return false;
                            }
                        }
                        temp = temp.siguiente;
                    }

                } else if (stockCalcular < 0) {
                    while (temp != null) {
                        if (temp.getContiene().getIdContiene() == obj.getIdProducto()) {
                            byte stockContiene = temp.getContiene().getStock();
                            stockContiene = (byte) (temp.getContiene().getStock() + Math.abs(stockCalcular));
                            if (IDaoContiene.actualizar(new Contiene(temp.getContiene().getIdContiene(),
                                    temp.getContiene().getIdProducto(), temp.getContiene().getIdTalla(),
                                    temp.getContiene().getIdColor(), temp.getContiene().getIdMarca(),
                                    temp.getContiene().getPrecio(), stockContiene))) {
                                dVentas[i] = obj;
                                IDaoContiene.guardarEnArchivo();
                                return true;
                            }
                            return false;
                        }
                        temp = temp.siguiente;
                    }
                } else {
                    dVentas[i] = obj;
                    return true;
                }
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
                    writer.write(dVentas[i].getIdDVenta() + ";" + dVentas[i].getIdProducto() + ";"+ 
                            dVentas[i].getCantidad() + ";" + dVentas[i].getPrecio()
                            + ";" + dVentas[i].getTotal() + ";" + dVentas[i].getIdVenta());
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
                    int cantidad = Integer.parseInt(datos[2]);
                    float precio = Float.parseFloat(datos[3]);
                    float total = Float.parseFloat(datos[4]);
                    int idVenta = Integer.parseInt(datos[5]);
                    this.enqueue(new DetalleVenta(idDVenta, id, cantidad, precio, total, idVenta));
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al cargar el archivo " + FILE_DVENTA, "ERROR",
                        JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showConfirmDialog(null, "Error al crear el archivo idsproductos", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        // Leer el ultimo codigo del archivo
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
            JOptionPane.showConfirmDialog(null, "Error al obtener el ultimo ID de productos", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }

    @Override
    public boolean agregar(DetalleVenta obj) {
        if (size < dVentas.length) {
            Nodo temp = IDaoContiene.inicio;
            while (temp != null) {
                if (temp.getContiene().getIdContiene() == obj.getIdProducto()) {
                    byte stockContiene = temp.getContiene().getStock();
                    if (obj.getCantidad() > stockContiene) {
                        JOptionPane.showMessageDialog(null, "La cantidad ingresada es mayor al stock que hay.");
                        return false;
                    } else {
                        stockContiene = (byte) (temp.getContiene().getStock() - obj.getCantidad());
                        if (IDaoContiene.actualizar(new Contiene(temp.getContiene().getIdContiene(),
                                temp.getContiene().getIdProducto(), temp.getContiene().getIdTalla(),
                                temp.getContiene().getIdColor(), temp.getContiene().getIdMarca(),
                                temp.getContiene().getPrecio(), stockContiene))) {
                            try (BufferedWriter codigos = new BufferedWriter(new FileWriter(FILE_IDSDVENTA, true))) {
                                codigos.write(obj.getIdDVenta() + "\n");
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(null, "Error al agregar el codigo de detalle de venta",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                                IDaoContiene.actualizar(new Contiene(temp.getContiene().getIdContiene(),
                                        temp.getContiene().getIdProducto(), temp.getContiene().getIdTalla(),
                                        temp.getContiene().getIdColor(), temp.getContiene().getIdMarca(),
                                        temp.getContiene().getPrecio(), temp.getContiene().getStock()));
                                return false;
                            }
                            dVentas[end] = obj;
                            end = (end + 1) % dVentas.length;
                            size++;
                            IDaoContiene.guardarEnArchivo();
                            return true;
                        }
                        return false;
                    }
                }
                temp = temp.siguiente;
            }
        }
        JOptionPane.showMessageDialog(null, "La cola esta llena.");
        return false;
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
            if (dv != null) { // Verificaciï¿½n para evitar el NullPointerException
                boolean coincideConId = String.valueOf(dv.getIdProducto()).contains(valorBuscar);
                String producto = idaoProducto.obtenerNombre(dv.getIdProducto());
                boolean coincideConProducto = producto != null && producto.contains(valorBuscar);

                if (coincideConId || coincideConProducto) {
                    resultado.add(dv);
                }
            }
        }
        return resultado;

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

    public void clearIdVenta(Venta Obj) {
    for (int i = 0; i < dVentas.length; i++) {
        if (dVentas[i] != null && dVentas[i].getIdVenta() == Obj.getIdVenta()) {
            System.out.println("Elemento eliminado en índice: " + i);

            // Desplazar todos los elementos posteriores hacia la izquierda
            for (int j = i; j < dVentas.length - 1; j++) {
                dVentas[j] = dVentas[j + 1];
            }

            // Asignar null al último elemento, que queda sin uso
            dVentas[dVentas.length - 1] = null;

            // Salir del bucle ya que el objeto fue encontrado y eliminado
            break;
        }
    }
}


    public List<DetalleVenta> listarPorIdVenta(int idVenta) {
        List<DetalleVenta> detallesVenta = new LinkedList<>();

        for (DetalleVenta dv : dVentas) {
            if (dv != null && dv.getIdVenta() == idVenta) {
                detallesVenta.add(dv);
            }
        }

        return detallesVenta;
    }
}
