package code.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase principal para ejecutar el codigo
 */
public class Main extends JFrame {
    private JTextField idField;

    /**
     * Metodo que permite mostrar ventana principal de seleccion
     */
    public Main() {
        setTitle("PaCEman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(380, 200);

        // Crear un panel principal con BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Crear un panel para el título
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("PaCEman");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.BLACK);
        titlePanel.add(titleLabel);

        // Crear un panel para los botones y centrarlos
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton startGameButton = new JButton("Jugador");
        JButton textEntryButton = new JButton("Observador");

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new PacManGame(0).setVisible(true);
            }
        });

        textEntryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPanel.removeAll();
                buttonPanel.revalidate();
                buttonPanel.repaint();

                idField = new JTextField(10);
                JButton visualizeButton = new JButton("Visualizar");

                visualizeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        java.lang.String id = idField.getText();
                        dispose();
                        new PacManGame(1, id).setVisible(true);
                    }
                });

                buttonPanel.add(new JLabel("ID: "));
                buttonPanel.add(idField);
                buttonPanel.add(visualizeButton);
            }
        });

        buttonPanel.add(startGameButton);
        buttonPanel.add(textEntryButton);

        // Agregar el panel de botones en el centro del panel principal
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Agregar el panel del título en la parte superior del panel principal
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        // Agregar el panel principal a la ventana
        add(mainPanel);

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);
    }

    /**
     * Permite la ejecucion del programa
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main mainMenu = new Main();
            mainMenu.setVisible(true);
        });
    }
}
