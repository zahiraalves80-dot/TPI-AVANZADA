package vista;

import controladora.Controladora;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.FamiliaAdoptante;
import modelo.Gato;
import modelo.OperacionException;
import modelo.Usuario;

public class VistaGatosVoluntario extends javax.swing.JFrame {
    
   private final Controladora control;
    private final Usuario veterinario; // Guardamos el usuario veterinario
    private DefaultTableModel modeloTabla;
    private TableRowSorter<TableModel> sorter;

    /**
     * Constructor para la VistaVeterinario.
     * @param control La controladora principal de la aplicación.
     * @param usuario El objeto Usuario (Veterinario) que ha iniciado sesión.
     */
    public VistaGatosVoluntario(Controladora control, Usuario usuario) {
        this.control = control;
        this.veterinario = usuario;
        initComponents();
        configurarTabla(); // Prepara la JTable y el sorter
        cargarGatos();     // Carga los datos iniciales
        
        // Personaliza el saludo
        jLabel1.setText("Gatos (Veterinario: " + usuario.getNombre() + ")");
    }
    /**
     * Configura la JTable, el modelo de tabla y el sistema de filtrado (sorter).
     */
    private void configurarTabla() {
        // 1. Definir las columnas de la tabla
        String titulos[] = {"ID", "Nombre Gato", "Raza", "Familia Adoptante", "Estado Salud"};
        modeloTabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // La tabla no es editable
            }
        };
        jTableGatos.setModel(modeloTabla);

        // 2. Configurar el ordenador/filtro
        sorter = new TableRowSorter<>(modeloTabla);
        jTableGatos.setRowSorter(sorter);

        // 3. Ocultar la columna ID (Columna 0)
        jTableGatos.getColumnModel().getColumn(0).setMaxWidth(0);
        jTableGatos.getColumnModel().getColumn(0).setMinWidth(0);
        jTableGatos.getColumnModel().getColumn(0).setPreferredWidth(0);
    }

    /**
     * Carga (o recarga) los gatos desde la base de datos a la JTable.
     * Utiliza el método disponible en la controladora.
     */
    private void cargarGatos() {
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de cargar
        try {
            // NOTA: Usamos traerGatosDisponibles() ya que es el único método
            // en tu Controladora que devuelve una List<Gato>.
            // Si necesitas ver TODOS (incluyendo adoptados), deberás añadir
            // un método "traerTodosLosGatos()" a tu Controladora.
            List<Gato> gatos = control.traerGatosDisponibles(); //

            if (gatos != null && !gatos.isEmpty()) {
                for (Gato g : gatos) {
                    // Determinar el nombre de la familia (si existe)
                    String nombreFamilia = "N/A (Disponible)";
                    if (g.getFamiliaAdoptante() != null) {
                        nombreFamilia = g.getFamiliaAdoptante().getNombre();
                    }
                    
                    Object[] fila = {
                        g.getIdGato(),
                        g.getNombre(),
                        g.getRaza(),
                        nombreFamilia,
                        g.getestadoFisico().toString() //
                    };
                    modeloTabla.addRow(fila);
                }
            }
        } catch (OperacionException e) {
            // Si no hay gatos, muestra el mensaje de la excepción
            JOptionPane.showMessageDialog(this, e.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            // Error crítico
            JOptionPane.showMessageDialog(this, "Error inesperado al cargar gatos: " + e.getMessage(), "Error Crítico", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    /**
     * Aplica los filtros de los JTextField a la JTable usando el sorter.
     */
    private void aplicarFiltros() {
        List<RowFilter<Object, Object>> filtros = new java.util.ArrayList<>();
        
        String filtroGato = jTextFieldFiltroNombreGato.getText().trim();
        String filtroFamilia = jTextFieldFiltroNombreFamilia.getText().trim();

        // Columna 1 es "Nombre Gato"
        if (!filtroGato.isEmpty()) {
            filtros.add(RowFilter.regexFilter("(?i)" + filtroGato, 1));
        }
        // Columna 3 es "Familia Adoptante"
        if (!filtroFamilia.isEmpty()) {
            filtros.add(RowFilter.regexFilter("(?i)" + filtroFamilia, 3));
        }
        
        // Aplica los filtros combinados (AND)
        sorter.setRowFilter(RowFilter.andFilter(filtros));
    }
    
    /**
     * Obtiene el ID del gato seleccionado en la tabla.
     * @return El ID del Gato, o -1 si no hay selección.
     */
    private long getIdGatoSeleccionado() {
        int filaVista = jTableGatos.getSelectedRow();
        if (filaVista == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un gato de la tabla.", "Error", JOptionPane.WARNING_MESSAGE);
            return -1;
        }
        
        // Convierte el índice de la vista (filtrada) al índice del modelo (datos reales)
        int filaModelo = jTableGatos.convertRowIndexToModel(filaVista);
        return (long) modeloTabla.getValueAt(filaModelo, 0); // Columna 0 = ID
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableGatos = new javax.swing.JTable();
        btnVerPerfilGatoSeleccionado = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        btnRegistrarGato = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldFiltroNombreGato = new javax.swing.JTextField();
        jTextFieldFiltroNombreFamilia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableGatos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableGatos);

        btnVerPerfilGatoSeleccionado.setText("Ver Perfil Gato Seleccionado");
        btnVerPerfilGatoSeleccionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerPerfilGatoSeleccionadoActionPerformed(evt);
            }
        });

        btnVolver.setText("Volver atrás");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnRegistrarGato.setText("Registrar Gato");
        btnRegistrarGato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarGatoActionPerformed(evt);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRegistrarGato)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnVerPerfilGatoSeleccionado)
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
                    .addComponent(btnVerPerfilGatoSeleccionado)
                    .addComponent(btnVolver)
                    .addComponent(btnRegistrarGato))
                .addGap(220, 220, 220))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Gatos");

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
                .addContainerGap(225, Short.MAX_VALUE))
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
                .addGap(16, 16, 16)
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
        aplicarFiltros();
    }//GEN-LAST:event_jTextFieldFiltroNombreGatoActionPerformed

    private void jTextFieldFiltroNombreFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFiltroNombreFamiliaActionPerformed
        aplicarFiltros();
    }//GEN-LAST:event_jTextFieldFiltroNombreFamiliaActionPerformed
/**
     * Cierra esta ventana y muestra la anterior.
     */
    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // 1. Crear la nueva ventana de Iniciar Sesión
        IniciarSesion login = new IniciarSesion(control); //
        
        // 2. Mostrar la ventana de Login
        login.setVisible(true);
        login.setLocationRelativeTo(null); // Centrarla
        
        // 3. Cerrar esta ventana (VistaVeterinario)
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed
/**
     * REGISTRAR: Muestra un JOptionPane con un formulario para crear una visita.
     */
    private void btnVerPerfilGatoSeleccionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerPerfilGatoSeleccionadoActionPerformed
        long idGato = getIdGatoSeleccionado();
        if (idGato == -1) return; // Error ya mostrado

        try {
            // 1. Buscar el objeto Gato completo
            Gato gato = control.buscarGatoCompleto((int) idGato); //
            
            // 2. Un veterinario no es una familia y no puede postularse.
            // Pasamos 'null' como familia a la VistaPerfilGatoFamilia.
            // (La VistaPerfilGatoFamilia debe estar preparada para manejar un 'null'
            // y desactivar el botón "Postularse" si la familia es null).
            FamiliaAdoptante familia = null; 
            
            // 3. Abrir la vista de perfil
            VistaPerfilGatoFamilia perfilGato = new VistaPerfilGatoFamilia(gato, familia, control); //
            perfilGato.setVisible(true);
            perfilGato.setLocationRelativeTo(this);
            // No cerramos esta ventana, solo mostramos un detalle

        } catch (OperacionException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error al Cargar Gato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(), "Error Crítico", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnVerPerfilGatoSeleccionadoActionPerformed

    private void btnRegistrarGatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarGatoActionPerformed
        // 1. Crear la nueva instancia, enviando 'this' como la vista anterior
            RegistrarGato registrarGato = new RegistrarGato(control, this);
        try{
            // 2. Mostrar la nueva ventana
            registrarGato.setVisible(true);
            registrarGato.setLocationRelativeTo(null);

            // 3. Ocultar la ventana actual (VistaVoluntario)
            this.setVisible(false);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al abrir el registro de gato: " + e.getMessage(),
                "Error de Navegación",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            }
    }//GEN-LAST:event_btnRegistrarGatoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrarGato;
    private javax.swing.JButton btnVerPerfilGatoSeleccionado;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableGatos;
    private javax.swing.JTextField jTextFieldFiltroNombreFamilia;
    private javax.swing.JTextField jTextFieldFiltroNombreGato;
    // End of variables declaration//GEN-END:variables
}
