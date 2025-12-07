# Inventory Management System 📦

O aplicație desktop dezvoltată în **Java** pentru gestionarea stocurilor unui magazin sau depozit. Aplicația folosește o interfață grafică (GUI) construită cu **Java Swing** și o bază de date **MySQL** pentru persistența datelor.

## 🚀 Funcționalități Principale

* **Autentificare:** Sistem de login simplu pentru acces securizat.
* **Adăugare Produse:** Posibilitatea de a introduce noi articole în inventar (Nume, Categorie, Cantitate).
* **Actualizare Stoc:** Modificarea cantității sau a detaliilor produselor existente.
* **Ștergere:** Eliminarea produselor din baza de date.
* **Căutare:** Căutarea rapidă a produselor după nume.
* **Filtrare Stoc Scăzut:** Vizualizarea rapidă a produselor care necesită reaprovizionare (cantitate < 10).
* **Listare:** Afișarea tuturor produselor din inventar.

## 🛠️ Tehnologii Utilizate

* **Limbaj:** Java (JDK 8+)
* **Interfață Grafică:** Java Swing & AWT
* **Bază de date:** MySQL
* **Conectivitate:** JDBC (Java Database Connectivity)

## ⚙️ Configurare și Instalare

Pentru a rula acest proiect pe calculatorul tău, urmează pașii de mai jos:

### 1. Cerințe de sistem
* Java Development Kit (JDK) instalat.
* Server MySQL instalat și rulând.
* Un IDE (IntelliJ IDEA, Eclipse) sau terminal.

### 2. Configurare Bază de Date
Creează o bază de date MySQL numită `proiect_schema`. Aplicația va crea automat tabela `inventory` la prima rulare, dar baza de date trebuie să existe deja.

Poți rula această comandă în consola MySQL:
```sql
CREATE DATABASE proiect_schema;
