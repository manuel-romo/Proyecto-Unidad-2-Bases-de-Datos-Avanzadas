/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantedominio.TipoProducto;
import itson.sistemarestaurantedominio.dtos.ProductoActualizadoDTO;
import itson.sistemarestaurantenegocio.excepciones.NombreProductoInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.PrecioProductoInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.ProductoConsultadoNoExisteException;
import itson.sistemarestaurantenegocio.excepciones.ProductoSinDireccionImagenException;
import itson.sistemarestaurantenegocio.excepciones.ProductoSinIdException;
import itson.sistemarestaurantenegocio.excepciones.ProductoSinNombreException;
import itson.sistemarestaurantenegocio.excepciones.ProductoSinPrecioException;
import itson.sistemarestaurantenegocio.excepciones.ProductoSinTipoException;
import itson.sistemarestaurantenegocio.excepciones.ProductoYaExisteException;
import itson.sistemarestaurantenegocio.interfaces.IProductosBO;
import itson.sistemarestaurantepersistencia.excepciones.ProductoNoExisteException;
import itson.sistemarestaurantepresentacion.excepciones.ImagenNoEncontradaException;
import itson.sistemarestaurantepresentacion.interfaces.IMediador;
import itson.sistemarestaurantepresentacion.utils.ImagenesUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ramuk
 */
public class EdicionProducto extends javax.swing.JFrame {
    
    private IMediador control;
    private IProductosBO productosBO;
    private Long idProducto;
    private String direccionImagenProducto;
    private DatosEncabezado datosEncabezado;
    
    private static final Logger LOG = Logger.getLogger(EdicionProducto.class.getName());
    
    /**
     * Creates new form EditarProducto
     */
    public EdicionProducto(IMediador control, IProductosBO productosBO, Long idProducto) {
        datosEncabezado = DatosEncabezado.getInstance();
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        
        this.control = control;
        this.productosBO = productosBO;
        this.idProducto = idProducto;
        this.direccionImagenProducto = "/imagenIngredientePredeterminada.png";
        
        panelBaseEncabezado.add(new Encabezado());
        
        cargarDatosProducto();
        
    }
    
    private void cargarDatosProducto(){
        Producto prodctoRecuperado;
        try {
            Producto productoRecuperado = productosBO.consultarProductoPorId(idProducto);

            campoTextoNombreProducto.setText(productoRecuperado.getNombre());
            campoTextoPrecioProducto.setText(String.valueOf(productoRecuperado.getPrecio()));

            ImageIcon imagenProducto;
            try {
                imagenProducto = ImagenesUtils.obtenerImagen(productoRecuperado.getDireccionImagen());
            } catch (ImagenNoEncontradaException ex) {
                imagenProducto = new ImageIcon(getClass().getResource("/imagenProductoPredeterminada.png"));
                LOG.severe("Error al cargar la imagen del producto. " + ex.getMessage());
            }

            etqImagenProducto.setIcon(imagenProducto);
        } catch (ProductoConsultadoNoExisteException ex) {
            LOG.severe("Error al mostrar el producto " + ex.getMessage());

            JOptionPane.showMessageDialog(
                this,
                "Error al mostrar los datos del producto.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );

            control.mostrarProductosPrincipal(this);
        }
    }
    
    private void mostrarSelectorArchivos() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Imagen JPG y PNG", "jpg", "png");
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
                        JOptionPane.INFORMATION_MESSAGE
                    );
                } catch (IOException ex) {
                    LOG.severe("Error al crear la carpeta \"imagenes\". " + ex.getMessage());
                    this.direccionImagenProducto = "/imagenProductoPredeterminada.png";
                    return;
                }
            }

            Path imagenesProductos = Paths.get(imagenes.toString(), "imagenesProductos");

            if (!Files.exists(imagenesProductos)) {
                try {
                    Files.createDirectories(imagenesProductos);
                    JOptionPane.showMessageDialog(
                        this,
                        "Se ha creado la carpeta \"imagenesProductos\" en la ruta: " + imagenesProductos.toAbsolutePath(),
                        "Carpeta \"imagenesProductos\" creada",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                } catch (IOException ex) {
                    LOG.severe("Error al crear la carpeta \"imagenesProductos\". " + ex.getMessage());
                    this.direccionImagenProducto = "/imagenProductoPredeterminada.png";
                    return;
                }
            }

            try {
                Path nuevaDireccionImagen = imagenesProductos.resolve(pathImagenSeleccionada.getFileName());
                Files.copy(pathImagenSeleccionada, nuevaDireccionImagen, StandardCopyOption.REPLACE_EXISTING);

                JOptionPane.showMessageDialog(
                    this,
                    "Se ha guardado la imagen en la carpeta: " + imagenesProductos.toString(),
                    "Imagen guardada",
                    JOptionPane.INFORMATION_MESSAGE
                );

                try {
                    byte[] bytesImagenSeleccionada = Files.readAllBytes(pathImagenSeleccionada);
                    ImageIcon imagenProducto = new ImageIcon(bytesImagenSeleccionada);
                    ImageIcon imagenRedimensionada = ImagenesUtils.redimensionarImagen(imagenProducto, 200, 200);
                    etqImagenProducto.setIcon(imagenRedimensionada);

                    Path pathPresentacion = Paths.get(System.getProperty("user.dir"));
                    direccionImagenProducto = pathPresentacion.relativize(nuevaDireccionImagen).toString();
                } catch (IOException ex) {
                    LOG.severe("Error al cargar la imagen. " + ex.getMessage());
                    this.direccionImagenProducto = "/imagenProductoPredeterminada.png";
                }
            } catch (IOException ex) {
                LOG.severe("Error al guardar la imagen. " + ex.getMessage());
                this.direccionImagenProducto = "/imagenProductoPredeterminada.png";
            }
        }
    }
    
    private void actualizarProducto() {
        String nombreProducto = campoTextoNombreProducto.getText();
        Float precio = Float.valueOf(campoTextoPrecioProducto.getText());
        String direccionImagenString = direccionImagenProducto;

        ProductoActualizadoDTO productoActualizadoDTO = new ProductoActualizadoDTO(
            idProducto, nombreProducto, precio, (TipoProducto)comboBoxTipoProducto.getSelectedItem(), direccionImagenString
        );

        try {
            productosBO.actualizarProducto(productoActualizadoDTO);
            JOptionPane.showMessageDialog(this, "El producto se ha actualizado exitosamente", 
                    "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);

        } catch (NombreProductoInvalidoException ex) {
            LOG.severe("Nombre del producto inválido. " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Nombre del producto inválido", JOptionPane.ERROR_MESSAGE);
        } catch (PrecioProductoInvalidoException ex) {
            LOG.severe("Precio del producto inválido. " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Precio del producto inválido", JOptionPane.ERROR_MESSAGE);
        } catch (ProductoYaExisteException ex) {
            LOG.severe("Producto existente. " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Producto existente", JOptionPane.ERROR_MESSAGE);
        } catch (ProductoSinIdException ex) {
            LOG.severe("Producto sin ID. " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Producto sin ID", JOptionPane.ERROR_MESSAGE);
        } catch (ProductoSinNombreException ex) {
            LOG.severe("Producto sin nombre. " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Producto sin nombre", JOptionPane.ERROR_MESSAGE);
        } catch (ProductoSinPrecioException ex) {
            LOG.severe("Producto sin precio. " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Producto sin precio", JOptionPane.ERROR_MESSAGE);
        } catch (ProductoSinDireccionImagenException ex) {
            LOG.severe("Producto sin dirección de imagen. " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Producto sin dirección de imagen", JOptionPane.ERROR_MESSAGE);
        } catch (ProductoSinTipoException ex) {
            LOG.severe("Producto sin tipo. " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Producto sin tipo", JOptionPane.ERROR_MESSAGE);
        } catch (ProductoConsultadoNoExisteException ex) {
            LOG.severe("Producto no existe. " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Producto no existe", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void mostrarProductosPrincipal() {
        control.mostrarProductosPrincipal(this);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBaseEncabezado = new javax.swing.JPanel();
        panelInformacionProducto = new javax.swing.JPanel();
        etqDatosProducto = new javax.swing.JLabel();
        etqNombre = new javax.swing.JLabel();
        campoTextoNombreProducto = new javax.swing.JTextField();
        etqPrecio = new javax.swing.JLabel();
        campoTextoPrecioProducto = new javax.swing.JTextField();
        comboBoxTipoProducto = new javax.swing.JComboBox<>();
        etqNombre1 = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        etqImagenProducto = new javax.swing.JLabel();
        btnSubirFotografia = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(870, 584));

        panelBaseEncabezado.setBackground(new java.awt.Color(250, 230, 188));

        javax.swing.GroupLayout panelBaseEncabezadoLayout = new javax.swing.GroupLayout(panelBaseEncabezado);
        panelBaseEncabezado.setLayout(panelBaseEncabezadoLayout);
        panelBaseEncabezadoLayout.setHorizontalGroup(
            panelBaseEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelBaseEncabezadoLayout.setVerticalGroup(
            panelBaseEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 85, Short.MAX_VALUE)
        );

        panelInformacionProducto.setBackground(new java.awt.Color(204, 255, 204));
        panelInformacionProducto.setPreferredSize(new java.awt.Dimension(428, 388));

        etqDatosProducto.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqDatosProducto.setText("Datos del producto");

        etqNombre.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        etqNombre.setText("Nombre:");

        campoTextoNombreProducto.setPreferredSize(new java.awt.Dimension(64, 31));

        etqPrecio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        etqPrecio.setText("Precio");

        campoTextoPrecioProducto.setPreferredSize(new java.awt.Dimension(64, 31));

        comboBoxTipoProducto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        comboBoxTipoProducto.setModel(new DefaultComboBoxModel<>(TipoProducto.values()));
        comboBoxTipoProducto.setSelectedItem(TipoProducto.PLATILLO);
        comboBoxTipoProducto.setToolTipText("");
        comboBoxTipoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTipoProductoActionPerformed(evt);
            }
        });

        etqNombre1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        etqNombre1.setText("Tipo de producto:");

        javax.swing.GroupLayout panelInformacionProductoLayout = new javax.swing.GroupLayout(panelInformacionProducto);
        panelInformacionProducto.setLayout(panelInformacionProductoLayout);
        panelInformacionProductoLayout.setHorizontalGroup(
            panelInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacionProductoLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(panelInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etqNombre1)
                    .addComponent(comboBoxTipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etqNombre)
                    .addComponent(etqDatosProducto)
                    .addComponent(campoTextoNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etqPrecio)
                    .addComponent(campoTextoPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        panelInformacionProductoLayout.setVerticalGroup(
            panelInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacionProductoLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(etqDatosProducto)
                .addGap(18, 18, 18)
                .addComponent(etqNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoTextoNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(etqNombre1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBoxTipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(etqPrecio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoTextoPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );

        btnRegistrar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnRegistrar.setText("Actualizar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCancelar.setText("Volver");
        btnCancelar.setPreferredSize(new java.awt.Dimension(116, 32));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Actualizar prodcuto");

        etqImagenProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenPlatilloPredeterminada.png"))); // NOI18N

        btnSubirFotografia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSubirFotografia.setText("Subir fotografía");
        btnSubirFotografia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirFotografiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBaseEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(etqImagenProducto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSubirFotografia)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(btnRegistrar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(panelInformacionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBaseEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(209, 209, 209)
                                .addComponent(btnSubirFotografia))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(panelInformacionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRegistrar)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 49, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(50, 50, 50)
                        .addComponent(etqImagenProducto)
                        .addGap(188, 188, 188))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubirFotografiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirFotografiaActionPerformed
        mostrarSelectorArchivos();
    }//GEN-LAST:event_btnSubirFotografiaActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        actualizarProducto();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        mostrarProductosPrincipal();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void comboBoxTipoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTipoProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxTipoProductoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnSubirFotografia;
    private javax.swing.JTextField campoTextoNombreProducto;
    private javax.swing.JTextField campoTextoPrecioProducto;
    private javax.swing.JComboBox<TipoProducto> comboBoxTipoProducto;
    private javax.swing.JLabel etqDatosProducto;
    private javax.swing.JLabel etqImagenProducto;
    private javax.swing.JLabel etqNombre;
    private javax.swing.JLabel etqNombre1;
    private javax.swing.JLabel etqPrecio;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel panelBaseEncabezado;
    private javax.swing.JPanel panelInformacionProducto;
    // End of variables declaration//GEN-END:variables
}
