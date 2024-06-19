package org.example.skelpinternetowy.Hibernate;

import org.example.skelpinternetowy.HibernateUtil;
import org.example.skelpinternetowy.SklepInternetowy;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

/**
 * Klasa DAO (Data Access Object) dla zamówień.
 * Odpowiada za interakcje z bazą danych dotyczące obiektów Zamowienie.
 */
public class ZamowienieDAO {

    /**
     * Pobiera wszystkie zamówienia z bazy danych.
     *
     * Funkcja otwiera sesję Hibernate i rozpoczyna transakcję. Następnie
     * wykonuje zapytanie HQL, aby pobrać wszystkie zamówienia z bazy danych.
     * Po pobraniu zamówień zatwierdza transakcję i zamyka sesję. W przypadku
     * wystąpienia błędu wycofuje transakcję i wypisuje ślad stosu błędu.
     *
     * @return lista wszystkich zamówień
     */
    public static List<Zamowienie> getAllZamowienia() {
        Transaction transaction = null; // Transakcja Hibernate
        List<Zamowienie> zamowienia = null; // Lista zamówień

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Rozpoczyna transakcję
            zamowienia = session.createQuery("from Zamowienie", Zamowienie.class).list(); // Pobiera wszystkie zamówienia
            transaction.commit(); // Zatwierdza transakcję
            session.close(); // Zamyka sesję
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Wycofuje transakcję w przypadku błędu
            }
            e.printStackTrace();
        }
        return zamowienia; // Zwraca listę zamówień
    }

    /**
     * Dodaje nowe zamówienie do bazy danych.
     *
     * Funkcja pobiera ostatnie zamówienie, aby uzyskać ostatni numer zamówienia
     * i identyfikator. Następnie dla każdego produktu w przekazanej liście produktów
     * tworzy nowe zamówienie, ustawia odpowiednie pola (klient, produkt, data zamówienia)
     * i zapisuje zamówienie w bazie danych. Na końcu zatwierdza transakcję i zamyka sesję.
     *
     * @param CheckOutProduct lista produktów do zamówienia
     */
    public void addZamowienie(List<Produkt> CheckOutProduct) {
        // Pobiera ostatnie zamówienie, aby uzyskać ostatni numer zamówienia i identyfikator
        Zamowienie lastOrder = getLastZamowienie();

        Integer lastOrderId = lastOrder.getIdZamowienia() + 1;
        Integer lastOrderNumber = lastOrder.getNrZamowienia() + 1;
        Klient client = SklepInternetowy.actualKlient; // Aktualny klient
        LocalDate date = LocalDate.now(); // Aktualna data

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction(); // Rozpoczyna transakcję
            for (Produkt product : CheckOutProduct) {
                // Tworzy nowe zamówienie dla każdego produktu
                Zamowienie noweZamowienie = new Zamowienie();
                noweZamowienie.setIdZamowienia(lastOrderId);
                noweZamowienie.setNrZamowienia(lastOrderNumber);
                noweZamowienie.setKlient(client);
                noweZamowienie.setProdukt(product);
                noweZamowienie.setDataZamowienia(date);
                session.save(noweZamowienie); // Zapisuje zamówienie w bazie danych
                lastOrderId++; // Zwiększa identyfikator zamówienia
            }
            transaction.commit(); // Zatwierdza transakcję
            session.close(); // Zamyka sesję
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Pobiera wszystkie zamówienia aktualnego klienta.
     *
     * Funkcja otwiera sesję Hibernate i rozpoczyna transakcję. Następnie
     * wykonuje zapytanie HQL, aby pobrać wszystkie zamówienia aktualnego
     * klienta z bazy danych. Po pobraniu zamówień zatwierdza transakcję
     * i zamyka sesję. W przypadku wystąpienia błędu wycofuje transakcję
     * i wypisuje ślad stosu błędu.
     *
     * @return lista zamówień aktualnego klienta
     */
    public List<Zamowienie> getAllClientOrder() {
        Transaction transaction = null; // Transakcja Hibernate
        List<Zamowienie> zamowienia = null; // Lista zamówień

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Rozpoczyna transakcję

            // Zapytanie HQL do pobrania zamówień aktualnego klienta
            String hql = "FROM Zamowienie WHERE klient.id = :clientId ORDER BY id DESC";
            Query<Zamowienie> query = session.createQuery(hql, Zamowienie.class);
            query.setParameter("clientId", SklepInternetowy.actualKlient.getIdKlienta());

            zamowienia = query.list(); // Wykonuje zapytanie i pobiera wyniki
            transaction.commit(); // Zatwierdza transakcję
            session.close(); // Zamyka sesję
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Wycofuje transakcję w przypadku błędu
            }
            e.printStackTrace();
        }
        return zamowienia; // Zwraca listę zamówień
    }

    /**
     * Pobiera ostatnie zamówienie z bazy danych.
     *
     * Funkcja otwiera sesję Hibernate i rozpoczyna transakcję. Następnie
     * wykonuje zapytanie HQL, aby pobrać ostatnie zamówienie z bazy danych,
     * sortując je malejąco po identyfikatorze i ograniczając wynik do jednego
     * zamówienia. Po pobraniu zamówienia zatwierdza transakcję i zamyka sesję.
     * W przypadku wystąpienia błędu wycofuje transakcję i wypisuje ślad stosu błędu.
     *
     * @return ostatnie zamówienie
     */
    public Zamowienie getLastZamowienie() {
        Transaction transaction = null; // Transakcja Hibernate
        Zamowienie lastZamowienie = null; // Ostatnie zamówienie

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Rozpoczyna transakcję

            // Zapytanie HQL do pobrania ostatniego zamówienia
            String hql = "FROM Zamowienie ORDER BY id DESC"; // Zastąp "id" odpowiednią kolumną
            Query<Zamowienie> query = session.createQuery(hql, Zamowienie.class);
            query.setMaxResults(1); // Ograniczenie do jednego wyniku

            lastZamowienie = query.uniqueResult(); // Pobiera unikalny wynik

            transaction.commit(); // Zatwierdza transakcję
            session.close(); // Zamyka sesję
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Wycofuje transakcję w przypadku błędu
            }
            e.printStackTrace();
        }
        return lastZamowienie; // Zwraca ostatnie zamówienie
    }

    /**
     * Wyświetla zamówienia.
     *
     * Funkcja iteruje przez listę zamówień i wyświetla ich szczegóły
     * (identyfikator zamówienia, numer zamówienia, klient, produkt, data zamówienia)
     * na konsoli.
     *
     * @param zamowienies lista zamówień do wyświetlenia
     */
    public static void wyswietlZamowienia(List<Zamowienie> zamowienies) {
        for (Zamowienie zamowienie : zamowienies) {
            // Wyświetla szczegóły zamówienia
            System.out.println("ID: " + zamowienie.getIdZamowienia() + " NR: " + zamowienie.getNrZamowienia() +
                    " Klient: " + zamowienie.getKlient() + " Produkt: " + zamowienie.getProdukt() +
                    " Data: " + zamowienie.getDataZamowienia());
        }
    }
}
