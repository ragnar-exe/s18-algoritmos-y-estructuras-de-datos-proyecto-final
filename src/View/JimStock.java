package View;

import dao.IDaoExtendido;
import dao.IDaoGenerico;
import daoImpl.CategoriaDaoImpl;
import daoImpl.ColorDaoImpl;
import daoImpl.ContieneDaoImpl;
import daoImpl.MarcaDaoImpl;
import daoImpl.ProductoDaoImpl;
import daoImpl.StockDaoImpl;
import daoImpl.TallaDaoImpl;
import javax.swing.JDesktopPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.Categoria;
import model.Color;
import model.Contiene;
import model.Marca;
import model.Nodo;
import model.Producto;
import model.Talla;

public class JimStock extends javax.swing.JInternalFrame {

    private StockDaoImpl crudStock;
    private ContieneDaoImpl crudContiene;
    private ProductoDaoImpl IDaoProducto;
    private CategoriaDaoImpl IDaoCategoria;
    private ColorDaoImpl IDaoColor;
    private MarcaDaoImpl IDaoMarca;
    private TallaDaoImpl IDaoTalla;
    private DefaultTableModel modelo;
    private Object[] filaDatos;

    public JimStock() {
        initComponents();
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setSize(ancho, alto - 106);
        crudStock = new StockDaoImpl();
        crudContiene = new ContieneDaoImpl();
        IDaoProducto = new ProductoDaoImpl();
        IDaoCategoria = new CategoriaDaoImpl();
        IDaoMarca = new MarcaDaoImpl();
        filaDatos = new Object[8];
        IDaoColor = new ColorDaoImpl();
        IDaoTalla = new TallaDaoImpl();
        modelo = new DefaultTableModel();
        limpiarTabla();
        crudStock.cargarDatosContiene();
//        preOrderKey(crudStock.NodoPrincipal);
        inOrderKey(crudStock.NodoPrincipal);
    }

    private void listarStockProductos() {
        modelo = (DefaultTableModel) tblStockProducto.getModel();
//        StockDaoImpl preOrderKey(Nodo nodo_root) {
//               if (nodo_root != null) {
//                   System.out.printf("%d - ", nodo_root.getKey());
//                   preOrderKey(nodo_root.getLchild());
//                   preOrderKey(nodo_root.getRchild());
//
//               }
//        }
        if (crudContiene.total() > 1) {
            txtBuscar.setEnabled(true);
        } else {
            txtBuscar.setEnabled(false);
        }
    }

    public void preOrderKey(Nodo nodo_root) {
        modelo = (DefaultTableModel) tblStockProducto.getModel();
        if (nodo_root != null) {
            filaDatos[0] = nodo_root.getContiene().getIdContiene();
            filaDatos[1] = IDaoProducto.obtenerNombre(nodo_root.getContiene().getIdProducto());
            filaDatos[2] = IDaoCategoria.obtenerNombre(IDaoProducto.obtenerCategoria(nodo_root.getContiene().getIdProducto()));
            filaDatos[3] = IDaoMarca.obtenerNombre(nodo_root.getContiene().getIdMarca());
            filaDatos[4] = IDaoTalla.obtenerNombre(nodo_root.getContiene().getIdTalla());
            filaDatos[5] = IDaoColor.obtenerNombre(nodo_root.getContiene().getIdColor());
            filaDatos[6] = nodo_root.getContiene().getPrecio();
            filaDatos[7] = nodo_root.getContiene().getStock();
            modelo.addRow(filaDatos);
            preOrderKey(nodo_root.getLchild());
            preOrderKey(nodo_root.getRchild());

        }
    }
    
    public void inOrderKey(Nodo nodo_root) {
        modelo = (DefaultTableModel) tblStockProducto.getModel();
        if (nodo_root != null) {
            inOrderKey(nodo_root.getLchild());
            filaDatos[0] = nodo_root.getContiene().getIdContiene();
            filaDatos[1] = IDaoProducto.obtenerNombre(nodo_root.getContiene().getIdProducto());
            filaDatos[2] = IDaoCategoria.obtenerNombre(IDaoProducto.obtenerCategoria(nodo_root.getContiene().getIdProducto()));
            filaDatos[3] = IDaoMarca.obtenerNombre(nodo_root.getContiene().getIdMarca());
            filaDatos[4] = IDaoTalla.obtenerNombre(nodo_root.getContiene().getIdTalla());
            filaDatos[5] = IDaoColor.obtenerNombre(nodo_root.getContiene().getIdColor());
            filaDatos[6] = nodo_root.getContiene().getPrecio();
            filaDatos[7] = nodo_root.getContiene().getStock();
            modelo.addRow(filaDatos);
            inOrderKey(nodo_root.getRchild());            
        }
    }

    private void limpiarTabla() {
        modelo = (DefaultTableModel) tblStockProducto.getModel();
        modelo.getDataVector().removeAllElements();
        tblStockProducto.removeAll();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnIrProductos = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblStockProducto = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblMensaje = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnIrProductos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnIrProductos.setText("Ver productos");
        btnIrProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIrProductosActionPerformed(evt);
            }
        });
        getContentPane().add(btnIrProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 160, -1, 31));

        tblStockProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Categoria", "Marca", "Talla", "Color", "Precio", "Stock"
            }
        ));
        jScrollPane4.setViewportView(tblStockProducto);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 1130, 240));

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        getContentPane().add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 156, 334, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel4.setText("Stock de Calzados");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Buscar:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 70, -1));

        lblMensaje.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(lblMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 446, 310, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIrProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIrProductosActionPerformed
        limpiarTabla();
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        FrmMenu menu = (FrmMenu) SwingUtilities.getWindowAncestor(this);  // Obtiene la ventana principal (FrmMenu)
        JDesktopPane jDesktopPane_menu = menu.getDesktopPane();  // Accede al JDesktopPane

        // Crear la vista de detalles de compra
        JimContiene vistaContiene = new JimContiene();

        // Asegúrate de que jDesktopPane_menu no sea null
        if (jDesktopPane_menu != null) {
            // Agregar el JInternalFrame (vistaStock) al JDesktopPane
            jDesktopPane_menu.add(vistaContiene);

            // Hacer visible el JInternalFrame
            vistaContiene.setVisible(true);

            // Establecer el JInternalFrame en el centro
            try {
                vistaContiene.setSelected(true);  // Asegura que esté al frente
            } catch (java.beans.PropertyVetoException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El JDesktopPane no está inicializado.");
        }
    }//GEN-LAST:event_btnIrProductosActionPerformed

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
//            for (Contiene co : crudContiene.listar(valorBuscar)) {
//                filaDatos[0] = co.getIdContiene();
//                filaDatos[1] = iDaoProducto.obtenerNombre(co.getIdProducto());
//                filaDatos[2] = iDaoCategoria.obtenerNombre(producto.obtenerIdForeignKey(co.getIdProducto()));
//                filaDatos[3] = iDaoMarca.obtenerNombre(co.getIdMarca());
//                filaDatos[4] = iDaoTalla.obtenerNombre(co.getIdTalla());
//                filaDatos[5] = iDaoColor.obtenerNombre(co.getIdColor());
//                filaDatos[6] = co.getPrecio();
//                filaDatos[7] = co.getStock();
//                modelo.addRow(filaDatos);
//                n++;
//            }
            lblMensaje.setText(n + " registros encontrados.");
        }
    }//GEN-LAST:event_txtBuscarKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIrProductos;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JTable tblStockProducto;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
