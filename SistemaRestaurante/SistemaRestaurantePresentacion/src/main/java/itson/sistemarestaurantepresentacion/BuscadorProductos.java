
package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantenegocio.interfaces.IProductosBO;
import itson.sistemarestaurantepresentacion.interfaces.IMediador;
import java.awt.Color;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Usuario
 */
public class BuscadorProductos extends javax.swing.JPanel {

    /**
     * Creates new form BuscadorProductos
     */
    public BuscadorProductos(IMediador control, IProductosBO productosBO) {
        private int MARGEN_HORIZONTAL_PANELES_INGREDIENTES = 5;
        private int MARGEN_VERTICAL_PANELES_INGREDIENTES = 5;
        private int ALTURA_PANEL_INGREDIENTE = 120;
        private int CANTIDAD_PANELES_FILA = 6;

        private Font FUENTE_NOMBRE_INGREDIENTE = new Font("Segoe UI", Font.BOLD, 20);

        private Color COLOR_PANEL_INGREDIENTE = new Color(249, 239, 211);
        private Color COLOR_BOTON_EDITAR_INGREDIENTE = new Color(210, 250, 176);
        private Color COLOR_BOTON_ELIMINAR_INGREDIENTE = new Color(255, 224, 206);

        private IMediador control;
        private IProductosBO productosBO;
        private Long idProductosSeleccionado;
        private List<Long> listaProductosCoincidentes;

        public BuscadorProductos(IMediador control, IProductosBO productosBO) {
            initComponents();
            this.setName("Búsqueda de clientes");
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            this.control = control;
            this.productosBO = productosBO;

            configurarCampoBusquedaProductos();
            configurarListaProductos();
            cargarProductosLista(campoTextoBuscarProducto.getText());
        }

        public void configurarCampoBusquedaProductos(){
            campoTextoBuscarProducto.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    actualizarListaIngredientes();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    actualizarListaIngredientes();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    actualizarListaIngredientes();
                }

                private void actualizarListaIngredientes() {

                    String textoBusqueda = campoTextoBuscarProducto.getText();

                    cargarIngredientesLista(textoBusqueda);
                }
            });
        }

        private void cargarProductosLista(String textoBusqueda){

            listaIdsProductosCoincidentes = new LinkedList<>();
            List<Producto> listaProductosCoincidente = new LinkedList<>();

            if(textoBusqueda.isBlank()){
                listaProductosCoincidente = productosBO.consultarProductos();

            } else if(comboBoxTipoBusqueda.getSelectedItem().toString().equals("Nombre")){

                listaProductosCoincidente = productosBO.consultarProductosNombre(textoBusqueda);

            } else if(comboBoxTipoBusqueda.getSelectedItem().toString().equals("Tipo")){
                listaProductosCoincidente = productosBO.consultarProductosTipo(textoBusqueda);

            }

            DefaultListModel modelo = (DefaultListModel) listaProductos.getModel();

            modelo.clear();

            for(Producto productoCoincidente: listaProductosCoincidente){

                String nombreProducto = productoCoincidente.getNombre();
                Float precio = productoCoincidente.getTipo();
                UnidadIngrediente unidad = ingredienteCoincidente.getUnidad();


                String unidadMostrar = "";
                if(unidad.toString().equals(UnidadIngrediente.GRAMO.toString())){
                    unidadMostrar = "gramos";
                } else if(unidad.toString().equals(UnidadIngrediente.MILILITRO.toString())){
                    unidadMostrar = "mililitros";
                } else if(unidad.toString().equals(UnidadIngrediente.PIEZA.toString())){
                    unidadMostrar = "piezas";
                }

                modelo.addElement(nombreProducto + ":  " + stock + " " + unidadMostrar);
                listaIdsIngredientesCoincidentes.add(ingredienteCoincidente.getId());
            }
        }

        private void configurarListaIngredientes(){
            listaIngredientes.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent evt) {

                    // Se determina si aún se está realizando la selección sobre la lista
                    if (!evt.getValueIsAdjusting()) {
                        int indiceIngredienteSeleccionado = listaIngredientes.getSelectedIndex();

                        if(indiceIngredienteSeleccionado == -1){
                            btnSeleccionarIngrediente.setEnabled(false);
                        } else{
                            idIngredienteSeleccionado = listaIdsIngredientesCoincidentes.get(indiceIngredienteSeleccionado);

                            btnSeleccionarIngrediente.setEnabled(true);
                        }

                    }
                }
            });
        }

        private void seleccionarIngrediente(){
            control.actualizarVentanaResultadoBusquedaIngrediente(this, idIngredienteSeleccionado);
        }

        private void cerrar(){
            control.cerrarBuscador(this);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipalBuscador = new javax.swing.JPanel();
        etqBuscarProducto = new javax.swing.JLabel();
        campoTxtBuscarProducto = new javax.swing.JTextField();
        etqCantidad = new javax.swing.JLabel();
        campoTxtCantidad = new javax.swing.JTextField();

        panelPrincipalBuscador.setBackground(new java.awt.Color(232, 232, 232));

        etqBuscarProducto.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqBuscarProducto.setText("Buscar producto por nombre o categoría");

        campoTxtBuscarProducto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        etqCantidad.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqCantidad.setText("Cantidad");

        campoTxtCantidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelPrincipalBuscadorLayout = new javax.swing.GroupLayout(panelPrincipalBuscador);
        panelPrincipalBuscador.setLayout(panelPrincipalBuscadorLayout);
        panelPrincipalBuscadorLayout.setHorizontalGroup(
            panelPrincipalBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalBuscadorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etqBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTxtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelPrincipalBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etqCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTxtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPrincipalBuscadorLayout.setVerticalGroup(
            panelPrincipalBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalBuscadorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etqBuscarProducto)
                    .addComponent(etqCantidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoTxtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTxtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipalBuscador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipalBuscador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campoTxtBuscarProducto;
    private javax.swing.JTextField campoTxtCantidad;
    private javax.swing.JLabel etqBuscarProducto;
    private javax.swing.JLabel etqCantidad;
    private javax.swing.JPanel panelPrincipalBuscador;
    // End of variables declaration//GEN-END:variables
}
