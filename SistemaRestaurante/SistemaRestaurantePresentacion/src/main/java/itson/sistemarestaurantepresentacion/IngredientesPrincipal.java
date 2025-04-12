
package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.UnidadIngrediente;
import static itson.sistemarestaurantedominio.UnidadIngrediente.GRAMO;
import static itson.sistemarestaurantedominio.UnidadIngrediente.MILILITRO;
import static itson.sistemarestaurantedominio.UnidadIngrediente.PIEZA;
import itson.sistemarestaurantedominio.Usuario;
import itson.sistemarestaurantenegocio.excepciones.IdIngredienteNuloException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteConsultadoNoExisteException;
import itson.sistemarestaurantenegocio.interfaces.IIngredientesBO;
import itson.sistemarestaurantenegocio.interfaces.IUsuariosBO;
import itson.sistemarestaurantenegocio.excepciones.UsuarioInexistenteException;
import itson.sistemarestaurantepresentacion.RegistroIngrediente;
import itson.sistemarestaurantepresentacion.excepciones.ImagenNoEncontradaException;
import itson.sistemarestaurantepresentacion.interfaces.IMediador;
import itson.sistemarestaurantepresentacion.interfaces.IVistaReceptoraIdIngrediente;
import itson.sistemarestaurantepresentacion.utils.ImagenesUtils;
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

/**
 * Clase que representa la ventana principal del módulo de ingredientes,
 * permite editar ingredientes, eliminarlos o registrar uno nuevo, además de
 * su búsuqeda. Muestra la información de su nombre, cantidad en stock, unidad
 * de medida e imagen representativa.
 * 
 * @author Manuel Romo López
 * ID: 00000253080
 */
public class IngredientesPrincipal extends JFrame implements IVistaReceptoraIdIngrediente{

    /**
     * Objeto que implementa la interfaz IMediador, es el control que coordina
     * el flujo entre pantallas.
     */
    private IMediador control;
    /**
     * Objeto que implementa la interfaz IIngredientesBO, es la clase que permite
     * realizar operaciones de negocio sobre objetos de tipo Ingrediente.
     */
    private IIngredientesBO ingredientesBO;
    
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
    
    private static final Logger LOG = Logger.getLogger(IngredientesPrincipal.class.getName());
    
    
    /**
     * Contructor de la clase, permite crear una ventana que representa la pantalla
     * principal del módulo de ingredientes.
     * @param control Objeto que implementa la interfaz IMediador, es el control que
     * gestiona el flujo entre ventanas.
     * @param ingredientesBO Objeto que implementa la interfaz IIngredientesBO, permite ejecutar
     * operaciones de negocio relacionados con los Ingredientes.
     */
    public IngredientesPrincipal(IMediador control, IIngredientesBO ingredientesBO) {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        
        this.ingredientesBO = ingredientesBO;
        this.control = control;
        
        panelBaseEncabezado.add(new Encabezado());
        
        cargarIngredientes();

    }
    
    /**
     * Método que permite cargar los ingredientes disponibles en inventario en 
     * esta ventana.
     */
    private void cargarIngredientes(){
        
        List<Ingrediente> listaIngredientesConsultados = ingredientesBO.consultarIngredientes();
        
        configurarLayoutPanelTodosIngredientes(listaIngredientesConsultados.size());
  
        for(Ingrediente ingrediente: listaIngredientesConsultados){
            
            panelIngredientes.add(crearPanelIngrediente(ingrediente));
            
        }
        
    }
    
    /**
     * Método que permite cargar un ingrediente disponible en inventario, a partir 
     * de su Id.
     */
    private void cargarIngrediente(Long idIngrediente){
        
        Ingrediente ingrediente = null;
        try {
            ingrediente = ingredientesBO.consultarIngrediente(idIngrediente);
            
            configurarPanelIngredienteSolo();
            
            JPanel panelIngrediente = crearPanelIngrediente(ingrediente);

            panelIngredientes.add(panelIngrediente, BorderLayout.CENTER);
            
            panelIngrediente.add(crearPanelBtnVolverProductosTodos());

        } catch (IngredienteConsultadoNoExisteException | IdIngredienteNuloException ex) {
            LOG.log(Level.SEVERE, "Error al mostrar el ingrediente buscado. " + ex.getMessage());
            JOptionPane.showMessageDialog(
                    this, 
                    ex.getMessage(), 
                    "Error al mostrar el ingrediente buscado", 
                    JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    /**
     * Método que permite configurar el layout del panel que contiene los ingredientes.
     * @param cantidadIngredientes Dato int que representa el número de ingredientes
     * distintos que se deben mostrar.
     */
    private void configurarLayoutPanelTodosIngredientes(int cantidadIngredientes){
        
        panelIngredientes.removeAll();
        
        int numeroFilasLlenar = (int)Math.ceil((double)cantidadIngredientes/CANTIDAD_PANELES_FILA);
                
                
        GridLayout gridLayout = new GridLayout(numeroFilasLlenar, CANTIDAD_PANELES_FILA);
        
        gridLayout.setHgap(MARGEN_HORIZONTAL_PANELES_INGREDIENTES);
        gridLayout.setVgap(MARGEN_VERTICAL_PANELES_INGREDIENTES);
        
        panelIngredientes.setLayout(gridLayout);
        
        panelIngredientes.setPreferredSize(
                new Dimension(0,
                2 * (numeroFilasLlenar * ALTURA_PANEL_INGREDIENTE) + ALTURA_PANEL_INGREDIENTE));
        
    }
    
    /**
     * Permite configurar el layout del panel que contiene a un solo ingrediente,
     * necesario cuando se realiza una búsqueda.
     */
    private void configurarPanelIngredienteSolo(){
        
        panelIngredientes.removeAll();
        panelIngredientes.setPreferredSize(new Dimension(0,0));
        
        BorderLayout borderLayout = new BorderLayout();
        
        panelIngredientes.setLayout(borderLayout);
        
    }
    
    /**
     * Método que permite crear el panel que contiene la información sobre cada
     * ingrediente a mostar, incluyendo botones para editarlo y eliminarlo.
     * @param ingrediente Objeto Long que representa el Id del ingrediente
     * a modificar.
     * @return Objeto JPanel, el panel que contiene la información del ingrediente
     * a mostrar.
     */
    private JPanel crearPanelIngrediente(Ingrediente ingrediente){
        
        // Se crea y configura el panel del ingrediente
        JPanel panelIngrediente = new JPanel();
        
        panelIngrediente.setBackground(COLOR_PANEL_INGREDIENTE);
            
        panelIngrediente.setLayout(new BoxLayout(panelIngrediente, BoxLayout.Y_AXIS));
        
        // Se crea y agrega el panel con la imagen del ingrediente.
        panelIngrediente.add(crearPanelImagenIngrediente(ingrediente));
        
        // Se crea y agrega el panel con el nombre del ingrediente.
        panelIngrediente.add(crearPanelNombreIngrediente(ingrediente));
        
        // Se crea y agrega el panel para la unidad y el stock del ingrediente.
        panelIngrediente.add(crearPanelStockUnidadIngrediente(ingrediente));
   
        // Se crea y agrega un panel para agregar los botones de editar y eliminar ingrediente
        panelIngrediente.add(crearPanelBtnEditarEliminarIngrediente(ingrediente));
        
        return panelIngrediente;
    }
    
    /**
     * Método que permite crear el panel con la imagen del ingrediente a mostrar.
     * @param ingrediente Objeto Ingrediente del que se mostrará su imagen.
     * @return Objeto JPanel, es el panel con la imagen del ingrediente.
     */
    private JPanel crearPanelImagenIngrediente(Ingrediente ingrediente){
        
        // Se crea el panel de la imangen del ingrediente
        JPanel panelImagenIngrediente = new JPanel(new FlowLayout());
            
        // Se crea una etiqueta para añadir su imagen asociada
        JLabel etqImagenIngrediente = new JLabel();

        // Se obtiene la imagen
        ImageIcon imagenIngrediente;
        try {
            imagenIngrediente = ImagenesUtils.obtenerImagen(ingrediente.getDireccionImagen());
        } catch (ImagenNoEncontradaException ex) {
            imagenIngrediente = new ImageIcon(getClass().getResource("/imagenIngredientePredeterminada.png"));
        }

        // Se determina el nuevo tamaño de la imagen para ajustarla al panel.
        int nuevoAnchoImagen = (panelIngredientes.getWidth() - (MARGEN_HORIZONTAL_PANELES_INGREDIENTES * (CANTIDAD_PANELES_FILA + 1)))
                /CANTIDAD_PANELES_FILA - MARGEN_LATERAL_IMAGEN_INGREDIENTE * 2;     
        
        int nuevoAltoImagen = ALTURA_PANEL_INGREDIENTE;

        // Se redimensiona la imagen
        ImageIcon imagenIngredienteRedimensionada = ImagenesUtils.redimensionarImagen(
                imagenIngrediente, nuevoAnchoImagen, nuevoAltoImagen);

        // Se agrega a la etiqueta la imagen redimensionada
        etqImagenIngrediente.setIcon(imagenIngredienteRedimensionada);

        // Se agrega la etiqueta con la imagen al panel.
        panelImagenIngrediente.add(etqImagenIngrediente);
        
        panelImagenIngrediente.setOpaque(false);
        
        return panelImagenIngrediente;
    }
    
    /**
     * Método que permite crear el panel con el nombre del ingrediente.
     * @param ingrediente Objeto Ingrediente del que se mostrará su nombre.
     * @return Objeto JPanel, el panel con el nombre del ingrediente.
     */
    private JPanel crearPanelNombreIngrediente(Ingrediente ingrediente){
        JPanel panelNombreIngrediente = new JPanel(new FlowLayout());
        
        JLabel nombreIngrediente = new JLabel(ingrediente.getNombre());
        
        nombreIngrediente.setFont(FUENTE_NOMBRE_INGREDIENTE);
        
        panelNombreIngrediente.add(nombreIngrediente);
        
        panelNombreIngrediente.setOpaque(false);
        
        return panelNombreIngrediente;
        
    }
    
    /**
     * Método que permite crear el panel que contiene el stock y la unidad del
     * ingrediente a mostrar.
     * @param ingrediente Objeto Ingrediente del que se mostrará su stock y
     * unidad.
     * @return Objeto JPanel, panel que contiene el srock y la unidad del
     * ingrediente.
     */
    private JPanel crearPanelStockUnidadIngrediente(Ingrediente ingrediente){
        
        JPanel panelStockUnidadIngrediente = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        String unidadMedida = "";
        if(null != ingrediente.getUnidad())switch (ingrediente.getUnidad()) {
            case GRAMO:
                unidadMedida = "gramos";
                break;
            case MILILITRO:
                unidadMedida = "mililitros";
                break;
            case PIEZA:
                unidadMedida = "piezas";
                break;
            default:
                break;
        }
        
        JLabel labelStockIngrediente = new JLabel("Stock: " + ingrediente.getCantidad() + " " + unidadMedida);
        
        panelStockUnidadIngrediente.add(labelStockIngrediente);
        
        panelStockUnidadIngrediente.setOpaque(false);
        
        return panelStockUnidadIngrediente;
    }
    
    /**
     * Método que permite crear un panel que contiene dos botones, el primero que permite
     * editar el ingrediente, y el segundo que permite eliminarlo.
     * @param ingrediente Objeto Ingrediente que representa el ingrediente que será
     * editado o elimnado.
     * @return Objeto JPanel, panel que contiene los botones para editar o eliminar
     * el ingrediente especificado.
     */
    private JPanel crearPanelBtnEditarEliminarIngrediente(Ingrediente ingrediente){
        JPanel panelBotonesEditarEliminarIngrediente = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        // Se crean ambos botones
        panelBotonesEditarEliminarIngrediente.add(crearBtnEditarIngrediente(ingrediente.getId()));
        panelBotonesEditarEliminarIngrediente.add(crearBtnEliminarIngrediente(ingrediente.getId()));

        panelBotonesEditarEliminarIngrediente.setOpaque(false);
        
        return panelBotonesEditarEliminarIngrediente;
    }
    
    /**
     * Método que permite crear el botón para editar los datos de un ingrediente.
     * @param idIngrediente Objeto Long que representa el Id del ingrediente para
     * el que se creará el botón de edición.
     * @return Objeto JButton, el botón que permite editar el ingrediente cuando
     * es presionado.
     */
    private JButton crearBtnEditarIngrediente(Long idIngrediente){
        
        JButton btnEditarIngrediente = new JButton("Editar");
        
        btnEditarIngrediente.setBackground(COLOR_BOTON_EDITAR_INGREDIENTE);
        
        // Se define un Action Listener para el JButton, se ejecutará el método
        // mostrarEditarIngrediente() cada vez que se presione.
        btnEditarIngrediente.addActionListener(e -> mostrarEditarIngrediente(idIngrediente));
        
        return btnEditarIngrediente;
    }
    
    /**
     * Método que permite crear el botón para eliminar un ingrediente.
     * @param idIngrediente Objeto Long que representa el Id del ingrediente
     * a eliminar cuando se presione el botón.
     * @return Objeto JButton, el botón que permite eliminar el ingrediente cuando
     * es presionado.
     */
    private JButton crearBtnEliminarIngrediente(Long idIngrediente){
        
        JButton btnEliminarIngrediente = new JButton("Eliminar");
        
        btnEliminarIngrediente.setBackground(COLOR_BOTON_ELIMINAR_INGREDIENTE);
        
        // Se define un Action Listener para el JButton, se ejecutará el método
        // eliminarIngrediente() cuando se presione.
        btnEliminarIngrediente.addActionListener(e -> eliminarIngrediente(idIngrediente));
        
        return btnEliminarIngrediente;
    }
    
    /**
     * Método que permite crear un panel que contiene al botón que al presionar
     * muestra la pantalla principal del módulo de ingredientes.
     * @return Objeto JPanel, panel que contiene el botón que permite mostrar la
     * pantalla principal del módulo de ingredientes.
     */
    private JPanel crearPanelBtnVolverProductosTodos(){
        JPanel panelBtnVolverProductosTodos = new JPanel();
        
        panelBtnVolverProductosTodos.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        panelBtnVolverProductosTodos.add(crearBtnVolverProductosTodos());
        
        panelBtnVolverProductosTodos.setOpaque(false);
        
        return panelBtnVolverProductosTodos;
    }
    
    
    /**
     * Método que permite crear un botón para volver a la pantalla principal
     * del módulo de productos.
     * @return Objeto JButton, el botón que permite eliminar el ingrediente cuando
     * es presionado.
     */
    private JButton crearBtnVolverProductosTodos(){
        
        JButton btnVolverProductosTodos = new JButton();
        
        btnVolverProductosTodos.addActionListener(e -> control.mostrarIngredientesPrincipal(this));
        
        btnVolverProductosTodos.setText("Volver");
        
        btnVolverProductosTodos.setBackground(COLOR_BOTON_VOLVER_PRINCIPAL_INGREDIENTES);
        
        return btnVolverProductosTodos;
    }
    
    /**
     * Método que permite mostrar la ventana que permite editar un ingrediente.
     * @param idIngrediente Objeto Long que representa el Id del ingrediente
     * que se va a editar.
     */
    private void mostrarEditarIngrediente(Long idIngrediente){
        
        control.mostrarEditarIngrediente(this, idIngrediente);
    }
    
    private void eliminarIngrediente(Long idIngrediente){
        // TODO Pendiente para que avise si hay productos relacionados con este ingrediente.
    }
    
    /**
     * Método que permite mostrar la pantalla de registro de un nuevo ingrediente.
     */
    private void mostrarRegistrarIngrediente(){
        control.mostrarRegistroIngrediente(this);
    
    }
    
    /**
     * Método que permite mostrar la ventana del buscador de ingredientes.
     */
    private void mostrarBuscadorIngredientes(){
        control.mostrarBuscadorIngredientes(this);
    }
    
    /**
     * Método que permite mostrar el menú principal del sistema.
     */
    private void mostrarMenuPrincipal(){
        control.mostrarMenuPrincipal(this);
    }
    
    /**
     * Implementación del método setIdIngrediente, de la interfaz {@link IVistaReceptoraIdIngrediente},
     * permite establecer el Id del ingrediente que fue seleccionado al ejecutar la
     * ventana que permite la búsqueda y selección de ingredientes.
     * @param idIngrediente Objeto Integer que representa el Id del ingrediente
     * que fue seleccionado por el usuario en la venta de búsqueda de ingredientes.
     */
    @Override
    public void setIdIngrediente(Long idIngrediente) {
        cargarIngrediente(idIngrediente);
        getContentPane().revalidate();
        repaint();
    }
    
    /**
     * Implementación del método habilitar(), de la interfaz {@link IVistaReceptoraIdIngrediente},
     * permite colocar el valor de atributo enabled de este JFrame como true o false, 
     * para permitir su edición o no.
     * @param habilitado Valor booleano que define el valor del atributo enabled de 
     * este JFrame, si es true, se permitirá su edición, si es false, no se permitirá
     * editarlo.
     */
    @Override
    public void habilitar(boolean habilitado) {
        setEnabled(habilitado);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRegistrarIngrediente = new javax.swing.JButton();
        panelScrollIngredientes = new javax.swing.JScrollPane();
        panelIngredientes = new javax.swing.JPanel();
        btnVolver = new javax.swing.JButton();
        panelBaseEncabezado = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnRegistrarIngrediente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnRegistrarIngrediente.setText("Registrar ingrediente");
        btnRegistrarIngrediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarIngredienteActionPerformed(evt);
            }
        });

        panelIngredientes.setBackground(new java.awt.Color(153, 153, 153));
        panelIngredientes.setEnabled(false);
        panelIngredientes.setLayout(new java.awt.GridLayout(1, 0));
        panelScrollIngredientes.setViewportView(panelIngredientes);

        btnVolver.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
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

        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnBuscar.setText("Buscar ingrediente");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegistrarIngrediente)
                .addGap(14, 14, 14))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(panelScrollIngredientes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
                        .addComponent(panelBaseEncabezado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(385, 385, 385)
                        .addComponent(btnVolver)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBaseEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrarIngrediente)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(panelScrollIngredientes, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVolver)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que establece que debe ejecutarse cuando se presione el JButton
     * btnRegistrarIngrediente.
     * @param evt Objeto ActionEvent que representa el evento de dar click sobre
     * el JButton btnRegistrarIngrediente.
     */
    private void btnRegistrarIngredienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarIngredienteActionPerformed
        mostrarRegistrarIngrediente();
    }//GEN-LAST:event_btnRegistrarIngredienteActionPerformed

    /**
     * Método que establece que debe ejecutarse cuando se presione el JButton
     * btnBuscar.
     * @param evt Objeto ActionEvent que representa el evento de dar click sobre
     * el JButton btnBuscar.
     */
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        mostrarBuscadorIngredientes();
    }//GEN-LAST:event_btnBuscarActionPerformed

    /**
     * Método que establece que debe ejecutarse cuando se presione el JButton
     * btnVolver.
     * @param evt Objeto ActionEvent que representa el evento de dar click sobre
     * el JButton btnVolver.
     */
    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        mostrarMenuPrincipal();
    }//GEN-LAST:event_btnVolverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnRegistrarIngrediente;
    private javax.swing.JButton btnVolver;
    private javax.swing.JPanel panelBaseEncabezado;
    private javax.swing.JPanel panelIngredientes;
    private javax.swing.JScrollPane panelScrollIngredientes;
    // End of variables declaration//GEN-END:variables


    
}
