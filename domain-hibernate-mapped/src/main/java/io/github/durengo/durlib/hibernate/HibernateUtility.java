package io.github.durengo.durlib.hibernate;

import org.h2.tools.Server;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.security.InvalidParameterException;
import java.util.List;

/**
 * A simple utility class that wraps over the org.hibernate library, also utilizes some org.h2 library functionality.
 * This utility is the main way to interact with the database.
 * It can save an object, return a list of objects, initialize the primary connection, or clear all records in the database.
 */
public class HibernateUtility {
    protected static String RootElementTableName;
    protected static StandardServiceRegistry registry;
    protected static SessionFactory sessionFactory;
    protected static Transaction transaction = null;
    protected static Server server = null;
    protected static Class<?> contextClass = null;

    /**
     * Utility class cannot have an instance. Throw exception if instantiated.
     */
    protected HibernateUtility() {
        throw new UnsupportedOperationException("This is a utility class and it cannot be instantiated.");
    }

    public static void setHibernateConfiguration(String config)
    {
        Configuration configuration = new Configuration();
        configuration.configure(HibernateUtility.class.getResource(config));
    }

    public static void setContextClass(Class cc)
    {
        contextClass = cc;
    }

    public static void setRootElementTableName(String tableName) { RootElementTableName = tableName; }

    /**
     * Initializes the connection to the database.
     * @return the sessionFactory, which can be used if needed for further processing, like transactions.
     */
    public static SessionFactory getConnectionHandler() {
        if (sessionFactory == null) {
            try {
                registry = new StandardServiceRegistryBuilder().configure().build();

                MetadataSources sources = new MetadataSources(registry);

                Metadata metadata = sources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    /**
     * Removes all records in the database.
     * DOES NOT RESET SEQUENCE COUNTER.
     */
    public static void clearDatabase() {
        List<?> items = loadObjects(contextClass);
        try (Session session = HibernateUtility.getConnectionHandler().openSession()) {
            for (Object item : items) {
                transaction = session.beginTransaction();
                session.delete(item);
                server = Server.createTcpServer().start();
                transaction.commit();
                server.shutdown();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static <T> void clearDatabase(Class<T> objClass) {
        List<T> items = loadObjects(objClass);
        try (Session session = HibernateUtility.getConnectionHandler().openSession()) {
            for (T item : items) {
                transaction = session.beginTransaction();
                session.delete(item);
                server = Server.createTcpServer().start();
                transaction.commit();
                server.shutdown();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * A methods that provides a way to save an object to the database.
     * Ideally the object that will be saved should be mapped within the code-base, and listed in the hibernate.cfg file.
     * @param obj the object to be saved to database.
     * @param <T> the type of the object to be saved to database.
     */
    public static <T> void saveObject(T obj) {
        try (Session session = HibernateUtility.getConnectionHandler().openSession()) {
            transaction = session.beginTransaction();
            session.save(obj);
            transaction.commit();
            System.out.println("Transaction committed successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("Transaction rolled back.");
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Ideally the object that will be loaded should be mapped within the code-base, and listed in the hibernate.cfg file.
     * @param classType the object class type.
     * @param <T> the object type list.
     * @return the object list that will be returned based on the classType provided.
     */
    public static <T> List<T> loadObjects(Class<T> classType) {
        try (Session session = HibernateUtility.getConnectionHandler().openSession()) {
            transaction = session.beginTransaction();
            String query = "from " + classType.getSimpleName() + " ";
            List<T> nl;// = session.createQuery(query, classType).list();
            Hibernate.initialize(nl = session.createQuery(query, classType).list());
            server = Server.createTcpServer().start();
            transaction.commit();
            server.shutdown();
            return nl;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    protected static String createStringQuery(String query)
    {
        return query + " ";
    }

    // TODO: 15/04/2023 use HQL Queries -> :variable
    public static Query createSelectQuery(String selectClause, String whereClause)
    {
        String newQuery;
        if(RootElementTableName != null || RootElementTableName != "")
        {
            newQuery = createStringQuery("SELECT " + selectClause + " FROM " + RootElementTableName + " WHERE " + whereClause);
        }
        else
        {
            throw new InvalidParameterException("RootElementTableName IS NOT SET.");
        }
        return sessionFactory.getCurrentSession().createQuery(newQuery);
    }

    public static String createCountQueryAsString()
    {
        String newQuery;
        if(RootElementTableName != null || RootElementTableName != "")
        {
            newQuery = createStringQuery("SELECT COUNT(*) FROM " + RootElementTableName);
        }
        else
        {
            throw new InvalidParameterException("RootElementTableName IS NOT SET.");
        }
        return newQuery;
    }
    public static String createSelectCountWhereQueryAsString(String whereClause)
    {
        String newQuery;
        if(RootElementTableName != null || RootElementTableName != "")
        {
            newQuery = createStringQuery("SELECT COUNT(*) FROM " + RootElementTableName + " WHERE " + whereClause);
        }
        else
        {
            throw new InvalidParameterException("RootElementTableName IS NOT SET.");
        }
        return newQuery;
    }

    public static String createSelectQueryAsString(String selectClause, String whereClause)
    {
        String newQuery;
        if(RootElementTableName != null || RootElementTableName != "")
        {
            newQuery = createStringQuery("SELECT " + selectClause + " FROM " + RootElementTableName + " WHERE " + whereClause);
        }
        else
        {
            throw new InvalidParameterException("RootElementTableName IS NOT SET.");
        }
        return newQuery;
    }

    public static String createFromWhereQueryAsString(String whereClause)
    {
        String newQuery;
        if(RootElementTableName != null || RootElementTableName != "")
        {
            newQuery = createStringQuery("FROM " + RootElementTableName + " WHERE " + whereClause);
        }
        else
        {
            throw new InvalidParameterException("RootElementTableName IS NOT SET.");
        }
        return newQuery;
    }

//    public static String createInsertIntoQueryAsString(String )
//    {
//
//    }

    public static <T> T loadObject(Class<T> classType, String query) {
        try (Session session = HibernateUtility.getConnectionHandler().openSession()) {
            transaction = session.beginTransaction();
            T nl;// = session.createQuery(query, classType).list();
            Hibernate.initialize(nl = (T) session.createQuery(query, classType).list().get(0));
            server = Server.createTcpServer().start();
            transaction.commit();
            server.shutdown();
            return nl;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    public static <T> long countObjects(Class<T> classType)
    {
        try (Session session = HibernateUtility.getConnectionHandler().openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<T> root = query.from(classType);
            query.select(cb.count(root));
            Long count;
            Hibernate.initialize(count = session.createQuery(query).getSingleResult());
            server = Server.createTcpServer().start();
            transaction.commit();
            server.shutdown();
            return count;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return 0;
    }

    public static <T> List<String> executeQueryGetStringList(String query) {
        try (Session session = HibernateUtility.getConnectionHandler().openSession()) {
            transaction = session.beginTransaction();
            List<String> nl;
            Hibernate.initialize(nl = session.createQuery(query).list());
            server = Server.createTcpServer().start();
            transaction.commit();
            server.shutdown();
            return nl;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Closes the connection to the database.
     */
    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
