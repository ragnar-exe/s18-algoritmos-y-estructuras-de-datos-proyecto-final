package daoImpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import model.Categoria;
import model.Contiene;
import model.Nodo;

public class StockDaoImpl {
    private static final String FILE_STOCKS = "stocks.txt";
    private static final String FILE_IDSSTOCKS = "idsstocks.txt";
    public Nodo NodoPrincipal = null;

    public StockDaoImpl() {
    }
    
    public static void preOrderValue(Nodo nodo_root) {
        if (nodo_root != null) {
            System.out.printf("%s ", nodo_root.getContiene());
            preOrderValue(nodo_root.getLchild());
            preOrderValue(nodo_root.getRchild());
        }
    }
    
    public static void preOrderKey(Nodo nodo_root) {
        if (nodo_root != null) {
            System.out.printf("%d - ", nodo_root.getKey());
            preOrderKey(nodo_root.getLchild());
            preOrderKey(nodo_root.getRchild());
            
        }
    }
    
    public static void inOrderValue(Nodo nodo_root) {
        if (nodo_root != null) {
            inOrderValue(nodo_root.getLchild());
            System.out.printf("%s", nodo_root.getContiene());
            preOrderValue(nodo_root.getRchild());
            
        }
    }
    
    public static void inOrderKey(Nodo nodo_root) {
        if (nodo_root != null) {
            inOrderKey(nodo_root.getLchild());
            System.out.printf("%d - ", nodo_root.getKey());
            inOrderKey(nodo_root.getRchild());
            
        }
    }  
    
    public static void postOrderValue(Nodo nodo_root) {
        postOrderValue(nodo_root.getLchild());
        postOrderValue(nodo_root.getRchild());
        System.out.printf("%s "+nodo_root.getContiene());
        
    }
    
    public static void postOrderKey(Nodo nodo_root) {
        if (nodo_root != null) {
            postOrderKey(nodo_root.getLchild());
            postOrderKey(nodo_root.getRchild());
            System.out.printf("%d - ",nodo_root.getKey());
        }
    }
    
    public static Nodo insertar(Nodo root, int key, Contiene contien) {
        if (root == null ) {
            root = new Nodo(key, contien);
        } else if (key < root.getKey()) {
            root.setLchild(insertar(root.getLchild(), key, contien));
        } else {
            root.setRchild(insertar(root.getRchild(), key, contien));
        }
        return root;
    }
    
    public static Nodo buscar(Nodo root, int key) {
        if (root == null || key == root.getKey()) {
            return root;
        } 
        if (key < root.getKey()) {
            return buscar(root.getLchild(), key);
        }
        return buscar(root.getRchild(), key);
    }
    
    public static Nodo eliminar(Nodo root, int key) {
        if (root == null ) {
            return root;
        }
        
        if (key < root.getKey()) {
            root.setLchild(eliminar(root.getLchild(), key));
        } else if (key > root.getKey()) {
            root.setRchild(eliminar(root.getRchild(), key));
        } else {
            if (root.getLchild() == null && root.getRchild() == null) {
                return null;
            } else if (root.getLchild() == null) {
                return root.getRchild();
            } else if (root.getRchild() == null ){
                return root.getLchild();
            }
            
            Nodo temp = minValorNodo(root.getRchild());
            root.setKey(temp.getKey());
            root.setContiene(temp.getContiene());
            root.setRchild(eliminar(root.getRchild(), temp.getKey()));
        }
        return root;
    }
    
    public static Nodo minValorNodo(Nodo root) {
        Nodo menor = root;
        while (menor != null && menor.getLchild() != null) {
            menor = menor.getLchild();
        }
        return menor;
    }
    
    public static Nodo insertarCarga(Nodo root, int key, Contiene contien) {
        if (root == null ) {
            root = new Nodo(key, contien);
        } else if (key < root.getKey()) {
            root.setLchild(insertar(root.getLchild(), key, contien));
        } else {
            root.setRchild(insertar(root.getRchild(), key, contien));
        }
        return root;
    }
    
    public static Nodo insertarCargaStockMenor(Nodo root, int key, Contiene contien) {
        if (root == null ) {
            root = new Nodo(key, contien);
        } else if (key < root.getKey()) {
            root.setLchild(insertar(root.getLchild(), key, contien));
        } else {
            root.setRchild(insertar(root.getRchild(), key, contien));
        }
        return root;
    }
    
    
    public void cargarDatos() {
        File fileStocks = new File(FILE_STOCKS);
        Nodo root = null;
        if (fileStocks.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileStocks))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(";");
                    int idContiene = Integer.parseInt(datos[0].strip());
                    int idProducto = Integer.parseInt(datos[1].strip());
                    int idTalla = Integer.parseInt(datos[2].strip());
                    int idColor = Integer.parseInt(datos[3].strip());
                    int idMarca = Integer.parseInt(datos[4].strip());
                    float precio = Float.parseFloat(datos[5].strip());
                    byte stock = Byte.parseByte(datos[6].strip());
                    Contiene lineaContiene = new Contiene(idContiene, idProducto, idTalla, idColor, idMarca, precio, stock);
                    if (root == null ) {
                        root = insertarCarga(root, stock, lineaContiene);
                    } else {
                        insertarCarga(root, stock, lineaContiene);
                    }
                }
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al cargar los contiene", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void cargarDatosContiene() {
        File fileContiene = new File(ContieneDaoImpl.FILE_CONTIENES);
        NodoPrincipal = null;
        if (fileContiene.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileContiene))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(";");
                    int idContiene = Integer.parseInt(datos[0].strip());
                    int idProducto = Integer.parseInt(datos[1].strip());
                    int idTalla = Integer.parseInt(datos[2].strip());
                    int idColor = Integer.parseInt(datos[3].strip());
                    int idMarca = Integer.parseInt(datos[4].strip());
                    float precio = Float.parseFloat(datos[5].strip());
                    byte stock = Byte.parseByte(datos[6].strip());
                    if (NodoPrincipal == null) {
                        NodoPrincipal = insertar(NodoPrincipal, idContiene, new Contiene(idContiene, idProducto, idTalla, idColor, idMarca, precio, stock));
                    } else {
                        insertar(NodoPrincipal, idContiene, new Contiene(idContiene, idProducto, idTalla, idColor, idMarca, precio, stock));
                    }
                }
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al cargar los contiene", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_STOCKS))) {
//            Nodo temp = inicio;
//            while (temp != null) {
//                writer.write(temp.getContiene().getIdContiene() + ";" + temp.getContiene().getIdProducto() + ";" + temp.getContiene().getIdTalla() + ";" + temp.getContiene().getIdColor() + ";" + temp.getContiene().getIdMarca() + ";" + temp.getContiene().getPrecio() + ";" + temp.getContiene().getStock());
//                writer.newLine();
//                temp = temp.getSiguiente();
//            }
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, "Error al guardar los contiene", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
