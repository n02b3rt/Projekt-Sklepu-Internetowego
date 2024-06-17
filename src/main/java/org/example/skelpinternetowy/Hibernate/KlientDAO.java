package org.example.skelpinternetowy.Hibernate;

import org.example.skelpinternetowy.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class KlientDAO {

    // Pobiera wszystkich klientów
    public List<Klient> getAllKlients() {
        List<Klient> klienci = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            klienci = session.createQuery("from Klient", Klient.class).list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return klienci;
    }

    // Pobiera klienta na podstawie nazwy
    public Klient getByNazwa(String nazwa) {
        Klient klient = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            klient = session.createQuery("from Klient where nazwa = :nazwa", Klient.class)
                    .setParameter("nazwa", nazwa)
                    .uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return klient;
    }

    // Dodaje nowego klienta
    public void addKlient(Klient klient) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(klient);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Edytuje istniejącego klienta
    public void editKlient(Klient klient) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(klient);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}