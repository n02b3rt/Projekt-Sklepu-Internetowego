package org.example.skelpinternetowy.Hibernate;

import org.example.skelpinternetowy.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ProduktDAO {
    public List<Produkt> getAllProducts(){
        Transaction transaction = null;
        List<Produkt> produkty = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            produkty = session.createQuery("from Produkt", Produkt.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return produkty;
    }
}
