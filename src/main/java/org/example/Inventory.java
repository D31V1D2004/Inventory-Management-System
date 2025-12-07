package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa Inventory gestioneaza un sistem de inventar utilizand o baza de date SQLite sau MySQL.
 * Permite operatii CRUD (Creare, Citire, Actualizare, Stergere) pe o tabela `items`.
 */
public class Inventory {

    private List<Item> inventoryItems;


    /**
     * Constructorul initializeaza baza de date si creeaza tabela `inventory` daca aceasta nu exista.
     * Aceasta metoda ruleaza un script SQL pentru a se asigura ca structura bazei de date este pregatita.
     */
    public Inventory() {
        inventoryItems = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiect_schema",
                "root", "password")) {
            // Script SQL pentru crearea tabelei 'invemtory' daca nu exista
            String createTableSQL = """
                CREATE TABLE IF NOT EXISTS inventory (
                    id INTEGER PRIMARY KEY AUTO_INCREMENT,
                    name TEXT NOT NULL,
                    category TEXT NOT NULL,
                    quantity INTEGER NOT NULL
                );
                """;
            // Executa scriptul de creare a tabelei
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(createTableSQL);
            }
        } catch (SQLException e) {
            // Afiseaza un mesaj de eroare in cazul unei exceptii
            System.err.println("Error initialization database: " + e.getMessage());
        }
    }

    /**
     * Adauga un articol nou in tabela `inventory`.
     * Aceasta metoda accepta parametrii necesari (nume, categorie, cantitate)
     * si insereaza datele in baza de date.
     *
     * @param name     Numele articolului.
     * @param category Categoria articolului.
     * @param quantity Cantitatea articolului.
     */
    public void addItem(String name, String category, int quantity) {
        // Interogare SQL pentru inserarea unui articol nou
        String sql = "INSERT INTO INVENTORY(name, category, quantity) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiect_schema",
                "root", "password");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Seteaza parametrii interogarii
            pstmt.setString(1, name);
            pstmt.setString(2, category);
            pstmt.setInt(3, quantity);
            // Executa interogarea
            pstmt.executeUpdate();
            System.out.println("Item added successfully:");
        } catch (SQLException e) {
            // Mesaj de eroare daca inserarea esueaza
            System.err.println("Error adding item: " + e.getMessage());
        }
    }

    /**
     * Actualizeaza un articol existent pe baza ID-ului sau.
     * Valoarea pentru fiecare camp este inlocuita de valorile oferite ca parametri.
     *
     * @param id       ID-ul articolului ce va fi actualizat.
     * @param name     Noul nume al articolului.
     * @param category Noua categorie a articolului.
     * @param quantity Noua cantitate a articolului.
     */
    public void updateItem(int id, String name, String category, int quantity) {
        // Interogare SQL pentru actualizarea unui articol
        String sql = "UPDATE INVENTORY SET name = ?, category = ?, quantity = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiect_schema",
                "root", "password");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Seteaza parametrii interogarii pentru actualizare
            pstmt.setString(1, name);
            pstmt.setString(2, category);
            pstmt.setInt(3, quantity);
            pstmt.setInt(4, id);

            // Verifica cate randuri au fost afectate de actualizare
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Item updated successfully:");
            } else {
                System.out.println("Item not found.");
            }
        } catch (SQLException e) {
            System.err.println("Update item error: " + e.getMessage());
        }
    }

    /**
     * Sterge un articol din tabela `inventory` pe baza ID-ului.
     *
     * @param id ID-ul articolului ce va fi sters.
     */
    public void deleteItem(int id) {
        // Interogare SQL pentru stergerea unui articol
        String sql = "DELETE FROM INVENTORY WHERE id = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiect_schema",
                "root", "password");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Seteaza ID-ul articolului ce trebuie sters
            pstmt.setInt(1, id);

            // Executa stergerea si verifica cate randuri au fost afectate
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Item deleted successfully.");
            } else {
                System.out.println("Item not found.");
            }
        } catch (SQLException e) {
            System.err.println("Deleting item error: " + e.getMessage());
        }
    }

    /**
     * Cauta articole in tabela `inventory` care se potrivesc partial sau complet cu numele si categoria oferite.
     *
     * @param name Parte din nume (folosind operatorul LIKE).
     * @return Lista de articole care se potrivesc cu criteriile de cautare.
     */
    public List<Item> searchItems(String name, String category) {
        List<Item> items = new ArrayList<>();
        // Interogare SQL pentru cautare folosind LIKE
        String sql = "SELECT * FROM INVENTORY WHERE name LIKE ? AND category LIKE ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiect_schema",
                "root", "password");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Seteaza parametrii pentru cautare
            pstmt.setString(1, "%" + name + "%");
            pstmt.setString(2, "%" + category + "%");

            // Ruleaza interogarea si adauga rezultatele in lista
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                items.add(new Item(rs.getInt("id"), rs.getString("name"), rs.getString("category"), rs.getInt("quantity")));
            }
        } catch (SQLException e) {
            System.err.println("Search items error: " + e.getMessage());
        }
        return items;
    }

    /**
     * Listeaza articolele din tabela `inventory`, optional filtrate dupa cantitate mica.
     *
     * @param lowStock Daca este true, filtreaza articolele cu stoc mai mic de 10.
     * @return Lista cu toate articolele (sau cu articolele cu stoc scazut, daca este specificat).
     */
    public List<Item> listItems(boolean lowStock) {
        List<Item> items = new ArrayList<>();
        // Interogare SQL de baza pentru listare
        String sql = "SELECT * FROM INVENTORY";
        if (lowStock) {
            // Filtreaza articolele cu stoc mai mic de 10
            sql += " WHERE quantity < 10";
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiect_schema",
                "root", "password");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // Adauga fiecare articol in lista
            while (rs.next()) {
                items.add(new Item(rs.getInt("id"), rs.getString("name"), rs.getString("category"), rs.getInt("quantity")));
            }
        } catch (SQLException e) {
            System.err.println("Listing Items error: " + e.getMessage());
        }
        return items;
    }

}