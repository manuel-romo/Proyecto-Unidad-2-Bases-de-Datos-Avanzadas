
package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.Usuario;
import itson.sistemarestaurantedominio.dtos.CorreoContraseniaInicioSesionDTO;
import itson.sistemarestaurantenegocio.IUsuariosBO;
import itson.sistemarestaurantenegocio.excepciones.ContraseniaIncorrectaException;
import itson.sistemarestaurantenegocio.excepciones.UsuarioInexistenteException;
import itson.sistemarestaurantenegocio.excepciones.FormatoContraseniaInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.FormatoCorreoElectronicoInvalidoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class InicioSesion extends JFrame {

    private IUsuariosBO usuariosBO;
    private Long idUsuario;
    
    private static final Logger LOG = Logger.getLogger(InicioSesion.class.getName());
    
    
    
    public InicioSesion(IUsuariosBO usuariosBO) {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        
        this.usuariosBO = usuariosBO;
    }

    private void iniciarSesion(){
        String correoElectronicoUsuario = campoTxtCorreoElectronico.getText();
        char[] contrasenia = campoTxtContrasenia.getPassword();
 
        CorreoContraseniaInicioSesionDTO correoContraseniaInicioSesionDTO = 
                new CorreoContraseniaInicioSesionDTO(correoElectronicoUsuario, contrasenia);
        
        try {
            
            Usuario usuario = usuariosBO.iniciarSesion(correoContraseniaInicioSesionDTO);
            
            idUsuario = usuario.getId();
            
            mostrarMenuPrincipal();
            
        } catch (FormatoCorreoElectronicoInvalidoException ex) {
            LOG.severe("Correo electrónico inválido. " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Correo electrónico inválido", JOptionPane.ERROR_MESSAGE);
        } catch(FormatoContraseniaInvalidoException ex){
            LOG.severe("Contraseña inválida. " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Contraseña inválida", JOptionPane.ERROR_MESSAGE);
        } catch(UsuarioInexistenteException ex){
            LOG.severe("Correo electrónico inexistente. " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Correo electrónico inexistente", JOptionPane.ERROR_MESSAGE);
        } catch(ContraseniaIncorrectaException ex){
            LOG.severe("Contraseña incorrecta. " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Contraseña incorrecta", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    private void mostrarMenuPrincipal(){
        
        try {
            
            MenuPrincipal menuPrincipal = new MenuPrincipal(usuariosBO, idUsuario);
            dispose();
            menuPrincipal.setVisible(true);
            
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
            limpiarCampos();
        }

    }
    
    private void limpiarCampos(){
        campoTxtCorreoElectronico.setText("");
        campoTxtContrasenia.setText("");
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        etqContrasenia = new javax.swing.JLabel();
        etqCorreoElectronico = new javax.swing.JLabel();
        campoTxtCorreoElectronico = new javax.swing.JTextField();
        btnIniciarSesion = new javax.swing.JButton();
        btnRegistrarUsuario = new javax.swing.JButton();
        campoTxtContrasenia = new javax.swing.JPasswordField();
        panelEncabezado = new javax.swing.JPanel();
        etqNombreUsuario = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));

        etqContrasenia.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqContrasenia.setText("Contraseña:");

        etqCorreoElectronico.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqCorreoElectronico.setText("Correo electrónico:");

        campoTxtCorreoElectronico.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        btnIniciarSesion.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnIniciarSesion.setText("Iniciar sesión");
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });

        btnRegistrarUsuario.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnRegistrarUsuario.setText("Registrar usuario");
        btnRegistrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarUsuarioActionPerformed(evt);
            }
        });

        panelEncabezado.setBackground(new java.awt.Color(255, 255, 204));

        etqNombreUsuario.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        etqNombreUsuario.setText("Nombre Usuario");

        javax.swing.GroupLayout panelEncabezadoLayout = new javax.swing.GroupLayout(panelEncabezado);
        panelEncabezado.setLayout(panelEncabezadoLayout);
        panelEncabezadoLayout.setHorizontalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(etqNombreUsuario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelEncabezadoLayout.setVerticalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(etqNombreUsuario)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 236, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnRegistrarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(394, 394, 394))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(etqCorreoElectronico)
                            .addComponent(etqContrasenia))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoTxtCorreoElectronico, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoTxtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(239, 239, 239))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnIniciarSesion)
                .addGap(418, 418, 418))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoTxtCorreoElectronico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etqCorreoElectronico))
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoTxtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etqContrasenia))
                .addGap(72, 72, 72)
                .addComponent(btnIniciarSesion)
                .addGap(26, 26, 26)
                .addComponent(btnRegistrarUsuario)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        iniciarSesion();
    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    private void btnRegistrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarUsuarioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JButton btnRegistrarUsuario;
    private javax.swing.JPasswordField campoTxtContrasenia;
    private javax.swing.JTextField campoTxtCorreoElectronico;
    private javax.swing.JLabel etqContrasenia;
    private javax.swing.JLabel etqCorreoElectronico;
    private javax.swing.JLabel etqNombreUsuario;
    private javax.swing.JPanel panelEncabezado;
    // End of variables declaration//GEN-END:variables
}
