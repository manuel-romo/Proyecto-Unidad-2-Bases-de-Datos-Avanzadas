package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.Mesa;
import itson.sistemarestaurantenegocio.interfaces.IMesasBO;
import itson.sistemarestaurantepresentacion.interfaces.IMediador;
import itson.sistemarestaurantepresentacion.utils.ImagenesUtils;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SeleccionMesaComanda extends JFrame {

    private IMediador control;
    private IMesasBO mesasBO;
    
    private Color COLOR_PANEL_MESA_DISPONIBLE = new Color(245, 236, 237);
    private Color COLOR_BOTON_SELECCIONAR_MESA = new Color(255, 227, 86);
    private Font FUENTE_TEXTO_PANEL_MESA_DISPONIBLE = new Font("Segoe UI", Font.BOLD, 15);
    
    public SeleccionMesaComanda(IMediador control, IMesasBO mesasBO) {
        initComponents();
        this.setName("Selector de mesas");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        this.control = control;
        this.mesasBO = mesasBO;
        
        cargarMesasDisponibles();
    }
    
    private void cargarMesasDisponibles(){
        
        List<Mesa> mesasDisponibles = mesasBO.consultarMesasDisponibles();
        
        configurarPanelMesas();
        
        for(Mesa mesaDisponible: mesasDisponibles){
            
            panelMesas.add(crearPanelMesaDisponible(mesaDisponible));

        }
        
    }
    
    private void configurarPanelMesas(){
        
        panelMesas.removeAll();
        panelMesas.setLayout(new BoxLayout(panelMesas, BoxLayout.Y_AXIS));
        
    }
    
    private JPanel crearPanelMesaDisponible(Mesa mesaDisponible){
        
        JPanel panelMesaDisponible = new JPanel();
        
        panelMesaDisponible.setBackground(COLOR_PANEL_MESA_DISPONIBLE);
           
        panelMesaDisponible.setLayout(new GridLayout(1,2));
        
        panelMesaDisponible.add(crearPanelNumeroImagenMesa(mesaDisponible));
        
        panelMesaDisponible.add(crearPanelBtnSeleccionarMesa(mesaDisponible));
        
        return panelMesaDisponible;
        
    }
    
    private JPanel crearPanelNumeroImagenMesa(Mesa mesaDisponible){
        
        JPanel panelNumeroImagenMesa = new JPanel(new FlowLayout(FlowLayout.LEADING));
        
        JLabel etqImagenMesa = new JLabel();
        
        ImageIcon imagenMesa = ImagenesUtils.redimensionarImagen(
                new ImageIcon(getClass().getResource("/imagenMesa.png")), 
                50, 
                50);
        
        etqImagenMesa.setIcon(imagenMesa);
       
        panelNumeroImagenMesa.add(new JPanel());
        
        panelNumeroImagenMesa.add(etqImagenMesa);
        
        panelNumeroImagenMesa.add(new JPanel());
        
        
        JLabel etqNumeroMesa = new JLabel("Número: " + String.format("%02d",mesaDisponible.getNumeroMesa()));
        
        etqNumeroMesa.setFont(FUENTE_TEXTO_PANEL_MESA_DISPONIBLE);
       
        panelNumeroImagenMesa.add(etqNumeroMesa);
        
        return panelNumeroImagenMesa;
    }
    
    private JPanel crearPanelBtnSeleccionarMesa(Mesa mesaDisponible){
        
        JPanel panelNumeroMesa = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        
        panelNumeroMesa.add(crearBtnSeleccionarMesa(mesaDisponible.getId()));
        
        panelNumeroMesa.add(new JPanel());
        
        return panelNumeroMesa; 
    }
    
    private JButton crearBtnSeleccionarMesa(Long idMesaDisponible){
        
        JButton btnSeleccionarMesa = new JButton("Seleccionar");
        
        btnSeleccionarMesa.setBackground(COLOR_BOTON_SELECCIONAR_MESA);
        
        btnSeleccionarMesa.setFont(FUENTE_TEXTO_PANEL_MESA_DISPONIBLE);
        
        btnSeleccionarMesa.addActionListener(e -> seleccionarMesa(idMesaDisponible));
        
        return btnSeleccionarMesa;
    }
    
    private void seleccionarMesa(Long idMesa){
        control.mostrarCreacionComanda(this, idMesa);
    }
    
    private void mostrarComandasPrincipal(){
        control.mostrarComandasPrincipal(this);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        panelEncabezado = new javax.swing.JPanel();
        etqIcono = new javax.swing.JLabel();
        etqRestauranteSahuaro = new javax.swing.JLabel();
        etqNombreUsuario = new javax.swing.JLabel();
        etqIconoUsuario = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        panelScrollMesas = new javax.swing.JScrollPane();
        panelMesas = new javax.swing.JPanel();
        etqSeleccionarMesa = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(232, 232, 232));

        panelEncabezado.setBackground(new java.awt.Color(250, 230, 188));

        etqRestauranteSahuaro.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        etqRestauranteSahuaro.setText("Restaurante el Sahuaro");

        etqNombreUsuario.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqNombreUsuario.setText("Nombre de usuario");

        etqIconoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconoUsuario2.png"))); // NOI18N

        btnCerrarSesion.setBackground(new java.awt.Color(253, 244, 167));
        btnCerrarSesion.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnCerrarSesion.setText("Cerrar sesión");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelEncabezadoLayout = new javax.swing.GroupLayout(panelEncabezado);
        panelEncabezado.setLayout(panelEncabezadoLayout);
        panelEncabezadoLayout.setHorizontalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(etqIcono)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etqRestauranteSahuaro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 532, Short.MAX_VALUE)
                .addGroup(panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEncabezadoLayout.createSequentialGroup()
                        .addComponent(etqNombreUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(etqIconoUsuario))
                    .addComponent(btnCerrarSesion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        panelEncabezadoLayout.setVerticalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addGroup(panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEncabezadoLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(etqRestauranteSahuaro))
                    .addGroup(panelEncabezadoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(etqIcono))
                    .addGroup(panelEncabezadoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(etqIconoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etqNombreUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCerrarSesion)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelMesas.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout panelMesasLayout = new javax.swing.GroupLayout(panelMesas);
        panelMesas.setLayout(panelMesasLayout);
        panelMesasLayout.setHorizontalGroup(
            panelMesasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1438, Short.MAX_VALUE)
        );
        panelMesasLayout.setVerticalGroup(
            panelMesasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 409, Short.MAX_VALUE)
        );

        panelScrollMesas.setViewportView(panelMesas);

        etqSeleccionarMesa.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        etqSeleccionarMesa.setText("Seleccione una mesa:");

        btnVolver.setBackground(new java.awt.Color(205, 255, 197));
        btnVolver.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
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
            .addComponent(panelEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelScrollMesas, javax.swing.GroupLayout.DEFAULT_SIZE, 1120, Short.MAX_VALUE)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(etqSeleccionarMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(505, 505, 505)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(etqSeleccionarMesa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelScrollMesas, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnVolver)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        mostrarComandasPrincipal();
    }//GEN-LAST:event_btnVolverActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel etqIcono;
    private javax.swing.JLabel etqIconoUsuario;
    private javax.swing.JLabel etqNombreUsuario;
    private javax.swing.JLabel etqRestauranteSahuaro;
    private javax.swing.JLabel etqSeleccionarMesa;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JPanel panelMesas;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JScrollPane panelScrollMesas;
    // End of variables declaration//GEN-END:variables
}
