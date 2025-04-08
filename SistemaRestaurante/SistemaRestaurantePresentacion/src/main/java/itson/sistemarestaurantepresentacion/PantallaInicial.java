package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantepresentacion.interfaces.IMediador;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class PantallaInicial extends JFrame{

    private IMediador control;
    private DatosEncabezado datosEncabezado;
    
    public PantallaInicial(IMediador control) {
        initComponents();
        datosEncabezado = DatosEncabezado.getInstance();
        datosEncabezado.setNombreRestaurante("El Sahuaro");
        datosEncabezado.setDireccionLogotipoRestaurante("/logotipoRestaurante.png");
        etqNombreRestaurante.setText(datosEncabezado.getNombreRestaurante());
        etqLogotipoRestaurante.setIcon(new ImageIcon(
                getClass().getResource(datosEncabezado.getDireccionLogotipoRestaurante())));
        
        
        this.setName("Restaurante " + datosEncabezado.getNombreRestaurante());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        this.control = control;
    }

    public void mostrarInicioSesion(){
        control.mostrarInicioSesion(this);
    }
    
    
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        panelEncabezado = new javax.swing.JPanel();
        etqNombreRestaurante = new javax.swing.JLabel();
        etqLogotipoRestaurante = new javax.swing.JLabel();
        panelDatos = new javax.swing.JPanel();
        btnIniciarSesion = new javax.swing.JButton();
        btnRegistrarse = new javax.swing.JButton();
        etqBienvenido = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(232, 232, 232));
        panelPrincipal.setForeground(new java.awt.Color(232, 232, 232));
        panelPrincipal.setPreferredSize(new java.awt.Dimension(749, 477));

        panelEncabezado.setBackground(new java.awt.Color(250, 230, 188));

        etqNombreRestaurante.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        etqNombreRestaurante.setText("Nombre de restaurante");

        javax.swing.GroupLayout panelEncabezadoLayout = new javax.swing.GroupLayout(panelEncabezado);
        panelEncabezado.setLayout(panelEncabezadoLayout);
        panelEncabezadoLayout.setHorizontalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(etqLogotipoRestaurante, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etqNombreRestaurante)
                .addGap(264, 264, 264))
        );
        panelEncabezadoLayout.setVerticalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(etqLogotipoRestaurante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(etqNombreRestaurante)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelDatos.setBackground(new java.awt.Color(255, 255, 255));

        btnIniciarSesion.setBackground(new java.awt.Color(255, 210, 210));
        btnIniciarSesion.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnIniciarSesion.setText("Iniciar Sesión");
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });

        btnRegistrarse.setBackground(new java.awt.Color(255, 210, 210));
        btnRegistrarse.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnRegistrarse.setText("Registrarse");
        btnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarseActionPerformed(evt);
            }
        });

        etqBienvenido.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        etqBienvenido.setText("Bienvenido al sistema");

        javax.swing.GroupLayout panelDatosLayout = new javax.swing.GroupLayout(panelDatos);
        panelDatos.setLayout(panelDatosLayout);
        panelDatosLayout.setHorizontalGroup(
            panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etqBienvenido)
                    .addGroup(panelDatosLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnIniciarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRegistrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        panelDatosLayout.setVerticalGroup(
            panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(etqBienvenido)
                .addGap(49, 49, 49)
                .addComponent(btnIniciarSesion)
                .addGap(53, 53, 53)
                .addComponent(btnRegistrarse)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(panelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(146, Short.MAX_VALUE))
            .addComponent(panelEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(panelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 67, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        mostrarInicioSesion();
    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    private void btnRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JButton btnRegistrarse;
    private javax.swing.JLabel etqBienvenido;
    private javax.swing.JLabel etqLogotipoRestaurante;
    private javax.swing.JLabel etqNombreRestaurante;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
