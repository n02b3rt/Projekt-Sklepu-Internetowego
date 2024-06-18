package org.example.skelpinternetowy.Hibernate;

import org.example.skelpinternetowy.HibernateUtil;
import org.example.skelpinternetowy.SklepInternetowy;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class ZamowienieDAO {
    public static List<Zamowienie> getAllZamowienia(){
        Transaction transaction = null;
        List<Zamowienie> zamowenia = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            zamowenia = session.createQuery("from Zamowienie", Zamowienie.class).list();
            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return zamowenia;
    }

    public void addZamowienie(List<Produkt> CheckOutProduct) {
        Zamowienie lastOrder = getLastZamowienie();

        Integer lastOrderId = lastOrder.getIdZamowienia() + 1;
        Integer lastOrderNumber = lastOrder.getNrZamowienia() + 1;
        Klient client = SklepInternetowy.actualKlient;
        LocalDate date = LocalDate.now();


        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            for (Produkt product : CheckOutProduct){
                Zamowienie noweZamowienie = new Zamowienie();
                noweZamowienie.setIdZamowienia(lastOrderId);
                noweZamowienie.setNrZamowienia(lastOrderNumber);
                noweZamowienie.setKlient(client);
                noweZamowienie.setProdukt(product);
                noweZamowienie.setDataZamowienia(date);
                session.save(noweZamowienie);
                lastOrderId++;
            }
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Zamowienie> getAllClientOrder() {
        Transaction transaction = null;
        List<Zamowienie> zamowienia = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "FROM Zamowienie WHERE klient.id = :clientId ORDER BY id DESC";
            Query<Zamowienie> query = session.createQuery(hql, Zamowienie.class);
            query.setParameter("clientId", SklepInternetowy.actualKlient.getIdKlienta());

            zamowienia = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return zamowienia;
    }
    public Zamowienie getLastZamowienie() {
        Transaction transaction = null;
        Zamowienie lastZamowienie = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "FROM Zamowienie ORDER BY id DESC"; // Zastąp "id" odpowiednią kolumną
            Query<Zamowienie> query = session.createQuery(hql, Zamowienie.class);
            query.setMaxResults(1); // Ograniczenie do jednego wyniku

            lastZamowienie = query.uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return lastZamowienie;
    }

    public static void wyswietlZamowienia(List<Zamowienie> zamowienies){
        for (Zamowienie zamowienie : zamowienies){
            System.out.println("ID: " + zamowienie.getIdZamowienia() + " NR: " + zamowienie.getNrZamowienia() + " Klient" + zamowienie.getKlient() + " Produkt " + zamowienie.getProdukt() + " Data: " + zamowienie.getDataZamowienia());
        }
    }
}
