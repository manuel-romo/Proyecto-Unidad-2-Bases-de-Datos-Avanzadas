package itson.sistemarestaurantepresentacion;

public class BusquedaProductosComanda extends javax.swing.JFrame {

    /**
     * Creates new form BusquedaProductosComanda
     */
    public BusquedaProductosComanda() {
        initComponents();
        this.setName("Búsqueda de productos");
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
        etqBuscarProducto = new javax.swing.JLabel();
        campoTxtBuscarProducto = new javax.swing.JTextField();
        etqCantidad = new javax.swing.JLabel();
        campoTxtCantidad = new javax.swing.JTextField();
        btnAniadirProducto1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(232, 232, 232));

        etqBuscarProducto.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqBuscarProducto.setForeground(new java.awt.Color(0, 0, 0));
        etqBuscarProducto.setText("Buscar producto por nombre o categoría");

        campoTxtBuscarProducto.setBackground(new java.awt.Color(255, 255, 255));
        campoTxtBuscarProducto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campoTxtBuscarProducto.setForeground(new java.awt.Color(0, 0, 0));

        etqCantidad.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqCantidad.setForeground(new java.awt.Color(0, 0, 0));
        etqCantidad.setText("Cantidad");

        campoTxtCantidad.setBackground(new java.awt.Color(255, 255, 255));
        campoTxtCantidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campoTxtCantidad.setForeground(new java.awt.Color(0, 0, 0));

        btnAniadirProducto1.setBackground(new java.awt.Color(205, 255, 197));
        btnAniadirProducto1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAniadirProducto1.setForeground(new java.awt.Color(0, 0, 0));
        btnAniadirProducto1.setText("Volver");
        btnAniadirProducto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAniadirProducto1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etqBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoTxtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etqCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoTxtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addComponent(btnAniadirProducto1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(etqCantidad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoTxtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(etqBuscarProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoTxtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 398, Short.MAX_VALUE)
                .addComponent(btnAniadirProducto1)
                .addContainerGap())
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

    private void btnAniadirProducto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAniadirProducto1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAniadirProducto1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAniadirProducto1;
    private javax.swing.JTextField campoTxtBuscarProducto;
    private javax.swing.JTextField campoTxtCantidad;
    private javax.swing.JLabel etqBuscarProducto;
    private javax.swing.JLabel etqCantidad;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
