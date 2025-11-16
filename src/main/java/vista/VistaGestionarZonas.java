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
import modelo.Zona;


import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton; // Para el botón del selector

public class VistaGestionarZonas extends javax.swing.JFrame {
    private final Controladora control;
    private final VistaAdministrador vistaAnterior;
    
    private DefaultTableModel modeloTabla;
    private TableRowSorter<TableModel> sorter;

    public VistaGestionarZonas(Controladora control, VistaAdministrador vistaAnterior) {
        this.control = control;
        this.vistaAnterior = vistaAnterior;
        initComponents(); 
        configurarTabla(); 
        cargarZonas();
    } 
    private String selectGPSFromMap(String defaultCoords) {
        JXMapViewer mapa = new JXMapViewer();
        mapa.setPreferredSize(new Dimension(600, 400));
        mapa.setTileFactory(new DefaultTileFactory(new OSMTileFactoryInfo()));
        
        // 1. Configurar posición inicial (por defecto o la existente)
        GeoPosition geoPosInicial;
        try {
            String[] coords = defaultCoords.split(",");
            double lat = Double.parseDouble(coords[0]);
            double lon = Double.parseDouble(coords[1]);
            geoPosInicial = new GeoPosition(lat, lon);
        } catch (Exception e) {
            // Coordenadas por defecto (ej. Buenos Aires)
            geoPosInicial = new GeoPosition(-34.6037, -58.3816);
        }

        mapa.setCenterPosition(geoPosInicial);
        mapa.setZoom(7);
        
        // Contenedor y variable de resultado
        JLabel lblCoordenadas = new JLabel("Coordenadas seleccionadas: " + defaultCoords);
        final String[] coordsSeleccionadas = {defaultCoords};

        // 2. Añadir Mouse Listener para obtener la posición al hacer clic
        mapa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) { // Click izquierdo
                    GeoPosition pos = mapa.convertPointToGeoPosition(e.getPoint());
                    coordsSeleccionadas[0] = String.format("%.6f,%.6f", pos.getLatitude(), pos.getLongitude());
                    lblCoordenadas.setText("Coordenadas seleccionadas: " + coordsSeleccionadas[0]);
                }
            }
        });

        // 3. Montar el panel del mapa
        JPanel panelMapa = new JPanel(new java.awt.BorderLayout());
        panelMapa.add(mapa, java.awt.BorderLayout.CENTER);
        panelMapa.add(lblCoordenadas, java.awt.BorderLayout.SOUTH);
        
        // 4. Mostrar en JOptionPane
        int result = JOptionPane.showConfirmDialog(
            this, 
            panelMapa, 
            "Seleccione la Ubicación GPS en el Mapa (Clic Izquierdo)", 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.PLAIN_MESSAGE
        );
        
        if (result == JOptionPane.OK_OPTION) {
            // Devolver las coordenadas seleccionadas al cerrar el diálogo con OK
            return coordsSeleccionadas[0];
        } else {
            return null;
        }
    }
    
       private void configurarTabla() {
        String titulos[] = {"ID", "Nombre", "Ubicación GPS"};
        modeloTabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        }; 
        jTableZonas.setModel(modeloTabla);
        sorter = new TableRowSorter<>(modeloTabla);
        jTableZonas.setRowSorter(sorter);
        
        jTableZonas.getColumnModel().getColumn(0).setMaxWidth(0);
        jTableZonas.getColumnModel().getColumn(0).setMinWidth(0);
        jTableZonas.getColumnModel().getColumn(0).setPreferredWidth(0);
        
        jTextFieldFiltroNombreZona.getDocument().addDocumentListener(new DocumentListener() { // <-- SINTAXIS CORREGIDA (No más paréntesis extra)
            public void changedUpdate(DocumentEvent e) { filtrarTabla(); }
            public void removeUpdate(DocumentEvent e) { filtrarTabla(); }
            public void insertUpdate(DocumentEvent e) { filtrarTabla(); }
        });
       }
       
       private void cargarZonas() {
        modeloTabla.setRowCount(0); // Limpiar tabla
        try {
            List<Zona> zonas = control.traerTodasLasZonas();
            if (zonas != null) {
                for (Zona z : zonas) {
                    Object[] fila = {
                        z.getIdZona(), z.getNombreZona(), z.getUbicacionGPS()
                    };
                    modeloTabla.addRow(fila);
                }
            }
        } catch (OperacionException e) {
            // Muestra el error de negocio si no hay zonas
            JOptionPane.showMessageDialog(this, e.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
       
     private void filtrarTabla() {  
    String filtroZona = jTextFieldFiltroNombreZona.getText();
    if (!filtroZona.isEmpty()) {
      sorter.setRowFilter(RowFilter.regexFilter("(?i)" + filtroZona, 1)); 
      } else {
        sorter.setRowFilter(null);
    }
    }
     private long getIdSeleccionado() {
    int filaVista = jTableZonas.getSelectedRow();
    if (filaVista == -1) {
        
        JOptionPane.showMessageDialog(this, "Debe seleccionar una zona de la tabla.", "Error", JOptionPane.WARNING_MESSAGE);
         return -1;
    }
    int filaModelo = jTableZonas.convertRowIndexToModel(filaVista);
    return (long) modeloTabla.getValueAt(filaModelo, 0); 
     }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableZonas = new javax.swing.JTable();
        btnRegistrarZona = new javax.swing.JButton();
        btnModificarZona = new javax.swing.JButton();
        btnEliminarZona = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldFiltroNombreZona = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableZonas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableZonas);

        btnRegistrarZona.setText("Registrar");
        btnRegistrarZona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarZonaActionPerformed(evt);
            }
        });

        btnModificarZona.setText("Modificar");
        btnModificarZona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarZonaActionPerformed(evt);
            }
        });

        btnEliminarZona.setText("Eliminar");
        btnEliminarZona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarZonaActionPerformed(evt);
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
                        .addComponent(btnRegistrarZona)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificarZona)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarZona)
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
                    .addComponent(btnRegistrarZona)
                    .addComponent(btnModificarZona)
                    .addComponent(btnEliminarZona)
                    .addComponent(btnVolver))
                .addGap(220, 220, 220))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Gestionar Zonas");

        jLabel2.setText("Nombre de la zona:");

        jTextFieldFiltroNombreZona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFiltroNombreZonaActionPerformed(evt);
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
                    .addComponent(jTextFieldFiltroNombreZona, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addContainerGap(349, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(34, 34, 34))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldFiltroNombreZona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    private void jTextFieldFiltroNombreZonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFiltroNombreZonaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFiltroNombreZonaActionPerformed

    private void btnModificarZonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarZonaActionPerformed
        long idZona = getIdSeleccionado();
        if (idZona == -1) return;
        
        try {
            Zona zona = control.buscarZona(idZona); 
            if (zona == null) throw new OperacionException("La zona seleccionada no existe.");

            JPanel panelForm = new JPanel(new java.awt.GridLayout(0, 1, 5, 5));
            JTextField txtNombre = new JTextField(zona.getNombreZona(), 20);
            JTextField txtUbicacion = new JTextField(zona.getUbicacionGPS(), 20);
            JButton btnSeleccionarMapa = new JButton("Cambiar Ubicación con Mapa");

            // 🟢 Lógica del botón para Abrir el Mapa (Modificar)
            btnSeleccionarMapa.addActionListener(e -> {
                String coords = selectGPSFromMap(txtUbicacion.getText());
                if (coords != null) {
                    txtUbicacion.setText(coords);
                }
            });
            
            panelForm.add(new JLabel("ID: " + idZona));
            panelForm.add(new JLabel("Nombre de la Zona:"));
            panelForm.add(txtNombre);
            panelForm.add(new JLabel("Ubicación GPS:"));
            panelForm.add(txtUbicacion);
            panelForm.add(btnSeleccionarMapa); // Añade el botón

            int resultado = JOptionPane.showConfirmDialog(this, panelForm, "Modificar Zona (ID: " + idZona + ")", 
                                                         JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (resultado == JOptionPane.OK_OPTION) {
                String nombre = txtNombre.getText().trim();
                String ubicacion = txtUbicacion.getText().trim();
                
                control.modificarZona(idZona, nombre, ubicacion);
                
                JOptionPane.showMessageDialog(this, "Zona modificada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarZonas(); 
            }

        } catch (OperacionException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error al Modificar", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarZonaActionPerformed

    private void btnEliminarZonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarZonaActionPerformed
    long idZona = getIdSeleccionado();
        if (idZona == -1) return;
        
        int respuesta = JOptionPane.showConfirmDialog(this, 
            "¿ESTÁS SEGURO de borrar la zona ID: " + idZona + "? Verifique que no tenga gatos asignados.", 
            "Confirmar Eliminación", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE);
            
        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                control.eliminarZona(idZona);
                
                JOptionPane.showMessageDialog(this, "Zona eliminada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarZonas(); // Recargar la tabla
            } catch (OperacionException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error al Eliminar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarZonaActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
       this.vistaAnterior.setVisible(true); 
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnRegistrarZonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarZonaActionPerformed
       JPanel panelForm = new JPanel(new java.awt.GridLayout(0, 1, 5, 5));
    JTextField txtNombre = new JTextField(20);
    //  Inicializar con un valor por defecto que JXMapViewer pueda parsear
    JTextField txtUbicacion = new JTextField("-34.6037,-58.3816", 20); 
    
    //  Crear el botón para el mapa
    JButton btnSeleccionarMapa = new JButton("Seleccionar en Mapa"); 

    // Lógica para abrir el mapa al hacer clic
    btnSeleccionarMapa.addActionListener(e -> {
        String coords = selectGPSFromMap(txtUbicacion.getText());
        if (coords != null) {
            txtUbicacion.setText(coords);
        }
    });

    panelForm.add(new JLabel("Nombre de la Zona:"));
    panelForm.add(txtNombre);
    panelForm.add(new JLabel("Ubicación GPS:"));
    panelForm.add(txtUbicacion);
    panelForm.add(btnSeleccionarMapa); 

    int resultado = JOptionPane.showConfirmDialog(this, panelForm, "Registrar Nueva Zona", 
                                                 JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (resultado == JOptionPane.OK_OPTION) {
        try {
            String nombre = txtNombre.getText().trim();
            String ubicacion = txtUbicacion.getText().trim();

            control.registrarZona(nombre, ubicacion);
            
            JOptionPane.showMessageDialog(this, "Zona registrada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarZonas(); // Recargar la tabla
            
        } catch (OperacionException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error al Registrar", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_btnRegistrarZonaActionPerformed

        
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminarZona;
    private javax.swing.JButton btnModificarZona;
    private javax.swing.JButton btnRegistrarZona;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableZonas;
    private javax.swing.JTextField jTextFieldFiltroNombreZona;
    // End of variables declaration//GEN-END:variables
}
