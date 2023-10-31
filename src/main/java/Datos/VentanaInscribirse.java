package Datos;

import dao.CursosDAO;
import dao.InscripcionesDAO;
import dao.EstudiantesDAO;
import isa.ejercicio.InscripcionesClass;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaInscribirse extends JFrame {

    private final CursosDAO cursosDAO;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JComboBox<String> cmbCursos;
    private InscripcionesDAO inscripcionesDAO;
    private EstudiantesDAO estudiantesDAO;

    private String usuario;

    private String[] cursosDisponibles = {"Matemáticas", "Física", "Programación"};

    // Constructor modificado para recibir EstudiantesDAO como parámetro
    public VentanaInscribirse(String usuario, InscripcionesDAO inscripcionesDAO, CursosDAO cursosDAO, EstudiantesDAO estudiantesDAO) {
        this.usuario = usuario;
        this.inscripcionesDAO = inscripcionesDAO;
        this.cursosDAO = cursosDAO;
        this.estudiantesDAO = estudiantesDAO; // Inicializar estudiantesDAO

        setTitle("Inscripciones");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel inscripcionPanel = new JPanel();
        inscripcionPanel.setLayout(new GridLayout(4, 2, 10, 10));
        inscripcionPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();

        JLabel lblApellido = new JLabel("Apellido:");
        txtApellido = new JTextField();

        JLabel lblCurso = new JLabel("Curso:");
        cmbCursos = new JComboBox<>(cursosDisponibles);

        JButton btnInscribirse = new JButton("Inscribirse");

        btnInscribirse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cursoSeleccionado = (String) cmbCursos.getSelectedItem();

                if (cursoSeleccionado.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un curso", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Crear una instancia de InscripcionesClass con la información
                    InscripcionesClass nuevaInscripcion = new InscripcionesClass(usuario, cursoSeleccionado);

                    // Guardar la inscripción en la base de datos utilizando el DAO
                    inscripcionesDAO.saveOrUpdate(nuevaInscripcion);

                    JOptionPane.showMessageDialog(null, "Inscripción exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                    limpiarCampos();
                }
            }
        });


        inscripcionPanel.add(lblNombre);
        inscripcionPanel.add(txtNombre);
        inscripcionPanel.add(lblApellido);
        inscripcionPanel.add(txtApellido);
        inscripcionPanel.add(lblCurso);
        inscripcionPanel.add(cmbCursos);
        inscripcionPanel.add(new JLabel());
        inscripcionPanel.add(btnInscribirse);

        add(inscripcionPanel);
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        cmbCursos.setSelectedIndex(0);
    }
}
