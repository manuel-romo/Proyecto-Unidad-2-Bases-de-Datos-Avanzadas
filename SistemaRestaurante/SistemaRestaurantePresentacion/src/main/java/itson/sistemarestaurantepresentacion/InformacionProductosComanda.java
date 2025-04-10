package itson.sistemarestaurantepresentacion;

public class InformacionProductosComanda extends javax.swing.JFrame {

    /**
     * Creates new form InformacionProductosComanda
     */
    public InformacionProductosComanda() {
        initComponents();
        this.setName("Información de productos en comanda");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        etqNombreProductoComanda = new javax.swing.JLabel();
        etqNombre = new javax.swing.JLabel();
        etqCantidad = new javax.swing.JLabel();
        etqCantidadProductoComanda = new javax.swing.JLabel();
        etqPrecioUnitario = new javax.swing.JLabel();
        etqSimboloPesos = new javax.swing.JLabel();
        etqMontoTotalProductoComanda = new javax.swing.JLabel();
        etqMontoTotal = new javax.swing.JLabel();
        etqPrecioUnitarioProductoComanda1 = new javax.swing.JLabel();
        etqSimboloPesos2 = new javax.swing.JLabel();
        etqNotas = new javax.swing.JLabel();
        panelNotas = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(255, 249, 198));

        etqNombreProductoComanda.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        etqNombreProductoComanda.setForeground(new java.awt.Color(0, 0, 0));
        etqNombreProductoComanda.setText("Nombre producto...");

        etqNombre.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqNombre.setForeground(new java.awt.Color(0, 0, 0));
        etqNombre.setText("Nombre");

        etqCantidad.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqCantidad.setForeground(new java.awt.Color(0, 0, 0));
        etqCantidad.setText("Cantidad");

        etqCantidadProductoComanda.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        etqCantidadProductoComanda.setForeground(new java.awt.Color(0, 0, 0));
        etqCantidadProductoComanda.setText("Cantidad...");

        etqPrecioUnitario.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqPrecioUnitario.setForeground(new java.awt.Color(0, 0, 0));
        etqPrecioUnitario.setText("Precio unitario");

        etqSimboloPesos.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        etqSimboloPesos.setForeground(new java.awt.Color(0, 0, 0));
        etqSimboloPesos.setText("$");

        etqMontoTotalProductoComanda.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        etqMontoTotalProductoComanda.setForeground(new java.awt.Color(0, 0, 0));
        etqMontoTotalProductoComanda.setText("Monto total...");

        etqMontoTotal.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqMontoTotal.setForeground(new java.awt.Color(0, 0, 0));
        etqMontoTotal.setText("Monto total");

        etqPrecioUnitarioProductoComanda1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        etqPrecioUnitarioProductoComanda1.setForeground(new java.awt.Color(0, 0, 0));
        etqPrecioUnitarioProductoComanda1.setText("Precio...");

        etqSimboloPesos2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        etqSimboloPesos2.setForeground(new java.awt.Color(0, 0, 0));
        etqSimboloPesos2.setText("$");

        etqNotas.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqNotas.setForeground(new java.awt.Color(0, 0, 0));
        etqNotas.setText("Notas:");

        panelNotas.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelNotasLayout = new javax.swing.GroupLayout(panelNotas);
        panelNotas.setLayout(panelNotasLayout);
        panelNotasLayout.setHorizontalGroup(
            panelNotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelNotasLayout.setVerticalGroup(
            panelNotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 234, Short.MAX_VALUE)
        );

        jButton1.setBackground(new java.awt.Color(205, 255, 197));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(etqNotas, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(panelNotas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(etqNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etqNombreProductoComanda, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(etqCantidadProductoComanda, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(etqCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addComponent(etqSimboloPesos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(etqPrecioUnitarioProductoComanda1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(etqPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(etqMontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelPrincipalLayout.createSequentialGroup()
                                .addComponent(etqSimboloPesos2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(etqMontoTotalProductoComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(317, 317, 317)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etqNombre)
                    .addComponent(etqCantidad)
                    .addComponent(etqPrecioUnitario)
                    .addComponent(etqMontoTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etqNombreProductoComanda)
                    .addComponent(etqCantidadProductoComanda)
                    .addComponent(etqSimboloPesos)
                    .addComponent(etqMontoTotalProductoComanda)
                    .addComponent(etqPrecioUnitarioProductoComanda1)
                    .addComponent(etqSimboloPesos2))
                .addGap(29, 29, 29)
                .addComponent(etqNotas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelNotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel etqCantidad;
    private javax.swing.JLabel etqCantidadProductoComanda;
    private javax.swing.JLabel etqMontoTotal;
    private javax.swing.JLabel etqMontoTotalProductoComanda;
    private javax.swing.JLabel etqNombre;
    private javax.swing.JLabel etqNombreProductoComanda;
    private javax.swing.JLabel etqNotas;
    private javax.swing.JLabel etqPrecioUnitario;
    private javax.swing.JLabel etqPrecioUnitarioProductoComanda1;
    private javax.swing.JLabel etqSimboloPesos;
    private javax.swing.JLabel etqSimboloPesos2;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel panelNotas;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
