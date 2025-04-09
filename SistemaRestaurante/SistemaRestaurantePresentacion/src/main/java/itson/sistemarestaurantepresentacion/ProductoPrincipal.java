package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantenegocio.excepciones.ProductoBuscadoNoExisteException;
import itson.sistemarestaurantenegocio.interfaces.IProductosBO;
import itson.sistemarestaurantepresentacion.excepciones.ImagenNoEncontradaException;
import itson.sistemarestaurantepresentacion.interfaces.IMediador;
import itson.sistemarestaurantepresentacion.utils.ImagenesUtils;
import itson.sistemarestaurantepresentacion.interfaces.IVistaReceptoraIdProducto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ProductoPrincipal extends JFrame implements IVistaReceptoraIdProducto{

    /**
     * Creates new form ProductoPrincipal
     */
    public ProductoPrincipal() {
        initComponents();
        this.setName("Selector de productos");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
    
    private IMediador control;
    
    private IProductosBO productosBO;
    
    private int MARGEN_HORIZONTAL_PANELES_INGREDIENTES = 5;
    private int MARGEN_VERTICAL_PANELES_INGREDIENTES = 5;
    private int ALTURA_PANEL_INGREDIENTE = 120;
    private int CANTIDAD_PANELES_FILA = 5;
    private int MARGEN_LATERAL_IMAGEN_INGREDIENTE = 20;
    
    private Font FUENTE_NOMBRE_INGREDIENTE = new Font("Segoe UI", Font.BOLD, 20);
    
    private Color COLOR_PANEL_INGREDIENTE = new Color(249, 239, 211);
    private Color COLOR_BOTON_EDITAR_INGREDIENTE = new Color(210, 250, 176);
    private Color COLOR_BOTON_ELIMINAR_INGREDIENTE = new Color(255, 224, 206);
    private Color COLOR_BOTON_VOLVER_PRINCIPAL_INGREDIENTES = new Color(202, 232, 255);

    private static final Logger LOG = Logger.getLogger(ProductoPrincipal.class.getName());
    
    public ProductoPrincipal(IMediador control, IProductosBO productosBO){
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        
        this.productosBO = productosBO;
        this.control = control;
        
        panelBaseEncabezado2.add(new Encabezado());
        
        cargarProductos();
    }
    
    private void cargarProductos(){
        
        List<Producto> listaProductosConsultados = productosBO.consultarProductos();
        
        configurarLayoutPanelTodosProductos(listaProductosConsultados.size());
  
        for(Producto producto: listaProductosConsultados){
            
            panelProductos.add(crearPanelProducto(producto));
            
        }    
    }
    
    private void cargarProducto(Long idProducto){
        
        Producto producto = null;
        try {
            producto = productosBO.consultarProductoPorId(idProducto);
            
            configurarPanelProductoSolo();
            
            JPanel panelProducto = crearPanelProducto(producto);

            panelProductos.add(panelProducto, BorderLayout.CENTER);
            
            panelProducto.add(crearPanelBtnVolverProductosTodos());

        } catch (ProductoBuscadoNoExisteException ex) {
            LOG.log(Level.SEVERE, "Error al mostrar el producto buscado. " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error al mostrar el producto buscado", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void configurarLayoutPanelTodosIngredientes(int cantidadIngredientes){
        
        panelProductos.removeAll();
        
        int numeroFilasLlenar = (int)Math.ceil((double)cantidadIngredientes/CANTIDAD_PANELES_FILA);
                
                
        GridLayout gridLayout = new GridLayout(numeroFilasLlenar, CANTIDAD_PANELES_FILA);
        
        gridLayout.setHgap(MARGEN_HORIZONTAL_PANELES_INGREDIENTES);
        gridLayout.setVgap(MARGEN_VERTICAL_PANELES_INGREDIENTES);
        
        panelProductos.setLayout(gridLayout);
        
        panelProductos.setPreferredSize(
                new Dimension(0,
                2 * (numeroFilasLlenar * ALTURA_PANEL_INGREDIENTE) + ALTURA_PANEL_INGREDIENTE));
        
    }
    
    private JPanel crearPanelBtnVolverProductosTodos(){
        JPanel panelBtnVolverProductosTodos = new JPanel();
        
        panelBtnVolverProductosTodos.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        panelBtnVolverProductosTodos.add(crearBtnVolverProductosTodos());
        
        panelBtnVolverProductosTodos.setOpaque(false);
        
        return panelBtnVolverProductosTodos;
    }
    
    private JButton crearBtnVolverProductosTodos(){
        
        JButton btnVolverProductosTodos = new JButton();
        
        btnVolverProductosTodos.addActionListener(e -> control.mostrarProductosPrincipal(this));
        
        btnVolverProductosTodos.setText("Volver");
        
        btnVolverProductosTodos.setBackground(COLOR_BOTON_VOLVER_PRINCIPAL_INGREDIENTES);
        
        return btnVolverProductosTodos;
    }
    
    private void configurarPanelProductoSolo(){
        
        panelProductos.removeAll();
        panelProductos.setPreferredSize(new Dimension(0,0));
        
        BorderLayout borderLayout = new BorderLayout();
        
        panelProductos.setLayout(borderLayout);
        
    }
    
    private JPanel crearPanelProducto(Producto producto){
        
        // Se crea y configura el panel del ingrediente
        JPanel panelProducto = new JPanel();
        
        panelProducto.setBackground(COLOR_PANEL_INGREDIENTE);
            
        panelProducto.setLayout(new BoxLayout(panelProducto, BoxLayout.Y_AXIS));
        
        // Se crea y agrega el panel con la imagen del ingrediente.
        panelProducto.add(crearPanelImagenProducto(producto));
        
        // Se crea y agrega el panel con el nombre del ingrediente.
        panelProducto.add(crearPanelNombreProducto(producto));
        
        panelProducto.add(crearPanelTipoProducto(producto));
   
        // Se crea y agrega un panel para agregar los botones de editar y eliminar ingrediente
        panelProducto.add(crearPanelBtnEditarEliminarProducto(producto));
        
        return panelProducto;
    }
    
    private JPanel crearPanelImagenProducto(Producto producto){
        
        // Se crea el panel de la imangen del ingrediente
        JPanel panelImagenProducto = new JPanel(new FlowLayout());
            
        // Se crea una etiqueta para añadir su imagen asociada
        JLabel etqImagenProducto = new JLabel();

        // Se obtiene la imagen
        ImageIcon imagenProducto;
        try {
            imagenProducto = ImagenesUtils.obtenerImagen(producto.getDireccionImagen());
        } catch (ImagenNoEncontradaException ex) {
            imagenProducto = new ImageIcon(getClass().getResource("/imagenIngredientePredeterminada.png"));
        }

        // Se determina el nuevo tamaño de la imagen para ajustarla al panel.
        int nuevoAnchoImagen = (panelProductos.getWidth() - (MARGEN_HORIZONTAL_PANELES_INGREDIENTES * (CANTIDAD_PANELES_FILA + 1)))
                /CANTIDAD_PANELES_FILA - MARGEN_LATERAL_IMAGEN_INGREDIENTE * 2;     
        
        int nuevoAltoImagen = ALTURA_PANEL_INGREDIENTE;

        // Se redimensiona la imagen
        ImageIcon imagenProductoRedimensionada = ImagenesUtils.redimensionarImagen(
                imagenProducto, nuevoAnchoImagen, nuevoAltoImagen);

        // Se agrega a la etiqueta la imagen redimensionada
        etqImagenProducto.setIcon(imagenProductoRedimensionada);

        // Se agrega la etiqueta con la imagen al panel.
        panelImagenProducto.add(etqImagenProducto);
        
        panelImagenProducto.setOpaque(false);
        
        return panelImagenProducto;
    }
    
    private JPanel crearPanelNombreProducto(Producto producto){
        JPanel panelNombreProducto = new JPanel(new FlowLayout());
        
        JLabel nombreProducto = new JLabel(producto.getNombre());
        
        nombreProducto.setFont(FUENTE_NOMBRE_INGREDIENTE);
        
        panelNombreProducto.add(nombreProducto);
        
        panelNombreProducto.setOpaque(false);
        
        return panelNombreProducto;   
    }
    
    private JPanel crearPanelTipoProducto(Producto producto){
        
        JPanel panelTipoProducto = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        String tipoProducto = "";
        if(null != producto.getTipo()) switch (producto.getTipo()) {
            case ENTRADA:
                tipoProducto = "Entrada";
                break;
            case PLATILLO:
                tipoProducto = "Platillo";
                break;
            case BEBIDA:
                tipoProducto = "Bebida";
                break;
            case POSTRE:
                tipoProducto = "Postre";
                break;
            default:
                break;
        }
        
        JLabel labelTipoProducto = new JLabel("Tipo: " + producto.getTipo());
        
        panelTipoProducto.add(labelTipoProducto);
        
        panelTipoProducto.setOpaque(false);
        
        return panelTipoProducto;
    }
    
    private JPanel crearPanelBtnEditarEliminarProducto(Producto producto){
        JPanel panelBotonesEditarEliminarProducto = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        // Se crean ambos botones
        panelBotonesEditarEliminarProducto.add(crearBtnEditarProducto(producto.getId()));
        panelBotonesEditarEliminarProducto.add(crearBtnEliminarProducto(producto.getId()));

        panelBotonesEditarEliminarProducto.setOpaque(false);
        
        return panelBotonesEditarEliminarProducto;
    }
    
    private void configurarLayoutPanelTodosProductos(int cantidadIngredientes){
        
        panelProductos.removeAll();
        
        int numeroFilasLlenar = (int)Math.ceil((double)cantidadIngredientes/CANTIDAD_PANELES_FILA);
                
                
        GridLayout gridLayout = new GridLayout(numeroFilasLlenar, CANTIDAD_PANELES_FILA);
        
        gridLayout.setHgap(MARGEN_HORIZONTAL_PANELES_INGREDIENTES);
        gridLayout.setVgap(MARGEN_VERTICAL_PANELES_INGREDIENTES);
        
        panelProductos.setLayout(gridLayout);
        
        panelProductos.setPreferredSize(
                new Dimension(0,
                2 * (numeroFilasLlenar * ALTURA_PANEL_INGREDIENTE) + ALTURA_PANEL_INGREDIENTE));
        
    }
    
    private JButton crearBtnEditarProducto(Long idProducto){
        
        JButton btnEditarProducto = new JButton("Editar");
        
        btnEditarProducto.setBackground(COLOR_BOTON_EDITAR_INGREDIENTE);
        
        // Se define un Action Listener para el JButton, se ejecutará el método
        // mostrarEditarIngrediente() cada vez que se presione.
        btnEditarProducto.addActionListener(e -> mostrarEditarProducto(idProducto));
        
        return btnEditarProducto;
    }
    
    private JButton crearBtnEliminarProducto(Long idProducto){
        
        JButton btnEliminarProducto = new JButton("Eliminar");
        
        btnEliminarProducto.setBackground(COLOR_BOTON_ELIMINAR_INGREDIENTE);
        
        btnEliminarProducto.addActionListener(e -> eliminarProducto(idProducto));
        
        return btnEliminarProducto;
    }
    
    
    private void mostrarEditarProducto(Long idProducto){
        
        control.mostrarEditarProductos(this, idProducto);
    }
    
    private void eliminarProducto(Long idProducto){
        // TODO Pendiente para que avise si hay productos relacionados con este ingrediente.
    }
    
    private void mostrarRegistrarProdcto(){
        control.mostrarRegistroProducto(this);
    
    }
    
    private void mostrarBuscadorProducto(){
        control.mostrarBuscadorProductos(this);
    }
    
    private void mostrarMenuPrincipal(){
        control.mostrarMenuPrincipal(this);
    }
    
    public void setIdProducto(Long idProducto) {
        cargarProducto(idProducto);
        getContentPane().revalidate();
        repaint();
    }
    
    public void habilitar(boolean habilitado) {
        setEnabled(habilitado);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        panelBaseEncabezado2 = new javax.swing.JPanel();
        btnRegistrarProducto = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        panelProductos = new javax.swing.JPanel();
        btnBuscarProducto = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(232, 232, 232));

        panelBaseEncabezado2.setBackground(new java.awt.Color(250, 230, 188));

        javax.swing.GroupLayout panelBaseEncabezado2Layout = new javax.swing.GroupLayout(panelBaseEncabezado2);
        panelBaseEncabezado2.setLayout(panelBaseEncabezado2Layout);
        panelBaseEncabezado2Layout.setHorizontalGroup(
            panelBaseEncabezado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelBaseEncabezado2Layout.setVerticalGroup(
            panelBaseEncabezado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 84, Short.MAX_VALUE)
        );

        btnRegistrarProducto.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnRegistrarProducto.setText("Registrar Producto");
        btnRegistrarProducto.setPreferredSize(new java.awt.Dimension(210, 32));
        btnRegistrarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarProductoActionPerformed(evt);
            }
        });

        btnVolver.setBackground(new java.awt.Color(205, 255, 197));
        btnVolver.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVolver.setText("Volver");
        btnVolver.setPreferredSize(new java.awt.Dimension(85, 32));
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        panelProductos.setBackground(new java.awt.Color(153, 153, 153));
        panelProductos.setEnabled(false);
        panelProductos.setLayout(new java.awt.GridLayout(1, 0));

        btnBuscarProducto.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnBuscarProducto.setText("Buscar Producto");
        btnBuscarProducto.setPreferredSize(new java.awt.Dimension(210, 32));
        btnBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(413, 413, 413)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(384, Short.MAX_VALUE))
            .addComponent(panelProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBaseEncabezado2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnRegistrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(panelBaseEncabezado2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panelProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void btnRegistrarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarProductoActionPerformed
        mostrarRegistrarProdcto();
    }//GEN-LAST:event_btnRegistrarProductoActionPerformed

    private void btnBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoActionPerformed
        mostrarBuscadorProducto();
    }//GEN-LAST:event_btnBuscarProductoActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        mostrarMenuPrincipal();
    }//GEN-LAST:event_btnVolverActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarProducto;
    private javax.swing.JButton btnRegistrarProducto;
    private javax.swing.JButton btnVolver;
    private javax.swing.JPanel panelBaseEncabezado2;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelProductos;
    // End of variables declaration//GEN-END:variables
}
