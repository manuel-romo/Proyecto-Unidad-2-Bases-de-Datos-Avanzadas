
package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.Usuario;
import itson.sistemarestaurantenegocio.IComandasBO;
import itson.sistemarestaurantenegocio.IIngredientesBO;
import itson.sistemarestaurantenegocio.IProductosBO;
import itson.sistemarestaurantenegocio.IUsuariosBO;
import itson.sistemarestaurantenegocio.excepciones.UsuarioInexistenteException;
import itson.sistemarestaurantenegocio.fabrica.FabricaObjetoNegocio;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class MenuPrincipal extends JFrame {

    private IUsuariosBO usuariosBO;
    private Long idUsuario;
    
    private static final Logger LOG = Logger.getLogger(MenuPrincipal.class.getName());
    

    public MenuPrincipal(IUsuariosBO usuariosBO, Long idUsuario) throws UsuarioInexistenteException {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        this.idUsuario = idUsuario;
        this.usuariosBO = usuariosBO;
        
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
    
    private void mostrarSeleccionComanda(){
        IComandasBO comandasBO = FabricaObjetoNegocio.crearComandasBO();
        try {
            ComandasPrincipal formularioSeleccionComanda = new ComandasPrincipal(usuariosBO, comandasBO, idUsuario);
            dispose();
            formularioSeleccionComanda.setVisible(true);
            
        } catch (UsuarioInexistenteException ex) {
            LOG.severe("ID de usuario inexistente " + ex.getMessage());
            JOptionPane.showMessageDialog(
                    this,
                    """
                        No se pudo recuperar la información del usuario. Es posible que haya sido modificada o eliminada.
                        Por favor, intente iniciar sesión de nuevo
                    """, 
                    "Error de sesión",
                    JOptionPane.ERROR_MESSAGE);
            mostrarInicioSesion();
        }
    }
    
    private void mostrarProductosPrincipal(){
        IProductosBO productosBO = FabricaObjetoNegocio.crearProductosBO();
        try {
            ProductosPrincipal formularioProductosPrincipal = new ProductosPrincipal(usuariosBO, productosBO, idUsuario);
            dispose();
            formularioProductosPrincipal.setVisible(true);
            
        } catch (UsuarioInexistenteException ex) {
            LOG.severe("ID de usuario inexistente " + ex.getMessage());
            JOptionPane.showMessageDialog(
                    this,
                    """
                        No se pudo recuperar la información del usuario. Es posible que haya sido modificada o eliminada.
                        Por favor, intente iniciar sesión de nuevo
                    """, 
                    "Error de sesión",
                    JOptionPane.ERROR_MESSAGE);
            mostrarInicioSesion();
        }
    }
    
    private void mostrarIngredientesPrincipal(){
        
        IIngredientesBO ingredientesBO = FabricaObjetoNegocio.crearIngredientesBO();
        try {
            IngredientesPrincipal formularioIngredientesPrincipal = new IngredientesPrincipal(usuariosBO, ingredientesBO, idUsuario);
            dispose();
            formularioIngredientesPrincipal.setVisible(true);
            
        } catch (UsuarioInexistenteException ex) {
            LOG.severe("ID de usuario inexistente " + ex.getMessage());
            JOptionPane.showMessageDialog(
                    this,
                    """
                        No se pudo recuperar la información del usuario. Es posible que haya sido modificada o eliminada.
                        Por favor, intente iniciar sesión de nuevo
                    """, 
                    "Error de sesión",
                    JOptionPane.ERROR_MESSAGE);
            mostrarInicioSesion();
        }
    }
    
    
    
    
    private void mostrarInicioSesion(){
        InciarSesion formularioInicioSesion = new InciarSesion(usuariosBO);
        dispose();
        formularioInicioSesion.setVisible(true);  
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        panelEncabezado = new javax.swing.JPanel();
        etqIcono = new javax.swing.JLabel();
        etqNombreUsuario1 = new javax.swing.JLabel();
        etqNombreUsuario = new javax.swing.JLabel();
        etqIconoUsuario = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnModuloComandas = new javax.swing.JButton();
        btnModuloProductos = new javax.swing.JButton();
        btnModuloIngredientes = new javax.swing.JButton();
        btnModuloClientesFrecuentes1 = new javax.swing.JButton();
        btnModuloClientesFrecuentes = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));

        panelPrincipal.setBackground(new java.awt.Color(232, 232, 232));

        panelEncabezado.setBackground(new java.awt.Color(250, 230, 188));

        etqIcono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoAplicacion.png"))); // NOI18N

        etqNombreUsuario1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        etqNombreUsuario1.setForeground(new java.awt.Color(0, 0, 0));
        etqNombreUsuario1.setText("Restaurante el Sahuaro");

        etqNombreUsuario.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqNombreUsuario.setForeground(new java.awt.Color(0, 0, 0));
        etqNombreUsuario.setText("Nombre de usuario");

        etqIconoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconoUsuario2.png"))); // NOI18N

        btnCerrarSesion.setBackground(new java.awt.Color(253, 244, 167));
        btnCerrarSesion.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(0, 0, 0));
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
                .addComponent(etqNombreUsuario1)
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
                        .addComponent(etqNombreUsuario1))
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

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnModuloComandas.setBackground(new java.awt.Color(255, 230, 188));
        btnModuloComandas.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnModuloComandas.setForeground(new java.awt.Color(0, 0, 0));
        btnModuloComandas.setText("Módulo de comandas");
        btnModuloComandas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModuloComandasActionPerformed(evt);
            }
        });

        btnModuloProductos.setBackground(new java.awt.Color(255, 230, 188));
        btnModuloProductos.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnModuloProductos.setForeground(new java.awt.Color(0, 0, 0));
        btnModuloProductos.setText("Módulo de productos");
        btnModuloProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModuloProductosActionPerformed(evt);
            }
        });

        btnModuloIngredientes.setBackground(new java.awt.Color(255, 230, 188));
        btnModuloIngredientes.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnModuloIngredientes.setForeground(new java.awt.Color(0, 0, 0));
        btnModuloIngredientes.setText("Módulo de ingredientes");
        btnModuloIngredientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModuloIngredientesActionPerformed(evt);
            }
        });

        btnModuloClientesFrecuentes1.setBackground(new java.awt.Color(255, 230, 188));
        btnModuloClientesFrecuentes1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnModuloClientesFrecuentes1.setForeground(new java.awt.Color(0, 0, 0));
        btnModuloClientesFrecuentes1.setText("Módulo de reportes");
        btnModuloClientesFrecuentes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModuloClientesFrecuentes1ActionPerformed(evt);
            }
        });

        btnModuloClientesFrecuentes.setBackground(new java.awt.Color(255, 230, 188));
        btnModuloClientesFrecuentes.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnModuloClientesFrecuentes.setForeground(new java.awt.Color(0, 0, 0));
        btnModuloClientesFrecuentes.setText("Módulo de clientes frecuentes");
        btnModuloClientesFrecuentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModuloClientesFrecuentesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(146, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnModuloClientesFrecuentes, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnModuloClientesFrecuentes1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModuloComandas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModuloProductos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModuloIngredientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(131, 131, 131))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(btnModuloComandas)
                .addGap(18, 18, 18)
                .addComponent(btnModuloProductos)
                .addGap(18, 18, 18)
                .addComponent(btnModuloIngredientes)
                .addGap(18, 18, 18)
                .addComponent(btnModuloClientesFrecuentes)
                .addGap(18, 18, 18)
                .addComponent(btnModuloClientesFrecuentes1)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Menú principal");

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addContainerGap(166, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(166, 166, 166))
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(257, 257, 257)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
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

    private void btnModuloProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModuloProductosActionPerformed
        mostrarProductosPrincipal();
    }//GEN-LAST:event_btnModuloProductosActionPerformed

    private void btnModuloComandasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModuloComandasActionPerformed
        mostrarSeleccionComanda();
    }//GEN-LAST:event_btnModuloComandasActionPerformed

    private void btnModuloIngredientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModuloIngredientesActionPerformed
        mostrarIngredientesPrincipal();
    }//GEN-LAST:event_btnModuloIngredientesActionPerformed

    private void btnModuloClientesFrecuentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModuloClientesFrecuentesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModuloClientesFrecuentesActionPerformed

    private void btnModuloClientesFrecuentes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModuloClientesFrecuentes1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModuloClientesFrecuentes1ActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarSesionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnModuloClientesFrecuentes;
    private javax.swing.JButton btnModuloClientesFrecuentes1;
    private javax.swing.JButton btnModuloComandas;
    private javax.swing.JButton btnModuloIngredientes;
    private javax.swing.JButton btnModuloProductos;
    private javax.swing.JLabel etqIcono;
    private javax.swing.JLabel etqIconoUsuario;
    private javax.swing.JLabel etqNombreUsuario;
    private javax.swing.JLabel etqNombreUsuario1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
