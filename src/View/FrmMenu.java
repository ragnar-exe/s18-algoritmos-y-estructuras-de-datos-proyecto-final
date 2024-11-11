package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

public class FrmMenu extends javax.swing.JFrame {

    public static JDesktopPane jDesktopPane_menu;

    public void mostrarVistaStock() {
        JimStock tablaStock = new JimStock();
        jDesktopPane_menu.add(tablaStock);
        tablaStock.setVisible(true);
    }

    public FrmMenu() {
        initComponents();
        this.setSize(new Dimension(1200, 700));
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setTitle("Novedades Isidora");

        this.setLayout(null);
        jDesktopPane_menu = new JDesktopPane();
        this.getContentPane().add(jDesktopPane_menu, BorderLayout.CENTER); // Agregar al contenedor principal

        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

        this.jDesktopPane_menu.setBounds(0, 0, ancho, (alto - 106));
        this.add(jDesktopPane_menu);
        mostrarVistaStock();
    }
    public JDesktopPane getDesktopPane() {
        return jDesktopPane_menu;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        JmStock = new javax.swing.JMenu();
        JmiStock = new javax.swing.JMenuItem();
        JmiGanancias = new javax.swing.JMenuItem();
        JmiVendidos = new javax.swing.JMenuItem();
        JmVenta = new javax.swing.JMenu();
        JmCompra = new javax.swing.JMenu();
        JimProductos = new javax.swing.JMenu();
        JmRegistroProducto = new javax.swing.JMenuItem();
        JmRegistroStock = new javax.swing.JMenuItem();
        JmTalla = new javax.swing.JMenu();
        JmCategorias = new javax.swing.JMenu();
        JmCliente = new javax.swing.JMenu();
        JmColores = new javax.swing.JMenu();
        JmProveedores = new javax.swing.JMenu();
        JimMarca = new javax.swing.JMenu();
        FrmLogin = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JmStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/stock.png"))); // NOI18N
        JmStock.setText("STOCK");
        JmStock.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        JmStock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JmStock.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        JmStock.setMinimumSize(new java.awt.Dimension(140, 50));
        JmStock.setPreferredSize(new java.awt.Dimension(100, 40));
        JmStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JmStockMouseClicked(evt);
            }
        });

        JmiStock.setText("STOCK");
        JmiStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiStockActionPerformed(evt);
            }
        });
        JmStock.add(JmiStock);

        JmiGanancias.setText("GANANCIAS");
        JmiGanancias.setPreferredSize(new java.awt.Dimension(100, 22));
        JmiGanancias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiGananciasActionPerformed(evt);
            }
        });
        JmStock.add(JmiGanancias);

        JmiVendidos.setText("VENDIDOS");
        JmiVendidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiVendidosActionPerformed(evt);
            }
        });
        JmStock.add(JmiVendidos);

        jMenuBar1.add(JmStock);

        JmVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/venta.png"))); // NOI18N
        JmVenta.setText("VENTA");
        JmVenta.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        JmVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JmVenta.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        JmVenta.setMinimumSize(new java.awt.Dimension(140, 50));
        JmVenta.setPreferredSize(new java.awt.Dimension(100, 40));
        JmVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JmVentaMouseClicked(evt);
            }
        });
        jMenuBar1.add(JmVenta);

        JmCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Carrito-de-compras.png"))); // NOI18N
        JmCompra.setText("COMPRAS");
        JmCompra.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        JmCompra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JmCompra.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        JmCompra.setMinimumSize(new java.awt.Dimension(140, 50));
        JmCompra.setPreferredSize(new java.awt.Dimension(120, 40));
        JmCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JmCompraMouseClicked(evt);
            }
        });
        jMenuBar1.add(JmCompra);

        JimProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/producto.png"))); // NOI18N
        JimProductos.setText("PRODUCTOS");
        JimProductos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        JimProductos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JimProductos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        JimProductos.setMinimumSize(new java.awt.Dimension(140, 50));
        JimProductos.setPreferredSize(new java.awt.Dimension(130, 40));
        JimProductos.setRequestFocusEnabled(false);

        JmRegistroProducto.setText("REGISTRO DE PRODUCTOS");
        JmRegistroProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmRegistroProductoActionPerformed(evt);
            }
        });
        JimProductos.add(JmRegistroProducto);

        JmRegistroStock.setText("REGISTRO DE  STOCK");
        JmRegistroStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmRegistroStockActionPerformed(evt);
            }
        });
        JimProductos.add(JmRegistroStock);

        jMenuBar1.add(JimProductos);

        JmTalla.setText("TALLA");
        JmTalla.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        JmTalla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JmTalla.setPreferredSize(new java.awt.Dimension(100, 40));
        JmTalla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JmTallaMouseClicked(evt);
            }
        });
        jMenuBar1.add(JmTalla);

        JmCategorias.setText("CATEGORIAS");
        JmCategorias.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        JmCategorias.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JmCategorias.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JmCategorias.setMinimumSize(new java.awt.Dimension(140, 50));
        JmCategorias.setPreferredSize(new java.awt.Dimension(105, 40));
        JmCategorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JmCategoriasMouseClicked(evt);
            }
        });
        jMenuBar1.add(JmCategorias);

        JmCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Clientes.png"))); // NOI18N
        JmCliente.setText("CLIENTES");
        JmCliente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        JmCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JmCliente.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        JmCliente.setMinimumSize(new java.awt.Dimension(140, 50));
        JmCliente.setName(""); // NOI18N
        JmCliente.setPreferredSize(new java.awt.Dimension(120, 40));
        JmCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JmClienteMouseClicked(evt);
            }
        });
        jMenuBar1.add(JmCliente);

        JmColores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/torta.png"))); // NOI18N
        JmColores.setText("COLORES");
        JmColores.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        JmColores.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JmColores.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        JmColores.setMinimumSize(new java.awt.Dimension(140, 50));
        JmColores.setPreferredSize(new java.awt.Dimension(125, 40));
        JmColores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JmColoresMouseClicked(evt);
            }
        });
        jMenuBar1.add(JmColores);

        JmProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/proveedor.png"))); // NOI18N
        JmProveedores.setText("PROVEEDORES");
        JmProveedores.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        JmProveedores.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JmProveedores.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        JmProveedores.setMinimumSize(new java.awt.Dimension(138, 50));
        JmProveedores.setName(""); // NOI18N
        JmProveedores.setPreferredSize(new java.awt.Dimension(160, 40));
        JmProveedores.setRolloverEnabled(false);
        JmProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JmProveedoresMouseClicked(evt);
            }
        });
        jMenuBar1.add(JmProveedores);

        JimMarca.setText("MARCA");
        JimMarca.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        JimMarca.setMinimumSize(new java.awt.Dimension(58, 50));
        JimMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JimMarcaMouseClicked(evt);
            }
        });
        jMenuBar1.add(JimMarca);

        FrmLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrar-s.png"))); // NOI18N
        FrmLogin.setText("CERRAR SESION");
        FrmLogin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        FrmLogin.setPreferredSize(new java.awt.Dimension(140, 40));
        FrmLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FrmLoginMouseClicked(evt);
            }
        });
        jMenuBar1.add(FrmLogin);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JmVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JmVentaMouseClicked
        // TODO add your handling code here:
        JimVenta vistaVentas = new JimVenta();
        jDesktopPane_menu.add(vistaVentas);
        vistaVentas.setVisible(true);
    }//GEN-LAST:event_JmVentaMouseClicked

    private void JmStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JmStockMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_JmStockMouseClicked

    private void JmiStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiStockActionPerformed
        mostrarVistaStock();
    }//GEN-LAST:event_JmiStockActionPerformed

    private void JmiGananciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiGananciasActionPerformed
        // TODO add your handling code here:
        JimGanancia tablaGanancias = new JimGanancia();
        jDesktopPane_menu.add(tablaGanancias);
        tablaGanancias.setVisible(true);
    }//GEN-LAST:event_JmiGananciasActionPerformed

    private void JmiVendidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiVendidosActionPerformed
        // TODO add your handling code here:
        JimVendido productosMasVendidos = new JimVendido();
        jDesktopPane_menu.add(productosMasVendidos);
        productosMasVendidos.setVisible(true);
    }//GEN-LAST:event_JmiVendidosActionPerformed

    private void JmCompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JmCompraMouseClicked
        // TODO add your handling code here:
        JimCompra vistaCompras = new JimCompra();
        jDesktopPane_menu.add(vistaCompras);
        vistaCompras.setVisible(true);
    }//GEN-LAST:event_JmCompraMouseClicked

    private void JmTallaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JmTallaMouseClicked
        JimTalla vistaTalla = new JimTalla();
        jDesktopPane_menu.add(vistaTalla);
        vistaTalla.setVisible(true);
    }//GEN-LAST:event_JmTallaMouseClicked

    private void JmCategoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JmCategoriasMouseClicked
        JimCategoria vistaCategorias = new JimCategoria();
        jDesktopPane_menu.add(vistaCategorias);
        vistaCategorias.setVisible(true);
    }//GEN-LAST:event_JmCategoriasMouseClicked

    private void JmClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JmClienteMouseClicked
        JimCliente vistaCliente = new JimCliente();
        jDesktopPane_menu.add(vistaCliente);
        vistaCliente.setVisible(true);
    }//GEN-LAST:event_JmClienteMouseClicked

    private void JmColoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JmColoresMouseClicked
        JimColor vistaColores = new JimColor();
        jDesktopPane_menu.add(vistaColores);
        vistaColores.setVisible(true);
    }//GEN-LAST:event_JmColoresMouseClicked

    private void JmProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JmProveedoresMouseClicked
        JimProveedor vistaProveedores = new JimProveedor();
        jDesktopPane_menu.add(vistaProveedores);
        vistaProveedores.setVisible(true);
    }//GEN-LAST:event_JmProveedoresMouseClicked

    private void FrmLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FrmLoginMouseClicked
        int option = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea cerrar sesión?", "Cerrar Sesión", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            this.dispose();
            FrmLogin login = new FrmLogin();
            login.setVisible(true);
        }
    }//GEN-LAST:event_FrmLoginMouseClicked

    private void JimMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JimMarcaMouseClicked
        JimMarca vistaMarcas = new JimMarca();
        jDesktopPane_menu.add(vistaMarcas);
        vistaMarcas.setVisible(true);
    }//GEN-LAST:event_JimMarcaMouseClicked

    private void JmRegistroStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmRegistroStockActionPerformed
        JimContiene vistaRegistroStock = new JimContiene();
        jDesktopPane_menu.add(vistaRegistroStock);
        vistaRegistroStock.setVisible(true);
    }//GEN-LAST:event_JmRegistroStockActionPerformed

    private void JmRegistroProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmRegistroProductoActionPerformed
        JimProducto vistaProductos = new JimProducto();
        jDesktopPane_menu.add(vistaProductos);
        vistaProductos.setVisible(true);
    }//GEN-LAST:event_JmRegistroProductoActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu FrmLogin;
    private javax.swing.JMenu JimMarca;
    private javax.swing.JMenu JimProductos;
    private javax.swing.JMenu JmCategorias;
    private javax.swing.JMenu JmCliente;
    private javax.swing.JMenu JmColores;
    private javax.swing.JMenu JmCompra;
    private javax.swing.JMenu JmProveedores;
    private javax.swing.JMenuItem JmRegistroProducto;
    private javax.swing.JMenuItem JmRegistroStock;
    private javax.swing.JMenu JmStock;
    private javax.swing.JMenu JmTalla;
    private javax.swing.JMenu JmVenta;
    private javax.swing.JMenuItem JmiGanancias;
    private javax.swing.JMenuItem JmiStock;
    private javax.swing.JMenuItem JmiVendidos;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}
