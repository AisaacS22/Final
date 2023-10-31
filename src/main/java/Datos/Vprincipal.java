package Datos;

import dao.InscripcionesDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vprincipal extends JFrame {
    private static final String USUARIO_CORRECTO = "usuario";
    private static final String CONTRASENA_CORRECTA = "123";

    public Vprincipal() {
        setTitle("Login");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\50242\\IdeaProjects\\Registro\\src\\main\\java\\Imagen\\imagen l.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        setContentPane(backgroundLabel);

        JButton btlogin = new JButton("Login");
        JTextField txtUsername = new JTextField();
        JPasswordField txtPassword = new JPasswordField();

        setLayout(null);

        btlogin.setBounds(120, 480, 100, 30);
        txtUsername.setBounds(5, 300, 260, 30);
        txtPassword.setBounds(5, 400, 260, 30);

        add(txtUsername);
        add(txtPassword);
        add(btlogin);

        btlogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsername.getText();
                String contrasena = new String(txtPassword.getPassword());

                if (autenticarUsuario(usuario, contrasena)) {
                    dispose();
                    InscripcionesDAO inscripcionesDAO = new InscripcionesDAO();

                    VentanaOpciones.mostrarVentana(usuario, inscripcionesDAO);
                } else {
                    JOptionPane.showMessageDialog(Vprincipal.this, "Usuario o contraseña incorrectos");
                }
            }
        });
    }

    private boolean autenticarUsuario(String usuario, String contrasena) {
        return usuario.equals(USUARIO_CORRECTO) && contrasena.equals(CONTRASENA_CORRECTA);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Vprincipal().setVisible(true);
            }
        });
    }
}
