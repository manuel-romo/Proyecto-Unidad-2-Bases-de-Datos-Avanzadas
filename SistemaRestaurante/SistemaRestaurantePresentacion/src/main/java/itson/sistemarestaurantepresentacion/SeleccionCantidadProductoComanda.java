
package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantenegocio.excepciones.IdProductoNuloException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteConsultadoNoExisteException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinIdException;
import itson.sistemarestaurantenegocio.excepciones.ProductoConsultadoNoExisteException;
import itson.sistemarestaurantenegocio.interfaces.IProductosBO;
import itson.sistemarestaurantepresentacion.interfaces.IMediador;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class SeleccionCantidadProductoComanda extends JFrame {

    private IMediador control;
    private IProductosBO productosBO;
    private Long idProducto;
    private Producto producto;
    
    private static final Logger LOG = Logger.getLogger(SeleccionCantidadProductoComanda.class.getName());
    
    
    public SeleccionCantidadProductoComanda(IMediador control, IProductosBO productosBO, Long idProducto){
        this.control = control;
        this.productosBO = productosBO;
        this.idProducto = idProducto;
        
        initComponents();
        cargarNombreProducto();
    }
    
    private void cargarNombreProducto(){
        
        try {
            producto = productosBO.consultarProductoPorId(idProducto);
            
            String nombreProducto = producto.getNombre();
            
            etqIngresoCantidad.setText(etqIngresoCantidad.getText() + " " + nombreProducto);
            
        } catch (ProductoConsultadoNoExisteException ex) {
            LOG.severe("No existe un producto con el Id enviado. " + ex.getMessage());
            JOptionPane.showMessageDialog(
                this, 
                ex.getMessage(), 
                "Ha ocurrido un error al verificar las existencias del producto", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void aceptarCantidad(){
        
        String cantidadCadena = campoTextoCantidad.getText();
        
        if (cantidadCadena.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar una cantidad", 
                    "Sin cantidad", 
                    JOptionPane.ERROR_MESSAGE);;
        }
        try {
            //Se valida que sea una cantidad flotante.
            float cantidad = Float.parseFloat(cantidadCadena);
            
            try {
                boolean hayDisponibildad = productosBO.consultarDisponibilidadProducto(idProducto, cantidad);
                
                if(hayDisponibildad){
                     control.actualizarVentanaCantidadProductoSeleccionada(this, idProducto, cantidad);
                } else{
                    JOptionPane.showMessageDialog(
                    this, 
                    "No hay disponibilidad de ingredientes suficientes para preparar " + cantidad + " unidades de " + producto.getNombre(), 
                    "Ha ocurrido un error verificar las existencias del producto.", 
                    JOptionPane.ERROR_MESSAGE);
                }
                
                
                
            } catch (IdProductoNuloException ex) {
                LOG.severe("El Id del produto consultado es nulo. " + ex.getMessage());
                JOptionPane.showMessageDialog(
                    this, 
                    ex.getMessage(), 
                    "Error al verificar existencias del producto.", 
                    JOptionPane.ERROR_MESSAGE);
                
            } catch (ProductoConsultadoNoExisteException ex) {
                
                LOG.severe("El producto consultado no existe. " + ex.getMessage());
                JOptionPane.showMessageDialog(
                    this, 
                    ex.getMessage(), 
                    "Error al verificar existencias del producto.", 
                    JOptionPane.ERROR_MESSAGE);
                
            } catch (IngredienteSinIdException ex) {
                
                LOG.severe("Ingrediente de producto a verificar no tiene Id. " + ex.getMessage());
                JOptionPane.showMessageDialog(
                    this, 
                    ex.getMessage(), 
                    "Error al verificar existencias del producto.", 
                    JOptionPane.ERROR_MESSAGE);
            } catch (IngredienteConsultadoNoExisteException ex) {
                
               LOG.severe("El ingrediente consultado no existe. " + ex.getMessage());
                JOptionPane.showMessageDialog(
                    this, 
                    ex.getMessage(), 
                    "Error al verificar existencias del producto.", 
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar una cantidad numérica.", 
                    "Cantidad no numérica", 
                    JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    private void cerrar(){
        control.cerrarSeleccionCantidadProducto(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        etqIngresoCantidad = new javax.swing.JLabel();
        campoTextoCantidad = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 153));

        jPanel1.setBackground(new java.awt.Color(255, 235, 215));

        etqIngresoCantidad.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        etqIngresoCantidad.setText("Ingrese la cantidad a agregar del producto: ");

        campoTextoCantidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campoTextoCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTextoCantidadActionPerformed(evt);
            }
        });

        btnAceptar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(etqIngresoCantidad))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(campoTextoCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(125, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnCancelar)
                .addGap(29, 29, 29)
                .addComponent(btnAceptar)
                .addGap(104, 104, 104))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(etqIngresoCantidad)
                .addGap(34, 34, 34)
                .addComponent(campoTextoCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoTextoCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTextoCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTextoCantidadActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        aceptarCantidad();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
       cerrar();
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JTextField campoTextoCantidad;
    private javax.swing.JLabel etqIngresoCantidad;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
