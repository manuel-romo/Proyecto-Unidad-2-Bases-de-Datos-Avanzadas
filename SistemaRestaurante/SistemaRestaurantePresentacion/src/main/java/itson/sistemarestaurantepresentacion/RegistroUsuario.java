package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantepresentacion.interfaces.IMediador;

public class RegistroUsuario extends javax.swing.JFrame {

    private IMediador control;
    
    public RegistroUsuario() {
        initComponents();
        this.setName("Registro de usuario");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        panelEncabezado = new javax.swing.JPanel();
        etqIcono = new javax.swing.JLabel();
        etqRestauranteSahuaro = new javax.swing.JLabel();
        etqIngresarDatos = new javax.swing.JLabel();
        panelIngresarDatos = new javax.swing.JPanel();
        etqTipoUsuario = new javax.swing.JLabel();
        comboBoxTipoUsuario = new javax.swing.JComboBox<>();
        etqNombres = new javax.swing.JLabel();
        campoTxtNombres = new javax.swing.JTextField();
        etqApellidoPaterno = new javax.swing.JLabel();
        campoTxtApellidoPaterno = new javax.swing.JTextField();
        etqApellidoMaterno = new javax.swing.JLabel();
        campoTxtApellidoMaterno = new javax.swing.JTextField();
        etqCorreoElectronico1 = new javax.swing.JLabel();
        campoTxtCorreoElectrónico = new javax.swing.JTextField();
        etqTelefono = new javax.swing.JLabel();
        campoTxtTelefono = new javax.swing.JTextField();
        etqContrasenia = new javax.swing.JLabel();
        campoTxtContrasenia = new javax.swing.JPasswordField();
        btnVolver = new javax.swing.JButton();
        btnGuardarDatos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(232, 232, 232));

        panelEncabezado.setBackground(new java.awt.Color(250, 230, 188));

        etqIcono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoAplicacion.png"))); // NOI18N

        etqRestauranteSahuaro.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        etqRestauranteSahuaro.setForeground(new java.awt.Color(0, 0, 0));
        etqRestauranteSahuaro.setText("Restaurante el Sahuaro");

        javax.swing.GroupLayout panelEncabezadoLayout = new javax.swing.GroupLayout(panelEncabezado);
        panelEncabezado.setLayout(panelEncabezadoLayout);
        panelEncabezadoLayout.setHorizontalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addComponent(etqIcono)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etqRestauranteSahuaro)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelEncabezadoLayout.setVerticalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addGroup(panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEncabezadoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(etqIcono))
                    .addGroup(panelEncabezadoLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(etqRestauranteSahuaro)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        etqIngresarDatos.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        etqIngresarDatos.setForeground(new java.awt.Color(0, 0, 0));
        etqIngresarDatos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etqIngresarDatos.setText("Ingrese sus datos");

        panelIngresarDatos.setBackground(new java.awt.Color(249, 250, 214));

        etqTipoUsuario.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqTipoUsuario.setForeground(new java.awt.Color(0, 0, 0));
        etqTipoUsuario.setText("Tipo de usuario:");

        comboBoxTipoUsuario.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxTipoUsuario.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        comboBoxTipoUsuario.setForeground(new java.awt.Color(0, 0, 0));
        comboBoxTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cliente", "Administrador", "Cocinero", "Mesero" }));
        comboBoxTipoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTipoUsuarioActionPerformed(evt);
            }
        });

        etqNombres.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqNombres.setForeground(new java.awt.Color(0, 0, 0));
        etqNombres.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etqNombres.setText("Nombre(s):");

        campoTxtNombres.setBackground(new java.awt.Color(255, 255, 255));
        campoTxtNombres.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        etqApellidoPaterno.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqApellidoPaterno.setForeground(new java.awt.Color(0, 0, 0));
        etqApellidoPaterno.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etqApellidoPaterno.setText("Apellido materno:");

        campoTxtApellidoPaterno.setBackground(new java.awt.Color(255, 255, 255));
        campoTxtApellidoPaterno.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        etqApellidoMaterno.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqApellidoMaterno.setForeground(new java.awt.Color(0, 0, 0));
        etqApellidoMaterno.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etqApellidoMaterno.setText("Apellido paterno:");

        campoTxtApellidoMaterno.setBackground(new java.awt.Color(255, 255, 255));
        campoTxtApellidoMaterno.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        etqCorreoElectronico1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqCorreoElectronico1.setForeground(new java.awt.Color(0, 0, 0));
        etqCorreoElectronico1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etqCorreoElectronico1.setText("Correo electrónico:");

        campoTxtCorreoElectrónico.setBackground(new java.awt.Color(255, 255, 255));
        campoTxtCorreoElectrónico.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        etqTelefono.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqTelefono.setForeground(new java.awt.Color(0, 0, 0));
        etqTelefono.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etqTelefono.setText("Teléfono:");

        campoTxtTelefono.setBackground(new java.awt.Color(255, 255, 255));
        campoTxtTelefono.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        etqContrasenia.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqContrasenia.setForeground(new java.awt.Color(0, 0, 0));
        etqContrasenia.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etqContrasenia.setText("Contraseña:");

        campoTxtContrasenia.setBackground(new java.awt.Color(255, 255, 255));
        campoTxtContrasenia.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        javax.swing.GroupLayout panelIngresarDatosLayout = new javax.swing.GroupLayout(panelIngresarDatos);
        panelIngresarDatos.setLayout(panelIngresarDatosLayout);
        panelIngresarDatosLayout.setHorizontalGroup(
            panelIngresarDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIngresarDatosLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(panelIngresarDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(etqTipoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboBoxTipoUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etqNombres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etqApellidoPaterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etqApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etqTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etqCorreoElectronico1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etqContrasenia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelIngresarDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campoTxtApellidoPaterno)
                    .addComponent(campoTxtNombres, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                    .addComponent(campoTxtApellidoMaterno)
                    .addComponent(campoTxtCorreoElectrónico)
                    .addComponent(campoTxtTelefono)
                    .addComponent(campoTxtContrasenia))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        panelIngresarDatosLayout.setVerticalGroup(
            panelIngresarDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIngresarDatosLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(etqTipoUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboBoxTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(panelIngresarDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etqNombres)
                    .addComponent(campoTxtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelIngresarDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etqApellidoMaterno)
                    .addComponent(campoTxtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelIngresarDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etqApellidoPaterno)
                    .addComponent(campoTxtApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelIngresarDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etqCorreoElectronico1)
                    .addComponent(campoTxtCorreoElectrónico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelIngresarDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etqTelefono)
                    .addComponent(campoTxtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelIngresarDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etqContrasenia)
                    .addComponent(campoTxtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        btnVolver.setBackground(new java.awt.Color(205, 255, 197));
        btnVolver.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(0, 0, 0));
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnGuardarDatos.setBackground(new java.awt.Color(205, 255, 197));
        btnGuardarDatos.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnGuardarDatos.setForeground(new java.awt.Color(0, 0, 0));
        btnGuardarDatos.setText("Guardar datos");
        btnGuardarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarDatosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(etqIngresarDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(226, 226, 226))
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(panelIngresarDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(219, 219, 219)
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btnGuardarDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(etqIngresarDatos)
                .addGap(24, 24, 24)
                .addComponent(panelIngresarDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolver)
                    .addComponent(btnGuardarDatos))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxTipoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTipoUsuarioActionPerformed
        
    }//GEN-LAST:event_comboBoxTipoUsuarioActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed

    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnGuardarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarDatosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarDatosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarDatos;
    private javax.swing.JButton btnVolver;
    private javax.swing.JTextField campoTxtApellidoMaterno;
    private javax.swing.JTextField campoTxtApellidoPaterno;
    private javax.swing.JPasswordField campoTxtContrasenia;
    private javax.swing.JTextField campoTxtCorreoElectrónico;
    private javax.swing.JTextField campoTxtNombres;
    private javax.swing.JTextField campoTxtTelefono;
    private javax.swing.JComboBox<String> comboBoxTipoUsuario;
    private javax.swing.JLabel etqApellidoMaterno;
    private javax.swing.JLabel etqApellidoPaterno;
    private javax.swing.JLabel etqContrasenia;
    private javax.swing.JLabel etqCorreoElectronico1;
    private javax.swing.JLabel etqIcono;
    private javax.swing.JLabel etqIngresarDatos;
    private javax.swing.JLabel etqNombres;
    private javax.swing.JLabel etqRestauranteSahuaro;
    private javax.swing.JLabel etqTelefono;
    private javax.swing.JLabel etqTipoUsuario;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JPanel panelIngresarDatos;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
