package tennis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TennisGameStateTest {
    @Test
    void testRegularStateConstruction() {
        RegularState state = new RegularState(2, 1); // 30-15
        assertEquals(2, state.getServerScore());
        assertEquals(1, state.getReceiverScore());

        assertThrows(IllegalArgumentException.class, () -> new RegularState(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> new RegularState(0, -1));
        assertThrows(IllegalArgumentException.class, () -> new RegularState(4, 0));
        assertThrows(IllegalArgumentException.class, () -> new RegularState(0, 4));
    }

    @Test
    void testRegularToRegular() {
        TennisGameState state = new RegularState(1, 1); // 15-15
        TennisGameState next = state.serverWinsRally();
        assertEquals(new RegularState(2, 1), next); // 30-15
    }

    @Test
    void testRegularToAdvantage() {
        TennisGameState state = new RegularState(2, 3); // 30-40
        TennisGameState next = state.serverWinsRally();
        assertEquals(new RegularState(3, 3), next); // Deuce
        next = next.serverWinsRally();
        assertEquals(new AdvantageState(true), next); // Advantage server
    }

    @Test
    void testRegularToWon() {
        TennisGameState state = new RegularState(3, 1); // 40-15
        TennisGameState next = state.serverWinsRally();
        assertEquals(new WonState(true), next); // Server wins
    }

    @Test
    void testAdvantageToDeuce() {
        TennisGameState state = new AdvantageState(false); // Advantage receiver
        TennisGameState next = state.serverWinsRally();
        assertEquals(new RegularState(3, 3), next); // Back to deuce
    }

    @Test
    void testAdvantageToWon() {
        TennisGameState state = new AdvantageState(true); // Advantage server
        TennisGameState next = state.serverWinsRally();
        assertEquals(new WonState(true), next); // Server wins
    }

    @Test
    void testWonState() {
        TennisGameState state = new WonState(true); // Server won
        assertThrows(IllegalStateException.class, () -> state.serverWinsRally());
    }

    @Test
    void testEquality() {
        assertEquals(new RegularState(2, 1), new RegularState(2, 1));
        assertNotEquals(new RegularState(2, 1), new RegularState(1, 2));
        assertEquals(new AdvantageState(true), new AdvantageState(true));
        assertNotEquals(new AdvantageState(true), new AdvantageState(false));
        assertEquals(new WonState(true), new WonState(true));
        assertNotEquals(new WonState(true), new WonState(false));

        assertNotEquals(new RegularState(3, 2), new AdvantageState(true));
        assertNotEquals(new AdvantageState(true), new WonState(true));
    }

    @Test
    void testToString() {
        assertEquals("Regular(30-15)", new RegularState(2, 1).toString());
        assertEquals("Advantage(Server)", new AdvantageState(true).toString());
        assertEquals("Advantage(Receiver)", new AdvantageState(false).toString());
        assertEquals("Won(Server)", new WonState(true).toString());
        assertEquals("Won(Receiver)", new WonState(false).toString());
    }
}
