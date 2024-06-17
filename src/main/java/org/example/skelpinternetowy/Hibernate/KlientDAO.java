package org.example.skelpinternetowy.Hibernate;

import org.example.skelpinternetowy.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class KlientDAO {

    public List<Klient> getAllKlients() {
        Transaction transaction = null;
        List<Klient> klienci = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            klienci = session.createQuery("from Klient", Klient.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return klienci;
    }

    public Klient getByNazwa(String nazwa) {
        Transaction transaction = null;
        Klient klient = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            klient = session.createQuery("from Klient where nazwa = :nazwa", Klient.class)
                    .setParameter("nazwa", nazwa)
                    .uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return klient;
    }

    public void addKlient(Klient klient) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(klient);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Zastanów się nad użyciem logowania w większych projektach
        }
    }
}

