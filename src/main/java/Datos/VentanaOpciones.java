package Datos;

import dao.CursosDAO;
import dao.EstudiantesDAO;
import dao.InscripcionesDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaOpciones extends JFrame {

    private final String usuario;
    private final InscripcionesDAO inscripcionesDAO;
    private final CursosDAO cursosDAO;
    private final EstudiantesDAO estudiantesDAO;

    public VentanaOpciones(String usuario, InscripcionesDAO inscripcionesDAO) {
        this.usuario = usuario;
        this.inscripcionesDAO = inscripcionesDAO;
        this.cursosDAO = new CursosDAO(HibernateUtil.getSessionFactory());
        this.estudiantesDAO = new EstudiantesDAO(HibernateUtil.getSessionFactory());

        setTitle("Opciones");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);


        // Crear panel de opciones
        JPanel opcionesPanel = new JPanel();
        opcionesPanel.setLayout(new GridLayout(3, 1, 10, 10));
        opcionesPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Botones para las opciones
        JButton btnInscripciones = new JButton("Inscripciones");
        JButton btnConsultas = new JButton("Consultas");
        JButton btnCursos = new JButton("Cursos");  // Cambiado el nombre del botón

        // Añadir ActionListener para el botón de Inscripciones
        btnInscripciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir la ventana de inscripciones con el usuario proporcionado y los DAOs
                VentanaInscribirse inscripcionFrame = new VentanaInscribirse(usuario, inscripcionesDAO, cursosDAO, estudiantesDAO);
                inscripcionFrame.setVisible(true);
            }
        });


        // Añadir ActionListener para el botón de Consultas
        btnConsultas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir la ventana de consultas con los DAOs de inscripciones y cursos
                VentanaConsultar consultaFrame = new VentanaConsultar(inscripcionesDAO, cursosDAO);
                consultaFrame.setVisible(true);
            }
        });


        // Añadir ActionListener para el botón de Cursos
        btnCursos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir la ventana de cursos con el DAO de cursos
                VentanaCursos.mostrarVentana(cursosDAO);
            }
        });

        // Añadir componentes al panel de opciones
        opcionesPanel.add(btnInscripciones);
        opcionesPanel.add(btnConsultas);
        opcionesPanel.add(btnCursos);  // Agregado el botón de cursos

        add(opcionesPanel);
    }

    public static void mostrarVentana(String usuario, InscripcionesDAO inscripcionesDAO) {
        new VentanaOpciones(usuario, inscripcionesDAO).setVisible(true);
    }
}
