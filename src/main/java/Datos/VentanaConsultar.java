package Datos;

import dao.CursosDAO;
import dao.InscripcionesDAO;
import isa.ejercicio.CursosClass;
import isa.ejercicio.EstudiantesClass;
import isa.ejercicio.InscripcionesClass;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaConsultar extends JFrame {

    private final InscripcionesDAO inscripcionesDAO;
    private final CursosDAO cursosDAO;

    public VentanaConsultar(InscripcionesDAO inscripcionesDAO, CursosDAO cursosDAO) {
        this.inscripcionesDAO = inscripcionesDAO;
        this.cursosDAO = cursosDAO;

        setTitle("Consultar Datos");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Crear panel de consulta
        JPanel consultarPanel = new JPanel();
        consultarPanel.setLayout(new GridLayout(2, 2, 10, 10));
        consultarPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Componentes para la consulta
        JButton btnConsultarCursos = new JButton("Consultar Cursos");
        JButton btnConsultarEstudiantes = new JButton("Consultar Estudiantes");

        // Añadir ActionListener para el botón de consulta de cursos
        btnConsultarCursos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la lista de cursos utilizando el DAO
                List<CursosClass> cursos = cursosDAO.getAllCursos();

                // Mostrar los resultados en una ventana o área de texto
                mostrarCursos(cursos);
            }
        });

        // Añadir ActionListener para el botón de consulta de estudiantes
        btnConsultarEstudiantes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la lista de estudiantes utilizando el DAO
                List<InscripcionesClass> inscripciones = inscripcionesDAO.getAllInscripciones();

                // Mostrar los resultados en una ventana o área de texto
                mostrarEstudiantesInscritos(inscripciones);
            }
        });

        // Añadir componentes al panel de consulta
        consultarPanel.add(btnConsultarCursos);
        consultarPanel.add(btnConsultarEstudiantes);

        add(consultarPanel);
    }

    // Método para mostrar los cursos consultados
    private void mostrarCursos(List<CursosClass> cursos) {
        StringBuilder mensaje = new StringBuilder("Cursos disponibles:\n");
        for (CursosClass curso : cursos) {
            mensaje.append("ID: ").append(curso.getIdCurso())
                    .append(", Nombre: ").append(curso.getNombreCurso()).append("\n");
        }
        JOptionPane.showMessageDialog(this, mensaje.toString());
    }

    // Método para mostrar los estudiantes consultados
    private void mostrarEstudiantesInscritos(List<InscripcionesClass> inscripciones) {
        StringBuilder mensaje = new StringBuilder("Estudiantes inscritos:\n");
        for (InscripcionesClass inscripcion : inscripciones) {
            EstudiantesClass estudiante = inscripcion.getEstudiantesByIdEstudiante();
            if (estudiante != null) {
                mensaje.append("Estudiante: ").append(estudiante.getNombreCompleto())
                        .append(", Curso: ").append(inscripcion.getCursosByIdCurso().getNombreCurso())
                        .append(", Fecha Inscripción: ").append(inscripcion.getFechaInscripcion()).append("\n");
            } else {
                mensaje.append("Estudiante desconocido, Curso: ").append(inscripcion.getCursosByIdCurso().getNombreCurso())
                        .append(", Fecha Inscripción: ").append(inscripcion.getFechaInscripcion()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(this, mensaje.toString());
    }


}
