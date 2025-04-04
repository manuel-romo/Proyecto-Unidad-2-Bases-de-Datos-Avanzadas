
package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.Usuario;
import itson.sistemarestaurantenegocio.IIngredientesBO;
import itson.sistemarestaurantenegocio.IUsuariosBO;
import itson.sistemarestaurantenegocio.excepciones.UsuarioInexistenteException;
import itson.sistemarestaurantepresentacion.RegistroIngrediente;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class IngredientesPrincipal extends JFrame{

    private IUsuariosBO usuariosBO;
    private IIngredientesBO ingredientesBO;
    private Long idUsuario;
    
    private static final Logger LOG = Logger.getLogger(IngredientesPrincipal.class.getName());

    
    
    public IngredientesPrincipal(IUsuariosBO usuariosBO, IIngredientesBO ingredientesBO, Long idUusario) throws UsuarioInexistenteException {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        this.usuariosBO = usuariosBO;
        this.ingredientesBO = ingredientesBO;
        this.idUsuario = idUusario;
        
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
    
    private void mostrarRegistrarIngrediente(){
        try {
            RegistroIngrediente formularioRegistrarIngrediente = new RegistroIngrediente(usuariosBO, ingredientesBO, idUsuario);
            dispose();
            formularioRegistrarIngrediente.setVisible(true);
            
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
        InicioSesion formularioInicioSesion = new InicioSesion(usuariosBO);
        dispose();
        formularioInicioSesion.setVisible(true);  
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelEncabezado = new javax.swing.JPanel();
        etqNombreUsuario = new javax.swing.JLabel();
        btnRegistrarIngrediente = new javax.swing.JButton();
        panelScrollIngredientes = new javax.swing.JScrollPane();
        panelIngredientes = new javax.swing.JPanel();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelEncabezado.setBackground(new java.awt.Color(255, 255, 204));

        etqNombreUsuario.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqNombreUsuario.setText("Nombre de Usuario");

        javax.swing.GroupLayout panelEncabezadoLayout = new javax.swing.GroupLayout(panelEncabezado);
        panelEncabezado.setLayout(panelEncabezadoLayout);
        panelEncabezadoLayout.setHorizontalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(etqNombreUsuario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelEncabezadoLayout.setVerticalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(etqNombreUsuario)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        btnRegistrarIngrediente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnRegistrarIngrediente.setText("Registrar ingrediente");
        btnRegistrarIngrediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarIngredienteActionPerformed(evt);
            }
        });

        panelIngredientes.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout panelIngredientesLayout = new javax.swing.GroupLayout(panelIngredientes);
        panelIngredientes.setLayout(panelIngredientesLayout);
        panelIngredientesLayout.setHorizontalGroup(
            panelIngredientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 986, Short.MAX_VALUE)
        );
        panelIngredientesLayout.setVerticalGroup(
            panelIngredientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 364, Short.MAX_VALUE)
        );

        panelScrollIngredientes.setViewportView(panelIngredientes);

        btnVolver.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVolver.setText("Volver");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnRegistrarIngrediente)
                .addGap(32, 32, 32))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelScrollIngredientes)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(450, 450, 450)
                .addComponent(btnVolver)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegistrarIngrediente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(panelScrollIngredientes, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVolver)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarIngredienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarIngredienteActionPerformed
        mostrarRegistrarIngrediente();
    }//GEN-LAST:event_btnRegistrarIngredienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrarIngrediente;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel etqNombreUsuario;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JPanel panelIngredientes;
    private javax.swing.JScrollPane panelScrollIngredientes;
    // End of variables declaration//GEN-END:variables
}
