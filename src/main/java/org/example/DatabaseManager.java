package org.example;

import java.sql.*;

/**
 * Gestioneaza conexiunea si operatiile cu baza de date pentru tabela "inventory".
 */
public class DatabaseManager {
    private static Connection connection;

    /**
     * Creeaza conexiunea la baza de date si initializeaza tabela "inventory" daca nu exista.
     *
     * @return Conexiunea la baza de date sau {@code null} in caz de eroare.
     */
    public static Connection initializeDatabase() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiect_schema",
                    "root", "password");
            System.out.println("Database connected successfully!");




        } catch (SQLException e) {
            System.err.println("Error initializing the database: " + e.getMessage());
        }
        return null;
    }


    /**
     * Adauga un produs nou in tabela "inventory".
     *
     * @param name     Numele produsului.
     * @param category Categoria produsului.
     * @param quantity Cantitatea produsului.
     */
    public static void addItem(String name, String category, int quantity) {
        String insertSQL = "INSERT INTO inventory(name, category, quantity) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, name);
            pstmt.setString(2, category);
            pstmt.setInt(3, quantity);
            pstmt.executeUpdate();
            System.out.println("Item added successfully: " + name);
        } catch (SQLException e) {
            System.err.println("Error adding item: " + e.getMessage());
        }
    }

    /**
     * Actualizeaza informatiile unui produs existent.

     * @param name     Numele produsului.
     * @param newName  Noul nume al produsului.
     * @param category Noua categorie a produsului.
     * @param quantity Noua cantitate a produsului.
     */
    public static void updateItem(String name, String newName, String category, int quantity) {
        String updateSQL = "UPDATE inventory SET name = ?, category = ?, quantity = ? WHERE name = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
            pstmt.setString(1, newName);
            pstmt.setString(2, category);
            pstmt.setInt(3, quantity);
            pstmt.setString(4, name);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Item updated successfully: Name " + name);
            } else {
                System.out.println("No item with Name \"" + name + "\" found.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating item: " + e.getMessage());
        }
    }

    /**
     * Sterge un produs din tabela "inventory" folosind ID-ul acestuia.
     *
     * @param id ID-ul produsului care trebuie sters.
     */
    public static void deleteItem(int id) {
        String deleteSQL = "DELETE FROM inventory WHERE name = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Item deleted successfully: ID " + id);
            } else {
                System.out.println("No item with ID " + id + " found.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting item: " + e.getMessage());
        }
    }



    /**
     * Cauta produse in tabela dupa nume si categorie.
     *
     * @param name     Numele (sau o parte din nume) produsului pentru cautare.
     * @param category Categoria (sau o parte din categorie) produsului pentru cautare.
     */
    public static void searchItems(String name, String category) {
        String searchSQL = "SELECT * FROM inventory WHERE name LIKE ? AND category LIKE ?";

        try (PreparedStatement pstmt = connection.prepareStatement(searchSQL)) {
            pstmt.setString(1, "%" + name + "%");
            pstmt.setString(2, "%" + category + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("Search Results:");
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") +
                            ", Category: " + rs.getString("category") + ", Quantity: " + rs.getInt("quantity"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error searching items: " + e.getMessage());
        }
    }

    /**
     * Listeaza toate produsele din tabela sau doar produsele cu stoc scazut.
     *
     * @param lowStock Daca este adevarat, listeaza doar produsele cu cantitate mai mica de 10.
     */
    public static void listItems(boolean lowStock) {
        String listSQL = lowStock ?
                "SELECT * FROM inventory WHERE quantity < 10" :
                "SELECT * FROM inventory";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(listSQL)) {

            System.out.println(lowStock ? "Low Stock Items:" : "All Inventory Items:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") +
                        ", Category: " + rs.getString("category") + ", Quantity: " + rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            System.err.println("Error listing items: " + e.getMessage());
        }
    }

    /**
     * Inchide conexiunea la baza de date.
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing the database connection: " + e.getMessage());
        }
    }


}