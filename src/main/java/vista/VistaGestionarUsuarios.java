package vista;

import java.time.LocalDate;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import controladora.Controladora;
import modelo.FamiliaAdoptante;
import modelo.OperacionException;
import modelo.Usuario;
import modelo.Visita;
import modelo.Voluntario;
import controladora.Controladora;
import modelo.Usuario;
import modelo.OperacionException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JOptionPane;
import java.util.List;
import javax.swing.RowFilter;
import modelo.Veterinario;

public class VistaGestionarUsuarios extends javax.swing.JFrame {
    private final Controladora control;
    private final VistaAdministrador vistaAnterior;
    private DefaultTableModel modeloTabla;
    private TableRowSorter<TableModel> sorter;
    

    VistaGestionarUsuarios(Controladora control, VistaAdministrador vistaAnterior) {
        this.control = control;
        this.vistaAnterior = vistaAnterior;
        initComponents();
        configurarTabla(); 
        cargarUsuarios();
    }
    
    private void configurarTabla() {
        String titulos[] = {"ID", "Nombre", "Correo", "Rol", "Teléfono"};
        modeloTabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        }; 
        jTableVisitas.setModel(modeloTabla); // Asumo que su tabla se llama jTableVisitas
        sorter = new TableRowSorter<>(modeloTabla);
        jTableVisitas.setRowSorter(sorter);
        
        // Ocultar la columna ID (Columna 0)
        jTableVisitas.getColumnModel().getColumn(0).setMaxWidth(0);
        jTableVisitas.getColumnModel().getColumn(0).setMinWidth(0);
        jTableVisitas.getColumnModel().getColumn(0).setPreferredWidth(0);
        
        // Añadir Listener al campo de filtro (txtFiltroGato en el formulario)
        txtFiltroGato.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrarTabla(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrarTabla(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrarTabla(); }
        });
    }

    private void cargarUsuarios() {
        modeloTabla.setRowCount(0); // Limpiar tabla
        try {
            List<Usuario> usuarios = control.traerTodosLosUsuarios();
            if (usuarios != null) {
                for (Usuario u : usuarios) {
                    Object[] fila = {
                        u.getIdUsuario(),
                        u.getNombre(),
                        u.getCorreo(),
                        u.getRol(),
                        u.getTelefono()
                    };
                    modeloTabla.addRow(fila);
                }
            }
        } catch (OperacionException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void filtrarTabla() {  
        String filtroNombre = txtFiltroGato.getText();
        if (!filtroNombre.isEmpty()) {
            // Filtra por la columna de Nombre (Columna 1)
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + filtroNombre, 1)); 
        } else {
            sorter.setRowFilter(null);
        }
    }
    
    // Método auxiliar para obtener el ID seleccionado
    private int getIdSeleccionado() {
        int filaVista = jTableVisitas.getSelectedRow();
        if (filaVista == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un usuario de la tabla.", "Error", JOptionPane.WARNING_MESSAGE);
            return -1;
        }
        int filaModelo = jTableVisitas.convertRowIndexToModel(filaVista);
        return (int) modeloTabla.getValueAt(filaModelo, 0); 
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableVisitas = new javax.swing.JTable();
        btnRegistrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtFiltroGato = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableVisitas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableVisitas);

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnVolver.setText("Volver atrás");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnVolver)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRegistrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnVolver))
                .addGap(220, 220, 220))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Gestionar Usuarios");

        jLabel2.setText("Nombre del usuario:");

        txtFiltroGato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFiltroGatoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtFiltroGato, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFiltroGato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(213, 213, 213))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFiltroGatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroGatoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFiltroGatoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
       int idUsuario = getIdSeleccionado();
    if (idUsuario == -1) return; // Muestra error si no hay selección

    try {
        // 1. Buscar el usuario completo
        Usuario usuario = control.buscarUsuario(idUsuario);

        // --- 2. Crear el formulario dinámico con los datos actuales ---
        JPanel panelForm = new JPanel(new java.awt.GridLayout(0, 1, 5, 5));
        
        // Campos comunes
        JTextField txtNombre = new JTextField(usuario.getNombre(), 20);
        JTextField txtCorreo = new JTextField(usuario.getCorreo(), 20);
        // Mostramos el teléfono como String, aunque se guarde como double
        JTextField txtTelefono = new JTextField(String.valueOf((int)usuario.getTelefono()), 20); 
        JTextField txtDireccion = new JTextField(usuario.getdireccion(), 20);
        
        // Campo específico de Veterinario (solo si aplica)
        JTextField txtMatricula = null;
        String matriculaInicial = "";

        if (usuario.getRol().equals("VETERINARIO")) {
            Veterinario vet = (Veterinario) usuario;
            matriculaInicial = String.valueOf(vet.getMatricula());
            txtMatricula = new JTextField(matriculaInicial, 20);
        }
        
        // Ensamblar el formulario
        panelForm.add(new JLabel("ID Usuario: " + idUsuario + " (Rol: " + usuario.getRol() + ")"));
        panelForm.add(new JLabel("Nombre:"));
        panelForm.add(txtNombre);
        panelForm.add(new JLabel("Correo:"));
        panelForm.add(txtCorreo);
        panelForm.add(new JLabel("Teléfono:"));
        panelForm.add(txtTelefono);
        panelForm.add(new JLabel("Dirección:"));
        panelForm.add(txtDireccion);

        if (txtMatricula != null) {
            panelForm.add(new JLabel("Matrícula (Solo Veterinario):"));
            panelForm.add(txtMatricula);
        }
        
        // --- 3. Mostrar el diálogo y obtener resultado ---
        int resultado = JOptionPane.showConfirmDialog(this, panelForm, "Modificar Usuario", 
                                                     JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (resultado == JOptionPane.OK_OPTION) {
            // 4. Capturar los datos modificados
            String nombre = txtNombre.getText().trim();
            String correo = txtCorreo.getText().trim();
            String telefonoStr = txtTelefono.getText().trim();
            String direccion = txtDireccion.getText().trim();
            String matriculaStr = (txtMatricula != null) ? txtMatricula.getText().trim() : "";

            // Llamar a la Controladora para guardar los cambios
            control.modificarUsuario(
                idUsuario,
                nombre,
                correo,
                telefonoStr,
                direccion,
                matriculaStr 
            );
            
            JOptionPane.showMessageDialog(this, "Usuario modificado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarUsuarios(); // Recargar la tabla
        }

    } catch (OperacionException e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), "Error de Modificación", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(), "Error Crítico", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
    int idUsuario = getIdSeleccionado();
    if (idUsuario == -1) return;

    int respuesta = JOptionPane.showConfirmDialog(this, 
        "¿ESTÁS SEGURO de borrar el usuario ID: " + idUsuario + "?", 
        "Confirmar Eliminación", 
        JOptionPane.YES_NO_OPTION, 
        JOptionPane.WARNING_MESSAGE);
        
    if (respuesta == JOptionPane.YES_OPTION) {
        try {
            control.eliminarUsuario(idUsuario);
            
            JOptionPane.showMessageDialog(this, "Usuario eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarUsuarios(); // Recargar la tabla
        } catch (OperacionException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error al Eliminar", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_btnEliminarActionPerformed
/**
     * Cierra esta ventana y muestra la anterior.
     */
    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        this.vistaAnterior.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed
/**
     * REGISTRAR: Muestra un JOptionPane con un formulario para crear una visita.
     */
    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        Registrarse reg = new Registrarse();
    reg.setLocationRelativeTo(null);
    reg.setVisible(true);
    }//GEN-LAST:event_btnRegistrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableVisitas;
    private javax.swing.JTextField txtFiltroGato;
    // End of variables declaration//GEN-END:variables
}
