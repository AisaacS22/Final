package Datos;

import dao.CursosDAO;
import dao.EstudiantesDAO;
import dao.InscripcionesDAO;
import isa.ejercicio.EstudiantesClass;
import service.InscripcionService;

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
    private InscripcionService inscripcionService; // Agregamos el servicio

    private String usuario;

    private String[] cursosDisponibles = {"Matemáticas", "Física", "Programación"};

    public VentanaInscribirse(String usuario, InscripcionesDAO inscripcionesDAO, CursosDAO cursosDAO, EstudiantesDAO estudiantesDAO) {
        this.usuario = usuario;
        this.inscripcionesDAO = inscripcionesDAO;
        this.cursosDAO = cursosDAO;
        this.estudiantesDAO = estudiantesDAO;
        this.inscripcionService = new InscripcionService(inscripcionesDAO, estudiantesDAO, cursosDAO);

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
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String cursoSeleccionado = (String) cmbCursos.getSelectedItem();

                if (nombre.isEmpty() || apellido.isEmpty() || cursoSeleccionado.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Verificar si el estudiante ya existe en la base de datos
                    EstudiantesClass estudiante = estudiantesDAO.getByNombreApellido(nombre, apellido);

                    if (estudiante == null) {
                        // Si el estudiante no existe, crear uno nuevo
                        estudiante = new EstudiantesClass();
                        estudiante.setNombre(nombre);
                        estudiante.setApellido(apellido);

                        // Guardar el nuevo estudiante en la base de datos
                        estudiantesDAO.saveOrUpdate(estudiante);
                    }

                    // Llamar al método del servicio para inscribir al estudiante
                    inscripcionService.inscribirEstudiante(estudiante, cursoSeleccionado);

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
