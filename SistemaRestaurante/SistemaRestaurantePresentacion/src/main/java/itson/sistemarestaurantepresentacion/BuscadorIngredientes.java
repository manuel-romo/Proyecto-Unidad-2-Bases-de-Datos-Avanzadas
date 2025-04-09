package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.UnidadIngrediente;
import itson.sistemarestaurantenegocio.interfaces.IIngredientesBO;
import itson.sistemarestaurantepresentacion.interfaces.IMediador;
import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class BuscadorIngredientes extends JFrame {

    private int MARGEN_HORIZONTAL_PANELES_INGREDIENTES = 5;
    private int MARGEN_VERTICAL_PANELES_INGREDIENTES = 5;
    private int ALTURA_PANEL_INGREDIENTE = 120;
    private int CANTIDAD_PANELES_FILA = 6;
    
    private Font FUENTE_NOMBRE_INGREDIENTE = new Font("Segoe UI", Font.BOLD, 20);
    
    private Color COLOR_PANEL_INGREDIENTE = new Color(249, 239, 211);
    private Color COLOR_BOTON_EDITAR_INGREDIENTE = new Color(210, 250, 176);
    private Color COLOR_BOTON_ELIMINAR_INGREDIENTE = new Color(255, 224, 206);
    
    private IMediador control;
    private IIngredientesBO ingredientesBO;
    private Long idIngredienteSeleccionado;
    private List<Long> listaIdsIngredientesCoincidentes;
    
    public BuscadorIngredientes(IMediador control,IIngredientesBO ingredientesBO) {
        initComponents();
        this.setName("Búsqueda de clientes");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.control = control;
        this.ingredientesBO = ingredientesBO;
        
        configurarCampoBusquedaIngredientes();
        configurarListaIngredientes();
        cargarIngredientesLista(campoTextoBuscarIngrediente.getText());
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

                cargarIngredientesLista(textoBusqueda);

                
                
            }
        });
    }
    
    private void cargarIngredientesLista(String textoBusqueda){

        listaIdsIngredientesCoincidentes = new LinkedList<>();
        List<Ingrediente> listaIngredientesCoincidente = new LinkedList<>();

        if(textoBusqueda.isBlank()){
            listaIngredientesCoincidente = ingredientesBO.consultarIngredientes();
        
        } else if(comboBoxTipoBusqueda.getSelectedItem().toString().equals("Nombre")){

            listaIngredientesCoincidente = ingredientesBO.consultarIngredientesNombre(textoBusqueda);

        } else if(comboBoxTipoBusqueda.getSelectedItem().toString().equals("Unidad")){
            listaIngredientesCoincidente = ingredientesBO.consultarIngredientesUnidad(textoBusqueda);

        }

        DefaultListModel modelo = (DefaultListModel) listaIngredientes.getModel();

        modelo.clear();

        for(Ingrediente ingredienteCoincidente: listaIngredientesCoincidente){

            String nombreProducto = ingredienteCoincidente.getNombre();
            Float stock = ingredienteCoincidente.getCantidad();
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
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        etqBuscarIngrediente = new javax.swing.JLabel();
        campoTextoBuscarIngrediente = new javax.swing.JTextField();
        btnCerrar = new javax.swing.JButton();
        btnSeleccionarIngrediente = new javax.swing.JButton();
        comboBoxTipoBusqueda = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        panelScrollListIngredientes = new javax.swing.JScrollPane();
        listaIngredientes = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        etqBuscarIngrediente1 = new javax.swing.JLabel();
        campoTextoBuscarIngrediente1 = new javax.swing.JTextField();
        btnCerrar1 = new javax.swing.JButton();
        btnSeleccionarIngrediente1 = new javax.swing.JButton();
        comboBoxTipoBusqueda1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        panelScrollListIngredientes1 = new javax.swing.JScrollPane();
        listaIngredientes1 = new javax.swing.JList<>();
        etqBuscarIngrediente2 = new javax.swing.JLabel();
        campoTextoBuscarIngrediente2 = new javax.swing.JTextField();
        btnSeleccionarIngrediente2 = new javax.swing.JButton();
        btnCerrar2 = new javax.swing.JButton();
        comboBoxTipoBusqueda2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

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

        btnSeleccionarIngrediente.setBackground(new java.awt.Color(205, 255, 197));
        btnSeleccionarIngrediente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSeleccionarIngrediente.setText("Seleccionar ingrediente");
        btnSeleccionarIngrediente.setEnabled(false);
        btnSeleccionarIngrediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarIngredienteActionPerformed(evt);
            }
        });

        comboBoxTipoBusqueda.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        DefaultComboBoxModel<String> modeloComboBox = new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Unidad"});
        comboBoxTipoBusqueda.setModel(modeloComboBox);
        comboBoxTipoBusqueda.setSelectedItem(modeloComboBox.getElementAt(0));
        comboBoxTipoBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTipoBusquedaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Buscar por:");

        listaIngredientes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        listaIngredientes.setModel(modeloLista);
        panelScrollListIngredientes.setViewportView(listaIngredientes);

        jPanel2.setBackground(new java.awt.Color(232, 232, 232));
        jPanel2.setPreferredSize(new java.awt.Dimension(605, 491));

        etqBuscarIngrediente1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqBuscarIngrediente1.setText("Buscar ingrediente");

        campoTextoBuscarIngrediente1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnCerrar1.setBackground(new java.awt.Color(205, 255, 197));
        btnCerrar1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCerrar1.setText("Cerrar");
        btnCerrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrar1ActionPerformed(evt);
            }
        });

        btnSeleccionarIngrediente1.setBackground(new java.awt.Color(205, 255, 197));
        btnSeleccionarIngrediente1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSeleccionarIngrediente1.setText("Seleccionar ingrediente");
        btnSeleccionarIngrediente1.setEnabled(false);
        btnSeleccionarIngrediente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarIngrediente1ActionPerformed(evt);
            }
        });

        comboBoxTipoBusqueda1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        comboBoxTipoBusqueda1.setModel(modeloComboBox);
        comboBoxTipoBusqueda.setSelectedItem(modeloComboBox.getElementAt(0));
        comboBoxTipoBusqueda1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTipoBusqueda1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Buscar por:");

        listaIngredientes1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        listaIngredientes.setModel(modeloLista);
        panelScrollListIngredientes1.setViewportView(listaIngredientes1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(btnCerrar1)
                .addGap(18, 18, 18)
                .addComponent(btnSeleccionarIngrediente1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(panelScrollListIngredientes1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(campoTextoBuscarIngrediente1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(etqBuscarIngrediente1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxTipoBusqueda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etqBuscarIngrediente1)
                    .addComponent(comboBoxTipoBusqueda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoTextoBuscarIngrediente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelScrollListIngredientes1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeleccionarIngrediente1)
                    .addComponent(btnCerrar1))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        etqBuscarIngrediente2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqBuscarIngrediente2.setText("Buscar ingrediente");

        campoTextoBuscarIngrediente2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnSeleccionarIngrediente2.setBackground(new java.awt.Color(205, 255, 197));
        btnSeleccionarIngrediente2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSeleccionarIngrediente2.setText("Seleccionar ingrediente");
        btnSeleccionarIngrediente2.setEnabled(false);
        btnSeleccionarIngrediente2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarIngrediente2ActionPerformed(evt);
            }
        });

        btnCerrar2.setBackground(new java.awt.Color(205, 255, 197));
        btnCerrar2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCerrar2.setText("Cerrar");
        btnCerrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrar2ActionPerformed(evt);
            }
        });

        comboBoxTipoBusqueda2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        comboBoxTipoBusqueda2.setModel(modeloComboBox);
        comboBoxTipoBusqueda.setSelectedItem(modeloComboBox.getElementAt(0));
        comboBoxTipoBusqueda2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTipoBusqueda2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Buscar por:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(btnCerrar)
                .addGap(18, 18, 18)
                .addComponent(btnSeleccionarIngrediente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelScrollListIngredientes, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(campoTextoBuscarIngrediente)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(etqBuscarIngrediente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxTipoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(etqBuscarIngrediente2)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(campoTextoBuscarIngrediente2)
                    .addContainerGap()))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnSeleccionarIngrediente2)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnCerrar2)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(comboBoxTipoBusqueda2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addGap(0, 0, Short.MAX_VALUE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelScrollListIngredientes, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeleccionarIngrediente)
                    .addComponent(btnCerrar))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(etqBuscarIngrediente2)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(campoTextoBuscarIngrediente2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnSeleccionarIngrediente2)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnCerrar2)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(comboBoxTipoBusqueda2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btnCerrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrar1ActionPerformed

    private void btnSeleccionarIngrediente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarIngrediente1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSeleccionarIngrediente1ActionPerformed

    private void comboBoxTipoBusqueda1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTipoBusqueda1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxTipoBusqueda1ActionPerformed

    private void btnSeleccionarIngrediente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarIngrediente2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSeleccionarIngrediente2ActionPerformed

    private void btnCerrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrar2ActionPerformed

    private void comboBoxTipoBusqueda2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTipoBusqueda2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxTipoBusqueda2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnCerrar1;
    private javax.swing.JButton btnCerrar2;
    private javax.swing.JButton btnSeleccionarIngrediente;
    private javax.swing.JButton btnSeleccionarIngrediente1;
    private javax.swing.JButton btnSeleccionarIngrediente2;
    private javax.swing.JTextField campoTextoBuscarIngrediente;
    private javax.swing.JTextField campoTextoBuscarIngrediente1;
    private javax.swing.JTextField campoTextoBuscarIngrediente2;
    private javax.swing.JComboBox<String> comboBoxTipoBusqueda;
    private javax.swing.JComboBox<String> comboBoxTipoBusqueda1;
    private javax.swing.JComboBox<String> comboBoxTipoBusqueda2;
    private javax.swing.JLabel etqBuscarIngrediente;
    private javax.swing.JLabel etqBuscarIngrediente1;
    private javax.swing.JLabel etqBuscarIngrediente2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JList<String> listaIngredientes;
    private javax.swing.JList<String> listaIngredientes1;
    private javax.swing.JScrollPane panelScrollListIngredientes;
    private javax.swing.JScrollPane panelScrollListIngredientes1;
    // End of variables declaration//GEN-END:variables
}
