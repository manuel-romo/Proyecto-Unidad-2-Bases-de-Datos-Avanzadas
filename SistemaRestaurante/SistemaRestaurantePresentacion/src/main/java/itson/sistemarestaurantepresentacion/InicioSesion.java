package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.Usuario;
import itson.sistemarestaurantedominio.dtos.CorreoContraseniaInicioSesionDTO;
import itson.sistemarestaurantenegocio.interfaces.IUsuariosBO;
import itson.sistemarestaurantenegocio.excepciones.ContraseniaIncorrectaException;
import itson.sistemarestaurantenegocio.excepciones.UsuarioInexistenteException;
import itson.sistemarestaurantenegocio.excepciones.FormatoContraseniaInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.FormatoCorreoElectronicoInvalidoException;
import itson.sistemarestaurantepresentacion.interfaces.IMediador;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class InicioSesion extends JFrame {

    private IMediador control;
    private IUsuariosBO usuariosBO;
    
    private DatosEncabezado datosEncabezado;
    
    
    private static final Logger LOG = Logger.getLogger(InicioSesion.class.getName());
    
    public InicioSesion(IMediador control, IUsuariosBO usuariosBO) {
        datosEncabezado = DatosEncabezado.getInstance();
        initComponents();
        this.setName("Inicio de sesión");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.control = control;
        this.usuariosBO = usuariosBO;
        
    }
   
    
    private void iniciarSesion(){
        String correoElectronicoUsuario = campoTxtCorreoElectronico.getText();
        char[] contrasenia = campoTxtContrasenia.getPassword();
 
        CorreoContraseniaInicioSesionDTO correoContraseniaInicioSesionDTO = 
                new CorreoContraseniaInicioSesionDTO(correoElectronicoUsuario, contrasenia);
        
        try {
            
            Usuario usuario = usuariosBO.iniciarSesion(correoContraseniaInicioSesionDTO);
            
            Long idUsuario = usuario.getId();
            
            // Se crea la sesión del usuario que inicio sesión.
            SesionUsuario.crearInstancia(idUsuario);
            
            datosEncabezado.setNombresUsuario(usuario.getNombres());
            datosEncabezado.setApellidoPaternoUsuario(usuario.getApellidoPaterno());
            // Imagen de perfil de usuario simulada.
            datosEncabezado.setDireccionImagenUsuario("/imagenIconoUsuario.png");
            
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
        control.mostrarMenuPrincipal(this);
    }
    
    private void limpiarCampos(){
        campoTxtCorreoElectronico.setText("");
        campoTxtContrasenia.setText("");
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        panelDatos = new javax.swing.JPanel();
        etqCorreoElectronico = new javax.swing.JLabel();
        campoTxtCorreoElectronico = new javax.swing.JTextField();
        etqContrasenia = new javax.swing.JLabel();
        campoTxtContrasenia = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton();
        etqInicioSesion = new javax.swing.JLabel();
        etqNoTienesCuenta = new javax.swing.JLabel();
        btnRegistrarUsuario1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        etqNombreRestaurante = new javax.swing.JLabel();
        etqLogotipoRestaurante = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));

        panelPrincipal.setBackground(new java.awt.Color(232, 232, 232));

        panelDatos.setBackground(new java.awt.Color(249, 250, 214));

        etqCorreoElectronico.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqCorreoElectronico.setText("Correo electrónico:");

        campoTxtCorreoElectronico.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        etqContrasenia.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqContrasenia.setText("Contraseña:");

        btnIngresar.setBackground(new java.awt.Color(205, 255, 197));
        btnIngresar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
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
                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(252, 252, 252))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(btnIngresar)
                .addGap(36, 36, 36))
        );

        etqInicioSesion.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        etqInicioSesion.setText("Inicio de sesión");

        etqNoTienesCuenta.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqNoTienesCuenta.setText("¿No tienes cuenta?");

        btnRegistrarUsuario1.setBackground(new java.awt.Color(253, 244, 167));
        btnRegistrarUsuario1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnRegistrarUsuario1.setText("Regístrate");
        btnRegistrarUsuario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarUsuario1ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(250, 230, 188));

        etqNombreRestaurante.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        etqNombreRestaurante.setText(datosEncabezado.getNombreRestaurante());

        etqLogotipoRestaurante.setIcon(new javax.swing.ImageIcon(getClass().getResource(datosEncabezado.getDireccionLogotipoRestaurante())));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(331, 331, 331)
                .addComponent(etqLogotipoRestaurante, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etqNombreRestaurante)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(etqNombreRestaurante)
                .addGap(32, 32, 32))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etqLogotipoRestaurante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

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
                .addContainerGap(178, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(etqInicioSesion)
                .addGap(27, 27, 27)
                .addComponent(panelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etqNoTienesCuenta)
                    .addComponent(btnRegistrarUsuario1))
                .addGap(34, 34, 34))
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

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        // TODO CORREGIR
        // iniciarSesion();
        SesionUsuario.crearInstancia(2L);

        datosEncabezado.setNombresUsuario("Maria");
        datosEncabezado.setApellidoPaternoUsuario("dfsfs");
        // Imagen de perfil de usuario simulada.
        datosEncabezado.setDireccionImagenUsuario("/imagenIconoUsuario.png");
        
        mostrarMenuPrincipal();
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void btnRegistrarUsuario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarUsuario1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarUsuario1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnRegistrarUsuario1;
    private javax.swing.JPasswordField campoTxtContrasenia;
    private javax.swing.JTextField campoTxtCorreoElectronico;
    private javax.swing.JLabel etqContrasenia;
    private javax.swing.JLabel etqCorreoElectronico;
    private javax.swing.JLabel etqInicioSesion;
    private javax.swing.JLabel etqLogotipoRestaurante;
    private javax.swing.JLabel etqNoTienesCuenta;
    private javax.swing.JLabel etqNombreRestaurante;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
