
package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.UnidadIngrediente;
import itson.sistemarestaurantedominio.dtos.IngredienteActualizadoDTO;
import itson.sistemarestaurantenegocio.interfaces.IIngredientesBO;
import itson.sistemarestaurantenegocio.excepciones.CantidadIngredienteInvalidaException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteBuscadoNoExisteException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinCantidadException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinDireccionImagenException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinIdException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinNombreException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinUnidadException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteYaExisteException;
import itson.sistemarestaurantenegocio.excepciones.NombreIngredienteInvalidoException;
import itson.sistemarestaurantepersistencia.excepciones.IngredienteNoExisteException;
import itson.sistemarestaurantepresentacion.excepciones.ImagenNoEncontradaException;
import itson.sistemarestaurantepresentacion.interfaces.IMediador;
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


public class EditarIngrediente extends JFrame {

    private IMediador control;
    private IIngredientesBO ingredientesBO;
    private Long idIngrediente;
    private String direccionImagenIngrediente;
    private DatosEncabezado datosEncabezado;
    
    private static final Logger LOG = Logger.getLogger(EditarIngrediente.class.getName());

    public EditarIngrediente(IMediador control, IIngredientesBO ingredientesBO, Long idIngrediente) {
        datosEncabezado = DatosEncabezado.getInstance();
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        
        this.control = control;
        this.ingredientesBO = ingredientesBO;
        this.idIngrediente = idIngrediente;
        this.direccionImagenIngrediente = "/imagenIngredientePredeterminada.png";
        
        panelBaseEncabezado.add(new Encabezado());
        
        cargarDatosIngrediente();
        
    }
    
    private void cargarDatosIngrediente(){
        
        Ingrediente ingredienteRecuperado;
        try {
            ingredienteRecuperado = ingredientesBO.consultarIngrediente(idIngrediente);
            
            campoTextoNombreIngrediente.setText(ingredienteRecuperado.getNombre());
            campoTextoCantidadProducto.setText(String.valueOf(ingredienteRecuperado.getCantidad()));
            comboBoxUnidadIngrediente.setSelectedItem(ingredienteRecuperado.getUnidad());

            ImageIcon imagenIngrediente;
            try {
                imagenIngrediente = ImagenesUtils.obtenerImagen(ingredienteRecuperado.getDireccionImagen());
            } catch (ImagenNoEncontradaException ex) {
                imagenIngrediente = new ImageIcon(getClass().getResource("imagenIngredientePredeterminada.png"));
                LOG.severe("Error al cargar la imagen del ingrediente. " + ex.getMessage());
            }

            etqImagenIngrediente.setIcon(imagenIngrediente);
        } catch (IngredienteBuscadoNoExisteException ex) {
            LOG.severe("Error al mostrar el ingrediente " + ex.getMessage());
            
            JOptionPane.showMessageDialog(
            this, 
            "Error al mostrar los datos del ingrediente.", 
            "Error",
            JOptionPane.ERROR_MESSAGE);
            
            control.mostrarIngredientesPrincipal(this);
            
        }
        
        
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
                        JOptionPane.showMessageDialog(
                        this,
                        "Error al crear la carpeta \"imagenes\". ",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);

                        LOG.severe("Error al crear la carpeta \"imagenes\". " + ex.getMessage());
                        this.direccionImagenIngrediente = "/imagenIngredientePredeterminada.png";
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
                        this.direccionImagenIngrediente = "/imagenIngredientePredeterminada.png";
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
                        
                        Path pathPresentacion = Paths.get(System.getProperty("user.dir"));
                        
                        direccionImagenIngrediente = pathPresentacion.relativize(nuevaDireccionImagen).toString();
                    
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(
                            this,
                            """
                            No se pudo cargar la imagen seleccionada.
                            """, 
                            "No se pudo cargar la imagen",
                                    JOptionPane.ERROR_MESSAGE);

                        LOG.severe("Error al cargar la imagen. " + ex.getMessage());
                        this.direccionImagenIngrediente = "/imagenIngredientePredeterminada.png";
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(
                            this, 
                            "Error al guardar la imagen en la carpeta \"imagenesIngredientes\".", 
                            "Error al guardar la imagen",
                            JOptionPane.ERROR_MESSAGE);
                    
                    LOG.severe("Error al guardar la imagen. " + ex.getMessage());
                    this.direccionImagenIngrediente = "/imagenIngredientePredeterminada.png";
                }
                
            
            
        }
    }
    
    
    private void actualizarIngrediente(){
        
        String nombreIngrediente = campoTextoNombreIngrediente.getText();
        UnidadIngrediente unidad = (UnidadIngrediente) comboBoxUnidadIngrediente.getSelectedItem();
        Float cantidad = Float.valueOf(campoTextoCantidadProducto.getText());      
        String direccionImagenString = direccionImagenIngrediente;
        
        IngredienteActualizadoDTO ingredienteActualizadoDTO = 
                new IngredienteActualizadoDTO(idIngrediente, nombreIngrediente, unidad, cantidad, direccionImagenString);
        
        try {
            ingredientesBO.actualizarIngrediente(ingredienteActualizadoDTO);
            JOptionPane.showMessageDialog(this, "El ingrediente se ha actualizado exitosamente", 
                    "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (NombreIngredienteInvalidoException ex) {
            LOG.severe("Nombre de ingrediente inváldio. " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Nombre de ingrediente inválido", JOptionPane.ERROR_MESSAGE);
        } catch (CantidadIngredienteInvalidaException ex){
            LOG.severe("Cantidad del ingrediente inválida. " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Cantidad del ingrediente inválida", JOptionPane.ERROR_MESSAGE);  
        } catch(IngredienteYaExisteException ex){
            LOG.severe("Ingrediente existente. " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Ingrediente existente", JOptionPane.ERROR_MESSAGE);
        } catch (IngredienteSinUnidadException ex) {
            LOG.severe("Ingrediente sin unidad. " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Ingrediente sin unidad", JOptionPane.ERROR_MESSAGE);
        } catch (IngredienteSinNombreException ex) {
            LOG.severe("Ingrediente sin nombre. " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Ingrediente sin nombre", JOptionPane.ERROR_MESSAGE);
        } catch (IngredienteSinDireccionImagenException ex) {
            LOG.severe("Ingrediente sin dirección de imagen. " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Ingrediente sin dirección de imagen", JOptionPane.ERROR_MESSAGE);
        } catch (IngredienteSinCantidadException ex) {
            LOG.severe("Ingrediente sin cantidad. " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Ingrediente sin cantidad", JOptionPane.ERROR_MESSAGE);
        } catch (IngredienteNoExisteException ex) {
            LOG.severe("Ingrediente no existe. " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Ingrediente no existe", JOptionPane.ERROR_MESSAGE);
        } catch (IngredienteSinIdException ex) {
            LOG.severe("Ingrediente no tiene Id. " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Ingrediente no tiene Id", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void mostrarIngredientesPrincipal(){
        control.mostrarIngredientesPrincipal(this);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
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
        panelBaseEncabezado = new javax.swing.JPanel();

        jScrollPane1.setViewportView(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelInformacionIngrediente.setBackground(new java.awt.Color(204, 255, 204));

        etqDatosNuevoIngrediente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqDatosNuevoIngrediente.setText("Datos del ingrediente");

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
        btnRegistrar.setText("Actualizar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCancelar.setText("Volver");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        panelBaseEncabezado.setBackground(new java.awt.Color(250, 230, 188));

        javax.swing.GroupLayout panelBaseEncabezadoLayout = new javax.swing.GroupLayout(panelBaseEncabezado);
        panelBaseEncabezado.setLayout(panelBaseEncabezadoLayout);
        panelBaseEncabezadoLayout.setHorizontalGroup(
            panelBaseEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelBaseEncabezadoLayout.setVerticalGroup(
            panelBaseEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 84, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar)
                .addGap(44, 44, 44)
                .addComponent(btnRegistrar)
                .addGap(322, 322, 322))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(etqImagenIngrediente)
                        .addGap(29, 29, 29)
                        .addComponent(btnSubirFotografia))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(etqRegistrarIngrediente)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(panelInformacionIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addComponent(panelBaseEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBaseEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelInformacionIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(etqRegistrarIngrediente)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(etqImagenIngrediente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 179, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSubirFotografia)
                                .addGap(184, 184, 184)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrar)
                    .addComponent(btnCancelar))
                .addGap(15, 15, 15))
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
        actualizarIngrediente();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        mostrarIngredientesPrincipal();
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
    private javax.swing.JLabel etqRegistrarIngrediente;
    private javax.swing.JLabel etqUnidadIngrediente;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JPanel panelBaseEncabezado;
    private javax.swing.JPanel panelInformacionIngrediente;
    // End of variables declaration//GEN-END:variables
}
