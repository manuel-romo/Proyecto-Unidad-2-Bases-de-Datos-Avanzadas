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


public class InciarSesion extends JFrame {

    private IUsuariosBO usuariosBO;
    private Long idUsuario;
    
    private static final Logger LOG = Logger.getLogger(InciarSesion.class.getName());
    
    
    
    public InciarSesion(IUsuariosBO usuariosBO) {
        initComponents();
        this.setName("Inicio de sesión");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
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

        panelPrincipal = new javax.swing.JPanel();
        panelEncabezado = new javax.swing.JPanel();
        etqRestaruranteSahuaro = new javax.swing.JLabel();
        etqIcono = new javax.swing.JLabel();
        panelDatos = new javax.swing.JPanel();
        etqCorreoElectronico = new javax.swing.JLabel();
        campoTxtCorreoElectronico = new javax.swing.JTextField();
        etqContrasenia = new javax.swing.JLabel();
        campoTxtContrasenia = new javax.swing.JPasswordField();
        btnVolver = new javax.swing.JButton();
        btnIngresar = new javax.swing.JButton();
        etqInicioSesion = new javax.swing.JLabel();
        etqNoTienesCuenta = new javax.swing.JLabel();
        btnRegistrarUsuario1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));

        panelPrincipal.setBackground(new java.awt.Color(232, 232, 232));

        panelEncabezado.setBackground(new java.awt.Color(250, 230, 188));

        etqRestaruranteSahuaro.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        etqRestaruranteSahuaro.setForeground(new java.awt.Color(0, 0, 0));
        etqRestaruranteSahuaro.setText("Restaurante el Sahuaro");

        etqIcono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoAplicacion.png"))); // NOI18N

        javax.swing.GroupLayout panelEncabezadoLayout = new javax.swing.GroupLayout(panelEncabezado);
        panelEncabezado.setLayout(panelEncabezadoLayout);
        panelEncabezadoLayout.setHorizontalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap(277, Short.MAX_VALUE)
                .addComponent(etqIcono)
                .addGap(18, 18, 18)
                .addComponent(etqRestaruranteSahuaro)
                .addGap(277, 277, 277))
        );
        panelEncabezadoLayout.setVerticalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addGroup(panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEncabezadoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(etqIcono))
                    .addGroup(panelEncabezadoLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(etqRestaruranteSahuaro)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelDatos.setBackground(new java.awt.Color(249, 250, 214));

        etqCorreoElectronico.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqCorreoElectronico.setForeground(new java.awt.Color(0, 0, 0));
        etqCorreoElectronico.setText("Correo electrónico:");

        campoTxtCorreoElectronico.setBackground(new java.awt.Color(255, 255, 255));
        campoTxtCorreoElectronico.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        etqContrasenia.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqContrasenia.setForeground(new java.awt.Color(0, 0, 0));
        etqContrasenia.setText("Contraseña:");

        campoTxtContrasenia.setBackground(new java.awt.Color(255, 255, 255));

        btnVolver.setBackground(new java.awt.Color(205, 255, 197));
        btnVolver.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(0, 0, 0));
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnIngresar.setBackground(new java.awt.Color(205, 255, 197));
        btnIngresar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnIngresar.setForeground(new java.awt.Color(0, 0, 0));
        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDatosLayout = new javax.swing.GroupLayout(panelDatos);
        panelDatos.setLayout(panelDatosLayout);
        panelDatosLayout.setHorizontalGroup(
            panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(etqCorreoElectronico)
                    .addComponent(etqContrasenia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoTxtCorreoElectronico, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTxtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(174, 174, 174))
        );
        panelDatosLayout.setVerticalGroup(
            panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoTxtCorreoElectronico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etqCorreoElectronico))
                .addGap(57, 57, 57)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoTxtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etqContrasenia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolver)
                    .addComponent(btnIngresar))
                .addGap(37, 37, 37))
        );

        etqInicioSesion.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        etqInicioSesion.setForeground(new java.awt.Color(0, 0, 0));
        etqInicioSesion.setText("Inicio de sesión");

        etqNoTienesCuenta.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqNoTienesCuenta.setForeground(new java.awt.Color(0, 0, 0));
        etqNoTienesCuenta.setText("¿No tienes cuenta?");

        btnRegistrarUsuario1.setBackground(new java.awt.Color(253, 244, 167));
        btnRegistrarUsuario1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnRegistrarUsuario1.setForeground(new java.awt.Color(0, 0, 0));
        btnRegistrarUsuario1.setText("Regístrate");
        btnRegistrarUsuario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarUsuario1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(292, 292, 292)
                        .addComponent(etqNoTienesCuenta)
                        .addGap(37, 37, 37)
                        .addComponent(btnRegistrarUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(370, 370, 370)
                        .addComponent(etqInicioSesion))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(panelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(146, Short.MAX_VALUE))
            .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addContainerGap(126, Short.MAX_VALUE)
                .addComponent(etqInicioSesion)
                .addGap(27, 27, 27)
                .addComponent(panelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etqNoTienesCuenta)
                    .addComponent(btnRegistrarUsuario1))
                .addGap(34, 34, 34))
            .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelPrincipalLayout.createSequentialGroup()
                    .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 455, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        iniciarSesion();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void btnRegistrarUsuario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarUsuario1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarUsuario1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnRegistrarUsuario1;
    private javax.swing.JButton btnVolver;
    private javax.swing.JPasswordField campoTxtContrasenia;
    private javax.swing.JTextField campoTxtCorreoElectronico;
    private javax.swing.JLabel etqContrasenia;
    private javax.swing.JLabel etqCorreoElectronico;
    private javax.swing.JLabel etqIcono;
    private javax.swing.JLabel etqInicioSesion;
    private javax.swing.JLabel etqNoTienesCuenta;
    private javax.swing.JLabel etqRestaruranteSahuaro;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
