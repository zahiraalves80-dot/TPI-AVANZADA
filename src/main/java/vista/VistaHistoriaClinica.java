package vista;

import controladora.Controladora;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Estudio;
import modelo.Gato; // 🟢 Agregado
import modelo.HistoriaClinica;
import modelo.Tratamiento;
import modelo.Usuario;
import modelo.Veterinario;
import modelo.OperacionException; // 🟢 Agregado

public class VistaHistoriaClinica extends javax.swing.JFrame {
    
   private final Controladora control;
    private final Gato gato; // 🟢 Agregado
    private final HistoriaClinica historia;
    private final Veterinario veterinario;
    private final JFrame vistaAnterior;
    private DefaultTableModel modeloTablaEstudios;
    private DefaultTableModel modeloTablaTratamientos;

    /**
     * 🟢 CONSTRUCTOR MODIFICADO
     * Acepta 'Gato' en lugar de 'HistoriaClinica'
     */
    public VistaHistoriaClinica(Controladora control, Gato gato, Usuario veterinario, JFrame vistaAnterior) {
        this.control = control;
        this.gato = gato; // 🟢 Guardamos el Gato
        this.historia = gato.getHistoriaClinica(); // 🟢 Obtenemos la HC desde el Gato
        this.veterinario = (Veterinario) veterinario;
        this.vistaAnterior = vistaAnterior; 
        
        initComponents();
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        
        configurarTablas();
        cargarDatos();
    }
    
    // Constructor original
    public VistaHistoriaClinica() {
        this.control = null;
        this.gato = null;
        this.historia = null;
        this.veterinario = null;
        this.vistaAnterior = null;
        initComponents();
    }

    private void configurarTablas() {
        // Tabla Estudios
        String titulosEstudios[] = {"ID", "Nombre Estudio", "Resultado"};
        modeloTablaEstudios = new DefaultTableModel(null, titulosEstudios) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        jTableEstudios.setModel(modeloTablaEstudios); //
        jTableEstudios.getColumnModel().getColumn(0).setMaxWidth(0); // Ocultar ID
        
        // Tabla Tratamientos
        String titulosTratamientos[] = {"ID", "Diagnóstico", "Tratamiento"};
         modeloTablaTratamientos = new DefaultTableModel(null, titulosTratamientos) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        jTableTratamientos.setModel(modeloTablaTratamientos); //
        jTableTratamientos.getColumnModel().getColumn(0).setMaxWidth(0); // Ocultar ID
    }
    
    /**
     * Carga o recarga los datos de las tablas.
     * Público para que las ventanas hijas (Estudio, Tratamiento) puedan llamarlo.
     */
    public void cargarDatos() {
        // 🟢 ================== INICIO CORRECCIÓN ==================
        // Usamos 'gato' y el 'historia' (que ya tenemos)
        jLabel1.setText("Historia Clínica (Gato ID: " + gato.getIdGato() + ")");
        jLabel4.setText("Fecha Creación: " + historia.getFechaCreacion().toString()); // Ahora existe
        jLabel5.setText("Veterinario (Logueado): " + veterinario.getNombre());
        // 🟢 =================== FIN CORRECCIÓN ====================

        // Cargar Estudios
        modeloTablaEstudios.setRowCount(0);
        List<Estudio> estudios = historia.getEstudios();
        if (estudios != null) {
            for (Estudio e : estudios) {
                Object[] fila = {e.getIdEstudio(), e.getNombreEstudio(), e.getDescripcion()};
                modeloTablaEstudios.addRow(fila);
            }
        }
        
        // Cargar Tratamientos
        modeloTablaTratamientos.setRowCount(0);
        List<Tratamiento> tratamientos = historia.getTratamientos();
        if (tratamientos != null) {
            for (Tratamiento t : tratamientos) {
                Object[] fila = {t.getidTratamiento(), t.getDiagostico(), t.getTratamiento()};
                modeloTablaTratamientos.addRow(fila);
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        btnSubirEstudio = new javax.swing.JButton();
        btnAgregarTratamiento = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEstudios = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableTratamientos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Historia Clínica");

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnSubirEstudio.setText("Subir Estudio");
        btnSubirEstudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirEstudioActionPerformed(evt);
            }
        });

        btnAgregarTratamiento.setText("Agregar Tratamiento");
        btnAgregarTratamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarTratamientoActionPerformed(evt);
            }
        });

        jTableEstudios.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableEstudios);

        jLabel2.setText("Estudios");

        jTableTratamientos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTableTratamientos);

        jLabel3.setText("Tratamientos");

        jLabel4.setText("Fecha: ");

        jLabel5.setText("Veterinaria:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnCerrar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgregarTratamiento)
                            .addComponent(btnSubirEstudio)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSubirEstudio)
                .addGap(2, 2, 2)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregarTratamiento)
                .addGap(18, 18, 18)
                .addComponent(btnCerrar)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarTratamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarTratamientoActionPerformed
        VistaTratamiento nuevoTratamiento = new VistaTratamiento(control, historia, this);
        nuevoTratamiento.setVisible(true);
        nuevoTratamiento.setLocationRelativeTo(this);
    }//GEN-LAST:event_btnAgregarTratamientoActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnSubirEstudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirEstudioActionPerformed
        VistaEstudio nuevoEstudio = new VistaEstudio(control, historia, this);
        nuevoEstudio.setVisible(true);
        nuevoEstudio.setLocationRelativeTo(this);
    }//GEN-LAST:event_btnSubirEstudioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarTratamiento;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnSubirEstudio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableEstudios;
    private javax.swing.JTable jTableTratamientos;
    // End of variables declaration//GEN-END:variables
}
