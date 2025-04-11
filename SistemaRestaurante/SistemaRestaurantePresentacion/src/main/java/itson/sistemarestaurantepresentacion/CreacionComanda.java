package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.Mesa;
import itson.sistemarestaurantenegocio.excepciones.IdMesaNuloException;
import itson.sistemarestaurantenegocio.excepciones.MesaConsultadaNoExisteException;
import itson.sistemarestaurantenegocio.interfaces.IComandasBO;
import itson.sistemarestaurantenegocio.interfaces.IMesasBO;
import itson.sistemarestaurantepresentacion.interfaces.IMediador;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CreacionComanda extends JFrame{

    private IMediador control;
    private IMesasBO mesasBO;
    private IComandasBO comandasBO;
    
    private static final Logger LOG = Logger.getLogger(CreacionComanda.class.getName());
    
    public CreacionComanda(IMediador control, IMesasBO mesasBO, IComandasBO comandasBO, Long idMesa) {
        initComponents();
        this.setName("Información de comanda");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        this.control = control;
        this.mesasBO = mesasBO;
        this.comandasBO = comandasBO;
        
    }
    
    /**
     * Método que permite crear una nueva comanda a partir de una mesa.
     * @param idMesa Objeto Long que representa el Id de la mesa que estará
     * asociada a la comanda.
     */
    private void crearNuevaComanda(Long idMesa){
        
        try {
            
            Mesa mesaNuevaComanda = mesasBO.consultarMesaId(idMesa);
            
        } catch (IdMesaNuloException | MesaConsultadaNoExisteException ex) {
            LOG.log(Level.SEVERE, "Error al obtener la mesa asociada a la nueva comanda. " + ex.getMessage());
            JOptionPane.showMessageDialog(
                    this, 
                    ex.getMessage(), 
                    "Error al obtener la mesa asociada a la nueva comanda. ", 
                    JOptionPane.ERROR_MESSAGE);
            
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        panelEncabezado = new javax.swing.JPanel();
        etqIcono = new javax.swing.JLabel();
        etqRestauranteSahuaro = new javax.swing.JLabel();
        etqNombreUsuario = new javax.swing.JLabel();
        etqIconoUsuario = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        panelInformacionComanda = new javax.swing.JPanel();
        etqCliente = new javax.swing.JLabel();
        etqFechaDeCreacion = new javax.swing.JLabel();
        etqEstadoDeComanda = new javax.swing.JLabel();
        etqFolio = new javax.swing.JLabel();
        etqEstado = new javax.swing.JLabel();
        etqFechaCreacion = new javax.swing.JLabel();
        panelSeparador = new javax.swing.JPanel();
        etqFolioDeComanda1 = new javax.swing.JLabel();
        panelScrollProductosComanda = new javax.swing.JScrollPane();
        tablaProductosComanda = new javax.swing.JTable();
        etqFolioDeComanda2 = new javax.swing.JLabel();
        etqClienteAsociado = new javax.swing.JLabel();
        etqTotalDeVenta = new javax.swing.JLabel();
        etqTotalVenta = new javax.swing.JLabel();
        etqSignoPesos = new javax.swing.JLabel();
        btnAniadirProducto = new javax.swing.JButton();
        btnAsociarCliente = new javax.swing.JButton();
        btnAniadirProducto1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(232, 232, 232));

        panelEncabezado.setBackground(new java.awt.Color(250, 230, 188));

        etqRestauranteSahuaro.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        etqRestauranteSahuaro.setText("Restaurante el Sahuaro");

        etqNombreUsuario.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqNombreUsuario.setText("Nombre de usuario");

        etqIconoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconoUsuario2.png"))); // NOI18N

        btnCerrarSesion.setBackground(new java.awt.Color(253, 244, 167));
        btnCerrarSesion.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnCerrarSesion.setText("Cerrar sesión");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelEncabezadoLayout = new javax.swing.GroupLayout(panelEncabezado);
        panelEncabezado.setLayout(panelEncabezadoLayout);
        panelEncabezadoLayout.setHorizontalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(etqIcono)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etqRestauranteSahuaro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEncabezadoLayout.createSequentialGroup()
                        .addComponent(etqNombreUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(etqIconoUsuario))
                    .addComponent(btnCerrarSesion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        panelEncabezadoLayout.setVerticalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addGroup(panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEncabezadoLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(etqRestauranteSahuaro))
                    .addGroup(panelEncabezadoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(etqIcono))
                    .addGroup(panelEncabezadoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(etqIconoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etqNombreUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCerrarSesion)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelInformacionComanda.setBackground(new java.awt.Color(255, 255, 255));

        etqCliente.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqCliente.setText("Cliente:");

        etqFechaDeCreacion.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqFechaDeCreacion.setText("Fecha de creación:");

        etqEstadoDeComanda.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqEstadoDeComanda.setText("Estado:");

        etqFolio.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        etqFolio.setText("Folio...");

        etqEstado.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        etqEstado.setText("Estado...");

        etqFechaCreacion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        etqFechaCreacion.setText("Fecha...");

        panelSeparador.setBackground(new java.awt.Color(232, 232, 232));

        etqFolioDeComanda1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqFolioDeComanda1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etqFolioDeComanda1.setText("Productos de comanda");

        javax.swing.GroupLayout panelSeparadorLayout = new javax.swing.GroupLayout(panelSeparador);
        panelSeparador.setLayout(panelSeparadorLayout);
        panelSeparadorLayout.setHorizontalGroup(
            panelSeparadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSeparadorLayout.createSequentialGroup()
                .addGap(273, 273, 273)
                .addComponent(etqFolioDeComanda1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(272, Short.MAX_VALUE))
        );
        panelSeparadorLayout.setVerticalGroup(
            panelSeparadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSeparadorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etqFolioDeComanda1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelScrollProductosComanda.setBackground(new java.awt.Color(255, 255, 255));

        tablaProductosComanda.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaProductosComanda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Cantidad", "Precio unitario", "Monto total", "Comentarios"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        panelScrollProductosComanda.setViewportView(tablaProductosComanda);

        etqFolioDeComanda2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqFolioDeComanda2.setText("Folio de comanda:");

        etqClienteAsociado.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        etqClienteAsociado.setText("Cliente general");

        etqTotalDeVenta.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqTotalDeVenta.setText("Total de la venta:");

        etqTotalVenta.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        etqTotalVenta.setText("0.00");

        etqSignoPesos.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        etqSignoPesos.setText("$");

        btnAniadirProducto.setBackground(new java.awt.Color(255, 230, 188));
        btnAniadirProducto.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAniadirProducto.setText("Añadir un producto");
        btnAniadirProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAniadirProductoActionPerformed(evt);
            }
        });

        btnAsociarCliente.setBackground(new java.awt.Color(255, 230, 188));
        btnAsociarCliente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAsociarCliente.setText("Asociar a un cliente");
        btnAsociarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsociarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInformacionComandaLayout = new javax.swing.GroupLayout(panelInformacionComanda);
        panelInformacionComanda.setLayout(panelInformacionComandaLayout);
        panelInformacionComandaLayout.setHorizontalGroup(
            panelInformacionComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacionComandaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInformacionComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelInformacionComandaLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(etqFolioDeComanda2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etqFolio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etqFechaDeCreacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etqFechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(etqEstadoDeComanda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etqEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelInformacionComandaLayout.createSequentialGroup()
                        .addGroup(panelInformacionComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelSeparador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelScrollProductosComanda))
                        .addGap(18, 18, 18)
                        .addGroup(panelInformacionComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelInformacionComandaLayout.createSequentialGroup()
                                .addComponent(etqCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(etqClienteAsociado, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelInformacionComandaLayout.createSequentialGroup()
                                .addComponent(etqTotalDeVenta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(etqSignoPesos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(etqTotalVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(panelInformacionComandaLayout.createSequentialGroup()
                .addGap(361, 361, 361)
                .addComponent(btnAniadirProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(btnAsociarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelInformacionComandaLayout.setVerticalGroup(
            panelInformacionComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacionComandaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelInformacionComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etqFechaDeCreacion)
                    .addComponent(etqEstadoDeComanda)
                    .addComponent(etqFolio)
                    .addComponent(etqEstado)
                    .addComponent(etqFechaCreacion)
                    .addComponent(etqFolioDeComanda2))
                .addGap(18, 18, 18)
                .addComponent(panelSeparador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelInformacionComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelInformacionComandaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelScrollProductosComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelInformacionComandaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelInformacionComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etqCliente)
                            .addComponent(etqClienteAsociado))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelInformacionComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etqTotalDeVenta)
                            .addComponent(etqTotalVenta)
                            .addComponent(etqSignoPesos))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInformacionComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAniadirProducto)
                    .addComponent(btnAsociarCliente))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        btnAniadirProducto1.setBackground(new java.awt.Color(205, 255, 197));
        btnAniadirProducto1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
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
            .addComponent(panelEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(panelInformacionComanda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(526, 526, 526)
                        .addComponent(btnAniadirProducto1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(panelInformacionComanda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAniadirProducto1)
                .addGap(0, 10, Short.MAX_VALUE))
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

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnAniadirProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAniadirProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAniadirProductoActionPerformed

    private void btnAsociarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsociarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAsociarClienteActionPerformed

    private void btnAniadirProducto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAniadirProducto1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAniadirProducto1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAniadirProducto;
    private javax.swing.JButton btnAniadirProducto1;
    private javax.swing.JButton btnAsociarCliente;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JLabel etqCliente;
    private javax.swing.JLabel etqClienteAsociado;
    private javax.swing.JLabel etqEstado;
    private javax.swing.JLabel etqEstadoDeComanda;
    private javax.swing.JLabel etqFechaCreacion;
    private javax.swing.JLabel etqFechaDeCreacion;
    private javax.swing.JLabel etqFolio;
    private javax.swing.JLabel etqFolioDeComanda1;
    private javax.swing.JLabel etqFolioDeComanda2;
    private javax.swing.JLabel etqIcono;
    private javax.swing.JLabel etqIconoUsuario;
    private javax.swing.JLabel etqNombreUsuario;
    private javax.swing.JLabel etqRestauranteSahuaro;
    private javax.swing.JLabel etqSignoPesos;
    private javax.swing.JLabel etqTotalDeVenta;
    private javax.swing.JLabel etqTotalVenta;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JPanel panelInformacionComanda;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JScrollPane panelScrollProductosComanda;
    private javax.swing.JPanel panelSeparador;
    private javax.swing.JTable tablaProductosComanda;
    // End of variables declaration//GEN-END:variables
}
