
package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.Usuario;
import itson.sistemarestaurantenegocio.IComandasBO;
import itson.sistemarestaurantenegocio.IUsuariosBO;
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
        setLocationRelativeTo(null);
        setResizable(false);
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

        try {
            CrearComanda formularioCrearComanda = new CrearComanda(usuariosBO, comandasBO, idUsuario);
            dispose();
            formularioCrearComanda.setVisible(true);
            
        } catch (UsuarioInexistenteException ex) {
            LOG.severe("ID de usuario inexistente " + ex.getMessage());
            JOptionPane.showMessageDialog(
                    this,
                    """
                        No se pudo recuperar la información del usuario. Es posible que haya sido modificada o eliminada.
                        Por favor, intente iniciar sesión de nuevo
                    """, 
                    "Error de sesión",
                    JOptionPane.ERROR_MESSAGE);
            mostrarInicioSesion();
        }
    }
    
    private void mostrarInicioSesion(){
        InciarSesion formularioInicioSesion = new InciarSesion(usuariosBO);
        dispose();
        formularioInicioSesion.setVisible(true);  
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        panelScrollComandas = new javax.swing.JScrollPane();
        panelComandas = new javax.swing.JPanel();
        btnVolver = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        panelEncabezado7 = new javax.swing.JPanel();
        etqIcono7 = new javax.swing.JLabel();
        etqNombreUsuario14 = new javax.swing.JLabel();
        etqNombreUsuario15 = new javax.swing.JLabel();
        etqIconoUsuario7 = new javax.swing.JLabel();
        btnCerrarSesion7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

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

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Crear nueva comanda");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        panelEncabezado7.setBackground(new java.awt.Color(250, 230, 188));

        etqIcono7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoAplicacion.png"))); // NOI18N

        etqNombreUsuario14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        etqNombreUsuario14.setForeground(new java.awt.Color(0, 0, 0));
        etqNombreUsuario14.setText("Restaurante el Sahuaro");

        etqNombreUsuario15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqNombreUsuario15.setForeground(new java.awt.Color(0, 0, 0));
        etqNombreUsuario15.setText("Nombre de usuario");

        etqIconoUsuario7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconoUsuario2.png"))); // NOI18N

        btnCerrarSesion7.setBackground(new java.awt.Color(253, 244, 167));
        btnCerrarSesion7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnCerrarSesion7.setForeground(new java.awt.Color(0, 0, 0));
        btnCerrarSesion7.setText("Cerrar sesión");
        btnCerrarSesion7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesion7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelEncabezado7Layout = new javax.swing.GroupLayout(panelEncabezado7);
        panelEncabezado7.setLayout(panelEncabezado7Layout);
        panelEncabezado7Layout.setHorizontalGroup(
            panelEncabezado7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezado7Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(etqIcono7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etqNombreUsuario14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelEncabezado7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEncabezado7Layout.createSequentialGroup()
                        .addComponent(etqNombreUsuario15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(etqIconoUsuario7))
                    .addComponent(btnCerrarSesion7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        panelEncabezado7Layout.setVerticalGroup(
            panelEncabezado7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezado7Layout.createSequentialGroup()
                .addGroup(panelEncabezado7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEncabezado7Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(etqNombreUsuario14))
                    .addGroup(panelEncabezado7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(etqIcono7))
                    .addGroup(panelEncabezado7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelEncabezado7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(etqIconoUsuario7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etqNombreUsuario15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCerrarSesion7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Comandas");

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEncabezado7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelScrollComandas, javax.swing.GroupLayout.DEFAULT_SIZE, 1120, Short.MAX_VALUE)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(506, 506, 506)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(panelEncabezado7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnCerrarSesion7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesion7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarSesion7ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnCerrarSesion1;
    private javax.swing.JButton btnCerrarSesion2;
    private javax.swing.JButton btnCerrarSesion3;
    private javax.swing.JButton btnCerrarSesion4;
    private javax.swing.JButton btnCerrarSesion5;
    private javax.swing.JButton btnCerrarSesion6;
    private javax.swing.JButton btnCerrarSesion7;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel etqIcono;
    private javax.swing.JLabel etqIcono1;
    private javax.swing.JLabel etqIcono2;
    private javax.swing.JLabel etqIcono3;
    private javax.swing.JLabel etqIcono4;
    private javax.swing.JLabel etqIcono5;
    private javax.swing.JLabel etqIcono6;
    private javax.swing.JLabel etqIcono7;
    private javax.swing.JLabel etqIconoUsuario;
    private javax.swing.JLabel etqIconoUsuario1;
    private javax.swing.JLabel etqIconoUsuario2;
    private javax.swing.JLabel etqIconoUsuario3;
    private javax.swing.JLabel etqIconoUsuario4;
    private javax.swing.JLabel etqIconoUsuario5;
    private javax.swing.JLabel etqIconoUsuario6;
    private javax.swing.JLabel etqIconoUsuario7;
    private javax.swing.JLabel etqNombreUsuario;
    private javax.swing.JLabel etqNombreUsuario1;
    private javax.swing.JLabel etqNombreUsuario10;
    private javax.swing.JLabel etqNombreUsuario11;
    private javax.swing.JLabel etqNombreUsuario12;
    private javax.swing.JLabel etqNombreUsuario13;
    private javax.swing.JLabel etqNombreUsuario14;
    private javax.swing.JLabel etqNombreUsuario15;
    private javax.swing.JLabel etqNombreUsuario2;
    private javax.swing.JLabel etqNombreUsuario3;
    private javax.swing.JLabel etqNombreUsuario4;
    private javax.swing.JLabel etqNombreUsuario5;
    private javax.swing.JLabel etqNombreUsuario6;
    private javax.swing.JLabel etqNombreUsuario7;
    private javax.swing.JLabel etqNombreUsuario8;
    private javax.swing.JLabel etqNombreUsuario9;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panelComandas;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JPanel panelEncabezado1;
    private javax.swing.JPanel panelEncabezado2;
    private javax.swing.JPanel panelEncabezado3;
    private javax.swing.JPanel panelEncabezado4;
    private javax.swing.JPanel panelEncabezado5;
    private javax.swing.JPanel panelEncabezado6;
    private javax.swing.JPanel panelEncabezado7;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JScrollPane panelScrollComandas;
    // End of variables declaration//GEN-END:variables
}
