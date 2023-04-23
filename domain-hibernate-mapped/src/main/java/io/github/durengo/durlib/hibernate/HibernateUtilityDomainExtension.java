package io.github.durengo.durlib.hibernate;

import io.github.durengo.durlib.domain.airplane.Airplane;
import io.github.durengo.durlib.domain.airport.*;
import org.h2.tools.Server;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class HibernateUtilityDomainExtension extends HibernateUtility
{
    public static void saveTelemetryByAirportId(int airport_id, Telemetry telemetry) {
        try (Session session = HibernateUtility.getConnectionHandler().openSession()) {
            transaction = session.beginTransaction();
            Airport item = session.get(Airport.class, airport_id);
            item.getTrafficControlTower().getTelemetryList().add(telemetry);
            session.update(item);
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

    public static List<Telemetry> loadTelemetriesByAirportId(String query) {
        try (Session session = HibernateUtility.getConnectionHandler().openSession()) {
            transaction = session.beginTransaction();
            List<Airport> nl;// = session.createQuery(query, classType).list();
            Hibernate.initialize(nl = session.createQuery(query, Airport.class).list());
            server = Server.createTcpServer().start();
            transaction.commit();
            server.shutdown();
            List<Telemetry> result = new ArrayList<Telemetry>();
            for(Telemetry item : nl.get(0).getTrafficControlTower().getTelemetryList())
            {
                result.add(item);
            }
            return result;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
}
