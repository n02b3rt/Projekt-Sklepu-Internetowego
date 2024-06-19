package org.example.skelpinternetowy;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Klasa narzędziowa do zarządzania SessionFactory Hibernate.
 */
public class HibernateUtil {
    // Statyczne pole przechowujące instancję SessionFactory
    private static final SessionFactory sessionFactory = buildSessionFactory();

    /**
     * Buduje i zwraca instancję SessionFactory.
     *
     * Ta metoda tworzy instancję SessionFactory na podstawie konfiguracji z pliku
     * hibernate.cfg.xml. Jeśli proces tworzenia SessionFactory się nie powiedzie,
     * metoda wypisuje błąd na standardowe wyjście błędów i rzuca
     * ExceptionInInitializerError.
     *
     * @return instancja SessionFactory
     * @throws ExceptionInInitializerError jeśli wystąpi błąd podczas tworzenia SessionFactory
     */
    private static SessionFactory buildSessionFactory() {
        try {
            // Tworzy SessionFactory z hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Wypisuje błąd i rzuca ExceptionInInitializerError, jeśli tworzenie SessionFactory się nie powiedzie
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Zwraca instancję SessionFactory.
     *
     * Ta metoda zwraca statyczną instancję SessionFactory, która jest tworzona
     * w momencie ładowania klasy HibernateUtil. Metoda jest używana do uzyskania
     * dostępu do SessionFactory w innych częściach aplikacji.
     *
     * @return instancja SessionFactory
     */
    public static SessionFactory getSessionFactory() {
        // Zwraca utworzoną instancję SessionFactory
        return sessionFactory;
    }

    /**
     * Zamyka SessionFactory.
     *
     * Ta metoda zamyka instancję SessionFactory, uwalniając wszystkie zasoby,
     * które były używane. Powinna być wywoływana podczas zamykania aplikacji
     * w celu uporządkowanego zamknięcia połączeń z bazą danych.
     */
    public static void shutdown() {
        // Zamyka instancję SessionFactory
        getSessionFactory().close();
    }
}
