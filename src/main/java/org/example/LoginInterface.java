package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * O clasa care implementeaza o interfata simpla de login folosind biblioteca Swing.
 * Interfata include campuri pentru username si parola, precum si un buton pentru autentificare.
 */
public class LoginInterface {
    private JTextField usernameField; // Camp pentru introducerea username-ului
    private JPasswordField passwordField; // Camp pentru introducerea parolei
    private JButton loginButton; // Buton pentru autentificare
    private JPanel panelMain; // Container principal pentru componentele UI

    /**
     * Constructor care initializeaza componenta grafica si configureaza interactiunile.
     */
    public LoginInterface() {
        // Creeaza panoul principal si seteaza un layout in grila
        panelMain = new JPanel(new GridLayout(3, 2, 10, 10));

        // Initializeaza campurile de introducere si butonul de login
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        // Adauga etichetele, campurile si butonul la panou
        panelMain.add(new JLabel("Username:"));
        panelMain.add(usernameField);
        panelMain.add(new JLabel("Password:"));
        panelMain.add(passwordField);
        panelMain.add(loginButton);

        // Configureaza actiunea pentru butonul de login
        loginButton.addActionListener(new ActionListener() {
            /**
             * Se executa atunci cand utilizatorul apasa butonul de login.
             * Validarea demonstrativa verifica daca username-ul si parola sunt corecte.
             *
             * @param e Evenimentul declansat de apasarea butonului
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Valideaza username-ul si parola pentru autentificare
                if (username.equals("admin") && password.equals("password")) {
                    // Mesaj pentru autentificare reusita
                    JOptionPane.showMessageDialog(panelMain, "Login successful!");
                } else {
                    // Mesaj pentru autentificare esuata
                    JOptionPane.showMessageDialog(panelMain, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * Punctul de intrare al aplicatiei. Creeaza si afiseaza interfata grafica.
     *
     * @param args Argumente pentru linia de comanda (neutilizate)
     */
    public static void main(String[] args) {
        // Configureaza fereastra principala
        JFrame frame = new JFrame("Login Interface");
        frame.setContentPane(new LoginInterface().panelMain); // Seteaza continutul ferestrei
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Inchide aplicatia la apasarea butonului de inchidere
        frame.pack(); // Ajusteaza dimensiunea ferestrei in functie de continut
        frame.setLocationRelativeTo(null); // Pozitioneaza fereastra in centrul ecranului
        frame.setVisible(true); // Afiseaza fereastra
    }
}
