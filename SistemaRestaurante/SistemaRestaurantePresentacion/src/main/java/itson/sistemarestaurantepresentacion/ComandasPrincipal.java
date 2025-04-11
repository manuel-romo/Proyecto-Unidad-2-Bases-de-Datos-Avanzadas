
package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.Usuario;
import itson.sistemarestaurantenegocio.interfaces.IComandasBO;
import itson.sistemarestaurantenegocio.interfaces.IUsuariosBO;
import itson.sistemarestaurantenegocio.excepciones.UsuarioInexistenteException;
import itson.sistemarestaurantenegocio.fabrica.FabricaObjetoNegocio;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class ComandasPrincipal extends JFrame {

    private IUsuariosBO usuariosBO;
    private IComandasBO comandasBO;
    private Long idUsuario;
    
    private static final Logger LOG = Logger.getLogger(ComandasPrincipal.class.getName());
    
    public ComandasPrincipal(IUsuariosBO usuariosBO, IComandasBO comandasBO, Long idUsuario) throws UsuarioInexistenteException {
        initComponents();
        this.setName("Selector de comandas");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.usuariosBO = usuariosBO;
        this.comandasBO = comandasBO;
        this.idUsuario = idUsuario;
        
        cargarNombreUsuarioEncabezado();
    }
    
    private void cargarNombreUsuarioEncabezado() throws UsuarioInexistenteException{
        
        Usuario usuario = usuariosBO.consultarUsuarioId(idUsuario);

        idUsuario = usuario.getId();

        String nombresUsuario = usuario.getNombres();
        String apellidoPaternoUsuario = usuario.getApellidoPaterno();
        String apellidoMaternoUsuario = usuario.getApellidoMaterno();

        etqNombreUsuario.setText(apellidoPaternoUsuario + " " + apellidoMaternoUsuario + ", " + nombresUsuario);
              
    }
    
    private void llenarTablaComandas(){
        
        JPanel panelComanad  = new JPanel();
    }
    
    private void crearComanda(){

    }
    
    private void mostrarInicioSesion(){
//        IniciarSesion formularioInicioSesion = new IniciarSesion(usuariosBO);
//        dispose();
//        formularioInicioSesion.setVisible(true);  
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        panelScrollComandas = new javax.swing.JScrollPane();
        panelComandas = new javax.swing.JPanel();
        btnVolver = new javax.swing.JButton();
        btnCrearComanda = new javax.swing.JButton();
        panelEncabezado = new javax.swing.JPanel();
        etqIcono = new javax.swing.JLabel();
        etqRestauranteSahuaro = new javax.swing.JLabel();
        etqNombreUsuario = new javax.swing.JLabel();
        etqIconoUsuario = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        etqComandas = new javax.swing.JLabel();

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
        btnVolver.setForeground(new java.awt.Color(0, 0, 0));
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnCrearComanda.setBackground(new java.awt.Color(255, 255, 255));
        btnCrearComanda.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCrearComanda.setForeground(new java.awt.Color(0, 0, 0));
        btnCrearComanda.setText("Crear nueva comanda");
        btnCrearComanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearComandaActionPerformed(evt);
            }
        });

        panelEncabezado.setBackground(new java.awt.Color(250, 230, 188));

        etqIcono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoAplicacion.png"))); // NOI18N

        etqRestauranteSahuaro.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        etqRestauranteSahuaro.setForeground(new java.awt.Color(0, 0, 0));
        etqRestauranteSahuaro.setText("Restaurante el Sahuaro");

        etqNombreUsuario.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqNombreUsuario.setForeground(new java.awt.Color(0, 0, 0));
        etqNombreUsuario.setText("Nombre de usuario");

        etqIconoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconoUsuario2.png"))); // NOI18N

        btnCerrarSesion.setBackground(new java.awt.Color(253, 244, 167));
        btnCerrarSesion.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(0, 0, 0));
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        etqComandas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        etqComandas.setForeground(new java.awt.Color(0, 0, 0));
        etqComandas.setText("Comandas");

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelScrollComandas, javax.swing.GroupLayout.DEFAULT_SIZE, 1120, Short.MAX_VALUE)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(etqComandas, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCrearComanda)))
                .addContainerGap())
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(506, 506, 506)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnCrearComandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearComandaActionPerformed
        
    }//GEN-LAST:event_btnCrearComandaActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnCrearComanda;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel etqComandas;
    private javax.swing.JLabel etqIcono;
    private javax.swing.JLabel etqIconoUsuario;
    private javax.swing.JLabel etqNombreUsuario;
    private javax.swing.JLabel etqRestauranteSahuaro;
    private javax.swing.JPanel panelComandas;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JScrollPane panelScrollComandas;
    // End of variables declaration//GEN-END:variables
}
