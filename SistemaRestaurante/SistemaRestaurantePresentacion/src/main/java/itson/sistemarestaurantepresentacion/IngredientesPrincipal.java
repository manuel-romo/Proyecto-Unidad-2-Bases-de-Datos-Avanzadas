
package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.UnidadIngrediente;
import itson.sistemarestaurantedominio.Usuario;
import itson.sistemarestaurantenegocio.interfaces.IIngredientesBO;
import itson.sistemarestaurantenegocio.interfaces.IUsuariosBO;
import itson.sistemarestaurantenegocio.excepciones.UsuarioInexistenteException;
import itson.sistemarestaurantepresentacion.RegistroIngrediente;
import itson.sistemarestaurantepresentacion.excepciones.ImagenNoEncontradaException;
import itson.sistemarestaurantepresentacion.interfaces.IMediador;
import itson.sistemarestaurantepresentacion.utils.ImagenesUtils;
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

//
public class IngredientesPrincipal extends JFrame{

    private IMediador control;
    private IUsuariosBO usuariosBO;
    private IIngredientesBO ingredientesBO;
    private Long idUsuario;
    
    private int MARGEN_HORIZONTAL_PANELES_INGREDIENTES = 5;
    private int MARGEN_VERTICAL_PANELES_INGREDIENTES = 5;
    private int ALTURA_PANEL_INGREDIENTE = 120;
    private int CANTIDAD_PANELES_FILA = 6;
    
    private Font FUENTE_NOMBRE_INGREDIENTE = new Font("Segoe UI", Font.BOLD, 20);
    
    private Color COLOR_PANEL_INGREDIENTE = new Color(249, 239, 211);
    private Color COLOR_BOTON_EDITAR_INGREDIENTE = new Color(210, 250, 176);
    private Color COLOR_BOTON_ELIMINAR_INGREDIENTE = new Color(255, 224, 206);
    
    private static final Logger LOG = Logger.getLogger(IngredientesPrincipal.class.getName());
    
    
    
    public IngredientesPrincipal(IMediador control, IUsuariosBO usuariosBO, IIngredientesBO ingredientesBO) {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        
        this.usuariosBO = usuariosBO;
        this.ingredientesBO = ingredientesBO;
        this.control = control;
        
        panelBaseEncabezado.add(new Encabezado());
        
        cargarIngredientes();
        
        
    }
    
    private void cargarIngredientes(){
        
        List<Ingrediente> listaIngredientesConsultados = ingredientesBO.consultarIngredientes();
        
        configurarLayoutPanelIngredientes(listaIngredientesConsultados.size());
        
        
        for(Ingrediente ingrediente: listaIngredientesConsultados){
            
            panelIngredientes.add(crearPanelIngrediente(ingrediente));
            
        }
        
    }
    
    private void configurarLayoutPanelIngredientes(int cantidadIngredientes){
        
        int numeroFilasLlenar = (int)Math.ceil((double)cantidadIngredientes/CANTIDAD_PANELES_FILA);
                
                
        GridLayout gridLayout = new GridLayout(numeroFilasLlenar, CANTIDAD_PANELES_FILA);
        
        gridLayout.setHgap(MARGEN_HORIZONTAL_PANELES_INGREDIENTES);
        gridLayout.setVgap(MARGEN_VERTICAL_PANELES_INGREDIENTES);
        
        panelIngredientes.setLayout(gridLayout);
        
        panelIngredientes.setPreferredSize(
                new Dimension(0,
                2 * (numeroFilasLlenar * ALTURA_PANEL_INGREDIENTE) + ALTURA_PANEL_INGREDIENTE));
        
        System.out.println(gridLayout.getColumns());
    }
    
    
    private JPanel crearPanelIngrediente(Ingrediente ingrediente){
        
        // Se crea y configura el panel del ingrediente
        JPanel panelIngrediente = new JPanel();
        
        panelIngrediente.setBackground(COLOR_PANEL_INGREDIENTE);
            
        panelIngrediente.setLayout(new BoxLayout(panelIngrediente, BoxLayout.Y_AXIS));
        
        
        // Se crea el panel de la imangen del ingrediente
        JPanel panelImagenIngrediente = new JPanel(new FlowLayout());
            
        // Se crea una etiqueta para añadir su imagen asociada
        JLabel etqImagenIngrediente = new JLabel();

        // Se obtiene la imagen
        ImageIcon imagenIngrediente;
        try {
            imagenIngrediente = ImagenesUtils.obtenerImagen(ingrediente.getDireccionImagen());
        } catch (ImagenNoEncontradaException ex) {
            imagenIngrediente = new ImageIcon(getClass().getResource("imagenIngredientePredeterminada.png"));
            Logger.getLogger(IngredientesPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Se determina el nuevo tamaño de la imagen para ajustarla al panel.
        int nuevoAnchoImagen = panelIngredientes.getWidth()/CANTIDAD_PANELES_FILA - (MARGEN_HORIZONTAL_PANELES_INGREDIENTES * (CANTIDAD_PANELES_FILA + 1));      
        int nuevoAltoImagen = ALTURA_PANEL_INGREDIENTE;

        // Se redimensiona la imagen
        ImageIcon imagenIngredienteRedimensionada = ImagenesUtils.redimensionarImagen(
                imagenIngrediente, nuevoAnchoImagen, nuevoAltoImagen);

        // Se agrega a la etiqueta la imagen redimensionada
        etqImagenIngrediente.setIcon(imagenIngredienteRedimensionada);

        // Se agrega la etiqueta con la imagen al panel.
        panelImagenIngrediente.add(etqImagenIngrediente);
        
        panelIngrediente.add(panelImagenIngrediente);
        
        // Se crea el panel del nombre del ingrediente.
        JPanel panelNombreIngrediente = new JPanel(new FlowLayout());
        
        JLabel nombreIngrediente = new JLabel(ingrediente.getNombre());
        
        nombreIngrediente.setFont(FUENTE_NOMBRE_INGREDIENTE);
        
        panelNombreIngrediente.add(nombreIngrediente);
        
        panelIngrediente.add(panelNombreIngrediente);
        
         
        // Se crea un panel para el stock del ingrediente
        JPanel panelStockIngrediente = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
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
        
        panelStockIngrediente.add(labelStockIngrediente);
        
        panelIngrediente.add(panelStockIngrediente);
   
        // Se crea un panel para agregar los botones de editar y eliminar ingrediente
        JPanel panelBotonesEditarEliminarIngrediente = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        // Se crean ambos botones
        panelBotonesEditarEliminarIngrediente.add(crearBotonEditarIngrediente(ingrediente.getId()));
        panelBotonesEditarEliminarIngrediente.add(crearBotonEliminarIngrediente(ingrediente.getId()));
        
        panelIngrediente.add(panelBotonesEditarEliminarIngrediente);
        
        return panelIngrediente;
    }
    
    private JButton crearBotonEditarIngrediente(Long idIngrediente){
        
        JButton botonEditarIngrediente = new JButton("Editar");
        
        botonEditarIngrediente.setBackground(COLOR_BOTON_EDITAR_INGREDIENTE);
        
        botonEditarIngrediente.addActionListener(e -> mostrarEditarIngrediente(idIngrediente));
        
        return botonEditarIngrediente;
    }
    
    private JButton crearBotonEliminarIngrediente(Long idIngrediente){
        
        JButton botonEliminarIngrediente = new JButton("Eliminar");
        
        botonEliminarIngrediente.setBackground(COLOR_BOTON_ELIMINAR_INGREDIENTE);
        
        return botonEliminarIngrediente;
    }
    
    private void mostrarEditarIngrediente(Long idIngrediente){
        
        control.mostrarEditarIngrediente(this, idIngrediente);
    }
    
    private void eliminarIngrediente(Long idIngrediente){
        // TODO Pendiente para que avise si hay productos relacionados con este ingrediente.
    }
    
    
    private void mostrarRegistrarIngrediente(){
        control.mostrarRegistroIngrediente(this);
    
    }
    
    private void mostrarInicioSesion(){
        control.mostrarInicioSesion(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRegistrarIngrediente = new javax.swing.JButton();
        panelScrollIngredientes = new javax.swing.JScrollPane();
        panelIngredientes = new javax.swing.JPanel();
        btnVolver = new javax.swing.JButton();
        panelBaseEncabezado = new javax.swing.JPanel();

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
        panelIngredientes.setLayout(new java.awt.GridLayout());
        panelScrollIngredientes.setViewportView(panelIngredientes);

        btnVolver.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVolver.setText("Volver");

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
                .addGap(0, 0, Short.MAX_VALUE)
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
                .addComponent(btnRegistrarIngrediente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(panelScrollIngredientes, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVolver)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarIngredienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarIngredienteActionPerformed
        mostrarRegistrarIngrediente();
    }//GEN-LAST:event_btnRegistrarIngredienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrarIngrediente;
    private javax.swing.JButton btnVolver;
    private javax.swing.JPanel panelBaseEncabezado;
    private javax.swing.JPanel panelIngredientes;
    private javax.swing.JScrollPane panelScrollIngredientes;
    // End of variables declaration//GEN-END:variables
}
