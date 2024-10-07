package View;

import daoImpl.ColorDaoImpl;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Color;

public class JimColor extends javax.swing.JInternalFrame {
    private ColorDaoImpl crudColor = new ColorDaoImpl();
    private DefaultTableModel modelo;
    private Object[] filaDatos;
    private int idColor;
    private boolean guardar = false;

    public JimColor() {
        initComponents();
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setSize(ancho, alto - 106);
        filaDatos = new Object[2];
        modelo = new DefaultTableModel();
        listarColores();
        habilitarCampo(false);
        crudBotones(false);
        registroBotones(false);
    }

    private void listarColores() {
        modelo = (DefaultTableModel) tblColor.getModel();
        for (Color c : crudColor.listar()) {
            if(c != null) {
                filaDatos[0] = c.getIdColor();
                filaDatos[1] = c.getNombre();
                modelo.addRow(filaDatos);
            }
        }
        if (crudColor.total() > 1) {
            txtBuscar.setEnabled(true);
        } else {
            txtBuscar.setEnabled(false);
        }
    }

    private void limpiarTabla() {
        modelo = (DefaultTableModel) tblColor.getModel();
        modelo.getDataVector().removeAllElements();
        tblColor.removeAll();
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
        txtNombre.requestFocus();
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
        tblColor = new javax.swing.JTable();
        txtNombre = new javax.swing.JTextField();
        lblMensaje = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel4.setText("Colores");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 40, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel11.setText("Nombre del Color:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, -1, 30));

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

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setText("Buscar:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, -1, 30));

        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 260, 70, 29));

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 260, 90, 29));

        tblColor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Color"
            }
        ));
        tblColor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblColorMouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tblColor);

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
        tblColor.clearSelection();
        guardar = false;
        lblMensaje.setText("");
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String title = txtNombre.getText().strip();
        if (title.length() > 0 && title.length() <= 50) {
            if (guardar) {
                if (crudColor.actualizar(new Color(idColor, title))) {
                    lblMensaje.setText("Se actualizo correctamente el color.");
                    habilitarCampo(false);
                    registroBotones(false);
                    crudBotones(false);
                    guardar = false;
                } else {
                    lblMensaje.setText("No se actualizo el color.");
                }
            } else {
                if (crudColor.obtenerId(title) == -1) {
                    if(crudColor.agregar(new Color(crudColor.obtenerIdAutoincrement(), title))) {
                        lblMensaje.setText("Se agrego correctamente el color.");
                        crudColor.agregarCodigo(crudColor.obtenerIdAutoincrement());
                        habilitarCampo(false);
                        registroBotones(false);
                        crudBotones(false);
                    } else {
                        lblMensaje.setText("No se agrego el color.");
                    }
                } else {
                    lblMensaje.setText("No se agrego el color porque color ya existe.");
                    txtNombre.requestFocus();
                }
            }
            tblColor.clearSelection();
            limpiarTabla();
            listarColores();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Advertencia, El nombre del color debe estar entre 1 y 50 letras.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        habilitarCampo(true);
        registroBotones(true);
        crudBotones(false);
        btnNuevo.setEnabled(false);
        lblMensaje.setText("");
        txtBuscar.setEnabled(false);
        tblColor.clearSelection();
        guardar = false;
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void tblColorMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblColorMouseReleased
        int fila = tblColor.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null,
                    "Debe seleccionar una fila.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            idColor = Integer.parseInt(tblColor.getValueAt(fila, 0).toString());
            txtNombre.setText(tblColor.getValueAt(fila, 1).toString());
            lblMensaje.setText("");
            txtNombre.setEnabled(false);
            registroBotones(false);
            crudBotones(true);
            btnCancelar.setEnabled(true);
        }
    }//GEN-LAST:event_tblColorMouseReleased

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        guardar = true;
        txtNombre.setEnabled(true);
        txtNombre.requestFocus();
        registroBotones(true);
        crudBotones(false);
        btnNuevo.setEnabled(false);
        lblMensaje.setText("");
        tblColor.clearSelection();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblColor.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        } else {
            if (JOptionPane.showConfirmDialog(null, "Desea eliminar el registro", "Eliminar", JOptionPane.YES_NO_OPTION, 3) == 0) {
                if (crudColor.eliminar(new Color(idColor))) {
                    limpiarTabla();
                    listarColores();
                    lblMensaje.setText("El registro se eliminó correctamente");
                } else {
                    lblMensaje.setText("El registro NO se pudo eliminar");
                }
            }
            registroBotones(false);
            crudBotones(false);
            txtNombre.setText("");
            tblColor.clearSelection();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        limpiarTabla();
        int n = 0;
        modelo = (DefaultTableModel) tblColor.getModel();
        String valorBuscar = txtBuscar.getText().strip();
        if (valorBuscar.equalsIgnoreCase("")) {
            limpiarTabla();
            listarColores();
            lblMensaje.setText("");
        } else {
//            for (Color co : crudColor.listar(valorBuscar)) {
//                filaDatos[0] = co.getIdColor();
//                filaDatos[1] = co.getNombre();
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
    private javax.swing.JTable tblColor;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
