package View;

import dao.IDaoGenerico;
import daoImpl.MarcaDaoImpl;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Marca;

public class JimMarca extends javax.swing.JInternalFrame {

    private IDaoGenerico<Marca> crudMarca;
    private DefaultTableModel modelo;
    private Object[] filaDatos;
    private int idMarca;
    private boolean guardar = false;

    public JimMarca() {
        initComponents();
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setSize(ancho, alto - 106);
        filaDatos = new Object[2];
//        crudMarca = new MarcaDaoImpl();
        modelo = new DefaultTableModel();
        listarMarcas();
        habilitarCampo(false);
        crudBotones(false);
        registroBotones(false);
    }

    private void listarMarcas() {
        modelo = (DefaultTableModel) tblMarca.getModel();
//        for (Marca m : crudMarca.listar()) {
//            filaDatos[0] = m.getIdMarca();
//            filaDatos[1] = m.getNombre();
//            modelo.addRow(filaDatos);
//        }
        if (crudMarca.total() > 1) {
            txtBuscar.setEnabled(true);
        } else {
            txtBuscar.setEnabled(false);
        }
    }

    private void limpiarTabla() {
        modelo = (DefaultTableModel) tblMarca.getModel();
        modelo.getDataVector().removeAllElements();
        tblMarca.removeAll();
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

    private void habilitarCampo(boolean f) {
        txtNombre.setText("");
        txtNombre.setEnabled(f);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblMarca = new javax.swing.JTable();
        txtNombre = new javax.swing.JTextField();
        lblMensaje = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel4.setText("Marcas");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 40, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel11.setText("Nombre de la Marca:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, -1, 30));

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 87, 31));

        btnNuevo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        getContentPane().add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 180, -1, 31));

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 180, -1, 31));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Buscar:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, -1, -1));

        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 270, 70, 29));

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 270, 90, 29));

        tblMarca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Marca"
            }
        ));
        tblMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblMarcaMouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tblMarca);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 990, 213));
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, 323, 30));
        txtNombre.getAccessibleContext().setAccessibleName("");

        lblMensaje.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(lblMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, 320, 30));

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        getContentPane().add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 270, 324, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        crudBotones(false);
        registroBotones(false);
        txtNombre.setText("");
        txtNombre.setEnabled(false);
        txtBuscar.setEnabled(true);
        tblMarca.clearSelection();
        guardar = false;
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String title = txtNombre.getText().strip();
        if (title.length() > 0 && title.length() <= 50) {
            if (guardar) {
                if (crudMarca.actualizar(new Marca(idMarca, title))) {
                    lblMensaje.setText("Se actualizo correctamente la marca.");
                    habilitarCampo(false);
                    registroBotones(false);
                    crudBotones(false);
                    guardar = false;
                } else {
                    lblMensaje.setText("No se actualizo la marca.");
                }
            } else {
                if (crudMarca.agregar(new Marca(title))) {
                    lblMensaje.setText("Se agrego correctamente la marca.");
                    habilitarCampo(false);
                    registroBotones(false);
                    crudBotones(false);
                } else {
                    lblMensaje.setText("No se agrego la marca.");
                }
            }
            tblMarca.clearSelection();
            limpiarTabla();
            listarMarcas();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Advertencia, El nombre de la marca debe estar entre 1 y 50 letras.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        txtNombre.setText("");
        lblMensaje.setText("");
        txtNombre.setEnabled(true);
        registroBotones(true);
        crudBotones(false);
        btnNuevo.setEnabled(false);
        txtNombre.requestFocus();
        txtBuscar.setEnabled(false);
        tblMarca.clearSelection();
        guardar = false;
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void tblMarcaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMarcaMouseReleased
        int fila = tblMarca.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null,
                    "Debe seleccionar una fila.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            idMarca = Integer.parseInt(tblMarca.getValueAt(fila, 0).toString());
            txtNombre.setText(tblMarca.getValueAt(fila, 1).toString());
            lblMensaje.setText("");
            txtNombre.setEnabled(false);
            registroBotones(false);
            crudBotones(true);
            btnCancelar.setEnabled(true);
        }
    }//GEN-LAST:event_tblMarcaMouseReleased

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        guardar = true;
        txtNombre.setEnabled(true);
        txtNombre.requestFocus();
        registroBotones(true);
        crudBotones(false);
        btnNuevo.setEnabled(false);
        lblMensaje.setText("");
        tblMarca.clearSelection();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblMarca.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        } else {
            if (JOptionPane.showConfirmDialog(null, "Desea eliminar el registro", "Eliminar", JOptionPane.YES_NO_OPTION, 3) == 0) {
                if (crudMarca.eliminar(new Marca(idMarca))) {
                    limpiarTabla();
                    listarMarcas();
                    lblMensaje.setText("El registro se eliminó correctamente");
                } else {
                    lblMensaje.setText("El registro NO se pudo eliminar");
                }
            }
            registroBotones(false);
            crudBotones(false);
            txtNombre.setText("");
            tblMarca.clearSelection();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        limpiarTabla();
        int n = 0;
        modelo = (DefaultTableModel) tblMarca.getModel();
        String valorBuscar = txtBuscar.getText().strip();
        if (valorBuscar.equalsIgnoreCase("")) {
            limpiarTabla();
            listarMarcas();
            lblMensaje.setText("");
        } else {
//            for (Marca ma : crudMarca.listar(valorBuscar)) {
//                filaDatos[0] = ma.getIdMarca();
//                filaDatos[1] = ma.getNombre();
//                modelo.addRow(filaDatos);
//                n++;
//            }
            lblMensaje.setText(n + " registros encontrados.");
        }
        txtNombre.setText("");
    }//GEN-LAST:event_txtBuscarKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JTable tblMarca;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
