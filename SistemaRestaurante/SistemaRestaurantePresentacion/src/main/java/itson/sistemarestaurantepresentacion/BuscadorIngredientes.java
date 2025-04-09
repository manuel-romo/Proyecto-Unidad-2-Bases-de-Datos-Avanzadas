package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.UnidadIngrediente;
import itson.sistemarestaurantenegocio.interfaces.IIngredientesBO;
import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class BuscadorIngredientes extends JDialog {

    private int MARGEN_HORIZONTAL_PANELES_INGREDIENTES = 5;
    private int MARGEN_VERTICAL_PANELES_INGREDIENTES = 5;
    private int ALTURA_PANEL_INGREDIENTE = 120;
    private int CANTIDAD_PANELES_FILA = 6;
    
    private Font FUENTE_NOMBRE_INGREDIENTE = new Font("Segoe UI", Font.BOLD, 20);
    
    private Color COLOR_PANEL_INGREDIENTE = new Color(249, 239, 211);
    private Color COLOR_BOTON_EDITAR_INGREDIENTE = new Color(210, 250, 176);
    private Color COLOR_BOTON_ELIMINAR_INGREDIENTE = new Color(255, 224, 206);
    
    private IIngredientesBO ingredientesBO;
    private JFrame framePadre;
    private Long idIngredienteSeleccionado;
    private List<Long> listaIdsIngredientesCoincidentes;
    
    public BuscadorIngredientes(JFrame framePadre, IIngredientesBO ingredientesBO) {
        initComponents();
        this.setName("Búsqueda de clientes");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        this.framePadre = framePadre;
        this.ingredientesBO = ingredientesBO;
        
        configurarListaIngredientes();
    }
    
    public void configurarCampoBusquedaIngredientes(){
        campoTextoBuscarIngrediente.getDocument().addDocumentListener(new DocumentListener() {
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
                String textoBusqueda = campoTextoBuscarIngrediente.getText();
                
                listaIdsIngredientesCoincidentes = new LinkedList<>();
                List<Ingrediente> listaIngredientesNombreCoincidente = new LinkedList<>();
                
                if(comboBoxTipoBusqueda.getSelectedItem().toString().equals("Nombre")){
                    
                    listaIngredientesNombreCoincidente = ingredientesBO.consultarIngredientesNombre(textoBusqueda);
                    
                } else if(comboBoxTipoBusqueda.getSelectedItem().toString().equals("Nombre")){
                    listaIngredientesNombreCoincidente = ingredientesBO.consultarIngredientesUnidad(textoBusqueda);

                }
                
                for(Ingrediente ingredienteCoincidente: listaIngredientesNombreCoincidente){
                    
                    String nombreProducto = ingredienteCoincidente.getNombre();
                    Float stock = ingredienteCoincidente.getCantidad();
                    UnidadIngrediente unidad = ingredienteCoincidente.getUnidad();
                    String direccionImagen = ingredienteCoincidente.getDireccionImagen();
                    
                    
                    String unidadMostrar = "";
                    if(unidad.toString().equals(UnidadIngrediente.GRAMO)){
                        unidadMostrar = "gramos";
                    } else if(unidad.toString().equals(UnidadIngrediente.MILILITRO)){
                        unidadMostrar = "mililitros";
                    } else if(unidad.toString().equals(UnidadIngrediente.PIEZA)){
                        unidadMostrar = "piezas";
                    }
                    
                    DefaultListModel<String> modelo = (DefaultListModel<String>) listaIngredientes.getModel();
                    
                    modelo.addElement(nombreProducto + " - " + stock + " " + unidadMostrar);
                    listaIdsIngredientesCoincidentes.add(ingredienteCoincidente.getId());
                    
                }
            }
        });
    }
    
    private void configurarListaIngredientes(){
        listaIngredientes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {

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
        dispose();
    }
    
    private void cerrar(){
        dispose();
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        etqBuscarIngrediente = new javax.swing.JLabel();
        campoTextoBuscarIngrediente = new javax.swing.JTextField();
        btnCerrar = new javax.swing.JButton();
        scrollPaneIngredientes = new javax.swing.JScrollPane();
        panelScrollListIngredientes = new javax.swing.JScrollPane();
        listaIngredientes = new javax.swing.JList<>();
        btnSeleccionarIngrediente = new javax.swing.JButton();
        comboBoxTipoBusqueda = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(232, 232, 232));
        jPanel1.setPreferredSize(new java.awt.Dimension(605, 491));

        etqBuscarIngrediente.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqBuscarIngrediente.setText("Buscar ingrediente");

        campoTextoBuscarIngrediente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnCerrar.setBackground(new java.awt.Color(205, 255, 197));
        btnCerrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        listaIngredientes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        listaIngredientes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        panelScrollListIngredientes.setViewportView(listaIngredientes);

        scrollPaneIngredientes.setViewportView(panelScrollListIngredientes);

        btnSeleccionarIngrediente.setBackground(new java.awt.Color(205, 255, 197));
        btnSeleccionarIngrediente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSeleccionarIngrediente.setText("Seleccionar ingrediente");
        btnSeleccionarIngrediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarIngredienteActionPerformed(evt);
            }
        });

        comboBoxTipoBusqueda.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        DefaultComboBoxModel<String> modeloComboBox = new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Unidad"});
        comboBoxTipoBusqueda.setModel(null);
        comboBoxTipoBusqueda.setSelectedItem(modeloComboBox.getElementAt(0));
        comboBoxTipoBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTipoBusquedaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Buscar por:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(btnCerrar)
                .addGap(18, 18, 18)
                .addComponent(btnSeleccionarIngrediente)
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollPaneIngredientes)
                    .addComponent(campoTextoBuscarIngrediente)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(etqBuscarIngrediente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxTipoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etqBuscarIngrediente)
                    .addComponent(comboBoxTipoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoTextoBuscarIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(scrollPaneIngredientes, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeleccionarIngrediente)
                    .addComponent(btnCerrar))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        cerrar();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnSeleccionarIngredienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarIngredienteActionPerformed
        seleccionarIngrediente();
    }//GEN-LAST:event_btnSeleccionarIngredienteActionPerformed

    private void comboBoxTipoBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTipoBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxTipoBusquedaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnSeleccionarIngrediente;
    private javax.swing.JTextField campoTextoBuscarIngrediente;
    private javax.swing.JComboBox<String> comboBoxTipoBusqueda;
    private javax.swing.JLabel etqBuscarIngrediente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JList<String> listaIngredientes;
    private javax.swing.JScrollPane panelScrollListIngredientes;
    private javax.swing.JScrollPane scrollPaneIngredientes;
    // End of variables declaration//GEN-END:variables
}
