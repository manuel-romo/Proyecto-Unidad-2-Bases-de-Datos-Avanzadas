/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package itson.sistemarestaurantepresentacion;

/**
 *
 * @author Usuario
 */
public class BuscadorProductos extends javax.swing.JPanel {

    /**
     * Creates new form BuscadorProductos
     */
    public BuscadorProductos() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipalBuscador = new javax.swing.JPanel();
        etqBuscarProducto = new javax.swing.JLabel();
        campoTxtBuscarProducto = new javax.swing.JTextField();
        etqCantidad = new javax.swing.JLabel();
        campoTxtCantidad = new javax.swing.JTextField();

        panelPrincipalBuscador.setBackground(new java.awt.Color(232, 232, 232));

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

        javax.swing.GroupLayout panelPrincipalBuscadorLayout = new javax.swing.GroupLayout(panelPrincipalBuscador);
        panelPrincipalBuscador.setLayout(panelPrincipalBuscadorLayout);
        panelPrincipalBuscadorLayout.setHorizontalGroup(
            panelPrincipalBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalBuscadorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etqBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTxtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelPrincipalBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etqCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTxtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPrincipalBuscadorLayout.setVerticalGroup(
            panelPrincipalBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalBuscadorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etqBuscarProducto)
                    .addComponent(etqCantidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoTxtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTxtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipalBuscador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipalBuscador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campoTxtBuscarProducto;
    private javax.swing.JTextField campoTxtCantidad;
    private javax.swing.JLabel etqBuscarProducto;
    private javax.swing.JLabel etqCantidad;
    private javax.swing.JPanel panelPrincipalBuscador;
    // End of variables declaration//GEN-END:variables
}
