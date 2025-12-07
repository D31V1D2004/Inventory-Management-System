package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Interfata grafica pentru gestionarea unui sistem de inventar.
 * Aplicatia permite utilizatorului sa se autentifice si sa efectueze operatii diverse pe inventar,
 * cum ar fi adaugarea, actualizarea, stergerea si listarea articolelor.
 */
public class InventoryManagementGUI {
    private Inventory inventory;

    /**
     * Punctul de intrare al aplicatiei. Initializeaza baza de date si lanseaza ecranul de autentificare.
     *
     * @param args Argumente din linia de comanda (nu sunt utilizate).
     */
    public static void main(String[] args) {
        DatabaseManager.initializeDatabase();
        InventoryManagementGUI gui = new InventoryManagementGUI();
        gui.showLogin();
    }

    /**
     * Constructor care initializeaza obiectul inventory utilizat pentru operatiunile pe inventar.
     */
    public InventoryManagementGUI() {
        inventory = new Inventory();
    }

    /**
     * Afiseaza interfata de autentificare, unde utilizatorii pot introduce datele
     * pentru a accesa sistemul de gestionare al inventarului.
     */
    private void showLogin() {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(300, 150);
        loginFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        loginFrame.add(panel);
        placeLoginComponents(panel, loginFrame);

        loginFrame.setVisible(true);
    }

    /**
     * Plaseaza componentele pentru ecranul de autentificare, cum ar fi campurile pentru username/parola si butonul de login.
     *
     * @param panel Panoul in care sunt plasate componentele.
     * @param loginFrame Fereastra care contine panoul de autentificare.
     */
    private void placeLoginComponents(JPanel panel, JFrame loginFrame) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Spatiere intre componente
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);

        // Username Field
        gbc.gridx = 1;
        gbc.gridy = 0;
        JTextField userText = new JTextField(20);
        panel.add(userText, gbc);

        // Password Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);

        // Password Field
        gbc.gridx = 1;
        gbc.gridy = 1;
        JPasswordField passwordText = new JPasswordField(20);
        panel.add(passwordText, gbc);

        // Login Button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton loginButton = new JButton("Login");
        panel.add(loginButton, gbc);

        // Adauga action listener pentru butonul de login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());
                if (username.equals("admin") && password.equals("password")) {
                    JOptionPane.showMessageDialog(loginFrame, "Login successful!");
                    loginFrame.dispose();
                    showInventoryManagement();
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Incorrect username or password. Please try again.");
                }
            }
        });
    }

    /**
     * Afiseaza interfata principala pentru gestionarea inventarului dupa ce utilizatorul s-a autentificat cu succes.
     */
    private void showInventoryManagement() {
        JFrame frame = new JFrame("Inventory Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        frame.add(panel);
        placeInventoryComponents(panel);

        frame.setVisible(true);
    }

    /**
     * Plaseaza componentele in interfata de gestionare a inventarului, incluzand butoane pentru operatii CRUD
     * si o zona de afisare a rezultatelor.
     *
     * @param panel Panoul in care sunt adaugate componentele.
     */
    private void placeInventoryComponents(JPanel panel) {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Spatiere intre componente
        gbc.fill = GridBagConstraints.BOTH;

        // Titlu
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel titleLabel = new JLabel("Inventory Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centrarea textului pe axa orizontala
        titleLabel.setHorizontalTextPosition(SwingConstants.CENTER); // Asigurarea pozitiei corecte
        panel.add(titleLabel, gbc);

        // Adaug constrangeri uniforme pentru butoane
        gbc.weightx = 1.0; // Spatiul pe axa X va fi distribuit egal intre butoane
        gbc.weighty = 0;   // Fara distributie verticala suplimentara
        gbc.gridwidth = 1; // Fiecare buton ocupa o coloana

        // Buton Add Item
        gbc.gridx = 0;
        gbc.gridy = 1;
        JButton addItemButton = new JButton("Add Item");
        panel.add(addItemButton, gbc);

        // Buton Update Item
        gbc.gridx = 1;
        gbc.gridy = 1;
        JButton updateItemButton = new JButton("Update Item");
        panel.add(updateItemButton, gbc);

        // Buton Delete Item
        gbc.gridx = 2;
        gbc.gridy = 1;
        JButton deleteItemButton = new JButton("Delete Item");
        panel.add(deleteItemButton, gbc);

        // Buton Search Items
        gbc.gridx = 0;
        gbc.gridy = 2;
        JButton searchItemButton = new JButton("Search Items");
        panel.add(searchItemButton, gbc);

        // Buton List Items
        gbc.gridx = 1;
        gbc.gridy = 2;
        JButton listItemsButton = new JButton("List Items");
        panel.add(listItemsButton, gbc);

        // Buton Exit
        gbc.gridx = 2;
        gbc.gridy = 2;
        JButton exitButton = new JButton("Exit");
        panel.add(exitButton, gbc);

        // Zona de afisare rezultate
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        panel.add(scrollPane, gbc);


        addItemButton.addActionListener(e -> {
            // Create a panel to hold input fields
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new GridLayout(3, 2, 5, 5));

            // Add labels and input fields to the panel
            inputPanel.add(new JLabel("Item Name:"));
            JTextField nameField = new JTextField();
            inputPanel.add(nameField);

            inputPanel.add(new JLabel("Category:"));
            JTextField categoryField = new JTextField();
            inputPanel.add(categoryField);

            inputPanel.add(new JLabel("Quantity:"));
            JTextField quantityField = new JTextField();
            inputPanel.add(quantityField);

            int result = JOptionPane.showConfirmDialog(panel, inputPanel, "Add New Item", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                String name = nameField.getText();
                String category = categoryField.getText();
                int quantity;

                // Validare pentru "Name"
                if (!name.matches("[a-zA-Z ]+")) { // Permite doar litere si spatii
                    JOptionPane.showMessageDialog(panel, "Item name must contain only letters and spaces.");
                    return;
                }

                // Validare pentru "Category"
                if (!category.matches("[a-zA-Z ]+")) { // Permite doar litere si spatii
                    JOptionPane.showMessageDialog(panel, "Category must contain only letters and spaces.");
                    return;
                }

                // Validare cantitate
                try {
                    quantity = Integer.parseInt(quantityField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Quantity must be a number.");
                    return;
                }

                // Adaugare item in inventory
                inventory.addItem(name, category, quantity);
                outputArea.setText("Item added!");
            }
        });

        updateItemButton.addActionListener(e -> {
            // Create a panel to hold input fields
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new GridLayout(4, 2, 5, 5));

            // Add labels and input fields to the panel
            inputPanel.add(new JLabel("Item ID:"));
            JTextField idField = new JTextField();
            inputPanel.add(idField);

            inputPanel.add(new JLabel("New Name:"));
            JTextField nameField = new JTextField();
            inputPanel.add(nameField);

            inputPanel.add(new JLabel("New Category:"));
            JTextField categoryField = new JTextField();
            inputPanel.add(categoryField);

            inputPanel.add(new JLabel("New Quantity:"));
            JTextField quantityField = new JTextField();
            inputPanel.add(quantityField);


            int result = JOptionPane.showConfirmDialog(panel, inputPanel, "Update Item", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                int id;
                int quantity;
                String name = nameField.getText();
                String category = categoryField.getText();

                // Validare pentru Name
                if (!name.matches("[a-zA-Z ]+")) { // Permite doar litere si spatii
                    JOptionPane.showMessageDialog(panel, "New Name must contain only letters and spaces.");
                    return;
                }

                // Validare pentru Category
                if (!category.matches("[a-zA-Z ]+")) { // Permite doar litere si spatii
                    JOptionPane.showMessageDialog(panel, "New Category must contain only letters and spaces.");
                    return;
                }

                // Validare ID
                try {
                    id = Integer.parseInt(idField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Invalid ID.");
                    return;
                }


                // Validare cantitate
                try {
                    quantity = Integer.parseInt(quantityField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "New Quantity must be a number.");
                    return;
                }

                // Update the item in inventory
                inventory.updateItem(id, name, category, quantity);
                outputArea.setText("Item updated!");
            }
        });


        deleteItemButton.addActionListener(e -> {
            int id;
            try {
                id = Integer.parseInt(JOptionPane.showInputDialog("Add item ID:"));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Invalid ID.");
                return;
            }
            inventory.deleteItem(id);
            outputArea.setText("Item deleted!");
        });

        searchItemButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Add item name:");
            if (!name.matches("[a-zA-Z ]+")) { // Permite doar litere si spatii
                JOptionPane.showMessageDialog(panel, "Name must contain only letters and spaces.");
                return;
            }
            String category = JOptionPane.showInputDialog("Add category name:");
            // Validare pentru "Category"
            if (!category.matches("[a-zA-Z ]+")) { // Permite doar litere si spatii
                JOptionPane.showMessageDialog(panel, "Category must contain only letters and spaces.");
                return;
            }
            outputArea.setText("Search results:\n");
            inventory.searchItems(name,category).forEach(item -> outputArea.append(item.toString() + "\n"));
        });


        listItemsButton.addActionListener(e -> {
            // Afisam o caseta de dialog pentru a cere utilizatorului limita cantitatii
            String input = JOptionPane.showInputDialog(panel, "Do you want to see items with quantity lower than:\n(Leave empty to view all items)", "Filter Items", JOptionPane.QUESTION_MESSAGE);

            // Verificam daca utilizatorul a apasat Cancel
            if (input == null) {
                // Apasarea pe "Cancel" inchide dialogul si opreste actiunea
                JOptionPane.showMessageDialog(panel, "Action canceled. No items displayed.");
                return;
            }

            // Verific daca utilizatorul a lasat caseta goala
            if (input.isEmpty()) {
                // Daca utilizatorul lasa caseta goala, afisez toate articolele
                outputArea.setText("Inventory list (all items):\n");
                inventory.listItems(false).forEach(item -> outputArea.append(item.toString() + "\n"));
                return;
            }

            try {
                // Daca utilizatorul introduce o limita numerica, convertim textul intr-un numar
                int quantityLimit = Integer.parseInt(input);

                // Filtrez articolele cu cantitatea mai mica decat valoarea introdusa
                outputArea.setText("Inventory list (quantity < " + quantityLimit + "):\n");
                inventory.listItems(false) // Obtinem intreaga lista de articole
                        .stream()
                        .filter(item -> item.getQuantity() < quantityLimit) // Filtrare manuala
                        .forEach(item -> outputArea.append(item.toString() + "\n")); // Afisare articole
            } catch (NumberFormatException ex) {
                // Gestionez situatia in care utilizatorul introduce un text nevalid
                JOptionPane.showMessageDialog(panel, "Invalid input. Please enter a valid number.");
            }
        });

        exitButton.addActionListener(e -> System.exit(0));
    }
}