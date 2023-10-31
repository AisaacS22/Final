package Datos;

import dao.InscripcionesDAO;
import isa.ejercicio.InscripcionesClass;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaConsultar extends JFrame {

    private final InscripcionesDAO inscripcionesDAO; // Nuevo campo DAO

    public VentanaConsultar(InscripcionesDAO inscripcionesDAO) {
        this.inscripcionesDAO = inscripcionesDAO;

        setTitle("Consultar Datos");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo la ventana actual
        setResizable(false);
        setLocationRelativeTo(null);

        // Crear panel de consulta
        JPanel consultarPanel = new JPanel();
        consultarPanel.setLayout(new GridLayout(3, 2, 10, 10));
        consultarPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Componentes para la consulta
        JLabel lblCampo = new JLabel("Campo:");
        JTextField txtCampo = new JTextField();

        JLabel lblValor = new JLabel("Valor:");
        JTextField txtValor = new JTextField();

        JButton btnConsultar = new JButton("Consultar");

        // Añadir ActionListener para el botón de consulta
        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el campo y el valor ingresado para la consulta
                String campo = txtCampo.getText();
                String valor = txtValor.getText();

                // Implementar la lógica de consulta utilizando el DAO
                List<InscripcionesClass> resultados = inscripcionesDAO.consultarPorCampoYValor(campo, valor);

                // Mostrar los resultados en una ventana o área de texto

            }
        });

        // Añadir componentes al panel de consulta
        consultarPanel.add(lblCampo);
        consultarPanel.add(txtCampo);
        consultarPanel.add(lblValor);
        consultarPanel.add(txtValor);
        consultarPanel.add(new JLabel()); // Espacio en blanco
        consultarPanel.add(btnConsultar);

        add(consultarPanel);
    }


}
