package org.example.skelpinternetowy.Hibernate;

import org.example.skelpinternetowy.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

/**
 * Klasa DAO (Data Access Object) dla klientów.
 * Odpowiada za interakcje z bazą danych dotyczące obiektów Klient.
 */
public class KlientDAO {

    /**
     * Pobiera wszystkich klientów z bazy danych.
     *
     * Funkcja otwiera sesję Hibernate i rozpoczyna transakcję. Następnie
     * wykonuje zapytanie HQL, aby pobrać wszystkich klientów z bazy danych.
     * Po pobraniu klientów zatwierdza transakcję i zamyka sesję. W przypadku
     * wystąpienia błędu, wyświetla ślad stosu błędu.
     *
     * @return lista wszystkich klientów
     */
    public List<Klient> getAllKlients() {
        List<Klient> klienci = null; // Lista klientów

        // Próbuje otworzyć sesję Hibernate i rozpocząć transakcję
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction(); // Rozpoczyna transakcję
            klienci = session.createQuery("from Klient", Klient.class).list(); // Pobiera wszystkich klientów
            transaction.commit(); // Zatwierdza transakcję
            session.close(); // Zamyka sesję
        } catch (Exception e) {
            e.printStackTrace();
        }
        return klienci; // Zwraca listę klientów
    }

    /**
     * Pobiera klienta na podstawie nazwy (loginu).
     *
     * Funkcja otwiera sesję Hibernate i rozpoczyna transakcję. Następnie
     * wykonuje zapytanie HQL, aby pobrać klienta o podanej nazwie z bazy danych.
     * Po pobraniu klienta zatwierdza transakcję i zamyka sesję. W przypadku
     * wystąpienia błędu, wyświetla ślad stosu błędu.
     *
     * @param nazwa nazwa (login) klienta
     * @return klient o podanej nazwie
     */
    public Klient getByNazwa(String nazwa) {
        Klient klient = null; // Klient

        // Próbuje otworzyć sesję Hibernate i rozpocząć transakcję
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction(); // Rozpoczyna transakcję
            klient = session.createQuery("from Klient where nazwa = :nazwa", Klient.class)
                    .setParameter("nazwa", nazwa)
                    .uniqueResult(); // Pobiera klienta o podanej nazwie
            transaction.commit(); // Zatwierdza transakcję
            session.close(); // Zamyka sesję
        } catch (Exception e) {
            e.printStackTrace();
        }
        return klient; // Zwraca klienta
    }

    /**
     * Dodaje nowego klienta do bazy danych.
     *
     * Funkcja otwiera sesję Hibernate i rozpoczyna transakcję. Następnie
     * zapisuje obiekt klienta w bazie danych. Po zapisaniu klienta zatwierdza
     * transakcję i zamyka sesję. W przypadku wystąpienia błędu, wyświetla
     * ślad stosu błędu.
     *
     * @param klient obiekt Klient do dodania
     */
    public void addKlient(Klient klient) {
        // Próbuje otworzyć sesję Hibernate i rozpocząć transakcję
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction(); // Rozpoczyna transakcję
            session.save(klient); // Zapisuje klienta w bazie danych
            transaction.commit(); // Zatwierdza transakcję
            session.close(); // Zamyka sesję
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Edytuje istniejącego klienta w bazie danych.
     *
     * Funkcja otwiera sesję Hibernate i rozpoczyna transakcję. Następnie
     * aktualizuje obiekt klienta w bazie danych. Po aktualizacji klienta
     * zatwierdza transakcję i zamyka sesję. W przypadku wystąpienia błędu,
     * wyświetla ślad stosu błędu.
     *
     * @param klient obiekt Klient do edycji
     */
    public void editKlient(Klient klient) {
        // Próbuje otworzyć sesję Hibernate i rozpocząć transakcję
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction(); // Rozpoczyna transakcję
            session.update(klient); // Aktualizuje klienta w bazie danych
            transaction.commit(); // Zatwierdza transakcję
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Usuwa istniejącego klienta z bazy danych.
     *
     * Funkcja otwiera sesję Hibernate i rozpoczyna transakcję. Następnie
     * usuwa obiekt klienta z bazy danych. Po usunięciu klienta zatwierdza
     * transakcję i zamyka sesję. W przypadku wystąpienia błędu, wyświetla
     * ślad stosu błędu.
     *
     * @param klient obiekt Klient do usunięcia
     */
    public void deleteKlient(Klient klient) {
        // Próbuje otworzyć sesję Hibernate i rozpocząć transakcję
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction(); // Rozpoczyna transakcję
            session.delete(klient); // Usuwa klienta z bazy danych
            transaction.commit(); // Zatwierdza transakcję
            session.close(); // Zamyka sesję
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
