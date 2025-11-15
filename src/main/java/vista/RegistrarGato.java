package vista;

import controladora.Controladora;
import javax.swing.JScrollPane;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import modelo.OperacionException;
import modelo.Zona;
import javax.swing.JFileChooser;
import java.io.File;

public class RegistrarGato extends javax.swing.JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RegistrarGato.class.getName());
    private final Controladora control;
    private String rutaFotoSeleccionada = "";
    private final VistaVoluntario vistaVoluntario;
    
    public RegistrarGato(Controladora control,VistaVoluntario vistaVoluntario) {
        this.control = control;
        this.vistaVoluntario = vistaVoluntario;
        initComponents();
        cargarZonas();
        cargarEstadosSalud();
    }

    private void cargarEstadosSalud() {
    // Importante: El Enum se llama Gato.EstadoSalud (definido en modelo/Gato.java)
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
    model.addElement("-"); // Opción por defecto
    
    // Recorre todos los valores del Enum EstadoSalud
    for (modelo.Gato.EstadoSalud estado : modelo.Gato.EstadoSalud.values()) {
        // Formatea el nombre del Enum para mostrarlo al usuario 
        // (Ej: EN_TRATAMIENTO se convierte a EN TRATAMIENTO)
        String display = estado.toString().replace("_", " "); 
        model.addElement(display);
    }
    
    // Asigna el nuevo modelo al ComboBox de Estado de Salud (jComboBox2)
    jComboBox2.setModel(model);
}
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        NOMBRE = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        RAZA = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        COLOR = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        RegistrarGato = new javax.swing.JButton();
        jComboBox5 = new javax.swing.JComboBox<>();
        SCROLL = new javax.swing.JScrollPane();
        CARACTERISTICAS = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Registrar Gato:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(jLabel1)
                .addContainerGap(127, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(396, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Nombre:");

        NOMBRE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NOMBREActionPerformed(evt);
            }
        });

        jLabel4.setText("Raza:");

        RAZA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RAZAActionPerformed(evt);
            }
        });

        jLabel5.setText("Sexo:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Femenino ", "Masculino " }));

        jLabel7.setText("Esterilizado:");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Si", "No" }));

        COLOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                COLORActionPerformed(evt);
            }
        });

        jLabel8.setText("Color:");

        jLabel9.setText("Caracteristicas/Observarciones:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Si", "No" }));

        jLabel10.setText("Estado de Salud:");

        jLabel6.setText("Disponible:");

        RegistrarGato.setText("Registrar");
        RegistrarGato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarGatoActionPerformed(evt);
            }
        });

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Si", "No" }));

        CARACTERISTICAS.setColumns(20);
        CARACTERISTICAS.setRows(5);
        SCROLL.setViewportView(CARACTERISTICAS);

        jLabel11.setText("Zona");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Si", "No" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jButton1.setText("Agregar Imagen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("volver");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(RegistrarGato)
                .addGap(26, 26, 26)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(SCROLL, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(jComboBox1, 0, 155, Short.MAX_VALUE)
                                    .addComponent(RAZA)
                                    .addComponent(NOMBRE)
                                    .addComponent(COLOR)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBox2, 0, 155, Short.MAX_VALUE)
                                        .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(20, 20, 20))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NOMBRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RAZA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(COLOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(12, 12, 12)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SCROLL, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RegistrarGato)
                    .addComponent(jButton2))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Registrar Gato");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(jLabel3)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void cargarZonas() {
        try {
            // Llama a la Controladora para obtener las zonas
            List<Zona> zonas = control.traerTodasLasZonas();
            
            // Crea un modelo para el ComboBox (jComboBox4)
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            model.addElement("-"); // Opción por defecto
            
            if (zonas != null) {
                for (Zona z : zonas) {
                    model.addElement(z.getNombreZona());
                }
            }
            jComboBox4.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al cargar zonas: " + e.getMessage(), 
                "Error de Carga", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    private void NOMBREActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NOMBREActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NOMBREActionPerformed

    private void RAZAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RAZAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RAZAActionPerformed

    private void RegistrarGatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarGatoActionPerformed
      // 1. Captura de Datos (Capa de Vista)
        String nombre = NOMBRE.getText().trim();
        String raza = RAZA.getText().trim();
        // Usamos .getSelectedItem().toString() para los ComboBox
        String sexo = jComboBox1.getSelectedItem().toString().trim();
        String color = COLOR.getText().trim();
        String esterilizado = jComboBox3.getSelectedItem().toString().trim();
        String caracteristicas = CARACTERISTICAS.getText().trim();
        String estadoSalud = jComboBox2.getSelectedItem().toString().trim(); 
        String disponible = jComboBox5.getSelectedItem().toString().trim();
        String nombreZona = jComboBox4.getSelectedItem().toString().trim(); 
        String rutaFoto = this.rutaFotoSeleccionada;

        try {
            // 2. Llamada a la Lógica de Negocio (Capa de Controladora)
            control.registrarGato(
                nombre, raza, sexo, color, 
                esterilizado, caracteristicas, 
                estadoSalud, disponible, nombreZona, rutaFoto
            );
            
            // 3. Mostrar Mensaje de Éxito y Limpiar Campos (Capa de Vista)
            JOptionPane.showMessageDialog(this, 
                "✅ Gato " + nombre + " registrado con éxito.", 
                "Registro Exitoso", 
                JOptionPane.INFORMATION_MESSAGE);
            
            limpiarCampos();

        } catch (OperacionException e) {
            // 4. Manejo de Errores de Negocio (Campos Vacíos, Zona Inválida, etc.)
            JOptionPane.showMessageDialog(this, 
                e.getMessage(), 
                "Error de Registro", 
                JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            // 5. Manejo de Errores Críticos (DB o sistema)
             JOptionPane.showMessageDialog(this, 
                "Error crítico: No se pudo guardar el gato. " + e.getMessage(), 
                "Error de Sistema", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // MÉTODO PARA LIMPIAR LA INTERFAZ
    private void limpiarCampos() {
        NOMBRE.setText("");      // Nombre
        RAZA.setText("");      // Raza
        COLOR.setText("");      // Color
        CARACTERISTICAS.setText("");       // Caracteristicas/Observaciones
        
        // Resetear ComboBox a la primera posición ("-")
        jComboBox1.setSelectedIndex(0); 
        jComboBox2.setSelectedIndex(0); 
        jComboBox3.setSelectedIndex(0); 
        jComboBox4.setSelectedIndex(0); 
        jComboBox5.setSelectedIndex(0); 
        this.rutaFotoSeleccionada = "";
       
      
    }//GEN-LAST:event_RegistrarGatoActionPerformed

    private void COLORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_COLORActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_COLORActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar Imagen del Gato");
        
        // Opcional: Filtrar para mostrar solo archivos de imagen
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes (JPG, PNG)", "jpg", "jpeg", "png"));
        
        int result = fileChooser.showOpenDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            this.rutaFotoSeleccionada = selectedFile.getAbsolutePath();
            
            // Notificar al usuario (opcional)
            JOptionPane.showMessageDialog(this, 
                "Ruta de imagen capturada:\n" + selectedFile.getName(), 
                "Imagen Seleccionada", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Si el usuario cancela, no hay foto seleccionada
            this.rutaFotoSeleccionada = ""; 
        }   
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // 1. Mostrar la ventana principal del Voluntario
    this.vistaVoluntario.setVisible(true); 
    
    // 2. Cerrar la ventana actual de registro de gato
    this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea CARACTERISTICAS;
    private javax.swing.JTextField COLOR;
    private javax.swing.JTextField NOMBRE;
    private javax.swing.JTextField RAZA;
    private javax.swing.JButton RegistrarGato;
    private javax.swing.JScrollPane SCROLL;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}