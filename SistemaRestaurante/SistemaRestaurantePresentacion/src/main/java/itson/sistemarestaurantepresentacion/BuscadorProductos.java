/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantedominio.TipoProducto;
import itson.sistemarestaurantenegocio.interfaces.IProductosBO;
import itson.sistemarestaurantepresentacion.interfaces.IMediador;
import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author ramuk
 */
public class BuscadorProductos extends JFrame {

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
    private Long idProductoSeleccionado;
    private List<Long> listaIdsProductosCoincidentes;
    
    public BuscadorProductos(IMediador control,IProductosBO productosBO) {
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
                actualizarListaProductos();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarListaProductos();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarListaProductos();
            }

            private void actualizarListaProductos() {
                
                String textoBusqueda = campoTextoBuscarProducto.getText();

                cargarProductosLista(textoBusqueda);
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
            Float stock = productoCoincidente.getPrecio();
            TipoProducto tipo = productoCoincidente.getTipo();


            String tipoMostrar = "";
            if(tipo.toString().equals(TipoProducto.BEBIDA.toString())){
                tipoMostrar = "bebida";
            } else if(tipo.toString().equals(TipoProducto.ENTRADA.toString())){
                tipoMostrar = "entrada";
            } else if(tipo.toString().equals(TipoProducto.PLATILLO.toString())){
                tipoMostrar = "platillo";
            } else if(tipo.toString().equals(TipoProducto.POSTRE.toString())){
                tipoMostrar = "postre";
            }

            modelo.addElement(nombreProducto + ":  " + stock + " " + tipoMostrar);
            listaIdsProductosCoincidentes.add(productoCoincidente.getId());
        }
    }
    
    private void configurarListaProductos(){
        listaProductos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent evt) {

                // Se determina si aún se está realizando la selección sobre la lista
                if (!evt.getValueIsAdjusting()) {
                    int indiceProductoSeleccionado = listaProductos.getSelectedIndex();

                    if(indiceProductoSeleccionado == -1){
                        btnSeleccionarProducto.setEnabled(false);
                    } else{
                        idProductoSeleccionado = listaIdsProductosCoincidentes.get(indiceProductoSeleccionado);

                        btnSeleccionarProducto.setEnabled(true);
                    }

                }
            }
        });
    }
    
    private void seleccionarProducto(){
        control.actualizarVentanaResultadoBusquedaProductos(this, idProductoSeleccionado);
    }
    
    private void cerrar(){
        control.cerrarBuscador(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        etqBuscarProducto = new javax.swing.JLabel();
        campoTextoBuscarProducto = new javax.swing.JTextField();
        btnCerrar = new javax.swing.JButton();
        btnSeleccionarProducto = new javax.swing.JButton();
        comboBoxTipoBusqueda = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        panelScrollListProductos = new javax.swing.JScrollPane();
        listaProductos = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        etqBuscarProducto1 = new javax.swing.JLabel();
        campoTextoBuscarProducto1 = new javax.swing.JTextField();
        btnCerrar1 = new javax.swing.JButton();
        btnSeleccionarProducto1 = new javax.swing.JButton();
        comboBoxTipoBusqueda1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        panelScrollListProductos1 = new javax.swing.JScrollPane();
        listaProductos1 = new javax.swing.JList<>();
        etqBuscarProducto2 = new javax.swing.JLabel();
        campoTextoBuscarProducto2 = new javax.swing.JTextField();
        btnSeleccionarProducto2 = new javax.swing.JButton();
        btnCerrar2 = new javax.swing.JButton();
        comboBoxTipoBusqueda2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(232, 232, 232));
        jPanel1.setPreferredSize(new java.awt.Dimension(605, 491));

        etqBuscarProducto.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqBuscarProducto.setText("Buscar producto");

        campoTextoBuscarProducto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campoTextoBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTextoBuscarProductoActionPerformed(evt);
            }
        });

        btnCerrar.setBackground(new java.awt.Color(205, 255, 197));
        btnCerrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnSeleccionarProducto.setBackground(new java.awt.Color(205, 255, 197));
        btnSeleccionarProducto.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSeleccionarProducto.setText("Seleccionar producto");
        btnSeleccionarProducto.setEnabled(false);
        btnSeleccionarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarProductoActionPerformed(evt);
            }
        });

        comboBoxTipoBusqueda.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        comboBoxTipoBusqueda.setModel(modeloComboBox);
        comboBoxTipoBusqueda.setSelectedItem(modeloComboBox.getElementAt(0));
        comboBoxTipoBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTipoBusquedaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Buscar por:");

        listaProductos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        listaProductos.setModel(modeloLista);
        panelScrollListProductos.setViewportView(listaProductos);

        jPanel2.setBackground(new java.awt.Color(232, 232, 232));
        jPanel2.setPreferredSize(new java.awt.Dimension(605, 491));

        etqBuscarProducto1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqBuscarProducto1.setText("Buscar producto");

        campoTextoBuscarProducto1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnCerrar1.setBackground(new java.awt.Color(205, 255, 197));
        btnCerrar1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCerrar1.setText("Cerrar");
        btnCerrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrar1ActionPerformed(evt);
            }
        });

        btnSeleccionarProducto1.setBackground(new java.awt.Color(205, 255, 197));
        btnSeleccionarProducto1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSeleccionarProducto1.setText("Seleccionar producto");
        btnSeleccionarProducto1.setEnabled(false);
        btnSeleccionarProducto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarProducto1ActionPerformed(evt);
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

        listaProductos1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        listaProductos.setModel(modeloLista);
        panelScrollListProductos1.setViewportView(listaProductos1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(btnCerrar1)
                .addGap(18, 18, 18)
                .addComponent(btnSeleccionarProducto1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(panelScrollListProductos1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(campoTextoBuscarProducto1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(etqBuscarProducto1)
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
                    .addComponent(etqBuscarProducto1)
                    .addComponent(comboBoxTipoBusqueda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoTextoBuscarProducto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelScrollListProductos1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeleccionarProducto1)
                    .addComponent(btnCerrar1))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        etqBuscarProducto2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        etqBuscarProducto2.setText("Buscar ingrediente");

        campoTextoBuscarProducto2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnSeleccionarProducto2.setBackground(new java.awt.Color(205, 255, 197));
        btnSeleccionarProducto2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSeleccionarProducto2.setText("Seleccionar ingrediente");
        btnSeleccionarProducto2.setEnabled(false);
        btnSeleccionarProducto2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarProducto2ActionPerformed(evt);
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
                .addComponent(btnSeleccionarProducto)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelScrollListProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(campoTextoBuscarProducto)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(etqBuscarProducto)
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
                    .addComponent(etqBuscarProducto2)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(campoTextoBuscarProducto2)
                    .addContainerGap()))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnSeleccionarProducto2)
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
                    .addComponent(etqBuscarProducto)
                    .addComponent(comboBoxTipoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoTextoBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelScrollListProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeleccionarProducto)
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
                    .addComponent(etqBuscarProducto2)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(campoTextoBuscarProducto2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnSeleccionarProducto2)
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

    private void btnSeleccionarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarProductoActionPerformed
        seleccionarProducto();
    }//GEN-LAST:event_btnSeleccionarProductoActionPerformed

    private void comboBoxTipoBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTipoBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxTipoBusquedaActionPerformed

    private void btnCerrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrar1ActionPerformed

    private void btnSeleccionarProducto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarProducto1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSeleccionarProducto1ActionPerformed

    private void comboBoxTipoBusqueda1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTipoBusqueda1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxTipoBusqueda1ActionPerformed

    private void btnSeleccionarProducto2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarProducto2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSeleccionarProducto2ActionPerformed

    private void btnCerrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrar2ActionPerformed

    private void comboBoxTipoBusqueda2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTipoBusqueda2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxTipoBusqueda2ActionPerformed

    private void campoTextoBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTextoBuscarProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTextoBuscarProductoActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnCerrar1;
    private javax.swing.JButton btnCerrar2;
    private javax.swing.JButton btnSeleccionarProducto;
    private javax.swing.JButton btnSeleccionarProducto1;
    private javax.swing.JButton btnSeleccionarProducto2;
    private javax.swing.JTextField campoTextoBuscarProducto;
    private javax.swing.JTextField campoTextoBuscarProducto1;
    private javax.swing.JTextField campoTextoBuscarProducto2;
    private javax.swing.JComboBox<String> comboBoxTipoBusqueda;
    private javax.swing.JComboBox<String> comboBoxTipoBusqueda1;
    private javax.swing.JComboBox<String> comboBoxTipoBusqueda2;
    private javax.swing.JLabel etqBuscarProducto;
    private javax.swing.JLabel etqBuscarProducto1;
    private javax.swing.JLabel etqBuscarProducto2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JList<String> listaProductos;
    private javax.swing.JList<String> listaProductos1;
    private javax.swing.JScrollPane panelScrollListProductos;
    private javax.swing.JScrollPane panelScrollListProductos1;
    // End of variables declaration//GEN-END:variables
}
