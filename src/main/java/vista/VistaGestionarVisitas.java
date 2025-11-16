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

public class VistaGestionarVisitas extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VistaGestionarVisitas.class.getName());

    private final Controladora control;
    private final Voluntario voluntario;
    private final VistaVoluntario vistaAnterior;
    private DefaultTableModel modeloTabla;
    private TableRowSorter<TableModel> sorter;
    
    public VistaGestionarVisitas(Controladora control, Voluntario voluntario, VistaVoluntario vistaAnterior) {
        this.control = control;
        this.voluntario = voluntario;
        this.vistaAnterior = vistaAnterior;
        
        initComponents(); // Carga el diseño de la interfaz
        
        configurarTabla(); // Prepara la tabla y el filtro
        cargarVisitas(); // Carga los datos iniciales
    }

    private void configurarTabla() {
        // 1. Configurar modelo de tabla
        String titulos[] = {"ID", "Fecha", "Familia", "Voluntario", "Descripción"};
        modeloTabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hace que la tabla no sea editable
            }
        };
        jTableVisitas.setModel(modeloTabla);
        
        // 2. Configurar el ordenador/filtro
        sorter = new TableRowSorter<>(modeloTabla);
        jTableVisitas.setRowSorter(sorter);

        // 3. Ocultar la columna ID (es útil tenerla pero no visible)
        jTableVisitas.getColumnModel().getColumn(0).setMaxWidth(0);
        jTableVisitas.getColumnModel().getColumn(0).setMinWidth(0);
        jTableVisitas.getColumnModel().getColumn(0).setPreferredWidth(0);

        // 4. Añadir Listeners a los campos de filtro
        // (Usé "Familia" y "Voluntario" en lugar de "Gato", se ajusta mejor al modelo)
        jTextFieldFiltroNombreGato.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { filtrarTabla(); }
            public void removeUpdate(DocumentEvent e) { filtrarTabla(); }
            public void insertUpdate(DocumentEvent e) { filtrarTabla(); }
        });
        
        jTextFieldFiltroNombreFamilia.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { filtrarTabla(); }
            public void removeUpdate(DocumentEvent e) { filtrarTabla(); }
            public void insertUpdate(DocumentEvent e) { filtrarTabla(); }
        });
    }
    /**
     * Carga (o recarga) todas las visitas desde la BD a la tabla.
     */
    private void cargarVisitas() {
        modeloTabla.setRowCount(0); // Limpiar tabla
        
        List<Visita> visitas = control.traerTodasLasVisitas();
        if (visitas != null) {
            for (Visita v : visitas) {
                Object[] fila = {
                    v.getIdVisita(),
                    v.getFecha().toString(),
                    v.getFamilia().getNombre(),
                    v.getVoluntarioEncargado().getNombre(),
                    v.getDescripcion()
                };
                modeloTabla.addRow(fila);
            }
        }
    }
    
    /**
     * Aplica los filtros de los JTextField a la JTable.
     */
    private void filtrarTabla() {
        List<RowFilter<Object, Object>> filtros = new java.util.ArrayList<>();
        
        String filtroFamilia = jTextFieldFiltroNombreFamilia.getText();
        String filtroVoluntario = jTextFieldFiltroNombreGato.getText();

        // Columna 2 es "Familia", Columna 3 es "Voluntario"
        if (!filtroFamilia.isEmpty()) {
            filtros.add(RowFilter.regexFilter("(?i)" + filtroFamilia, 2));
        }
        if (!filtroVoluntario.isEmpty()) {
            filtros.add(RowFilter.regexFilter("(?i)" + filtroVoluntario, 3));
        }
        
        sorter.setRowFilter(RowFilter.andFilter(filtros));
    }
    
    /**
     * Obtiene el ID de la fila seleccionada en la tabla.
     * Maneja la conversión del índice de la vista al índice del modelo.
     * @return El ID de la Visita, o -1 si no hay selección.
     */
    private long getIdSeleccionado() {
        int filaVista = jTableVisitas.getSelectedRow();
        if (filaVista == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una visita de la tabla.", "Error", JOptionPane.WARNING_MESSAGE);
            return -1;
        }
        
        int filaModelo = jTableVisitas.convertRowIndexToModel(filaVista);
        return (long) modeloTabla.getValueAt(filaModelo, 0); // Columna 0 = ID
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableVisitas = new javax.swing.JTable();
        btnRegistrarVisita = new javax.swing.JButton();
        btnVerVisitaSeleccionada = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldFiltroNombreGato = new javax.swing.JTextField();
        jTextFieldFiltroNombreFamilia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

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

        btnRegistrarVisita.setText("Registrar Visita");
        btnRegistrarVisita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarVisitaActionPerformed(evt);
            }
        });

        btnVerVisitaSeleccionada.setText("Ver Visita Seleccionada");
        btnVerVisitaSeleccionada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerVisitaSeleccionadaActionPerformed(evt);
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
                        .addComponent(btnRegistrarVisita)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVerVisitaSeleccionada)
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
                    .addComponent(btnRegistrarVisita)
                    .addComponent(btnVerVisitaSeleccionada)
                    .addComponent(btnVolver))
                .addGap(220, 220, 220))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Gestionar Visitas");

        jLabel2.setText("Nombre del gato:");

        jTextFieldFiltroNombreGato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFiltroNombreGatoActionPerformed(evt);
            }
        });

        jTextFieldFiltroNombreFamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFiltroNombreFamiliaActionPerformed(evt);
            }
        });

        jLabel3.setText("Familia Adoptante:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldFiltroNombreGato, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldFiltroNombreFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldFiltroNombreFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldFiltroNombreGato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    private void jTextFieldFiltroNombreGatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFiltroNombreGatoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFiltroNombreGatoActionPerformed

    private void jTextFieldFiltroNombreFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFiltroNombreFamiliaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFiltroNombreFamiliaActionPerformed
/**
     * Cierra esta ventana y muestra la anterior.
     */
    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        this.vistaAnterior.setVisible(true); // Muestra la VistaVoluntario
        this.dispose(); // Cierra esta ventana (GestionarVisita)
    }//GEN-LAST:event_btnVolverActionPerformed
/**
     * REGISTRAR: Muestra un JOptionPane con un formulario para crear una visita.
     */
    private void btnRegistrarVisitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarVisitaActionPerformed
        // --- 1. Crear el formulario personalizado para el JOptionPane ---
        JPanel panelForm = new JPanel(new java.awt.GridLayout(0, 1, 5, 5));
        
        // Declaración fuera del try/catch para que sea accesible
        List<FamiliaAdoptante> familias;
        DefaultComboBoxModel<FamiliaAdoptante> comboModel = new DefaultComboBoxModel<>();
        
        try {
            // Cargar Familias (Línea que lanza OperacionException)
            familias = control.traerTodasLasFamilias();
            
            // Si la carga tiene éxito, llena el modelo
            for (FamiliaAdoptante f : familias) {
                comboModel.addElement(f); 
            }
            
        } catch (OperacionException e) {
            // Muestra el error de negocio (ej. "No hay familias registradas")
            JOptionPane.showMessageDialog(this, 
                e.getMessage(), 
                "Error de Carga de Familias", 
                JOptionPane.WARNING_MESSAGE);
            return; // Termina el método si no hay familias para registrar
        }
        
        // ComboBox de Familias
        JComboBox<FamiliaAdoptante> cmbFamilias = new JComboBox<>(comboModel); // Usa el modelo lleno
        
        // Área de texto para Descripción
        JTextArea txtDescripcion = new JTextArea(5, 20);
        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
        
        panelForm.add(new JLabel("Familia a Visitar:"));
        panelForm.add(cmbFamilias);
        panelForm.add(new JLabel("Descripción de la Visita:"));
        panelForm.add(scrollDescripcion);

        // --- 2. Mostrar el JOptionPane ---
        int resultado = JOptionPane.showConfirmDialog(this, panelForm, "Registrar Nueva Visita", 
                                                     JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // --- 3. Procesar la entrada ---
        if (resultado == JOptionPane.OK_OPTION) {
            try {
                // ... (El resto de la lógica de procesamiento y la segunda llamada al control que ya está en un try/catch)
                FamiliaAdoptante familiaSel = (FamiliaAdoptante) cmbFamilias.getSelectedItem();
                String descripcion = txtDescripcion.getText();
                
                if (familiaSel == null || descripcion.trim().isEmpty()) {
                    throw new OperacionException("Debe seleccionar una familia y añadir una descripción.");
                }

                // Llamar a la lógica de negocio
                control.registrarVisitaDeSeguimiento(
                    familiaSel.getIdUsuario(), 
                    this.voluntario.getIdUsuario(), // El voluntario logueado
                    LocalDate.now(), 
                    descripcion
                );
                
                JOptionPane.showMessageDialog(this, "Visita registrada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarVisitas(); // Recargar la tabla

            } catch (OperacionException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error al Registrar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnRegistrarVisitaActionPerformed

    private void btnVerVisitaSeleccionadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerVisitaSeleccionadaActionPerformed
        long idVisita = getIdSeleccionado();
        if (idVisita == -1) return; // Ya se mostró el error

        try {
            // 1. Buscar los datos actuales de la visita
            Visita visita = control.buscarVisita(idVisita);

            // --- 2. Crear el formulario personalizado con datos cargados ---
            JPanel panelForm = new JPanel(new java.awt.GridLayout(0, 1, 5, 5));

            // (En modificar, no dejamos cambiar la familia ni el voluntario, solo la descripción)
            JTextField txtFamilia = new JTextField(visita.getFamilia().getNombre());
            txtFamilia.setEditable(false);

            JTextArea txtDescripcion = new JTextArea(visita.getDescripcion(), 5, 20);
            JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);

            panelForm.add(new JLabel("Familia (No editable):"));
            panelForm.add(txtFamilia);
            panelForm.add(new JLabel("Descripción:"));
            panelForm.add(scrollDescripcion);
            // (Podrías añadir un JDatePicker para la fecha)

            // --- 3. Mostrar el JOptionPane ---
            int resultado = JOptionPane.showConfirmDialog(this, panelForm, "Modificar Visita (ID: " + idVisita + ")",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            // --- 4. Procesar la entrada ---
            if (resultado == JOptionPane.OK_OPTION) {
                String nuevaDescripcion = txtDescripcion.getText();

                if (nuevaDescripcion.trim().isEmpty()) {
                    throw new OperacionException("La descripción no puede estar vacía.");
                }

                // Llamar a la lógica de negocio
                control.modificarVisita(idVisita, nuevaDescripcion, visita.getFecha()); // Mantiene la fecha original

                JOptionPane.showMessageDialog(this, "Visita modificada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarVisitas(); // Recargar la tabla
            }

        } catch (OperacionException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error al Modificar", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnVerVisitaSeleccionadaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrarVisita;
    private javax.swing.JButton btnVerVisitaSeleccionada;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableVisitas;
    private javax.swing.JTextField jTextFieldFiltroNombreFamilia;
    private javax.swing.JTextField jTextFieldFiltroNombreGato;
    // End of variables declaration//GEN-END:variables
}
