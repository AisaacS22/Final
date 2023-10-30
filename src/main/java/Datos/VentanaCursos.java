package Datos;

import dao.CursosDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCursos extends JFrame {
    private final CursosDAO cursosDAO;

    public VentanaCursos(CursosDAO cursosDAO) {
        this.cursosDAO = cursosDAO;

        setTitle("Gestión de Cursos");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel opcionesPanel = new JPanel();
        opcionesPanel.setLayout(new GridLayout(2, 1, 10, 10));
        opcionesPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton btnAgregarCurso = new JButton("Agregar Curso");
        JButton btnEliminarCurso = new JButton("Eliminar Curso");

        btnAgregarCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCurso();
            }
        });

        btnEliminarCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCurso();
            }
        });

        opcionesPanel.add(btnAgregarCurso);
        opcionesPanel.add(btnEliminarCurso);

        add(opcionesPanel);
    }

    private void agregarCurso() {
        // Lógica para agregar un curso
        String nombreCurso = JOptionPane.showInputDialog(VentanaCursos.this, "Ingrese el nombre del curso:");
        if (nombreCurso != null && !nombreCurso.isEmpty()) {
            cursosDAO.saveOrUpdateCurso(nombreCurso);
            JOptionPane.showMessageDialog(VentanaCursos.this, "Curso '" + nombreCurso + "' agregado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(VentanaCursos.this, "Nombre de curso inválido.");
        }
    }

    private void eliminarCurso() {
        // Lógica para eliminar un curso
        String idCursoEliminar = JOptionPane.showInputDialog(VentanaCursos.this, "Ingrese el ID del curso a eliminar:");
        try {
            int id = Integer.parseInt(idCursoEliminar);
            cursosDAO.delete(id);
            JOptionPane.showMessageDialog(VentanaCursos.this, "Curso con ID " + id + " eliminado exitosamente.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(VentanaCursos.this, "ID de curso inválido. Ingrese un número.");
        }
    }

    public static void mostrarVentana(CursosDAO cursosDAO) {
        new VentanaCursos(cursosDAO).setVisible(true);
    }
}
