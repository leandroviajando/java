package airports;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

public class AirportTest {
    @Test
    void testNewAirport() {
        Airport airport = new Airport();
        assertTrue(airport.getConnections().isEmpty());
    }

    @Test
    void testConnectTo() {
        Airport airport1 = new Airport();
        Airport airport2 = new Airport();

        airport1.connectTo(airport2);

        assertTrue(airport1.getConnections().contains(airport2));
        assertTrue(airport2.getConnections().contains(airport1));
        assertEquals(1, airport1.getConnections().size());
        assertEquals(1, airport2.getConnections().size());
    }

    @Test
    void testDisconnect() {
        Airport airport1 = new Airport();
        Airport airport2 = new Airport();

        airport1.connectTo(airport2);
        airport1.disconnect(airport2);

        assertFalse(airport1.getConnections().contains(airport2));
        assertFalse(airport2.getConnections().contains(airport1));
        assertTrue(airport1.getConnections().isEmpty());
        assertTrue(airport2.getConnections().isEmpty());
    }

    @Test
    void testMultipleConnections() {
        Airport hub = new Airport();
        Airport spoke1 = new Airport();
        Airport spoke2 = new Airport();
        Airport spoke3 = new Airport();

        hub.connectTo(spoke1);
        hub.connectTo(spoke2);
        hub.connectTo(spoke3);

        assertEquals(3, hub.getConnections().size());
        assertTrue(hub.getConnections().contains(spoke1));
        assertTrue(hub.getConnections().contains(spoke2));
        assertTrue(hub.getConnections().contains(spoke3));
        assertEquals(1, spoke1.getConnections().size());
        assertEquals(1, spoke2.getConnections().size());
        assertEquals(1, spoke3.getConnections().size());
    }

    @Test
    void testCannotConnectToSelf() {
        Airport airport = new Airport();
        assertThrows(IllegalArgumentException.class, () -> airport.connectTo(airport));
    }

    @Test
    void testCannotConnectToNull() {
        Airport airport = new Airport();
        assertThrows(IllegalArgumentException.class, () -> airport.connectTo(null));
    }

    @Test
    void testCannotDisconnectUnconnected() {
        Airport airport1 = new Airport();
        Airport airport2 = new Airport();
        assertThrows(IllegalArgumentException.class, () -> airport1.disconnect(airport2));
    }

    @Test
    void testCannotDisconnectNull() {
        Airport airport = new Airport();
        assertThrows(IllegalArgumentException.class, () -> airport.disconnect(null));
    }

    @Test
    void testConnectionsSetImmutable() {
        Airport airport1 = new Airport();
        Airport airport2 = new Airport();
        airport1.connectTo(airport2);

        Set<Airport> connections = airport1.getConnections();
        assertThrows(UnsupportedOperationException.class, () -> connections.add(new Airport()));
        assertThrows(UnsupportedOperationException.class, () -> connections.remove(airport2));
    }

    @Test
    void testComplexNetwork() {
        Airport hub1 = new Airport();
        Airport hub2 = new Airport();
        Airport spoke1 = new Airport();
        Airport spoke2 = new Airport();

        // Create a network where:
        // - hub1 is connected to hub2, spoke1
        // - hub2 is connected to hub1, spoke2
        hub1.connectTo(hub2);
        hub1.connectTo(spoke1);
        hub2.connectTo(spoke2);

        // Test hub1 connections
        assertTrue(hub1.getConnections().contains(hub2));
        assertTrue(hub1.getConnections().contains(spoke1));
        assertFalse(hub1.getConnections().contains(spoke2));
        assertEquals(2, hub1.getConnections().size());

        // Test hub2 connections
        assertTrue(hub2.getConnections().contains(hub1));
        assertTrue(hub2.getConnections().contains(spoke2));
        assertFalse(hub2.getConnections().contains(spoke1));
        assertEquals(2, hub2.getConnections().size());

        // Test spoke connections
        assertTrue(spoke1.getConnections().contains(hub1));
        assertTrue(spoke2.getConnections().contains(hub2));
        assertEquals(1, spoke1.getConnections().size());
        assertEquals(1, spoke2.getConnections().size());

        // Remove a connection and verify
        hub1.disconnect(hub2);
        assertFalse(hub1.getConnections().contains(hub2));
        assertFalse(hub2.getConnections().contains(hub1));
        assertEquals(1, hub1.getConnections().size());
        assertEquals(1, hub2.getConnections().size());
    }
}
