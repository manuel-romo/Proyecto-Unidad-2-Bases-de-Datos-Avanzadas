
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
        InicioSesion formularioInicioSesion = new InicioSesion(usuariosBO);
        dispose();
        formularioInicioSesion.setVisible(true);  
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnModuloProductos = new javax.swing.JButton();
        panelEncabezado = new javax.swing.JPanel();
        etqNombreUsuario = new javax.swing.JLabel();
        btnModuloComandas = new javax.swing.JButton();
        btnModuloIngredientes = new javax.swing.JButton();
        btnModuloClientesFrecuentes = new javax.swing.JButton();
        btnModuloClientesFrecuentes1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));

        btnModuloProductos.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnModuloProductos.setText("Módulo de productos");
        btnModuloProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModuloProductosActionPerformed(evt);
            }
        });

        panelEncabezado.setBackground(new java.awt.Color(255, 255, 204));

        etqNombreUsuario.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        etqNombreUsuario.setText("Nombre Usuario");

        javax.swing.GroupLayout panelEncabezadoLayout = new javax.swing.GroupLayout(panelEncabezado);
        panelEncabezado.setLayout(panelEncabezadoLayout);
        panelEncabezadoLayout.setHorizontalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(etqNombreUsuario)
                .addContainerGap(782, Short.MAX_VALUE))
        );
        panelEncabezadoLayout.setVerticalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(etqNombreUsuario)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        btnModuloComandas.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnModuloComandas.setText("Módulo de comandas");
        btnModuloComandas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModuloComandasActionPerformed(evt);
            }
        });

        btnModuloIngredientes.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnModuloIngredientes.setText("Módulo de ingredientes");
        btnModuloIngredientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModuloIngredientesActionPerformed(evt);
            }
        });

        btnModuloClientesFrecuentes.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnModuloClientesFrecuentes.setText("Módulo de clientes frecuentes");
        btnModuloClientesFrecuentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModuloClientesFrecuentesActionPerformed(evt);
            }
        });

        btnModuloClientesFrecuentes1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnModuloClientesFrecuentes1.setText("Módulo de reportes");
        btnModuloClientesFrecuentes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModuloClientesFrecuentes1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(369, 369, 369)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnModuloComandas, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModuloProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModuloIngredientes, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModuloClientesFrecuentes)
                    .addComponent(btnModuloClientesFrecuentes1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107)
                .addComponent(btnModuloComandas)
                .addGap(18, 18, 18)
                .addComponent(btnModuloProductos)
                .addGap(18, 18, 18)
                .addComponent(btnModuloIngredientes)
                .addGap(18, 18, 18)
                .addComponent(btnModuloClientesFrecuentes)
                .addGap(18, 18, 18)
                .addComponent(btnModuloClientesFrecuentes1)
                .addContainerGap(146, Short.MAX_VALUE))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnModuloClientesFrecuentes;
    private javax.swing.JButton btnModuloClientesFrecuentes1;
    private javax.swing.JButton btnModuloComandas;
    private javax.swing.JButton btnModuloIngredientes;
    private javax.swing.JButton btnModuloProductos;
    private javax.swing.JLabel etqNombreUsuario;
    private javax.swing.JPanel panelEncabezado;
    // End of variables declaration//GEN-END:variables
}
