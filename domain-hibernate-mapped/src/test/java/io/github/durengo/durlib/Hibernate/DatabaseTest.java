package io.github.durengo.durlib.Hibernate;

import io.github.durengo.durlib.airplane.Airplane;
import io.github.durengo.durlib.airplane.AirplaneType;
import io.github.durengo.durlib.airport.*;
import io.github.durengo.durlib.people.*;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class DatabaseTest {
    Airport airport = null;
    public DatabaseTest(){
        Airspace airspace = new Airspace(3, 1000.0, AirspaceClass.ClassDelta, 0);
        Runway runway = new Runway(5, 400.0, 150.0);
        Telemetry telemetry = new Telemetry(6, Date.from(Instant.parse("2023-03-11T19:26:50.848+02:00")), 100.0, 32.0, 5.0, 30.0, 80.0);
        ControlTowerOperator operator = new ControlTowerOperator(4, "Oper", "Ator", "1970-06-10", 70.0);
        List<Runway> runwayList = new ArrayList<Runway>();
        runwayList.add(runway);
        List<Telemetry> telemetryList = new ArrayList<Telemetry>();
        telemetryList.add(telemetry);
        List<ControlTowerOperator> controlTowerOperatorList = new ArrayList<ControlTowerOperator>();
        controlTowerOperatorList.add(operator);
        TrafficControlTower tower = new TrafficControlTower(2, airspace, runwayList, telemetryList, controlTowerOperatorList);

        List<Airplane> airplaneList = new ArrayList<Airplane>();
        List<CrewMember> memberList1 = new ArrayList<CrewMember>();
        List<Passenger> passengerList1 = new ArrayList<Passenger>();


        CrewMember member1 = new CrewMember(8, "Cap", "Capn", "1980-02-01", 80.0);
        member1.setType(CrewMemberType.Pilot);
        memberList1.add(member1);

        Passenger passenger1 = new Passenger(9, "Seb", "Ter", "2000-03-23", 100.0, new Luggage(10, "Toothbrush", 5.0), new Ticket(11, "Lebanon", 1));
        Passenger passenger2 = new Passenger(12, "Sebas", "Terme", "2000-03-23", 100.0, new Luggage(13, "Bat", 3.0), new Ticket(14, "Lebanon", 2));
        passengerList1.add(passenger1);
        passengerList1.add(passenger2);

        Airplane airplane1 = new Airplane(AirplaneType.LightJet);
        airplane1.setId(7);
        airplane1.setDestinationAirport("Lebanon");
        airplane1.setCrewList(memberList1);
        airplane1.setPassengerList(passengerList1);
        airplane1.setCurrentPassengers(3);
        airplaneList.add(airplane1);

        List<CrewMember> memberList2 = new ArrayList<CrewMember>();
        List<Passenger> passengerList2 = new ArrayList<Passenger>();

        CrewMember member2 = new CrewMember(16, "Cap", "Capn", "1980-02-01", 80.0);
        member2.setType(CrewMemberType.Pilot);
        memberList2.add(member2);

        Passenger passenger3 = new Passenger(17, "Sebas", "Terme", "2000-03-23", 100.0, new Luggage(13, "Bat", 3.0), new Ticket(18, "America", 0));
        passengerList2.add(passenger3);

        Airplane airplane2 = new Airplane(AirplaneType.JumboJet);
        airplane2.setId(15);
        airplane2.setDestinationAirport("America");
        airplane2.setCrewList(memberList2);
        airplane2.setPassengerList(passengerList2);
        airplane2.setCurrentPassengers(2);
        airplaneList.add(airplane2);

        airport = new Airport(1, "Waterbury Airport", AirportSize.SmallAirport, -18.1332, -27.2473, "Waterbury", "Haiti", tower, airplaneList);
    }
    @Test
    public void saveAndLoadToDatabase() throws IOException, URISyntaxException {
//        try
//        {
//            HibernateUtility.getConnectionHandler();
//        }
//        catch (HibernateException e)
//        {
//            e.printStackTrace();
//            assumeTrue("CANNOT CONNECT TO DATABASE, MAKE SURE DATABASE IS RUNNING FOR THE TEST TO CONTINUE", false);
//        }
//        ClassLoader classLoader = Setup.class.getClassLoader();
//        URL resourceUrl = null;
//
//        HibernateUtility.setContextClass(Airport.class);
//        HibernateUtility.clearDatabase();
//
//        JaxbUtility.setContext(Airport.class);
//        //JaxbUtility.setXsdSchema("target/classes/Airport.xsd");
//        resourceUrl = classLoader.getResource("Airport.xsd");
//        JaxbUtility.setXsdSchema(resourceUrl.getPath());
//        JaxbUtility.setOutputType(JaxbUtilityOutputType.StringWriter);
//
//        //String file = new String(Files.readAllBytes(Paths.get("target/classes/test_small.xml")), StandardCharsets.UTF_8) + "\n";
//        resourceUrl = classLoader.getResource("test_small.xml");
//
//        String file = new String(Files.readAllBytes(Paths.get(resourceUrl.toURI())), StandardCharsets.UTF_8) + "\n";
//        StringWriter sw = new StringWriter();
//        sw.write(file);
//        JaxbUtility.setWriter(sw);
//        Airport expectedAirport = JaxbUtility.transformToPOJO(Airport.class);
//        expectedAirport.resetIds();
//        HibernateUtility.saveObject(expectedAirport);
//        airport.resetIds();
//        HibernateUtility.saveObject(airport);
//
//        List<Airport> airportList = HibernateUtility.loadObjects(Airport.class);
//        for(Airport port: airportList)
//        {
//            port.resetIds();
//        }
//
//        assertEquals(airportList.get(0).toString(), airportList.get(1).toString());
    }
}
