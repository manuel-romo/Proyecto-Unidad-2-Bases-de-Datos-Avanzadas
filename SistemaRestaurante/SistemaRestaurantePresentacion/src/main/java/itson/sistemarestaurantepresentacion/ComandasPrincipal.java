
package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.Usuario;
import itson.sistemarestaurantenegocio.interfaces.IComandasBO;
import itson.sistemarestaurantenegocio.interfaces.IUsuariosBO;
import itson.sistemarestaurantenegocio.excepciones.UsuarioInexistenteException;
import itson.sistemarestaurantenegocio.fabrica.FabricaObjetoNegocio;
import itson.sistemarestaurantepresentacion.interfaces.IMediador;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class ComandasPrincipal extends JFrame {

    private IMediador control;
    private IComandasBO comandasBO;
    
    private static final Logger LOG = Logger.getLogger(ComandasPrincipal.class.getName());
    
    public ComandasPrincipal(IMediador control, IComandasBO comandasBO){
        initComponents();
        this.setName("Selector de comandas");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        this.control = control;
        this.comandasBO = comandasBO;
        
        panelBaseEncabezado.add(new Encabezado());
        
    }
    
    private void cargarComandas(){
        
    }
    
    private void llenarTablaComandas(){
        
        JPanel panelComanad  = new JPanel();
    }
    
    private void mostrarSeleccionMesaComanda(){
        control.mostrarSeleccionMesaComanda(this);
    }
    
    private void mostrarMenuPrincipal(){
        control.mostrarMenuPrincipal(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        panelScrollComandas = new javax.swing.JScrollPane();
        panelComandas = new javax.swing.JPanel();
        btnVolver = new javax.swing.JButton();
        btnCrearComanda = new javax.swing.JButton();
        etqComandas = new javax.swing.JLabel();
        panelBaseEncabezado = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(232, 232, 232));
        panelPrincipal.setForeground(new java.awt.Color(232, 232, 232));

        panelComandas.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout panelComandasLayout = new javax.swing.GroupLayout(panelComandas);
        panelComandas.setLayout(panelComandasLayout);
        panelComandasLayout.setHorizontalGroup(
            panelComandasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1438, Short.MAX_VALUE)
        );
        panelComandasLayout.setVerticalGroup(
            panelComandasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 409, Short.MAX_VALUE)
        );

        panelScrollComandas.setViewportView(panelComandas);

        btnVolver.setBackground(new java.awt.Color(205, 255, 197));
        btnVolver.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnCrearComanda.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCrearComanda.setText("Crear nueva comanda");
        btnCrearComanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearComandaActionPerformed(evt);
            }
        });

        etqComandas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        etqComandas.setText("Comandas");

        javax.swing.GroupLayout panelBaseEncabezadoLayout = new javax.swing.GroupLayout(panelBaseEncabezado);
        panelBaseEncabezado.setLayout(panelBaseEncabezadoLayout);
        panelBaseEncabezadoLayout.setHorizontalGroup(
            panelBaseEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelBaseEncabezadoLayout.setVerticalGroup(
            panelBaseEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 73, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelScrollComandas, javax.swing.GroupLayout.DEFAULT_SIZE, 1120, Short.MAX_VALUE)
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addComponent(etqComandas, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCrearComanda))))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(506, 506, 506)
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(panelBaseEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(panelBaseEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrearComanda)
                    .addComponent(etqComandas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelScrollComandas, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVolver)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        mostrarMenuPrincipal();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnCrearComandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearComandaActionPerformed
        mostrarSeleccionMesaComanda();
    }//GEN-LAST:event_btnCrearComandaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrearComanda;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel etqComandas;
    private javax.swing.JPanel panelBaseEncabezado;
    private javax.swing.JPanel panelComandas;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JScrollPane panelScrollComandas;
    // End of variables declaration//GEN-END:variables
}
