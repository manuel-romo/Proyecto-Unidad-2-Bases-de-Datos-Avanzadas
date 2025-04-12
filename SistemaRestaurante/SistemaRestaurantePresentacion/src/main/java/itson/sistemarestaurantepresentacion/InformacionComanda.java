package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.Comanda;
import itson.sistemarestaurantedominio.Mesa;
import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantenegocio.excepciones.IdMesaNuloException;
import itson.sistemarestaurantenegocio.excepciones.IdProductoNuloException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinIdException;
import itson.sistemarestaurantenegocio.excepciones.MesaConsultadaNoExisteException;
import itson.sistemarestaurantenegocio.excepciones.MesaNuevaComandaNulaException;
import itson.sistemarestaurantenegocio.excepciones.ProductoConsultadoNoExisteException;
import itson.sistemarestaurantenegocio.interfaces.IComandasBO;
import itson.sistemarestaurantenegocio.interfaces.IMesasBO;
import itson.sistemarestaurantenegocio.interfaces.IProductosBO;
import itson.sistemarestaurantepresentacion.interfaces.IMediador;
import itson.sistemarestaurantepresentacion.interfaces.IVistaReceptoraIdProducto;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class InformacionComanda extends JFrame implements IVistaReceptoraIdProducto{

    private IMediador control;
    private IMesasBO mesasBO;
    private IComandasBO comandasBO;
    private IProductosBO productosBO;
    
    private Color COLOR_ESTADO_COMANDA_ABIERTA = new Color(58, 189, 86);
    private Color COLOR_ESTADO_COMANDA_ENTREGADA = new Color(58, 125, 189);
    private Color COLOR_ESTADO_COMANDA_CANCELADA = new Color(58, 189, 86);
    
    private Font FUENTE_ESTADO_COMANDA = new Font("Segoe UI", Font.BOLD, 15);
    
    private static final Logger LOG = Logger.getLogger(InformacionComanda.class.getName());
    
    public InformacionComanda(IMediador control, IMesasBO mesasBO, IComandasBO comandasBO, IProductosBO productosBO, Long idMesa) {
        initComponents();
        this.setName("Información de comanda");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        this.control = control;
        this.mesasBO = mesasBO;
        this.comandasBO = comandasBO;
        this.productosBO = productosBO;
        
        crearNuevaComanda(idMesa);
        
    }
    
    /**
     * Método que permite crear una nueva comanda a partir de una mesa.
     * @param idMesa Objeto Long que representa el Id de la mesa que estará
     * asociada a la comanda.
     */
    private void crearNuevaComanda(Long idMesa){
        
        try {
            
            Mesa mesaNuevaComanda = mesasBO.consultarMesaId(idMesa);
            
            Comanda comanda = comandasBO.registrarComanda(mesaNuevaComanda);
            
            cargarDatosComanda(comanda);
            
        } catch (IdMesaNuloException | MesaConsultadaNoExisteException | MesaNuevaComandaNulaException ex) {
            LOG.log(Level.SEVERE, "Error al obtener la mesa asociada a la nueva comanda. " + ex.getMessage());
            JOptionPane.showMessageDialog(
                    this, 
                    ex.getMessage(), 
                    "Error al obtener la mesa asociada a la nueva comanda. ", 
                    JOptionPane.ERROR_MESSAGE);
            
        }
    }
    
    /**
     * Método que permite cargar los datos mostrados en la ventana de información
     * de comanda.
     * @param comanda Objeto Comanda, es la comanda que se va a mostrar.
     */
    private void cargarDatosComanda(Comanda comanda){
        
        establecerTextoEqtFolioValor(comanda.getFolio());
        establecerTextoEtqFechaHoraCreacion(comanda.getFechaHoraCreacion());
        
        establecerTextoEtqEstadoValor(comanda.getFolio());
        
        if(comanda.getCliente() != null){
            
            establecerTextoEtqClienteValor(comanda.getCliente().getNombres() + " " + comanda.getCliente().getApellidoPaterno());
            establecerTextoNumeroTelefonoClienteValor(comanda.getCliente().getTelefono());
            
        } else{
            
            establecerTextoEtqClienteValor("");
            establecerTextoNumeroTelefonoClienteValor("");
        }
      
    }
    
    private void establecerTextoEqtFolioValor(String folio){
        etqFolio.setText(folio);
    }
    
    private void establecerTextoEtqFechaHoraCreacion(Calendar fechaHoraCreacion){
        
        SimpleDateFormat formatoFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        String fechaFormateada = formatoFechaHora.format(fechaHoraCreacion.getTime());
        
        etqFechaCreacion.setText(fechaFormateada);
    }
    
    
    private void establecerTextoEtqClienteValor(String nombreCompletoCliente){
        etqNombresApellidoClienteAsociado.setText(nombreCompletoCliente);
    }
    
    private void establecerTextoNumeroTelefonoClienteValor(String numeroTelefonoCliente){
        etqNumeroTelefonoClienteValor.setText(numeroTelefonoCliente);
    }
    
    
    
    
    private void establecerTextoEtqEstadoValor(String estado){
        
        etqEstado.setFont(FUENTE_ESTADO_COMANDA);
        
        if(estado.equals("ABIERTA")){
            etqEstado.setText("Abierta");      
            etqEstado.setForeground(COLOR_ESTADO_COMANDA_ABIERTA);
                 
        } else if (estado.equals("ENTREGADA")){
            etqEstado.setText("Entregada");      
            etqEstado.setForeground(COLOR_ESTADO_COMANDA_ENTREGADA);
            
        } else if(estado.equals("CANCELADA")){
            etqEstado.setText("Cancelada");      
            etqEstado.setForeground(COLOR_ESTADO_COMANDA_ENTREGADA);
        }
        
    }
    
    private void asociarCliente(){
        
    }
    
    private void cargarProducto(Long idProducto){
        
        try {
            
            Producto productoAgregar = productosBO.consultarProductoPorId(idProducto);
            
            DefaultTableModel modelo = (DefaultTableModel) tablaProductosComanda.getModel();
            
            
            
        } catch (ProductoConsultadoNoExisteException ex) {
            LOG.severe("Ha ocurrido un error al añadir el producto. " + ex.getMessage());
            JOptionPane.showMessageDialog(
                    this, 
                    ex.getMessage(), 
                    "Ha ocurrido un error al añadir el producto", 
                    JOptionPane.ERROR_MESSAGE);
        }
        
    }
    

    
    private void agregarProducto(){
        control.mostrarBuscadorProductos(this);
    }
    
    private void seleccionarCantidadProducto(Long idProducto){
        control.mostrarSeleccionCantidadProducto(this, idProducto);
    }
    
    public void setCantidadProducto(Long idProducto, float cantidad){
        
    }
    
     @Override
    public void setIdProducto(Long idProducto) {
        seleccionarCantidadProducto(idProducto);
        getContentPane().revalidate();
        repaint();
    }

    @Override
    public void habilitar(boolean habilitar) {
        setEnabled(habilitar);
    }
    
    private void mostrarMenuPrincipal(){
        control.mostrarMenuPrincipal(this);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
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
        etqNombresApellidoClienteAsociado = new javax.swing.JLabel();
        etqTotalDeVenta = new javax.swing.JLabel();
        etqTotalVenta = new javax.swing.JLabel();
        etqSignoPesos = new javax.swing.JLabel();
        btnAniadirProducto = new javax.swing.JButton();
        btnAsociarCliente = new javax.swing.JButton();
        etqNumeroTelefonoCliente = new javax.swing.JLabel();
        etqNumeroTelefonoClienteValor = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(232, 232, 232));

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

        etqNombresApellidoClienteAsociado.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        etqNombresApellidoClienteAsociado.setText("Nombre de cliente");

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

        etqNumeroTelefonoCliente.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqNumeroTelefonoCliente.setText("Teléfono:");

        etqNumeroTelefonoClienteValor.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        etqNumeroTelefonoClienteValor.setText("Teléfono de cliente");

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
                        .addGap(289, 289, 289))
                    .addGroup(panelInformacionComandaLayout.createSequentialGroup()
                        .addGroup(panelInformacionComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelSeparador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelScrollProductosComanda))
                        .addGap(18, 18, 18)
                        .addGroup(panelInformacionComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelInformacionComandaLayout.createSequentialGroup()
                                .addGroup(panelInformacionComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelInformacionComandaLayout.createSequentialGroup()
                                        .addComponent(etqSignoPesos)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(etqTotalVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(panelInformacionComandaLayout.createSequentialGroup()
                                        .addGroup(panelInformacionComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(etqNumeroTelefonoCliente)
                                            .addComponent(etqTotalDeVenta))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(25, 25, 25))
                            .addGroup(panelInformacionComandaLayout.createSequentialGroup()
                                .addGroup(panelInformacionComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(etqNombresApellidoClienteAsociado, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(etqNumeroTelefonoClienteValor, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelInformacionComandaLayout.createSequentialGroup()
                                        .addGroup(panelInformacionComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(etqEstadoDeComanda)
                                            .addComponent(etqCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(etqEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
            .addGroup(panelInformacionComandaLayout.createSequentialGroup()
                .addGap(254, 254, 254)
                .addComponent(btnAniadirProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
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
                        .addComponent(etqCliente)
                        .addGap(12, 12, 12)
                        .addComponent(etqNombresApellidoClienteAsociado)
                        .addGap(18, 18, 18)
                        .addComponent(etqNumeroTelefonoCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etqNumeroTelefonoClienteValor)
                        .addGap(18, 18, 18)
                        .addComponent(etqTotalDeVenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelInformacionComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etqTotalVenta)
                            .addComponent(etqSignoPesos))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInformacionComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAniadirProducto)
                    .addComponent(btnAsociarCliente))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        btnVolver.setBackground(new java.awt.Color(205, 255, 197));
        btnVolver.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(panelInformacionComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 988, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(433, 433, 433)
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(panelInformacionComanda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVolver)
                .addGap(0, 16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAniadirProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAniadirProductoActionPerformed
        agregarProducto();
    }//GEN-LAST:event_btnAniadirProductoActionPerformed

    private void btnAsociarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsociarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAsociarClienteActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        mostrarMenuPrincipal();
    }//GEN-LAST:event_btnVolverActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAniadirProducto;
    private javax.swing.JButton btnAsociarCliente;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel etqCliente;
    private javax.swing.JLabel etqEstado;
    private javax.swing.JLabel etqEstadoDeComanda;
    private javax.swing.JLabel etqFechaCreacion;
    private javax.swing.JLabel etqFechaDeCreacion;
    private javax.swing.JLabel etqFolio;
    private javax.swing.JLabel etqFolioDeComanda1;
    private javax.swing.JLabel etqFolioDeComanda2;
    private javax.swing.JLabel etqNombresApellidoClienteAsociado;
    private javax.swing.JLabel etqNumeroTelefonoCliente;
    private javax.swing.JLabel etqNumeroTelefonoClienteValor;
    private javax.swing.JLabel etqSignoPesos;
    private javax.swing.JLabel etqTotalDeVenta;
    private javax.swing.JLabel etqTotalVenta;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelInformacionComanda;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JScrollPane panelScrollProductosComanda;
    private javax.swing.JPanel panelSeparador;
    private javax.swing.JTable tablaProductosComanda;
    // End of variables declaration//GEN-END:variables

}
