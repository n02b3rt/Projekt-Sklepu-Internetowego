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
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return klienci;
    }
}
