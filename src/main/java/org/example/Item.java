package org.example;

/**
 * Reprezinta un articol cu un identificator unic, nume, categorie si cantitate.
 * Aceasta clasa ofera metode pentru a prelua informatii despre articol si a le formata intr-un string.
 */
public class Item {
    private final int id; // Identificator unic pentru articol
    private String name; // Numele articolului
    private String category; // Categoria din care face parte articolul
    private int quantity; // Cantitatea disponibila a articolului

    /**
     * Construieste un obiect de tip Item cu id-ul, numele, categoria si cantitatea specificata.
     *
     * @param id       Identificatorul unic al articolului (trebuie sa fie > 0)
     * @param name     Numele articolului (nu poate fi null sau gol)
     * @param category Categoria articolului (nu poate fi null sau gol)
     * @param quantity Cantitatea disponibila a articolului (trebuie sa fie >= 0)
     * @throws IllegalArgumentException Daca id-ul, numele, categoria sau cantitatea nu sunt valide
     */
    public Item(int id, String name, String category, int quantity) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID-ul trebuie sa fie pozitiv.");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Numele nu poate fi null sau gol.");
        }
        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException("Categoria nu poate fi null sau goala.");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Cantitatea nu poate fi negativa.");
        }

        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
    }

    /**
     * Modifica numele articolului.
     *
     * @param name Noul nume al articolului (nu poate fi null sau gol)
     * @throws IllegalArgumentException Daca numele este null sau gol
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Numele nu poate fi null sau gol.");
        }
        this.name = name;
    }

    /**
     * Modifica categoria articolului.
     *
     * @param category Noua categorie a articolului (nu poate fi null sau goala)
     * @throws IllegalArgumentException Daca categoria este null sau goala
     */
    public void setCategory(String category) {
        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException("Categoria nu poate fi null sau goala.");
        }
        this.category = category;
    }

    /**
     * Modifica cantitatea articolului.
     *
     * @param quantity Noua cantitate a articolului (trebuie sa fie >= 0)
     * @throws IllegalArgumentException Daca cantitatea este mai mica decat 0
     */
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Cantitatea nu poate fi negativa.");
        }
        this.quantity = quantity;
    }

    /**
     * Returneaza identificatorul unic al articolului.
     *
     * @return Id-ul articolului
     */
    public int getId() {
        return id;
    }

    /**
     * Returneaza numele articolului.
     *
     * @return Numele articolului
     */
    public String getName() {
        return name;
    }

    /**
     * Returneaza categoria articolului.
     *
     * @return Categoria articolului
     */
    public String getCategory() {
        return category;
    }

    /**
     * Returneaza cantitatea disponibila a articolului.
     *
     * @return Cantitatea articolului
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returneaza o reprezentare sub forma de string a articolului in formatul:
     * "ID: {id}, Name: {name}, Category: {category}, Quantity: {quantity}"
     *
     * @return O reprezentare sub forma de string a articolului
     */
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Category: " + category + ", Quantity: " + quantity;
    }
}
