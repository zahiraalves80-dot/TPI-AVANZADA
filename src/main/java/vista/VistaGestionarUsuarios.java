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
import modelo.Usuario;
import modelo.Veterinario; 
import javax.swing.JFrame;

public class VistaGestionarUsuarios extends javax.swing.JFrame {
    private final Controladora control;
    private final VistaAdministrador vistaAnterior;
    
    private DefaultTableModel modeloTabla;
    private TableRowSorter<TableModel> sorter;
    
    public VistaGestionarUsuarios(Controladora control, VistaAdministrador vistaAnterior) {
        this.control = control;
        this.vistaAnterior = vistaAnterior;
        initComponents();
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        configurarTabla(); 
        cargarUsuarios();
    }
    
    
    public VistaGestionarUsuarios() {
        this.control = null;
        this.vistaAnterior = null;
        initComponents();
    }
    
    private void configurarTabla() {
        // Incluyo 'Teléfono' y 'Dirección' para tener más datos
        String titulos[] = {"ID", "Nombre", "Correo", "Rol", "Teléfono"};
        modeloTabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        jTableUsuarios.setModel(modeloTabla);
        
        sorter = new TableRowSorter<>(modeloTabla);
        jTableUsuarios.setRowSorter(sorter);
        
        // Ocultar Columna ID (0)
        jTableUsuarios.getColumnModel().getColumn(0).setMaxWidth(0);
        jTableUsuarios.getColumnModel().getColumn(0).setMinWidth(0);
        jTableUsuarios.getColumnModel().getColumn(0).setPreferredWidth(0);
    
        // Listener para el filtro
        jTextFieldFiltroNombreUsuario.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { filtrarTabla(); }
            public void removeUpdate(DocumentEvent e) { filtrarTabla(); }
            public void insertUpdate(DocumentEvent e) { filtrarTabla(); }
        });
    }

    private void cargarUsuarios() {
        modeloTabla.setRowCount(0); // Limpiar tabla
        try {
            // Asumo que este método existe en Controladora y devuelve List<Usuario>
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar usuarios: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void filtrarTabla() {
        String filtro = jTextFieldFiltroNombreUsuario.getText();
        if (!filtro.isEmpty()) {
            // Filtra por la columna 1 (Nombre)
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + filtro, 1)); 
        } else {
            sorter.setRowFilter(null);
        }
    }
    
    private int getIdSeleccionado() {
        int filaVista = jTableUsuarios.getSelectedRow();
        if (filaVista == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un usuario de la tabla.", "Error", JOptionPane.WARNING_MESSAGE);
            return -1;
        }
        // Convierte el índice de la vista al índice del modelo
        int filaModelo = jTableUsuarios.convertRowIndexToModel(filaVista);
        return (int) modeloTabla.getValueAt(filaModelo, 0); // Columna 0 = ID
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableUsuarios = new javax.swing.JTable();
        btnRegistrarUsuario = new javax.swing.JButton();
        btnModificarUsuario = new javax.swing.JButton();
        btnEliminarUsuario = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldFiltroNombreUsuario = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableUsuarios);

        btnRegistrarUsuario.setText("Registrar");
        btnRegistrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarUsuarioActionPerformed(evt);
            }
        });

        btnModificarUsuario.setText("Modificar");
        btnModificarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarUsuarioActionPerformed(evt);
            }
        });

        btnEliminarUsuario.setText("Eliminar");
        btnEliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUsuarioActionPerformed(evt);
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
                        .addComponent(btnRegistrarUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificarUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarUsuario)
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
                    .addComponent(btnRegistrarUsuario)
                    .addComponent(btnModificarUsuario)
                    .addComponent(btnEliminarUsuario)
                    .addComponent(btnVolver))
                .addGap(220, 220, 220))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Gestionar Usuarios");

        jLabel2.setText("Nombre del usuario:");

        jTextFieldFiltroNombreUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFiltroNombreUsuarioActionPerformed(evt);
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
                    .addComponent(jTextFieldFiltroNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldFiltroNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void jTextFieldFiltroNombreUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFiltroNombreUsuarioActionPerformed
        filtrarTabla();
    }//GEN-LAST:event_jTextFieldFiltroNombreUsuarioActionPerformed

    private void btnModificarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarUsuarioActionPerformed
       int idUsuario = getIdSeleccionado();
       if (idUsuario == -1) return;

       try {
           Usuario usuario = control.buscarUsuario(idUsuario);
           if (usuario == null) {
               JOptionPane.showMessageDialog(this, "Usuario no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
               return;
           }

           // --- 1. Crear el formulario de modificación con valores actuales ---
           JPanel panelForm = new JPanel(new java.awt.GridLayout(0, 2, 5, 5));
           
           JTextField txtNombre = new JTextField(usuario.getNombre());
           JTextField txtCorreo = new JTextField(usuario.getCorreo());
           JTextField txtTelefono = new JTextField(String.valueOf((int)usuario.getTelefono()));
           JTextField txtDireccion = new JTextField(usuario.getdireccion());
           JTextField txtMatricula = new JTextField();
           
           boolean esVeterinario = usuario.getRol().equals("VETERINARIO");
           if (esVeterinario) {
               Veterinario vet = (Veterinario)usuario;
               txtMatricula.setText(String.valueOf(vet.getMatricula()));
           }
           
           // Construcción del formulario
           panelForm.add(new JLabel("ID (Rol):"));
           panelForm.add(new JLabel(String.valueOf(idUsuario) + " (" + usuario.getRol() + ")"));
           panelForm.add(new JLabel("Nombre:"));
           panelForm.add(txtNombre);
           panelForm.add(new JLabel("Correo:"));
           panelForm.add(txtCorreo);
           panelForm.add(new JLabel("Teléfono:"));
           panelForm.add(txtTelefono);
           panelForm.add(new JLabel("Dirección:"));
           panelForm.add(txtDireccion);
           
           if (esVeterinario) {
               panelForm.add(new JLabel("Matrícula:"));
               panelForm.add(txtMatricula);
           }
           
           int resultado = JOptionPane.showConfirmDialog(this, panelForm, "Modificar Usuario", 
                                                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

           if (resultado == JOptionPane.OK_OPTION) {
               // --- 2. Recopilar y validar datos ---
               String nombre = txtNombre.getText().trim();
               String correo = txtCorreo.getText().trim();
               String telefonoStr = txtTelefono.getText().trim();
               String direccion = txtDireccion.getText().trim();

               if (nombre.isEmpty() || correo.isEmpty() || telefonoStr.isEmpty() || direccion.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                   return;
               }

               double telefono = 0;
               try {
                   telefono = Double.parseDouble(telefonoStr);
               } catch (NumberFormatException e) {
                   JOptionPane.showMessageDialog(this, "El teléfono debe ser un número válido.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                   return;
               }
               
               // --- 3. Aplicar cambios al objeto y persistir ---
               usuario.setNombre(nombre);
               usuario.setCorreo(correo);
               usuario.setTelefono(telefono);
               usuario.setdireccion(direccion);

               if (esVeterinario) {
                   String matriculaStr = txtMatricula.getText().trim();
                   if (matriculaStr.isEmpty()) {
                       JOptionPane.showMessageDialog(this, "La matrícula es obligatoria para veterinarios.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                       return;
                   }
                   int matricula = 0;
                   try {
                       matricula = Integer.parseInt(matriculaStr);
                   } catch (NumberFormatException e) {
                       JOptionPane.showMessageDialog(this, "La matrícula debe ser un número entero.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                       return;
                   }
                   ((Veterinario)usuario).setMatricula(matricula);
               }

               control.modificarUsuario(usuario);
               
               JOptionPane.showMessageDialog(this, "✅ Usuario modificado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
               cargarUsuarios(); // Recargar la tabla
           }

       } catch (OperacionException e) {
           JOptionPane.showMessageDialog(this, "Error de operación: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
       } catch (Exception e) {
           JOptionPane.showMessageDialog(this, "Error inesperado al modificar: " + e.getMessage(), "Error Crítico", JOptionPane.ERROR_MESSAGE);
       }
    }//GEN-LAST:event_btnModificarUsuarioActionPerformed

    private void btnEliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUsuarioActionPerformed
        int idUsuario = getIdSeleccionado();
        if (idUsuario == -1) return;

        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea eliminar el usuario ID: " + idUsuario + "? Esta acción es irreversible.", 
            "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                control.eliminarUsuario(idUsuario); 
                JOptionPane.showMessageDialog(this, " Usuario eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarUsuarios(); // Recargar la tabla
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar el usuario: " + e.getMessage() + ". Asegúrese de que no tenga datos asociados.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarUsuarioActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
       this.vistaAnterior.setVisible(true); 
       this.dispose(); 
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnRegistrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarUsuarioActionPerformed
        try {
        // 1. Crear la instancia de la ventana Registrarse, pasándole la Controladora.
        // Esto es VITAL para que Registrarse.java pueda llamar a control.crearUsuario().
        Registrarse panelRegistro = new Registrarse(this.control); 
        
        // 2. Mostrar la nueva ventana
        panelRegistro.setVisible(true);
        panelRegistro.setLocationRelativeTo(null);
        

        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
            "Error al abrir la ventana de registro: " + e.getMessage(), 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnRegistrarUsuarioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminarUsuario;
    private javax.swing.JButton btnModificarUsuario;
    private javax.swing.JButton btnRegistrarUsuario;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableUsuarios;
    private javax.swing.JTextField jTextFieldFiltroNombreUsuario;
    // End of variables declaration//GEN-END:variables
}
