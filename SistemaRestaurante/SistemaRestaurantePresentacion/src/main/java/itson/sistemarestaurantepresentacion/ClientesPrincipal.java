package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.Cliente;
import itson.sistemarestaurantenegocio.excepciones.ClienteConsultadoNoExisteException;
import itson.sistemarestaurantenegocio.excepciones.IdClienteNuloException;
import itson.sistemarestaurantenegocio.interfaces.IClientesBO;
import itson.sistemarestaurantepresentacion.interfaces.IMediador;
import itson.sistemarestaurantepresentacion.interfaces.IVistaReceptoraIdCliente;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * Clase que representa un formulario para presentar
 * La pantalla principal del módulo de clientes
 * @author Yuri Germán García López
 * ID: 00000252583
 */
public class ClientesPrincipal extends javax.swing.JFrame implements IVistaReceptoraIdCliente{

    /**
     * Objeto que implementa la interfaz IMediador, es el control que coordina
     * el flujo entre pantallas
     */
    private IMediador control;
    /**
     * Objeto que implementa la interfaz IClientesBO, clase que permite
     * realizar operaciones de negocio sobre objetos de tipo Cliente
     */
    private IClientesBO clientesBO;
    
    private int CANTIDAD_PANELES_FILA = 5;
    private int MARGEN_HORIZONTAL_PANELES_CLIENTES = 5;
    private int MARGEN_VERTICAL_PANELES_CLIENTES = 5;
    private int ALTURA_PANEL_CLIENTE = 120;
    private int MARGEN_LATERAL_IMAGEN_ICONO_CLIENTE = 20;
    
    private Font FUENTE_NOMBRE_CLIENTE = new Font("Segoe UI", Font.BOLD, 20);
    private Color COLOR_FUENTE_LETRA = new Color(0,0,0);
    
    private Color COLOR_PANEL_CLIENTE = new Color(255, 255, 255);
    private Color COLOR_BOTON_EDITAR_CLIENTE = new Color(205, 255, 197);
    private Color COLOR_BOTON_ELIMINAR_CLIENTE= new Color(255, 163, 163);
    private Color COLOR_BOTON_VOLVER_PRINCIPAL_CLIENTES = new Color(205, 255, 197);
    
    private static final Logger LOG = Logger.getLogger(IngredientesPrincipal.class.getName());

    /**
     * Contructor que permite crear una ventana que representa la pantalla
     * Principal del módulo de clientes
     * @param control Representa el mediador que funje como control para el flujo entre pantallas
     * @param clientesBO Representa una instancia de la clase clientesBO para realizar operaciones de negocio en clientes
     */
    public ClientesPrincipal(IMediador control, IClientesBO clientesBO) {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        
        this.clientesBO = clientesBO;
        this.control = control;
        
        panelBaseEncabezado.add(new Encabezado());
        
        cargarClientes();
    }

    /**
     * Método que permite cargar los clientes frecuentes registrados en el sistema
     */
    private void cargarClientes(){  
        List<Cliente> listaClientesConsultados = clientesBO.consultarClientes();
        
        configurarLayoutPanelTodosClientes(listaClientesConsultados.size());
  
        for(Cliente cliente: listaClientesConsultados){
            
            panelClientesFrecuentes.add(crearPanelCliente(cliente));  
        }
    }
    
    /**
     * Método que permite cargar un cliente registrado en el sistema a partir de su Id
     */
    private void cargarCliente(Long idCliente){
        Cliente cliente = null;
        try {
            cliente = clientesBO.consultarCliente(idCliente);
            
            configurarPanelClienteSolo();
            
            JPanel panelCliente = crearPanelCliente(cliente);

            panelClientesFrecuentes.add(panelCliente, BorderLayout.CENTER);
            
            panelCliente.add(crearPanelBtnVolverClientesFrecuentes());

        } catch (ClienteConsultadoNoExisteException | IdClienteNuloException ex) {
            LOG.log(Level.SEVERE, "Error al mostrar el cliente buscado. " + ex.getMessage());
            JOptionPane.showMessageDialog(
                    this, 
                    ex.getMessage(), 
                    "Error al mostrar el cliente buscado", 
                    JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    /**
     * Método que permite configurar el layout del panel que contiene los clientes frecuentes
     * @param cantidadClientes Dato int que representa el número de clientes frecuentes que se deben mostrar
     */
    private void configurarLayoutPanelTodosClientes(int cantidadClientes){    
        panelClientesFrecuentes.removeAll();
        
        int numeroFilasLlenar = (int)Math.ceil((double)cantidadClientes/CANTIDAD_PANELES_FILA);
                
                
        GridLayout gridLayout = new GridLayout(numeroFilasLlenar, CANTIDAD_PANELES_FILA);
        
        gridLayout.setHgap(MARGEN_HORIZONTAL_PANELES_CLIENTES);
        gridLayout.setVgap(MARGEN_VERTICAL_PANELES_CLIENTES);
        
        panelClientesFrecuentes.setLayout(gridLayout);
        
        panelClientesFrecuentes.setPreferredSize(
                new Dimension(0,
                2 * (numeroFilasLlenar * ALTURA_PANEL_CLIENTE) + ALTURA_PANEL_CLIENTE));
    }
    
    /**
     * Permite configurar el layout del panel que contiene a un solo cliente
     * necesario cuando se realiza una búsqueda.
     */
    private void configurarPanelClienteSolo(){
        
        panelClientesFrecuentes.removeAll();
        panelClientesFrecuentes.setPreferredSize(new Dimension(0,0));
        
        BorderLayout borderLayout = new BorderLayout();
        
        panelClientesFrecuentes.setLayout(borderLayout);
    }
    
    /**
     * Método que permite crear el panel que contiene la información sobre cada
     * cliente a mostar, incluyendo botones para editarlo y eliminarlo
     * @param cliente Objeto Long que representa el Id del cliente
     * a modificar.
     * @return JPanel  que contiene la información del cliente
     * a mostrar.
     */
    private JPanel crearPanelCliente(Cliente cliente){
        
        //Crear y configurar el panel del cliente
        JPanel panelCliente = new JPanel();
        
        panelCliente.setBackground(COLOR_PANEL_CLIENTE);
            
        panelCliente.setLayout(new BoxLayout(panelCliente, BoxLayout.Y_AXIS));
        
        //Crear y agregar el panel con la imagen del icono del cliente
        panelCliente.add(crearPanelImagenIconoCliente());
        
        //Crear y agregar el panel con el nombre del cliente
        panelCliente.add(crearPanelNombreCliente(cliente));
        
        //Crear y agregar el panel para el correo electrónico del cliente
        panelCliente.add(crearPanelCorreoElectronicoCliente(cliente));
        
        //Crear y agregar el panel para los puntos de fidelidad del cliente
        panelCliente.add(crearPanelPuntosFidelidadCliente(cliente));
   
        //Crear y agregar un panel para agregar los botones de editar y eliminar cliente
        panelCliente.add(crearPanelBtnEditarEliminarIngrediente(cliente));
        
        return panelCliente;
    }
    
    /**
     * Método que permite crear el panel con la imagen del icono del cliente
     * @return JPanel con la imagen del icono del cliente
     */
    private JPanel crearPanelImagenIconoCliente(){
        
        //Crear el panel de la imangen del icono del cliente
        JPanel panelImagenIconoCliente = new JPanel(new FlowLayout());
            
        //Crear una etiqueta para añadir la imagen del icono
        JLabel etqImagenIconoCliente = new JLabel();

        //Obtener la imagen del icono
        ImageIcon imagenCliente;

        imagenCliente = new ImageIcon(getClass().getResource("/iconoUsuario2.png"));

        //Determinar el nuevo tamaño de la imagen para ajustarla al panel
        int nuevoAnchoImagen = (panelClientesFrecuentes.getWidth() - (MARGEN_HORIZONTAL_PANELES_CLIENTES * (CANTIDAD_PANELES_FILA + 1)))
                /CANTIDAD_PANELES_FILA - MARGEN_LATERAL_IMAGEN_ICONO_CLIENTE * 2;     
        
        int nuevoAltoImagen = ALTURA_PANEL_CLIENTE;

        //Redimensionar la imagen del icono de usuario
        ImageIcon imagenClienteRedimensionada = ImagenesUtils.redimensionarImagen(
                imagenCliente, nuevoAnchoImagen, nuevoAltoImagen);

        //Agregar a la etiqueta la imagen redimensionada del icono
        etqImagenIconoCliente.setIcon(imagenClienteRedimensionada);

        //Agregar la etiqueta con la imagen del icono al panel
        panelImagenIconoCliente.add(etqImagenIconoCliente);
        
        panelImagenIconoCliente.setOpaque(false);
        
        return panelImagenIconoCliente;
    }
    
    /**
     * Método que permite crear el panel con el nombre del cliente
     * @param cliente Objeto Cliente del que se mostrará su nombre
     * @return JPanel con el nombre del cliente
     */
    private JPanel crearPanelNombreCliente(Cliente cliente){
        JPanel panelNombreCliente = new JPanel(new FlowLayout());
        
        JLabel nombreCliente = new JLabel(cliente.getNombres());
        
        nombreCliente.setForeground(COLOR_FUENTE_LETRA);
        
        nombreCliente.setFont(FUENTE_NOMBRE_CLIENTE);
        
        panelNombreCliente.add(nombreCliente);
        
        panelNombreCliente.setOpaque(false);
        
        return panelNombreCliente;
    }
    
    /**
     * Método que permite crear el panel con el correo electrónico del cliente
     * @param cliente Objeto Cliente del que se mostrará su correo electrónico
     * @return JPanel con el correo electrónico del cliente
     */
    private JPanel crearPanelCorreoElectronicoCliente(Cliente cliente){
        JPanel panelCorreoElectronicoCliente = new JPanel(new FlowLayout());
        
        JLabel labelCorreoElectronico = new JLabel("Correo Electronico: " + cliente.getCorreoElectronico());
        labelCorreoElectronico.setForeground(COLOR_FUENTE_LETRA);
        
        panelCorreoElectronicoCliente.add(labelCorreoElectronico);
        
        panelCorreoElectronicoCliente.setOpaque(false);
        
        return panelCorreoElectronicoCliente;
    }
    
    /**
     * Método que permite crear un panel que contiene dos botones, el primero que permite
     * editar el cliente, y el segundo que permite eliminarlo
     * @param cliente Objeto Cliente que representa el cliente que será editado o elimnado
     * @return JPanel que contiene los botones para editar o eliminar el ingrediente especificado
     */
    private JPanel crearPanelBtnEditarEliminarIngrediente(Cliente cliente){
        JPanel panelBotonesEditarEliminarCliente = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        //Crear ambos botones
        panelBotonesEditarEliminarCliente.add(crearBtnEditarCliente(cliente.getId()));
        panelBotonesEditarEliminarCliente.add(crearBtnEliminarCliente(cliente.getId()));

        panelBotonesEditarEliminarCliente.setOpaque(false);
        
        return panelBotonesEditarEliminarCliente;
    }
    
    /**
     * Método que permite crear el botón para editar los datos de un cliente
     * @param idCliente Objeto Long que representa el Id del cliente para el que se creará el botón de edición
     * @return JButton que permite editar el cliente cuando es presionado
     */
    private JButton crearBtnEditarCliente(Long idCliente){
        
        JButton btnEditarCliente = new JButton("Editar");
        
        btnEditarCliente.setBackground(COLOR_BOTON_EDITAR_CLIENTE);
        btnEditarCliente.setForeground(COLOR_FUENTE_LETRA);
        
        //Definir un Action Listener para el JButton, se ejecutará el método
        //mostrarEditarCliente() cada vez que se presione
        btnEditarCliente.addActionListener(e -> mostrarEditarCliente(idCliente));
        
        return btnEditarCliente;
    }
    
    /**
     * Método que permite crear el botón para eliminar un cliente
     * @param idCliente Objeto Long que representa el Id del cliente a eliminar cuando se presione el botón
     * @return JButton que permite eliminar el cliente cuando es presionado
     */
    private JButton crearBtnEliminarCliente(Long idCliente){
        
        JButton btnEliminarCliente = new JButton("Eliminar");
        
        btnEliminarCliente.setBackground(COLOR_BOTON_ELIMINAR_CLIENTE);
        btnEliminarCliente.setForeground(COLOR_FUENTE_LETRA);
        
        //Definir  un Action Listener para el JButton, se ejecutará el método
        //eliminarCliente() cuando se presione
        btnEliminarCliente.addActionListener(e -> eliminarIngrediente(idCliente));
        
        return btnEliminarCliente;
    }
    
    /**
     * Método que permite crear un panel que contiene al botón que al presionar
     * muestra la pantalla principal del módulo de clientes
     * @return JPanel que contiene el botón que permite mostrar la
     * pantalla principal del módulo de clientes
     */
    private JPanel crearPanelBtnVolverClientesFrecuentes(){
        JPanel panelBtnVolverClientesFrecuentes = new JPanel();
        
        panelBtnVolverClientesFrecuentes.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        panelBtnVolverClientesFrecuentes.add(crearBtnVolverClientesFrecuentes());
        
        panelBtnVolverClientesFrecuentes.setOpaque(false);
        
        return panelBtnVolverClientesFrecuentes;
    }
    
    /**
     * Método que permite crear un botón para volver a la pantalla principal
     * del módulo de clientes
     * @return JButton que permite eliminar el cliente cuando
     * es presionado
     */
    private JButton crearBtnVolverClientesFrecuentes(){
        
        JButton btnVolverClientesFrecuentes = new JButton();
        
        btnVolverClientesFrecuentes.addActionListener(e -> control.mostrarClientesPrincipal(this));
        
        btnVolverClientesFrecuentes.setText("Volver");
        
        btnVolverClientesFrecuentes.setForeground(COLOR_FUENTE_LETRA);
        
        btnVolverClientesFrecuentes.setBackground(COLOR_BOTON_VOLVER_PRINCIPAL_CLIENTES);
        
        return btnVolverClientesFrecuentes;
    }
    
    /**
     * Método que permite mostrar la ventana que permite editar un cliente
     * @param idCliente Objeto Long que representa el Id del cliente
     * que se va a editar.
     */
    private void mostrarEditarCliente(Long idCliente){
        control.mostrarEditarCliente(this, idCliente);
    }
    
    private void eliminarIngrediente(Long idIngrediente){
        // TODO Pendiente para que avise si hay productos relacionados con este ingrediente
    }
    
    /**
     * Método que permite mostrar la pantalla de registro de un cliente frecuente
     */
    private void mostrarRegistrarClienteFrecuente(){
        control.mostrarRegistroClienteFrecuente(this);
    
    }
    
    /**
     * Método que permite mostrar la ventana del buscador de clientes
     */
    private void mostrarBuscadorClientes(){
        control.mostrarBuscadorClientes(this);
    }
    
    /**
     * Método que permite mostrar el menú principal del sistema
     */
    private void mostrarMenuPrincipal(){
        control.mostrarMenuPrincipal(this);
    }
    
    /**
     * Implementación del método setIdCliente que permite establecer el Id del cliente 
     * que fue seleccionado al ejecutar la ventana que permite la búsqueda y selección de cliente
     * @param idCliente Objeto Integer que representa el Id del cliente
     * que fue seleccionado por el usuario en la venta de búsqueda de clientes
     */
    @Override
    public void setIdCliente(Long idCliente) {
        cargarCliente(idCliente);
        getContentPane().revalidate();
        repaint();
    }
    
    /**
     * Implementación del método habilitar(), de la interfaz que
     * permite colocar el valor de atributo enabled de este JFrame como true o false, 
     * para permitir su edición o no
     * @param habilitado Valor booleano que define el valor del atributo enabled de 
     * este JFrame, si es true, se permitirá su edición, si es false, no se permitirá
     * editarlo.
     */
    @Override
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
        panelBaseEncabezado = new javax.swing.JPanel();
        etqClientesFrecuentes = new javax.swing.JLabel();
        btnBuscarClientes = new javax.swing.JButton();
        btnRegistrarClienteFrecuente = new javax.swing.JButton();
        panelScrollClientesFrecuentes = new javax.swing.JScrollPane();
        panelClientesFrecuentes = new javax.swing.JPanel();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(232, 232, 232));

        panelBaseEncabezado.setBackground(new java.awt.Color(255, 230, 188));

        javax.swing.GroupLayout panelBaseEncabezadoLayout = new javax.swing.GroupLayout(panelBaseEncabezado);
        panelBaseEncabezado.setLayout(panelBaseEncabezadoLayout);
        panelBaseEncabezadoLayout.setHorizontalGroup(
            panelBaseEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelBaseEncabezadoLayout.setVerticalGroup(
            panelBaseEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        etqClientesFrecuentes.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqClientesFrecuentes.setForeground(new java.awt.Color(0, 0, 0));
        etqClientesFrecuentes.setText("Clientes frecuentes:");

        btnBuscarClientes.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarClientes.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnBuscarClientes.setForeground(new java.awt.Color(0, 0, 0));
        btnBuscarClientes.setText("Buscar clientes");
        btnBuscarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClientesActionPerformed(evt);
            }
        });

        btnRegistrarClienteFrecuente.setBackground(new java.awt.Color(255, 255, 255));
        btnRegistrarClienteFrecuente.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnRegistrarClienteFrecuente.setForeground(new java.awt.Color(0, 0, 0));
        btnRegistrarClienteFrecuente.setText("Registrar cliente frecuente");
        btnRegistrarClienteFrecuente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarClienteFrecuenteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelClientesFrecuentesLayout = new javax.swing.GroupLayout(panelClientesFrecuentes);
        panelClientesFrecuentes.setLayout(panelClientesFrecuentesLayout);
        panelClientesFrecuentesLayout.setHorizontalGroup(
            panelClientesFrecuentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
        );
        panelClientesFrecuentesLayout.setVerticalGroup(
            panelClientesFrecuentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 408, Short.MAX_VALUE)
        );

        panelScrollClientesFrecuentes.setViewportView(panelClientesFrecuentes);

        btnVolver.setBackground(new java.awt.Color(205, 255, 197));
        btnVolver.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(0, 0, 0));
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBaseEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(panelScrollClientesFrecuentes)
                        .addContainerGap())
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(etqClientesFrecuentes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnRegistrarClienteFrecuente)
                        .addGap(18, 18, 18))))
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(467, 467, 467)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(panelBaseEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etqClientesFrecuentes)
                    .addComponent(btnBuscarClientes)
                    .addComponent(btnRegistrarClienteFrecuente))
                .addGap(18, 18, 18)
                .addComponent(panelScrollClientesFrecuentes, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVolver)
                .addGap(0, 17, Short.MAX_VALUE))
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

    private void btnBuscarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClientesActionPerformed
        mostrarBuscadorClientes();
    }//GEN-LAST:event_btnBuscarClientesActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        mostrarMenuPrincipal();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnRegistrarClienteFrecuenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarClienteFrecuenteActionPerformed
        mostrarRegistrarClienteFrecuente();
    }//GEN-LAST:event_btnRegistrarClienteFrecuenteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarClientes;
    private javax.swing.JButton btnRegistrarClienteFrecuente;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel etqClientesFrecuentes;
    private javax.swing.JPanel panelBaseEncabezado;
    private javax.swing.JPanel panelClientesFrecuentes;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JScrollPane panelScrollClientesFrecuentes;
    // End of variables declaration//GEN-END:variables
}
