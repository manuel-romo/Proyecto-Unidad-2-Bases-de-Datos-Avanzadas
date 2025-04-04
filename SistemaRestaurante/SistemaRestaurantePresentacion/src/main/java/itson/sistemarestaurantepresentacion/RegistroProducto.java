
package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.TipoProducto;
import itson.sistemarestaurantedominio.Usuario;
import itson.sistemarestaurantenegocio.IProductosBO;
import itson.sistemarestaurantenegocio.IUsuariosBO;
import itson.sistemarestaurantenegocio.excepciones.UsuarioInexistenteException;
import itson.sistemarestaurantepresentacion.utils.ImagenesUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class RegistroProducto extends JFrame {

    private IUsuariosBO usuariosBO;
    private IProductosBO productosBO;
    private Long idUsuario;
    
    private static final Logger LOG = Logger.getLogger(RegistroProducto.class.getName());
    
    public RegistroProducto(IUsuariosBO usuariosBO, IProductosBO productosBO, Long idUsuario) throws UsuarioInexistenteException {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        this.usuariosBO = usuariosBO;
        this.productosBO = productosBO;
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
    
    private void mostrarSelectorArchivos(){

        JFileChooser fileChooser = new JFileChooser();
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Imagen JPG y PNG", 
                "jpg", "png");
        
        fileChooser.setFileFilter(filter);
        
        int respuesta = fileChooser.showOpenDialog(this);
        
        if (respuesta == JFileChooser.APPROVE_OPTION) {
            Path pathImagenSeleccionada = fileChooser.getSelectedFile().toPath();
             
            try {
                byte[] bytesImagenSeleccionada = Files.readAllBytes(pathImagenSeleccionada);
                
                ImageIcon imagenProducto = new ImageIcon(bytesImagenSeleccionada);
                
                ImageIcon imagenRedimensionada = ImagenesUtils.redimensionarImagen(imagenProducto, 200, 200);
                
                etqImagenProducto.setIcon(imagenRedimensionada);
                
            } catch (IOException ex) {
                LOG.severe("Error al cargar la imagen. " + ex.getMessage());
                JOptionPane.showMessageDialog(
                    this,
                    """
                        No se pudo cargar la imagen seleccionada.
                    """, 
                    "No se pudo cargar la imagen",
                            JOptionPane.ERROR_MESSAGE);
            }
            
            
        }
    }
    
    

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        etqNombreUsuario = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        etqSeleccionarIngrediente = new javax.swing.JLabel();
        etqBuscarIngrediente = new javax.swing.JLabel();
        campoTextoBuscarIngrediente = new javax.swing.JTextField();
        etqPanelPrecioTipoProducto = new javax.swing.JPanel();
        etqSignoPesos = new javax.swing.JLabel();
        campoTextoPrecioProducto = new javax.swing.JTextField();
        etqPrecioProducto1 = new javax.swing.JLabel();
        etqPrecioProducto2 = new javax.swing.JLabel();
        comboBoxTipoProducto = new javax.swing.JComboBox<>(TipoProducto.values());
        etqRegistrarProducto = new javax.swing.JLabel();
        btnSubirFotografia = new javax.swing.JButton();
        etqImagenProducto = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        etqNombreUsuario.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqNombreUsuario.setText("Nombre de Usuario");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(etqNombreUsuario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(etqNombreUsuario)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));

        etqSeleccionarIngrediente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqSeleccionarIngrediente.setText("Seleccionar ingredientes");

        etqBuscarIngrediente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        etqBuscarIngrediente.setText("Buscar producto por nombre o unidad:");

        campoTextoBuscarIngrediente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(etqSeleccionarIngrediente)
                .addGap(173, 173, 173))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etqBuscarIngrediente)
                    .addComponent(campoTextoBuscarIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(etqSeleccionarIngrediente)
                .addGap(18, 18, 18)
                .addComponent(etqBuscarIngrediente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoTextoBuscarIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(292, Short.MAX_VALUE))
        );

        etqPanelPrecioTipoProducto.setBackground(new java.awt.Color(255, 255, 204));

        etqSignoPesos.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqSignoPesos.setText("$");

        campoTextoPrecioProducto.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        campoTextoPrecioProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTextoPrecioProductoActionPerformed(evt);
            }
        });

        etqPrecioProducto1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqPrecioProducto1.setText("Precio del producto");

        etqPrecioProducto2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqPrecioProducto2.setText("Precio del producto");

        comboBoxTipoProducto.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        comboBoxTipoProducto.setModel(new DefaultComboBoxModel<>(TipoProducto.values()));
        comboBoxTipoProducto.setSelectedItem(TipoProducto.PLATILLO
        );
        comboBoxTipoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTipoProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout etqPanelPrecioTipoProductoLayout = new javax.swing.GroupLayout(etqPanelPrecioTipoProducto);
        etqPanelPrecioTipoProducto.setLayout(etqPanelPrecioTipoProductoLayout);
        etqPanelPrecioTipoProductoLayout.setHorizontalGroup(
            etqPanelPrecioTipoProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(etqPanelPrecioTipoProductoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(etqSignoPesos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(etqPanelPrecioTipoProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etqPrecioProducto1)
                    .addComponent(campoTextoPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(etqPanelPrecioTipoProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(comboBoxTipoProducto, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(etqPrecioProducto2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        etqPanelPrecioTipoProductoLayout.setVerticalGroup(
            etqPanelPrecioTipoProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(etqPanelPrecioTipoProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etqPrecioProducto1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(etqPanelPrecioTipoProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoTextoPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etqSignoPesos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(etqPrecioProducto2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboBoxTipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        etqRegistrarProducto.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqRegistrarProducto.setText("Registrar producto");

        btnSubirFotografia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSubirFotografia.setText("Subir fotografía");
        btnSubirFotografia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirFotografiaActionPerformed(evt);
            }
        });

        etqImagenProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenPlatilloPredeterminada.png"))); // NOI18N

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnRegistrar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etqRegistrarProducto)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(etqImagenProducto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSubirFotografia)
                                .addGap(53, 53, 53))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(etqPanelPrecioTipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(448, 448, 448)
                .addComponent(btnCancelar)
                .addGap(18, 18, 18)
                .addComponent(btnRegistrar)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(etqRegistrarProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(etqImagenProducto)
                            .addComponent(btnSubirFotografia))
                        .addGap(30, 30, 30)
                        .addComponent(etqPanelPrecioTipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnRegistrar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoTextoPrecioProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTextoPrecioProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTextoPrecioProductoActionPerformed

    private void btnSubirFotografiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirFotografiaActionPerformed
        mostrarSelectorArchivos();
    }//GEN-LAST:event_btnSubirFotografiaActionPerformed

    private void comboBoxTipoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTipoProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxTipoProductoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnSubirFotografia;
    private javax.swing.JTextField campoTextoBuscarIngrediente;
    private javax.swing.JTextField campoTextoPrecioProducto;
    private javax.swing.JComboBox<TipoProducto> comboBoxTipoProducto;
    private javax.swing.JLabel etqBuscarIngrediente;
    private javax.swing.JLabel etqImagenProducto;
    private javax.swing.JLabel etqNombreUsuario;
    private javax.swing.JPanel etqPanelPrecioTipoProducto;
    private javax.swing.JLabel etqPrecioProducto1;
    private javax.swing.JLabel etqPrecioProducto2;
    private javax.swing.JLabel etqRegistrarProducto;
    private javax.swing.JLabel etqSeleccionarIngrediente;
    private javax.swing.JLabel etqSignoPesos;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
