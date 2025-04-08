
package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.Usuario;
import itson.sistemarestaurantenegocio.interfaces.IComandasBO;
import itson.sistemarestaurantenegocio.interfaces.IUsuariosBO;
import itson.sistemarestaurantenegocio.excepciones.UsuarioInexistenteException;
import javax.swing.JFrame;


public class CrearComanda extends JFrame {

    private IUsuariosBO usuariosBO;
    private IComandasBO comandasBO;
    private Long idUsuario;
    
    public CrearComanda(IUsuariosBO usuariosBO, IComandasBO comandasBO, Long idUsuario) throws UsuarioInexistenteException {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        this.usuariosBO = usuariosBO;
        this.comandasBO = comandasBO;
        this.idUsuario = idUsuario; 
        
        cargarNombreUsuarioEncabezado();
    }

    private void cargarNombreUsuarioEncabezado() throws UsuarioInexistenteException{
        
        Usuario usuario = usuariosBO.consultarUsuarioId(idUsuario);

        idUsuario = usuario.getId();

        String nombresUsuario = usuario.getNombres();
        String apellidoPaternoUsuario = usuario.getApellidoPaterno();
        String apellidoMaternoUsuario = usuario.getApellidoMaterno();

        etqNombreUsuario.setText(apellidoPaternoUsuario + " " + apellidoMaternoUsuario + ", " + nombresUsuario);
              
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelEncabezado = new javax.swing.JPanel();
        etqNombreUsuario = new javax.swing.JLabel();
        panelPrincipalInformacionComanda = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        etqFechaCreacionComanda = new javax.swing.JLabel();
        etqEstadoComanda = new javax.swing.JLabel();
        etqValorEstadoComanda = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        etqTituloProductosComanda = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        btnAniadirProducto = new javax.swing.JButton();
        btnAsociarCliente = new javax.swing.JButton();
        etqNombreCliente = new javax.swing.JLabel();
        etqCliente = new javax.swing.JLabel();
        etqTotalVenta = new javax.swing.JLabel();
        etqMontoVenta = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelEncabezado.setBackground(new java.awt.Color(255, 255, 204));

        etqNombreUsuario.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqNombreUsuario.setText("Nombre de Usuario");

        javax.swing.GroupLayout panelEncabezadoLayout = new javax.swing.GroupLayout(panelEncabezado);
        panelEncabezado.setLayout(panelEncabezadoLayout);
        panelEncabezadoLayout.setHorizontalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(etqNombreUsuario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelEncabezadoLayout.setVerticalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(etqNombreUsuario)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        panelPrincipalInformacionComanda.setBackground(new java.awt.Color(255, 255, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Folio de comanda");

        etqFechaCreacionComanda.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqFechaCreacionComanda.setText("Fecha de creación de comanda");

        etqEstadoComanda.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqEstadoComanda.setText("Estado:");

        etqValorEstadoComanda.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqValorEstadoComanda.setText("Valor Estado");

        etqTituloProductosComanda.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqTituloProductosComanda.setText("Productos de comanda");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(328, 328, 328)
                .addComponent(etqTituloProductosComanda)
                .addContainerGap(255, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etqTituloProductosComanda)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        btnAniadirProducto.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAniadirProducto.setText("Añadir producto");

        btnAsociarCliente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAsociarCliente.setText("Asociar cliente");

        etqNombreCliente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqNombreCliente.setText("Nombre Cliente");

        etqCliente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqCliente.setText("Cliente:");

        etqTotalVenta.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqTotalVenta.setText("Total de la venta:");

        etqMontoVenta.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqMontoVenta.setText("$0.00");

        javax.swing.GroupLayout panelPrincipalInformacionComandaLayout = new javax.swing.GroupLayout(panelPrincipalInformacionComanda);
        panelPrincipalInformacionComanda.setLayout(panelPrincipalInformacionComandaLayout);
        panelPrincipalInformacionComandaLayout.setHorizontalGroup(
            panelPrincipalInformacionComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalInformacionComandaLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelPrincipalInformacionComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelPrincipalInformacionComandaLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(52, 52, 52)
                        .addComponent(etqFechaCreacionComanda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                        .addComponent(etqEstadoComanda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etqValorEstadoComanda))
                    .addGroup(panelPrincipalInformacionComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panelPrincipalInformacionComandaLayout.createSequentialGroup()
                            .addComponent(btnAniadirProducto)
                            .addGap(40, 40, 40)
                            .addComponent(btnAsociarCliente)
                            .addGap(167, 167, 167))
                        .addGroup(panelPrincipalInformacionComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelPrincipalInformacionComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etqNombreCliente)
                    .addComponent(etqCliente)
                    .addComponent(etqTotalVenta)
                    .addGroup(panelPrincipalInformacionComandaLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(etqMontoVenta)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPrincipalInformacionComandaLayout.setVerticalGroup(
            panelPrincipalInformacionComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalInformacionComandaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelPrincipalInformacionComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(etqFechaCreacionComanda)
                    .addComponent(etqValorEstadoComanda)
                    .addComponent(etqEstadoComanda))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalInformacionComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalInformacionComandaLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalInformacionComandaLayout.createSequentialGroup()
                        .addComponent(etqCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etqNombreCliente)
                        .addGap(33, 33, 33)
                        .addComponent(etqTotalVenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etqMontoVenta)
                        .addGap(55, 55, 55)))
                .addGroup(panelPrincipalInformacionComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAniadirProducto)
                    .addComponent(btnAsociarCliente))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        btnVolver.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVolver.setText("Volver");

        btnAceptar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAceptar.setText("Aceptar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(360, 360, 360)
                .addComponent(btnVolver)
                .addGap(35, 35, 35)
                .addComponent(btnAceptar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelPrincipalInformacionComanda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelPrincipalInformacionComanda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolver)
                    .addComponent(btnAceptar))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAniadirProducto;
    private javax.swing.JButton btnAsociarCliente;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel etqCliente;
    private javax.swing.JLabel etqEstadoComanda;
    private javax.swing.JLabel etqFechaCreacionComanda;
    private javax.swing.JLabel etqMontoVenta;
    private javax.swing.JLabel etqNombreCliente;
    private javax.swing.JLabel etqNombreUsuario;
    private javax.swing.JLabel etqTituloProductosComanda;
    private javax.swing.JLabel etqTotalVenta;
    private javax.swing.JLabel etqValorEstadoComanda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JPanel panelPrincipalInformacionComanda;
    // End of variables declaration//GEN-END:variables
}
