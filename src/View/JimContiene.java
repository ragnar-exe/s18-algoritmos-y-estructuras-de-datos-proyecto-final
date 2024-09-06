package View;

import dao.IDaoExtendido;
import dao.IDaoGenerico;
import daoImpl.CategoriaDaoImpl;
import daoImpl.ColorDaoImpl;
import daoImpl.ContieneDaoImpl;
import daoImpl.MarcaDaoImpl;
import daoImpl.ProductoDaoImpl;
import daoImpl.TallaDaoImpl;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Categoria;
import model.Color;
import model.Contiene;
import model.Marca;
import model.Producto;
import model.Talla;
import dao.IDaoObtenerId;

public class JimContiene extends javax.swing.JInternalFrame {

    private IDaoGenerico<Contiene> crudContiene;
    private IDaoObtenerId<Producto> iDaoProducto;
    private IDaoExtendido<Categoria> iDaoCategoria;
    private IDaoExtendido<Color> iDaoColor;
    private IDaoExtendido<Marca> iDaoMarca;
    private IDaoExtendido<Talla> iDaoTalla;
    private DefaultTableModel modelo;
    private Object[] filaDatos;
    private int idContiene;
    private boolean guardar = false;

    public JimContiene() {
        initComponents();
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setSize(ancho, alto - 106);
        crudContiene = new ContieneDaoImpl();
        iDaoProducto = new ProductoDaoImpl();
        iDaoCategoria = new CategoriaDaoImpl();
        iDaoMarca = new MarcaDaoImpl();
        filaDatos = new Object[8];
        iDaoColor = new ColorDaoImpl();
        iDaoTalla = new TallaDaoImpl();
        modelo = new DefaultTableModel();
        cargarProductos();
        cargarMarcas();
        cargarTallas();
        cargarColores();
        limpiarTabla();
        listarStockProductos();
        limpiarCampos();
        habilitarCampo(false);
        crudBotones(false);
        registroBotones(false);
    }

    private void cargarProductos() {
        cboProducto.addItem("Seleccionar");
        for (Producto c : iDaoProducto.listar()) {
            cboProducto.addItem(c.getNombre());
        }
    }

    private void cargarMarcas() {
        cboMarca.addItem("Seleccionar");
        for (Marca m : iDaoMarca.listar()) {
            cboMarca.addItem(m.getNombre());
        }
    }

    private void cargarTallas() {
        cboTalla.addItem("Seleccionar");
        for (Talla c : iDaoTalla.listar()) {
            cboTalla.addItem(String.valueOf(c.getNumero()));
        }
    }

    private void cargarColores() {
        cboColor.addItem("Seleccionar");
        for (Color c : iDaoColor.listar()) {
            cboColor.addItem(c.getNombre());
        }
    }

    private void listarStockProductos() {
        modelo = (DefaultTableModel) tblStockProducto.getModel();
        for (Contiene c : crudContiene.listar()) {
            filaDatos[0] = c.getIdContiene();
            filaDatos[1] = iDaoProducto.obtenerNombre(c.getIdProducto());
            filaDatos[2] = iDaoCategoria.obtenerNombre(iDaoProducto.obtenerIdForeignKey(c.getIdProducto()));
            filaDatos[3] = iDaoMarca.obtenerNombre(c.getIdMarca());
            filaDatos[4] = iDaoTalla.obtenerNombre(c.getIdTalla());
            filaDatos[5] = iDaoColor.obtenerNombre(c.getIdColor());
            filaDatos[6] = c.getPrecio();
            filaDatos[7] = c.getStock();
            modelo.addRow(filaDatos);
        }
        if (crudContiene.total() > 1) {
            txtBuscar.setEnabled(true);
        } else {
            txtBuscar.setEnabled(false);
        }
    }

    private void limpiarTabla() {
        modelo = (DefaultTableModel) tblStockProducto.getModel();
        modelo.getDataVector().removeAllElements();
        tblStockProducto.removeAll();
    }

    private void registroBotones(boolean f) {
        btnGuardar.setEnabled(f);
        btnCancelar.setEnabled(f);
    }

    private void crudBotones(boolean f) {
        btnNuevo.setEnabled(!f);
        btnEditar.setEnabled(f);
        btnEliminar.setEnabled(f);
    }

    private void limpiarCampos() {
        cboProducto.setSelectedIndex(0);
        cboMarca.setSelectedIndex(0);
        cboTalla.setSelectedIndex(0);
        cboColor.setSelectedIndex(0);
        txtPrecio.setText("");
        txtStock.setText("");
    }

    private void habilitarCampo(boolean f) {
        cboProducto.setEnabled(f);
        cboMarca.setEnabled(f);
        cboTalla.setEnabled(f);
        cboColor.setEnabled(f);
        txtPrecio.setEnabled(f);
        txtStock.setEnabled(f);
    }

    private void buscarCampo(boolean f) {
        txtBuscar.setText("");
        txtBuscar.setEnabled(f);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboMarca = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cboColor = new javax.swing.JComboBox<>();
        txtPrecio = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboTalla = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblStockProducto = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        cboProducto = new javax.swing.JComboBox<>();
        txtStock = new javax.swing.JTextField();
        lblMensaje = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel4.setText("Registro de Stock de Productos");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Marca:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, -1, 30));

        getContentPane().add(cboMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 290, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Color:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, -1, 30));

        getContentPane().add(cboColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 100, 280, 30));
        getContentPane().add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 140, 280, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Precio");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Producto:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, -1, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Talla:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 40, 30));

        getContentPane().add(cboTalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 290, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Buscar:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, 30));

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        getContentPane().add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, 350, 30));

        tblStockProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Categoría", "Marca", "Talla", "Color", "Precio", "Cantidad"
            }
        ));
        tblStockProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblStockProductoMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tblStockProducto);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 1090, 210));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Stock:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 180, -1, -1));

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, 90, 30));

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 90, 30));

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 310, 90, 32));

        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 310, 90, 33));

        btnNuevo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        getContentPane().add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 220, 90, 30));

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setText("Guardar");
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 580, 90, -1));

        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton8.setText("Cancelar");
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 580, -1, -1));

        cboProducto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(cboProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 290, 30));
        getContentPane().add(txtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 180, 280, 30));

        lblMensaje.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(lblMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, 390, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (cboProducto.getSelectedItem().equals("Seleccionar")) {
            JOptionPane.showMessageDialog(null,
                    "Advertencia, Debe seleccionar una producto.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            cboProducto.requestFocus();
            return;
        }
        
        if (cboMarca.getSelectedItem().equals("Seleccionar")) {
            JOptionPane.showMessageDialog(null,
                    "Advertencia, Debe seleccionar una marca.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            cboMarca.requestFocus();
            return;
        }
        
        if (cboTalla.getSelectedItem().equals("Seleccionar")) {
            JOptionPane.showMessageDialog(null,
                    "Advertencia, Debe seleccionar una marca.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            cboTalla.requestFocus();
            return;
        }
        
        if (cboColor.getSelectedItem().equals("Seleccionar")) {
            JOptionPane.showMessageDialog(null,
                    "Advertencia, Debe seleccionar una marca.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            cboColor.requestFocus();
            return;
        }
        
        if (txtPrecio.getText().strip().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Advertencia, Debe ingresar un precio.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            cboMarca.requestFocus();
            return;
        }
        
        float precio;
        try {
            precio = Float.parseFloat(txtPrecio.getText().strip());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                        "Advertencia, el precio debe ser numeros decimales.",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
            txtPrecio.requestFocus();
            return;
        }
        
        byte stock;
        try {
            stock = Byte.parseByte(txtStock.getText().strip());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                        "Advertencia, el precio debe ser numeros decimales.",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
            txtStock.requestFocus();
            return;
        }
        
        int idProducto = iDaoProducto.obtenerId(cboProducto.getSelectedItem().toString());
        int idTalla = iDaoTalla.obtenerId(cboTalla.getSelectedItem().toString());
        int idColor = iDaoColor.obtenerId(cboColor.getSelectedItem().toString());
        int idMarca = iDaoMarca.obtenerId(cboMarca.getSelectedItem().toString());
        if (guardar) {
                if (crudContiene.actualizar(new Contiene(idContiene, idProducto, idTalla, idColor, idMarca, precio, stock))) {
                    lblMensaje.setText("Se actualizo correctamente el registro de stock con id " + idContiene + ".");
                    limpiarCampos();
                    habilitarCampo(false);
                    registroBotones(false);
                    crudBotones(false);
                    guardar = false;
                } else {
                    lblMensaje.setText("No se actualizo el registro de stock.");
                }
            } else {
                if (crudContiene.agregar(new Contiene(idProducto, idTalla, idColor, idMarca, precio, stock))) {
                    lblMensaje.setText("Se agrego correctamente el registro de stock.");
                    limpiarCampos();
                    habilitarCampo(false);
                    registroBotones(false);
                    crudBotones(false);
                } else {
                    lblMensaje.setText("No se agrego el registro de stock.");
                }
            }
            tblStockProducto.clearSelection();
            limpiarTabla();
            listarStockProductos();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblStockProducto.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        } else {
            if (JOptionPane.showConfirmDialog(null, "Desea eliminar el registro", "Eliminar", JOptionPane.YES_NO_OPTION, 3) == 0) {
                if (crudContiene.eliminar(new Contiene(idContiene))) {
                    lblMensaje.setText("El registro se eliminó correctamente");
                } else {
                    lblMensaje.setText("El registro NO se pudo eliminar");
                }
            }
            buscarCampo(true);
            limpiarTabla();
            listarStockProductos();
            limpiarCampos();
            registroBotones(false);
            crudBotones(false);
            tblStockProducto.clearSelection();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        habilitarCampo(true);
        limpiarCampos();
        limpiarTabla();
        buscarCampo(false);
        cboProducto.requestFocus();
        registroBotones(true);
        crudBotones(false);
        btnNuevo.setEnabled(false);
        lblMensaje.setText("");
        listarStockProductos();
        tblStockProducto.clearSelection();
        guardar = false;
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarCampos();
        habilitarCampo(false);
        crudBotones(false);
        registroBotones(false);
        buscarCampo(false);
        limpiarTabla();
        listarStockProductos();
        tblStockProducto.clearSelection();
        guardar = false;
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        guardar = true;
        buscarCampo(false);
        habilitarCampo(true);
        cboProducto.requestFocus();
        crudBotones(false);
        registroBotones(true);
        btnNuevo.setEnabled(false);
        lblMensaje.setText("");
        tblStockProducto.clearSelection();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void tblStockProductoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStockProductoMouseReleased
        int fila = tblStockProducto.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null,
                    "Debe seleccionar una fila.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            habilitarCampo(false);
            idContiene = Integer.parseInt(tblStockProducto.getValueAt(fila, 0).toString());
            cboProducto.setSelectedItem(tblStockProducto.getValueAt(fila, 1).toString());
            cboMarca.setSelectedItem(tblStockProducto.getValueAt(fila, 3).toString());
            cboTalla.setSelectedItem(tblStockProducto.getValueAt(fila, 4).toString());
            cboColor.setSelectedItem(tblStockProducto.getValueAt(fila, 5).toString());
            txtPrecio.setText(tblStockProducto.getValueAt(fila, 6).toString());
            txtStock.setText(tblStockProducto.getValueAt(fila, 7).toString());
            lblMensaje.setText("");
            buscarCampo(false);
            registroBotones(false);
            crudBotones(true);
            btnCancelar.setEnabled(true);
        }
    }//GEN-LAST:event_tblStockProductoMouseReleased

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        limpiarTabla();
        int n = 0;
        modelo = (DefaultTableModel) tblStockProducto.getModel();
        String valorBuscar = txtBuscar.getText().strip();
        if (valorBuscar.equalsIgnoreCase("")) {
            limpiarTabla();
            listarStockProductos();
            lblMensaje.setText("");
        } else {
            for (Contiene co : crudContiene.listar(valorBuscar)) {
                filaDatos[0] = co.getIdContiene();
                filaDatos[1] = iDaoProducto.obtenerNombre(co.getIdProducto());
                filaDatos[2] = iDaoCategoria.obtenerNombre(iDaoProducto.obtenerIdForeignKey(co.getIdProducto()));
                filaDatos[3] = iDaoMarca.obtenerNombre(co.getIdMarca());
                filaDatos[4] = iDaoTalla.obtenerNombre(co.getIdTalla());
                filaDatos[5] = iDaoColor.obtenerNombre(co.getIdColor());
                filaDatos[6] = co.getPrecio();
                filaDatos[7] = co.getStock();
                modelo.addRow(filaDatos);
                n++;
            }
            lblMensaje.setText(n + " registros encontrados.");
        }
        limpiarCampos();
    }//GEN-LAST:event_txtBuscarKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cboColor;
    private javax.swing.JComboBox<String> cboMarca;
    private javax.swing.JComboBox<String> cboProducto;
    private javax.swing.JComboBox<String> cboTalla;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JTable tblStockProducto;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}
