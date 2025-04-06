
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

        jPanel1 = new javax.swing.JPanel();
        etqNombreUsuario = new javax.swing.JLabel();
        panelScrollComandas = new javax.swing.JScrollPane();
        panelComandas = new javax.swing.JPanel();
        btnVolver = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        etqNombreUsuario.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        etqNombreUsuario.setText("Nombre Usuario");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(etqNombreUsuario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(etqNombreUsuario)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        panelComandas.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout panelComandasLayout = new javax.swing.GroupLayout(panelComandas);
        panelComandas.setLayout(panelComandasLayout);
        panelComandasLayout.setHorizontalGroup(
            panelComandasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 998, Short.MAX_VALUE)
        );
        panelComandasLayout.setVerticalGroup(
            panelComandasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );

        panelScrollComandas.setViewportView(panelComandas);

        btnVolver.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("Crear nueva comanda");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelScrollComandas)
            .addGroup(layout.createSequentialGroup()
                .addGap(455, 455, 455)
                .addComponent(btnVolver)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelScrollComandas, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVolver)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVolverActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel etqNombreUsuario;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelComandas;
    private javax.swing.JScrollPane panelScrollComandas;
    // End of variables declaration//GEN-END:variables
}
