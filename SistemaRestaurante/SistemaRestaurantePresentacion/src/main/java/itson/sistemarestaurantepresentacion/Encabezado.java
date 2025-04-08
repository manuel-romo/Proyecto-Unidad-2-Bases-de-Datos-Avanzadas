
package itson.sistemarestaurantepresentacion;

import javax.swing.ImageIcon;


public class Encabezado extends javax.swing.JPanel {

    private DatosEncabezado datosEncabezado;
    
    
    public Encabezado() {
        initComponents();
        datosEncabezado = DatosEncabezado.getInstance();
        String nombreRestaurante = datosEncabezado.getNombreRestaurante();
        String nombresUsuario = datosEncabezado.getNombresUsuario();
        String apellidoPaternoUsuario = datosEncabezado.getApellidoPaternoUsuario();
        String direccionLogotipoRestaurante = datosEncabezado.getDireccionLogotipoRestaurante();
        String direccionImagenUsuario = datosEncabezado.getDireccionImagenUsuario();
        
        etqNombreRestaurante.setText(nombreRestaurante);
        etqNombresUsuario.setText(nombresUsuario + " " + apellidoPaternoUsuario);
        etqLogotipoRestaurante.setIcon(new ImageIcon(getClass().getResource(direccionLogotipoRestaurante)));
        etqImagenUsuario.setIcon(new ImageIcon(getClass().getResource(direccionImagenUsuario)));
        
        
        
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        etqNombreRestaurante = new javax.swing.JLabel();
        etqNombresUsuario = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        etqImagenUsuario = new javax.swing.JLabel();
        etqLogotipoRestaurante = new javax.swing.JLabel();

        setBackground(new java.awt.Color(250, 230, 188));

        etqNombreRestaurante.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        etqNombreRestaurante.setText("Nombre restaurante");

        etqNombresUsuario.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqNombresUsuario.setText("Nombre(s) Apellido Usuario");

        btnCerrarSesion.setBackground(new java.awt.Color(253, 244, 167));
        btnCerrarSesion.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnCerrarSesion.setText("Cerrar sesión");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etqLogotipoRestaurante, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etqNombreRestaurante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 270, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etqNombresUsuario, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCerrarSesion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etqImagenUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etqLogotipoRestaurante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(etqNombresUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCerrarSesion)
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(etqNombreRestaurante)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(etqImagenUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarSesionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JLabel etqImagenUsuario;
    private javax.swing.JLabel etqLogotipoRestaurante;
    private javax.swing.JLabel etqNombreRestaurante;
    private javax.swing.JLabel etqNombresUsuario;
    // End of variables declaration//GEN-END:variables
}
