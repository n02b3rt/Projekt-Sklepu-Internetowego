package org.example.skelpinternetowy.Hibernate;

import org.example.skelpinternetowy.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

/**
 * Klasa DAO (Data Access Object) dla produktów.
 * Odpowiada za interakcje z bazą danych dotyczące obiektów Produkt.
 */
public class ProduktDAO {

    /**
     * Pobiera wszystkie produkty z bazy danych.
     *
     * Funkcja otwiera sesję Hibernate i rozpoczyna transakcję. Następnie
     * wykonuje zapytanie HQL, aby pobrać wszystkie produkty z bazy danych.
     * Po pobraniu produktów zatwierdza transakcję i zamyka sesję. W przypadku
     * wystąpienia błędu wycofuje transakcję i wypisuje ślad stosu błędu.
     *
     * @return lista wszystkich produktów
     */
    public List<Produkt> getAllProducts() {
        Transaction transaction = null; // Transakcja Hibernate
        List<Produkt> produkty = null; // Lista produktów

        // Próbuje otworzyć sesję Hibernate i rozpocząć transakcję
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Rozpoczyna transakcję
            produkty = session.createQuery("from Produkt", Produkt.class).list(); // Pobiera wszystkie produkty
            transaction.commit(); // Zatwierdza transakcję
            session.close(); // Zamyka sesję
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Wycofuje transakcję w przypadku błędu
            }
            e.printStackTrace();
        }
        return produkty; // Zwraca listę produktów
    }
}
