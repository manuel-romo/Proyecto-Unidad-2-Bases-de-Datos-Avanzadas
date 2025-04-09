
package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.Usuario;
import itson.sistemarestaurantenegocio.interfaces.IComandasBO;
import itson.sistemarestaurantenegocio.interfaces.IIngredientesBO;
import itson.sistemarestaurantenegocio.interfaces.IProductosBO;
import itson.sistemarestaurantenegocio.interfaces.IUsuariosBO;
import itson.sistemarestaurantenegocio.excepciones.UsuarioInexistenteException;
import itson.sistemarestaurantenegocio.fabrica.FabricaObjetoNegocio;
import itson.sistemarestaurantepresentacion.excepciones.SesionUsuarioInvalidaException;
import itson.sistemarestaurantepresentacion.interfaces.IMediador;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class MenuPrincipal extends JFrame {

    private IMediador control;
    private IUsuariosBO usuariosBO;
    private Long idUsuario;
    
    private static final Logger LOG = Logger.getLogger(MenuPrincipal.class.getName());
    

    public MenuPrincipal(IMediador control, IUsuariosBO usuariosBO) {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        try {
            this.idUsuario = SesionUsuario.getInstance().getIdUsuario();
        } catch (SesionUsuarioInvalidaException ex) {
            LOG.log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(
                    null, 
                    "Ha ocurrido un error en su sesión.",
                    "Error de sesión",
                    JOptionPane.ERROR_MESSAGE);
            
            mostrarPantallaInicial();
        }
        
        this.control = control;  
        this.usuariosBO = usuariosBO;
        
        panelBaseEncabezado.add(new Encabezado());
    }
    
    private void mostrarPantallaInicial(){
        control.mostrarPantallaInicial(this);
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
        control.mostrarProductosPrincipal(this);
    }
    
    private void mostrarIngredientesPrincipal(){
        control.mostrarIngredientesPrincipal(this);
       
    }
    
    
    
    
    private void mostrarInicioSesion(){
        control.mostrarInicioSesion(this);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnModuloProductos = new javax.swing.JButton();
        btnModuloComandas = new javax.swing.JButton();
        btnModuloIngredientes = new javax.swing.JButton();
        btnModuloClientesFrecuentes = new javax.swing.JButton();
        btnModuloClientesFrecuentes1 = new javax.swing.JButton();
        panelBaseEncabezado = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));

        btnModuloProductos.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnModuloProductos.setText("Módulo de productos");
        btnModuloProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModuloProductosActionPerformed(evt);
            }
        });

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

        panelBaseEncabezado.setBackground(new java.awt.Color(250, 230, 188));
        panelBaseEncabezado.setPreferredSize(new java.awt.Dimension(870, 84));
        panelBaseEncabezado.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(263, 263, 263)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnModuloClientesFrecuentes1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModuloProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModuloComandas, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModuloIngredientes, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModuloClientesFrecuentes))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(panelBaseEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBaseEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(btnModuloComandas)
                .addGap(32, 32, 32)
                .addComponent(btnModuloProductos)
                .addGap(30, 30, 30)
                .addComponent(btnModuloIngredientes)
                .addGap(34, 34, 34)
                .addComponent(btnModuloClientesFrecuentes)
                .addGap(41, 41, 41)
                .addComponent(btnModuloClientesFrecuentes1)
                .addContainerGap(163, Short.MAX_VALUE))
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
    private javax.swing.JPanel panelBaseEncabezado;
    // End of variables declaration//GEN-END:variables
}
