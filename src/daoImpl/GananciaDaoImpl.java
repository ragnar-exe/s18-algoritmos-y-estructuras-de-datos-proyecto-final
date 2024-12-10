package daoImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import model.Nodo;
import model.Venta;

public class GananciaDaoImpl {
    public Nodo nodoGananciaPrincipal = null;

    public static void preOrder(Nodo nodo_root) {
        if (nodo_root != null) {
            System.out.print(nodo_root.getVenta());
            preOrder(nodo_root.getIzquierdo());
            preOrder(nodo_root.getDerecho());
        }
    }

    public static void inOrder(Nodo nodo_root) {
        
    }

    public static void postOrder(Nodo nodo_root) {
        if (nodo_root != null) {
            postOrder(nodo_root.getIzquierdo());
            postOrder(nodo_root.getDerecho());
            System.out.print(nodo_root.getVenta());
        }
    }

    public void cargarDatosVenta() {
        File fileGanancia = new File(VentaDaoImpl.FILE_VENTAS);
        nodoGananciaPrincipal = null;
        int asignar = 0;
        if (fileGanancia.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileGanancia))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(";");
                    int id = Integer.parseInt(datos[0]);
                    String fecha = datos[1];
                    int idCliente = Integer.parseInt(datos[2]);
                    float subTotal = Float.parseFloat(datos[3]);
                    float impuesTotal = Float.parseFloat(datos[4]);
                    float total = Float.parseFloat(datos[5]);
                    boolean estado = Boolean.parseBoolean(datos[6]);
                    Venta venta = new Venta(idCliente, idCliente, total, subTotal, impuesTotal, fecha, estado);
                    if (nodoGananciaPrincipal == null) {
                        nodoGananciaPrincipal = new Nodo(venta);
                        asignar++;
                    } else {
                        insertarEnArbol(nodoGananciaPrincipal, new Nodo(venta), asignar);
                    }
                }
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al cargar las ventas para la ganancias.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void insertarEnArbol(Nodo root, Nodo nuevoNodo, int asignar) {
        if (asignar % 2 == 0) {
            if (root.getIzquierdo() == null) {
                root.setIzquierdo(nuevoNodo);
            } else {
                insertarEnArbol(root.getIzquierdo(), nuevoNodo, asignar / 2);
            }
        } else {
            if (root.getDerecho() == null) {
                root.setDerecho(nuevoNodo);
            } else {
                insertarEnArbol(root.getDerecho(), nuevoNodo, asignar / 2);
            }
        }
    }
}
