package org.example.skelpinternetowy.Hibernate;

import org.example.skelpinternetowy.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ZamowienieDAO {
    public List<Zamowienie> getAllZamowienia(){
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
}
