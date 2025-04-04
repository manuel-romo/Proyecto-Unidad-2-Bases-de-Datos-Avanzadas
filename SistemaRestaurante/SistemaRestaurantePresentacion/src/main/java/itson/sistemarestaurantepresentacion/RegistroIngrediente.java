
package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.UnidadIngrediente;
import itson.sistemarestaurantedominio.Usuario;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import itson.sistemarestaurantenegocio.IIngredientesBO;
import itson.sistemarestaurantenegocio.IUsuariosBO;
import itson.sistemarestaurantenegocio.excepciones.CantidadIngredienteInvalidaException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteYaExisteException;
import itson.sistemarestaurantenegocio.excepciones.NombreIngredienteInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.UsuarioInexistenteException;
import itson.sistemarestaurantepresentacion.utils.ImagenesUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class RegistroIngrediente extends JFrame {

    private IUsuariosBO usuariosBO;
    private IIngredientesBO ingredientesBO;
    private Long idUsuario;
    private String rutaImagenIngrediente;
    
    private static final Logger LOG = Logger.getLogger(RegistroIngrediente.class.getName());

    public RegistroIngrediente(IUsuariosBO usuariosBO, IIngredientesBO ingredientesBO, Long idUsuario) throws UsuarioInexistenteException {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        this.usuariosBO = usuariosBO;
        this.ingredientesBO = ingredientesBO;
        this.idUsuario = idUsuario;
        this.rutaImagenIngrediente = "/imagenIngredientePredeterminada.png";
        
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
             
            
                
                String carpetaProyecto = System.getProperty("user.dir");
                
                Path imagenes = Paths.get(carpetaProyecto, "imagenes");

                if (!Files.exists(imagenes)) {
                    try {
                        Files.createDirectories(imagenes);
                        JOptionPane.showMessageDialog(
                        this,
                        "Se ha creado la carpeta \"imagenes\" en la ruta: " + imagenes.toAbsolutePath(),
                        "Carpeta \"imagenes\" creada",
                        JOptionPane.INFORMATION_MESSAGE);
                        
                    } catch (IOException ex) {
                        System.err.println("Error al crear la carpeta \"imagenes\". " + ex.getMessage());
                        LOG.severe("Error al crear la carpeta \"imagenes\". " + ex.getMessage());
                        this.rutaImagenIngrediente = "/imagenIngredientePredeterminada.png";
                        return;
                    }
                }
                
                Path imagenesIngredientes = Paths.get(imagenes.toString(), "imagenesIngredientes");
                
                if (!Files.exists(imagenesIngredientes)) {
                    try {
                        Files.createDirectories(imagenesIngredientes);
                        JOptionPane.showMessageDialog(
                        this,
                        "Se ha creado la carpeta \"imagenesIngredientes\" en la ruta: " + imagenesIngredientes.toAbsolutePath(),
                        "Carpeta \"imagenesIngredientes\" creada",
                        JOptionPane.INFORMATION_MESSAGE);
                        
                    } catch (IOException ex) {
                        System.err.println("Error al crear la carpeta \"imagenesIngredientes\". " + ex.getMessage());
                        LOG.severe("Error al crear la carpeta \"imagenesIngredientes\". " + ex.getMessage());
                        this.rutaImagenIngrediente = "/imagenIngredientePredeterminada.png";
                        return;
                    }
                }
                
                try {
 
                    Path nuevaDireccionImagen = imagenesIngredientes.resolve(pathImagenSeleccionada.getFileName());
 
                    Files.copy(pathImagenSeleccionada, nuevaDireccionImagen, StandardCopyOption.REPLACE_EXISTING);
                    
                    
                    JOptionPane.showMessageDialog(
                            this, 
                            "Se ha guardado la imagen en la carpeta: " + imagenesIngredientes.toString(), 
                            "Imagen guardada",
                            JOptionPane.INFORMATION_MESSAGE);
                    
                    try {
                        byte[] bytesImagenSeleccionada = Files.readAllBytes(pathImagenSeleccionada);

                        ImageIcon imagenProducto = new ImageIcon(bytesImagenSeleccionada);

                        ImageIcon imagenRedimensionada = ImagenesUtils.redimensionarImagen(imagenProducto, 200, 200);

                        etqImagenIngrediente.setIcon(imagenRedimensionada);
                        
                        rutaImagenIngrediente = nuevaDireccionImagen.toString();
                    
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(
                            this,
                            """
                            No se pudo cargar la imagen seleccionada.
                            """, 
                            "No se pudo cargar la imagen",
                                    JOptionPane.ERROR_MESSAGE);

                        LOG.severe("Error al cargar la imagen. " + ex.getMessage());
                        this.rutaImagenIngrediente = "/imagenIngredientePredeterminada.png";
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(
                            this, 
                            "Error al guardar la imagen en la carpeta \"imagenesIngredientes\".", 
                            "Error al guardar la imagen",
                            JOptionPane.ERROR_MESSAGE);
                    
                    LOG.severe("Error al guardar la imagen. " + ex.getMessage());
                    this.rutaImagenIngrediente = "/imagenIngredientePredeterminada.png";
                }
                
            
            
        }
    }
    
    
    private void registrarIngrediente(){
        
        String nombreIngrediente = campoTextoNombreIngrediente.getText();
        UnidadIngrediente unidad = (UnidadIngrediente) comboBoxUnidadIngrediente.getSelectedItem();
        String cantidad = campoTextoCantidadProducto.getText();      
        String direccionImagenString = rutaImagenIngrediente;
        
        NuevoIngredienteDTO nuevoIngredienteDTO = new NuevoIngredienteDTO(nombreIngrediente, unidad, cantidad, direccionImagenString);
        
        try {
            ingredientesBO.registrarIngrediente(nuevoIngredienteDTO);
            JOptionPane.showMessageDialog(this, "El ingrediente se ha registrado exitosamente", 
                    "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (NombreIngredienteInvalidoException ex) {
            LOG.severe("Nombre de ingrediente inváldio. " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Nombre de ingrediente inválido", JOptionPane.ERROR_MESSAGE);
        } catch (CantidadIngredienteInvalidaException ex){
            LOG.severe("Cantidad del ingrediente inválida. " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Cantidad del ingrediente inválida", JOptionPane.ERROR_MESSAGE);  
        } catch(IngredienteYaExisteException ex){
            LOG.severe("Ingrediente existente. " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Ingrediente existente", JOptionPane.ERROR_MESSAGE);
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel1 = new javax.swing.JPanel();
        etqNombreUsuario = new javax.swing.JLabel();
        panelInformacionIngrediente = new javax.swing.JPanel();
        etqDatosNuevoIngrediente = new javax.swing.JLabel();
        etqNombreIngrediente = new javax.swing.JLabel();
        campoTextoNombreIngrediente = new javax.swing.JTextField();
        comboBoxUnidadIngrediente = new javax.swing.JComboBox<>();
        etqUnidadIngrediente = new javax.swing.JLabel();
        etqCantidadIngrediente = new javax.swing.JLabel();
        campoTextoCantidadProducto = new javax.swing.JTextField();
        etqImagenIngrediente = new javax.swing.JLabel();
        btnSubirFotografia = new javax.swing.JButton();
        etqRegistrarIngrediente = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        jScrollPane1.setViewportView(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        etqNombreUsuario.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqNombreUsuario.setText("Nombre de Usuario");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(etqNombreUsuario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(etqNombreUsuario)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        panelInformacionIngrediente.setBackground(new java.awt.Color(255, 255, 204));

        etqDatosNuevoIngrediente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqDatosNuevoIngrediente.setText("Datos del nuevo ingrediente");

        etqNombreIngrediente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        etqNombreIngrediente.setText("Nombre");

        campoTextoNombreIngrediente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        comboBoxUnidadIngrediente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        comboBoxUnidadIngrediente.setModel(new DefaultComboBoxModel<>(UnidadIngrediente.values()));
        comboBoxUnidadIngrediente.setSelectedItem(UnidadIngrediente.GRAMO);
        comboBoxUnidadIngrediente.setToolTipText("");
        comboBoxUnidadIngrediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxUnidadIngredienteActionPerformed(evt);
            }
        });

        etqUnidadIngrediente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        etqUnidadIngrediente.setText("Unidad del ingrediente");

        etqCantidadIngrediente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        etqCantidadIngrediente.setText("Cantidad");

        campoTextoCantidadProducto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout panelInformacionIngredienteLayout = new javax.swing.GroupLayout(panelInformacionIngrediente);
        panelInformacionIngrediente.setLayout(panelInformacionIngredienteLayout);
        panelInformacionIngredienteLayout.setHorizontalGroup(
            panelInformacionIngredienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacionIngredienteLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panelInformacionIngredienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etqNombreIngrediente)
                    .addComponent(etqDatosNuevoIngrediente)
                    .addComponent(campoTextoNombreIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelInformacionIngredienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(comboBoxUnidadIngrediente, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(etqUnidadIngrediente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(etqCantidadIngrediente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(campoTextoCantidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        panelInformacionIngredienteLayout.setVerticalGroup(
            panelInformacionIngredienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacionIngredienteLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(etqDatosNuevoIngrediente)
                .addGap(18, 18, 18)
                .addComponent(etqNombreIngrediente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoTextoNombreIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(etqUnidadIngrediente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBoxUnidadIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(etqCantidadIngrediente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoTextoCantidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
        );

        etqImagenIngrediente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenIngredientePredeterminada.png"))); // NOI18N

        btnSubirFotografia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSubirFotografia.setText("Subir fotografía");
        btnSubirFotografia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirFotografiaActionPerformed(evt);
            }
        });

        etqRegistrarIngrediente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqRegistrarIngrediente.setText("Registrar ingrediente");

        btnRegistrar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(etqImagenIngrediente)
                        .addGap(76, 76, 76)
                        .addComponent(btnSubirFotografia))
                    .addComponent(etqRegistrarIngrediente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(panelInformacionIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar)
                .addGap(44, 44, 44)
                .addComponent(btnRegistrar)
                .addGap(375, 375, 375))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(220, 220, 220)
                        .addComponent(btnSubirFotografia))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(etqRegistrarIngrediente)
                        .addGap(18, 18, 18)
                        .addComponent(etqImagenIngrediente))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(panelInformacionIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrar)
                    .addComponent(btnCancelar))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxUnidadIngredienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxUnidadIngredienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxUnidadIngredienteActionPerformed

    private void btnSubirFotografiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirFotografiaActionPerformed
        mostrarSelectorArchivos();
    }//GEN-LAST:event_btnSubirFotografiaActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        registrarIngrediente();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnSubirFotografia;
    private javax.swing.JTextField campoTextoCantidadProducto;
    private javax.swing.JTextField campoTextoNombreIngrediente;
    private javax.swing.JComboBox<UnidadIngrediente> comboBoxUnidadIngrediente;
    private javax.swing.JLabel etqCantidadIngrediente;
    private javax.swing.JLabel etqDatosNuevoIngrediente;
    private javax.swing.JLabel etqImagenIngrediente;
    private javax.swing.JLabel etqNombreIngrediente;
    private javax.swing.JLabel etqNombreUsuario;
    private javax.swing.JLabel etqRegistrarIngrediente;
    private javax.swing.JLabel etqUnidadIngrediente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JPanel panelInformacionIngrediente;
    // End of variables declaration//GEN-END:variables
}
