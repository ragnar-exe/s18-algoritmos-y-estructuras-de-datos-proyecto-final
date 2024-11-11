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
import model.Contiene;
import model.Nodo;

public class ContieneDaoImpl implements IDaoGenerico<Contiene>{
    private static final String FILE_CONTIENES = "contienes.txt";
    private static final String FILE_IDSCONTIENE = "idscontienes.txt";
    public Nodo inicio;
    public Nodo fin;
    
    public ContieneDaoImpl() {
        this.inicio = null;
        this.fin = null;
        cargarDatos();
    }

    @Override
    public int obtenerUltimoId() {
        int id = 1;

        // Verificar si el archivo existe
        if (!Files.exists(Paths.get(FILE_IDSCONTIENE))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_IDSCONTIENE))) {
                writer.write("0\n");
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al crear el archivo idscontiene", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Leer el último código del archivo
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_IDSCONTIENE))) {
            List<String> lines = Files.readAllLines(Paths.get(FILE_IDSCONTIENE));
            if (!lines.isEmpty()) {
                try {
                    int lastCode = Integer.parseInt(lines.get(lines.size() - 1).strip());
                    id = lastCode + 1;
                } catch (NumberFormatException e) {
                    id = 1;
                }
            }
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, "Error al obtener el ultimo ID de contiene", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }

    @Override
    public boolean agregar(Contiene obj) {
        // agregar al final
        boolean guardar = false;
        if (inicio == null) {
            inicio = new Nodo(null, null, obj);
            fin = inicio;
            
            guardar = true;
        } else {
            Nodo nuevo = new Nodo(null, fin, obj);
            fin.setSiguiente(nuevo);
            fin = nuevo;
            guardar = true;
        }
        if (guardar) {
            try (BufferedWriter codigos = new BufferedWriter(new FileWriter(FILE_IDSCONTIENE, true))) {
                codigos.write(obj.getIdContiene()+ "\n");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al agregar el codigo de contiene", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return guardar;
    }

    public boolean agregarInicio(Contiene obj) {
        boolean guardar = false;
        if (inicio == null) {
            inicio = new Nodo(null, null, obj);
            fin = inicio;
            guardar = true;
        } else {
            Nodo nuevo = new Nodo(inicio, null, obj);
            inicio.setAnterior(nuevo);
            inicio = nuevo;
            guardar = true;
        }
        if (guardar) {
            try (BufferedWriter codigos = new BufferedWriter(new FileWriter(FILE_IDSCONTIENE, true))) {
                codigos.write(obj.getIdContiene()+ "\n");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al agregar el codigo de contiene", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return guardar;
    }

    public boolean agregarPosicion(Contiene obj, int posicion) {
        if (posicion < 0) {
            JOptionPane.showMessageDialog(null, "Posicion invalida", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        Nodo check = inicio;
        while (check != null) {
                    if ( check.getContiene().getIdProducto() == obj.getIdProducto() &&
                    check.getContiene().getIdTalla() == obj.getIdTalla() &&
                    check.getContiene().getIdColor() == obj.getIdColor() &&
                    check.getContiene().getIdMarca() == obj.getIdMarca() &&
                    check.getContiene().getPrecio() == obj.getPrecio()) {
                        JOptionPane.showMessageDialog(null, 
            "El producto con estas caracteristica ya existe. \nSe procedio a aumentar el stock de ese producto \nCon id "+check.getContiene().getIdContiene()+" y stock "+check.getContiene().getStock(), "Error", JOptionPane.ERROR_MESSAGE);
                        byte stockActualizado = (byte) (check.getContiene().getPrecio() + obj.getStock());
                        //check.setContiene(obj);
                        System.out.println(stockActualizado);
                        return false;
                    }
            check = check.getSiguiente();
        }
        if (posicion == 0) {
            Nodo nuevo = new Nodo(inicio, null, obj);
            inicio.setAnterior(nuevo);
            inicio = nuevo;
            if (fin == null) {
                fin = inicio;
            }
            try (BufferedWriter codigos = new BufferedWriter(new FileWriter(FILE_IDSCONTIENE, true))) {
                codigos.write(obj.getIdContiene()+ "\n");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al agregar el codigo de contiene", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        } else {
            Nodo temp = inicio;
            int cont = 0;
            while (cont < posicion - 1) {
                temp = temp.getSiguiente();
                cont++;
            }
            Nodo nuevo = new Nodo(temp, temp.getSiguiente(), obj);
            temp.getSiguiente().setAnterior(nuevo);
            temp.setSiguiente(nuevo);
            try (BufferedWriter codigos = new BufferedWriter(new FileWriter(FILE_IDSCONTIENE, true))) {
                codigos.write(obj.getIdContiene()+ "\n");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al agregar el codigo de contiene", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        }
    }

    @Override
    public boolean actualizar(Contiene obj) {
        Nodo temp = inicio;
        while (temp != null) {
            if (temp.getContiene().getIdContiene() == obj.getIdContiene()) {
                Nodo check = inicio;
                while (check != null) {
                    if (check != temp && (check.getContiene().getIdProducto() == obj.getIdProducto() &&
                    check.getContiene().getIdTalla() == obj.getIdTalla() &&
                    check.getContiene().getIdColor() == obj.getIdColor() &&
                    check.getContiene().getIdMarca() == obj.getIdMarca() &&
                    check.getContiene().getPrecio() == obj.getPrecio())) {
                        JOptionPane.showMessageDialog(null, "El producto con estas caracteristica ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                    check = check.getSiguiente();
                }
                temp.setContiene(obj);
                return true;
            }
            temp = temp.getSiguiente();
        }
        return false;
    }

    @Override
    public boolean eliminar(Contiene obj) {
        boolean res = false;
        Nodo temp = inicio;
        while (temp != null) {
            if (temp.getContiene().getIdContiene() == obj.getIdContiene()) {
                if (temp == inicio) {
                    res = eliminarInicio();
                } else if (temp == fin) {
                    res = eliminarFinal();
                } else {
                    temp.getAnterior().setSiguiente(temp.getSiguiente());
                    temp.getSiguiente().setAnterior(temp.getAnterior());
                    res = true;
                }
            }
            temp = temp.getSiguiente();
        }
        return res;
    }

    public boolean eliminarInicio() {
        inicio = inicio.getSiguiente();
        boolean res = false;
        if (inicio != null) {
            inicio.setAnterior(null);
            res = true;
        } else {
            fin = null;
            res = true;
        }
        return res;
    }

    public boolean eliminarFinal() {
        fin = fin.getAnterior();
        boolean res = false;
        if (fin != null) {
            fin.setSiguiente(null);
            res = true;
        } else {
            inicio = null;
            res = true;
        }
        return res;
    }

    @Override
    public void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_CONTIENES))) {
            Nodo temp = inicio;
            while (temp != null) {
                writer.write(temp.getContiene().getIdContiene()+";"+temp.getContiene().getIdProducto()+";"+temp.getContiene().getIdTalla()+";"+temp.getContiene().getIdColor()+";"+temp.getContiene().getIdMarca() + ";" + temp.getContiene().getPrecio()+";"+temp.getContiene().getStock());
                writer.newLine();
                temp = temp.getSiguiente();
            }
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, "Error al guardar los contiene", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void cargarDatos() {
        File fileMarcas = new File(FILE_CONTIENES);
        if (fileMarcas.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileMarcas))) {
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
                    Nodo nuevo = new Nodo(null, null, new Contiene(idContiene,idProducto,idTalla,idColor,idMarca,precio,stock));
                    if (inicio == null) {
                        fin = nuevo;
                        inicio = fin;
                    } else {
                        fin.setSiguiente(nuevo);
                        nuevo.setAnterior(fin);
                        fin = nuevo;
                    }
                }
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al cargar los contiene", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public int total() {
        int total = 0;
        Nodo temp = inicio;
        while (temp != null) {
            temp.getMarca();
            temp = temp.getSiguiente();
            total++;
        }
        return total;
    }
    
}
