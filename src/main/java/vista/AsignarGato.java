package vista;

import controladora.Controladora;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import modelo.FamiliaAdoptante;
import modelo.Gato;
import modelo.OperacionException;

public class AsignarGato extends javax.swing.JFrame {
    
    class ComboBoxItem {
        private long id;
        private String display;
        public ComboBoxItem(long id, String display) {
            this.id = id;
            this.display = display;
        }
        public long getId() { return id; }
        @Override
        public String toString() { return display; }
    }
    
    private final Controladora control;

    public AsignarGato(Controladora control) {
        this.control = control;
        initComponents();
    }
    private void cargarDatos() {
        try {
            // Cargar Gatos Disponibles (jComboBox1)
            List<Gato> gatos = control.traerGatosDisponibles();
            DefaultComboBoxModel<ComboBoxItem> modeloGato = new DefaultComboBoxModel<>();
            modeloGato.addElement(new ComboBoxItem(0, "Seleccionar Gato...")); 
            for (Gato g : gatos) {
                modeloGato.addElement(new ComboBoxItem(g.getIdGato(), g.getNombre() + " (" + g.getRaza() + ")"));
            }
            jComboBox1.setModel(modeloGato);

            // Cargar Familias Adoptantes (jComboBox2)
            List<FamiliaAdoptante> familias = control.traerTodasLasFamilias();
            DefaultComboBoxModel<ComboBoxItem> modeloFamilia = new DefaultComboBoxModel<>();
            modeloFamilia.addElement(new ComboBoxItem(0, "Seleccionar Familia...")); 
            for (FamiliaAdoptante f : familias) {
                modeloFamilia.addElement(new ComboBoxItem(f.getIdUsuario(), f.getNombre() + " (" + f.getdireccion() + ")"));
            }
            jComboBox2.setModel(modeloFamilia);

        } catch (OperacionException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error de Carga", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Asignar Gato:");

        jLabel2.setText("Gato a asignar:");

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Familia destino:");

        jButton1.setText("Asignar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox1, 0, 190, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton1)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ComboBoxItem gatoItem = (ComboBoxItem) jComboBox1.getSelectedItem();
        ComboBoxItem familiaItem = (ComboBoxItem) jComboBox2.getSelectedItem();
        
        long idGato = gatoItem.getId();
        int idFamilia = (int) familiaItem.getId(); 

        // 2. Validación de Vista
        if (idGato == 0 || idFamilia == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un gato y una familia válidos.", "Error de Selección", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // 3. Llamar a la lógica de negocio
            control.asignarGatoAFamilia(idGato, idFamilia);
            
            // 4. Éxito y recarga/cierre
            JOptionPane.showMessageDialog(this, 
                "✅ Gato asignado exitosamente a: " + familiaItem.toString(), 
                "Éxito en Adopción", 
                JOptionPane.INFORMATION_MESSAGE);
            
            this.dispose(); // Cierra la ventana tras la asignación

        } catch (OperacionException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error de Operación", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error crítico: " + e.getMessage(), "Error de Persistencia", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<ComboBoxItem> jComboBox1;
    private javax.swing.JComboBox<ComboBoxItem> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
